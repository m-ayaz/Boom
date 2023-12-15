package com.example.panels.paint;

import com.example.apppaints.AppColor;
import com.example.apppaints.AppPaint;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;

public class ColorField extends PaintField {

    ColorPicker colorPicker =new ColorPicker();



    public ColorField(ObservableList<AppPaint> fillArray, ObservableList<Node> children, AppColor appColor){
//        colorPicker.setValue(appColor);
        addRow(0, colorPicker, removeButton);
        addRow(1, emptySpace, new HBox(solidColorButton,linearGradientButton,radialGradientButton,imagePatternButton));
    }

}
