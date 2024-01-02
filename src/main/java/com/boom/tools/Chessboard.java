package com.boom.tools;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class Chessboard extends GridPane {

    double bitLength;
    int m,n;
    Color boldColor, liteColor;

    public Chessboard(double bitLength, int m, int n, Color boldColor, Color liteColor) {
        super();
        this.bitLength=bitLength;
        this.m=m;
        this.n=n;
        this.boldColor=boldColor;
        this.liteColor=liteColor;
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

    public String toSVG(int tabIndent){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + j) % 2 == 0) {
                    stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"%s\" stroke=\"transparent\" stroke-width=\"0\" transform=\"translate(%f,%f)\"/>".formatted(bitLength,bitLength,boldColor.toString().replace("0x","#"),i*bitLength,j*bitLength));
                } else {
                    stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<rect x=\"0\" y=\"0\" rx=\"0\" ry=\"0\" width=\"%f\" height=\"%f\" fill=\"%s\" stroke=\"transparent\" stroke-width=\"0\" transform=\"translate(%f,%f)\"/>".formatted(bitLength,bitLength,liteColor.toString().replace("0x","#"),i*bitLength,j*bitLength));
                }
            }
        }
        return stringBuilder.toString();

    }

}
