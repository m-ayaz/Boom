package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public final class LittleTextOnCursor extends Text implements LittleIndicatorStructure {

    public LittleTextOnCursor() {
        setText("A");
        setStroke(new Color(0, 0, 0, 1));
        setFont(new Font(20));
        setVisible(false);
        setX(-15);
        setY(25);
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
