package com.boom.panels.chart.string_number;

import com.boom.structures.abstracts.AppXYChart;
import com.boom.structures.abstracts.DataFieldBase;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

import static com.boom.tools.Tools.print;

public class DataField_StringNumber extends DataFieldBase<String,Number> {

    DataField_StringNumber(ObservableList<Node> children, AppXYChart<String, Number> appXYChart, XYChart.Series<String, Number> series, XYChart.Data<String, Number> data) {
        super(children, appXYChart, series, data);


        xValueArea.setOnKeyTyped(keyEvent -> {
            try {
                data.setXValue(xValueArea.getText());
            } catch (NumberFormatException e) {
                print(e);
            }
        });


        yValueArea.setOnKeyTyped(keyEvent -> {
            try {
                data.setYValue(Double.valueOf(yValueArea.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });

        addButton.setOnAction(event -> {
            int seriesIndex = ((XYChart) appXYChart.getNode()).getData().indexOf(series);
            XYChart.Data<String, Number> newData = appXYChart.addData(data.getXValue(), data.getYValue(), seriesIndex, children.indexOf(this));
            DataField_StringNumber newDataField = new DataField_StringNumber(children, appXYChart, series, newData);
            children.add(children.indexOf(this) + 1, newDataField);
        });
    }

}
