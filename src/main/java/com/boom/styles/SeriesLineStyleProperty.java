package com.boom.styles;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class SeriesLineStyleProperty extends SimpleStringProperty {

    public SeriesLineStyleProperty() {
        super();
        color.addListener((_1, _2, _3) -> update());
        width.addListener((_1, _2, _3) -> update());
    }

    void update(){
        Color c = color.get() == null ? new Color(0, 0, 0, 0) : color.get();
        Double w = width.get() == 0 ? 1 : width.get();
        set("-fx-stroke: rgba(%f,%f,%f,%f);-fx-stroke-width: %fpx;".formatted(c.getRed() * 255, c.getGreen() * 255, c.getBlue() * 255, c.getOpacity(), w));
    }

    public ObjectProperty<Color> color = new SimpleObjectProperty<>();

    public DoubleProperty width = new SimpleDoubleProperty();

}
