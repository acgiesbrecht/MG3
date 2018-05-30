/**

 import com.gnadenheimer.mg3.DaoBase;
 import com.gnadenheimer.mg3.model.domain.TblTransferencias;
 import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;
 import javafx.fxml.FXML;
 import javafx.fxml.Initializable;
 import javafx.scene.control.TableColumn;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

 import java.net.URL;
 import java.time.format.DateTimeFormatter;
 import java.util.List;
 import java.util.ResourceBundle;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
package com.gnadenheimer.mg3.controller.admin;

 import com.gnadenheimer.mg3.DaoBase;
 import com.gnadenheimer.mg3.model.domain.TblTransferencias;
 import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;
import com.panemu.tiwulfx.common.TableCriteria;
import com.panemu.tiwulfx.common.TableData;
import com.panemu.tiwulfx.table.CheckBoxColumn;
import com.panemu.tiwulfx.table.CtaCteColumn;
import com.panemu.tiwulfx.table.LocalDateColumn;
import com.panemu.tiwulfx.table.NumberColumn;
import com.panemu.tiwulfx.table.TableControl;
import com.panemu.tiwulfx.table.TableController;
import com.panemu.tiwulfx.table.TextColumn;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


 public class AdminTransferenciasController implements Initializable {

 private static final Logger LOGGER = LogManager.getLogger(AdminTransferenciasController.class);
 private DaoBase<TblEntidades> daoTblEntidades = new DaoBase<>(TblEntidades.class);
 private DaoBase<TblTransferencias> daoTblTransferencias = new DaoBase<>(TblTransferencias.class);

 @FXML private TableControl transferenciasTable;

 //EntityManager em = Persistence.createEntityManagerFactory("pcb_PU").createEntityManager();
 /**
  * Initializes the controller class.
  *
  * @param url
 * @param rb
 *
 @Override public void initialize(URL url, ResourceBundle rb) {

 transferenciasTable.setController(cntlTblTransferencias);
 transferenciasTable.setRecordClass(TblTransferencias.class);

 NumberColumn<TblTransferencias, Integer> cId = new NumberColumn<>("id", Integer.class);
 cId.setText("Nro.");

 LocalDateColumn<TblTransferencias> cFechaCompromiso = new LocalDateColumn<>("fechahoraCompromiso");
 cFechaCompromiso.setText("Fecha Compromiso");
 cFechaCompromiso.setDateFormat(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
 cFechaCompromiso.setMinWidth(130);

 LocalDateColumn<TblTransferencias> cFecha = new LocalDateColumn<>("fechahora");
 cFecha.setText("Fecha pago");
 cFecha.setDateFormat(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
 //cFecha.setMinWidth(150);
 cFecha.setSortType(TableColumn.SortType.ASCENDING);

 /*TypeAheadColumn<TblTransferencias, TblEntidades> cEntidad = new TypeAheadColumn<>("idEntidad");
 cEntidad.setText("Nombre o Razon Social");
 cEntidad.setMinWidth(200);
 List<TblEntidades> lEntidades = daoTblEntidades.getList();
 lEntidades.forEach((p) -> {
 cEntidad.addItem(p.getNombreCompleto(), p);
 });*
 TextColumn<TblTransferencias> cNombre = new TextColumn<>("idEntidad.nombreCompleto");
 cNombre.setMinWidth(250);
 cNombre.setText("Nombre o Razon Social");

 CtaCteColumn<TblTransferencias, Integer> cCtaCte = new CtaCteColumn<>("idEntidad.ctacte", Integer.class);
 cCtaCte.setText("Cta. Cte.");

 TextColumn<TblTransferencias> cConcepto = new TextColumn<>("concepto");
 cConcepto.setText("Concepto");

 NumberColumn<TblTransferencias, Integer> cMontoAporte = new NumberColumn<>("montoAporte", Integer.class);
 cMontoAporte.setMinWidth(150);
 cMontoAporte.setText("Importe Aporte");

 NumberColumn<TblTransferencias, Integer> cMontoDonacion = new NumberColumn<>("montoDonacion", Integer.class);
 cMontoDonacion.setMinWidth(150);
 cMontoDonacion.setText("Importe Donacion");

 NumberColumn<TblTransferencias, Integer> cMontoTotal = new NumberColumn<>("montoTotal", Integer.class);
 cMontoTotal.setMinWidth(150);
 cMontoTotal.setText("Importe Total");

 CheckBoxColumn<TblTransferencias> cCobrado = new CheckBoxColumn<>("cobrado");
 cCobrado.setText("Cobrado");

 transferenciasTable.addColumn(cId, cFechaCompromiso, cFecha, cNombre, cCtaCte, cConcepto, cMontoAporte, cMontoDonacion, cMontoTotal, cCobrado);
 transferenciasTable.setVisibleComponents(false, TableControl.Component.BUTTON_INSERT);
 transferenciasTable.setVisibleComponents(false, TableControl.Component.BUTTON_EDIT);
 transferenciasTable.getTableView().setColumnResizePolicy((param) -> true);
 transferenciasTable.getTableView().getSortOrder().add(cFecha);

 transferenciasTable.reload();
 }

 private final TableController<TblTransferencias> cntlTblTransferencias = new TableController<TblTransferencias>() {

 @Override public TableData loadData(int startIndex, List<TableCriteria> filteredColumns, List<String> sortedColumns, List<TableColumn.SortType> sortingOrders, int maxResult) {
 return daoTblTransferencias.fetch(startIndex, filteredColumns, sortedColumns, sortingOrders, maxResult);
 }

 @Override public List<TblTransferencias> insert(List<TblTransferencias> newRecords) {
 return daoTblTransferencias.insert(newRecords);
 }

 @Override public List<TblTransferencias> update(List<TblTransferencias> records) {
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
 }*
 @Override public void delete(List<TblTransferencias> records) {
 daoTblTransferencias.delete(records);
 }
 };

 }
 */
