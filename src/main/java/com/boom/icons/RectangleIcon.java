package com.boom.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public final class RectangleIcon extends Rectangle {

    public RectangleIcon(double width, double height, Paint fillColor, Paint strokeColor, double strokeWidth){
        setWidth(width);
        setHeight(height);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }

}
