/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_CATEGORIAS_ARTICULOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCategoriasArticulos.findAll", query = "SELECT t FROM TblCategoriasArticulos t"),
    @NamedQuery(name = "TblCategoriasArticulos.findById", query = "SELECT t FROM TblCategoriasArticulos t WHERE t.id = :id"),
    @NamedQuery(name = "TblCategoriasArticulos.findByDescripcion", query = "SELECT t FROM TblCategoriasArticulos t WHERE t.descripcion = :descripcion")})
public class TblCategoriasArticulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaArticulo")
    //private List<TblEventoDetalle> tblEventoDetalleList;

    public TblCategoriasArticulos() {
    }

    public TblCategoriasArticulos(Integer id) {
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

    /*@XmlTransient
    public List<TblEventoDetalle> getTblEventoDetalleList() {
        return tblEventoDetalleList;
    }

    public void setTblEventoDetalleList(List<TblEventoDetalle> tblEventoDetalleList) {
        this.tblEventoDetalleList = tblEventoDetalleList;
    }*/
    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCategoriasArticulos)) {
            return false;
        }
        TblCategoriasArticulos other = (TblCategoriasArticulos) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
