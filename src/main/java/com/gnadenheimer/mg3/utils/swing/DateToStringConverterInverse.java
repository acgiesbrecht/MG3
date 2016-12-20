/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.utils.swing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class DateToStringConverterInverse extends Converter {

    @Override
    public Object convertReverse(Object value) {
        return ((LocalDate) value).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public LocalDate convertForward(Object value) {
        try {
            return (LocalDate) value;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
