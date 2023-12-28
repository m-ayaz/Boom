package com.boom.icons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public final class MinusSignIcon extends Polygon {

    List<Double> initialPoints=new ArrayList<>();

    public MinusSignIcon(double vertLength, double horLength,Color fillColor,Color strokeColor,double strokeWidth){

        super();

        initialPoints.add(horLength/2);
        initialPoints.add(vertLength/2);

        initialPoints.add(-horLength/2);
        initialPoints.add(vertLength/2);

        initialPoints.add(-horLength/2);
        initialPoints.add(-vertLength/2);

        initialPoints.add(horLength/2);
        initialPoints.add(-vertLength/2);

        getPoints().setAll(initialPoints);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }

}
