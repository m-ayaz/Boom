package com.boom.styles;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class SeriesMarkersStyleProperty extends SimpleStringProperty {

    public SeriesMarkersStyleProperty() {
        super();
        background.addListener((a, b, c) -> set(background.get() + border.get() + prefWidth.get() + prefHeight.get() + borderWidth.get()));
        border.addListener((a, b, c) -> set(background.get() + border.get() + prefWidth.get() + prefHeight.get() + borderWidth.get()));
        prefWidth.addListener((a, b, c) -> set(background.get() + border.get() + prefWidth.get() + prefHeight.get() + borderWidth.get()));
        prefWidth.addListener((a, b, c) -> set(background.get() + border.get() + prefWidth.get() + prefHeight.get() + borderWidth.get()));
        borderWidth.addListener((a, b, c) -> set(background.get() + border.get() + prefWidth.get() + prefHeight.get() + borderWidth.get()));
    }

    StringProperty background = new SimpleStringProperty();
    StringProperty border = new SimpleStringProperty();
    StringProperty prefWidth = new SimpleStringProperty();
    StringProperty prefHeight = new SimpleStringProperty();
    StringProperty borderWidth = new SimpleStringProperty();

    public void setBackground(Color color) {
        background.set("-fx-background-color: rgba(%f,%f,%f,%f);".formatted(color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, color.getOpacity()));
    }

    public void setBorder(Color color) {
        border.set("-fx-border-color: rgba(%f,%f,%f,%f);".formatted(color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, color.getOpacity()));
    }

    public void setPrefWidth(double x) {
        prefWidth.set("-fx-pref-width: %fpx;".formatted(x));
    }

    public void setPrefHeight(double x) {
        prefHeight.set("-fx-pref-width: %fpx;".formatted(x));
    }

    public void setBorderWidth(double x) {
        borderWidth.set("-fx-border-width %fpx;".formatted(x));
    }

}

