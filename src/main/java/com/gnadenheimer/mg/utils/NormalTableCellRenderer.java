/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author user
 */
public class NormalTableCellRenderer extends DefaultTableCellRenderer {

    private boolean enProceso = false;
    private static final Logger LOGGER = LogManager.getLogger(NormalTableCellRenderer.class);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        try {
            if (this.isEnProceso()) {
                if (table.getColumnCount() > 2) {
                    if (table.getValueAt(row, 2) != null) {
                        if (table.getValueAt(row, 1) != null) {
                            if (table.getValueAt(row, 1).toString().equals("En Proceso...")) {
                                setBackground(Color.pink);
                                setForeground(Color.black);
                            } else {
                                setBackground(table.getBackground());
                                setForeground(table.getForeground());
                            }
                        }
                    }
                }

                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
        return this;
    }

    /**
     * @return the enProceso
     */
    public boolean isEnProceso() {
        return enProceso;
    }

    /**
     * @param enProceso the enProceso to set
     */
    public void setEnProceso(boolean enProceso) {
        this.enProceso = enProceso;
    }
}
