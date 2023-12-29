package com.boom.tools;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class Chessboard extends GridPane {

    public Chessboard(double bitLength, int m, int n, Color boldColor, Color liteColor) {
        super();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Rectangle rectangle = new Rectangle(bitLength, bitLength);
                rectangle.setStrokeWidth(0);
                add(rectangle, i, j);
                if ((i + j) % 2 == 0) {
                    rectangle.setFill(boldColor);
                } else {
                    rectangle.setFill(liteColor);
                }
            }
        }
    }

}
