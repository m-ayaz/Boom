package com.example.indicators;

import com.example.structures.LittleShapeStructure;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LittleTextOnCursor extends Text implements LittleShapeStructure {

    public LittleTextOnCursor(){
        setText("A");
        setStroke(new Color(0,0,0,1));
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
//        setFont(new Font(0));
//        setX(0);
//        setY(0);
    }
}
