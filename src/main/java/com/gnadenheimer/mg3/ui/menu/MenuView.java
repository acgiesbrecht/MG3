package com.gnadenheimer.mg3.ui.menu;

import com.gnadenheimer.mg3.ui.admin.config.AdminConfigView;
import com.gnadenheimer.mg3.ui.admin.entidades.TblEntidadesView;
import com.gnadenheimer.mg3.utils.CurrentUser;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import javax.inject.Inject;

public class MenuView implements FxmlView<MenuViewModel> {

    @InjectViewModel
    private MenuViewModel viewModel;
    @Inject
    CurrentUser currentUser;

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
    private MenuItem mnuAdEventos, mnuAdEventosCuotas, mnuAdEventosCategorias, mnuAdTransferencias;
    @FXML
    private MenuItem mnuAdRecibos, mnuAdTimbrados, mnuAdFacturas, mnuAdAutofacturasTimbrados, mnuAdAutofacturas, mnuAdNotCreditoTimbrados, mnuAdNotaCredito, mnuAdCentrosCosto, mnuAdPlanCuentas, mnuAdAsientosManuales, mnuAdIglesia, mnuAdUsuarios, mnuAdGrupos, mnuAdConfig, mnuAdBackUp;

    @FXML
    private MenuItem mnuInfR, mnuInfAyC, mnuInfContabilidad;

    public void initialize() {
        try {
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

            mnuAdEntidades.setOnAction((event) -> {
                viewModel.loadView(TblEntidadesView.class, "Entidades");
            });

            mnuAdConfig.setOnAction((event) -> {
                viewModel.loadView(AdminConfigView.class, "Configuracion");
            });

            mnuLogout.setOnAction((event) -> {
                viewModel.logout();
            });
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }



/*
        mnuFacturasFlete.setOnAction((event) -> {
            final ViewTuple<TblBasFacturasFleteView, TblBasFacturasFleteViewModel> tuple = FluentViewLoader.fxmlView(TblBasFacturasFleteView.class).load();
            setView(tuple.getView(),"Facturas de Flete");
        });
*/



}