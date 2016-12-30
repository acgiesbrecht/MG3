/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.informes;

import com.gnadenheimer.mg3.utils.TimeTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import jfxtras.scene.control.LocalDateTimeTextField;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class InformesContabilidadController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboPeriodo.getItems().addAll("Este año", "Este mes", "Hoy");
        cboPeriodo.getSelectionModel().selectFirst();
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

}
