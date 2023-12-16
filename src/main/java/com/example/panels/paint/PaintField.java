package com.example.panels.paint;

import com.example.apppaints.AppLinearGradient;
import com.example.apppaints.AppPaint;
import com.example.apppaints.AppStop;
import com.example.icons.MinusSignIcon;
import com.example.icons.PlusSignIcon;
import com.example.styles.BackgroundsProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.setSize;

public class PaintField extends GridPane {

    Button addColorButton = new Button("color");
    Button addLinearGradientButton = new Button("lin-grad");
    Button removeButton = new Button();

    ColorPicker settingsButton = new ColorPicker(); //todo this one needs an icon of cog  :D

    Label infoLabel=new Label("info");


    Label emptySpace = new Label();

    PaintField(ObservableList<Node> paintsPaneChildren, BackgroundsProperty backgroundsProperty, AppPaint appPaint) {

        super();



//        setAddColorButtonBehavior(paintsPaneChildren, backgroundsProperty);

//        setRemoveButtonBehavior(paintsPaneChildren, backgroundsProperty,appStop);

//        setColorPickerBehavior(appStop);


        addRow(0, infoLabel,settingsButton,removeButton);
        addRow(1, emptySpace, addColorButton,addLinearGradientButton);

        setGraphics(30, 30);


    }


    void setAddColorButtonBehavior(ObservableList<Node> stopsPaneChildren, AppLinearGradient appLinearGradient) {
//        addColorButton.setOnAction(event -> {
//            AppStop newAppStop = new AppStop(Double.parseDouble(proportion.getText()), colorPicker.getValue());
//            StopField newStopField = new StopField(stopsPaneChildren, appLinearGradient, newAppStop);
//            appLinearGradient.addAppStop(stopsPaneChildren.indexOf(this), newAppStop);
//            stopsPaneChildren.add(stopsPaneChildren.indexOf(this) + 1, newStopField);
//        });
//        addButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add new stop"));
//        addButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setRemoveButtonBehavior(ObservableList<Node> stopsPaneChildren, AppLinearGradient appLinearGradient,AppStop appStop) {
//        removeButton.setOnAction(event -> {
//            appLinearGradient.removeAppStop(appStop);
//            stopsPaneChildren.remove(this);
//        });
//        removeButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Remove this stop"));
//        removeButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setColorPickerBehavior( AppStop appStop) {
//        colorPicker.setValue(appStop.color.get());
//        colorPicker.setOnAction(event -> appStop.color.set(colorPicker.getValue()));
    }

    void setGraphics(double width, double height) {
//        setSize(addButton, width, height);
//        setSize(removeButton, width, height);
//        addButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
//        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

}
