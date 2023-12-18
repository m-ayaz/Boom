package com.example.structures.enums;


public enum PaintTypeEnum {

    Color(javafx.scene.paint.Color.class.getName()),
    LinearGradient(javafx.scene.paint.LinearGradient.class.getName()),
    RadialGradient(javafx.scene.paint.RadialGradient.class.getName()),
    ImagePattern(javafx.scene.paint.ImagePattern.class.getName());

    private final String paintType;

    PaintTypeEnum(String nodeType) {
        this.paintType = nodeType;
    }

    public String getPaintType() {
        return paintType;
    }
}