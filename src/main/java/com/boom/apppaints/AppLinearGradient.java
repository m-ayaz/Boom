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
import org.json.JSONObject;


import java.util.stream.Collectors;

public final class AppLinearGradient extends AppGradient {


    public DoubleProperty startX = new SimpleDoubleProperty(0);
    public DoubleProperty startY = new SimpleDoubleProperty(0);
    public DoubleProperty endX = new SimpleDoubleProperty(0);
    public DoubleProperty endY = new SimpleDoubleProperty(0);


    public AppLinearGradient(LinearGradient linearGradient) {
        super(linearGradient);
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
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("startX", startX.get());
        jsonObject.put("startY", startY.get());
        jsonObject.put("endX", endX.get());
        jsonObject.put("endY", endY.get());
        jsonObject.put("isProportional", isProportional.get());
        jsonObject.put("stopsProportions", appStops.stream().map(appStop -> appStop.get().getOffset()).toArray());
        jsonObject.put("stopsColors", appStops.stream().map(appStop -> appStop.get().getColor().toString()).toArray());
        return jsonObject;
    }

    @Override
    public String toSVG(int tabIndent) {
        return "\n" + "\t".repeat(tabIndent) + "<linearGradient id=\"%s\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" gradientUnits=\"%s\">".formatted(id,
                startX.get(), startY.get(), endX.get(), endY.get(), (isProportional.get() ? "objectBoundingBox" : "userSpaceOnUse")) +
                appStops.stream().map(appStop -> appStop.toSVG(tabIndent + 1)).collect(Collectors.joining()) +
                "\n" + "\t".repeat(tabIndent) + "</linearGradient>";
    }

    @Override
    public AppPaint copy() {
        return new AppLinearGradient(new LinearGradient(startX.get(), startY.get(), endX.get(), endY.get(), isProportional.get(), CycleMethod.NO_CYCLE, appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())));
    }

    @Override
    protected void update() {
        set(new LinearGradient(startX.get(), startY.get(), endX.get(), endY.get(), isProportional.get(), CycleMethod.NO_CYCLE, appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())));
    }

}








