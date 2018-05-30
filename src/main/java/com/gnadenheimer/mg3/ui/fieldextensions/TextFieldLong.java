package com.gnadenheimer.mg3.ui.fieldextensions;

import javafx.geometry.Pos;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.LongStringConverter;

import java.text.DecimalFormat;
import java.util.function.UnaryOperator;

public class TextFieldLong extends TextFieldBase {

    public TextFieldLong() {
        super();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        decimalFormat.setParseBigDecimal(true);
        LongStringConverter converter = new LongStringConverter() {
            @Override
            public Long fromString(String value) {
                try {
                    if (value == null) {
                        return null;
                    }

                    value = value.trim();
                    if (value.length() < 1) {
                        return null;
                    }
                    return Long.valueOf(decimalFormat.parse(value.replace(",", "").replace(".", "")).longValue());
                } catch (Exception ex) {
                    return null;
                }
            }

            @Override
            public String toString(Long value) {
                if (value == null) {
                    return "";
                }
                return decimalFormat.format(value);
            }
        };

        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            // if proposed change results in a valid value, return change as-is:
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            } else if ("-".equals(change.getText())) {

                // if user types or pastes a "-" in middle of current text,
                // toggle sign of value:

                if (change.getControlText().startsWith("-")) {
                    // if we currently start with a "-", remove first character:
                    change.setText("");
                    change.setRange(0, 1);
                    // since we're deleting a character instead of adding one,
                    // the caret position needs to move back one, instead of
                    // moving forward one, so we modify the proposed change to
                    // move the caret two places earlier than the proposed change:
                    change.setCaretPosition(change.getCaretPosition() - 2);
                    change.setAnchor(change.getAnchor() - 2);
                } else {
                    // otherwise just insert at the beginning of the text:
                    change.setRange(0, 0);
                }
                return change;
            }
            // invalid change, veto it by returning null:
            return null;
        };
        setMaxWidth(150);
        setAlignment(Pos.CENTER_RIGHT);
        setTextFormatter(new TextFormatter<>(converter));
    }

}

