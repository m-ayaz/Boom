package com.boom.tools;


import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

import java.util.stream.Collectors;

public class SVGConversion {

    public static String getPreamble(double width,double height){
        return "<svg width=\"%f\" height=\"%f\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">".formatted(width,height);
    }

    public static String getEnding(){
        return "</svg>";
    }

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

    public static <T> String tickMarks(ObservableList<Axis.TickMark<T>> axisTickMarks, int tabIndent) {
        return "\n" + "\t".indent(tabIndent) + "xtick = {" + axisTickMarks.stream().map(tick -> tick.getValue().toString()).collect(Collectors.joining(",")) + "}";
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


//    static String stopToSVG(Stop stop, int tabIndent) {
//        return "\n" + "\t".repeat(tabIndent) + "<stop offset=\"%f\" stop-color=\"%s\" />".formatted(stop.getOffset(), stop.getColor().toString().replace("0x", "#"));
//    }
//
//    public static String paintToSVG(Paint paint, int tabIndent, String id) {
//
//
//
//        if(paint.getClass().getName().equals(PaintTypeEnum.Color.getPaintType())) {
//            return "\n"+"\t".repeat(tabIndent)+"<linearGradient id=\"%s\">  <stop stop-color=\"%s\"/> </linearGradient>".formatted(id,paint.toString().replace("0x", "#"));
//        }else if(paint.getClass().getName().equals(PaintTypeEnum.LinearGradient.getPaintType())) {
//            StringBuilder stringBuilder = new StringBuilder();
//            LinearGradient linearGradient=(LinearGradient) paint;
//            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<linearGradient id=\"%s\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" gradientUnits=\"%s\">".formatted(id,
//                    linearGradient.getStartX(),
//                    linearGradient.getStartY(),
//                    linearGradient.getEndX(),
//                    linearGradient.getEndY(),
//                    (linearGradient.isProportional() ? "objectBoundingBox" : "userSpaceOnUse")
//            ));
//            for (Stop stop : linearGradient.getStops()) {
//                stringBuilder.append(stopToSVG(stop,tabIndent+1));
//            }
//            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("</linearGradient>");
//            return stringBuilder.toString();
//        }else{
//            throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
//        }
//
//
////        return null;
//    }

//    public static String linearGradientToSVG(LinearGradient linearGradient, int tabIndent, String id) {
//        StringBuilder stringBuilder = new StringBuilder();
//
////        "<linearGradient id=\"yourcolor\">\n" +
////                "  <stop stop-color=\"#991132\"/>\n" +
////                "</linearGradient>"
//
//        stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<linearGradient id=\"%s\" x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" gradientUnits=\"%s\">".formatted(id,
//                linearGradient.getStartX(),
//                linearGradient.getStartY(),
//                linearGradient.getEndX(),
//                linearGradient.getEndY(),
//                (linearGradient.isProportional() ? "objectBoundingBox" : "userSpaceOnUse")
//        ));
//        for (Stop stop : linearGradient.getStops()) {
//            stringBuilder.append("\n").append("\t".repeat(tabIndent + 1)).append("<stop stop-color=\"%s\" offset=\"%f\"/>".formatted(stop.getColor().toString().replace("0x", "#"), stop.getOffset()));
//        }
//        stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("</linearGradient>");
//        return stringBuilder.toString();
//
//
////        return null;
//    }

//    public sta

//    public static String stopToSVG(Stop stop, int tabIndent) {
//        return "\n" + "\t".repeat(tabIndent) + "<stop" +
//                "\n" + "\t".repeat(tabIndent + 1) + "stop-color=\"%s\"".formatted(stop.getColor().toString().replace("0x", "#")) +
//                "\n" + "\t".repeat(tabIndent + 1) + "offset=\"%f\"".formatted(stop.getOffset()) +
//                "\n" + "\t".repeat(tabIndent) + "/>";
//    }

}











