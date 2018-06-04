/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain.models;

import com.gnadenheimer.mg3.model.domain.miembros.TblEntidades;
import java.io.Serializable;

public class AportesPendientes implements Serializable {

    private TblEntidades miembro;
    private Long importeSaldoAnterior;
    private Long importeCompromiso;
    private Long importeCompromisoAnual;
    private Long importePagos;
    private String ctaCteIglesia;

    public AportesPendientes() {
    }

    /**
     * @return the compromiso
     */
    public Long getImporteCompromiso() {
        return importeCompromiso;
    }

    /**
     * @param compromiso the compromiso to set
     */
    public void setImporteCompromiso(Long importeCompromiso) {
        this.importeCompromiso = importeCompromiso;
    }

    /**
     * @return the importePagos
     */
    public Long getImportePagos() {
        return importePagos;
    }

    /**
     * @param importePagos the importePagos to set
     */
    public void setImportePagos(Long importePagos) {
        this.importePagos = importePagos;
    }

    /**
     * @return the miembro
     */
    public TblEntidades getMiembro() {
        return miembro;
    }

    /**
     * @param miembro the miembro to set
     */
    public void setMiembro(TblEntidades miembro) {
        this.miembro = miembro;
    }

    /**
     * @return the importeSaldoAnterior
     */
    public Long getImporteSaldoAnterior() {
        return importeSaldoAnterior;
    }

    /**
     * @param importeSaldoAnterior the importeSaldoAnterior to set
     */
    public void setImporteSaldoAnterior(Long importeSaldoAnterior) {
        this.importeSaldoAnterior = importeSaldoAnterior;
    }

    /**
     * @return the importeCompromisoAnual
     */
    public Long getImporteCompromisoAnual() {
        return importeCompromisoAnual;
    }

    /**
     * @param importeCompromisoAnual the importeCompromisoAnual to set
     */
    public void setImporteCompromisoAnual(Long importeCompromisoAnual) {
        this.importeCompromisoAnual = importeCompromisoAnual;
    }

    /**
     * @return the ctaCteIglesia
     */
    public String getCtaCteIglesia() {
        return ctaCteIglesia;
    }

    /**
     * @param ctaCteIglesia the ctaCteIglesia to set
     */
    public void setCtaCteIglesia(String ctaCteIglesia) {
        this.ctaCteIglesia = ctaCteIglesia;
    }

}
