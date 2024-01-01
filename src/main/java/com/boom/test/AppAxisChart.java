package com.boom.test;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;

public class AppAxisChart extends AreaChart<Number,Number> {
    public AppAxisChart(Axis<Number> axis, Axis<Number> axis1) {
        super(axis, axis1);
    }
}
