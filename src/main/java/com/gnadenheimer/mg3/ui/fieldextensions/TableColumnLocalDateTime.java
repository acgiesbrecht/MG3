package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.scene.control.TableCell;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TableColumnLocalDateTime<R> extends TableColumnBase<R, LocalDateTime> {

    public TableColumnLocalDateTime(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith);

        this.setCellFactory(col -> new TableCell<R, LocalDateTime>() {
            @Override
            public void updateItem(LocalDateTime date, boolean empty) {
                super.updateItem(date, empty);

                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                }
            }
        });
    }
}
