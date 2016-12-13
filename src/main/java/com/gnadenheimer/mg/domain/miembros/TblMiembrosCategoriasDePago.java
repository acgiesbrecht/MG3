/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain.miembros;

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
@Table(name = "TBL_MIEMBROS_CATEGORIAS_DE_PAGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblMiembrosCategoriasDePago.findAll", query = "SELECT t FROM TblMiembrosCategoriasDePago t"),
    @NamedQuery(name = "TblMiembrosCategoriasDePago.findById", query = "SELECT t FROM TblMiembrosCategoriasDePago t WHERE t.id = :id"),
    @NamedQuery(name = "TblMiembrosCategoriasDePago.findByDescripcion", query = "SELECT t FROM TblMiembrosCategoriasDePago t WHERE t.descripcion = :descripcion")})
public class TblMiembrosCategoriasDePago implements Serializable {

    @OneToMany(mappedBy = "idMiembrosCategoriaDePago")
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
    @Column(name = "ES_ACTIVACION")
    private Boolean esActivacion;    

    public TblMiembrosCategoriasDePago() {
    }

    public TblMiembrosCategoriasDePago(Integer id) {
        this.id = id;
    }

    public TblMiembrosCategoriasDePago(Integer id, String descripcion) {
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
        if (!(object instanceof TblMiembrosCategoriasDePago)) {
            return false;
        }
        TblMiembrosCategoriasDePago other = (TblMiembrosCategoriasDePago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @XmlTransient
    public List<TblEntidades> getTblEntidadesList() {
        return tblEntidadesList;
    }

    public void setTblEntidadesList(List<TblEntidades> tblEntidadesList) {
        this.tblEntidadesList = tblEntidadesList;
    }

    /**
     * @return the esActivacion
     */
    public Boolean getEsActivacion() {
        return esActivacion;
    }

    /**
     * @param esActivacion the esActivacion to set
     */
    public void setEsActivacion(Boolean esActivacion) {
        this.esActivacion = esActivacion;
    }

}
