package com.boom.structures.enums;


import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;

public enum NodeTypeEnum {

    LineChart_NN(LineChart.class.getName()+"_NN"),
    LineChart_NS(LineChart.class.getName()+"_NS"),
    LineChart_SN(LineChart.class.getName()+"_SN"),
    ScatterChart_NN(ScatterChart.class.getName()+"_NN"),
    ScatterChart_NS(ScatterChart.class.getName()+"_NS"),
    ScatterChart_SN(ScatterChart.class.getName()+"_SN"),
    BarChart_NS(BarChart.class.getName()+"_NS"),
    BarChart_SN(BarChart.class.getName()+"_SN"),
    AreaChart_NN(AreaChart.class.getName()+"_NN"),
    AreaChart_NS(AreaChart.class.getName()+"_NS"),
    AreaChart_SN(AreaChart.class.getName()+"_SN"),
    DynamicDragRectangle("drag");
//    Ellipse(javafx.scene.shape.Ellipse.class.getName()),
//    Rectangle(javafx.scene.shape.Rectangle.class.getName()),
//    Line(javafx.scene.shape.Line.class.getName()),
//    Arc(javafx.scene.shape.Arc.class.getName()),
//    Polygon(javafx.scene.shape.Polygon.class.getName()),
//    Polyline(javafx.scene.shape.Polyline.class.getName()),
//    QuadCurve(javafx.scene.shape.QuadCurve.class.getName()),
//    CubicCurve(javafx.scene.shape.CubicCurve.class.getName());
    private final String nodeType;

    NodeTypeEnum(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }
}