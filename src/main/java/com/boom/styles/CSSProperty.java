package com.boom.styles;

import com.boom.structures.abstracts.AppPaint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.boom.configuration.Configs.ID_LENGTH;
import static com.boom.tools.Tools.uuid;

public class CSSProperty extends SimpleStringProperty {

    public final String id = uuid(ID_LENGTH);

    private final ObservableList<AppPaint> fillArray = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<AppPaint> strokeArray = FXCollections.observableList(new ArrayList<>());
    private final DoubleProperty strokeWidth = new SimpleDoubleProperty();
//    ObjectProperty<StrokeLineCap> strokeLineCap=new SimpleObjectProperty<>();
//    ObjectProperty<StrokeLineJoin> strokeLineJoin=new SimpleObjectProperty<>();
//    DoubleProperty strokeMiterLimit=new SimpleDoubleProperty();
//    DoubleProperty strokeDashOffset=new SimpleDoubleProperty();

//    public String getFillColorFX() {
//        return fillColorFX;
//    }
//
//    public String getStrokeColorFX() {
//        return strokeColorFX;
//    }
//
//    public String getStrokeWidthFX() {
//        return strokeWidthFX;
//    }

    String fillColorFX;
    String strokeColorFX;
    String strokeWidthFX;

    public CSSProperty(String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        super();
//        Shape h;
//        h.setStro
        this.fillColorFX = fillColorFX;
        this.strokeColorFX = strokeColorFX;
        this.strokeWidthFX = strokeWidthFX;
        fillArray.addListener((ListChangeListener<AppPaint>) change -> update());
        strokeArray.addListener((ListChangeListener<AppPaint>) change -> update());
        strokeWidth.addListener((a, b, c) -> update());
    }

    public void setFill(int index, AppPaint appPaint) {
        fillArray.set(index, appPaint);
        appPaint.addListener((a, b, c) -> update());
    }

    public void setStroke(int index, AppPaint appPaint) {
        strokeArray.set(index, appPaint);
        appPaint.addListener((a, b, c) -> update());
    }

    public void addFill(int index, AppPaint appPaint) {
        fillArray.add(index, appPaint);
        appPaint.addListener((a, b, c) -> update());
    }

    public void addStroke(int index, AppPaint appPaint) {
        strokeArray.add(index, appPaint);
        appPaint.addListener((a, b, c) -> update());
    }

    public void addFill(AppPaint appPaint) {
        fillArray.add(appPaint);
        appPaint.addListener((a, b, c) -> update());
    }

    public void addStroke(AppPaint appPaint) {
        strokeArray.add(appPaint);
        appPaint.addListener((a, b, c) -> update());
    }

    public ObservableList<AppPaint> getFillArray() {
        return fillArray;
    }

    public ObservableList<AppPaint> getStrokeArray() {
        return strokeArray;
    }

    public void removeAllFills() {
        fillArray.clear();
    }

    public void removeAllStrokes() {
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
        double width = strokeWidth.get();
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


    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray fills=new JSONArray();
        JSONArray strokes=new JSONArray();
        fillArray.forEach(appPaint -> fills.put(appPaint.toJSON()));
        strokeArray.forEach(appPaint -> strokes.put(appPaint.toJSON()));
        jsonObject.put("fillColorFX", fillColorFX);
        jsonObject.put("strokeColorFX", strokeColorFX);
        jsonObject.put("strokeWidthFX", strokeWidthFX);
        jsonObject.put("strokeWidth", strokeWidth.get());
        jsonObject.put("fillArray", fills);
        jsonObject.put("strokeArray", strokes);
        return jsonObject;
    }

    public void setFromJSON(JSONObject jsonObject){
        removeAllFills();
        removeAllStrokes();
        jsonObject.getJSONArray("fillArray").forEach(fill->addFill(AppPaint.parseJSON((JSONObject) fill)));
        jsonObject.getJSONArray("strokeArray").forEach(stroke->addStroke(AppPaint.parseJSON((JSONObject) stroke)));
        setStrokeWidth(jsonObject.getDouble("strokeWidth"));
    }


}