/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.model.domain.models;

import com.gnadenheimer.mg3.utils.Utils;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 *
 * @author user
 */
public class MesModel {

    @Inject
    Utils utils;

    private Integer id;
    private String descripcion;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {        
        return utils.getMes(id);
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
}
