package com.example.panels.paint;

import com.example.styles.AppColor;
import com.example.styles.AppPaint;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ColorField extends PaintField {

    ColorPicker colorPicker =new ColorPicker();



    public ColorField(ObservableList<AppPaint> fillArray, ObservableList<Node> children, AppColor appColor){
//        colorPicker.setValue(appColor);
        addRow(0, colorPicker, removeButton);
        addRow(1, emptySpace, new HBox(solidColorButton,linearGradientButton,radialGradientButton,imagePatternButton));
    }

}
