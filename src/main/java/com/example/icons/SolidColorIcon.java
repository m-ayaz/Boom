package com.example.icons;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class SolidColorIcon extends Rectangle {

    public SolidColorIcon(Color color, double width, double height) {
        super(width, height);
        setFill(color);
    }

}
