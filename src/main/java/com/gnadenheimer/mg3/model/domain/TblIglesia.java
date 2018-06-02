/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_IGLESIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblIglesia.findAll", query = "SELECT t FROM TblIglesia t"),
    @NamedQuery(name = "TblIglesia.findById", query = "SELECT t FROM TblIglesia t WHERE t.id = :id"),
    @NamedQuery(name = "TblIglesia.findByNombre", query = "SELECT t FROM TblIglesia t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblIglesia.findByRucSinDv", query = "SELECT t FROM TblIglesia t WHERE t.rucSinDv = :rucSinDv"),
    @NamedQuery(name = "TblIglesia.findByCtacte", query = "SELECT t FROM TblIglesia t WHERE t.ctacte = :ctacte"),
    @NamedQuery(name = "TblIglesia.findByDomicilio", query = "SELECT t FROM TblIglesia t WHERE t.domicilio = :domicilio"),
    @NamedQuery(name = "TblIglesia.findByBox", query = "SELECT t FROM TblIglesia t WHERE t.box = :box")})
public class TblIglesia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 20)
    @Column(name = "RUC_SIN_DV")
    private String rucSinDv;
    @Column(name = "CTACTE")
    private Integer ctacte;
    @Size(max = 50)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "BOX")
    private Integer box;

    public TblIglesia() {
    }

    public TblIglesia(Integer id) {
        this.id = id;
    }

    public TblIglesia(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRucSinDv() {
        return rucSinDv;
    }

    public void setRucSinDv(String rucSinDv) {
        this.rucSinDv = rucSinDv;
    }

    public Integer getCtacte() {
        return ctacte;
    }

    public void setCtacte(Integer ctacte) {
        this.ctacte = ctacte;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getBox() {
        return box;
    }

    public void setBox(Integer box) {
        this.box = box;
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
        if (!(object instanceof TblIglesia)) {
            return false;
        }
        TblIglesia other = (TblIglesia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.model.domain.TblIglesia[ id=" + id + " ]";
    }

}
