package com.example.icons;

import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;

public class RadialGradientIcon extends Rectangle {

    public RadialGradientIcon(double width, double height, RadialGradient radialGradient) {
        super(width, height);
        setFill(radialGradient);
    }

}
