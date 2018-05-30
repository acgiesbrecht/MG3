/*
 * Copyright (C) 2014 Panemu.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.gnadenheimer.mg3.utils.tiwulfx;


import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

/**
 * @author Amrullah <amrullah@panemu.com>
 */
public class TypeAheadTableCell<R, C> extends TableCell<R, C> {

    private final TypeAheadField<C> typeAheadField;

    public TypeAheadTableCell(TableColumn<R, C> column) {
        this.typeAheadField = new TypeAheadField();
        //this.typeAheadField.editableProperty().bind(column.editableProperty());
        this.typeAheadField.disableProperty().bind(column.editableProperty().not());
			/*this.typeAheadField.setOnShowing(event -> {
				final TableView<R> tableView = getTableView();
				tableView.getSelectionModel().select(getTableRow().getIndex());
				tableView.edit(tableView.getSelectionModel().getSelectedIndex(), column);
			});*/
        this.typeAheadField.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (isEditing()) {
                commitEdit(newValue);
            }
        });
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(C item, boolean empty) {
        super.updateItem(item, empty);

        setText(null);
        if (empty) {
            setGraphic(null);
        } else {
            this.typeAheadField.setValue(item);
            this.setGraphic(this.typeAheadField);
        }
    }
}
