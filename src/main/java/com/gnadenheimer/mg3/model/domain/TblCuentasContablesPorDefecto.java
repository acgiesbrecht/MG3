/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "TBL_CUENTAS_CONTABLES_POR_DEFECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCuentasContablesPorDefecto.findAll", query = "SELECT t FROM TblCuentasContablesPorDefecto t"),
    @NamedQuery(name = "TblCuentasContablesPorDefecto.findById", query = "SELECT t FROM TblCuentasContablesPorDefecto t WHERE t.id = :id")})
public class TblCuentasContablesPorDefecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ID_CUENTA_CTA_CTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaCtaCte;
    @JoinColumn(name = "ID_CUENTA_CAJA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaCaja;
    @JoinColumn(name = "ID_CUENTA_DONACIONES", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaDonaciones;
    @JoinColumn(name = "ID_CUENTA_APORTES", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaAportes;
    @JoinColumn(name = "ID_CUENTA_A_COBRAR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaACobrar;
    @JoinColumn(name = "ID_CUENTA_DEBE_COMPRAS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaDebeCompras;
    @JoinColumn(name = "ID_CUENTA_HABER_COMPRAS_FACTURA_CREDITO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaHaberComprasFacturaCredito;
    @JoinColumn(name = "ID_CUENTA_HABER_COMPRAS_FACTURA_CONTADO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCuentasContables idCuentaHaberComprasFacturaContado;

    public TblCuentasContablesPorDefecto() {
    }

    public TblCuentasContablesPorDefecto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TblCuentasContables getIdCuentaCtaCte() {
        return idCuentaCtaCte;
    }

    public void setIdCuentaCtaCte(TblCuentasContables idCuentaCtaCte) {
        this.idCuentaCtaCte = idCuentaCtaCte;
    }

    public TblCuentasContables getIdCuentaCaja() {
        return idCuentaCaja;
    }

    public void setIdCuentaCaja(TblCuentasContables idCuentaCaja) {
        this.idCuentaCaja = idCuentaCaja;
    }

    public TblCuentasContables getIdCuentaDonaciones() {
        return idCuentaDonaciones;
    }

    public void setIdCuentaDonaciones(TblCuentasContables idCuentaDonaciones) {
        this.idCuentaDonaciones = idCuentaDonaciones;
    }

    public TblCuentasContables getIdCuentaAportes() {
        return idCuentaAportes;
    }

    public void setIdCuentaAportes(TblCuentasContables idCuentaAportes) {
        this.idCuentaAportes = idCuentaAportes;
    }

    public TblCuentasContables getIdCuentaACobrar() {
        return idCuentaACobrar;
    }

    public void setIdCuentaACobrar(TblCuentasContables idCuentaACobrar) {
        this.idCuentaACobrar = idCuentaACobrar;
    }

    public TblCuentasContables getIdCuentaDebeCompras() {
        return idCuentaDebeCompras;
    }

    public void setIdCuentaDebeCompras(TblCuentasContables idCuentaDebeCompras) {
        this.idCuentaDebeCompras = idCuentaDebeCompras;
    }

    public TblCuentasContables getIdCuentaHaberComprasFacturaCredito() {
        return idCuentaHaberComprasFacturaCredito;
    }

    public void setIdCuentaHaberComprasFacturaCredito(TblCuentasContables idCuentaHaberComprasFacturaCredito) {
        this.idCuentaHaberComprasFacturaCredito = idCuentaHaberComprasFacturaCredito;
    }

    public TblCuentasContables getIdCuentaHaberComprasFacturaContado() {
        return idCuentaHaberComprasFacturaContado;
    }

    public void setIdCuentaHaberComprasFacturaContado(TblCuentasContables idCuentaHaberComprasFacturaContado) {
        this.idCuentaHaberComprasFacturaContado = idCuentaHaberComprasFacturaContado;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCuentasContablesPorDefecto)) {
            return false;
        }
        TblCuentasContablesPorDefecto other = (TblCuentasContablesPorDefecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.model.domain.TblCuentasContablesPorDefecto[ id=" + id + " ]";
    }

}
