package com.example.structures;

import com.example.styles.BackgroundsProperty;
import javafx.scene.shape.Shape;

public abstract class AppShape extends AppNode {

    public AppShape(Shape shape) {
        super(shape);
        backgroundStyle =new BackgroundsProperty("-fx-fill","-fx-stroke","-fx-stroke-width");

    }

}
