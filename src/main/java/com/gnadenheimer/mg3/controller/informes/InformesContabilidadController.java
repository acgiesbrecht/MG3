/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.informes;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblCentrosDeCosto;
import com.gnadenheimer.mg3.utils.TimeTextField;
import com.gnadenheimer.mg3.utils.Utils;
import com.panemu.tiwulfx.form.TypeAheadControl;
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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class InformesContabilidadController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboPeriodo.getItems().addAll("Este año", "Este mes", "Hoy");
        cboPeriodo.getSelectionModel().selectFirst();

        TblCentrosDeCosto cdcTodos = new TblCentrosDeCosto();
        cdcTodos.setId(99999);

        List<TblCentrosDeCosto> lCdC = daoTblCentrosDeCosto.getList();
        cboMayorCdC.addItem("TODOS", cdcTodos);
        lCdC.forEach((p) -> {
            cboMayorCdC.addItem(p.getDescripcion(), p);
        });
        cboMayorCdC.setValue(cdcTodos);

//        startDate.bind(startDate);
        selectPreiodo(new ActionEvent());
    }

    @FXML
    private void selectPreiodo(ActionEvent event) {
        LocalDateTime startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDate = LocalDateTime.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        switch (cboPeriodo.getSelectionModel().getSelectedItem()) {
            case "Este año":
                startDate = LocalDateTime.now().withDayOfYear(1).toLocalDate().atStartOfDay();
                endDate = startDate.plusYears(1).minusNanos(1);
                break;
            case "Este mes":
                startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                endDate = startDate.plusMonths(1).minusNanos(1);
                break;
            case "Hoy":
                startDate = LocalDateTime.now().toLocalDate().atStartOfDay();
                endDate = startDate.plusDays(1).minusNanos(1);
                break;
        }
        dpDesde.setValue(startDate.toLocalDate());
        ttDesde.setText(startDate.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        dpHasta.setValue(endDate.toLocalDate());
        ttHasta.setText(endDate.toLocalTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    @FXML
    private void verLibroMayor(ActionEvent event) {
        try {
            Map parameters = new HashMap();
            parameters.put("fechaDesde", Timestamp.valueOf(getStartDate()));
            parameters.put("fechaHasta", Timestamp.valueOf(getEndDate()));
            TblCentrosDeCosto cdc = cboMayorCdC.getValue();
            if (cdc == null) {
                if (chbMayorSoloTotales.isSelected()) {
                    Utils.getInstance().showReport("libro_mayor", "libro_mayor_solo_totales_subreport", "libro_mayor_subreport_saldo_anterior", parameters, false);
                } else {
                    Utils.getInstance().showReport("libro_mayor", "libro_mayor_subreport", "libro_mayor_subreport_saldo_anterior", parameters, false);
                }
            } else {
                parameters.put("centroDeCosto", cdc.getId());
                parameters.put("centroDeCostoNombre", cdc.getDescripcion());
                if (chbMayorSoloTotales.isSelected()) {
                    Utils.getInstance().showReport("libro_mayor_cc", "libro_mayor_solo_totales_subreport_cc", "libro_mayor_subreport_saldo_anterior_cc", parameters, false);
                } else {
                    Utils.getInstance().showReport("libro_mayor_cc", "libro_mayor_subreport_cc", "libro_mayor_subreport_saldo_anterior_cc", parameters, false);
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

}
