package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.domain.TblDatabaseUpdates;
import com.gnadenheimer.mg3.domain.TblRoles;
import com.gnadenheimer.mg3.domain.TblUsers;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.prefs.Preferences;

public class App extends MvvmfxCdiApplication {

    @Inject
    EntityManager entityManager;
    @Inject
    LoginManager loginManager;
    @Inject
    CurrentUser currentUser;
    @Inject
    Utils utils;

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    Map<String, String> persistenceMap = new HashMap<>();
    private static final BorderPane root = new BorderPane();
    public static Stage mainStage;
    public static Integer periodoFiscal = Preferences.userRoot().node("MG").getInt("PeriodoFiscal", LocalDate.now().getYear());

    /**
     * Just a root getter for the ui to use
     *
     * @return
     */
    public static BorderPane getRoot() {
        return root;
    }


    @Override
    public void startMvvmfx(final Stage stage) throws Exception {
        try {


            //utils.setEntityManagerFactory();

            databaseUpdate();
            //AUTO LOGIN-------------------------------
            currentUser.setUser(null);
            List<TblUsers> list = entityManager.createQuery("SELECT t FROM TblUsers t").getResultList();
            for (TblUsers user : list) {
                if (user.getNombre().equals("adrian") && BCrypt.checkpw(String.valueOf("adrian"), user.getPassword())) {
                    TblRoles role = new TblRoles();
                    role.setId(4);
                    List<TblRoles> listRoles = new ArrayList();
                    listRoles.add(role);
                    user.setTblRolesList(listRoles);
                    currentUser.setUser(user);
                }
            }
        } catch (Exception ex) {
            App.showException(this.toString(), ex.getMessage(), ex);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
        Properties prop = new Properties();
        prop.load(resourceAsStream);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/mg3.png")));

        stage.setMaximized(true);
        stage.setTitle("MG " + prop.getProperty("project.version") + "." + prop.getProperty("project.build"));
        stage.show();

        //----------------------------------------
        if (currentUser.getUser() == null) {
            loginManager.showLoginScreen();
        } else {
            loginManager.showMainView();
        }

        mainStage = stage;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Consulta");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Warning");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Informacion");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showException(String title, String message, Exception exception) {
        LOGGER.error(title, exception);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Detalles:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    private void databaseUpdate() {
        try {

            entityManager.getTransaction().begin();

            try {
                Object o = entityManager.createNativeQuery("select count(*) from tbl_users where 1=2").getSingleResult();
            } catch (Exception e) {
                utils.executeSQL("/sql/javadb.sql");
            }

            Boolean hasUpdated = false;

            try {
                Object o = entityManager.createNativeQuery("select count(*) from tbl_database_updates where 1=2").getSingleResult();
            } catch (Exception e) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160224.sql", hasUpdated);
                if (!hasUpdated) {
                    App.showException("ERROR de Base de Datos", e.getMessage(), e);
                }
            }

            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160219.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160219.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160219.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160219.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160323.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160323.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_contabilidad_y_compras.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_contabilidad_y_compras.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160330.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160330.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160409.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160409.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160429.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160429.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160507.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160507.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160601.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160601.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160715.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160715.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160810.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160810.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160815.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160815.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160902.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160902.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160905.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160905.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160912.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20160912.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20161104.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20161104.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20161115.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20161115.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20170122.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20170122.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20170123.sql") == null) {
                hasUpdated = utils.executeUpdateSQL("/sql/javadb_20170123.sql", hasUpdated);
                if (!hasUpdated) {
                    return;
                }
            }
            if (hasUpdated) {
                App.showInfo("Exito", "La Base de datos se ha actualizado con exito.");
            }
        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

}
