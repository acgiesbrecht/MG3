/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "RECIBO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibo.findAll", query = "SELECT r FROM Recibo r"),
    @NamedQuery(name = "Recibo.findById", query = "SELECT r FROM Recibo r WHERE r.id = :id"),
    @NamedQuery(name = "Recibo.findByFechahora", query = "SELECT r FROM Recibo r WHERE r.fechahora = :fechahora"),
    @NamedQuery(name = "Recibo.findByNombre", query = "SELECT r FROM Recibo r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Recibo.findByConcepto", query = "SELECT r FROM Recibo r WHERE r.concepto = :concepto"),
    @NamedQuery(name = "Recibo.findByMonto", query = "SELECT r FROM Recibo r WHERE r.monto = :monto")})
public class Recibo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "FECHAHORA")
    
    private LocalDateTime fechahora;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CONCEPTO")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "MONTO")
    private Integer monto;

    public Recibo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }
    
}
