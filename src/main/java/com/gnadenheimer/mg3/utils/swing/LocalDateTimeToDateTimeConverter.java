/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.utils.swing;

import com.gnadenheimer.mg3.App;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class LocalDateTimeToDateTimeConverter extends Converter {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Override
    public Object convertForward(Object value) {
        try {
            return LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault());
        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            return null;
        }
    }

    @Override
    public Date convertReverse(Object value) {

        try {
            return Date.from(((LocalDateTime) value).atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            return null;
        }

    }
}
