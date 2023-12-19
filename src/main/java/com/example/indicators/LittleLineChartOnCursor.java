package com.example.indicators;

import com.example.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import static com.example.tools.Tools.setCustomSize;

public class LittleLineChartOnCursor extends LineChart<Number,Number> implements LittleIndicatorStructure {

    //todo set up background color to white later.
    public LittleLineChartOnCursor() {
        super(new NumberAxis(),new NumberAxis());
        getData().add(new XYChart.Series<>());
        getData().get(0).getData().add(new XYChart.Data<>(1,1));
        getData().get(0).getData().add(new XYChart.Data<>(2,4));
        getData().get(0).getData().add(new XYChart.Data<>(3,2));
        getData().get(0).getData().add(new XYChart.Data<>(4,3));
        getXAxis().setTickLabelsVisible(false);
        getYAxis().setTickLabelsVisible(false);
        setLegendVisible(false);
        setVisible(false);
        setCustomSize(this,100,100);
    }

    @Override
    public void show(double centerX, double centerY) {
        setVisible(true);
        setTranslateX(centerX);
        setTranslateY(centerY);
    }
    @Override
    public void hide() {
        setVisible(false);
    }
}
