/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AdminLocal
 */
@Entity
@Table(name = "TBL_NOTAS_DE_CREDITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNotasDeCredito.findAll", query = "SELECT t FROM TblNotasDeCredito t"),
    @NamedQuery(name = "TblNotasDeCredito.findById", query = "SELECT t FROM TblNotasDeCredito t WHERE t.id = :id"),
    @NamedQuery(name = "TblNotasDeCredito.findByNro", query = "SELECT t FROM TblNotasDeCredito t WHERE t.nro = :nro"),
    @NamedQuery(name = "TblNotasDeCredito.findByFechahora", query = "SELECT t FROM TblNotasDeCredito t WHERE t.fechahora = :fechahora")})
public class TblNotasDeCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NRO")
    private String nro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    
    private LocalDateTime fechahora;
    @JoinColumn(name = "NRO_FACTURA", referencedColumnName = "NRO")
    @ManyToOne(optional = false)
    private TblFacturas nroFactura;
    @JoinColumn(name = "ID_TIMBRADO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblTimbradosNotasDeCredito idTimbrado;
    @Column(name = "ANULADO")
    private Boolean anulado;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;
    @JoinTable(name = "TBL_NOTAS_DE_CREDITO_ASIENTOS", joinColumns = {
        @JoinColumn(name = "ID_NOTA_DE_CREDITO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientos> tblAsientosList;

    public TblNotasDeCredito() {
    }

    public TblNotasDeCredito(Integer id) {
        this.id = id;
    }

    public TblNotasDeCredito(Integer id, String nro, LocalDateTime fechahora) {
        this.id = id;
        this.nro = nro;
        this.fechahora = fechahora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public TblFacturas getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(TblFacturas nroFactura) {
        this.nroFactura = nroFactura;
    }

    public TblTimbradosNotasDeCredito getIdTimbrado() {
        return idTimbrado;
    }

    public void setIdTimbrado(TblTimbradosNotasDeCredito idTimbrado) {
        this.idTimbrado = idTimbrado;
    }

    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
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
        if (!(object instanceof TblNotasDeCredito)) {
            return false;
        }
        TblNotasDeCredito other = (TblNotasDeCredito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg.domain.TblNotasDeCredito[ id=" + id + " ]";
    }

}
