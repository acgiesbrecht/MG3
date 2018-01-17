/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.ui.admin.transferencias;

import com.gnadenheimer.mg3.dao.TblEntidadesDao;
import com.gnadenheimer.mg3.dao.TblTransferenciasDao;
import com.gnadenheimer.mg3.domain.TblTransferencias;
import com.gnadenheimer.mg3.ui.AbstractView;
import com.gnadenheimer.mg3.ui.fieldextensions.*;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author adriang
 */
public class AdminTransferenciasView extends AbstractView<TblTransferencias> implements FxmlView<AdminTransferenciasViewModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminTransferenciasView.class);

    @Inject
    TblEntidadesDao tblEntidadesDao;
    @Inject
    TblTransferenciasDao tblTransferenciasDao;

    @InjectViewModel
    private AdminTransferenciasViewModel viewModel;

    public void initialize() {

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnInteger<TblTransferencias> cId = new TableColumnInteger<>("Nro","id",60.0);
        TableColumnLocalDateTime<TblTransferencias> cFechaCompromiso = new TableColumnLocalDateTime<>("Fecha/Hora Compromiso","fechahoraCompromiso",160.0);
        TableColumnLocalDateTime<TblTransferencias> cFecha = new TableColumnLocalDateTime<>("Fecha/Hora Pago","fechahora",160.0);
        TableColumnString<TblTransferencias> cNombre = new TableColumnString<>("Entidad","idEntidad.nombreCompleto",350.0);
        TableColumnCtaCte<TblTransferencias> cCtaCte = new TableColumnCtaCte<>("Cta. Cte. N°","idEntidad.ctacte",80.0);
        TableColumnString<TblTransferencias> cConcepto = new TableColumnString<>("Concepto","concepto",200.0);
        TableColumnInteger<TblTransferencias> cMontoAporte = new TableColumnInteger<>("Importe Aporte","montoAporte", 100.0);
        TableColumnInteger<TblTransferencias> cMontoDonacion = new TableColumnInteger<>("Importe Donacion","montoDonacion", 100.0);
        TableColumnInteger<TblTransferencias> cMontoTotal = new TableColumnInteger<>("Importe Total","montoTotal", 100.0);
        TableColumnCheckBox<TblTransferencias> cCobrado = new TableColumnCheckBox<>("Cobrado","cobrado");


        itemsTable.getColumns().addAll(cId, cFechaCompromiso, cFecha, cNombre, cCtaCte, cConcepto, cMontoAporte, cMontoDonacion, cMontoTotal, cCobrado);

    }

}
