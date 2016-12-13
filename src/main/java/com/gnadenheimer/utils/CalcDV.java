/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.utils;

/**
 *
 * @author Industria
 */
public class CalcDV {

    public static Integer Pa_Calcular_Dv_11_A(String p_numero, Integer p_basemax) {
        Integer v_total, v_resto, k, v_numero_aux, v_digit;
        String v_numero_al = "";

        for (Integer i = 0; i < p_numero.length(); i++) {
            char c = p_numero.charAt(i);
            if (Character.isDigit(c)) {
                v_numero_al += c;
            } else {
                v_numero_al += (int) c;
            }
        }

        k = 2;
        v_total = 0;

        for (Integer i = v_numero_al.length() - 1; i >= 0; i--) {
            k = k > p_basemax ? 2 : k;
            v_numero_aux = v_numero_al.charAt(i) - 48;
            v_total += v_numero_aux * k++;
        }

        v_resto = v_total % 11;
        v_digit = v_resto > 1 ? 11 - v_resto : 0;

        return v_digit;
    }

    public static Boolean isValidRUC(String ruc) {
        String base = ruc.substring(0, ruc.length() - 2);
        String dv = ruc.substring(ruc.length() - 1, ruc.length());
        return String.valueOf(Pa_Calcular_Dv_11_A(base, 11)).equals(dv);
    }

    public static String getRucEntero(String rucSinDv) {
        return rucSinDv + "-" + String.valueOf(Pa_Calcular_Dv_11_A(rucSinDv, 11));
    }

    public static String getDVfromRUC(String ruc) {
        return ruc.substring(ruc.length() - 1, ruc.length());
    }

    public static String getRUCsinDVfromRUC(String ruc) {
        return ruc.substring(0, ruc.length() - 2);
    }
}
