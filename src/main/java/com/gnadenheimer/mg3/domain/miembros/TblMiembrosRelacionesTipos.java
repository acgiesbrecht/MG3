/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain.miembros;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_MIEMBROS_RELACIONES_TIPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblMiembrosRelacionesTipos.findAll", query = "SELECT t FROM TblMiembrosRelacionesTipos t"),
    @NamedQuery(name = "TblMiembrosRelacionesTipos.findById", query = "SELECT t FROM TblMiembrosRelacionesTipos t WHERE t.id = :id"),
    @NamedQuery(name = "TblMiembrosRelacionesTipos.findByDescripcion", query = "SELECT t FROM TblMiembrosRelacionesTipos t WHERE t.descripcion = :descripcion")})
public class TblMiembrosRelacionesTipos implements Serializable {

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
    @OneToMany(mappedBy = "idMiembrosRelacionesTipo")
    private List<TblMiembrosRelaciones> tblMiembrosRelacionesList;

    public TblMiembrosRelacionesTipos() {
    }

    public TblMiembrosRelacionesTipos(Integer id) {
        this.id = id;
    }

    public TblMiembrosRelacionesTipos(Integer id, String descripcion) {
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

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblMiembrosRelacionesTipos)) {
            return false;
        }
        TblMiembrosRelacionesTipos other = (TblMiembrosRelacionesTipos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblMiembrosRelacionesTipos[ id=" + id + " ]";
    }

}
