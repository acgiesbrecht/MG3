/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.ui.informes;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.dao.DaoBase;
import com.gnadenheimer.mg3.domain.TblCentrosDeCosto;
import com.gnadenheimer.mg3.utils.TimeTextField;
import com.gnadenheimer.mg3.utils.Utils;
import com.panemu.tiwulfx.form.TypeAheadControl;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javax.inject.Inject;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class InformesContabilidadController implements Initializable {

    @Inject
    Utils utils;

    private final DaoBase<TblCentrosDeCosto> daoTblCentrosDeCosto = new DaoBase<>(TblCentrosDeCosto.class);

    @FXML
    private ComboBox<String> cboPeriodo;
    @FXML
    private DatePicker dpHasta;
    @FXML
    private TimeTextField ttHasta;
    @FXML
    private DatePicker dpDesde;
    @FXML
    private TimeTextField ttDesde;
    @FXML
    private TypeAheadControl<TblCentrosDeCosto> cboMayorCdC;
    @FXML
    private Button cmdMayor;
    @FXML
    private CheckBox chbMayorSoloTotales;

    private final ObjectProperty<LocalDateTime> startDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> endDate = new SimpleObjectProperty<>();
    @FXML
    private Button cmdLibroIngresos;
    @FXML
    private Button cmdLibroEgresos;
    @FXML
    private Button cmdExtractoDetalle;
    @FXML
    private Button cmdExtractoResumen;
    @FXML
    private TypeAheadControl<TblCentrosDeCosto> cboExtractoCdC;

    /**
     * Initializes the ui class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboPeriodo.getItems().addAll("Este a�o", "Este mes", "Hoy");
        cboPeriodo.getSelectionModel().selectFirst();

        TblCentrosDeCosto cdcTodos = new TblCentrosDeCosto();
        cdcTodos.setId(99999);

        List<TblCentrosDeCosto> lCdC = daoTblCentrosDeCosto.getList();
        cboMayorCdC.addItem("TODOS", cdcTodos);
        cboExtractoCdC.addItem("TODOS", cdcTodos);
        lCdC.forEach((p) -> {
            cboMayorCdC.addItem(p.getDescripcion(), p);
            cboExtractoCdC.addItem(p.getDescripcion(), p);
        });
        cboMayorCdC.setValue(cdcTodos);
        cboExtractoCdC.setValue(cdcTodos);

//        startDate.bind(startDate);
        selectPeriodo(new ActionEvent());
    }

    @FXML
    private void selectPeriodo(ActionEvent event) {
        LocalDateTime startDateL = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDateL = LocalDateTime.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        switch (cboPeriodo.getSelectionModel().getSelectedItem()) {
            case "Este a�o":
                startDateL = LocalDateTime.now().withDayOfYear(1).toLocalDate().atStartOfDay();
                endDateL = startDateL.plusYears(1).minusNanos(1);
                break;
            case "Este mes":
                startDateL = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                endDateL = startDateL.plusMonths(1).minusNanos(1);
                break;
            case "Hoy":
                startDateL = LocalDateTime.now().toLocalDate().atStartOfDay();
                endDateL = startDateL.plusDays(1).minusNanos(1);
                break;
        }
        dpDesde.setValue(startDateL.toLocalDate());
        ttDesde.setText(startDateL.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        dpHasta.setValue(endDateL.toLocalDate());
        ttHasta.setText(endDateL.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @FXML
    private void verLibroMayor(ActionEvent event) {
        try {
            Map parameters = new HashMap();
            parameters.put("fechaDesde", Timestamp.valueOf(getStartDate()));
            parameters.put("fechaHasta", Timestamp.valueOf(getEndDate()));
            TblCentrosDeCosto cdc = cboMayorCdC.getValue();
            if (cdc.getDescripcion() == null) {
                if (chbMayorSoloTotales.isSelected()) {
                    utils.showReport("libro_mayor", "libro_mayor_solo_totales_subreport", "libro_mayor_subreport_saldo_anterior", parameters, false);
                } else {
                    utils.showReport("libro_mayor", "libro_mayor_subreport", "libro_mayor_subreport_saldo_anterior", parameters, false);
                }
            } else {
                parameters.put("centroDeCosto", cdc.getId());
                parameters.put("centroDeCostoNombre", cdc.getDescripcion());
                if (chbMayorSoloTotales.isSelected()) {
                    utils.showReport("libro_mayor_cc", "libro_mayor_solo_totales_subreport_cc", "libro_mayor_subreport_saldo_anterior_cc", parameters, false);
                } else {
                    utils.showReport("libro_mayor_cc", "libro_mayor_subreport_cc", "libro_mayor_subreport_saldo_anterior_cc", parameters, false);
                }
            }
        } catch (Exception ex) {
            App.showException("Error", ex.getMessage(), ex);
        }
    }

    /**
     * @return the startDate
     */
    public ObjectProperty<LocalDateTime> startDateProperty() {
        return startDate;
    }

    public LocalDateTime getStartDate() {
        LocalDateTime ldt = LocalDateTime.of(dpDesde.getValue(), LocalTime.parse(ttDesde.getText()));
        startDate.set(ldt);
        return startDate.get();
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate.set(startDate);
    }

    /**
     * @return the endDate
     */
    public ObjectProperty<LocalDateTime> endDateProperty() {
        return endDate;
    }

    public LocalDateTime getEndDate() {
        LocalDateTime ldt = LocalDateTime.of(dpHasta.getValue(), LocalTime.parse(ttHasta.getText()));
        endDate.set(ldt);
        return endDate.get();
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate.set(endDate);
    }

    @FXML
    private void cmdLibroIngresos(ActionEvent event) {
        try {
            Map parameters = new HashMap();
            parameters.put("fechaDesde", Timestamp.valueOf(getStartDate()));
            parameters.put("fechaHasta", Timestamp.valueOf(getEndDate()));
            utils.showReport("libro_ingresos", parameters, true);
        } catch (Exception ex) {
            App.showException("Error", ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdLibroEgresos(ActionEvent event) {
        try {
            Map parameters = new HashMap();
            parameters.put("fechaDesde", Timestamp.valueOf(getStartDate()));
            parameters.put("fechaHasta", Timestamp.valueOf(getEndDate()));
            utils.showReport("libro_egresos", parameters, true);
        } catch (Exception ex) {
            App.showException("Error", ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdExtractoDetalle(ActionEvent event) {
        try {
            Map parameters = new HashMap();
            parameters.put("fechaDesde", Timestamp.valueOf(getStartDate()));
            parameters.put("fechaHasta", Timestamp.valueOf(getEndDate()));
            TblCentrosDeCosto cdc = cboExtractoCdC.getValue();
            if (cdc.getDescripcion() == null) {
                utils.showReport("extracto_ctacte", "extracto_ctacte_subreport_saldo_anterior", parameters, false);
            } else {
                parameters.put("centroDeCosto", cdc.getId());
                parameters.put("centroDeCostoNombre", cdc.getDescripcion());
                utils.showReport("extracto_ctacte_cc", "extracto_ctacte_subreport_saldo_anterior_cc", parameters, false);
            }
        } catch (Exception ex) {
            App.showException("Error", ex.getMessage(), ex);
        }
    }

    @FXML
    private void cmdExtractoResumen(ActionEvent event) {
        try {
            Map parameters = new HashMap();
            parameters.put("fechaDesde", Timestamp.valueOf(getStartDate()));
            parameters.put("fechaHasta", Timestamp.valueOf(getEndDate()));
            TblCentrosDeCosto cdc = cboExtractoCdC.getValue();
            if (cdc == null) {
                utils.showReport("extracto_ctacte_resumen", "extracto_ctacte_subreport_saldo_anterior", parameters, false);
            } else {
                parameters.put("centroDeCosto", cdc.getId());
                parameters.put("centroDeCostoNombre", cdc.getDescripcion());
                utils.showReport("extracto_ctacte_resumen_cc", "extracto_ctacte_subreport_saldo_anterior_cc", parameters, false);
            }
        } catch (Exception ex) {
            App.showException("Error", ex.getMessage(), ex);
        }
    }

}
