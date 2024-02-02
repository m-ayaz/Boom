package com.boom.indicators;

import com.boom.structures.interfaces.LittleIndicatorStructure;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;

public  class LittleAreaChartOnCursor extends AreaChart<Number,Number> implements LittleIndicatorStructure {

    //todo set up background color to white later.
    public LittleAreaChartOnCursor() {
        super(new NumberAxis(), new NumberAxis());
        getData().add(new Series<>());
        getData().get(0).getData().add(new Data<>(1, 1));
        getData().get(0).getData().add(new Data<>(2, 4));
        getData().get(0).getData().add(new Data<>(3, 2));
        getData().get(0).getData().add(new Data<>(4, 3));
        getXAxis().setTickLabelsVisible(false);
        getYAxis().setTickLabelsVisible(false);
        setLegendVisible(false);
        setVisible(false);
        setPrefWidth(100);
        setPrefHeight(100);
        setMaxWidth(100);
        setMaxHeight(100);
        setMinWidth(100);
        setMinHeight(100);
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
