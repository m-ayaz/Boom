package com.boom.icons;

import javafx.scene.chart.PieChart;

import static com.boom.tools.Tools.setCustomSize;

public  class PieChartIcon extends PieChart {
    public PieChartIcon(double width,double height) {

        getData().add(new Data("",2));
        getData().add(new Data("",3));
        getData().add(new Data("",2.5));
        getData().add(new Data("",4));

        setLegendVisible(false);
        setStyle("-fx-padding: -5pt;");

        setCustomSize(this,width,height);
    }
}
