package com.example.icons;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class LinearGradientIcon extends Rectangle {

    public LinearGradientIcon(double startX, double startY, double endX, double endY, Color startColor, Color endColor, double width, double height) {
        super(width, height);
        setFill(new LinearGradient(startX, startY, endX, endY, true, CycleMethod.NO_CYCLE, new Stop(0, startColor), new Stop(1, endColor)));
    }

}
