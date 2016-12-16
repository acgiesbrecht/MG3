package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.utils.Utils;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.derby.drda.NetworkServerControl;

public class App extends Application {

    public static EntityManagerFactory factory;
    Map<String, String> persistenceMap = new HashMap<>();

    // Creating a static root to pass to the controller
    private static final BorderPane root = new BorderPane();

    /**
     * Just a root getter for the controller to use
     */
    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {

        persistenceMap = Utils.getInstance().getPersistenceMap();

        if (Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "true"))) {
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
        }

       //MenuBar bar = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        //AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));

        // constructing our scene using the static root
        
        //root.setCenter(paneOne);

        factory = Persistence.createEntityManagerFactory("mg_PU", persistenceMap);
        
        root.setTop(FXMLLoader.load(getClass().getResource("/fxml/MenuPrincipal.fxml")));
        root.setCenter(FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml")));        
        Scene scene = new Scene(root, 640, 480);
        /*scene
                .getStylesheets()
                .add(getClass()
                        .getResource("/tiwulfx.css")
                        .toExternalForm());*/

        stage.setScene(scene);

        InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
        Properties prop = new Properties();
        prop.load(resourceAsStream);

        stage.setTitle("MG 3 " + prop.getProperty("project.version") + "." + prop.getProperty("project.build"));
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
}
