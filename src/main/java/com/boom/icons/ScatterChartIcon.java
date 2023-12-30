package com.boom.icons;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;

import static com.boom.tools.Tools.setCustomSize;

public final class ScatterChartIcon extends ScatterChart<Number,Number> {
    public ScatterChartIcon(double width,double height) {

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

        setCustomSize(this,width,height);

    }
}
