/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain.miembros;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AdminLocal
 */
@Entity
@Table(name = "TBL_APORTES_IMPORTE_MENSUAL_SALDO_ANTERIOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAportesImporteMensualSaldoAnterior.findAll", query = "SELECT t FROM TblAportesImporteMensualSaldoAnterior t")
    , @NamedQuery(name = "TblAportesImporteMensualSaldoAnterior.findById", query = "SELECT t FROM TblAportesImporteMensualSaldoAnterior t WHERE t.id = :id")
    , @NamedQuery(name = "TblAportesImporteMensualSaldoAnterior.findByAno", query = "SELECT t FROM TblAportesImporteMensualSaldoAnterior t WHERE t.ano = :ano")
    , @NamedQuery(name = "TblAportesImporteMensualSaldoAnterior.findByImporteMesnual", query = "SELECT t FROM TblAportesImporteMensualSaldoAnterior t WHERE t.importeMensual = :importeMensual")
    , @NamedQuery(name = "TblAportesImporteMensualSaldoAnterior.findBySaldoAnterior", query = "SELECT t FROM TblAportesImporteMensualSaldoAnterior t WHERE t.saldoAnterior = :saldoAnterior")})
public class TblAportesImporteMensualSaldoAnterior implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANO")
    private int ano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPORTE_MESNUAL")
    private long importeMensual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALDO_ANTERIOR")
    private long saldoAnterior;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblEntidades idEntidad;

    public TblAportesImporteMensualSaldoAnterior() {
    }

    public TblAportesImporteMensualSaldoAnterior(Integer id) {
        this.id = id;
    }

    public TblAportesImporteMensualSaldoAnterior(Integer id, int ano, long importeMensual, long saldoAnterior) {
        this.id = id;
        this.ano = ano;
        this.importeMensual = importeMensual;
        this.saldoAnterior = saldoAnterior;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getImporteMensual() {
        return importeMensual;
    }

    public void setImporteMensual(long importeMensual) {
        this.importeMensual = importeMensual;
    }

    public long getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(long saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public TblEntidades getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(TblEntidades idEntidad) {
        this.idEntidad = idEntidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAportesImporteMensualSaldoAnterior)) {
            return false;
        }
        TblAportesImporteMensualSaldoAnterior other = (TblAportesImporteMensualSaldoAnterior) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.model.domain.miembros.TblAportesImporteMensualSaldoAnterior[ id=" + id + " ]";
    }

}
