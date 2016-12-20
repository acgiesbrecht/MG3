/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.controller.egresos;

import com.gnadenheimer.mg3.DaoBase;
import com.gnadenheimer.mg3.domain.TblFacturasCompra;
import com.panemu.tiwulfx.form.Form;
import com.panemu.tiwulfx.form.NumberControl;
import com.panemu.tiwulfx.form.TextControl;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author AdminLocal
 */
public class FacturasCompraEditController extends AnchorPane implements Initializable {

    private DaoBase<TblFacturasCompra> daoTblFacturasCompra = new DaoBase<>(TblFacturasCompra.class);

    @FXML
    private Button btnAdd;
    @FXML
    private ImageView cmdAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private ImageView cmdEdit;
    @FXML
    private Button btnSave;
    @FXML
    private ImageView cmdSave;
    @FXML
    private Form<TblFacturasCompra> tblFacturasCompraForm;
    @FXML
    private TextControl txtTimbrado;
    @FXML
    private TextControl txtNro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*txtNro.getPatternVerifiers().put("n", new IntegerRangePatternVerifier(0, 999));
        txtNro.getPatternVerifiers().put("a", new IntegerRangePatternVerifier(0, 9999999));
        txtNro.setPattern("n-n-a");
         */
        StringConverter<String> formatter;
        formatter = new StringConverter<String>() {
            @Override
            public String fromString(String string) {
                if (string != null) {
                    return com.gnadenheimer.mg3.utils.Utils.completarNroFactura(string);
                } else {
                    return null;
                }
            }

            @Override
            public String toString(String object) {
                if (object != null) {
                    return com.gnadenheimer.mg3.utils.Utils.completarNroFactura(object);
                } else {
                    return null;
                }
            }
        };

        /*UnaryOperator<TextFormatter.Change> filter;
        filter = (TextFormatter.Change change) -> {
            String text = change.getText();
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isDigit(text.charAt(i))) {
                    return null;
                }
            }
            return change;
        };
        txtNro.setTextFormatter(new TextFormatter<>(formatter, "", filter));*/
        txtNro.setTextFormatter(new TextFormatter<>(formatter, ""));

        btnSave.setOnAction(eventHandler);

        btnEdit.setOnAction(eventHandler);

        btnAdd.setOnAction(eventHandler);
//        btnReload.setOnAction(eventHandler);

        btnSave.disableProperty()
                .bind(tblFacturasCompraForm.modeProperty().isEqualTo(Form.Mode.READ));
        btnAdd.disableProperty()
                .bind(tblFacturasCompraForm.modeProperty().isNotEqualTo(Form.Mode.READ));
        btnEdit.disableProperty()
                .bind(tblFacturasCompraForm.modeProperty().isNotEqualTo(Form.Mode.READ));

        tblFacturasCompraForm.bindChildren();
    }
    private EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            if (t.getSource() == btnSave && tblFacturasCompraForm.validate()) {
                TblFacturasCompra p = tblFacturasCompraForm.getRecord();
                if (p.getId() == null) {
                    p = daoTblFacturasCompra.insert(p);
                } else {
                    p = daoTblFacturasCompra.update(p);
                    //p = daoTblFacturasCompra.initRelationship(p, Person_.insurance.getName());
                }
                tblFacturasCompraForm.setRecord(p);
                tblFacturasCompraForm.setMode(Form.Mode.READ);
            } else if (t.getSource() == btnEdit) {
                tblFacturasCompraForm.setMode(Form.Mode.EDIT);
            } else if (t.getSource() == btnAdd) {
                tblFacturasCompraForm.setRecord(new TblFacturasCompra());
                tblFacturasCompraForm.setMode(Form.Mode.INSERT);
//            } else if (t.getSource() == btnReload) {
//                personForm.setValueObject(person);
//                personForm.setMode(Form.Mode.READ);
//                personForm.validate();//ensure to remove exclamation mark next to the invalid fields
            }
        }
    };

    public void setTblFacturasCompra(TblFacturasCompra factura) {
        tblFacturasCompraForm.setRecord(factura);
    }

    public void setMode(Form.Mode mode) {
        tblFacturasCompraForm.setMode(mode);
    }
}
