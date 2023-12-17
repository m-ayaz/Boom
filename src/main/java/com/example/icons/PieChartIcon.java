package com.example.icons;

import javafx.scene.chart.PieChart;

import static com.example.tools.Tools.setCustomSize;

public class PieChartIcon extends PieChart {
    public PieChartIcon() {

        getData().add(new Data("",2));
        getData().add(new Data("",3));
        getData().add(new Data("",2.5));
        getData().add(new Data("",4));

        setLegendVisible(false);
        setStyle("-fx-padding: -5pt;");

        int size=60;

        setCustomSize(this,size,size);
    }
}
