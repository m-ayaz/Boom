package com.boom.panels.chart;

//import com.boom.structures.abstracts.AppXYChart;
import com.boom.appcharts.AppAxisChartWrapper;
import com.boom.structures.abstracts.DataFieldBase;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

import static com.boom.tools.Tools.print;

@SuppressWarnings("unchecked")
public  class DataField extends DataFieldBase<Number,Number> {


    DataField(ObservableList<Node> children, AppAxisChartWrapper appAxisChartWrapper, XYChart.Series<Number, Number> series, XYChart.Data<Number, Number> data) {
        super(children, appAxisChartWrapper, series, data);


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
            int seriesIndex = ((XYChart<Number,Number>) appXYChart.styleableNode).getData().indexOf(series);
            XYChart.Data<Number, Number> newData = appXYChart.addData(data.getXValue(), data.getYValue(), seriesIndex, children.indexOf(this));
            DataField newDataField = new DataField(children, appXYChart, series, newData);
            children.add(children.indexOf(this) + 1, newDataField);
        });
    }

}
