/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.ui.admin;

import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblCentrosDeCosto;
import com.gnadenheimer.mg3.domain.TblEventoTipos;
import com.gnadenheimer.mg3.domain.TblEventos;
import com.gnadenheimer.mg3.domain.TblGrupos;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.Utils;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableCriteria.Operator;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class AdminEventosController implements Initializable {

    @Inject
    CurrentUser currentUser;

    private DaoBase<TblEventos> daoTblEventos = new DaoBase<>(TblEventos.class);
    private DaoBase<TblEventoTipos> daoTblEventoTipos = new DaoBase<>(TblEventoTipos.class);
    private DaoBase<TblGrupos> daoTblGrupos = new DaoBase<>(TblGrupos.class);
    private DaoBase<TblCentrosDeCosto> daoTblCentrosDeCosto = new DaoBase<>(TblCentrosDeCosto.class);

    @FXML
    private TableControl<TblEventos> eventosTable;

    /**
     * Initializes the ui class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventosTable.setController(cntlTblEventos);
        eventosTable.setRecordClass(TblEventos.class);

        LocalDateColumn<TblEventos> cFecha = new LocalDateColumn<>("fecha");
        cFecha.setText("Fecha");
        cFecha.setDateFormat(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        cFecha.setMinWidth(150);

        TypeAheadColumn<TblEventos, TblEventoTipos> cTipo = new TypeAheadColumn<>("idEventoTipo");
        daoTblEventoTipos.getList("select t from TblEventoTipos t where t.id < 4").forEach((e) -> {
            cTipo.addItem(e.getDescripcion(), e);
        });
        cTipo.setText("Tipo de Evento");

        TextColumn<TblEventos> cDescripcion = new TextColumn<>("descripcion");
        cDescripcion.setText("Descripcion");

        TypeAheadColumn<TblEventos, TblCentrosDeCosto> cCentroDeCosto = new TypeAheadColumn<>("idCentroDeCosto");
        daoTblCentrosDeCosto.getList().forEach((e) -> {
            cCentroDeCosto.addItem(e.getDescripcion(), e);
        });
        cCentroDeCosto.setText("Centro de Costo");

        TypeAheadColumn<TblEventos, TblGrupos> cGrupo = new TypeAheadColumn<>("idGrupo");
        daoTblGrupos.getList().forEach((e) -> {
            cGrupo.addItem(e.getDescripcion(), e);
        });
        cGrupo.setText("Grupo de Acceso");

        NumberColumn<TblEventos, Integer> cAporte = new NumberColumn<>("porcentajeAporte", Integer.class);
        cAporte.setText("% Aporte");

        NumberColumn<TblEventos, Integer> cDonacion = new NumberColumn<>("porcentajeDonacion", Integer.class);
        cDonacion.setText("% Donacion");

        eventosTable.addColumn(cFecha, cTipo, cDescripcion, cCentroDeCosto, cGrupo, cAporte, cDonacion);
        eventosTable.getTableView().setColumnResizePolicy((param) -> true);
        eventosTable.getTableView().getSortOrder().add(cFecha);
        eventosTable.reload();
    }

    private final TableController<TblEventos> cntlTblEventos = new TableController<TblEventos>() {
        @Override
        public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {

            TableCriteria<List<TblGrupos>> filtroGrupos = new TableCriteria<>("idGrupo", Operator.in, currentUser.getUser().getTblGruposList());
            TableCriteria<LocalDate> filtroInicioPeriodoFiscal = new TableCriteria<>("fecha", Operator.ge, Utils.inicioPeriodoFiscal());
            TableCriteria<LocalDate> filtroFinPeriodoFiscal = new TableCriteria<>("fecha", Operator.le, Utils.finPeriodoFiscal());
            filteredColumns.add(filtroGrupos);
            filteredColumns.add(filtroInicioPeriodoFiscal);
            filteredColumns.add(filtroFinPeriodoFiscal);
            return daoTblEventos.fetch(startIndex, filteredColumns, sortedColumns, sortingOrders, maxResult);
        }

        @Override
        public List<TblEventos> insert(List<TblEventos> newRecords) {
            return daoTblEventos.insert(newRecords);
        }

        @Override
        public TblEventos preInsert(TblEventos newRecord) {
            daoTblCentrosDeCosto.getList().stream().filter((c) -> (c.getPreferido())).forEach((c) -> {
                newRecord.setIdCentroDeCosto(c);
            });
            newRecord.setIdGrupo(currentUser.getUser().getTblGruposList().get(0));
            newRecord.setIdUser(currentUser.getUser());
            return newRecord;
        }

        @Override
        public List<TblEventos> update(List<TblEventos> records) {
            return daoTblEventos.update(records);
        }

        /*
        @Override
        public boolean canDelete(TableControl table) {

             * This checking is not perfect. If there are Persons filtered thus not
             * displayed in tblPerson, the delete is not canceled. An error will be displayed
             * along with the stack trace. The better implementation is to count the children
             * from database and ensure the result is zero.

            boolean nochildren = tblEventos.getRecords().isEmpty();
            if (!nochildren) {
                MessageDialogBuilder.error().message("Unable to delete TblBasPrecios (code "
                        + TblEventos.getSelectedItem().getCode() + ") because"
                        + "\nthere are Persons refer to it!").show(getScene().getWindow());
            }
            return nochildren;
        }*/
        @Override
        public void delete(List<TblEventos> records) {
            daoTblEventos.delete(records);
        }
    };

}
