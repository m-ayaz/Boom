package com.example.indicators;

import com.example.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LittleLineOnCursor extends Line implements LittleIndicatorStructure {
    public LittleLineOnCursor() {
        setStrokeWidth(4);
        setStroke(new Color(160.0/256,160.0/256,60.0/256,1));
        setVisible(false);
        setStartX(-15);
        setStartY(0);
        setEndX(-5);
        setEndY(15);
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
