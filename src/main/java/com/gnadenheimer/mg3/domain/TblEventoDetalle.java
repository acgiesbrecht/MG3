/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.domain;

import com.gnadenheimer.mg3.domain.miembros.TblEntidades;
import java.io.Serializable;
import java.util.List;
import java.time.LocalDateTime;
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
@Table(name = "TBL_EVENTO_DETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEventoDetalle.findAll", query = "SELECT t FROM TblEventoDetalle t"),
    @NamedQuery(name = "TblEventoDetalle.findById", query = "SELECT t FROM TblEventoDetalle t WHERE t.id = :id"),
    @NamedQuery(name = "TblEventoDetalle.findByFechahora", query = "SELECT t FROM TblEventoDetalle t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblEventoDetalle.findByObservacion", query = "SELECT t FROM TblEventoDetalle t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TblEventoDetalle.findByMonto", query = "SELECT t FROM TblEventoDetalle t WHERE t.monto = :monto")})
public class TblEventoDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAHORA")
    
    private LocalDateTime fechahora;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MONTO")
    private Integer monto;
    @JoinTable(name = "TBL_EVENTO_DETALLE_ASIENTOS", joinColumns = {
        @JoinColumn(name = "ID_EVENTO_DETALLE", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_ASIENTO", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<TblAsientos> tblAsientosList;
    @JoinColumn(name = "ID_CATEGORIA_ARTICULO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblCategoriasArticulos idCategoriaArticulo;
    @JoinColumn(name = "ID_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblEntidades idEntidad;
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblEventos idEvento;
    @JoinColumn(name = "ID_FORMA_DE_PAGO_PREFERIDA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TblFormasDePago idFormaDePagoPreferida;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    @ManyToOne
    private TblUsers idUser;

    public TblEventoDetalle() {
    }

    public TblEventoDetalle(Integer id) {
        this.id = id;
    }

    public TblEventoDetalle(Integer id, LocalDateTime fechahora, Integer monto) {
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

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    @XmlTransient
    public List<TblAsientos> getTblAsientosList() {
        return tblAsientosList;
    }

    public void setTblAsientosList(List<TblAsientos> tblAsientosList) {
        this.tblAsientosList = tblAsientosList;
    }

    public TblCategoriasArticulos getIdCategoriaArticulo() {
        return idCategoriaArticulo;
    }

    public void setIdCategoriaArticulo(TblCategoriasArticulos idCategoriaArticulo) {
        this.idCategoriaArticulo = idCategoriaArticulo;
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
        Integer hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEventoDetalle)) {
            return false;
        }
        TblEventoDetalle other = (TblEventoDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.parah.mg.domain.eventos.TblEventoDetalle[ id=" + id + " ]";
    }

}
