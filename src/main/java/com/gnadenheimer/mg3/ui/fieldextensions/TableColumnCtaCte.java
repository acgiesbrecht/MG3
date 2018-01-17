package com.gnadenheimer.mg3.ui.fieldextensions;

import com.gnadenheimer.utils.FormatCtaCte;
import javafx.scene.control.TableCell;

public class TableColumnCtaCte<R> extends TableColumnBase<R, Integer> {

    public TableColumnCtaCte(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, true);
        this.setCellFactory(col ->
                new TableCell<R, Integer>() {
                    @Override
                    public void updateItem(Integer number, boolean empty) {
                        super.updateItem(number, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(FormatCtaCte.format(number));
                        }
                    }
                });
    }

}
