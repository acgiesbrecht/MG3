package com.gnadenheimer.mg3.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class InformationDialog {

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
        if (result.get() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }
    }


}
