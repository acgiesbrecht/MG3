package com.gnadenheimer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatCtaCte.class);

    public static String format(Integer ctaCte) {
        try {
            String sCta = String.valueOf(ctaCte);
            if (sCta.length() == 6 || sCta.length() == 8) {
                sCta = "0" + sCta;
            }

            if (sCta.length() == 9) {
                sCta = sCta.substring(0, 2) + "-" + sCta.substring(2, 6) + "-" + sCta.substring(6, 7) + "/" + sCta.substring(7, 9);
            }
            if (sCta.length() == 7) {
                sCta = sCta.substring(0, 2) + "-" + sCta.substring(2, 6) + "-" + sCta.substring(6, 7);
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
