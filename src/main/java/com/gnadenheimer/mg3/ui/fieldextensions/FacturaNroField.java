package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class FacturaNroField extends MaskField {

    public FacturaNroField(){
        super();
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    try{
                        String temp = getText().replace("_", "");
                        String[] partes = temp.split("-");
                        if (partes.length > 1) {
                            temp = partes[0] + "-" + partes[1] + "-" + String.format("%07d", Integer.parseInt(partes[2]));
                        } else {
                            temp = getText();
                        }
                        setText(temp);
                    }catch (Exception ex){
                        setText("");
                    }
                }
            }
        });
        this.setMask("DDD-DDD-DDDDDDD");
    }

}
