/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class DateToStringConverter extends Converter {

    @Override
    public Object convertForward(Object value) {
        return ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public LocalDate convertReverse(Object value) {
        try {
            return LocalDate.parse(value.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
