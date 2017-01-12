package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.domain.TblDatabaseUpdates;
import com.gnadenheimer.mg3.domain.TblRoles;
import com.gnadenheimer.mg3.domain.TblTransferencias;
import com.gnadenheimer.mg3.domain.TblUsers;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import com.panemu.tiwulfx.common.TiwulFXUtil;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.persistence.EntityManager;
import org.apache.derby.drda.NetworkServerControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class App extends Application {

    public static CurrentUser currentUser = CurrentUser.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    Map<String, String> persistenceMap = new HashMap<>();
    private static final BorderPane root = new BorderPane();
    public static Stage mainStage;
    public static Integer periodoFiscal = Preferences.userRoot().node("MG").getInt("PeriodoFiscal", LocalDate.now().getYear());

    /**
     * Just a root getter for the controller to use
     *
     * @return
     */
    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            if (Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "true"))) {
                Properties p = System.getProperties();
                p.setProperty("derby.system.home", Preferences.userRoot().node("MG").get("Datadir", System.getProperty("user.dir") + File.separator + "javadb"));
                p.setProperty("derby.drda.host", "0.0.0.0");
                p.setProperty("derby.language.sequence.preallocator", "1");
                NetworkServerControl server = new NetworkServerControl();
                server.start(null);
            }

            Utils.getInstance().setEntityManagerFactory();

            databaseUpdate();
            //AUTO LOGIN-------------------------------
            currentUser.setUser(null);
            EntityManager entityManager = Utils.getInstance().getEntityManagerFactory().createEntityManager();
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

        TiwulFXUtil.setLocale(new Locale("es", "PY"));

        Scene scene = new Scene(root);
        TiwulFXUtil.setTiwulFXStyleSheet(scene);
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
            LoginManager.getInstance().showLoginScreen();
        } else {
            LoginManager.getInstance().showMainView();
        }

        mainStage = stage;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Warning");
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
            EntityManager entityManager = Utils.getInstance().getEntityManagerFactory().createEntityManager();
            entityManager.getTransaction().begin();

            try {
                Object o = entityManager.createNativeQuery("select count(*) from tbl_users where 1=2").getSingleResult();
            } catch (Exception e) {
                Utils.getInstance().executeSQL("/sql/javadb.sql");
            }

            Boolean hasBackedUp = false;

            try {
                Object o = entityManager.createNativeQuery("select count(*) from tbl_database_updates where 1=2").getSingleResult();
            } catch (Exception e) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160224.sql", hasBackedUp);
            }

            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160219.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160219.sql", hasBackedUp);
            }

            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160219.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160219.sql", hasBackedUp);
            }

            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160323.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160323.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_contabilidad_y_compras.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_contabilidad_y_compras.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160330.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160330.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160409.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160409.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160429.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160429.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160507.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160507.sql", hasBackedUp);
                List<TblTransferencias> listT = entityManager.createQuery("SELECT t FROM TblTransferencias t").getResultList();
                if (listT != null) {
                    for (TblTransferencias t : listT) {
                        if (t.getFechahoraCompromiso() == null) {
                            t.setFechahoraCompromiso(t.getFechahora());
                            entityManager.merge(t);
                        }
                    }
                    entityManager.getTransaction().commit();
                    entityManager.getTransaction().begin();
                }
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160601.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160601.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160715.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160715.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160810.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160810.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160815.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160815.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160902.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160902.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160905.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160905.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20160912.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20160912.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20161104.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20161104.sql", hasBackedUp);
            }
            if (entityManager.find(TblDatabaseUpdates.class, "/sql/javadb_20161115.sql") == null) {
                hasBackedUp = Utils.getInstance().executeUpdateSQL("/sql/javadb_20161115.sql", hasBackedUp);
            }
        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

}
