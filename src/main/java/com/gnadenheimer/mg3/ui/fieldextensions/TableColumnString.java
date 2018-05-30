package com.gnadenheimer.mg3.ui.fieldextensions;

public class TableColumnString<R> extends TableColumnBase<R, String> {

    public TableColumnString(String sText, String valuePropertyName, Double prefWith) {
        super(sText, valuePropertyName, prefWith, false);
    }

}
