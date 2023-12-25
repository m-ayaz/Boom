package com.boom.apppaints;

import com.boom.structures.abstracts.AppGradient;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.stream.Collectors;

public class AppLinearGradient extends AppGradient {


    public DoubleProperty startX = new SimpleDoubleProperty(0);
    public DoubleProperty startY = new SimpleDoubleProperty(0);
    public DoubleProperty endX = new SimpleDoubleProperty(0);
    public DoubleProperty endY = new SimpleDoubleProperty(0);


    public AppLinearGradient(LinearGradient linearGradient, String id) {
        super(linearGradient, id);
        linearGradient.getStops().forEach(stop -> addAppStop(new AppStop(stop)));
        startX.set(linearGradient.getStartX());
        startY.set(linearGradient.getStartY());
        endX.set(linearGradient.getEndX());
        endY.set(linearGradient.getEndY());
        isProportional.set(linearGradient.isProportional());
        appStops.addListener((ListChangeListener<AppStop>) change -> update());
        startX.addListener((a, b, c) -> update());
        startY.addListener((a, b, c) -> update());
        endX.addListener((a, b, c) -> update());
        endY.addListener((a, b, c) -> update());
        isProportional.addListener((a, b, c) -> update());
    }

    @Override
    public String toTeX() {
        return null;
    }

    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public String toSVG(int tabIndent) {
        return "\n" + "\t".repeat(tabIndent) + "<linearGradient id=\"%s\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" gradientUnits=\"%s\">".formatted(id,
                startX.get(), startY.get(), endX.get(), endY.get(), (isProportional.get() ? "objectBoundingBox" : "userSpaceOnUse")) +
                appStops.stream().map(appStop -> appStop.toSVG(tabIndent + 1)).collect(Collectors.joining()) +
                "\n" + "\t".repeat(tabIndent) + "</linearGradient>";
    }

    @Override
    public AppPaint copy(String id) {
        return new AppLinearGradient(new LinearGradient(startX.get(), startY.get(), endX.get(), endY.get(), isProportional.get(), CycleMethod.NO_CYCLE, appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())), id);
    }

    @Override
    protected void update() {
        set(new LinearGradient(startX.get(), startY.get(), endX.get(), endY.get(), isProportional.get(), CycleMethod.NO_CYCLE, appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())));
    }

}








