package com.boom.apppaints;

import com.boom.structures.abstracts.AppGradient;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.boom.tools.Tools.arrayToList;
import static java.lang.Math.*;

public  class AppRadialGradient extends AppGradient {


    public DoubleProperty focusAngle = new SimpleDoubleProperty(0);
    public DoubleProperty focusDistance = new SimpleDoubleProperty(0);
    public DoubleProperty centerX = new SimpleDoubleProperty(0);
    public DoubleProperty centerY = new SimpleDoubleProperty(0);
    public DoubleProperty radius = new SimpleDoubleProperty(0);

    public AppRadialGradient(RadialGradient radialGradient) {
        super(radialGradient);
        radialGradient.getStops().forEach(stop -> addAppStop(new AppStop(stop)));
        centerX.set(radialGradient.getCenterX());
        centerY.set(radialGradient.getCenterY());
        focusAngle.set(radialGradient.getFocusAngle());
        focusDistance.set(radialGradient.getFocusDistance());
        radius.set(radialGradient.getRadius());
        isProportional.set(radialGradient.isProportional());
        cycleMethod.set(radialGradient.getCycleMethod());
        appStops.addListener((ListChangeListener<AppStop>) change -> update());
        centerX.addListener((a, b, c) -> update());
        centerY.addListener((a, b, c) -> update());
        focusAngle.addListener((a, b, c) -> update());
        focusDistance.addListener((a, b, c) -> update());
        radius.addListener((a, b, c) -> update());
        isProportional.addListener((a, b, c) -> update());
        cycleMethod.addListener((a,b,c)->update());
    }

    @Override
    public String toTeX() {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject=new JSONObject();
        List<Double> stopsProportions=new ArrayList<>();
        List<String> stopsColors=new ArrayList<>();
        appStops.forEach(appStop -> {
            stopsProportions.add(appStop.offset.get());
            stopsColors.add(appStop.appColor.getFormatted());
        });
        jsonObject.put("type",type);
        jsonObject.put("id",id);
        jsonObject.put("focusAngle",focusAngle.get());
        jsonObject.put("focusDistance",focusDistance.get());
        jsonObject.put("centerX",centerX.get());
        jsonObject.put("centerY",centerY.get());
        jsonObject.put("radius",radius.get());
        jsonObject.put("isProportional",isProportional.get());
        jsonObject.put("cycleMethod",cycleMethod.get().name());
        jsonObject.put("stopsProportions",stopsProportions);
        jsonObject.put("stopsColors",stopsColors);
        return jsonObject;
    }

    @Override
    public String toSVG(int tabIndent) {
        return "\n" + "\t".repeat(tabIndent) + "<radialGradient id=\"%s\" cx=\"%f\" cy=\"%f\" fx=\"%f\" fy=\"%f\" r=\"%f\" gradientUnits=\"%s\" spreadMethod=\"%s\">".formatted(id,
                centerX.get(), centerY.get(), centerX.get()+0.99*focusDistance.get()*radius.get()*cos(focusAngle.get()*PI/180), centerY.get()+0.99*focusDistance.get()*radius.get()*sin(focusAngle.get()*PI/180),radius.get(), svgIsProportional.get(isProportional.get()),svgCycleMethod.get(cycleMethod.get().name())) +
                appStops.stream().map(appStop -> appStop.toSVG(tabIndent + 1)).collect(Collectors.joining()) +
                "\n" + "\t".repeat(tabIndent) + "</radialGradient>";
    }

    @Override
    public AppPaint copy() {
        return new AppRadialGradient(new RadialGradient(focusAngle.get(), focusDistance.get(), centerX.get(), centerY.get(),
                radius.get(), isProportional.get(), cycleMethod.get(),
                appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())));
    }

    @Override
    protected void update() {
        set(new RadialGradient(focusAngle.get(), focusDistance.get(), centerX.get(), centerY.get(),
                radius.get(), isProportional.get(), cycleMethod.get(),
                appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())));
    }

}







