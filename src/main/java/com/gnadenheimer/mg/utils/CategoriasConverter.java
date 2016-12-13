/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import com.gnadenheimer.mg.domain.TblCategoriasArticulos;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class CategoriasConverter extends Converter {

    private static final Logger LOGGER = LogManager.getLogger(CategoriasConverter.class);
    private EntityManager entityManager;

    @Override
    public Object convertForward(Object value) {
        return ((TblCategoriasArticulos) value).getDescripcion();
    }

    @Override
    public TblCategoriasArticulos convertReverse(Object value) {
        TblCategoriasArticulos res = new TblCategoriasArticulos();
        try {
            System.out.println(value);
            //res = entityManager.find(TblRematesCategorias.class, value);
            if (value.toString().length() > 0) {
                res = (TblCategoriasArticulos) entityManager.createQuery("SELECT t FROM TblCategoriasArticulos t WHERE t.descripcion = '" + value + "'").getSingleResult();
            }
        } catch (NoResultException ex) {
            res = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
        return res;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param entityManager the em to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
