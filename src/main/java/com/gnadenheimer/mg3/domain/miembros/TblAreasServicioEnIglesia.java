/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain.miembros;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_AREAS_SERVICIO_EN_IGLESIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAreasServicioEnIglesia.findAll", query = "SELECT t FROM TblAreasServicioEnIglesia t"),
    @NamedQuery(name = "TblAreasServicioEnIglesia.findById", query = "SELECT t FROM TblAreasServicioEnIglesia t WHERE t.id = :id"),
    @NamedQuery(name = "TblAreasServicioEnIglesia.findByDescripcion", query = "SELECT t FROM TblAreasServicioEnIglesia t WHERE t.descripcion = :descripcion")})
public class TblAreasServicioEnIglesia implements Serializable {

    @OneToMany(mappedBy = "idAreaServicioEnIglesia")
    private List<TblEntidades> tblEntidadesList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public TblAreasServicioEnIglesia() {
    }

    public TblAreasServicioEnIglesia(Integer id) {
        this.id = id;
    }

    public TblAreasServicioEnIglesia(Integer id, String descripcion) {
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

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAreasServicioEnIglesia)) {
            return false;
        }
        TblAreasServicioEnIglesia other = (TblAreasServicioEnIglesia) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblAreasServicioEnIglesia[ id=" + id + " ]";
    }

    @XmlTransient
    public List<TblEntidades> getTblEntidadesList() {
        return tblEntidadesList;
    }

    public void setTblEntidadesList(List<TblEntidades> tblEntidadesList) {
        this.tblEntidadesList = tblEntidadesList;
    }

}
