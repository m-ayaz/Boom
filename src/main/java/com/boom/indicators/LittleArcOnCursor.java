package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;

public class LittleArcOnCursor extends Arc implements LittleIndicatorStructure {
    public LittleArcOnCursor() {
        setRadiusX(10);
        setRadiusY(5);
        setCenterX(-15);
        setCenterY(15);
        setStartAngle(45);
        setLength(270);
        setType(ArcType.ROUND);
        setFill(new Color(0.3, 1, 0.3, 1));
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
