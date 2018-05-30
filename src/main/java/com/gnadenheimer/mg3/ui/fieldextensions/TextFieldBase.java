package com.gnadenheimer.mg3.ui.fieldextensions;

import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import javafx.scene.control.TextField;

public class TextFieldBase extends TextField {

    public TextFieldBase() {
        super();
        setOnAction(event -> {
            if (getSkin() instanceof BehaviorSkinBase)
                ((BehaviorSkinBase) getSkin()).getBehavior().traverseNext();

        });
    }

}

