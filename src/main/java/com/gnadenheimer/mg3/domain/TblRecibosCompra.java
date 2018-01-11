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
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "TBL_RECIBOS_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRecibosCompra.findAll", query = "SELECT t FROM TblRecibosCompra t"),
    @NamedQuery(name = "TblRecibosCompra.findById", query = "SELECT t FROM TblRecibosCompra t WHERE t.id = :id"),
    @NamedQuery(name = "TblRecibosCompra.findByFechahora", query = "SELECT t FROM TblRecibosCompra t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblRecibosCompra.findByRazonSocial", query = "SELECT t FROM TblRecibosCompra t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "TblRecibosCompra.findByRuc", query = "SELECT t FROM TblRecibosCompra t WHERE t.ruc = :ruc"),
    @NamedQuery(name = "TblRecibosCompra.findByMonto", query = "SELECT t FROM TblRecibosCompra t WHERE t.monto = :monto"),
    @NamedQuery(name = "TblRecibosCompra.findByObservacion", query = "SELECT t FROM TblRecibosCompra t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TblRecibosCompra.findByIdUser", query = "SELECT t FROM TblRecibosCompra t WHERE t.idUser = :idUser")})
public class TblRecibosCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NRO")
    private String nro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechahora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUC")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private Integer monto;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;
    @JoinTable(name = "TBL_RECIBOS_COMPRA_ASIENTOS", joinColumns = {
        @JoinColumn(name = "ID_RECIBO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientos> tblAsientosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblRecibosCompra")
    private List<TblRecibosCompraFacturasCompra> tblRecibosCompraFacturasCompraList;

    public TblRecibosCompra() {
    }

    public TblRecibosCompra(Integer id) {
        this.id = id;
    }

    public TblRecibosCompra(Integer id, String nro, LocalDate fechahora, String razonSocial, String ruc, Integer monto, TblUsers idUser) {
        this.id = id;
        this.nro = nro;
        this.fechahora = fechahora;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.monto = monto;
        this.idUser = idUser;
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

    public LocalDate getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDate fechahora) {
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

    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
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
        if (!(object instanceof TblRecibosCompra)) {
            return false;
        }
        TblRecibosCompra other = (TblRecibosCompra) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblRecibosCompra[ id=" + id + " ]";
    }

    @XmlTransient
    public List<TblRecibosCompraFacturasCompra> getTblRecibosCompraFacturasCompraList() {
        return tblRecibosCompraFacturasCompraList;
    }

    public void setTblRecibosCompraFacturasCompraList(List<TblRecibosCompraFacturasCompra> tblRecibosCompraFacturasCompraList) {
        this.tblRecibosCompraFacturasCompraList = tblRecibosCompraFacturasCompraList;
    }

}
