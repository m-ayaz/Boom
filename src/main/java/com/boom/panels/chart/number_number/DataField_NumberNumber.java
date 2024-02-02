package com.boom.panels.chart.number_number;

import com.boom.structures.abstracts.AppXYChart;
import com.boom.structures.abstracts.DataFieldBase;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

import static com.boom.tools.Tools.print;

@SuppressWarnings("unchecked")
public  class DataField_NumberNumber extends DataFieldBase<Number,Number> {


    DataField_NumberNumber(ObservableList<Node> children, AppXYChart<Number, Number> appXYChart, XYChart.Series<Number, Number> series, XYChart.Data<Number, Number> data) {
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
                data.setYValue(Double.valueOf(yValueArea.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });


        addButton.setOnAction(event -> {
            int seriesIndex = ((XYChart<Number,Number>) appXYChart.getStyleableNode()).getData().indexOf(series);
            XYChart.Data<Number, Number> newData = appXYChart.addData(data.getXValue(), data.getYValue(), seriesIndex, children.indexOf(this));
            DataField_NumberNumber newDataField = new DataField_NumberNumber(children, appXYChart, series, newData);
            children.add(children.indexOf(this) + 1, newDataField);
        });
    }

}
