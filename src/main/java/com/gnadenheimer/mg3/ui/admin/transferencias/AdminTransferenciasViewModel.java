package com.gnadenheimer.mg3.ui.admin.transferencias;

import com.gnadenheimer.mg3.dao.TblTransferenciasDao;
import com.gnadenheimer.mg3.domain.TblTransferencias;
import com.gnadenheimer.mg3.ui.AbstractViewModel;

import javax.inject.Inject;

public class AdminTransferenciasViewModel extends AbstractViewModel<TblTransferencias> {

    @Inject
    TblTransferenciasDao tblTransferenciasDao;

    public void initialize() {
        setDao(tblTransferenciasDao);
        initializeAbstract();
    }

}
