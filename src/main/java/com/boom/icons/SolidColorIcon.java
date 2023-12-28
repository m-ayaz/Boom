package com.boom.icons;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public final class SolidColorIcon extends Rectangle {

    public SolidColorIcon(double width, double height,Color color) {
        super(width, height);
        setFill(color);
    }

}
