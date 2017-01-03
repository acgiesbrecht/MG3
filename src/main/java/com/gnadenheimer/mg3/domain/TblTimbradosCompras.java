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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_TIMBRADOS_COMPRAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTimbradosCompras.findAll", query = "SELECT t FROM TblTimbradosCompras t"),
    @NamedQuery(name = "TblTimbradosCompras.findByNro", query = "SELECT t FROM TblTimbradosCompras t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblTimbradosCompras.findByFechaVencimiento", query = "SELECT t FROM TblTimbradosCompras t WHERE t.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "TblTimbradosCompras.findByRucSinDv", query = "SELECT t FROM TblTimbradosCompras t WHERE t.rucSinDv = :rucSinDv"),
    @NamedQuery(name = "TblTimbradosCompras.findByIdUser", query = "SELECT t FROM TblTimbradosCompras t WHERE t.idUser = :idUser")})
public class TblTimbradosCompras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRO")
    private String nro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_VENCIMIENTO")
    
    private LocalDate fechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUC_SIN_DV")
    private String rucSinDv;
    @Column(name = "ID_USER")
    private Integer idUser;

    public TblTimbradosCompras() {
    }

    public TblTimbradosCompras(String nro) {
        this.nro = nro;
    }

    public TblTimbradosCompras(String nro, LocalDate fechaVencimiento, String rucSinDv) {
        this.nro = nro;
        this.fechaVencimiento = fechaVencimiento;
        this.rucSinDv = rucSinDv;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getRucSinDv() {
        return rucSinDv;
    }

    public void setRucSinDv(String rucSinDv) {
        this.rucSinDv = rucSinDv;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nro != null ? nro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTimbradosCompras)) {
            return false;
        }
        TblTimbradosCompras other = (TblTimbradosCompras) object;
        if ((this.nro == null && other.nro != null) || (this.nro != null && !this.nro.equals(other.nro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblTimbradosCompras[ nro=" + nro + " ]";
    }

}
