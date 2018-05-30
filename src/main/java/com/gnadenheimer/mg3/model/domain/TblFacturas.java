/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Table(name = "TBL_FACTURAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblFacturas.findAll", query = "SELECT t FROM TblFacturas t"),
    @NamedQuery(name = "TblFacturas.findByNro", query = "SELECT t FROM TblFacturas t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblFacturas.findByFechahora", query = "SELECT t FROM TblFacturas t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblFacturas.findByRazonSocial", query = "SELECT t FROM TblFacturas t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "TblFacturas.findByRuc", query = "SELECT t FROM TblFacturas t WHERE t.ruc = :ruc"),
    @NamedQuery(name = "TblFacturas.findByImporteDonacion", query = "SELECT t FROM TblFacturas t WHERE t.importeDonacion = :importeDonacion"),
    @NamedQuery(name = "TblFacturas.findByImporteAporte", query = "SELECT t FROM TblFacturas t WHERE t.importeAporte = :importeAporte"),
    @NamedQuery(name = "TblFacturas.findByAnulado", query = "SELECT t FROM TblFacturas t WHERE t.anulado = :anulado"),
    @NamedQuery(name = "TblFacturas.findByDomicilio", query = "SELECT t FROM TblFacturas t WHERE t.domicilio = :domicilio"),
    @NamedQuery(name = "TblFacturas.findByCasillaDeCorreo", query = "SELECT t FROM TblFacturas t WHERE t.casillaDeCorreo = :casillaDeCorreo")})
public class TblFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRO")
    private Integer nro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    
    private LocalDateTime fechahora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUC")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPORTE_DONACION")
    private Integer importeDonacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPORTE_APORTE")
    private Integer importeAporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANULADO")
    private Boolean anulado;
    @Size(max = 255)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "CASILLA_DE_CORREO")
    private Integer casillaDeCorreo;
    @JoinTable(name = "TBL_FACTURAS_ASIENTOS", joinColumns = {
        @JoinColumn(name = "NRO_FACTURA", referencedColumnName = "NRO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientos> tblAsientosList;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblEntidades idEntidad;
    @JoinColumn(name = "ID_TIMBRADO", referencedColumnName = "NRO")
    @ManyToOne(optional = false)
    private TblTimbrados idTimbrado;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;

    public TblFacturas() {
    }

    public TblFacturas(Integer nro) {
        this.nro = nro;
    }

    public TblFacturas(Integer nro, LocalDateTime fechahora, String razonSocial, String ruc, Integer importeDonacion, Integer importeAporte, Boolean anulado) {
        this.nro = nro;
        this.fechahora = fechahora;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.importeDonacion = importeDonacion;
        this.importeAporte = importeAporte;
        this.anulado = anulado;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Integer getImporteDonacion() {
        return importeDonacion;
    }

    public void setImporteDonacion(Integer importeDonacion) {
        this.importeDonacion = importeDonacion;
    }

    public Integer getImporteAporte() {
        return importeAporte;
    }

    public void setImporteAporte(Integer importeAporte) {
        this.importeAporte = importeAporte;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getCasillaDeCorreo() {
        return casillaDeCorreo;
    }

    public void setCasillaDeCorreo(Integer casillaDeCorreo) {
        this.casillaDeCorreo = casillaDeCorreo;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
    }

    public TblEntidades getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(TblEntidades idEntidad) {
        this.idEntidad = idEntidad;
    }

    public TblTimbrados getIdTimbrado() {
        return idTimbrado;
    }

    public void setIdTimbrado(TblTimbrados idTimbrado) {
        this.idTimbrado = idTimbrado;
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
        if (!(object instanceof TblFacturas)) {
            return false;
        }
        TblFacturas other = (TblFacturas) object;
        return (this.nro != null || other.nro == null) && (this.nro == null || this.nro.equals(other.nro));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblFacturas[ nro=" + nro + " ]";
    }

}
