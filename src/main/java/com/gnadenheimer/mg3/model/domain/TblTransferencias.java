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
import javax.persistence.FetchType;
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
@Table(name = "TBL_TRANSFERENCIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTransferencias.findAll", query = "SELECT t FROM TblTransferencias t")
    ,
    @NamedQuery(name = "TblTransferencias.findById", query = "SELECT t FROM TblTransferencias t WHERE t.id = :id")
    ,
    @NamedQuery(name = "TblTransferencias.findByFechahora", query = "SELECT t FROM TblTransferencias t WHERE t.fechahora = :fechahora")
    ,
    @NamedQuery(name = "TblTransferencias.findByConcepto", query = "SELECT t FROM TblTransferencias t WHERE t.concepto = :concepto")
    ,
    @NamedQuery(name = "TblTransferencias.findByMontoAporte", query = "SELECT t FROM TblTransferencias t WHERE t.montoAporte = :montoAporte")
    ,
    @NamedQuery(name = "TblTransferencias.findByMontoDonacion", query = "SELECT t FROM TblTransferencias t WHERE t.montoDonacion = :montoDonacion")
    ,
    @NamedQuery(name = "TblTransferencias.findByCobrado", query = "SELECT t FROM TblTransferencias t WHERE t.cobrado = :cobrado")})
public class TblTransferencias implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "COBRADO")
    private Boolean cobrado;
    @JoinTable(name = "TBL_TRANSFERENCIAS_ASIENTOS_TEMPORALES", joinColumns = {
        @JoinColumn(name = "ID_TRANSFERENCIA", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO_TEMPORAL", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientosTemporales> tblAsientosTemporalesList;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblEntidades idEntidad;
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TblEventos idEvento;
    @JoinColumn(name = "ID_EVENTO_TIPO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblEventoTipos idEventoTipo;
    @JoinColumn(name = "ID_EVENTO_DETALLE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblEventoDetalle idEventoDetalle;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TblUsers idUser;
    @Column(name = "SEQ_PAGO")
    private Integer seqPago;

    public TblTransferencias() {
    }

    public TblTransferencias(Integer id) {
        this.id = id;
    }

    public TblTransferencias(Integer id, LocalDate fechahora, Integer montoAporte, Integer montoDonacion, Boolean cobrado) {
        this.id = id;
        this.fechahora = fechahora;
        this.montoAporte = montoAporte;
        this.montoDonacion = montoDonacion;
        this.cobrado = cobrado;
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

    public Boolean getCobrado() {
        return cobrado;
    }

    public void setCobrado(Boolean cobrado) {
        this.cobrado = cobrado;
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

    public TblEventoDetalle getIdEventoDetalle() {
        return idEventoDetalle;
    }

    public void setIdEventoDetalle(TblEventoDetalle idEventoDetalle) {
        this.idEventoDetalle = idEventoDetalle;
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
        if (!(object instanceof TblTransferencias)) {
            return false;
        }
        TblTransferencias other = (TblTransferencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.model.domain.TblTransferencias[ id=" + id + " ]";
    }

    public Integer getMontoTotal() {
        return getMontoAporte() + getMontoDonacion();
    }

    /**
     * @return the seqPago
     */
    public Integer getSeqPago() {
        return seqPago;
    }

    /**
     * @param seqPago the seqPago to set
     */
    public void setSeqPago(Integer seqPago) {
        this.seqPago = seqPago;
    }

}
