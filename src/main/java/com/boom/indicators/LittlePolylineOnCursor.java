package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineJoin;

public  class LittlePolylineOnCursor extends Polyline implements LittleIndicatorStructure {

    public LittlePolylineOnCursor() {
        double width=20,height=20,strokeWidth=2;
        Color fillColor=Color.TRANSPARENT,strokeColor=Color.BLACK;
        getPoints().addAll(-width/2,-height/2,width/6,height/2,width/2,0.,width/6,-height/2,-width/2,height/2);
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
