/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Adrian Giesbrecht
 */
@Embeddable
public class TblRecibosCompraFacturasCompraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RECIBO")
    private Integer idRecibo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_FACTURA_COMPRA")
    private Integer idFacturaCompra;

    public TblRecibosCompraFacturasCompraPK() {
    }

    public TblRecibosCompraFacturasCompraPK(Integer idRecibo, Integer idFacturaCompra) {
        this.idRecibo = idRecibo;
        this.idFacturaCompra = idFacturaCompra;
    }

    public Integer getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Integer getIdFacturaCompra() {
        return idFacturaCompra;
    }

    public void setIdFacturaCompra(Integer idFacturaCompra) {
        this.idFacturaCompra = idFacturaCompra;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (Integer) idRecibo;
        hash += (Integer) idFacturaCompra;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRecibosCompraFacturasCompraPK)) {
            return false;
        }
        TblRecibosCompraFacturasCompraPK other = (TblRecibosCompraFacturasCompraPK) object;
        if (this.idRecibo != other.idRecibo) {
            return false;
        }
        if (this.idFacturaCompra != other.idFacturaCompra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parah.mg.domain.TblRecibosCompraFacturasCompraPK[ idRecibo=" + idRecibo + ", idFacturaCompra=" + idFacturaCompra + " ]";
    }

}
