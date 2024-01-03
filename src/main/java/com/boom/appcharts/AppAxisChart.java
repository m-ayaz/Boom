package com.boom.appcharts;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppAxisChart extends Pane {

//    ObservableList<Serializable> xData= FXCollections.observableList(new ArrayList<>());
//    ObservableList<Serializable> yData= FXCollections.observableList(new ArrayList<>());

    Pane legendsContainer=new Pane();
    ObservableList<Text> seriesLegendList =FXCollections.observableList(new ArrayList<>());
    ObservableList<Polygon> seriesPlotAreaList = FXCollections.observableList(new ArrayList<>());
    ObservableList<Polyline> seriesPlotLineList = FXCollections.observableList(new ArrayList<>());
    ObservableList<Group> seriesMarkersList = FXCollections.observableList(new ArrayList<>());
    Group xGridLines = new Group();
    Group yGridLines = new Group();
    Pane plotRegion = new Pane();

    ObservableList<Series> seriesList = FXCollections.observableList(new ArrayList<>());

    SimpleIntegerProperty numberOfUnNumerableXData = new SimpleIntegerProperty(0);
    SimpleIntegerProperty numberOfUnNumerableYData = new SimpleIntegerProperty(0);

    SimpleBooleanProperty isXDataNumerable = new SimpleBooleanProperty();
    SimpleBooleanProperty isYDataNumerable = new SimpleBooleanProperty();

    List<Serializable> xData=new ArrayList<>();
    List<Serializable> yData=new ArrayList<>();

    public AppAxisChart() {

//        XYChart.Data<Number,Number> x;
//        x.

//        isXDataNumerable.bi
        isXDataNumerable.bind(numberOfUnNumerableXData.isEqualTo(0));
        isYDataNumerable.bind(numberOfUnNumerableYData.isEqualTo(0));
    }

    public void addSeries() {
        seriesList.add(new Series());
        Polygon seriesPlotArea=new Polygon();
        Polyline seriesPlotLine=new Polyline();
        Group seriesMarkers=new Group();
        seriesPlotAreaList.add(seriesPlotArea);
        seriesPlotLineList.add(seriesPlotLine);
        seriesMarkersList.add(seriesMarkers);
        seriesLegendList.add(new Text());
        plotRegion.getChildren().addAll(seriesPlotArea,seriesPlotLine,seriesMarkers);
    }

//    public void removeSeries(int seriesIndex){
//        seriesList.remove(seriesIndex);
//    }
//
//    public void removeSeries(Series series){
//        seriesList.remove(series);
//    }


//    public void addSeries(int index){
//
//    }

//    public void addData(int seriesIndex, String x, String y) {
//        seriesList.get(seriesIndex).addData(new Data(x, y));
//        try{
//            Double.parseDouble(x);
//        }catch (Exception e){
//            numberOfUnNumerableXData.set(numberOfUnNumerableXData.get() + 1);
//        }
//        try{
//            Double.parseDouble(y);
//        }catch (Exception e){
//            numberOfUnNumerableYData.set(numberOfUnNumerableYData.get() + 1);
//        }
////        switch (x.getClass().getSimpleName()) {
////            case "Double" -> {
////            }
////            case "String" -> numberOfUnNumerableXData.set(numberOfUnNumerableXData.get() + 1);
////            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
////        }
////        switch (y.getClass().getSimpleName()) {
////            case "Double" -> {
////            }
////            case "String" -> numberOfUnNumerableYData.set(numberOfUnNumerableYData.get() + 1);
////            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
////        }
//
//    }

    public void addData(int seriesIndex, int dataIndex, String x, String y) {
        seriesList.get(seriesIndex).addData(dataIndex, new Data(x, y));
        try{
            Double.parseDouble(x);
        }catch (Exception e){
            numberOfUnNumerableXData.set(numberOfUnNumerableXData.get() + 1);
        }
        try{
            Double.parseDouble(y);
        }catch (Exception e){
            numberOfUnNumerableYData.set(numberOfUnNumerableYData.get() + 1);
        }

    }

    //    short XDecimalPoint=1,YDecimalPoint=1;
//
//    public void setXDecimalPoint(short decimalPoint){
//
//    }
//
//    public void setYDecimalPoint(short decimalPoint){
//
//    }
    boolean isAutoSetXTicks = true, isAutoSetYTicks = true,isXNumeric,isYNumeric;

    public void setAutoSetXTicks(boolean isAutoSetXTicks) {

    }

    public void setAutoSetYTicks(boolean isAutoSetYTicks) {

    }

    public void setXNumeric(boolean isXNumeric) {
        if (isXDataNumerable.get()) {

        }
    }

    public void setYNumeric(boolean isYNumeric) {

    }

    void updatePlot() {
        xData.clear();
        yData.clear();
        if(isXNumeric){
//            xData.
        }
    }

    double plotWidth, plotHeight;

    double minXData=Double.POSITIVE_INFINITY,
            maxXData=Double.NEGATIVE_INFINITY,
            minYData=Double.POSITIVE_INFINITY,
            maxYData=Double.NEGATIVE_INFINITY;


}

