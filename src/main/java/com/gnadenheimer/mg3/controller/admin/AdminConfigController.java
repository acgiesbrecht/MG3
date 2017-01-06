/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.vas7n.va.widgets.MaskField;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class AdminConfigController implements Initializable {

    @FXML
    private TextField txtDataDirectory;
    @FXML
    private Button cmdDataDirectory;
    @FXML
    private MaskField txtIP;
    @FXML
    private Button cmdSave;
    @FXML
    private Button cmdCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cmdDataDirectory(ActionEvent event) {
    }

    @FXML
    private void cmdSave(ActionEvent event) {
    }

    @FXML
    private void cmdCancel(ActionEvent event) {
    }

}
