/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.egresos;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblFacturasCompra;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.dialog.MessageDialogBuilder;
import com.panemu.tiwulfx.form.Form;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TextColumn;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author adriang
 */
public class FacturasComprasController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger(FacturasComprasController.class);
    private DaoBase<TblFacturasCompra> daoTblFacturasCompra = new DaoBase<>(TblFacturasCompra.class);

    @FXML
    private TableControl<TblFacturasCompra> masterTable;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        masterTable.setController(cntlTblFacturasCompra);
        masterTable.setRecordClass(TblFacturasCompra.class);

        TextColumn<TblFacturasCompra> cNro = new TextColumn<>("nro");

        masterTable.addColumn(cNro);
        masterTable.reload();

    }
    private final TableController<TblFacturasCompra> cntlTblFacturasCompra = new TableController<TblFacturasCompra>() {

        private Stage dialogStage;// = new Stage();
        private FacturasCompraEditController facturasCompraEditController;

        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
            return daoTblFacturasCompra.fetch(startIndex, filteredColumns, sortedColumns, sortingOrders, maxResult);
        }

        @Override
        public TblFacturasCompra preInsert(TblFacturasCompra newRecord) {
            showFacturasCompraEdit(newRecord, Form.Mode.INSERT);

            return null;
        }

        @Override
        public boolean canEdit(TblFacturasCompra selectedRecord) {
            if (selectedRecord == null) {
                MessageDialogBuilder.error().message("Please select a record to edit.").show(null);
                return false;
            }
            showFacturasCompraEdit(selectedRecord, Form.Mode.EDIT);
            return false;
        }

        @Override
        public void doubleClick(TblFacturasCompra record) {
            showFacturasCompraEdit(record, Form.Mode.EDIT);
        }

        @Override
        public void delete(List<TblFacturasCompra> records) {
            daoTblFacturasCompra.delete(records);
        }

        private void showFacturasCompraEdit(TblFacturasCompra factura, Form.Mode mode) {
            try {
                if (dialogStage == null) {

                    /**
                     * cannot instantiate dialogStage when instantiating this
                     * class because it's done in non-FX thread. It turns that
                     * instantiating stage should be in FX thread..
                     */
                    dialogStage = new Stage();
                    dialogStage.initOwner(App.mainStage);
                    dialogStage.initModality(Modality.WINDOW_MODAL);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/egresos/FacturasComprasEdit.fxml"));

                    dialogStage.setScene(new Scene((AnchorPane) loader.load()));
                    //TiwulFXUtil.setTiwulFXStyleSheet(dialogStage.getScene());
                    facturasCompraEditController = loader.<FacturasCompraEditController>getController();
                }
                facturasCompraEditController.setTblFacturasCompra(factura);
                facturasCompraEditController.setMode(mode);
                facturasCompraEditController.setDialogStage(dialogStage);
                dialogStage.showAndWait();
            } catch (Exception ex) {
                App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
            }
        }
    };
}
