package com.example.structures;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public abstract class LinearGradientIndicator {

    Circle start=new Circle();
    Circle end=new Circle();

    Line indicator=new Line();


    public BooleanProperty visibleProperty=new SimpleBooleanProperty();

    public LinearGradientIndicator(){

        start.setFill(Color.RED);
        end.setFill(Color.GREEN);

        start.setRadius(20);
        end.setRadius(20);

        indicator.endXProperty().bind(end.centerXProperty());
        indicator.endYProperty().bind(end.centerYProperty());
        indicator.startXProperty().bind(start.centerXProperty());
        indicator.startYProperty().bind(start.centerYProperty());

        start.visibleProperty().bind(visibleProperty);
        end.visibleProperty().bind(visibleProperty);
        indicator.visibleProperty().bind(visibleProperty);

//        linearGradient

    }

}
