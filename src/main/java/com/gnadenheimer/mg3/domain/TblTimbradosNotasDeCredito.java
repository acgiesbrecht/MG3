/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author AdminLocal
 */
@Entity
@Table(name = "TBL_TIMBRADOS_NOTAS_DE_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findAll", query = "SELECT t FROM TblTimbradosNotasDeCredito t"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findById", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.id = :id"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByNro", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByEstablecimiento", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.establecimiento = :establecimiento"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByPuntoDeExpedicion", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.puntoDeExpedicion = :puntoDeExpedicion"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByFechaInicio", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByFechaVencimiento", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByNroNotaDeCreditoIncio", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.nroNotaDeCreditoIncio = :nroNotaDeCreditoIncio"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByNroNotaDeCreditoFin", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.nroNotaDeCreditoFin = :nroNotaDeCreditoFin"),
    @NamedQuery(name = "TblTimbradosNotasDeCredito.findByActivo", query = "SELECT t FROM TblTimbradosNotasDeCredito t WHERE t.activo = :activo")})
public class TblTimbradosNotasDeCredito implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTimbrado")
    private List<TblNotasDeCredito> tblNotasDeCreditoList;

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
    @Size(min = 1, max = 3)
    @Column(name = "ESTABLECIMIENTO")
    private String establecimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "PUNTO_DE_EXPEDICION")
    private String puntoDeExpedicion;
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
    @Column(name = "NRO_NOTA_DE_CREDITO_INCIO")
    private int nroNotaDeCreditoIncio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRO_NOTA_DE_CREDITO_FIN")
    private int nroNotaDeCreditoFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private Boolean activo;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;

    public TblTimbradosNotasDeCredito() {
    }

    public TblTimbradosNotasDeCredito(Integer id) {
        this.id = id;
    }

    public TblTimbradosNotasDeCredito(Integer id, String nro, String establecimiento, String puntoDeExpedicion, LocalDate fechaInicio, LocalDate fechaVencimiento, int nroNotaDeCreditoIncio, int nroNotaDeCreditoFin, Boolean activo) {
        this.id = id;
        this.nro = nro;
        this.establecimiento = establecimiento;
        this.puntoDeExpedicion = puntoDeExpedicion;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.nroNotaDeCreditoIncio = nroNotaDeCreditoIncio;
        this.nroNotaDeCreditoFin = nroNotaDeCreditoFin;
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

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getPuntoDeExpedicion() {
        return puntoDeExpedicion;
    }

    public void setPuntoDeExpedicion(String puntoDeExpedicion) {
        this.puntoDeExpedicion = puntoDeExpedicion;
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

    public int getNroNotaDeCreditoIncio() {
        return nroNotaDeCreditoIncio;
    }

    public void setNroNotaDeCreditoIncio(int nroNotaDeCreditoIncio) {
        this.nroNotaDeCreditoIncio = nroNotaDeCreditoIncio;
    }

    public int getNroNotaDeCreditoFin() {
        return nroNotaDeCreditoFin;
    }

    public void setNroNotaDeCreditoFin(int nroNotaDeCreditoFin) {
        this.nroNotaDeCreditoFin = nroNotaDeCreditoFin;
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTimbradosNotasDeCredito)) {
            return false;
        }
        TblTimbradosNotasDeCredito other = (TblTimbradosNotasDeCredito) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblTimbradosNotasDeCredito[ id=" + id + " ]";
    }

    @XmlTransient
    public List<TblNotasDeCredito> getTblNotasDeCreditoList() {
        return tblNotasDeCreditoList;
    }

    public void setTblNotasDeCreditoList(List<TblNotasDeCredito> tblNotasDeCreditoList) {
        this.tblNotasDeCreditoList = tblNotasDeCreditoList;
    }

}
