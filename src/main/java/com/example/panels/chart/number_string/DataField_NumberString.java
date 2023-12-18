package com.example.panels.chart.number_string;

import com.example.structures.abstracts.AppXYChart;
import com.example.structures.abstracts.DataFieldBase;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

import static com.example.tools.Tools.print;

public class DataField_NumberString extends DataFieldBase<Number,String> {

    DataField_NumberString(ObservableList<Node> children, AppXYChart<Number, String> appXYChart, XYChart.Series<Number, String> series, XYChart.Data<Number, String> data) {
        super(children, appXYChart, series, data);


        xValueArea.setOnKeyTyped(keyEvent -> {
            try {
                data.setXValue(Double.valueOf(xValueArea.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });


        yValueArea.setOnKeyTyped(keyEvent -> {
            try {
                data.setYValue(yValueArea.getText());
            } catch (NumberFormatException e) {
                print(e);
            }
        });

        addButton.setOnAction(event -> {
            int seriesIndex = ((XYChart) appXYChart.getRegion()).getData().indexOf(series);
            XYChart.Data<Number, String> newData = appXYChart.addData(data.getXValue(), data.getYValue(), seriesIndex, children.indexOf(this));
            DataField_NumberString newDataField = new DataField_NumberString(children, appXYChart, series, newData);
            children.add(children.indexOf(this) + 1, newDataField);
        });
    }

}