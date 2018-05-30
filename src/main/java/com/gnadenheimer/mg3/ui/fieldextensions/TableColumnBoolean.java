package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.scene.control.cell.CheckBoxTableCell;

public class TableColumnBoolean<R> extends TableColumnBase<R, Boolean> {

    public TableColumnBoolean(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, false);
        //setCellFactory(tc -> new CheckBoxTableCell<>());
        setCellFactory(CheckBoxTableCell.forTableColumn(this));
        setEditable(true);
        setMaxWidth(50);
        setMinWidth(50);
    }

}
