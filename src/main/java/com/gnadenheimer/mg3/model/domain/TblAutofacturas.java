/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

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
@Table(name = "TBL_AUTOFACTURAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAutofacturas.findAll", query = "SELECT t FROM TblAutofacturas t"),
    @NamedQuery(name = "TblAutofacturas.findById", query = "SELECT t FROM TblAutofacturas t WHERE t.id = :id"),
    @NamedQuery(name = "TblAutofacturas.findByNro", query = "SELECT t FROM TblAutofacturas t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblAutofacturas.findByFechahora", query = "SELECT t FROM TblAutofacturas t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblAutofacturas.findByNombre", query = "SELECT t FROM TblAutofacturas t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblAutofacturas.findByDomicilio", query = "SELECT t FROM TblAutofacturas t WHERE t.domicilio = :domicilio"),
    @NamedQuery(name = "TblAutofacturas.findByDireccionDeTransaccion", query = "SELECT t FROM TblAutofacturas t WHERE t.direccionDeTransaccion = :direccionDeTransaccion"),
    @NamedQuery(name = "TblAutofacturas.findByCi", query = "SELECT t FROM TblAutofacturas t WHERE t.ci = :ci"),
    @NamedQuery(name = "TblAutofacturas.findByCantidad", query = "SELECT t FROM TblAutofacturas t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "TblAutofacturas.findByConcepto", query = "SELECT t FROM TblAutofacturas t WHERE t.concepto = :concepto"),
    @NamedQuery(name = "TblAutofacturas.findByPrecioUnitario", query = "SELECT t FROM TblAutofacturas t WHERE t.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "TblAutofacturas.findByMonto", query = "SELECT t FROM TblAutofacturas t WHERE t.monto = :monto"),
    @NamedQuery(name = "TblAutofacturas.findByObservacion", query = "SELECT t FROM TblAutofacturas t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TblAutofacturas.findByAnulado", query = "SELECT t FROM TblAutofacturas t WHERE t.anulado = :anulado")})
public class TblAutofacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NRO")
    private String nro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    private LocalDateTime fechahora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DIRECCION_DE_TRANSACCION")
    private String direccionDeTransaccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CI")
    private String ci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CONCEPTO")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_UNITARIO")
    private Integer precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private Integer monto;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANULADO")
    private Boolean anulado;
    @JoinTable(name = "TBL_AUTOFACTURAS_ASIENTOS", joinColumns = {
        @JoinColumn(name = "ID_AUTOFACTURA", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientos> tblAsientosList;
    @JoinColumn(name = "ID_TIMBRADO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblTimbradosAutofacturas idTimbrado;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONDICION_CONTADO")
    private Boolean condicionContado;

    public TblAutofacturas() {
    }

    public TblAutofacturas(Integer id) {
        this.id = id;
    }

    public TblAutofacturas(Integer id, String nro, LocalDateTime fechahora, String nombre, String domicilio, Boolean condicionContado, String direccionDeTransaccion, String ci, Integer cantidad, String concepto, Integer precioUnitario, Integer monto, Boolean anulado) {
        this.id = id;
        this.nro = nro;
        this.fechahora = fechahora;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.direccionDeTransaccion = direccionDeTransaccion;
        this.ci = ci;
        this.cantidad = cantidad;
        this.concepto = concepto;
        this.precioUnitario = precioUnitario;
        this.monto = monto;
        this.anulado = anulado;
        this.condicionContado = condicionContado;
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

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDireccionDeTransaccion() {
        return direccionDeTransaccion;
    }

    public void setDireccionDeTransaccion(String direccionDeTransaccion) {
        this.direccionDeTransaccion = direccionDeTransaccion;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
    }

    public TblTimbradosAutofacturas getIdTimbrado() {
        return idTimbrado;
    }

    public void setIdTimbrado(TblTimbradosAutofacturas idTimbrado) {
        this.idTimbrado = idTimbrado;
    }

    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
    }

    public Boolean getCondicionContado() {
        return condicionContado;
    }

    public void setCondicionContado(Boolean condicionContado) {
        this.condicionContado = condicionContado;
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
        if (!(object instanceof TblAutofacturas)) {
            return false;
        }
        TblAutofacturas other = (TblAutofacturas) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblAutofacturas[ id=" + id + " ]";
    }

}
