package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
public  class LittleEllipseOnCursor extends Ellipse implements LittleIndicatorStructure {
    public LittleEllipseOnCursor() {
        setRadiusX(10);
        setRadiusY(5);
        setCenterX(-15);
        setCenterY(15);
        setFill(new Color(0.3, 0.3, 1, 1));
        setVisible(false);
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