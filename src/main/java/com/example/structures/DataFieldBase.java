package com.example.structures;

import com.example.icons.MinusSignIcon;
import com.example.icons.PlusSignIcon;
import com.example.panels.chart.number_number.DataField_NumberNumber;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.example.tools.Tools.setSize;

public abstract class DataFieldBase<T1,T2> extends GridPane {

    protected  TextArea xValueArea = new TextArea();
    protected  TextArea yValueArea = new TextArea();

    protected Button addButton = new Button();
    protected Button removeButton = new Button();
    protected Label emptySpace = new Label("Add new data");

    protected XYChart.Data<T1,T2> data;

    protected AppXYChart<T1,T2> appXYChart;

    public DataFieldBase(ObservableList<Node> children, AppXYChart<T1,T2> appXYChart, XYChart.Series<T1,T2> series, XYChart.Data<T1,T2> data){

        this.data = data;

        this.appXYChart = appXYChart;

        setGraphics(40, 100, 100);

        xValueArea.setText(data.getXValue().toString());
        yValueArea.setText(data.getYValue().toString());


        xValueArea.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                xValueArea.setText(data.getXValue().toString());
            }
        });

        yValueArea.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                yValueArea.setText(data.getYValue().toString());
            }
        });

        addRow(0, new HBox(xValueArea, yValueArea), removeButton);
        addRow(1, emptySpace, addButton);
        setSize(addButton, 40, 40);
        addButton.setOnMouseEntered(mouseEvent -> emptySpace.setVisible(true));
        addButton.setOnMouseExited(mouseEvent -> emptySpace.setVisible(false));
        addButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
        setSize(removeButton, 40, 40);
        removeButton.setOnAction(event -> {
            series.getData().remove(data);
            children.remove(this);
        });

        emptySpace.setVisible(false);
    }

    void setGraphics(double height, double xValueAreaWidth, double yValueAreaWidth) {
        setSize(xValueArea, xValueAreaWidth, height);
        setSize(yValueArea, yValueAreaWidth, height);
    }

}
