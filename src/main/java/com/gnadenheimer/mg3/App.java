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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.derby.drda.NetworkServerControl;

public class App extends Application {

    public static EntityManagerFactory factory;
    Map<String, String> persistenceMap = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {

        persistenceMap = Utils.getInstance().getPersistenceMap();

        if (Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "true"))) {
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
        }

        factory = Persistence.createEntityManagerFactory("mg_PU", persistenceMap);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(("/tiwulfx.css"));//load tiwulfx.css
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
