/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "TBL_RECIBOS_COMPRA_FACTURAS_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRecibosCompraFacturasCompra.findAll", query = "SELECT t FROM TblRecibosCompraFacturasCompra t"),
    @NamedQuery(name = "TblRecibosCompraFacturasCompra.findByIdRecibo", query = "SELECT t FROM TblRecibosCompraFacturasCompra t WHERE t.tblRecibosCompraFacturasCompraPK.idRecibo = :idRecibo"),
    @NamedQuery(name = "TblRecibosCompraFacturasCompra.findByIdFacturaCompra", query = "SELECT t FROM TblRecibosCompraFacturasCompra t WHERE t.tblRecibosCompraFacturasCompraPK.idFacturaCompra = :idFacturaCompra"),
    @NamedQuery(name = "TblRecibosCompraFacturasCompra.findByMonto", query = "SELECT t FROM TblRecibosCompraFacturasCompra t WHERE t.monto = :monto")})
public class TblRecibosCompraFacturasCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblRecibosCompraFacturasCompraPK tblRecibosCompraFacturasCompraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private Integer monto;
    @JoinColumn(name = "ID_FACTURA_COMPRA", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblFacturasCompra tblFacturasCompra;
    @JoinColumn(name = "ID_RECIBO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblRecibosCompra tblRecibosCompra;

    public TblRecibosCompraFacturasCompra() {
    }

    public TblRecibosCompraFacturasCompra(TblRecibosCompraFacturasCompraPK tblRecibosCompraFacturasCompraPK) {
        this.tblRecibosCompraFacturasCompraPK = tblRecibosCompraFacturasCompraPK;
    }

    public TblRecibosCompraFacturasCompra(TblRecibosCompraFacturasCompraPK tblRecibosCompraFacturasCompraPK, Integer monto) {
        this.tblRecibosCompraFacturasCompraPK = tblRecibosCompraFacturasCompraPK;
        this.monto = monto;
    }

    public TblRecibosCompraFacturasCompra(Integer idRecibo, Integer idFacturaCompra) {
        this.tblRecibosCompraFacturasCompraPK = new TblRecibosCompraFacturasCompraPK(idRecibo, idFacturaCompra);
    }

    public TblRecibosCompraFacturasCompraPK getTblRecibosCompraFacturasCompraPK() {
        return tblRecibosCompraFacturasCompraPK;
    }

    public void setTblRecibosCompraFacturasCompraPK(TblRecibosCompraFacturasCompraPK tblRecibosCompraFacturasCompraPK) {
        this.tblRecibosCompraFacturasCompraPK = tblRecibosCompraFacturasCompraPK;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public TblFacturasCompra getTblFacturasCompra() {
        return tblFacturasCompra;
    }

    public void setTblFacturasCompra(TblFacturasCompra tblFacturasCompra) {
        this.tblFacturasCompra = tblFacturasCompra;
    }

    public TblRecibosCompra getTblRecibosCompra() {
        return tblRecibosCompra;
    }

    public void setTblRecibosCompra(TblRecibosCompra tblRecibosCompra) {
        this.tblRecibosCompra = tblRecibosCompra;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (tblRecibosCompraFacturasCompraPK != null ? tblRecibosCompraFacturasCompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblRecibosCompraFacturasCompra)) {
            return false;
        }
        TblRecibosCompraFacturasCompra other = (TblRecibosCompraFacturasCompra) object;
        return (this.tblRecibosCompraFacturasCompraPK != null || other.tblRecibosCompraFacturasCompraPK == null) && (this.tblRecibosCompraFacturasCompraPK == null || this.tblRecibosCompraFacturasCompraPK.equals(other.tblRecibosCompraFacturasCompraPK));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblRecibosCompraFacturasCompra[ tblRecibosCompraFacturasCompraPK=" + tblRecibosCompraFacturasCompraPK + " ]";
    }

}
