package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.scene.control.cell.CheckBoxTableCell;

public class TableColumnCheckBox<R> extends TableColumnBase<R, Integer> {

    public TableColumnCheckBox(String sText, String valuePropertyName) {
        super(sText, valuePropertyName);
        this.setCellFactory( tc -> new CheckBoxTableCell<>());
    }

}
