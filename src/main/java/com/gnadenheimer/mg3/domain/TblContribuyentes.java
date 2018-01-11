/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "TBL_CONTRIBUYENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblContribuyentes.findAll", query = "SELECT t FROM TblContribuyentes t"),
    @NamedQuery(name = "TblContribuyentes.findByRucSinDv", query = "SELECT t FROM TblContribuyentes t WHERE t.rucSinDv = :rucSinDv"),
    @NamedQuery(name = "TblContribuyentes.findByDv", query = "SELECT t FROM TblContribuyentes t WHERE t.dv = :dv"),
    @NamedQuery(name = "TblContribuyentes.findByRazonSocial", query = "SELECT t FROM TblContribuyentes t WHERE t.razonSocial = :razonSocial")})
public class TblContribuyentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUC_SIN_DV")
    private String rucSinDv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DV")
    private String dv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    public TblContribuyentes() {
    }

    public TblContribuyentes(String rucSinDv) {
        this.rucSinDv = rucSinDv;
    }

    public TblContribuyentes(String rucSinDv, String dv, String razonSocial) {
        this.rucSinDv = rucSinDv;
        this.dv = dv;
        this.razonSocial = razonSocial;
    }

    public String getRucSinDv() {
        return rucSinDv;
    }

    public void setRucSinDv(String rucSinDv) {
        this.rucSinDv = rucSinDv;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Override
    public int hashCode() {
        Integer hash = 0;
        hash += (rucSinDv != null ? rucSinDv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblContribuyentes)) {
            return false;
        }
        TblContribuyentes other = (TblContribuyentes) object;
        return (this.rucSinDv != null || other.rucSinDv == null) && (this.rucSinDv == null || this.rucSinDv.equals(other.rucSinDv));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblContribuyentes[ rucSinDv=" + rucSinDv + " ]";
    }

}
