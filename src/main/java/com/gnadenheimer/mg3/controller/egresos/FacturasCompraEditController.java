/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.egresos;

import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblAsientos;
import com.gnadenheimer.mg3.domain.TblFacturasCompra;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.form.FacturaNroControl;
import com.panemu.tiwulfx.form.Form;
import com.panemu.tiwulfx.form.LocalDateControl;
import com.panemu.tiwulfx.form.TableViewControl;
import com.panemu.tiwulfx.form.TextControl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableViewControl<TblAsientos> tableAsientos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //TableColumn<TblAsientos, Integer> cId = new TableColumn<>();
        //
        TableColumn<TblAsientos, Integer> cId = new TableColumn<>("Nro.");
        cId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        //cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        /*cId.setCellValueFactory(new Callback<CellDataFeatures<TblAsientos, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<TblAsientos, Integer> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue();
            }
        });*/

        tableAsientos.getInputComponent().getColumns().add(cId);

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
                System.out.print(tableAsientos.getInputComponent().getItems().get(0).toString());
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

    public void setTblFacturasCompra(TblFacturasCompra factura) {
        tblFacturasCompraForm.setRecord(factura);
    }

    public void setMode(Form.Mode mode) {
        tblFacturasCompraForm.setMode(mode);
    }
}
