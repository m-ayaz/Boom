package com.boom.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

public final class EllipseIcon extends Ellipse {

    public  EllipseIcon(double radiusX, double radiusY, Paint fillColor, Paint strokeColor,double strokeWidth){
        setRadiusX(radiusX);
        setRadiusY(radiusY);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }

}
