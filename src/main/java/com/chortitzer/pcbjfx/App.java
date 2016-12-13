package com.chortitzer.pcbjfx;

import com.panemu.tiwulfx.common.GraphicFactory;
import java.io.InputStream;
import java.util.Properties;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App extends Application {

    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pcb_PU");
    
    @Override
    public void start(Stage stage) throws Exception {

        Image img = new Image(GraphicFactory.class.getResourceAsStream("/com/panemu/tiwulfx/res/images/add.png"));
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(("tiwulfx.css"));//load tiwulfx.css
        stage.setScene(scene);
        
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
            Properties prop = new Properties();
            prop.load(resourceAsStream);            
        
        stage.setTitle("NIS " +  prop.getProperty("project.version") + "." + prop.getProperty("project.build"));
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
}
