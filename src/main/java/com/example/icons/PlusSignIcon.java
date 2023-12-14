package com.example.icons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;

public class PlusSignIcon extends Polygon {

    List<Double> initialPoints=new ArrayList<>();

    public PlusSignIcon(double vertLength,double horLength,Color fillColor,Color strokeColor,double strokeWidth){

        super();

        initialPoints.add(horLength/2);
        initialPoints.add(horLength/2);

        initialPoints.add(horLength/2);
        initialPoints.add(vertLength+horLength/2);

        initialPoints.add(-horLength/2);
        initialPoints.add(vertLength+horLength/2);

        initialPoints.add(-horLength/2);
        initialPoints.add(horLength/2);

        initialPoints.add(-horLength/2-vertLength);
        initialPoints.add(horLength/2);

        initialPoints.add(-horLength/2-vertLength);
        initialPoints.add(-horLength/2);

        initialPoints.add(-horLength/2);
        initialPoints.add(-horLength/2);

        initialPoints.add(-horLength/2);
        initialPoints.add(-horLength/2-vertLength);

        initialPoints.add(horLength/2);
        initialPoints.add(-horLength/2-vertLength);

        initialPoints.add(horLength/2);
        initialPoints.add(-horLength/2);

        initialPoints.add(horLength/2+vertLength);
        initialPoints.add(-horLength/2);

        initialPoints.add(horLength/2+vertLength);
        initialPoints.add(horLength/2);

        getPoints().setAll(initialPoints);
        setFill(fillColor);
        setStroke(strokeColor);
        setStrokeWidth(strokeWidth);
    }

}
