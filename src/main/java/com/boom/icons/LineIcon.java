package com.boom.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public final class LineIcon extends Line {

    public LineIcon(double startX, double startY,double endX, double endY, Paint strokeColor,double strokeWidth){
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }


}
