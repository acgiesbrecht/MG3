/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain.models;

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
@Table(name = "MIEMBROS_CON_PAGOS_PENDIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MiembrosConPagosPendientes.findAll", query = "SELECT m FROM MiembrosConPagosPendientes m"),
    @NamedQuery(name = "MiembrosConPagosPendientes.findById", query = "SELECT m FROM MiembrosConPagosPendientes m WHERE m.id = :id"),
    @NamedQuery(name = "MiembrosConPagosPendientes.findByNombre", query = "SELECT m FROM MiembrosConPagosPendientes m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MiembrosConPagosPendientes.findByCtacte", query = "SELECT m FROM MiembrosConPagosPendientes m WHERE m.ctacte = :ctacte"),
    @NamedQuery(name = "MiembrosConPagosPendientes.findByDomicilio", query = "SELECT m FROM MiembrosConPagosPendientes m WHERE m.domicilio = :domicilio"),
    @NamedQuery(name = "MiembrosConPagosPendientes.findByBox", query = "SELECT m FROM MiembrosConPagosPendientes m WHERE m.box = :box")})
public class MiembrosConPagosPendientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CTACTE")
    private Integer ctacte;
    @Size(max = 50)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "BOX")
    private Integer box;

    public MiembrosConPagosPendientes() {
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

}
