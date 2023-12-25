package com.boom.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;

public class ArcIcon extends Arc {

    public ArcIcon(double radiusX, double radiusY,double startAngle,double length, Paint fillColor, Paint strokeColor, double strokeWidth){
        setRadiusX(radiusX);
        setRadiusY(radiusY);
        setStartAngle(startAngle);
        setLength(length);
        setType(ArcType.ROUND);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }

}
