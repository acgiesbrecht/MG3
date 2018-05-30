package com.gnadenheimer.mg3.ui.admin.entidades;

import com.gnadenheimer.mg3.AbstractViewModel;
import com.gnadenheimer.mg3.model.dao.TblEntidadesDao;
import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import javax.inject.Inject;

public class TblEntidadesViewModel extends AbstractViewModel<TblEntidades> {

    @Inject
    TblEntidadesDao tblEntidadesDao;

    public void initialize() {
        setDao(tblEntidadesDao);
        initializeAbstract();
    }

    public IntegerProperty idProperty() {
        return itemWrapper.field("id", TblEntidades::getId, TblEntidades::setId);
    }

    public StringProperty nombresProperty() {
        return itemWrapper.field("nombres", TblEntidades::getNombres, TblEntidades::setNombres);
    }

    public StringProperty apellidosProperty() {
        return itemWrapper.field("apellidos", TblEntidades::getApellidos, TblEntidades::setApellidos);
    }
}
