package com.example.icons;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;

import static com.example.tools.Tools.setSize;

public class AreaChartIcon extends AreaChart<Number,Number> {
    public AreaChartIcon() {

        super(new NumberAxis(),new NumberAxis());

        Series<Number,Number> series=new Series<>();
        series.getData().add(new Data<>(1,1));
        series.getData().add(new Data<>(2,4));
        series.getData().add(new Data<>(3,2));
        series.getData().add(new Data<>(4,3));

        getXAxis().setTickLabelsVisible(false);
        getXAxis().setTickMarkVisible(false);
        getYAxis().setTickLabelsVisible(false);
        getYAxis().setTickMarkVisible(false);

        getData().add(series);
        setLegendVisible(false);
        setStyle("-fx-padding: -5pt;");

        int size=70;

        setSize(this,size,size);
    }
}
