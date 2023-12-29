package com.boom.icons;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineJoin;

public final class PolylineIcon extends Polyline {

    public PolylineIcon(double width, double height, Paint fillColor, Paint strokeColor, double strokeWidth){
        getPoints().addAll(-175.*width/350,-100.*height/200,-125.*width/350,-100.*height/200,-75.*width/350,0.*height/200,-25.*width/350,-100.*height/200,25.*width/350,-100.*height/200,75.0*width/350,0.*height/200,125.*width/350,-100.*height/200,175.*width/350,-100.*height/200,125.*width/350,100.*height/200,-125.*width/350,100.*height/200);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
        setStrokeLineJoin(StrokeLineJoin.ROUND);
    }

}
