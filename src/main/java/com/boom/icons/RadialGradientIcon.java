package com.boom.icons;

import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;

public final class RadialGradientIcon extends Rectangle {

    public RadialGradientIcon(double width, double height, RadialGradient radialGradient) {
        super(width, height);
        setFill(radialGradient);
    }

}
