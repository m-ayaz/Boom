package com.example.styles;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ColorProperty_copy extends SimpleObjectProperty<Paint> {

    public ColorProperty_copy(Color color) {
        super(null, color.toString().replaceAll("0x","#"), color);
    }

}
