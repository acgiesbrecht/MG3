/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author adriang
 */
public class MenuController implements Initializable {

    CurrentUser currentUser = CurrentUser.getInstance();
    @FXML
    private MenuItem mnuInRemates;
    @FXML
    private MenuItem mnuInRematesP;
    @FXML
    private MenuItem mnuInRematesIT;
    @FXML
    private MenuItem mnuAdEntidades;
    @FXML
    private MenuItem mnuLogout;
    @FXML
    private MenuItem mnuInAportes;
    @FXML
    private MenuItem mnuInColectas;
    @FXML
    private MenuItem mnuInAportesColectasIT;
    @FXML
    private MenuItem mnuInDonacionesVarias;
    @FXML
    private MenuItem mnuInFacturacionColectiva;
    @FXML
    private MenuItem mnuInFacturacionUnica;
    @FXML
    private MenuItem mnuInNotaDeCredito;
    @FXML
    private MenuItem mnuEgFacturas;
    @FXML
    private MenuItem mnuEgNotaDeCredito;
    @FXML
    private MenuItem mnuEgRecibos;
    @FXML
    private MenuItem mnuEgNotaDeCredito1;
    @FXML
    private MenuItem mnuAdEventos;
    @FXML
    private MenuItem mnuAdEventosCuotas;
    @FXML
    private MenuItem mnuAdEventosCategorias;
    @FXML
    private MenuItem mnuAdTransferencias;
    @FXML
    private MenuItem mnuAdRecibos;
    @FXML
    private MenuItem mnuAdTimbrados;
    @FXML
    private MenuItem mnuAdFacturas;
    @FXML
    private MenuItem mnuAdAutofacturasTimbrados;
    @FXML
    private MenuItem mnuAdAutofacturas;
    @FXML
    private MenuItem mnuAdNotCreditoTimbrados;
    @FXML
    private MenuItem mnuAdNotaCredito;
    @FXML
    private MenuItem mnuAdCentrosCosto;
    @FXML
    private MenuItem mnuAdPlanCuentas;
    @FXML
    private MenuItem mnuAdAsientosManuales;
    @FXML
    private MenuItem mnuAdIglesia;
    @FXML
    private MenuItem mnuAdUsuarios;
    @FXML
    private MenuItem mnuAdGrupos;
    @FXML
    private MenuItem mnuAdConfig;
    @FXML
    private MenuItem mnuAdBackUp;
    @FXML
    private MenuItem mnuInfR;
    @FXML
    private MenuItem mnuInfAyC;
    @FXML
    private MenuItem mnuInfContabilidad;

    @FXML
    void logout(ActionEvent event) {
        LoginManager.getInstance().logout();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mnuAdEntidades.setDisable(!currentUser.hasRole(2));
    }

    @FXML
    private void mnuAdEntidades(ActionEvent event) {
        setPane("/fxml/admin/AdminEntidades.fxml");
    }

    private void setPane(String paneUrl) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(paneUrl));
            App.getRoot().setCenter(pane);
        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

    @FXML
    private void mnuEgFacturas(ActionEvent event) {
        setPane("/fxml/egresos/FacturasCompras.fxml");
    }

    @FXML
    private void mnuAdTransferencias(ActionEvent event) {
        setPane("/fxml/admin/AdminTransferencias.fxml");
    }

}
