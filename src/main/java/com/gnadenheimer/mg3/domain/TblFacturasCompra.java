/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "TBL_FACTURAS_COMPRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblFacturasCompra.findAll", query = "SELECT t FROM TblFacturasCompra t"),
    @NamedQuery(name = "TblFacturasCompra.findById", query = "SELECT t FROM TblFacturasCompra t WHERE t.id = :id"),
    @NamedQuery(name = "TblFacturasCompra.findByNro", query = "SELECT t FROM TblFacturasCompra t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblFacturasCompra.findByNroTimbrado", query = "SELECT t FROM TblFacturasCompra t WHERE t.nroTimbrado = :nroTimbrado"),
    @NamedQuery(name = "TblFacturasCompra.findByVencimientoTimbrado", query = "SELECT t FROM TblFacturasCompra t WHERE t.vencimientoTimbrado = :vencimientoTimbrado"),
    @NamedQuery(name = "TblFacturasCompra.findByCondicionContado", query = "SELECT t FROM TblFacturasCompra t WHERE t.condicionContado = :condicionContado"),
    @NamedQuery(name = "TblFacturasCompra.findByFechaVencimientoCredito", query = "SELECT t FROM TblFacturasCompra t WHERE t.fechaVencimientoCredito = :fechaVencimientoCredito"),
    @NamedQuery(name = "TblFacturasCompra.findByCuotasCredito", query = "SELECT t FROM TblFacturasCompra t WHERE t.cuotasCredito = :cuotasCredito"),
    @NamedQuery(name = "TblFacturasCompra.findByFechahora", query = "SELECT t FROM TblFacturasCompra t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblFacturasCompra.findByRazonSocial", query = "SELECT t FROM TblFacturasCompra t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "TblFacturasCompra.findByRuc", query = "SELECT t FROM TblFacturasCompra t WHERE t.ruc = :ruc"),
    @NamedQuery(name = "TblFacturasCompra.findByMontoExentas", query = "SELECT t FROM TblFacturasCompra t WHERE t.montoExentas = :montoExentas"),
    @NamedQuery(name = "TblFacturasCompra.findByMontoIva5", query = "SELECT t FROM TblFacturasCompra t WHERE t.montoIva5 = :montoIva5"),
    @NamedQuery(name = "TblFacturasCompra.findByMontoIva10", query = "SELECT t FROM TblFacturasCompra t WHERE t.montoIva10 = :montoIva10"),
    @NamedQuery(name = "TblFacturasCompra.findByIva5", query = "SELECT t FROM TblFacturasCompra t WHERE t.iva5 = :iva5"),
    @NamedQuery(name = "TblFacturasCompra.findByIva10", query = "SELECT t FROM TblFacturasCompra t WHERE t.iva10 = :iva10"),
    @NamedQuery(name = "TblFacturasCompra.findByObservacion", query = "SELECT t FROM TblFacturasCompra t WHERE t.observacion = :observacion")})
public class TblFacturasCompra implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblFacturasCompra")
    private List<TblRecibosCompraFacturasCompra> tblRecibosCompraFacturasCompraList;

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
    @Size(min = 1, max = 8)
    @Column(name = "NRO_TIMBRADO")
    private String nroTimbrado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VENCIMIENTO_TIMBRADO")
    
    private LocalDate vencimientoTimbrado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONDICION_CONTADO")
    private Boolean condicionContado;
    @Column(name = "FECHA_VENCIMIENTO_CREDITO")
    
    private LocalDate fechaVencimientoCredito;
    @Column(name = "CUOTAS_CREDITO")
    private Integer cuotasCredito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    
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
    @Column(name = "MONTO_EXENTAS")
    private Integer montoExentas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_IVA5")
    private Integer montoIva5;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_IVA10")
    private Integer montoIva10;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA5")
    private Integer iva5;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IVA10")
    private Integer iva10;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinTable(name = "TBL_FACTURAS_COMPRA_ASIENTOS", joinColumns = {
        @JoinColumn(name = "ID_FACTURA_COMPRA", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientos> tblAsientosList;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;

    public TblFacturasCompra() {
    }

    public TblFacturasCompra(Integer id) {
        this.id = id;
    }

    public TblFacturasCompra(Integer id, String nro, String nroTimbrado, LocalDate vencimientoTimbrado, Boolean condicionContado, LocalDate fechahora, String razonSocial, String ruc, Integer montoExentas, Integer montoIva5, Integer montoIva10, Integer iva5, Integer iva10) {
        this.id = id;
        this.nro = nro;
        this.nroTimbrado = nroTimbrado;
        this.vencimientoTimbrado = vencimientoTimbrado;
        this.condicionContado = condicionContado;
        this.fechahora = fechahora;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.montoExentas = montoExentas;
        this.montoIva5 = montoIva5;
        this.montoIva10 = montoIva10;
        this.iva5 = iva5;
        this.iva10 = iva10;
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

    public String getNroTimbrado() {
        return nroTimbrado;
    }

    public void setNroTimbrado(String nroTimbrado) {
        this.nroTimbrado = nroTimbrado;
    }

    public LocalDate getVencimientoTimbrado() {
        return vencimientoTimbrado;
    }

    public void setVencimientoTimbrado(LocalDate vencimientoTimbrado) {
        this.vencimientoTimbrado = vencimientoTimbrado;
    }

    public Boolean getCondicionContado() {
        return condicionContado;
    }

    public void setCondicionContado(Boolean condicionContado) {
        this.condicionContado = condicionContado;
    }

    public LocalDate getFechaVencimientoCredito() {
        return fechaVencimientoCredito;
    }

    public void setFechaVencimientoCredito(LocalDate fechaVencimientoCredito) {
        this.fechaVencimientoCredito = fechaVencimientoCredito;
    }

    public Integer getCuotasCredito() {
        return cuotasCredito;
    }

    public void setCuotasCredito(Integer cuotasCredito) {
        this.cuotasCredito = cuotasCredito;
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

    public Integer getMontoExentas() {
        return montoExentas;
    }

    public void setMontoExentas(Integer montoExentas) {
        this.montoExentas = montoExentas;
    }

    public Integer getMontoIva5() {
        return montoIva5;
    }

    public void setMontoIva5(Integer montoIva5) {
        this.montoIva5 = montoIva5;
    }

    public Integer getMontoIva10() {
        return montoIva10;
    }

    public void setMontoIva10(Integer montoIva10) {
        this.montoIva10 = montoIva10;
    }

    public Integer getIva5() {
        return iva5;
    }

    public void setIva5(Integer iva5) {
        this.iva5 = iva5;
    }

    public Integer getIva10() {
        return iva10;
    }

    public void setIva10(Integer iva10) {
        this.iva10 = iva10;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
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
        if (!(object instanceof TblFacturasCompra)) {
            return false;
        }
        TblFacturasCompra other = (TblFacturasCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parah.mg.domain.TblFacturasCompra[ id=" + id + " ]";
    }

    @XmlTransient
    public List<TblRecibosCompraFacturasCompra> getTblRecibosCompraFacturasCompraList() {
        return tblRecibosCompraFacturasCompraList;
    }

    public void setTblRecibosCompraFacturasCompraList(List<TblRecibosCompraFacturasCompra> tblRecibosCompraFacturasCompraList) {
        this.tblRecibosCompraFacturasCompraList = tblRecibosCompraFacturasCompraList;
    }

}
