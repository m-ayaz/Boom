package com.boom.styles;

import com.boom.configuration.Configs;
import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.boom.tools.Tools.uuid;

public class CSSProperty extends SimpleStringProperty {

public final String id=uuid(Configs.ID_LENGTH);

    ObservableList<AppPaint> fillArray = FXCollections.observableList(new ArrayList<>());
    ObservableList<AppPaint> strokeArray = FXCollections.observableList(new ArrayList<>());
    DoubleProperty strokeWidth = new SimpleDoubleProperty();

    public String getFillColorFX() {
        return fillColorFX;
    }

    public String getStrokeColorFX() {
        return strokeColorFX;
    }

    public String getStrokeWidthFX() {
        return strokeWidthFX;
    }

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

    public void addFill(AppPaint appPaint) {
        fillArray.add( appPaint);
        appPaint.addListener((a,b,c) -> update());
    }

    public void addStroke(AppPaint appPaint) {
        strokeArray.add( appPaint);
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

    public String fillsToSVG(int tabIndent) {
        return String.join("", fillArray.stream().map(appPaint -> appPaint.toSVG(tabIndent)).toList());
    }

    public String fillsToTeX() {
        return null;
    }

    public String strokesToTeX() {
        return null;
    }

    public String strokesToSVG(int tabIndent) {
        return String.join("", strokeArray.stream().map(appPaint -> appPaint.toSVG(tabIndent)).toList());
    }


    public JSONObject toJSON(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("fillColorFX",fillColorFX);
        jsonObject.put("strokeColorFX",strokeColorFX);
        jsonObject.put("strokeWidthFX",strokeWidthFX);
        jsonObject.put("strokeWidth",strokeWidth.get());
        jsonObject.put("fillArray",fillArray.stream().map(AppPaint::toJSON).toArray());
        jsonObject.put("strokeArray",strokeArray.stream().map(AppPaint::toJSON).toArray());
        return jsonObject;
    }




}