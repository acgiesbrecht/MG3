package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.scene.control.TableCell;

public class TableColumnInteger<R> extends TableColumnBase<R, Integer> {

    public TableColumnInteger(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, true);
        this.setCellFactory(col ->
                new TableCell<R, Integer>() {
                    @Override
                    public void updateItem(Integer number, boolean empty) {
                        super.updateItem(number, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%,d", number));
                        }
                    }
                });
    }

}
