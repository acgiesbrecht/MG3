/*
 * License GNU LGPL
 * Copyright (C) 2012 Amrullah <amrullah@panemu.com>.
 */
package com.panemu.tiwulfx.form;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author Amrullah <amrullah@panemu.com>
 */
public class TableViewControl<R> extends BaseListControl<R, TableView<R>> {

    private TableView<R> textField;

    public TableViewControl() {
        this("");
    }

    public TableViewControl(String propertyName) {
        super(propertyName, new TableView<R>());
        textField = getInputComponent();
        value.bind(textField.itemsProperty());
    }

    @Override
    public void setValue(ObservableList<R> value) {
        textField.itemsProperty().set(value);
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
