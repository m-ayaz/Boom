package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LittleRectangleOnCursor extends Rectangle implements LittleIndicatorStructure {

    public LittleRectangleOnCursor() {
        setWidth(20);
        setHeight(10);
        setX(-25);
        setY(5);
        setFill(new Color(1, 0.3, 0.3, 1));
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