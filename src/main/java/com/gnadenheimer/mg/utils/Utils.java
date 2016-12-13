/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import com.gnadenheimer.mg.domain.TblAutofacturas;
import com.gnadenheimer.mg.domain.TblEventoCuotas;
import com.gnadenheimer.mg.domain.TblFacturas;
import com.gnadenheimer.mg.domain.TblNotasDeCredito;
import com.gnadenheimer.mg.domain.miembros.TblEntidades;
import com.gnadenheimer.mg.domain.models.CuotaModel;
import java.awt.Component;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.Strings;

/**
 *
 * @author user
 */
public class Utils extends Component {

    private static final Utils UTILS = new Utils();
    private static final Logger LOGGER = LogManager.getLogger(Utils.class);
    CurrentUser currentUser = CurrentUser.getInstance();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Utils() {
    }

    /* Static 'instance' method */
    public static Utils getInstance() {
        return UTILS;
    }

    public List<CuotaModel> getCuotas(TblEventoCuotas eventoCuotas, Integer monto) {
        List<LocalDate> fechas = getCuotasFechas(eventoCuotas);
        List<CuotaModel> listCuotas = new ArrayList<>();
        float divi = monto * 1.0F / fechas.size();
        Integer montoCuota = Math.round(divi);
        for (LocalDate fecha : fechas) {
            CuotaModel cuota = new CuotaModel();
            cuota.setFecha(fecha);
            cuota.setMonto(montoCuota);
            listCuotas.add(cuota);
        }
        if (montoCuota * fechas.size() > monto) {
            CuotaModel cuota = new CuotaModel();
            cuota.setFecha(listCuotas.get(listCuotas.size() - 1).getFecha());
            cuota.setMonto(montoCuota - 1);
            listCuotas.set(listCuotas.size() - 1, cuota);
        } else if (montoCuota * fechas.size() < monto) {
            CuotaModel cuota = new CuotaModel();
            cuota.setFecha(listCuotas.get(listCuotas.size() - 1).getFecha());
            cuota.setMonto(montoCuota + 1);
            listCuotas.set(listCuotas.size() - 1, cuota);
        }

        return listCuotas;
    }

    public List<LocalDate> getCuotasFechas(TblEventoCuotas cuotas) {
        List<LocalDate> cuotasList = new ArrayList<>();
        if (cuotas.getFecha1() != null) {
            cuotasList.add(cuotas.getFecha1());
        }
        if (cuotas.getFecha2() != null) {
            cuotasList.add(cuotas.getFecha2());
        }
        if (cuotas.getFecha3() != null) {
            cuotasList.add(cuotas.getFecha3());
        }
        if (cuotas.getFecha4() != null) {
            cuotasList.add(cuotas.getFecha4());
        }
        return cuotasList;
    }

    public Map<String, String> getPersistenceMap() {
        try {
            Properties p = System.getProperties();
            p.setProperty("derby.system.home", Preferences.userRoot().node("MG").get("Datadir", (new JFileChooser()).getFileSystemView().getDefaultDirectory().toString() + "\\javadb"));
            p.setProperty("derby.drda.host", "0.0.0.0");
            p.setProperty("derby.language.sequence.preallocator", "1");
            String databaseIP;
            databaseIP = Preferences.userRoot().node("MG").get("DatabaseIP", "127.0.0.1");
            Map<String, String> persistenceMap = new HashMap<>();
            persistenceMap.put("javax.persistence.jdbc.url", "jdbc:derby://" + databaseIP + ":1527/mgdb;create=true");
            persistenceMap.put("javax.persistence.jdbc.user", "mg");
            persistenceMap.put("javax.persistence.jdbc.password", "123456");
            persistenceMap.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.ClientDriver");
            persistenceMap.put("backUpDir", Preferences.userRoot().node("MG").get("Datadir", (new JFileChooser()).getFileSystemView().getDefaultDirectory().toString() + "\\javadb") + "\\autoBackUp");
            return persistenceMap;
        } catch (Exception exx) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + exx.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), exx);
            return null;
        }
    }

    public void printFactura(TblFacturas factura) {
        try {

            Map parameters = new HashMap();
            parameters.put("factura_id", factura.getNro());
            parameters.put("fechahora", java.sql.Date.valueOf(factura.getFechahora().toLocalDate()));
            parameters.put("razon_social", factura.getRazonSocial());
            parameters.put("ruc", factura.getRuc());
            parameters.put("domicilio", factura.getDomicilio());
            parameters.put("box", factura.getCasillaDeCorreo() == null ? "" : factura.getCasillaDeCorreo().toString());
            parameters.put("usuario", factura.getIdUser().getNombrecompleto());
            parameters.put("importe_aporte", factura.getImporteAporte());
            parameters.put("importe_donacion", factura.getImporteDonacion());

            parameters.put("logo", getClass().getResourceAsStream("/reports/cclogo200.png"));
            parameters.put("logo2", getClass().getResourceAsStream("/reports/cclogo200.png"));
            parameters.put("logo3", getClass().getResourceAsStream("/reports/cclogo200.png"));

            parameters.put(JRParameter.REPORT_LOCALE, Locale.forLanguageTag("es"));

            //JOptionPane.showMessageDialog(null, getClass().getResource("/reports/cclogo200.png").getPath());
            String reportFactura = Preferences.userRoot().node("MG").get("formatoFactura", "Preimpreso sin rejilla");
            if (reportFactura.equals("Preimpreso sin rejilla")) {
                reportFactura = "factura_con_rejilla";
            } else if (reportFactura.equals("Preimpreso con rejilla")) {
                reportFactura = "factura";
            } else if (reportFactura.equals("Preimpreso con rejilla modelo especial Bethel Theodor")) {
                reportFactura = "factura_bethel";
            }
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/" + reportFactura + ".jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            jasperPrint.setLeftMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaLeftMargin", "0")));
            jasperPrint.setTopMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaTopMargin", "0")));

            //JasperViewer jReportsViewer = new JasperViewer(jasperPrint, false);
            //jReportsViewer.setVisible(true);
            jasperPrint.setLeftMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaLeftMargin", "0")));
            jasperPrint.setTopMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaTopMargin", "0")));
            JasperPrintManager.printReport(jasperPrint, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    public void printAutofactura(TblAutofacturas factura) {
        try {

            Map parameters = new HashMap();
            parameters.put("factura_nro", factura.getNro());
            parameters.put("fechahora", java.sql.Date.valueOf(factura.getFechahora().toLocalDate()));
            parameters.put("nombre", factura.getNombre());
            parameters.put("ci", factura.getCi());
            parameters.put("domicilio", factura.getDomicilio());
            parameters.put("direccionDeTransaccion", factura.getDireccionDeTransaccion());
            parameters.put("cantidad", factura.getCantidad());
            parameters.put("concepto", factura.getConcepto());
            parameters.put("precioUnitario", factura.getPrecioUnitario());
            parameters.put("monto", factura.getMonto());
            parameters.put("usuario", factura.getIdUser().getNombrecompleto());

            String reportFactura = Preferences.userRoot().node("MG").get("formateFactura", "Preimpreso sin rejilla").equals("Preimpreso sin rejilla")
                    ? "autofactura_con_rejilla"
                    : "autofactura";
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/" + reportFactura + ".jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            jasperPrint.setLeftMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaLeftMargin", "0")));
            jasperPrint.setTopMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaTopMargin", "0")));

            //JasperViewer jReportsViewer = new JasperViewer(jasperPrint, false);
            //jReportsViewer.setVisible(true);
            jasperPrint.setLeftMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaLeftMargin", "0")));
            jasperPrint.setTopMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaTopMargin", "0")));
            JasperPrintManager.printReport(jasperPrint, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    public void printNotaDeCredito(TblNotasDeCredito notaDeCredito) {
        try {

            Map parameters = new HashMap();
            parameters.put("nota_de_credito_nro", notaDeCredito.getNro());
            parameters.put("factura_nro", notaDeCredito.getNroFactura().getNro());
            parameters.put("fechahora", java.sql.Date.valueOf(notaDeCredito.getFechahora().toLocalDate()));
            parameters.put("razon_social", notaDeCredito.getNroFactura().getRazonSocial());
            parameters.put("ruc", notaDeCredito.getNroFactura().getRuc());
            parameters.put("domicilio", notaDeCredito.getNroFactura().getDomicilio());
            parameters.put("box", notaDeCredito.getNroFactura().getCasillaDeCorreo() == null ? "" : notaDeCredito.getNroFactura().getCasillaDeCorreo().toString());
            parameters.put("importe_aporte", notaDeCredito.getNroFactura().getImporteAporte());
            parameters.put("importe_donacion", notaDeCredito.getNroFactura().getImporteDonacion());
            parameters.put("usuario", notaDeCredito.getIdUser().getNombrecompleto());

            /*String reportFactura = Preferences.userRoot().node("MG").get("formateFactura", "Preimpreso sin rejilla").equals("Preimpreso sin rejilla")
                    ? "nota_de_credito_con_rejilla"
                    : "nota_de_credito";*/
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/nota_de_credito_con_rejilla.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            //JasperViewer jReportsViewer = new JasperViewer(jasperPrint, false);
            //jReportsViewer.setVisible(true);
            jasperPrint.setLeftMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaLeftMargin", "0")));
            jasperPrint.setTopMargin(Integer.getInteger(Preferences.userRoot().node("MG").get("facturaTopMargin", "0")));
            JasperPrintManager.printReport(jasperPrint, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    public Integer getIndexOfModel(ListModel model, Object value) {
        if (value == null) {
            return -1;
        }
        if (model instanceof DefaultListModel) {
            return ((DefaultListModel) model).indexOf(value);
        }
        for (Integer i = 0; i < model.getSize(); i++) {
            if (value.equals(model.getElementAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public String getNombreCompleto(TblEntidades ent) {
        return ent.getNombres() + " " + ent.getApellidos();
    }

    public Boolean executeUpdateSQL(String filename, Boolean hasBackedUp) {
        if (!hasBackedUp) {
            Integer reply = JOptionPane.showConfirmDialog(null, "Se encuentró una actualización de la base de datos. Se procederá a hacer un BackUp de sus base de datos existente. Desea proceder?", "Seguridad", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                hasBackedUp = exectueBackUp(getPersistenceMap().get("backUpDir"));
                executeSQL(filename);
            }
        } else {
            executeSQL(filename);
        }
        return hasBackedUp;
    }

    public void executeSQL(String filename) {
        try {
            Map<String, String> persistenceMap = Utils.getInstance().getPersistenceMap();
            Boolean error = false;
            Connection conn = DriverManager.getConnection(persistenceMap.get("javax.persistence.jdbc.url"), persistenceMap.get("javax.persistence.jdbc.user"), persistenceMap.get("javax.persistence.jdbc.password"));
            //JOptionPane.showMessageDialog(null, filename);
            String ss = IOUtils.toString(getClass().getResourceAsStream(filename));
            List<String> sql = Arrays.asList(ss.split(";"));
            Statement stmt = conn.createStatement();
            for (String s : sql) {
                try {
                    stmt.executeUpdate(s);
                } catch (SQLException exx) {
                    error = true;
                    JOptionPane.showMessageDialog(null, exx.getMessage() + String.valueOf(exx.getErrorCode()));
                    LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), exx);
                }
            }

            if (!filename.equals("/sql/javadb.sql")) {
                try {
                    stmt.executeUpdate("INSERT INTO TBL_DATABASE_UPDATES (ID) VALUES('" + filename + "')");
                } catch (SQLException exx) {
                    error = true;
                    JOptionPane.showMessageDialog(null, exx.getMessage() + String.valueOf(exx.getErrorCode()));
                    LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), exx);
                }
            }

            if (error) {
                JOptionPane.showMessageDialog(null, "Error. Por favor pruebe otra vez.");
            } else {
                JOptionPane.showMessageDialog(null, "Base de Datos actualizada con exito!");

            }

            stmt.close();
            conn.close();
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
        }
    }

    public Boolean exectueBackUp(String backupDirectory) {
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            //String backupfile = backupDirectory + "\\BackUp_" + sdf.format(new LocalDateTime());
            String backupfile = backupDirectory + "\\BackUp_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
            Connection conn = DriverManager.getConnection(getPersistenceMap().get("javax.persistence.jdbc.url"), getPersistenceMap().get("javax.persistence.jdbc.user"), getPersistenceMap().get("javax.persistence.jdbc.password"));

            try (CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)")) {
                cs.setString(1, backupfile);
                cs.execute();
                cs.close();
            } catch (Exception ex) {
                LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
                JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "BackUp guardado con exito en: " + backupfile);
            return true;
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            return false;
        }
    }

    public void showReport(String reportFile, String subReportFile, Map parameters, Boolean landscape) {
        try {
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/" + subReportFile + ".jrxml"));
            parameters.put("subreportObject", report);
            showReport(reportFile, parameters, landscape);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }

    public void showReport(String reportFile, String subReportFile, String subSubReportFile, Map parameters, Boolean landscape) {
        try {
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/" + subSubReportFile + ".jrxml"));
            parameters.put("subSubreportObject", report);
            showReport(reportFile, subReportFile, parameters, landscape);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }

    public void showReport(String reportFile, Map parameters, Boolean landscape) {
        try {
            String url = getPersistenceMap().get("javax.persistence.jdbc.url");
            String user = getPersistenceMap().get("javax.persistence.jdbc.user");
            String pass = getPersistenceMap().get("javax.persistence.jdbc.password");
            parameters.put("user", currentUser.getUser().getNombrecompleto());

            if (landscape) {
                JasperReport reportHeader = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/header_landscape.jrxml"));
                parameters.put("subreportHeader", reportHeader);
            } else {
                JasperReport reportHeader = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/header.jrxml"));
                parameters.put("subreportHeader", reportHeader);
            }

            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/" + reportFile + ".jrxml"));
            report.setWhenNoDataType(WhenNoDataTypeEnum.NO_DATA_SECTION);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, DriverManager.getConnection(url, user, pass));
            JasperViewer jReportsViewer = new JasperViewer(jasperPrint, false);
            jReportsViewer.setVisible(true);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }

    public static String getMesUpperCase(Integer mes) {
        return Strings.toUpperCase(getMes(mes));
    }

    public static String getMes(Integer mes) {
        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Setiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "Error";
        }
    }

    public static String generateFacturaNroFull(Integer nro) {
        try {
            return String.format("001-001-%07d", nro);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            return "";
        }
    }

    public static String generateNextFacturaNroFull(String nro) {
        try {
            String[] s = nro.split("-");
            Integer i = Integer.parseInt(s[2]) + 1;
            
            return String.format(s[0] + "-" + s[1] + "-%07d", i);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            return "";
        }
    }

    public static String generateFacturaNroConEstPtoExp(String est, String ptoExp, Integer nro) {
        try {
            return est + "-" + ptoExp + "-" + String.format("%07d", nro);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            return "";
        }
    }

    public static String generateNextFacturaNroConEstPtoExp(String est, String ptoExp, String nro) {
        try {
            String[] s = nro.split("-");
            Integer i = Integer.parseInt(s[2]) + 1;
            return String.format(est + "-" + ptoExp + "-%07d", i);
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            return "";
        }
    }

    public static String completarNroFactura(String nro) {
        try {
            String temp = nro.replace("_", "");
            String[] partes = temp.split("-");
            if (partes.length > 0) {
                return partes[0] + "-" + partes[1] + "-" + String.format("%07d", Integer.parseInt(partes[2]));
            } else {
                return nro;
            }
        } catch (Exception ex) {
            LOGGER.error(Thread.currentThread().getStackTrace()[1].getMethodName(), ex);
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            return "";
        }
    }

    public static String formatFacturaNroLibroIngresos(String nro) {
        if (!nro.contains("-")) {
            return generateFacturaNroFull(Integer.parseInt(nro.trim()));
        } else {
            return nro;
        }
    }
}
