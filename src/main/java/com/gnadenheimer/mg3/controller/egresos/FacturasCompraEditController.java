/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.egresos;

import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblAsientos;
import com.gnadenheimer.mg3.domain.TblFacturasCompra;
import com.gnadenheimer.mg3.domain.miembros.TblEntidades;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.form.FacturaNroControl;
import com.panemu.tiwulfx.form.Form;
import com.panemu.tiwulfx.form.LocalDateControl;
import com.panemu.tiwulfx.form.TextControl;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class FacturasCompraEditController extends AnchorPane implements Initializable {

    private DaoBase<TblFacturasCompra> daoTblFacturasCompra = new DaoBase<>(TblFacturasCompra.class);

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnSave;
    @FXML
    private Form<TblFacturasCompra> tblFacturasCompraForm;
    @FXML
    private TextControl txtTimbrado;
    @FXML
    private FacturaNroControl txtNro;
    @FXML
    private LocalDateControl vencimientoTimbrado;
    @FXML
    private TextControl txtRazonSocial;
    @FXML
    private TextControl txtRuc;
    @FXML
    private TableControl<TblAsientos> tableAsientos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableAsientos.setRecordClass(TblAsientos.class);
        tableAsientos.setController(cntlTblAsientos);

        btnSave.setOnAction(eventHandler);
        btnEdit.setOnAction(eventHandler);
        btnAdd.setOnAction(eventHandler);

        btnSave.disableProperty().bind(tblFacturasCompraForm.modeProperty().isEqualTo(Form.Mode.READ));
        btnAdd.disableProperty().bind(tblFacturasCompraForm.modeProperty().isNotEqualTo(Form.Mode.READ));
        btnEdit.disableProperty().bind(tblFacturasCompraForm.modeProperty().isNotEqualTo(Form.Mode.READ));

        tblFacturasCompraForm.bindChildren();

    }
    private EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (t.getSource() == btnSave && tblFacturasCompraForm.validate()) {
                TblFacturasCompra p = tblFacturasCompraForm.getRecord();
                if (p.getId() == null) {
                    p = daoTblFacturasCompra.insert(p);
                } else {
                    p = daoTblFacturasCompra.update(p);
                    //p = daoTblFacturasCompra.initRelationship(p, Person_.insurance.getName());
                }
                tblFacturasCompraForm.setRecord(p);
                tblFacturasCompraForm.setMode(Form.Mode.READ);
            } else if (t.getSource() == btnEdit) {
                tblFacturasCompraForm.setMode(Form.Mode.EDIT);
            } else if (t.getSource() == btnAdd) {
                tblFacturasCompraForm.setRecord(new TblFacturasCompra());
                tblFacturasCompraForm.setMode(Form.Mode.INSERT);
//            } else if (t.getSource() == btnReload) {
//                personForm.setValueObject(person);
//                personForm.setMode(Form.Mode.READ);
//                personForm.validate();//ensure to remove exclamation mark next to the invalid fields
            }
        }
    };

    private final TableController<TblAsientos> cntlTblAsientos = new TableController<TblAsientos>() {
        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
            return new TableData(tblFacturasCompraForm.getRecord().getTblAsientosList(), false, tblFacturasCompraForm.getRecord().getTblAsientosList().size());
        }

        @Override
        public List<TblAsientos> insert(List<TblAsientos> newRecords) {
            tblFacturasCompraForm.getRecord().getTblAsientosList().addAll(newRecords);
            return newRecords;
        }

        /*@Override
        public List<TblAsientos> update(List<TblAsientos> records) {
            return daoTblEntidades.update(records);
        }*/
        @Override
        public void delete(List<TblAsientos> records) {
            tblFacturasCompraForm.getRecord().getTblAsientosList().removeAll(records);
        }
    };

    public void setTblFacturasCompra(TblFacturasCompra factura) {
        tblFacturasCompraForm.setRecord(factura);
    }

    public void setMode(Form.Mode mode) {
        tblFacturasCompraForm.setMode(mode);
    }
}
