/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class DateTimeToStringConverter extends Converter {

    @Override
    public Object convertForward(Object value) {
        return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public LocalDateTime convertReverse(Object value) {
        try {
            return (LocalDateTime) value;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
