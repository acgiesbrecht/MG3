/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class LocalDateToDateConverter extends Converter {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Override
    public Object convertForward(Object value) {
        try {
            return LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault()).toLocalDate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            return null;
        }
    }

    @Override
    public Date convertReverse(Object value) {

        try {
            return Date.from(((LocalDateTime) value).atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            return null;
        }

    }
}
