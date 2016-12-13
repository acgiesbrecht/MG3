package com.gnadenheimer.utils;

import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Industria
 */
public class FormatCtaCte {

    private static final Logger LOGGER = LogManager.getLogger(FormatCtaCte.class);

    public static String format(Integer ctaCte) {
        try {
            String sCta = String.valueOf(ctaCte);
            if (sCta.length() == 5 || sCta.length() == 7) {
                sCta = "0" + sCta;
            }

            if (sCta.length() == 8) {
                sCta = sCta.substring(0, 2) + "-" + sCta.substring(2, 5) + "-" + sCta.substring(5, 6) + "/" + sCta.substring(6, 8);
            }
            if (sCta.length() == 6) {
                sCta = sCta.substring(0, 2) + "-" + sCta.substring(2, 5) + "-" + sCta.substring(5, 6);
            }
            return sCta;
        } catch (Exception exx) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + exx.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), exx);
            return "error";
        }
    }

    public static void main(String[] args) {

    }
}
