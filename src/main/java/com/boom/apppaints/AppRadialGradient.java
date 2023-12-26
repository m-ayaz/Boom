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

import java.util.stream.Collectors;

public final class AppRadialGradient extends AppGradient {


    public DoubleProperty focusAngle = new SimpleDoubleProperty(0);
    public DoubleProperty focusDistance = new SimpleDoubleProperty(0);
    public DoubleProperty centerX = new SimpleDoubleProperty(0);
    public DoubleProperty centerY = new SimpleDoubleProperty(0);
    public DoubleProperty radius = new SimpleDoubleProperty(0);

    public AppRadialGradient(RadialGradient radialGradient, String id) {
        super(radialGradient, id);
        radialGradient.getStops().forEach(stop -> addAppStop(new AppStop(stop)));
        centerX.set(radialGradient.getCenterX());
        centerY.set(radialGradient.getCenterY());
        focusAngle.set(radialGradient.getFocusAngle());
        focusDistance.set(radialGradient.getFocusDistance());
        radius.set(radialGradient.getRadius());
        isProportional.set(radialGradient.isProportional());
        appStops.addListener((ListChangeListener<AppStop>) change -> update());
        centerX.addListener((a, b, c) -> update());
        centerY.addListener((a, b, c) -> update());
        focusAngle.addListener((a, b, c) -> update());
        focusDistance.addListener((a, b, c) -> update());
        radius.addListener((a, b, c) -> update());
        isProportional.addListener((a, b, c) -> update());
    }

    @Override
    public String toTeX() {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("focusAngle",focusAngle);
        jsonObject.put("focusDistance",focusDistance);
        jsonObject.put("centerX",centerX);
        jsonObject.put("centerY",centerY);
        jsonObject.put("radius",radius);
        jsonObject.put("isProportional",isProportional);
        jsonObject.put("focusAngle",focusAngle);
        jsonObject.put("stopsProportions",appStops.stream().map(appStop -> appStop.get().getOffset()));
        jsonObject.put("stopsColors",appStops.stream().map(appStop -> appStop.get().getColor().toString()));
        return jsonObject;
    }

    @Override
    public void parseFromJSON(JSONObject jsonObject) {

    }

    @Override
    public String toSVG(int tabIndent) {
        return null;
    }

    @Override
    public AppPaint copy(String id) {
        return new AppRadialGradient(new RadialGradient(focusAngle.get(), focusDistance.get(), centerX.get(), centerY.get(),
                radius.get(), isProportional.get(), CycleMethod.NO_CYCLE,
                appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())), id);
    }

    @Override
    protected void update() {
        set(new RadialGradient(focusAngle.get(), focusDistance.get(), centerX.get(), centerY.get(),
                radius.get(), isProportional.get(), CycleMethod.NO_CYCLE,
                appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())));
    }

}







