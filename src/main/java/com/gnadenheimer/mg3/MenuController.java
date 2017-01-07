/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3;

import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

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
        mnuAdEntidades.setDisable(!currentUser.hasRole(3));
        mnuAdEventos.setDisable(!currentUser.hasRole(3));
        mnuInRemates.setDisable(!currentUser.hasRole(3));
        mnuInRematesP.setDisable(!currentUser.hasRole(3));
        mnuInRematesIT.setDisable(!currentUser.hasRole(3));
        mnuAdEntidades.setDisable(!currentUser.hasRole(3));
        mnuInAportes.setDisable(!currentUser.hasRole(3));
        mnuInColectas.setDisable(!currentUser.hasRole(3));
        mnuInAportesColectasIT.setDisable(!currentUser.hasRole(3));
        mnuInDonacionesVarias.setDisable(!currentUser.hasRole(3));
        mnuInFacturacionColectiva.setDisable(!currentUser.hasRole(3));
        mnuInFacturacionUnica.setDisable(!currentUser.hasRole(3));
        mnuInNotaDeCredito.setDisable(!currentUser.hasRole(3));
        mnuEgFacturas.setDisable(!currentUser.hasRole(3));
        mnuEgNotaDeCredito.setDisable(!currentUser.hasRole(3));
        mnuEgRecibos.setDisable(!currentUser.hasRole(3));
        mnuEgNotaDeCredito1.setDisable(!currentUser.hasRole(3));
        mnuAdEventos.setDisable(!currentUser.hasRole(3));
        mnuAdEventosCuotas.setDisable(!currentUser.hasRole(3));
        mnuAdEventosCategorias.setDisable(!currentUser.hasRole(3));
        mnuAdTransferencias.setDisable(!currentUser.hasRole(3));
        mnuAdRecibos.setDisable(!currentUser.hasRole(3));
        mnuAdTimbrados.setDisable(!currentUser.hasRole(3));
        mnuAdFacturas.setDisable(!currentUser.hasRole(3));
        mnuAdAutofacturasTimbrados.setDisable(!currentUser.hasRole(3));
        mnuAdAutofacturas.setDisable(!currentUser.hasRole(3));
        mnuAdNotCreditoTimbrados.setDisable(!currentUser.hasRole(3));
        mnuAdNotaCredito.setDisable(!currentUser.hasRole(3));
        mnuAdCentrosCosto.setDisable(!currentUser.hasRole(3));
        mnuAdPlanCuentas.setDisable(!currentUser.hasRole(3));
        mnuAdAsientosManuales.setDisable(!currentUser.hasRole(3));
        mnuAdIglesia.setDisable(!currentUser.hasRole(3));
        mnuAdUsuarios.setDisable(!currentUser.hasRole(3));
        mnuAdGrupos.setDisable(!currentUser.hasRole(3));
        mnuAdConfig.setDisable(!currentUser.hasRole(3));
        mnuInfR.setDisable(!currentUser.hasRole(3));
        mnuInfAyC.setDisable(!currentUser.hasRole(3));
        mnuInfContabilidad.setDisable(!currentUser.hasRole(3));
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
