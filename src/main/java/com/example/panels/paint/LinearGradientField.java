package com.example.panels.paint;

import com.example.styles.AppLinearGradient;
import com.example.styles.AppPaint;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import static com.example.tools.Tools.print;

public class LinearGradientField extends PaintField {

//    List<ColorPicker> colors =new ArrayList<>();

//    AppLinearGradient appLinearGradient;

    ColorPicker stopColor = new ColorPicker();

    Button addStopButton = new Button("Add stop");
    Button removeStopButton = new Button("remove stop");

    ComboBox<String> stopsSelector = new ComboBox<>();

//    List<Double> stopsPosX=new ArrayList<>();
//    List<Double> stopsPosY=new ArrayList<>();

    TextField startX = new TextField();
    TextField startY = new TextField();

    TextField endX = new TextField();
    TextField endY = new TextField();
    TextField stopProportion = new TextField();

    CheckBox isProportional = new CheckBox("isProportional");


    Label emptySpace = new Label();

    HBox holder = new HBox();


    public LinearGradientField(ObservableList<AppPaint> fillArray, ObservableList<Node> children, AppLinearGradient appLinearGradient) {

//        this.appLinearGradient = appLinearGradient;

        updateStopsSelectorItems(appLinearGradient);

        startX.setText(appLinearGradient.startX.getValue().toString());
        startY.setText(appLinearGradient.startY.getValue().toString());
        endX.setText(appLinearGradient.endX.getValue().toString());
        endY.setText(appLinearGradient.endY.getValue().toString());

        ObservableList<Stop> stops = appLinearGradient.stops;

        stopsSelector.setOnAction(event -> {

            int index = stopsSelector.getItems().indexOf(stopsSelector.getValue());
            if(index!=-1) {
                stopProportion.setText(String.valueOf(stops.get(index).getOffset()));
                stopColor.setValue(stops.get(index).getColor());
            }
        });

        stopColor.setOnAction(event -> {
            int index=stopsSelector.getItems().indexOf(stopsSelector.getValue());
            if(index!=-1){
                stops.set(index,new Stop(stops.get(index).getOffset(),stopColor.getValue()));
            }
        });

        setGriding();

        linearGradientButton.setOnAction(event -> {

        });

        removeStopButton.setOnAction(event -> {
            int index = stopsSelector.getItems().indexOf(stopsSelector.getValue());
            if (index != -1) {
                appLinearGradient.stops.remove(index);
                updateStopsSelectorItems(appLinearGradient);
            }
        });

        removeButton.setOnAction(event -> {
            fillArray.remove(appLinearGradient);
            children.remove(this);
        });

    }

    void setGriding() {
        GridPane temp1 = new GridPane(), temp2 = new GridPane();
        temp1.addRow(0, stopsSelector, stopColor, stopProportion);
        temp2.addRow(0, startX, endX, isProportional, addStopButton, removeStopButton);
        temp2.addRow(1, startY, endY);
//        temp1.addRow(1, colorsSelector,stopColor,stopProportion);
//        temp1.addRow(0,startColorPosX,startColorPosY);
//        temp1.addRow(1,startColor);
//        temp2.addRow(0,endColorPosX,endColorPosY);
//        temp2.addRow(1,endColor);
        holder.getChildren().addAll(temp1, new Rectangle(), temp2);
        addRow(0, holder, removeButton);
        addRow(1, emptySpace, new HBox(solidColorButton, linearGradientButton, radialGradientButton, imagePatternButton));
    }

    void setGraphics() {

    }

    void updateStopsSelectorItems(AppLinearGradient appLinearGradient) {
        stopsSelector.getItems().clear();
        for (int j = 0; j < appLinearGradient.stops.size(); j++) {
            stopsSelector.getItems().add("Stop#" + (j + 1));
        }
    }

}
