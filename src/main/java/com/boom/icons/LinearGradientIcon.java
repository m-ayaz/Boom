package com.boom.icons;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class LinearGradientIcon extends Rectangle {

    public LinearGradientIcon(double width, double height,LinearGradient linearGradient) {
        super(width, height);
        setFill(linearGradient);
    }

}
