/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_EVENTO_CUOTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEventoCuotas.findAll", query = "SELECT t FROM TblEventoCuotas t"),
    @NamedQuery(name = "TblEventoCuotas.findByIdEvento", query = "SELECT t FROM TblEventoCuotas t WHERE t.idEvento = :idEvento"),
    @NamedQuery(name = "TblEventoCuotas.findByFecha1", query = "SELECT t FROM TblEventoCuotas t WHERE t.fecha1 = :fecha1"),
    @NamedQuery(name = "TblEventoCuotas.findByFecha2", query = "SELECT t FROM TblEventoCuotas t WHERE t.fecha2 = :fecha2"),
    @NamedQuery(name = "TblEventoCuotas.findByFecha3", query = "SELECT t FROM TblEventoCuotas t WHERE t.fecha3 = :fecha3"),
    @NamedQuery(name = "TblEventoCuotas.findByFecha4", query = "SELECT t FROM TblEventoCuotas t WHERE t.fecha4 = :fecha4"),
    @NamedQuery(name = "TblEventoCuotas.findByIdUser", query = "SELECT t FROM TblEventoCuotas t WHERE t.idUser = :idUser")})
public class TblEventoCuotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EVENTO")
    private Integer idEvento;
    @Column(name = "FECHA_1")
    
    private LocalDate fecha1;
    @Column(name = "FECHA_2")
    
    private LocalDate fecha2;
    @Column(name = "FECHA_3")
    
    private LocalDate fecha3;
    @Column(name = "FECHA_4")
    
    private LocalDate fecha4;
    @Column(name = "ID_USER")
    private Integer idUser;
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TblEventos tblEventos;

    public TblEventoCuotas() {
    }

    public TblEventoCuotas(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public LocalDate getFecha1() {
        return fecha1;
    }

    public void setFecha1(LocalDate fecha1) {
        this.fecha1 = fecha1;
    }

    public LocalDate getFecha2() {
        return fecha2;
    }

    public void setFecha2(LocalDate fecha2) {
        this.fecha2 = fecha2;
    }

    public LocalDate getFecha3() {
        return fecha3;
    }

    public void setFecha3(LocalDate fecha3) {
        this.fecha3 = fecha3;
    }

    public LocalDate getFecha4() {
        return fecha4;
    }

    public void setFecha4(LocalDate fecha4) {
        this.fecha4 = fecha4;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public TblEventos getTblEventos() {
        return tblEventos;
    }

    public void setTblEventos(TblEventos tblEventos) {
        this.tblEventos = tblEventos;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEventoCuotas)) {
            return false;
        }
        TblEventoCuotas other = (TblEventoCuotas) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblEventoCuotas[ idEvento=" + idEvento + " ]";
    }

}
