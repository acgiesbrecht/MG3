/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.admin;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.domain.TblContribuyentes;
import com.gnadenheimer.mg3.domain.TblFacturas;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.prefs.Preferences;
import java.util.zip.ZipInputStream;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.apache.commons.lang.StringEscapeUtils;
import ru.vas7n.va.widgets.MaskField;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class AdminConfigController implements Initializable {

    private TextField txtDataDirectory;
    @FXML
    private Button cmdDataDirectory;
    @FXML
    private MaskField txtIP;
    @FXML
    private Button cmdSave;
    @FXML
    private Button cmdCancel;
    @FXML
    private RadioButton rbServidor;
    @FXML
    private ToggleGroup ServerClient;
    @FXML
    private RadioButton rbCliente;
    @FXML
    private ComboBox<String> cboTransferencias;
    @FXML
    private ComboBox<String> cboFacturasFormato;
    @FXML
    private Button cmdPrintFactura;
    @FXML
    private ToggleGroup ServerClient1;
    @FXML
    private Button cmdUpdateSET;
    @FXML
    private Button cmdNombresComma;
    @FXML
    private TextField txtDataDir;
    @FXML
    private RadioButton rbAyCPorMes;
    @FXML
    private RadioButton rbAyCPorEvento;
    @FXML
    private Label lblUpdateSET;
    @FXML
    private ComboBox<Integer> cboPeriodoFiscal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboTransferencias.getItems().addAll("Normal", "Triplicado");
        cboFacturasFormato.getItems().addAll("Preimpreso con rejilla", "Preimpreso sin rejilla", "Preimpreso con rejilla modelo especial Bethel Theodor");
        cboPeriodoFiscal.getItems().addAll(2016, 2017);
        txtIP.setText(Preferences.userRoot().node("MG").get("DatabaseIP", "127.000.000.001"));
        txtDataDir.setText(Preferences.userRoot().node("MG").get("Datadir", "C:\\javadb"));
        rbServidor.setSelected(Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "true")));
        cboTransferencias.getSelectionModel().select(Preferences.userRoot().node("MG").get("modoImpresion", "Normal"));
        cboFacturasFormato.getSelectionModel().select(Preferences.userRoot().node("MG").get("formatoFactura", "Preimpreso sin rejilla"));
        rbAyCPorMes.setSelected(Boolean.parseBoolean(Preferences.userRoot().node("MG").get("cobrarAC", "true")));
        cboPeriodoFiscal.getSelectionModel().select(Integer.valueOf(Preferences.userRoot().node("MG").getInt("PeriodoFiscal", LocalDate.now().getYear())));

        txtDataDir.visibleProperty().bind(rbServidor.selectedProperty());
    }

    @FXML
    private void cmdDataDirectory(ActionEvent event) {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(App.mainStage);
            if (selectedDirectory != null) {
                txtDataDirectory.setText(selectedDirectory.getAbsolutePath());
            }
        } catch (Exception ex) {
            App.showException(this.getClass().getName(), ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdSave(ActionEvent event) {
        Preferences.userRoot().node("MG").put("DatabaseIP", txtIP.getText());
        Preferences.userRoot().node("MG").put("Datadir", txtDataDir.getText());
        Preferences.userRoot().node("MG").put("isServer", String.valueOf(rbServidor.isSelected()));
        Preferences.userRoot().node("MG").put("modoImpresion", cboTransferencias.getSelectionModel().getSelectedItem());
        Preferences.userRoot().node("MG").put("formatoFactura", cboFacturasFormato.getSelectionModel().getSelectedItem());
        Preferences.userRoot().node("MG").put("cobrarAC", String.valueOf(rbAyCPorMes.isSelected()));
        Preferences.userRoot().node("MG").putInt("PeriodoFiscal", cboPeriodoFiscal.getSelectionModel().getSelectedItem());

        App.periodoFiscal = cboPeriodoFiscal.getSelectionModel().getSelectedItem();

        LoginManager.getInstance().showMainView();
    }

    @FXML
    private void cmdCancel(ActionEvent event) {
        LoginManager.getInstance().showMainView();
    }

    @FXML
    private void rbServidor(ActionEvent event) {
    }

    @FXML
    private void cmdPrintFactura(ActionEvent event) {
        try {
            TblFacturas testF = new TblFacturas();
            testF.setNro(1234567);
            testF.setFechahora(LocalDateTime.now());
            testF.setRazonSocial("Empresa SA");
            testF.setRuc("8888888-9");
            testF.setDomicilio("Loma Plata");
            testF.setCasillaDeCorreo(1158);
            testF.setIdUser(CurrentUser.getInstance().getUser());
            testF.setImporteAporte(15000000);
            testF.setImporteDonacion(25000000);

            Utils.getInstance().printFactura(testF);

        } catch (Exception ex) {
            App.showException(this.getClass().getName(), ex.getMessage(), ex);

        }
    }

    @FXML
    private void cmdUpdateSET(ActionEvent event) {
        Task taskUpdateSET = new Task<Void>() {
            @Override
            public Void call() {
                try {
                    Map<String, String> persistenceMap = Utils.getInstance().getPersistenceMap();
                    EntityManager entityManager = App.factory.createEntityManager();
                    entityManager.getTransaction().begin();
                    String temp = "";
                    Integer count = 0;
                    entityManager.createQuery("delete from TblContribuyentes t").executeUpdate();
                    for (Integer i = 0; i <= 9; i++) {
                        URL url = new URL("http://www.set.gov.py/rest/contents/download/collaboration/sites/PARAGUAY-SET/documents/informes-periodicos/ruc/ruc" + String.valueOf(i) + ".zip");
                        ZipInputStream zipStream = new ZipInputStream(url.openStream(), StandardCharsets.UTF_8);
                        zipStream.getNextEntry();

                        Scanner sc = new Scanner(zipStream, "UTF-8");

                        while (sc.hasNextLine()) {
                            String[] ruc = sc.nextLine().split("\\|");
                            temp = ruc[0] + " - " + ruc[1] + " - " + ruc[2];
                            if (ruc[0].length() > 0 && ruc[1].length() > 0 && ruc[2].length() == 1) {
                                TblContribuyentes c = new TblContribuyentes();
                                c.setRucSinDv(ruc[0]);
                                c.setRazonSocial(StringEscapeUtils.escapeSql(ruc[1]));
                                c.setDv(ruc[2]);
                                entityManager.persist(c);
                                updateMessage("Descargando listado de RUC con terminacion " + String.valueOf(i) + " - Cantidad de contribuyentes procesada: " + String.format("%,d", count) + " de aprox. 850.000.");
                                count++;
                            } else {
                                updateMessage(temp);
                            }
                        }
                        entityManager.getTransaction().commit();
                        entityManager.getTransaction().begin();
                    }

                    updateMessage("Lista de RUC actualizada...");
                    return null;
                } catch (Exception ex) {
                    App.showException(this.getClass().getName(), ex.getMessage(), ex);
                }
                return null;
            }
        };
        lblUpdateSET.textProperty().bind(taskUpdateSET.messageProperty());
        new Thread(taskUpdateSET).start();
    }

    @FXML
    private void cmdNombresComma(ActionEvent event) {

    }

}
