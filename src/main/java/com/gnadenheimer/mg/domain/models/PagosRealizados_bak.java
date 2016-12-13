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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "PAGOS_REALIZADOS")
@XmlRootElement
public class PagosRealizados_bak implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "T_APORTE")
    private Integer tAporte;
    @Column(name = "T_DONACION")
    private Integer tDonacion;
    @Column(name = "R_APORTE")
    private Integer rAporte;
    @Column(name = "R_DONACION")
    private Integer rDonacion;
    @Column(name = "F_APORTE")
    private Integer fAporte;
    @Column(name = "F_DONACION")
    private Integer fDonacion;

    public PagosRealizados_bak() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTAporte() {
        return tAporte;
    }

    public void setTAporte(Integer tAporte) {
        this.tAporte = tAporte;
    }

    public Integer getTDonacion() {
        return tDonacion;
    }

    public void setTDonacion(Integer tDonacion) {
        this.tDonacion = tDonacion;
    }

    public Integer getRAporte() {
        return rAporte;
    }

    public void setRAporte(Integer rAporte) {
        this.rAporte = rAporte;
    }

    public Integer getRDonacion() {
        return rDonacion;
    }

    public void setRDonacion(Integer rDonacion) {
        this.rDonacion = rDonacion;
    }

    public Integer getFAporte() {
        return fAporte;
    }

    public void setFAporte(Integer fAporte) {
        this.fAporte = fAporte;
    }

    public Integer getFDonacion() {
        return fDonacion;
    }

    public void setFDonacion(Integer fDonacion) {
        this.fDonacion = fDonacion;
    }

}
