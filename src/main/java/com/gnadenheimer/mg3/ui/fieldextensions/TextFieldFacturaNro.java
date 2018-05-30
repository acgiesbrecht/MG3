package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class TextFieldFacturaNro extends MaskField {

    public TextFieldFacturaNro() {
        super();
        focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (!newPropertyValue) {
                    try {
                        String temp = getText().replace("_", "");
                        String[] partes = temp.split("-");
                        if (partes.length > 1) {
                            temp = partes[0] + "-" + partes[1] + "-" + String.format("%07d", Integer.parseInt(partes[2]));
                        } else {
                            temp = getText();
                        }
                        setText(temp);
                    } catch (Exception ex) {
                        setText("");
                    }
                }
            }
        });
        setMask("DDD-DDD-DDDDDDD");
        setMaxWidth(150);
        final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);

        /*addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent KV) {
                try {
                    Robot eventRobot = new Robot();
                    switch (KV.getCode()) {
                        case ENTER: {
                            if (!(KV.getTarget() instanceof TextArea)) {
                                eventRobot.keyPress(java.awt.event.KeyEvent.VK_TAB);
                                eventRobot.keyRelease(java.awt.event.KeyEvent.VK_TAB);
                                KV.consume();
                            }
                            break;
                        }
                        case TAB: {
                            if (!(KV.getTarget() instanceof TextArea)) {
                                KV.consume();
                            }
                            break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });*/

    }

}
