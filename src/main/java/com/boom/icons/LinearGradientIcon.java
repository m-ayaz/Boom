package com.boom.icons;

import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;

public final class LinearGradientIcon extends Rectangle {

    public LinearGradientIcon(double width, double height,LinearGradient linearGradient) {
        super(width, height);
        setFill(linearGradient);
    }

}
