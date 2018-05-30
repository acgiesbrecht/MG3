/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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
            App.showException(this.getClass().getName(), ex.getMessage(), ex);
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

    @FXML
    private void mnuInfContabilidad(ActionEvent event) {
        setPane("/fxml/informes/InformesContabilidad.fxml");
    }

    @FXML
    private void mnuAdConfig(ActionEvent event) {
        setPane("/fxml/admin/AdminConfig.fxml");
    }

    @FXML
    private void mnuAdBackUp(ActionEvent event) {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Eligir ubicación de la base de datos.");
            File selectedDirectory = directoryChooser.showDialog(App.mainStage);
            if (selectedDirectory != null) {
                Utils.getInstance().exectueBackUp(selectedDirectory.getAbsolutePath());
            }
        } catch (Exception ex) {
            App.showException(this.getClass().getName(), ex.getMessage(), ex);
        }
    }

    @FXML
    private void mnuAdEventos(ActionEvent event) {
        setPane("/fxml/admin/AdminEventos.fxml");
    }

}
