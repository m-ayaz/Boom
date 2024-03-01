package com.boom.panels.chart;

import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.icons.MinusSignIcon;
import com.boom.icons.PlusSignIcon;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;

public  class DataField extends GridPane {

    protected TextArea xValueArea = new TextArea();
    protected TextArea yValueArea = new TextArea();
    protected Button addButton = new Button();
    protected Button removeButton = new Button();
    protected Label emptySpace = new Label("Add new data");

    DataField(ObservableList<Node> dataFields, AppSeries appSeries, int dataIndex) {

        setGraphics();

        addRow(0, new HBox(xValueArea, yValueArea), removeButton);
        addRow(1, emptySpace, addButton);

        addButton.setOnMouseEntered(mouseEvent -> emptySpace.setVisible(true));
        addButton.setOnMouseExited(mouseEvent -> emptySpace.setVisible(false));


        removeButton.setOnAction(event -> {
            appSeries.removeData(dataFields.indexOf(this) - 1);
            dataFields.remove(this);
        });

        emptySpace.setVisible(false);

        xValueArea.setText(String.valueOf(appSeries.getData(dataIndex)[0]));
        yValueArea.setText(String.valueOf(appSeries.getData(dataIndex)[1]));

        xValueArea.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                xValueArea.setText(String.valueOf(appSeries.getData(dataFields.indexOf(this) - 1)[0]));
            }
        });

        yValueArea.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                yValueArea.setText(String.valueOf(appSeries.getData(dataFields.indexOf(this) - 1)[1]));
            }
        });

        xValueArea.setOnKeyTyped(keyEvent -> {
            try {
                appSeries.modifyData(dataFields.indexOf(this) - 1, Double.parseDouble(xValueArea.getText()), appSeries.getData(dataFields.indexOf(this) - 1)[1]);
            } catch (NumberFormatException e) {
                print(e);
            }
        });


        yValueArea.setOnKeyTyped(keyEvent -> {
            try {
                appSeries.modifyData(dataFields.indexOf(this) - 1, appSeries.getData(dataFields.indexOf(this) - 1)[0], Double.parseDouble(yValueArea.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });


        addButton.setOnAction(event -> {
            appSeries.addData(dataFields.indexOf(this), appSeries.getData(dataFields.indexOf(this) - 1)[0], appSeries.getData(dataFields.indexOf(this) - 1)[1]);
            dataFields.add(dataFields.indexOf(this) + 1, new DataField(dataFields, appSeries, dataFields.indexOf(this)));
        });
    }


    void setGraphics() {
        setCustomSize(xValueArea, 100, 40);
        setCustomSize(yValueArea, 100, 40);
        setCustomSize(removeButton, 40, 40);
        setCustomSize(addButton, 40, 40);
        addButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

}
