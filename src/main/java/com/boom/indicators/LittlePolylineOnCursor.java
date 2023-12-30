package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeLineJoin;

import static com.boom.tools.Tools.print;

public final class LittlePolylineOnCursor extends Polyline implements LittleIndicatorStructure {

    public LittlePolylineOnCursor() {
        double width=20,height=20,strokeWidth=2;
        Color fillColor=Color.TRANSPARENT,strokeColor=Color.BLACK;
        getPoints().addAll(-width/2,-height/2,width/6,height/2,width/2,0.,width/6,-height/2,-width/2,height/2);
//        getPoints().setAll(-100.,-100.,50.,100.,100.,0.,50.,-100.,-100.,100.);
//        print(-width/2+","+-height/2+","+width/6+","+height/2+","+width/2+","+0.+","+width/6+","+height/2+","+(-width/2)+","+height/2);
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
