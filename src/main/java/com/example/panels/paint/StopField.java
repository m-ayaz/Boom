package com.example.panels.paint;

import com.example.structures.abstracts.AppGradient;
import com.example.apppaints.AppStop;
import com.example.icons.MinusSignIcon;
import com.example.icons.PlusSignIcon;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.setCustomSize;

public class StopField extends GridPane {

    Button addButton = new Button();
    Button removeButton = new Button();

//    ColorPicker colorPicker = new ColorPicker();

    Button chooseColorButton=new Button();

    Rectangle chooseColorButtonColor=new Rectangle(10,10);

    TextField proportion = new TextField();

    Label emptySpace = new Label();

//    AppColor appColor;

    StopField(ObservableList<Node> stopsPaneChildren, AppGradient appGradient, AppStop appStop) {

        super();


        setProportionBehavior(appStop);

        setAddButtonBehavior(stopsPaneChildren, appGradient,appStop);

        setRemoveButtonBehavior(stopsPaneChildren, appGradient,appStop);

        setChooseColorButtonBehavior(appStop);

        addRow(0, new HBox(chooseColorButton, proportion), removeButton);
        addRow(1, emptySpace, addButton);

        setGraphics(30, 30);

    }

    Label chooseColorButtonLabel=new Label();

    void setChooseColorButtonBehavior(AppStop appStop){
        appStop.appColor.getPaintProperty().addListener((a,b,c)->{
            chooseColorButtonColor.setFill(c);
            chooseColorButtonLabel.setText(c.toString());
        });
        chooseColorButton.setOnAction(event -> {
            ColorManagementPanel colorManagementPanel=new ColorManagementPanel(appStop.appColor);
            colorManagementPanel.show(Stage.getWindows().get(Stage.getWindows().size()-1));
        });
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

    void setAddButtonBehavior(ObservableList<Node> stopsPaneChildren, AppGradient appGradient,AppStop appStop) {
        addButton.setOnAction(event -> {
            AppStop newAppStop = new AppStop(Double.parseDouble(proportion.getText()), (Color) appStop.appColor.getPaintProperty().get());
            StopField newStopField = new StopField(stopsPaneChildren, appGradient, newAppStop);
            appGradient.addAppStop(stopsPaneChildren.indexOf(this), newAppStop);
            stopsPaneChildren.add(stopsPaneChildren.indexOf(this) + 1, newStopField);
        });
        addButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add new stop"));
        addButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setRemoveButtonBehavior(ObservableList<Node> stopsPaneChildren, AppGradient appGradient,AppStop appStop) {
        removeButton.setOnAction(event -> {
            appGradient.removeAppStop(appStop);
            stopsPaneChildren.remove(this);
        });
        removeButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Remove this stop"));
        removeButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

//    void setColorPickerBehavior( AppStop appStop) {
//        colorPicker.setValue(appStop.color.get());
//        colorPicker.setOnAction(event -> appStop.color.set(colorPicker.getValue()));
//    }

    void setGraphics(double width, double height) {
        setCustomSize(addButton, width, height);
        setCustomSize(removeButton, width, height);
        addButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
        chooseColorButton.setGraphic(new HBox(chooseColorButtonColor,chooseColorButtonLabel));
    }

}
