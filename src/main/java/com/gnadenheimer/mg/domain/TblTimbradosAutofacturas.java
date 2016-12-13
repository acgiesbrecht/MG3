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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_TIMBRADOS_AUTOFACTURAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTimbradosAutofacturas.findAll", query = "SELECT t FROM TblTimbradosAutofacturas t"),
    @NamedQuery(name = "TblTimbradosAutofacturas.findById", query = "SELECT t FROM TblTimbradosAutofacturas t WHERE t.id = :id"),
    @NamedQuery(name = "TblTimbradosAutofacturas.findByNro", query = "SELECT t FROM TblTimbradosAutofacturas t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblTimbradosAutofacturas.findByFechaInicio", query = "SELECT t FROM TblTimbradosAutofacturas t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TblTimbradosAutofacturas.findByFechaVencimiento", query = "SELECT t FROM TblTimbradosAutofacturas t WHERE t.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "TblTimbradosAutofacturas.findByNroFacturaIncio", query = "SELECT t FROM TblTimbradosAutofacturas t WHERE t.nroFacturaIncio = :nroFacturaIncio"),
    @NamedQuery(name = "TblTimbradosAutofacturas.findByNroFacturaFin", query = "SELECT t FROM TblTimbradosAutofacturas t WHERE t.nroFacturaFin = :nroFacturaFin"),
    @NamedQuery(name = "TblTimbradosAutofacturas.findByActivo", query = "SELECT t FROM TblTimbradosAutofacturas t WHERE t.activo = :activo")})
public class TblTimbradosAutofacturas implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTimbrado")
    private List<TblAutofacturas> tblAutofacturasList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "NRO")
    private String nro;
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
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;

    public TblTimbradosAutofacturas() {
    }

    public TblTimbradosAutofacturas(Integer id) {
        this.id = id;
    }

    public TblTimbradosAutofacturas(Integer id, String nro, LocalDate fechaInicio, LocalDate fechaVencimiento, Integer nroFacturaIncio, Integer nroFacturaFin, Boolean activo) {
        this.id = id;
        this.nro = nro;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.nroFacturaIncio = nroFacturaIncio;
        this.nroFacturaFin = nroFacturaFin;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
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

    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof TblTimbradosAutofacturas)) {
            return false;
        }
        TblTimbradosAutofacturas other = (TblTimbradosAutofacturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nro;
    }

    @XmlTransient
    public List<TblAutofacturas> getTblAutofacturasList() {
        return tblAutofacturasList;
    }

    public void setTblAutofacturasList(List<TblAutofacturas> tblAutofacturasList) {
        this.tblAutofacturasList = tblAutofacturasList;
    }

}
