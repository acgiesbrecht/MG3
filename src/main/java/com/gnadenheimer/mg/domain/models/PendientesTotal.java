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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "pendientes_total")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PendientesTotal.findAll", query = "SELECT p FROM PendientesTotal p"),
    @NamedQuery(name = "PendientesTotal.findByNombre", query = "SELECT p FROM PendientesTotal p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PendientesTotal.findByCtacte", query = "SELECT p FROM PendientesTotal p WHERE p.ctacte = :ctacte"),
    @NamedQuery(name = "PendientesTotal.findByComprasTotal", query = "SELECT p FROM PendientesTotal p WHERE p.comprasTotal = :comprasTotal"),
    @NamedQuery(name = "PendientesTotal.findByPagosTotal", query = "SELECT p FROM PendientesTotal p WHERE p.pagosTotal = :pagosTotal"),
    @NamedQuery(name = "PendientesTotal.findBySaldo", query = "SELECT p FROM PendientesTotal p WHERE p.saldo = :saldo")})
public class PendientesTotal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CTACTE")
    private Integer ctacte;
    @Column(name = "COMPRAS_TOTAL")
    private Integer comprasTotal;
    @Column(name = "PAGOS_TOTAL")
    private Integer pagosTotal;
    @Column(name = "SALDO")
    private Integer saldo;

    public PendientesTotal() {
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

    public Integer getComprasTotal() {
        return comprasTotal;
    }

    public void setComprasTotal(Integer comprasTotal) {
        this.comprasTotal = comprasTotal;
    }

    public Integer getPagosTotal() {
        return pagosTotal;
    }

    public void setPagosTotal(Integer pagosTotal) {
        this.pagosTotal = pagosTotal;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

}
