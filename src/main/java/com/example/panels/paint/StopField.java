package com.example.panels.paint;

import com.example.icons.MinusSignIcon;
import com.example.icons.PlusSignIcon;
import com.example.apppaints.AppLinearGradient;
import com.example.apppaints.AppStop;
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

public class StopField extends GridPane {

    Button addButton = new Button();
    Button removeButton = new Button();

    ColorPicker colorPicker = new ColorPicker();

    TextField proportion = new TextField();

    Label emptySpace = new Label();

    StopField(ObservableList<Node> stopsPaneChildren, AppLinearGradient appLinearGradient, AppStop appStop) {

        super();


        setProportionBehavior(appStop);

        setAddButtonBehavior(stopsPaneChildren, appLinearGradient);

        setRemoveButtonBehavior(stopsPaneChildren, appLinearGradient,appStop);

        setColorPickerBehavior(appStop);


        addRow(0, new HBox(colorPicker, proportion), removeButton);
        addRow(1, emptySpace, addButton);

        setGraphics(30, 30);


    }

    void setProportionBehavior(AppStop appStop) {
        proportion.setText(appStop.offset.getValue().toString());
        proportion.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                proportion.setText(appStop.offset.getValue().toString());
            }
        });
        proportion.setOnKeyTyped(keyEvent -> {
            try {
                appStop.offset.set(Double.parseDouble(proportion.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setAddButtonBehavior(ObservableList<Node> stopsPaneChildren, AppLinearGradient appLinearGradient) {
        addButton.setOnAction(event -> {
            AppStop newAppStop = new AppStop(Double.parseDouble(proportion.getText()), colorPicker.getValue());
            StopField newStopField = new StopField(stopsPaneChildren, appLinearGradient, newAppStop);
            appLinearGradient.addAppStop(stopsPaneChildren.indexOf(this), newAppStop);
            stopsPaneChildren.add(stopsPaneChildren.indexOf(this) + 1, newStopField);
        });
        addButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add new stop"));
        addButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setRemoveButtonBehavior(ObservableList<Node> stopsPaneChildren, AppLinearGradient appLinearGradient,AppStop appStop) {
        removeButton.setOnAction(event -> {
            appLinearGradient.removeAppStop(appStop);
            stopsPaneChildren.remove(this);
        });
        removeButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Remove this stop"));
        removeButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setColorPickerBehavior( AppStop appStop) {
        colorPicker.setValue(appStop.color.get());
        colorPicker.setOnAction(event -> appStop.color.set(colorPicker.getValue()));
    }

    void setGraphics(double width, double height) {
        setSize(addButton, width, height);
        setSize(removeButton, width, height);
        addButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

}
