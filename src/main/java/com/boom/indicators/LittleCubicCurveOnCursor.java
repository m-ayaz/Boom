package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

public  class LittleCubicCurveOnCursor extends CubicCurve implements LittleIndicatorStructure {

    public LittleCubicCurveOnCursor() {
        setStrokeWidth(5);
        setStroke(new Color(1,0.3,0.7, 1));
        setFill(Color.TRANSPARENT);
        setVisible(false);
        setStartX(0);
        setStartY(0);
        setControlX1(7+10);
        setControlY1(-10);
        setControlX2(14-10);
        setControlY2(21+10);
        setEndX(21);
        setEndY(21);
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