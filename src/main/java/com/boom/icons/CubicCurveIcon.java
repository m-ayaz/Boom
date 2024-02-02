package com.boom.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.CubicCurve;

public  class CubicCurveIcon extends CubicCurve {

    public CubicCurveIcon(double startX, double startY, double controlX1, double controlY1, double controlX2, double controlY2, double endX, double endY, Paint fillColor, Paint strokeColor, double strokeWidth){
        setStartX(startX);
        setStartY(startY);
        setControlX1(controlX1);
        setControlY1(controlY1);
        setControlX2(controlX2);
        setControlY2(controlY2);
        setEndX(endX);
        setEndY(endY);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }

}
