package com.boom.apppaints;

import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import org.json.JSONObject;

import java.util.stream.Collectors;


public  class AppColor extends AppPaint {

//    DoubleProperty red=new SimpleDoubleProperty();
//    DoubleProperty green=new SimpleDoubleProperty();
//    DoubleProperty blue=new SimpleDoubleProperty();
//    DoubleProperty alpha=new SimpleDoubleProperty();

    public AppColor(Color color){
        super(color);
//        red.set(color.getRed());
//        green.set(color.getGreen());
//        blue.set(color.getBlue());
//        alpha.set(color.getOpacity());
//        red.addListener((a, b, c) -> update());
//        green.addListener((a, b, c) -> update());
//        blue.addListener((a, b, c) -> update());
//        alpha.addListener((a, b, c) -> update());
    }

    @Override
    public String toTeX() {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("type",type);
        jsonObject.put("id",id);
        jsonObject.put("color",getFormatted());
        return jsonObject;
    }


    @Override
    public String toSVG(int tabIndent) {
        return "\n"+"\t".repeat(tabIndent)+"<linearGradient id=\"%s\">  <stop stop-color=\"%s\"/> </linearGradient>".formatted(id, getFormatted());
    }

    @Override
    public AppPaint copy() {
        return new AppColor(Color.valueOf(getFormatted()));
    }

    @Override
    protected void update() {
//        set(new Color(red.get(),green.get(),blue.get(),alpha.get()));
//        set(new LinearGradient(startX.get(), startY.get(), endX.get(), endY.get(), isProportional.get(), cycleMethod.get(), appStops.stream().map(appStop -> new Stop(appStop.offset.get(), (Color) appStop.appColor.get())).collect(Collectors.toList())));
    }

}

















