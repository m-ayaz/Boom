package com.boom.apppaints;

import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import org.json.JSONObject;

import java.util.stream.Collectors;

public  class AppImagePattern extends AppPaint {


    public DoubleProperty x = new SimpleDoubleProperty(0);
    public DoubleProperty y = new SimpleDoubleProperty(0);
    public DoubleProperty width = new SimpleDoubleProperty(0);
    public DoubleProperty height = new SimpleDoubleProperty(0);
    public BooleanProperty isProportional = new SimpleBooleanProperty(true);
    public SimpleObjectProperty<Image> image=new SimpleObjectProperty<>();


    public AppImagePattern(ImagePattern imagePattern) {
        super(imagePattern);
//        imagePattern.getStops().forEach(stop -> addAppStop(new AppStop(stop)));
        x.set(imagePattern.getX());
        y.set(imagePattern.getY());
        width.set(imagePattern.getWidth());
        height.set(imagePattern.getHeight());
        isProportional.set(imagePattern.isProportional());
        image.set(imagePattern.getImage());
//        cycleMethod.set(imagePattern.getCycleMethod());
//        appStops.addListener((ListChangeListener<AppStop>) change -> update());
        x.addListener((a, b, c) -> update());
        y.addListener((a, b, c) -> update());
        width.addListener((a, b, c) -> update());
        height.addListener((a, b, c) -> update());
        isProportional.addListener((a, b, c) -> update());
        image.addListener((a,b,c)->update());
//        cycleMethod.addListener((a,b,c)->update());
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
        jsonObject.put("x", x.get());
        jsonObject.put("y", y.get());
        jsonObject.put("width", width.get());
        jsonObject.put("height", height.get());
        jsonObject.put("isProportional", isProportional.get());
        throw new RuntimeException("[DEVELOPER_ERROR] image dir is not prepared.");
//        jsonObject.put("imageDir",image.get().)
//        jsonObject.put("cycleMethod", cycleMethod.get().name());
//        jsonObject.put("stopsProportions", appStops.stream().map(appStop -> appStop.get().getOffset()).toArray());
//        jsonObject.put("stopsColors", appStops.stream().map(appStop -> appStop.get().getColor().toString()).toArray());
//        return jsonObject;
    }

    @Override
    public String toSVG(int tabIndent) {
        return null;
//        return "\n" + "\t".repeat(tabIndent) + "<linearGradient id=\"%s\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" gradientUnits=\"%s\" spreadMethod=\"%s\">".formatted(id,
//                x.get(), y.get(), width.get(), height.get(), svgIsProportional.get(isProportional.get()),svgCycleMethod.get(cycleMethod.get().name())) +
//                appStops.stream().map(appStop -> appStop.toSVG(tabIndent + 1)).collect(Collectors.joining()) +
//                "\n" + "\t".repeat(tabIndent) + "</linearGradient>";
    }

    @Override
    public AppPaint copy() {
        return new AppImagePattern(new ImagePattern(image.get(),x.get(), y.get(), width.get(), height.get(), isProportional.get()));
    }


    @Override
    protected void update() {
        set(new ImagePattern(image.get(),x.get(), y.get(), width.get(), height.get(), isProportional.get()));
    }

}








