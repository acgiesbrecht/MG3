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
package com.panemu.tiwulfx.table;

import com.panemu.tiwulfx.common.TiwulFXUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import jfxtras.scene.control.LocalDateTimeTextField;

/**
 *
 * @author Amrullah <amrullah@panemu.com>
 */
public class DateTimeTableCell<R> extends BaseCell<R, LocalDateTime> {

    private LocalDateTimeTextField dateTimePicker;
    private DateTimeColumn<R> column;

    public DateTimeTableCell(DateTimeColumn<R> column) {
        super(column);
        this.column = column;
        //datePickerConverter = new LocalDateConverterWithDateFormat(column.getDateFormat());
    }

    @Override
    protected void setValueToEditor(LocalDateTime value) {
        dateTimePicker.setLocalDateTime(value);
    }

    @Override
    protected LocalDateTime getValueFromEditor() {
        return dateTimePicker.getLocalDateTime();
    }

    @Override
    protected Control getEditor() {
        if (dateTimePicker == null) {
            dateTimePicker = new LocalDateTimeTextField();
            //dateTimePicker.setConverter(datePickerConverter);

//            TiwulFXUtil.attachShortcut(dateTimePicker, column.getController());
            final Callback<LocalDateTimeTextField, DateCell> dayCellFactory
                    = new Callback<LocalDateTimeTextField, DateCell>() {
                @Override
                public DateCell call(final LocalDateTimeTextField dateTimePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (column.getController() != null && !column.getController().isEnabled(item.atStartOfDay())) {
                                setDisable(true);
                            }
                        }
                    };
                }
            };
            //dateTimePicker.setDayCellFactory(dayCellFactory);

            dateTimePicker.localDateTimeProperty().addListener(new ChangeListener<LocalDateTime>() {

                @Override
                public void changed(ObservableValue<? extends LocalDateTime> observable, LocalDateTime oldValue, LocalDateTime newValue) {
                    if (column.getController() != null && newValue != null && !column.getController().isEnabled(newValue)) {
                        column.getController().onDisabledDateSelected(dateTimePicker, oldValue);
                    }
                }
            });
        }
        return dateTimePicker;
    }

    @Override
    protected void attachEnterEscapeEventHandler() {
        /**
         * Use event filter instead on onKeyPressed because Enter and Escape
         * have been consumed by Combobox it self
         */
        dateTimePicker.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(getValueFromEditor());
                    t.consume();
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                    /**
                     * Propagate ESCAPE key press to cell to go to Browsing mode
                     * on Agile editing only
                     */
                    DateTimeTableCell.this.fireEvent(t);
                }
            }
        });
    }

    private StringConverter<LocalDateTime> dateTimePickerConverter;

}
