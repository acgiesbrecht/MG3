/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_TIMBRADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTimbrados.findAll", query = "SELECT t FROM TblTimbrados t"),
    @NamedQuery(name = "TblTimbrados.findByNro", query = "SELECT t FROM TblTimbrados t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblTimbrados.findByFechaInicio", query = "SELECT t FROM TblTimbrados t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TblTimbrados.findByFechaVencimiento", query = "SELECT t FROM TblTimbrados t WHERE t.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "TblTimbrados.findByNroFacturaIncio", query = "SELECT t FROM TblTimbrados t WHERE t.nroFacturaIncio = :nroFacturaIncio"),
    @NamedQuery(name = "TblTimbrados.findByNroFacturaFin", query = "SELECT t FROM TblTimbrados t WHERE t.nroFacturaFin = :nroFacturaFin"),
    @NamedQuery(name = "TblTimbrados.findByActivo", query = "SELECT t FROM TblTimbrados t WHERE t.activo = :activo")})
public class TblTimbrados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRO")
    private Integer nro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRO_FACTURA_INCIO")
    private Integer nroFacturaIncio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRO_FACTURA_FIN")
    private Integer nroFacturaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private Boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTimbrado")
    private List<TblFacturas> tblFacturasList;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;

    public TblTimbrados() {
    }

    public TblTimbrados(Integer nro) {
        this.nro = nro;
    }

    public TblTimbrados(Integer nro, LocalDate fechaInicio, LocalDate fechaVencimiento, Integer nroFacturaIncio, Integer nroFacturaFin, Boolean activo) {
        this.nro = nro;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.nroFacturaIncio = nroFacturaIncio;
        this.nroFacturaFin = nroFacturaFin;
        this.activo = activo;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getNroFacturaIncio() {
        return nroFacturaIncio;
    }

    public void setNroFacturaIncio(Integer nroFacturaIncio) {
        this.nroFacturaIncio = nroFacturaIncio;
    }

    public Integer getNroFacturaFin() {
        return nroFacturaFin;
    }

    public void setNroFacturaFin(Integer nroFacturaFin) {
        this.nroFacturaFin = nroFacturaFin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<TblFacturas> getTblFacturasList() {
        return tblFacturasList;
    }

    public void setTblFacturasList(List<TblFacturas> tblFacturasList) {
        this.tblFacturasList = tblFacturasList;
    }

    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (nro != null ? nro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTimbrados)) {
            return false;
        }
        TblTimbrados other = (TblTimbrados) object;
        if ((this.nro == null && other.nro != null) || (this.nro != null && !this.nro.equals(other.nro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nro.toString();
    }

}
