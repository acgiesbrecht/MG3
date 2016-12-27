/*
 * License GNU LGPL
 * Copyright (C) 2012 Amrullah <amrullah@panemu.com>.
 */
package com.panemu.tiwulfx.form;

import com.panemu.tiwulfx.table.CustomTableView;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

/**
 *
 * @author Amrullah <amrullah@panemu.com>
 */
public class TableViewControl<R> extends BaseControl<R, CustomTableView<R>> {

    private CustomTableView<R> tableView;

    public TableViewControl() {
        this("");
    }

    public TableViewControl(String propertyName) {
        super(propertyName, new CustomTableView<R>());
        tableView = getInputComponent();
        value.bind((ObservableValue) tableView.itemsProperty());
    }

    @Override
    public void setValue(R value) {
        tableView.itemsProperty().set((ObservableList<R>) value);
    }

    @Override
    protected void bindValuePropertyWithControl(CustomTableView inputControl) {
        value.bind(inputControl.itemsProperty());
    }

    @Override
    protected void bindEditablePropertyWithControl(CustomTableView inputControl) {
        inputControl.editableProperty().bind(editableProperty());
    }

}
