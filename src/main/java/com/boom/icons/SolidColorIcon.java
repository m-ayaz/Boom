package com.boom.icons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class SolidColorIcon extends Rectangle {

    public SolidColorIcon(double width, double height,Color color) {
        super(width, height);
        setFill(color);
    }

}
