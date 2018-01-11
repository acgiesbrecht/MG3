/*
 * License GNU LGPL
 * Copyright (C) 2012 Amrullah <amrullah@panemu.com>.
 */
package com.panemu.tiwulfx.form;

/*
import com.gnadenheimer.mg3.utils.Utils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import jidefx.scene.control.field.MaskTextField;

/*
/**
 *
 * @author Amrullah <amrullah@panemu.com>
 *//*
public class FacturaNroControl extends BaseControl<String, TextField> {

    private MaskField textField;

    public FacturaNroControl() {
        this("");
    }

    public FacturaNroControl(String propertyName) {
        super(propertyName, new MaskTextField());
        textField = getInputComponent();
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    String temp = getValue().replace("_", "");
                    String[] partes = temp.split("-");
                    if (partes.length > 1) {
                        temp = partes[0] + "-" + partes[1] + "-" + String.format("%07d", Integer.parseInt(partes[2]));
                    } else {
                        temp = getValue();
                    }
                    setValue(Utils.completarNroFactura(temp));
                }
            }
        });
        textField.setMask("DDD-DDD-DDDDDDD");
        value.bind(textField.textProperty());
    }

    @Override
    public void setValue(String value) {
        textField.textProperty().set(value);
    }

    @Override
    protected void bindValuePropertyWithControl(MaskField inputControl) {
        value.bind(inputControl.textProperty());
    }

    @Override
    protected void bindEditablePropertyWithControl(MaskField inputControl) {
        inputControl.editableProperty().bind(editableProperty());
    }

}
*/