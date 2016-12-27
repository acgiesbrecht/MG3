/*
 * License GNU LGPL
 * Copyright (C) 2012 Amrullah <amrullah@panemu.com>.
 */
package com.panemu.tiwulfx.form;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author Amrullah <amrullah@panemu.com>
 */
public class TableViewControl<R> extends BaseControl<R, TableView<R>> {

    private TableView<R> tableView;

    public TableViewControl() {
        this("");
    }

    public TableViewControl(String propertyName) {
        super(propertyName, new TableView<R>());
        tableView = getInputComponent();
        value.bind((ObservableValue) tableView.itemsProperty());
    }

    @Override
    public void setValue(R value) {
        tableView.itemsProperty().set((ObservableList<R>) value);
    }

    @Override
    protected void bindValuePropertyWithControl(TableView inputControl) {
        value.bind(inputControl.itemsProperty());
    }

    @Override
    protected void bindEditablePropertyWithControl(TableView inputControl) {
        inputControl.editableProperty().bind(editableProperty());
    }

}
