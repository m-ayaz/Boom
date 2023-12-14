package com.example.styles;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BackgroundsProperty extends SimpleStringProperty {

    public ObservableList<AppPaint> fillArray = FXCollections.observableList(new ArrayList<>());
    public ObservableList<AppPaint> strokeArray = FXCollections.observableList(new ArrayList<>());
    public DoubleProperty strokeWidth = new SimpleDoubleProperty();

    public BackgroundsProperty(String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        super();
        fillArray.addListener((ListChangeListener<AppPaint>) change -> update(fillColorFX, strokeColorFX, strokeWidthFX));
        strokeArray.addListener((ListChangeListener<AppPaint>) change -> update(fillColorFX, strokeColorFX, strokeWidthFX));
        strokeWidth.addListener(change -> update(fillColorFX, strokeColorFX, strokeWidthFX));
    }


    public void addFill(int index, AppPaint prop) {
        fillArray.add(index, prop);
    }

    public void addStroke(int index, AppPaint prop) {
        strokeArray.add(index, prop);
    }

    public void removeFill(int index) {
        fillArray.remove(index);
    }

    public void removeStroke(int index) {
        strokeArray.remove(index);
    }

    public void setStrokeWidth(double width) {
        strokeWidth.set(width);
    }

//    String paintToString(Color color) {
//        return "rgba(%f,%f,%f,%f)".formatted(color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, color.getOpacity());
//    }

//    String paintToString(LinearGradient linearGradient) {
//        if (!linearGradient.isProportional() || linearGradient.getStops().size() != 2) {
//            throw new AppException(AppExceptionEnum.UnexpectedError);
//        }
//        return "linear-gradient(from %f%% %f%% to %f%% %f%%, %s, %s)".formatted(linearGradient.getStartX(), linearGradient.getStartY(), linearGradient.getEndX(), linearGradient.getEndY(), paintToString(linearGradient.getStops().get(0).getColor()), paintToString(linearGradient.getStops().get(1).getColor()));
//    }

    void update(String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        String fills = fillArray.size() == 0 ? "rgba(0,0,0,0)" : fillArray.stream().map(AppPaint::get).collect(Collectors.joining(","));
        String strokes = strokeArray.size() == 0 ? "rgba(0,0,0,0)" : strokeArray.stream().map(AppPaint::get).collect(Collectors.joining(","));
        double width = strokeWidth == null || strokeWidth.get() == 0 ? 1 : strokeWidth.get();
        set("%s: %s; %s: %s; %s: %s;".formatted(fillColorFX, fills, strokeColorFX, strokes, strokeWidthFX, width));
    }


}
