/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain.models;

import com.gnadenheimer.mg.domain.TblAsientosTemporales;
import com.gnadenheimer.mg.domain.TblEventoTipos;
import com.gnadenheimer.mg.domain.miembros.TblEntidades;
import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adriang
 */
@Entity
public class PagosRealizados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private TblEntidades entidad;
    private LocalDate fecha;
    private List<TblAsientosTemporales> asientosTemporalesList;
    private TblEventoTipos eventoTipo;
    private Integer montoAporte;
    private Integer montoDonacion;
    private Boolean facturado = false;

    /**
     * @return the entidad
     */
    public TblEntidades getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(TblEntidades entidad) {
        this.entidad = entidad;
    }

    /**
     * @return the asientosTemporalesList
     */
    public List<TblAsientosTemporales> getAsientosTemporalesList() {
        return asientosTemporalesList;
    }

    /**
     * @param asientosTemporalesList the asientosTemporalesList to set
     */
    public void setAsientosTemporalesList(List<TblAsientosTemporales> asientosTemporalesList) {
        this.asientosTemporalesList = asientosTemporalesList;
    }

    /**
     * @return the montoAporte
     */
    public Integer getMontoAporte() {
        return montoAporte;
    }

    /**
     * @param montoAporte the montoAporte to set
     */
    public void setMontoAporte(Integer montoAporte) {
        this.montoAporte = montoAporte;
    }

    /**
     * @return the montoDonacion
     */
    public Integer getMontoDonacion() {
        return montoDonacion;
    }

    /**
     * @param montoDonacion the montoDonacion to set
     */
    public void setMontoDonacion(Integer montoDonacion) {
        this.montoDonacion = montoDonacion;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the facturado
     */
    public Boolean getFacturado() {
        return facturado;
    }

    /**
     * @param facturado the facturado to set
     */
    public void setFacturado(Boolean facturado) {
        this.facturado = facturado;
    }

    /**
     * @return the eventoTipo
     */
    public TblEventoTipos getEventoTipo() {
        return eventoTipo;
    }

    /**
     * @param eventoTipo the eventoTipo to set
     */
    public void setEventoTipo(TblEventoTipos eventoTipo) {
        this.eventoTipo = eventoTipo;
    }

}
