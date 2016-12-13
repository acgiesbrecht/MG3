/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Industria
 */
public abstract class NumeroALetras {

    private static final Logger LOGGER = LogManager.getLogger(NumeroALetras.class);
    /**
     * Esta clase provee la funcionalidad de convertir un numero representado en
     * digitos a una representacion en letras. Mejorado para leer centavos
     *
     * @author Camilo Nova
     */
    private static final String[] UNIDADES = {"", "UN ", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
        "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
        "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE"};

    private static final String[] DECENAS = {"VENTI", "TREINTA ", "CUARENTA ",
        "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
        "CIEN "};

    private static final String[] CENTENAS = {"CIENTO ", "DOSCIENTOS ",
        "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
        "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS "};

    public static String convertNumberToLetter(String number)
            throws NumberFormatException {
        return convertNumberToLetter(Integer.valueOf(number));
    }

    /**
     * Convierte un numero en representacion numerica a uno en representacion de
     * texto. El numero es valido si esta entre 0 y 999'999.999
     *
     * @param number Numero a convertir
     * @return Numero convertido a texto
     * @throws NumberFormatException Si el numero esta fuera del rango
     */
    public static String convertNumberToLetter(Integer integerNumber)
            throws NumberFormatException {
        try {
            StringBuilder converted = new StringBuilder();

            String patternThreeDecimalPoints = "#.###";

            DecimalFormat format = new DecimalFormat(patternThreeDecimalPoints);
            format.setRoundingMode(RoundingMode.DOWN);

            // formateamos el numero, para ajustarlo a el formato de tres puntos
            // decimales
            String formatedDouble = format.format(integerNumber);
            integerNumber = Integer.valueOf(formatedDouble);

            // Validamos que sea un numero legal
            if (integerNumber > 999999999) {
                throw new NumberFormatException(
                        "El numero es mayor de 999'999.999, "
                        + "no es posible convertirlo");
            }

            if (integerNumber < 0) {
                throw new NumberFormatException("El numero debe ser positivo");
            }

            String splitNumber[] = String.valueOf(integerNumber).replace('.', '#')
                    .split("#");

            // Descompone el trio de millones
            Integer millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
                    8))
                    + String.valueOf(getDigitAt(splitNumber[0], 7))
                    + String.valueOf(getDigitAt(splitNumber[0], 6)));
            if (millon == 1) {
                converted.append("UN MILLON ");
            } else if (millon > 1) {
                converted.append(convertNumber(String.valueOf(millon))
                        + "MILLONES ");
            }

            // Descompone el trio de miles
            Integer miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0],
                    5))
                    + String.valueOf(getDigitAt(splitNumber[0], 4))
                    + String.valueOf(getDigitAt(splitNumber[0], 3)));
            if (miles == 1) {
                converted.append("MIL ");
            } else if (miles > 1) {
                converted.append(convertNumber(String.valueOf(miles)) + "MIL ");
            }

            // Descompone el ultimo trio de unidades
            Integer cientos = Integer.parseInt(String.valueOf(getDigitAt(
                    splitNumber[0], 2))
                    + String.valueOf(getDigitAt(splitNumber[0], 1))
                    + String.valueOf(getDigitAt(splitNumber[0], 0)));
            if (cientos == 1) {
                converted.append("UN");
            }

            if (millon + miles + cientos == 0) {
                converted.append("CERO");
            }
            if (cientos > 1) {
                converted.append(convertNumber(String.valueOf(cientos)));
            }
            converted.append(".");
            return converted.toString();
        } catch (Exception exx) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + exx.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), exx);
            return "error";
        }
    }

    /**
     * Convierte los trios de numeros que componen las unidades, las decenas y
     * las centenas del numero.
     *
     * @param number Numero a convetir en digitos
     * @return Numero convertido en letras
     */
    private static String convertNumber(String number) {
        try {
            if (number.length() > 3) {
                throw new NumberFormatException(
                        "La longitud maxima debe ser 3 digitos");
            }

            // Caso especial con el 100
            if (number.equals("100")) {
                return "CIEN";
            }

            StringBuilder output = new StringBuilder();
            if (getDigitAt(number, 2) != 0) {
                output.append(CENTENAS[getDigitAt(number, 2) - 1]);
            }

            Integer k = Integer.parseInt(String.valueOf(getDigitAt(number, 1))
                    + String.valueOf(getDigitAt(number, 0)));

            if (k <= 20) {
                output.append(UNIDADES[k]);
            } else if (k > 30 && getDigitAt(number, 0) != 0) {
                output.append(DECENAS[getDigitAt(number, 1) - 2] + "Y "
                        + UNIDADES[getDigitAt(number, 0)]);
            } else {
                output.append(DECENAS[getDigitAt(number, 1) - 2]
                        + UNIDADES[getDigitAt(number, 0)]);
            }

            return output.toString();
        } catch (Exception exx) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + exx.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), exx);
            return "error";
        }
    }

    /**
     * Retorna el digito numerico en la posicion indicada de derecha a izquierda
     *
     * @param origin Cadena en la cual se busca el digito
     * @param position Posicion de derecha a izquierda a retornar
     * @return Digito ubicado en la posicion indicada
     */
    private static Integer getDigitAt(String origin, Integer position) {
        if (origin.length() > position && position >= 0) {
            return origin.charAt(origin.length() - position - 1) - 48;
        }
        return 0;
    }

}
