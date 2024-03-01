package com.boom.structures.abstracts;

import com.boom.appcharts.AppAxisChartWrapper;
import com.boom.icons.MinusSignIcon;
import com.boom.icons.PlusSignIcon;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import static com.boom.tools.Tools.setCustomSize;

public abstract class DataFieldBase<T1,T2> extends GridPane {

    protected  TextArea xValueArea = new TextArea();
    protected  TextArea yValueArea = new TextArea();

    protected Button addButton = new Button();
    protected Button removeButton = new Button();
    protected Label emptySpace = new Label("Add new data");

    protected XYChart.Data<T1,T2> data;

    protected AppAxisChartWrapper appAxisChartWrapper;

    public DataFieldBase(ObservableList<Node> children, AppAxisChartWrapper appAxisChartWrapper, XYChart.Series<T1,T2> series, XYChart.Data<T1,T2> data){

        this.data = data;

        this.appAxisChartWrapper = appAxisChartWrapper;

        setGraphics();

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

        addButton.setOnMouseEntered(mouseEvent -> emptySpace.setVisible(true));
        addButton.setOnMouseExited(mouseEvent -> emptySpace.setVisible(false));


        removeButton.setOnAction(event -> {
            series.getData().remove(data);
            children.remove(this);
        });

        emptySpace.setVisible(false);
    }

    void setGraphics() {
        setCustomSize(xValueArea, 100, 40);
        setCustomSize(yValueArea, 100, 40);
        setCustomSize(removeButton, 40, 40);

        addButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

}
