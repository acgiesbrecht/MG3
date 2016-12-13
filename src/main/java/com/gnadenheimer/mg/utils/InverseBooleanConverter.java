/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class InverseBooleanConverter extends Converter {

    @Override
    public Object convertForward(Object value) {
        return !((Boolean) value);
    }

    @Override
    public Boolean convertReverse(Object value) {
        return !((Boolean) value);
    }

}
