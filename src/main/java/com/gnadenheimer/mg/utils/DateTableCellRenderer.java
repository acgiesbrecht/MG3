/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author user
 */
public class DateTableCellRenderer extends NormalTableCellRenderer {

    private final Logger LOGGER = LogManager.getLogger(this);

    @Override
    public void setValue(Object value) {
        try {

            //setText((value == null) ? "" : new SimpleDateFormat("yyyy-MM-dd").format(value));
            setText((value == null) ? "" : ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
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
