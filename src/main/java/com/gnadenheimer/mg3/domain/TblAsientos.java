/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adrian Giesbrecht
 */
@Entity
@Table(name = "TBL_ASIENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAsientos.findAll", query = "SELECT t FROM TblAsientos t")
    ,
    @NamedQuery(name = "TblAsientos.findById", query = "SELECT t FROM TblAsientos t WHERE t.id = :id")
    ,
    @NamedQuery(name = "TblAsientos.findByFechahora", query = "SELECT t FROM TblAsientos t WHERE t.fechahora = :fechahora")
    ,
    @NamedQuery(name = "TblAsientos.findByObservacion", query = "SELECT t FROM TblAsientos t WHERE t.observacion = :observacion")
    ,
    @NamedQuery(name = "TblAsientos.findByMonto", query = "SELECT t FROM TblAsientos t WHERE t.monto = :monto")})
public class TblAsientos implements Serializable {

    private static final long serialVersionUID = 1L;

    private IntegerProperty id = new SimpleIntegerProperty();

    private ObjectProperty<LocalDateTime> fechahora = new SimpleObjectProperty<>();

    private StringProperty observacion = new SimpleStringProperty();

    private IntegerProperty monto = new SimpleIntegerProperty();

    private BooleanProperty asientoManual = new SimpleBooleanProperty();

    private ObjectProperty<List<TblAsientosTemporales>> tblAsientosTemporalesList = new SimpleObjectProperty<>();

    private ObjectProperty<TblCentrosDeCosto> idCentroDeCostoDebe = new SimpleObjectProperty<>();

    private ObjectProperty<TblCentrosDeCosto> idCentroDeCostoHaber = new SimpleObjectProperty<>();

    private ObjectProperty<TblCuentasContables> idCuentaContableHaber = new SimpleObjectProperty<>();

    private ObjectProperty<TblCuentasContables> idCuentaContableDebe = new SimpleObjectProperty<>();

    private ObjectProperty<TblUsers> idUser = new SimpleObjectProperty<>();

    public TblAsientos() {
    }

    /*
    public TblAsientos(Integer id) {
        this.id = id;
    }

    public TblAsientos(Integer id, LocalDateTime fechahora, Integer monto) {
        this.id = id;
        this.fechahora = fechahora;
        this.monto = monto;
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

    public IntegerProperty idProperty() {
        return id;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    public LocalDateTime getFechahora() {
        return fechahora.get();
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora.set(fechahora);
    }

    public ObjectProperty<LocalDateTime> fechahoraProperty() {
        return fechahora;
    }

    @Size(max = 255)
    @Column(name = "OBSERVACION")
    public String getObservacion() {
        return observacion.get();
    }

    public void setObservacion(String observacion) {
        this.observacion.set(observacion);
    }

    public StringProperty observacionProperty() {
        return observacion;
    }

    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    public Integer getMonto() {
        return monto.get();
    }

    public void setMonto(Integer monto) {
        this.monto.set(monto);
    }

    public IntegerProperty montoProperty() {
        return monto;
    }

    @XmlTransient
    @JoinTable(name = "TBL_ASIENTOS_ASIENTOS_TEMPORALES", joinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO_TEMPORAL", referencedColumnName = "ID")})
    @ManyToMany
    public List<TblAsientosTemporales> getTblAsientosTemporalesList() {
        return tblAsientosTemporalesList.get();
    }

    public void setTblAsientosTemporalesList(List<TblAsientosTemporales> tblAsientosTemporalesList) {
        this.tblAsientosTemporalesList.set(tblAsientosTemporalesList);
    }

    public ObjectProperty<List<TblAsientosTemporales>> tblAsientosTemporalesListProperty() {
        return tblAsientosTemporalesList;
    }

    @JoinColumn(name = "ID_CENTRO_DE_COSTO_DEBE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCentrosDeCosto getIdCentroDeCostoDebe() {
        return idCentroDeCostoDebe.get();
    }

    public void setIdCentroDeCostoDebe(TblCentrosDeCosto idCentroDeCostoDebe) {
        this.idCentroDeCostoDebe.set(idCentroDeCostoDebe);
    }

    public ObjectProperty<TblCentrosDeCosto> idCentroDeCostoDebeProperty() {
        return idCentroDeCostoDebe;
    }

    @JoinColumn(name = "ID_CENTRO_DE_COSTO_HABER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCentrosDeCosto getIdCentroDeCostoHaber() {
        return idCentroDeCostoHaber.get();
    }

    public void setIdCentroDeCostoHaber(TblCentrosDeCosto idCentroDeCostoHaber) {
        this.idCentroDeCostoHaber.set(idCentroDeCostoHaber);
    }

    public ObjectProperty<TblCentrosDeCosto> idCentroDeCostoHaberProperty() {
        return idCentroDeCostoHaber;
    }

    @JoinColumn(name = "ID_CUENTA_CONTABLE_HABER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCuentasContables getIdCuentaContableHaber() {
        return idCuentaContableHaber.get();
    }

    public void setIdCuentaContableHaber(TblCuentasContables idCuentaContableHaber) {
        this.idCuentaContableHaber.set(idCuentaContableHaber);
    }

    public ObjectProperty<TblCuentasContables> idCuentaContableHaberProperty() {
        return idCuentaContableHaber;
    }

    @JoinColumn(name = "ID_CUENTA_CONTABLE_DEBE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblCuentasContables getIdCuentaContableDebe() {
        return idCuentaContableDebe.get();
    }

    public void setIdCuentaContableDebe(TblCuentasContables idCuentaContableDebe) {
        this.idCuentaContableDebe.set(idCuentaContableDebe);
    }

    public ObjectProperty<TblCuentasContables> idCuentaContableDebeProperty() {
        return idCuentaContableDebe;
    }

    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    public TblUsers getIdUser() {
        return idUser.get();
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser.set(idUser);
    }

    public ObjectProperty<TblUsers> idUserProperty() {
        return idUser;
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
        if (!(object instanceof TblAsientos)) {
            return false;
        }
        TblAsientos other = (TblAsientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parah.mg.domain.TblAsientos[ id=" + id + " ]";
    }

    /**
     * @return the asientoManual
     */
    @Column(name = "ASIENTO_MANUAL")
    public Boolean getAsientoManual() {
        return asientoManual.get();
    }

    /**
     * @param asientoManual the asientoManual to set
     */
    public void setAsientoManual(Boolean asientoManual) {
        if (asientoManual != null) {
            this.asientoManual.set(asientoManual);
        }
    }

    public BooleanProperty asientoManualProperty() {
        return asientoManual;
    }

}
