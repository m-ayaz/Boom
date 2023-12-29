package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;

public final class LittleQuadCurveOnCursor extends QuadCurve implements LittleIndicatorStructure {

    public LittleQuadCurveOnCursor() {
        setStrokeWidth(5);
        setStroke(new Color(1,0.3,0.7, 1));
        setFill(Color.TRANSPARENT);
        setVisible(false);
        setStartX(0);
        setStartY(0);
        setControlX(20+10);
        setControlY(0-10);
        setEndX(20);
        setEndY(20);
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