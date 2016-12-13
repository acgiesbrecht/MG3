/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.pcbjfx.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adriang
 */
@Entity
@Table(name = "tbl_bas_precios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblBasPrecios.findAll", query = "SELECT t FROM TblBasPrecios t"),
    @NamedQuery(name = "TblBasPrecios.findByFechahoraVigencia", query = "SELECT t FROM TblBasPrecios t WHERE t.fechahoraVigencia = :fechahoraVigencia"),
    @NamedQuery(name = "TblBasPrecios.findByValorGsPorKg", query = "SELECT t FROM TblBasPrecios t WHERE t.valorGsPorKg = :valorGsPorKg")})
public class TblBasPrecios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "fechahora_vigencia")    
    private LocalDateTime fechahoraVigencia;
    @Basic(optional = false)
    @Column(name = "valor_gs_por_kg")
    private int valorGsPorKg;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tblproductos idProducto;

    public TblBasPrecios() {
    }

    public TblBasPrecios(LocalDateTime fechahoraVigencia) {
        this.fechahoraVigencia = fechahoraVigencia;
    }

    public TblBasPrecios(LocalDateTime fechahoraVigencia, int valorGsPorKg) {
        this.fechahoraVigencia = fechahoraVigencia;
        this.valorGsPorKg = valorGsPorKg;
    }

    public LocalDateTime getFechahoraVigencia() {
        return fechahoraVigencia;
    }

    public void setFechahoraVigencia(LocalDateTime fechahoraVigencia) {
        this.fechahoraVigencia = fechahoraVigencia;
    }

    public int getValorGsPorKg() {
        return valorGsPorKg;
    }

    public void setValorGsPorKg(int valorGsPorKg) {
        this.valorGsPorKg = valorGsPorKg;
    }

    public Tblproductos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Tblproductos idProducto) {
        this.idProducto = idProducto;
    }
/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechahoraVigencia != null ? fechahoraVigencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblBasPrecios)) {
            return false;
        }
        TblBasPrecios other = (TblBasPrecios) object;
        if ((this.fechahoraVigencia == null && other.fechahoraVigencia != null) || (this.fechahoraVigencia != null && !this.fechahoraVigencia.equals(other.fechahoraVigencia))) {
            return false;
        }
        return true;
    }*/

    @Override
    public String toString() {
        return "com.chortitzer.industria.bascula.domain.TblBasPrecios[ fechahoraVigencia=" + fechahoraVigencia + " ]";
    }
   

}
