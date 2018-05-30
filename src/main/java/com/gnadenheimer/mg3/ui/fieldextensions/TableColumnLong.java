package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.scene.control.TableCell;

public class TableColumnLong<R> extends TableColumnBase<R, Long> {

    public TableColumnLong(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, true);
        this.setCellFactory(col ->
                new TableCell<R, Long>() {
                    @Override
                    public void updateItem(Long number, boolean empty) {
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
