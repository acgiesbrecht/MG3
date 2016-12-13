/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain.models;

import com.gnadenheimer.mg.domain.TblEventoDetalle;
import com.gnadenheimer.mg.domain.miembros.TblEntidades;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adriang
 */
@Entity
public class PagosEventoPendientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private TblEventoDetalle tblEventoDetalle;

    private Boolean cobrado;

    public PagosEventoPendientes() {
    }

    public PagosEventoPendientes(TblEventoDetalle tblEventoDetalle, Boolean cobrado) {
        this.tblEventoDetalle = tblEventoDetalle;
        this.cobrado = cobrado;
    }

    /**
     * @return the tblEventoDetalle
     */
    public TblEventoDetalle getTblEventoDetalle() {
        return tblEventoDetalle;
    }

    /**
     * @param tblEventoDetalle the tblEventoDetalle to set
     */
    public void setTblEventoDetalle(TblEventoDetalle tblEventoDetalle) {
        this.tblEventoDetalle = tblEventoDetalle;
    }

    /**
     * @return the cobrado
     */
    public Boolean getCobrado() {
        return cobrado;
    }

    /**
     * @param cobrado the cobrado to set
     */
    public void setCobrado(Boolean cobrado) {
        this.cobrado = cobrado;
    }

}
