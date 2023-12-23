package com.boom.styles;

import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CSSProperty extends SimpleStringProperty {



    ObservableList<AppPaint> fillArray = FXCollections.observableList(new ArrayList<>());
    ObservableList<AppPaint> strokeArray = FXCollections.observableList(new ArrayList<>());
    DoubleProperty strokeWidth = new SimpleDoubleProperty();
    String fillColorFX;
    String strokeColorFX;
    String strokeWidthFX;

    public CSSProperty(String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        super();
        this.fillColorFX = fillColorFX;
        this.strokeColorFX = strokeColorFX;
        this.strokeWidthFX = strokeWidthFX;
        fillArray.addListener((ListChangeListener< AppPaint>) change -> update());
        strokeArray.addListener((ListChangeListener<AppPaint>) change -> update());
        strokeWidth.addListener((a,b,c) -> update());
    }

    public void addFill(int index, AppPaint appPaint) {
        fillArray.add(index, appPaint);
        appPaint.addListener((a,b,c) -> update());
    }

    public void addStroke(int index, AppPaint appPaint) {
        strokeArray.add(index, appPaint);
        appPaint.addListener(  (a,b,c) -> update());
    }

    public ObservableList<AppPaint> getFillArray() {
        return fillArray;
    }

    public ObservableList<AppPaint> getStrokeArray() {
        return strokeArray;
    }

    public void removeAllFills(){
        fillArray.clear();
    }

    public void removeAllStrokes(){
        strokeArray.clear();
    }

    public double getStrokeWidth() {
        return strokeWidth.get();
    }

    public void setStrokeWidth(double width) {
        strokeWidth.set(width);
    }

    public void removeFill(int index) {
        fillArray.remove(index);
    }

    public void removeFill(AppPaint appPaint) {
        fillArray.remove(appPaint);
    }

    public void removeStroke(AppPaint appPaint) {
        strokeArray.remove(appPaint);
    }

    public void removeStroke(int index) {
        strokeArray.remove(index);
    }

    public DoubleProperty strokeWidthProperty() {
        return strokeWidth;
    }

    void update() {
        String fills = fillArray.size() == 0 ? "rgba(0,0,0,0)" : fillArray.stream().map(AppPaint::getFormatted).collect(Collectors.joining(","));
        String strokes = strokeArray.size() == 0 ? "rgba(0,0,0,0)" : strokeArray.stream().map(AppPaint::getFormatted).collect(Collectors.joining(","));
        double width = strokeWidth == null || strokeWidth.get() == 0 ? 1 : strokeWidth.get();
        set("%s: %s; %s: %s; %s: %s;".formatted(fillColorFX, fills, strokeColorFX, strokes, strokeWidthFX, width));
    }


}