/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import com.gnadenheimer.mg3.domain.miembros.TblEntidades;
import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private final IntegerProperty id = new SimpleIntegerProperty();

    private final ObjectProperty<LocalDate> fechahora = new SimpleObjectProperty<>();

    private final ObjectProperty<LocalDate> fechahoraCompromiso = new SimpleObjectProperty<>();

    private final StringProperty concepto = new SimpleStringProperty();

    private final IntegerProperty montoAporte = new SimpleIntegerProperty();

    private final IntegerProperty montoDonacion = new SimpleIntegerProperty();
    
    private final IntegerProperty montoTotal = new SimpleIntegerProperty();

    private final BooleanProperty cobrado = new SimpleBooleanProperty();

    private List<TblAsientosTemporales> tblAsientosTemporalesList;

    private final ObjectProperty<TblEntidades> idEntidad = new SimpleObjectProperty<>();

    private TblEventos idEvento;

    private TblEventoTipos idEventoTipo;

    private TblEventoDetalle idEventoDetalle;

    private TblUsers idUser;

    public TblTransferencias() {
    }

    /*
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
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    public LocalDate getFechahora() {
        return fechahora.get();
    }

    public void setFechahora(LocalDate fechahora) {
        this.fechahora.set(fechahora);
    }

    @Column(name = "FECHAHORA_COMPROMISO")
    public LocalDate getFechahoraCompromiso() {
        return fechahoraCompromiso.get();
    }

    public void setFechahoraCompromiso(LocalDate fechahoraCompromiso) {
        this.fechahoraCompromiso.set(fechahoraCompromiso);
    }

    @Size(max = 50)
    @Column(name = "CONCEPTO")
    public String getConcepto() {
        return concepto.get();
    }

    public void setConcepto(String concepto) {
        this.concepto.set(concepto);
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_APORTE")
    public Integer getMontoAporte() {
        return montoAporte.get();
    }

    public void setMontoAporte(Integer montoAporte) {
        this.montoAporte.set(montoAporte);
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO_DONACION")
    public Integer getMontoDonacion() {
        return montoDonacion.get();
    }

    public void setMontoDonacion(Integer montoDonacion) {
        this.montoDonacion.set(montoDonacion);
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "COBRADO")
    public Boolean getCobrado() {
        return cobrado.get();
    }

    public void setCobrado(Boolean cobrado) {
        this.cobrado.set(cobrado);
    }

    @JoinTable(name = "TBL_TRANSFERENCIAS_ASIENTOS_TEMPORALES", joinColumns = {
        @JoinColumn(name = "ID_TRANSFERENCIA", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO_TEMPORAL", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    @XmlTransient
    public List<TblAsientosTemporales> getTblAsientosTemporalesList() {
        return tblAsientosTemporalesList;
    }

    public void setTblAsientosTemporalesList(List<TblAsientosTemporales> tblAsientosTemporalesList) {
        this.tblAsientosTemporalesList = tblAsientosTemporalesList;
    }

    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public TblEntidades getIdEntidad() {
        return idEntidad.get();
    }

    public void setIdEntidad(TblEntidades idEntidad) {
        this.idEntidad.set(idEntidad);
    }

    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public TblEventos getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(TblEventos idEvento) {
        this.idEvento = idEvento;
    }

    @JoinColumn(name = "ID_EVENTO_TIPO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public TblEventoTipos getIdEventoTipo() {
        return idEventoTipo;
    }

    public void setIdEventoTipo(TblEventoTipos idEventoTipo) {
        this.idEventoTipo = idEventoTipo;
    }

    @JoinColumn(name = "ID_EVENTO_DETALLE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public TblEventoDetalle getIdEventoDetalle() {
        return idEventoDetalle;
    }

    public void setIdEventoDetalle(TblEventoDetalle idEventoDetalle) {
        this.idEventoDetalle = idEventoDetalle;
    }

    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
    }

    /*
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
     */
    @Override
    public String toString() {
        return "com.parah.mg.domain.TblTransferencias[ id=" + id + " ]";
    }

    public Integer getMontoTotal() {
        return getMontoAporte() + getMontoDonacion();
    }
    
    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal.set(montoTotal);
    }

}
