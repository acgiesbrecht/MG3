/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "TBL_CUENTAS_CONTABLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCuentasContables.findAll", query = "SELECT t FROM TblCuentasContables t"),
    @NamedQuery(name = "TblCuentasContables.findById", query = "SELECT t FROM TblCuentasContables t WHERE t.id = :id"),
    @NamedQuery(name = "TblCuentasContables.findByDescripcion", query = "SELECT t FROM TblCuentasContables t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TblCuentasContables.findByImputable", query = "SELECT t FROM TblCuentasContables t WHERE t.imputable = :imputable")})
public class TblCuentasContables implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaContableHaber")
    private List<TblAsientos> tblAsientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaContableDebe")
    private List<TblAsientos> tblAsientosList1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaContableHaber")
    private List<TblAsientosTemporales> tblAsientosTemporalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaContableDebe")
    private List<TblAsientosTemporales> tblAsientosTemporalesList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaContableCtaCtePorDefecto")
    private List<TblCentrosDeCosto> tblCentrosDeCostoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentaContableEfectivoPorDefecto")
    private List<TblCentrosDeCosto> tblCentrosDeCostoList1;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "IMPUTABLE")
    private Boolean imputable;
    @OneToMany(mappedBy = "idCuentaMadre")
    private List<TblCuentasContables> tblCuentasContablesList;
    @JoinColumn(name = "ID_CUENTA_MADRE", referencedColumnName = "ID")
    @ManyToOne
    private TblCuentasContables idCuentaMadre;

    public TblCuentasContables() {
    }

    public TblCuentasContables(Integer id) {
        this.id = id;
    }

    public TblCuentasContables(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getImputable() {
        return imputable;
    }

    public void setImputable(Boolean imputable) {
        this.imputable = imputable;
    }

    @XmlTransient
    public List<TblCuentasContables> getTblCuentasContablesList() {
        return tblCuentasContablesList;
    }

    public void setTblCuentasContablesList(List<TblCuentasContables> tblCuentasContablesList) {
        this.tblCuentasContablesList = tblCuentasContablesList;
    }

    public TblCuentasContables getIdCuentaMadre() {
        return idCuentaMadre;
    }

    public void setIdCuentaMadre(TblCuentasContables idCuentaMadre) {
        this.idCuentaMadre = idCuentaMadre;
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
        if (!(object instanceof TblCuentasContables)) {
            return false;
        }
        TblCuentasContables other = (TblCuentasContables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @XmlTransient
    public List<TblAsientosTemporales> getTblAsientosTemporalesList() {
        return tblAsientosTemporalesList;
    }

    public void setTblAsientosTemporalesList(List<TblAsientosTemporales> tblAsientosTemporalesList) {
        this.tblAsientosTemporalesList = tblAsientosTemporalesList;
    }

    @XmlTransient
    public List<TblAsientosTemporales> getTblAsientosTemporalesList1() {
        return tblAsientosTemporalesList1;
    }

    public void setTblAsientosTemporalesList1(List<TblAsientosTemporales> tblAsientosTemporalesList1) {
        this.tblAsientosTemporalesList1 = tblAsientosTemporalesList1;
    }

    @XmlTransient
    public List<TblCentrosDeCosto> getTblCentrosDeCostoList() {
        return tblCentrosDeCostoList;
    }

    public void setTblCentrosDeCostoList(List<TblCentrosDeCosto> tblCentrosDeCostoList) {
        this.tblCentrosDeCostoList = tblCentrosDeCostoList;
    }

    @XmlTransient
    public List<TblCentrosDeCosto> getTblCentrosDeCostoList1() {
        return tblCentrosDeCostoList1;
    }

    public void setTblCentrosDeCostoList1(List<TblCentrosDeCosto> tblCentrosDeCostoList1) {
        this.tblCentrosDeCostoList1 = tblCentrosDeCostoList1;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList1() {
        return tblAsientosList1;
    }

    public void setTblAsientosList1(List<TblAsientos> tblAsientosList1) {
        this.tblAsientosList1 = tblAsientosList1;
    }

}
