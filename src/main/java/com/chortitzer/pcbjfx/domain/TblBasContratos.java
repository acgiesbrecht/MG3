/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.pcbjfx.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblBasContratos.findAll", query = "SELECT t FROM TblBasContratos t"),
    @NamedQuery(name = "TblBasContratos.findById", query = "SELECT t FROM TblBasContratos t WHERE t.id = :id"),
    @NamedQuery(name = "TblBasContratos.findByFecha", query = "SELECT t FROM TblBasContratos t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TblBasContratos.findByPrecioGsPorKg", query = "SELECT t FROM TblBasContratos t WHERE t.precioGsPorKg = :precioGsPorKg")})
public class TblBasContratos implements Serializable , Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")   
    private LocalDateTime fecha;
    @Basic(optional = false)
    @Column(name = "precio_gs_por_kg")
    private int precioGsPorKg;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tblempresa idEmpresa;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tblproductos idProducto;

    public TblBasContratos() {
    }

    public TblBasContratos(Integer id) {
        this.id = id;
    }

    public TblBasContratos(Integer id, LocalDateTime fecha, int precioGsPorKg) {
        this.id = id;
        this.fecha = fecha;
        this.precioGsPorKg = precioGsPorKg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getPrecioGsPorKg() {
        return precioGsPorKg;
    }

    public void setPrecioGsPorKg(int precioGsPorKg) {
        this.precioGsPorKg = precioGsPorKg;
    }

    public Tblempresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Tblempresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Tblproductos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Tblproductos idProducto) {
        this.idProducto = idProducto;
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
        if (!(object instanceof TblBasContratos)) {
            return false;
        }
        TblBasContratos other = (TblBasContratos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasContratos[ id=" + id + " ]";
    }

}
