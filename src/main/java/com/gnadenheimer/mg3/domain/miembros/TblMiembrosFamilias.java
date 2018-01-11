/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain.miembros;

import javax.persistence.*;
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
@Table(name = "TBL_MIEMBROS_FAMILIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblMiembrosFamilias.findAll", query = "SELECT t FROM TblMiembrosFamilias t"),
    @NamedQuery(name = "TblMiembrosFamilias.findById", query = "SELECT t FROM TblMiembrosFamilias t WHERE t.id = :id"),
    @NamedQuery(name = "TblMiembrosFamilias.findByDescripcion", query = "SELECT t FROM TblMiembrosFamilias t WHERE t.descripcion = :descripcion")})
public class TblMiembrosFamilias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Lob
    @Column(name = "FOTO")
    private Serializable foto;
    @OneToMany(mappedBy = "idMiembrosFamilia")
    private List<TblMiembrosRelaciones> tblMiembrosRelacionesList;

    public TblMiembrosFamilias() {
    }

    public TblMiembrosFamilias(Integer id) {
        this.id = id;
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

    public Serializable getFoto() {
        return foto;
    }

    public void setFoto(Serializable foto) {
        this.foto = foto;
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
        if (!(object instanceof TblMiembrosFamilias)) {
            return false;
        }
        TblMiembrosFamilias other = (TblMiembrosFamilias) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblMiembrosFamilias[ id=" + id + " ]";
    }

}
