/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import javax.swing.SwingConstants;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author user
 */
public class CuentaContableCellRenderer extends NormalTableCellRenderer {

    @Override
    public void setValue(Object value) {
        setText((value == null) ? "" : StringUtils.repeat(" ", getSpaces(value.toString())) + value.toString());
        //setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private Integer getSpaces(String s) {
        Integer c = 0;
        Integer i = s.length() - 1;

        while (s.charAt(i) == '0') {
            i--;
            c++;
        }
        return (8 - c) * 2;
    }
    /*
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, Integer row, Integer column) {
     super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
     //System.out.println(String.valueOf(column) + " - " + table.getValueAt(row, column).toString());

     if (table.getValueAt(row, 2).toString().equals("En Proceso...")) {
     setBackground(Color.pink);
     setForeground(Color.black);
     } else {
     setBackground(table.getBackground());
     setForeground(table.getForeground());
     }
     if (isSelected) {
     setBackground(table.getSelectionBackground());
     setForeground(table.getSelectionForeground());
     }
     return this;
     }*/
}
