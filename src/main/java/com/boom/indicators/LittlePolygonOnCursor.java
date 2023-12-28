package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineJoin;

public final class LittlePolygonOnCursor extends Polygon implements LittleIndicatorStructure {
    public LittlePolygonOnCursor() {
        double width=20,height=20,strokeWidth=0;
        Color fillColor=new Color(1,1,0,1),strokeColor=Color.BLACK;
        getPoints().addAll(-175.*width/350,-100.*height/200,-125.*width/350,-100.*height/200,-75.*width/350,0.*height/200,-25.*width/350,-100.*height/200,25.*width/350,-100.*height/200,75.0*width/350,0.*height/200,125.*width/350,-100.*height/200,175.*width/350,-100.*height/200,125.*width/350,100.*height/200,-125.*width/350,100.*height/200);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
        setStrokeLineJoin(StrokeLineJoin.ROUND);
    }

    @Override
    public void show(double centerX, double centerY) {
        setVisible(true);
        setTranslateX(centerX);
        setTranslateY(centerY);
    }

    @Override
    public void hide() {
        setVisible(false);
    }
}
