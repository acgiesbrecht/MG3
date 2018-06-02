package com.gnadenheimer.mg3.utils;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

@Singleton
public class InformationDialog {

    @Inject
    Logger LOGGER;

    public void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public Boolean showConfirmation(String question, Boolean yesDefault) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setContentText(question);
        confirmation.getButtonTypes().clear();
        confirmation.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        //Deactivate Defaultbehavior for yes-Button:
        Button yesButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.YES);
        yesButton.setDefaultButton(yesDefault);
        //Activate Defaultbehavior for no-Button:
        Button noButton = (Button) confirmation.getDialogPane().lookupButton(ButtonType.NO);
        noButton.setDefaultButton(!yesDefault);

        Optional<ButtonType> result = confirmation.showAndWait();
        return result.get() == ButtonType.YES;
    }

    public void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Warning");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Informacion");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void showException(String title, String message, Exception exception) {
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

}
