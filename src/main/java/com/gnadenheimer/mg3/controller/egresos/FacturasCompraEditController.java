/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.egresos;

import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblAsientos;
import com.gnadenheimer.mg3.domain.TblCentrosDeCosto;
import com.gnadenheimer.mg3.domain.TblCuentasContables;
import com.gnadenheimer.mg3.domain.TblFacturasCompra;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.form.FacturaNroControl;
import com.panemu.tiwulfx.form.Form;
import com.panemu.tiwulfx.form.LocalDateControl;
import com.panemu.tiwulfx.form.TextControl;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TypeAheadColumn;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class FacturasCompraEditController extends AnchorPane implements Initializable {

    private final DaoBase<TblFacturasCompra> daoTblFacturasCompra = new DaoBase<>(TblFacturasCompra.class);
    private final DaoBase<TblCentrosDeCosto> daoTblCentrosDeCosto = new DaoBase<>(TblCentrosDeCosto.class);
    private final DaoBase<TblCuentasContables> daoTblCuentasContables = new DaoBase<>(TblCuentasContables.class);

    ObjectProperty<TblFacturasCompra> tblFacturasCompra = new SimpleObjectProperty<>();
    private ObjectProperty<Form.Mode> mode = new SimpleObjectProperty<>();
    private Stage dialogStage;

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
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //TableColumn<TblAsientos, Integer> cId = new TableColumn<>();
        //
        tableAsientos.setRecordClass(TblAsientos.class);
        tableAsientos.setController(cntlTblAsientos);

        NumberColumn<TblAsientos, Integer> cId = new NumberColumn<>("Nro.", Integer.class);
        cId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TypeAheadColumn<TblAsientos, TblCentrosDeCosto> cCdCD = new TypeAheadColumn<>("Centro de Costo Debe");
        List<TblCentrosDeCosto> lCdC = daoTblCentrosDeCosto.getList();
        lCdC.forEach((p) -> {
            cCdCD.addItem(p.getDescripcion(), p);
        });
        cCdCD.setCellValueFactory(cellData -> cellData.getValue().idCentroDeCostoDebeProperty());
        cCdCD.setMinWidth(150);

        TypeAheadColumn<TblAsientos, TblCuentasContables> cCCD = new TypeAheadColumn<>("Cuenta Contable Debe");
        TableCriteria tc = new TableCriteria("imputable", TableCriteria.Operator.eq, true);
        List<TableCriteria> listTc = new ArrayList<>();
        listTc.add(tc);
        List<TblCuentasContables> lCC = daoTblCuentasContables.getList("select t from TblCuentasContables t where t.imputable = :trueValue order by id");
        lCC.forEach((p) -> {
            cCCD.addItem(p.getDescripcion(), p);
        });
        cCCD.setCellValueFactory(cellData -> cellData.getValue().idCuentaContableDebeProperty());
        cCCD.setMinWidth(150);

        TypeAheadColumn<TblAsientos, TblCentrosDeCosto> cCdCH = new TypeAheadColumn<>("Centro de Costo Haber");
        lCdC.forEach((p) -> {
            cCdCH.addItem(p.getDescripcion(), p);
        });
        cCdCH.setCellValueFactory(cellData -> cellData.getValue().idCentroDeCostoHaberProperty());
        cCdCH.setMinWidth(150);

        TypeAheadColumn<TblAsientos, TblCuentasContables> cCCH = new TypeAheadColumn<>("Cuenta Contable Haber");
        lCC.forEach((p) -> {
            cCCH.addItem(p.getDescripcion(), p);
        });
        cCCH.setCellValueFactory(cellData -> cellData.getValue().idCuentaContableHaberProperty());
        cCCH.setMinWidth(150);

        NumberColumn<TblAsientos, Integer> cMonto = new NumberColumn<>("Importe", Integer.class);
        cMonto.setCellValueFactory(cellData -> cellData.getValue().montoProperty().asObject());

        tableAsientos.addColumn(cCdCD, cCCD, cCdCH, cCCH, cMonto);
        tableAsientos.setVisibleComponents(false, TableControl.Component.BUTTON_PAGINATION);
        tableAsientos.setVisibleComponents(false, TableControl.Component.BUTTON_RELOAD);

    }

    public void setTblFacturasCompra(TblFacturasCompra factura) {
        tblFacturasCompra.set(factura);
        txtNro.setValue(factura.getNro());
        txtTimbrado.setValue(factura.getNroTimbrado());
        txtRazonSocial.setValue(factura.getRazonSocial());
        txtRuc.setValue(factura.getRuc());
        vencimientoTimbrado.setValue(factura.getVencimientoTimbrado());
        tableAsientos.reload();
    }

    public TblFacturasCompra getTblFacturasCompra() {
        return tblFacturasCompra.get();
    }

    public ObjectProperty<TblFacturasCompra> tblFacturasCompraProperty() {
        return tblFacturasCompra;
    }

    private final TableController<TblAsientos> cntlTblAsientos = new TableController<TblAsientos>() {
        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
            if (tblFacturasCompra.get().getTblAsientosList() != null) {
                return new TableData(tblFacturasCompra.get().getTblAsientosList(), false, tblFacturasCompra.get().getTblAsientosList().size());
            } else {
                return new TableData();
            }
        }

        /*
        @Override
        public List<TblAsientos> insert(List<TblAsientos> newRecords) {
            //return daoTblEntidades.insert(newRecords);
        }
         *//*
        @Override
        public List<TblAsientos> update(List<TblAsientos> records) {
            //return daoTblEntidades.update(records);
        }


        @Override
        public boolean canDelete(TableControl table) {

             * This checking is not perfect. If there are Persons filtered thus not
             * displayed in tblPerson, the delete is not canceled. An error will be displayed
             * along with the stack trace. The better implementation is to count the children
             * from database and ensure the result is zero.

            boolean nochildren = tblEntidades.getRecords().isEmpty();
            if (!nochildren) {
                MessageDialogBuilder.error().message("Unable to delete TblBasPrecios (code "
                        + TblEntidades.getSelectedItem().getCode() + ") because"
                        + "\nthere are Persons refer to it!").show(getScene().getWindow());
            }
            return nochildren;
        }
        @Override
        public void delete(List<TblAsientos> records) {
            //tableAsientos.getTableView().itemsProperty().addListener((observable, oldValue, newValue) -> {
            TblFacturasCompra f = tblFacturasCompraForm.getRecord();
            f.getTblAsientosList().removeAll(records);
            tblFacturasCompraForm.setRecord(f);
            //});
        }*/
    };

    /**
     * @return the mode
     */
    public Form.Mode getMode() {
        return mode.get();
    }

    public void setMode(Form.Mode mode) {
        this.mode.set(mode);
    }

    public ObjectProperty<Form.Mode> modeProperty() {
        return mode;
    }

    @FXML
    private void save(ActionEvent event) {
        if (mode.get() == Form.Mode.EDIT) {
            daoTblFacturasCompra.update(tblFacturasCompra.get());
        } else {
            daoTblFacturasCompra.insert(tblFacturasCompra.get());
        }
        dialogStage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        dialogStage.close();
    }

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
