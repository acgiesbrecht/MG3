/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import com.gnadenheimer.utils.FormatCtaCte;

/**
 *
 * @author user
 */
public class CtaCteTableCellRenderer extends NormalTableCellRenderer {

    @Override
    public void setValue(Object value) {
        setText((value == null) ? "" : FormatCtaCte.format((Integer) value));
    }
}
