package com.example.tools;


import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.stream.Collectors;

public class TeXConversion {

    public static String colorAsStroke(Color color, int tabIndent) {
        if (color != null) {
            return ("\n" + "\t".repeat(tabIndent) + "draw = {rgb,255:red,%d;green,%d;blue,%d},\n" + "\t".repeat(tabIndent) + "draw opacity = %f").formatted(Math.round(color.getRed() * 255), Math.round(color.getGreen() * 255), Math.round(color.getBlue() * 255), color.getOpacity());
        } else {
            return "";
        }
    }

    public static String colorAsFill(Color color, int tabIndent) {
        if (color != null) {
            return ("\n" + "\t".repeat(tabIndent) + "fill = {rgb,255:red,%d;green,%d;blue,%d},\n" + "\t".repeat(tabIndent) + "fill opacity = %f").formatted(Math.round(color.getRed() * 255), Math.round(color.getGreen() * 255), Math.round(color.getBlue() * 255), color.getOpacity());
        } else {
            return "";
        }
    }

    public static <T> String tickMarks(ObservableList<Axis.TickMark<T>> axisTickMarks, int tabIndent){
        return "\n"+"\t".indent(tabIndent)+"xtick = {"+axisTickMarks.stream().map(tick->tick.getValue().toString()).collect(Collectors.joining(","))+"}";
    }

    public static String areaChartSeriesData(XYChart.Series<?, ?> series, int tabIndent, boolean isDataSorted) {
        if (isDataSorted) {
            return "\n" + "\t".repeat(tabIndent) + "coordinates{" + series.getData().sorted().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()) + "}\\closedcycle";
        } else {
            return "\n" + "\t".repeat(tabIndent) + "coordinates{" + series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()) + "}\\closedcycle";
        }
    }

    public static String lineChartSeriesData(XYChart.Series<?, ?> series, int tabIndent, boolean isDataSorted) {
        if (isDataSorted) {
            return "\n" + "\t".repeat(tabIndent) + "coordinates{" + series.getData().sorted().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()) + "}";
        } else {
            return "\n" + "\t".repeat(tabIndent) + "coordinates{" + series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()) + "}";
        }
    }

    public static String scatterChartSeriesData(XYChart.Series<?, ?> series, int tabIndent, boolean isDataSorted) {
        if (isDataSorted) {
            return "\n" + "\t".repeat(tabIndent) + "coordinates{" + series.getData().sorted().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()) + "}";
        } else {
            return "\n" + "\t".repeat(tabIndent) + "coordinates{" + series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()) + "}";
        }
    }

}