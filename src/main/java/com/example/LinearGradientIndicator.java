package com.example;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class LinearGradientIndicator extends Group {

    public Circle start=new Circle();
    public Circle end=new Circle();

    Line indicator=new Line();


    public BooleanProperty visibleProperty=new SimpleBooleanProperty();

    public LinearGradientIndicator(){

        super();

        getChildren().addAll(start,end,indicator);




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

        start.setOnMousePressed(mouseEvent -> {

        });

    }

}
