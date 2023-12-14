package com.example.panels.paint;

import com.example.icons.LinearGradientIcon;
import com.example.icons.SolidColorIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public abstract class PaintField extends GridPane {

    protected Button solidColorButton = new Button();
    protected Button linearGradientButton = new Button();
    protected Button radialGradientButton = new Button("rad-grad");
    protected Button imagePatternButton = new Button("img-patt");
    protected Button removeButton = new Button("rem");

    protected Label emptySpace = new Label();

    PaintField() {
        solidColorButton.setGraphic(new SolidColorIcon(Color.color(0, 0, 1, 0.5), 20, 20));
        linearGradientButton.setGraphic(new LinearGradientIcon(0, 0, 1, 0, Color.color(0, 0, 1, 0.25), Color.color(0, 0, 1, 1), 20, 20));
//        setBorder(Border.stroke(Color.BLACK));
//        setBorder();
    }

}