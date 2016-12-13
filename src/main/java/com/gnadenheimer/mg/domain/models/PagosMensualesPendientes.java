/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.domain.models;

import com.gnadenheimer.mg.domain.miembros.TblEntidades;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adriang
 */
@Entity
public class PagosMensualesPendientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private TblEntidades entidad;

    private Integer mes;

    private Integer ano;

    private Integer montoAporte;

    private Integer montoDonacion;

    private Boolean cobrado;

    public PagosMensualesPendientes() {
    }

    public PagosMensualesPendientes(TblEntidades entidad, Integer mes, Integer ano, Integer montoAporte, Integer montoDonacion) {
        this.entidad = entidad;
        this.mes = mes;
        this.ano = ano;
        this.montoAporte = montoAporte;
        this.montoDonacion = montoDonacion;
    }

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
     * @return the mes
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(Integer mes) {
        this.mes = mes;
    }

    /**
     * @return the ano
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Integer ano) {
        this.ano = ano;
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
     * @return the cobrado
     */
    public Boolean getCobrado() {
        return cobrado;
    }

    public Integer getMontoTotal() {
        return montoAporte + montoDonacion;
    }

    /**
     * @param cobrado the cobrado to set
     */
    public void setCobrado(Boolean cobrado) {
        this.cobrado = cobrado;
    }

}
