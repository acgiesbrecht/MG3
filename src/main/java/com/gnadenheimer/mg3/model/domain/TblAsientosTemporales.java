/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "TBL_ASIENTOS_TEMPORALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAsientosTemporales.findAll", query = "SELECT t FROM TblAsientosTemporales t")
    ,
    @NamedQuery(name = "TblAsientosTemporales.findById", query = "SELECT t FROM TblAsientosTemporales t WHERE t.id = :id")
    ,
    @NamedQuery(name = "TblAsientosTemporales.findByFechahora", query = "SELECT t FROM TblAsientosTemporales t WHERE t.fechahora = :fechahora")
    ,
    @NamedQuery(name = "TblAsientosTemporales.findByMonto", query = "SELECT t FROM TblAsientosTemporales t WHERE t.monto = :monto")
    ,
    @NamedQuery(name = "TblAsientosTemporales.findByFacturado", query = "SELECT t FROM TblAsientosTemporales t WHERE t.facturado = :facturado")
    ,
    @NamedQuery(name = "TblAsientosTemporales.findByEsAporte", query = "SELECT t FROM TblAsientosTemporales t WHERE t.esAporte = :esAporte")})
public class TblAsientosTemporales implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private LocalDateTime fechahora;

    private Integer monto;

    private Boolean facturado;

    private Boolean esAporte;

    private List<TblAsientos> tblAsientosList;

    private TblCentrosDeCosto idCentroDeCostoDebe;

    private TblCentrosDeCosto idCentroDeCostoHaber;

    private TblCuentasContables idCuentaContableHaber;

    private TblCuentasContables idCuentaContableDebe;

    public TblAsientosTemporales() {
    }

    public TblAsientosTemporales(Integer id) {
        this.id = id;
    }

    public TblAsientosTemporales(Integer id, LocalDateTime fechahora, Integer monto, Boolean facturado) {
        this.id = id;
        this.fechahora = fechahora;
        this.monto = monto;
        this.facturado = facturado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "FACTURADO")
    public Boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(Boolean facturado) {
        this.facturado = facturado;
    }

    @Column(name = "ES_APORTE")
    public Boolean getEsAporte() {
        return esAporte;
    }

    public void setEsAporte(Boolean esAporte) {
        this.esAporte = esAporte;
    }

    @XmlTransient
    @ManyToMany(mappedBy = "tblAsientosTemporalesList")
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
    }

    @JoinColumn(name = "ID_CENTRO_DE_COSTO_DEBE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCentrosDeCosto getIdCentroDeCostoDebe() {
        return idCentroDeCostoDebe;
    }

    public void setIdCentroDeCostoDebe(TblCentrosDeCosto idCentroDeCostoDebe) {
        this.idCentroDeCostoDebe = idCentroDeCostoDebe;
    }

    @JoinColumn(name = "ID_CENTRO_DE_COSTO_HABER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCentrosDeCosto getIdCentroDeCostoHaber() {
        return idCentroDeCostoHaber;
    }

    public void setIdCentroDeCostoHaber(TblCentrosDeCosto idCentroDeCostoHaber) {
        this.idCentroDeCostoHaber = idCentroDeCostoHaber;
    }

    @JoinColumn(name = "ID_CUENTA_CONTABLE_HABER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCuentasContables getIdCuentaContableHaber() {
        return idCuentaContableHaber;
    }

    public void setIdCuentaContableHaber(TblCuentasContables idCuentaContableHaber) {
        this.idCuentaContableHaber = idCuentaContableHaber;
    }

    @JoinColumn(name = "ID_CUENTA_CONTABLE_DEBE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCuentasContables getIdCuentaContableDebe() {
        return idCuentaContableDebe;
    }

    public void setIdCuentaContableDebe(TblCuentasContables idCuentaContableDebe) {
        this.idCuentaContableDebe = idCuentaContableDebe;
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
        if (!(object instanceof TblAsientosTemporales)) {
            return false;
        }
        TblAsientosTemporales other = (TblAsientosTemporales) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblAsientosTemporales[ id=" + id + " ]";
    }

}
