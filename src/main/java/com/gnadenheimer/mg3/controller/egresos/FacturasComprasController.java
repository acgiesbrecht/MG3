/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.egresos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jidefx.scene.control.field.FormattedTextField;
import jidefx.scene.control.field.verifier.IntegerRangePatternVerifier;

/**
 * FXML Controller class
 *
 * @author adriang
 */
public class FacturasComprasController implements Initializable {

    @FXML
    private FormattedTextField<String> txtNro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtNro.getPatternVerifiers().put("n", new IntegerRangePatternVerifier(0, 999));
        txtNro.getPatternVerifiers().put("a", new IntegerRangePatternVerifier(0, 9999999));
        txtNro.setPattern("n-n-a");

    }

}
