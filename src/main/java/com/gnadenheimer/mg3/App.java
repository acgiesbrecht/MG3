package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.ui.main.MainView;
import com.gnadenheimer.mg3.ui.main.MainViewModel;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.apache.derby.drda.NetworkServerControl;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.prefs.Preferences;

public class App extends MvvmfxCdiApplication {

    @Inject
    CurrentUser currentUser;
    @Inject
    LoginManager loginManager;
    @Inject
    Logger LOGGER;

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
    public void startMvvmfx(final Stage stage) throws Exception {
        try {
            if (Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "true"))) {
                Properties p = System.getProperties();
                p.setProperty("derby.system.home", Preferences.userRoot().node("MG").get("Datadir", System.getProperty("user.dir") + File.separator + "javadb"));
                p.setProperty("derby.drda.host", "0.0.0.0");
                p.setProperty("derby.language.sequence.preallocator", "1");
                NetworkServerControl server = new NetworkServerControl();
                server.start(null);
            }

        } catch (Exception ex) {
            //App.showException(this.toString(), ex.getMessage(), ex);
            ex.printStackTrace();
        }

        /*TiwulFXUtil.setLocale(new Locale("es", "PY"));

        Scene scene = new Scene(root);
        TiwulFXUtil.setTiwulFXStyleSheet(scene);
        stage.setScene(scene);*/

        final ViewTuple<MainView, MainViewModel> tuple = FluentViewLoader.fxmlView(MainView.class).load();
        //final ViewTuple<TblEntidadesView, TblEntidadesViewModel> tuple = FluentViewLoader.fxmlView(TblEntidadesView.class).load();

        // Locate View for loaded FXML file
        final Parent view = tuple.getView();

        final Scene scene = new Scene(view);

        scene.getStylesheets().add(this.getClass().getResource("/css/main.css").toExternalForm());

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


        mainStage = stage;
        loginManager.showLoginView();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    public Boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Consulta");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }





}
