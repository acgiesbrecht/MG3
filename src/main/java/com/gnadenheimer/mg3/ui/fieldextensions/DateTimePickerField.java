package com.gnadenheimer.mg3.ui.fieldextensions;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import tornadofx.control.DateTimePicker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimePickerField extends DateTimePicker {

    public DateTimePickerField() {
        super();
        setConverter(new StringConverter<LocalDate>() {

            private final DateTimeFormatter fastFormatter1 = DateTimeFormatter.ofPattern("ddMMuuuu");
            private final DateTimeFormatter fastFormatter2 = DateTimeFormatter.ofPattern("d/M/u");
            private final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

            @Override
            public String toString(LocalDate object) {
                try {
                    return object.format(defaultFormatter);
                } catch (Exception ex) {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                try {
                    return LocalDate.parse(string, fastFormatter1);
                } catch (DateTimeParseException ignored) {
                }
                try {
                    return LocalDate.parse(string, fastFormatter2);
                } catch (DateTimeParseException ignored) {
                }
                return LocalDate.parse(string, defaultFormatter);
            }
        });
        TextField textField = getEditor();
        textField.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if (!"0123456789/".contains(event.getCharacter())) {
                return;
            }
            if ("/".equals(event.getCharacter()) && (textField.getText().isEmpty() || textField.getText().charAt(textField.getCaretPosition() - 1) == '/')) {
                //If the users types slash again after it has been added, cancels it.
                System.out.println("Cancelando o bagulho!");
                event.consume();
            }
            textField.selectForward();
            if (!event.getCharacter().equals("/") && textField.getSelectedText().equals("/")) {
                textField.cut();
                textField.selectForward();
            }
            textField.cut();

            Platform.runLater(() -> {
                String textUntilHere = textField.getText(0, textField.getCaretPosition());
                if (textUntilHere.matches("\\d\\d") || textUntilHere.matches("\\d\\d/\\d\\d")) {
                    String textAfterHere = "";
                    try {
                        textAfterHere = textField.getText(textField.getCaretPosition() + 1, textField.getText().length());
                    } catch (Exception ignored) {
                    }
                    int caretPosition = textField.getCaretPosition();
                    textField.setText(textUntilHere + "/" + textAfterHere);
                    textField.positionCaret(caretPosition + 1);
                }
            });
        });

        setOnAction(event -> {
            if (getSkin() instanceof BehaviorSkinBase)
                ((BehaviorSkinBase) getSkin()).getBehavior().traverseNext();
        });
    }


}
