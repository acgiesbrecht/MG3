package com.gnadenheimer.mg3.ui.admin.config;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.model.domain.TblContribuyentes;
import com.gnadenheimer.mg3.model.domain.TblFacturas;
import com.gnadenheimer.mg3.ui.fieldextensions.MaskField;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.InformationDialog;
import com.gnadenheimer.mg3.utils.LoginManager;
import com.gnadenheimer.mg3.utils.Utils;
import de.saxsys.mvvmfx.FxmlView;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import org.apache.commons.lang.StringEscapeUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.prefs.Preferences;
import java.util.zip.ZipInputStream;

public class AdminConfigView implements FxmlView<AdminConfigViewModel> {

    @Inject
    EntityManager entityManager;
    @Inject
    Utils utils;
    @Inject
    LoginManager loginManager;
    @Inject
    CurrentUser currentUser;
    @Inject
    InformationDialog informationDialog;

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

    public void initialize() {
        try {
            cboTransferencias.getItems().addAll("Normal", "Triplicado");
            cboFacturasFormato.getItems().addAll("Preimpreso con rejilla", "Preimpreso sin rejilla", "Preimpreso con rejilla modelo especial Bethel Theodor");
            cboPeriodoFiscal.getItems().addAll(2016, 2017);
            txtIP.setText(Preferences.userRoot().node("MG").get("DatabaseIP", "127.000.000.001"));
            txtDataDir.setText(Preferences.userRoot().node("MG").get("Datadir", System.getProperty("user.dir") + File.separator + "javadb"));
            rbServidor.setSelected(Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "true")));
            rbCliente.setSelected(!Boolean.parseBoolean(Preferences.userRoot().node("MG").get("isServer", "truemaster   1")));
            cboTransferencias.getSelectionModel().select(Preferences.userRoot().node("MG").get("modoImpresion", "Normal"));
            cboFacturasFormato.getSelectionModel().select(Preferences.userRoot().node("MG").get("formatoFactura", "Preimpreso sin rejilla"));
            rbAyCPorMes.setSelected(Boolean.parseBoolean(Preferences.userRoot().node("MG").get("cobrarAC", "true")));
            cboPeriodoFiscal.getSelectionModel().select(Integer.valueOf(Preferences.userRoot().node("MG").getInt("PeriodoFiscal", LocalDate.now().getYear())));

            txtDataDir.visibleProperty().bind(rbServidor.selectedProperty());
        } catch (Exception ex) {
            informationDialog.showException(this.getClass().getName(), ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdDataDirectory(ActionEvent event) {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(App.mainStage);
            if (selectedDirectory != null) {
                txtDataDir.setText(selectedDirectory.getAbsolutePath());
            }
        } catch (Exception ex) {
            informationDialog.showException(this.getClass().getName(), ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdSave(ActionEvent event) {
        try {
            Preferences.userRoot().node("MG").put("DatabaseIP", txtIP.getText());
            Preferences.userRoot().node("MG").put("Datadir", txtDataDir.getText());
            Preferences.userRoot().node("MG").put("isServer", String.valueOf(rbServidor.isSelected()));
            Preferences.userRoot().node("MG").put("modoImpresion", cboTransferencias.getSelectionModel().getSelectedItem());
            Preferences.userRoot().node("MG").put("formatoFactura", cboFacturasFormato.getSelectionModel().getSelectedItem());
            Preferences.userRoot().node("MG").put("cobrarAC", String.valueOf(rbAyCPorMes.isSelected()));
            Preferences.userRoot().node("MG").putInt("PeriodoFiscal", cboPeriodoFiscal.getSelectionModel().getSelectedItem());

            App.periodoFiscal = cboPeriodoFiscal.getSelectionModel().getSelectedItem();

            loginManager.showStartView();
        } catch (Exception ex) {
            informationDialog.showException(this.getClass().getName(), ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdCancel(ActionEvent event) {
        loginManager.showStartView();
    }

    @FXML
    private void rbServidor(ActionEvent event) {
        txtIP.setText("127.000.000.001");
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
            testF.setIdUser(currentUser.getUser());
            testF.setImporteAporte(15000000);
            testF.setImporteDonacion(25000000);

            utils.printFactura(testF);

        } catch (Exception ex) {
            informationDialog.showException(this.getClass().getName(), ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdUpdateSET(ActionEvent event) {
        Task taskUpdateSET = new Task<Void>() {
            @Override
            public Void call() {
                try {
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
                    informationDialog.showException(this.getClass().getName(), ex.getMessage(), ex);
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