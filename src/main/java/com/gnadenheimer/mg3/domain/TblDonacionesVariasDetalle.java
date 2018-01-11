/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import com.gnadenheimer.mg3.domain.miembros.TblEntidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author AdminLocal
 */
@Entity
@Table(name = "TBL_DONACIONES_VARIAS_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDonacionesVariasDetalle.findAll", query = "SELECT t FROM TblDonacionesVariasDetalle t"),
    @NamedQuery(name = "TblDonacionesVariasDetalle.findById", query = "SELECT t FROM TblDonacionesVariasDetalle t WHERE t.id = :id"),
    @NamedQuery(name = "TblDonacionesVariasDetalle.findByFechahora", query = "SELECT t FROM TblDonacionesVariasDetalle t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblDonacionesVariasDetalle.findByObservacion", query = "SELECT t FROM TblDonacionesVariasDetalle t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TblDonacionesVariasDetalle.findByMonto", query = "SELECT t FROM TblDonacionesVariasDetalle t WHERE t.monto = :monto")})
public class TblDonacionesVariasDetalle implements Serializable {

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
    private LocalDateTime fechahora;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private int monto;
    @JoinColumn(name = "ID_CENTRO_DE_COSTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCentrosDeCosto idCentroDeCosto;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblEntidades idEntidad;
    @JoinColumn(name = "ID_FORMA_DE_PAGO_PREFERIDA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblFormasDePago idFormaDePagoPreferida;
    @JoinTable(name = "TBL_DONACIONES_VARIAS_DETALLE_ASIENTOS", joinColumns = {
        @JoinColumn(name = "ID_DONACIONES_VARIAS_DETALLE", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientos> tblAsientosList;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne
    private TblUsers idUser;

    public TblDonacionesVariasDetalle() {
    }

    public TblDonacionesVariasDetalle(Integer id) {
        this.id = id;
    }

    public TblDonacionesVariasDetalle(Integer id, LocalDateTime fechahora, int monto) {
        this.id = id;
        this.fechahora = fechahora;
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public TblCentrosDeCosto getIdCentroDeCosto() {
        return idCentroDeCosto;
    }

    public void setIdCentroDeCosto(TblCentrosDeCosto idCentroDeCosto) {
        this.idCentroDeCosto = idCentroDeCosto;
    }

    public TblEntidades getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(TblEntidades idEntidad) {
        this.idEntidad = idEntidad;
    }

    public TblFormasDePago getIdFormaDePagoPreferida() {
        return idFormaDePagoPreferida;
    }

    public void setIdFormaDePagoPreferida(TblFormasDePago idFormaDePagoPreferida) {
        this.idFormaDePagoPreferida = idFormaDePagoPreferida;
    }

    public TblUsers getIdUser() {
        return idUser;
    }

    public void setIdUser(TblUsers idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof TblDonacionesVariasDetalle)) {
            return false;
        }
        TblDonacionesVariasDetalle other = (TblDonacionesVariasDetalle) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.gnadenheimer.mg3.domain.TblDonacionesVariasDetalle[ id=" + id + " ]";
    }

    /**
     * @return the tblAsientosList
     */
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    /**
     * @param tblAsientosList the tblAsientosList to set
     */
    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
    }

}
