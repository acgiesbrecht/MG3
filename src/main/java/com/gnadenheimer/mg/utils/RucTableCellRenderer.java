/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import com.gnadenheimer.utils.CalcDV;

/**
 *
 * @author user
 */
public class RucTableCellRenderer extends NormalTableCellRenderer {

    @Override
    public void setValue(Object value) {
        setText((value == null) ? "" : CalcDV.getRucEntero(value.toString()));
    }
}
