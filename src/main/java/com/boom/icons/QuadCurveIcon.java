package com.boom.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.QuadCurve;

public final class QuadCurveIcon extends QuadCurve {

    public QuadCurveIcon(double startX,double startY,double controlX,double controlY,double endX,double endY, Paint fillColor, Paint strokeColor, double strokeWidth){
        setStartX(startX);
        setStartY(startY);
        setControlX(controlX);
        setControlY(controlY);
        setEndX(endX);
        setEndY(endY);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }

}
