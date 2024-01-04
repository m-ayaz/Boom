package com.boom.appcharts;


import com.boom.test.DataComparator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

// todo: disallow addition of duplicate series or data

public class AppAxisChart extends Pane {

    public ObservableList<ObservableList<AppData>> seriesList = FXCollections.observableList(new ArrayList<>());
    Pane legendsContainer = new Pane();
    ObservableList<Text> seriesLegendList = FXCollections.observableList(new ArrayList<>());
    public ObservableList<Polygon> seriesPlotAreaList = FXCollections.observableList(new ArrayList<>());
    public ObservableList<Polyline> seriesPlotLineList = FXCollections.observableList(new ArrayList<>());
    ObservableList<Group> seriesMarkersList = FXCollections.observableList(new ArrayList<>());
    Group xGridLines = new Group();
    Group yGridLines = new Group();
    Pane plotRegion = new Pane();
    DataComparator dataComparator = new DataComparator();

    public SimpleDoubleProperty plotRegionWidth = new SimpleDoubleProperty();
    public SimpleDoubleProperty plotRegionHeight = new SimpleDoubleProperty();


    boolean isAutoSetXTicks = true, isAutoSetYTicks = true, isXNumeric, isYNumeric;

    public SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty(0.1);
//    double plotWidth, plotHeight;
//    double minXData = Double.POSITIVE_INFINITY, maxXData = Double.NEGATIVE_INFINITY,
//            minYData = Double.POSITIVE_INFINITY, maxYData = Double.NEGATIVE_INFINITY;

    public AppAxisChart() {

        setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-border-width: 0px");

        plotRegion.setStyle("-fx-border-width: 1;-fx-border-color: black");

//        plotRegion.setBackground(Background.EMPTY);

        getChildren().add(plotRegion);

        bindPlotRegionSize();
    }

    /**
     * Binds plot region width and height to plotRegionWidth and plotRegionHeight. When plot region
     * width and height change, the location of all series data are updated.
     */
    void bindPlotRegionSize() {

        plotRegion.prefWidthProperty().bindBidirectional(plotRegionWidth);
        plotRegion.minWidthProperty().bindBidirectional(plotRegionWidth);
        plotRegion.maxWidthProperty().bindBidirectional(plotRegionWidth);

        plotRegion.prefHeightProperty().bindBidirectional(plotRegionHeight);
        plotRegion.minHeightProperty().bindBidirectional(plotRegionHeight);
        plotRegion.maxHeightProperty().bindBidirectional(plotRegionHeight);

        plotRegionWidth.addListener((a, b, c) -> {
            for (int i = 0; i < seriesList.size(); i++) {
                updateSeriesPreviewAtChart(i);
            }
        });
        plotRegionHeight.addListener((a, b, c) -> {
            for (int i = 0; i < seriesList.size(); i++) {
                updateSeriesPreviewAtChart(i);
            }
        });
        rightPlotMargin.addListener((a, b, c) -> {
            for (int i = 0; i < seriesList.size(); i++) {
                updateSeriesPreviewAtChart(i);
            }
        });
        leftPlotMargin.addListener((a, b, c) -> {
            for (int i = 0; i < seriesList.size(); i++) {
                updateSeriesPreviewAtChart(i);
            }
        });
        topPlotMargin.addListener((a, b, c) -> {
            for (int i = 0; i < seriesList.size(); i++) {
                updateSeriesPreviewAtChart(i);
            }
        });
        bottomPlotMargin.addListener((a, b, c) -> {
            for (int i = 0; i < seriesList.size(); i++) {
                updateSeriesPreviewAtChart(i);
            }
        });
    }

    public void addData(int seriesIndex, int dataIndex, AppData appData) {
        seriesList.get(seriesIndex).add(dataIndex, appData);
        updateSeriesPreviewAtChart(seriesIndex);
    }

    public void addManyData(int seriesIndex, int dataIndex, List<AppData> appDataList) {
        for (int i = 0; i < appDataList.size(); i++) {
            seriesList.get(seriesIndex).add(dataIndex + i, appDataList.get(i));
        }
        updateSeriesPreviewAtChart(seriesIndex);
    }

    public void addManyData(int seriesIndex, int dataIndex, AppData... appDataList) {
        for (int i = 0; i < appDataList.length; i++) {
            seriesList.get(seriesIndex).add(dataIndex + i, appDataList[i]);
        }
        updateSeriesPreviewAtChart(seriesIndex);
    }

    public void removeData(int seriesIndex, int dataIndex) {
        seriesList.get(seriesIndex).remove(dataIndex);
        updateSeriesPreviewAtChart(seriesIndex);
    }

    public void removeData(int seriesIndex, AppData appData) {
        seriesList.get(seriesIndex).remove(appData);
        updateSeriesPreviewAtChart(seriesIndex);
    }

    public void updateData(int seriesIndex, int dataIndex, String x, String y) {
        seriesList.get(seriesIndex).get(dataIndex).setX(x);
        seriesList.get(seriesIndex).get(dataIndex).setY(y);
        updateSeriesPreviewAtChart(seriesIndex);
    }

    public void removeSeries(int seriesIndex) {
        seriesList.remove(seriesIndex);
        seriesPlotAreaList.remove(seriesIndex);
        seriesPlotLineList.remove(seriesIndex);
        seriesMarkersList.remove(seriesIndex);
        seriesLegendList.remove(seriesIndex);
        plotRegion.getChildren().remove(3 * seriesIndex);
        plotRegion.getChildren().remove(3 * seriesIndex + 1);
        plotRegion.getChildren().remove(3 * seriesIndex + 2);
    }


    public void addSeries() {
        addSeries(seriesList.size());
    }

    public void addSeries(int seriesIndex) {

        seriesList.add(seriesIndex, FXCollections.observableList(new ArrayList<>()));

        Polygon newSeriesPlotArea = new Polygon();
        Polyline newSeriesPlotLine = new Polyline();
        Group seriesMarkers = new Group();

        seriesPlotAreaList.add(seriesIndex, newSeriesPlotArea);
        seriesPlotLineList.add(seriesIndex, newSeriesPlotLine);
        seriesMarkersList.add(seriesIndex, seriesMarkers);
        seriesLegendList.add(seriesIndex, new Text());
        plotRegion.getChildren().add(3 * seriesIndex, newSeriesPlotArea);
        plotRegion.getChildren().add(3 * seriesIndex + 1, newSeriesPlotLine);
        plotRegion.getChildren().add(3 * seriesIndex + 2, seriesMarkers);

    }


    List<Double> temp = new ArrayList<>();

    void updateSeriesPreviewAtChart(int seriesIndex) {

        ObservableList<AppData> series = seriesList.get(seriesIndex);
        Polyline seriesPlotLine = seriesPlotLineList.get(seriesIndex);
        Polygon seriesPlotArea = seriesPlotAreaList.get(seriesIndex);
        temp.clear();
        series.sort(dataComparator);

        double minX = series.stream().mapToDouble(data -> Double.parseDouble(data.getX())).min().orElse(Double.POSITIVE_INFINITY);
        double maxX = series.stream().mapToDouble(data -> Double.parseDouble(data.getX())).max().orElse(Double.NEGATIVE_INFINITY);
        double minY = series.stream().mapToDouble(data -> Double.parseDouble(data.getY())).min().orElse(Double.POSITIVE_INFINITY);
        double maxY = series.stream().mapToDouble(data -> Double.parseDouble(data.getY())).max().orElse(Double.NEGATIVE_INFINITY);

        series.forEach(data -> {
            if (maxX == minX) {
                temp.add(plotRegionWidth.get() / 2);
            } else {
                temp.add((Double.parseDouble(data.getX()) - minX) / (maxX - minX) * plotRegionWidth.get() * (1 - leftPlotMargin.get() - rightPlotMargin.get()) + leftPlotMargin.get() * plotRegionWidth.get());
            }
            if (maxY == minY) {
                temp.add(plotRegionHeight.get() / 2);
            } else {
                temp.add((maxY - Double.parseDouble(data.getY())) / (maxY - minY) * plotRegionHeight.get() * (1 - topPlotMargin.get() - bottomPlotMargin.get()) + plotRegionHeight.get() * topPlotMargin.get());
            }
        });

        seriesPlotLine.getPoints().setAll(temp);
        seriesPlotArea.getPoints().setAll(plotRegionWidth.get() * (1 - rightPlotMargin.get()), plotRegionHeight.get() * (1 - bottomPlotMargin.get()), plotRegionWidth.get() * leftPlotMargin.get(), plotRegionHeight.get() * (1 - bottomPlotMargin.get()));
        seriesPlotArea.getPoints().addAll(temp);
    }

}









