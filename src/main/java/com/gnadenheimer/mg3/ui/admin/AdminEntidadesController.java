/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.ui.admin;

import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.miembros.TblEntidades;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class AdminEntidadesController implements Initializable {

    private DaoBase<TblEntidades> daoTblEntidades = new DaoBase<>(TblEntidades.class);

    @FXML
    private TableControl entidadesTable;

    /**
     * Initializes the ui class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entidadesTable.setController(cntlTblEntidades);
        entidadesTable.setRecordClass(TblEntidades.class);

        NumberColumn<TblEntidades, Integer> cID = new NumberColumn<>("id", Integer.class);
        cID.setText("ID");

        TextColumn<TblEntidades> cNombre = new TextColumn<>("nombres");
        cNombre.setText("Nombres");

        TextColumn<TblEntidades> cApellidos = new TextColumn<>("apellidos");
        cApellidos.setText("Apellidos");

        CtaCteColumn<TblEntidades, Integer> cCtaCte = new CtaCteColumn<>("ctacte", Integer.class);
        cCtaCte.setText("Cta. Cte.");
        cCtaCte.setSortType(TableColumn.SortType.ASCENDING);

        RucColumn<TblEntidades> cRuc = new RucColumn<>("rucSinDv");
        cRuc.setText("RUC");

        TextColumn<TblEntidades> cDomicilio = new TextColumn<>("domicilio");
        cDomicilio.setText("Domicilio");

        NumberColumn<TblEntidades, Integer> cBox = new NumberColumn<>("box", Integer.class);
        cBox.setText("Box");

        LocalDateColumn<TblEntidades> cFechaNacimiento = new LocalDateColumn<>("fechaNacimiento");
        cFechaNacimiento.setText("Fecha Nacimiento");
        cFechaNacimiento.setDateFormat(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        cFechaNacimiento.setMinWidth(150);

        CheckBoxColumn<TblEntidades> cEsMiembroActivo = new CheckBoxColumn<>("isMiembroActivo");

        entidadesTable.addColumn(cID, cNombre, cApellidos, cCtaCte, cRuc, cDomicilio, cBox, cFechaNacimiento, cEsMiembroActivo);
        entidadesTable.getTableView().setColumnResizePolicy((param) -> true);
        entidadesTable.getTableView().getSortOrder().add(cCtaCte);
        entidadesTable.reload();
    }

    private final TableController<TblEntidades> cntlTblEntidades = new TableController<TblEntidades>() {
        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
            return daoTblEntidades.fetch(startIndex, filteredColumns, sortedColumns, sortingOrders, maxResult);
        }

        @Override
        public List<TblEntidades> insert(List<TblEntidades> newRecords) {
            return daoTblEntidades.insert(newRecords);
        }

        @Override
        public List<TblEntidades> update(List<TblEntidades> records) {
            return daoTblEntidades.update(records);
        }

        /*
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
        }*/
        @Override
        public void delete(List<TblEntidades> records) {
            daoTblEntidades.delete(records);
        }
    };

}
