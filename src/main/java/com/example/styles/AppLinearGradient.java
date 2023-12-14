package com.example.styles;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.ArrayList;

public class AppLinearGradient extends AppPaint {


    public ObservableList<Stop> stops = FXCollections.observableList(new ArrayList<>());
    public DoubleProperty startX = new SimpleDoubleProperty(0);
    public DoubleProperty startY = new SimpleDoubleProperty(0);
    public DoubleProperty endX = new SimpleDoubleProperty(0);
    public DoubleProperty endY = new SimpleDoubleProperty(0);
    public BooleanProperty isProportional = new SimpleBooleanProperty(true);

    public AppLinearGradient(LinearGradient linearGradient) {
        super(linearGradient);
        stops.setAll(linearGradient.getStops());
        startX.set(linearGradient.getStartX());
        startY.set(linearGradient.getStartY());
        endX.set(linearGradient.getEndX());
        endY.set(linearGradient.getEndY());
        isProportional.set(linearGradient.isProportional());
        stops.addListener((ListChangeListener<Stop>) change -> update());
        startX.addListener(change -> update());
        startY.addListener(change -> update());
        endX.addListener(change -> update());
        endY.addListener(change -> update());
        isProportional.addListener(change -> update());
    }

    void update() {
        paint = new LinearGradient(startX.get(), startY.get(), endX.get(), endY.get(), isProportional.get(), CycleMethod.NO_CYCLE, stops);
    }

}