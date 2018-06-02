/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain;

import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;
import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;
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
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "TBL_RECIBOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRecibos.findAll", query = "SELECT t FROM TblRecibos t"),
    @NamedQuery(name = "TblRecibos.findById", query = "SELECT t FROM TblRecibos t WHERE t.id = :id"),
    @NamedQuery(name = "TblRecibos.findByFechahora", query = "SELECT t FROM TblRecibos t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblRecibos.findByConcepto", query = "SELECT t FROM TblRecibos t WHERE t.concepto = :concepto"),
    @NamedQuery(name = "TblRecibos.findByMontoAporte", query = "SELECT t FROM TblRecibos t WHERE t.montoAporte = :montoAporte"),
    @NamedQuery(name = "TblRecibos.findByMontoDonacion", query = "SELECT t FROM TblRecibos t WHERE t.montoDonacion = :montoDonacion")})
public class TblRecibos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechahora;
    @Column(name = "FECHAHORA_COMPROMISO")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechahoraCompromiso;
    @Size(max = 50)
    @Column(name = "CONCEPTO")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_APORTE")
    private Integer montoAporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_DONACION")
    private Integer montoDonacion;
    @JoinTable(name = "TBL_RECIBOS_ASIENTOS_TEMPORALES", joinColumns = {
        @JoinColumn(name = "ID_RECIBO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO_TEMPORAL", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientosTemporales> tblAsientosTemporalesList;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblEntidades idEntidad;
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
    @ManyToOne
    private TblEventos idEvento;
    @JoinColumn(name = "ID_EVENTO_TIPO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblEventoTipos idEventoTipo;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblUsers idUser;

    public TblRecibos() {
    }

    public TblRecibos(Integer id) {
        this.id = id;
    }

    public TblRecibos(Integer id, LocalDate fechahora, Integer montoAporte, Integer montoDonacion) {
        this.id = id;
        this.fechahora = fechahora;
        this.montoAporte = montoAporte;
        this.montoDonacion = montoDonacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDate fechahora) {
        this.fechahora = fechahora;
    }

    public LocalDate getFechahoraCompromiso() {
        return fechahoraCompromiso;
    }

    public void setFechahoraCompromiso(LocalDate fechahoraCompromiso) {
        this.fechahoraCompromiso = fechahoraCompromiso;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getMontoAporte() {
        return montoAporte;
    }

    public void setMontoAporte(Integer montoAporte) {
        this.montoAporte = montoAporte;
    }

    public Integer getMontoDonacion() {
        return montoDonacion;
    }

    public void setMontoDonacion(Integer montoDonacion) {
        this.montoDonacion = montoDonacion;
    }

    @XmlTransient
    public List<TblAsientosTemporales> getTblAsientosTemporalesList() {
        return tblAsientosTemporalesList;
    }

    public void setTblAsientosTemporalesList(List<TblAsientosTemporales> tblAsientosTemporalesList) {
        this.tblAsientosTemporalesList = tblAsientosTemporalesList;
    }

    public TblEntidades getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(TblEntidades idEntidad) {
        this.idEntidad = idEntidad;
    }

    public TblEventos getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(TblEventos idEvento) {
        this.idEvento = idEvento;
    }

    public TblEventoTipos getIdEventoTipo() {
        return idEventoTipo;
    }

    public void setIdEventoTipo(TblEventoTipos idEventoTipo) {
        this.idEventoTipo = idEventoTipo;
    }

    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof TblRecibos)) {
            return false;
        }
        TblRecibos other = (TblRecibos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.model.domain.TblRecibos[ id=" + id + " ]";
    }

}
