/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.admin;

import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblTransferencias;
import com.gnadenheimer.mg3.domain.miembros.TblEntidades;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.form.TypeAheadControl;
import com.panemu.tiwulfx.table.LocalDateColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TypeAheadColumn;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author adriang
 */
public class AdminTransferenciasController implements Initializable {

    private static final Logger LOGGER = LogManager.getLogger(AdminTransferenciasController.class);
    private DaoBase<TblEntidades> daoTblEntidades = new DaoBase<>(TblEntidades.class);
    private DaoBase<TblTransferencias> daoTblTransferencias = new DaoBase<>(TblTransferencias.class);

    @FXML
    private TableControl transferenciasTable;

    //EntityManager em = Persistence.createEntityManagerFactory("pcb_PU").createEntityManager();
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        transferenciasTable.setController(cntlTblTransferencias);
        transferenciasTable.setRecordClass(TblTransferencias.class);

        LocalDateColumn<TblTransferencias> cFechaCompromiso = new LocalDateColumn<>("fechahoraCompromiso");
        cFechaCompromiso.setText("Fecha Compromiso");
        cFechaCompromiso.setDateFormat(DateTimeFormatter.ISO_DATE);
        cFechaCompromiso.setMinWidth(150);

        LocalDateColumn<TblTransferencias> cFecha = new LocalDateColumn<>("fechahora");
        cFecha.setText("Fecha pago");
        cFecha.setDateFormat(DateTimeFormatter.ISO_DATE);
        cFecha.setMinWidth(150);
        cFecha.setSortType(TableColumn.SortType.ASCENDING);

        TypeAheadColumn<TblTransferencias, TblEntidades> cEntidad = new TypeAheadColumn<>("idEntidad");
        cEntidad.setText("Nombre o Razon Social");
        cEntidad.setMinWidth(200);
        List<TblEntidades> lEntidades = daoTblEntidades.getList();
        lEntidades.forEach((p) -> {
            cEntidad.addItem(p.getNombreCompleto(), p);
        });
        NumberColumn<TblTransferencias, Integer> cMontoDonacion = new NumberColumn<>("montoDonacion", Integer.class);
        cMontoDonacion.setText("Monto Donacion");
        transferenciasTable.addColumn(cFecha, cEntidad, cMontoDonacion);
        transferenciasTable.reload();

    }

    private final TableController<TblTransferencias> cntlTblTransferencias = new TableController<TblTransferencias>() {

        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
            return daoTblTransferencias.fetch(startIndex, filteredColumns, sortedColumns, sortingOrders, maxResult);
        }

        @Override
        public List<TblTransferencias> insert(List<TblTransferencias> newRecords) {
            return daoTblTransferencias.insert(newRecords);
        }

        @Override
        public List<TblTransferencias> update(List<TblTransferencias> records) {
            return daoTblTransferencias.update(records);
        }

        /*@Override
        public boolean canDelete(TableControl table) {
            *
             * This checking is not perfect. If there are Persons filtered thus not
             * displayed in tblPerson, the delete is not canceled. An error will be displayed
             * along with the stack trace. The better implementation is to count the children
             * from database and ensure the result is zero.

            boolean nochildren = tblPerson.getRecords().isEmpty();
            if (!nochildren) {
                MessageDialogBuilder.error().message("Unable to delete TblBasPrecios (code "+
                        TblBasPrecios.getSelectedItem().getCode() +") because"
                        + "\nthere are Persons refer to it!").show(getScene().getWindow());
            }
            return nochildren;
        }*/
        @Override
        public void delete(List<TblTransferencias> records) {
            daoTblTransferencias.delete(records);
        }
    };

}
