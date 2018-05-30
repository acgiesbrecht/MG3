/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain.miembros;

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
@Table(name = "TBL_MIEMBROS_RELACIONES_ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblMiembrosRelacionesRoles.findAll", query = "SELECT t FROM TblMiembrosRelacionesRoles t"),
    @NamedQuery(name = "TblMiembrosRelacionesRoles.findById", query = "SELECT t FROM TblMiembrosRelacionesRoles t WHERE t.id = :id"),
    @NamedQuery(name = "TblMiembrosRelacionesRoles.findByDescripcion", query = "SELECT t FROM TblMiembrosRelacionesRoles t WHERE t.descripcion = :descripcion")})
public class TblMiembrosRelacionesRoles implements Serializable {

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
    @OneToMany(mappedBy = "idMiembrosRelacionesRol2")
    private List<TblMiembrosRelaciones> tblMiembrosRelacionesList;
    @OneToMany(mappedBy = "idMiembrosRelacionesRol1")
    private List<TblMiembrosRelaciones> tblMiembrosRelacionesList1;

    public TblMiembrosRelacionesRoles() {
    }

    public TblMiembrosRelacionesRoles(Integer id) {
        this.id = id;
    }

    public TblMiembrosRelacionesRoles(Integer id, String descripcion) {
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

    @XmlTransient
    public List<TblMiembrosRelaciones> getTblMiembrosRelacionesList() {
        return tblMiembrosRelacionesList;
    }

    public void setTblMiembrosRelacionesList(List<TblMiembrosRelaciones> tblMiembrosRelacionesList) {
        this.tblMiembrosRelacionesList = tblMiembrosRelacionesList;
    }

    @XmlTransient
    public List<TblMiembrosRelaciones> getTblMiembrosRelacionesList1() {
        return tblMiembrosRelacionesList1;
    }

    public void setTblMiembrosRelacionesList1(List<TblMiembrosRelaciones> tblMiembrosRelacionesList1) {
        this.tblMiembrosRelacionesList1 = tblMiembrosRelacionesList1;
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
        if (!(object instanceof TblMiembrosRelacionesRoles)) {
            return false;
        }
        TblMiembrosRelacionesRoles other = (TblMiembrosRelacionesRoles) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblMiembrosRelacionesRoles[ id=" + id + " ]";
    }

}
