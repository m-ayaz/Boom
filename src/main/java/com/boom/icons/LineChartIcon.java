package com.boom.icons;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import static com.boom.tools.Tools.setCustomSize;

public  class LineChartIcon extends LineChart<Number,Number> {
    public LineChartIcon(double width,double height) {

        super(new NumberAxis(),new NumberAxis());

        XYChart.Series<Number,Number> series=new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(1,1));
        series.getData().add(new XYChart.Data<>(2,4));
        series.getData().add(new XYChart.Data<>(3,2));
        series.getData().add(new XYChart.Data<>(4,3));

        getXAxis().setTickLabelsVisible(false);
        getXAxis().setTickMarkVisible(false);
        getYAxis().setTickLabelsVisible(false);
        getYAxis().setTickMarkVisible(false);

        getData().add(series);
        setLegendVisible(false);
        setStyle("-fx-padding: -5pt;");

        setCustomSize(this,width,height);
    }
}
