package com.boom.appcharts;


import com.boom.structures.enums.AppAxisSortingPolicy;
import com.boom.test.DataComparator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

// todo: disallow addition of duplicate series or data

public class AppAxisChart extends BorderPane {

    public List<ObservableList<AppData>> seriesList = FXCollections.observableList(new ArrayList<>());
    public List<Polygon> seriesPlotAreaList = new ArrayList<>();
    public List<Polyline> seriesPlotLineList = new ArrayList<>();
    public SimpleDoubleProperty plotRegionWidth = new SimpleDoubleProperty();
    public SimpleDoubleProperty plotRegionHeight = new SimpleDoubleProperty();
    public SimpleDoubleProperty xAxisRegionHeight = new SimpleDoubleProperty();

    // todo: shape, fill, stroke and stroke width should change into AppNode and BackgroundStyle
    public SimpleDoubleProperty yAxisRegionWidth = new SimpleDoubleProperty();
    public SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty(0.1);
    Pane legendsContainer = new Pane();
    List<Text> seriesLegendList = new ArrayList<>();
    List<Group> seriesMarkerSetList = new ArrayList<>();
    List<Shape> seriesMarkersList = new ArrayList<>();
    List<Paint> seriesMarkersFillPaintList = new ArrayList<>();
    List<Paint> seriesMarkersStrokePaintList = new ArrayList<>();
    List<Double> seriesMarkersStrokeWidthList = new ArrayList<>();


//    boolean isAutoSetXTicks = true, isAutoSetYTicks = true;
    Group xGridLines = new Group();
    Group yGridLines = new Group();
    Pane plotRegion = new Pane();
    DataComparator dataComparator = new DataComparator();
    boolean isXNumeric;
    boolean isYNumeric;
    Pane xAxisRegion = new Pane();
    Pane yAxisRegion = new Pane();
//    double plotWidth, plotHeight;
//    double minXData = Double.POSITIVE_INFINITY, maxXData = Double.NEGATIVE_INFINITY,
//            minYData = Double.POSITIVE_INFINITY, maxYData = Double.NEGATIVE_INFINITY;
    AppAxisSortingPolicy appAxisSortingPolicy;
    List<Double> temp = new ArrayList<>();
    Supplier<DoubleStream> tempDataXListSupplier, tempDataYListSupplier;

    SimpleIntegerProperty numberOfXTicks=new SimpleIntegerProperty(10);
    SimpleIntegerProperty numberOfYTicks=new SimpleIntegerProperty(10);

    public AppAxisChart() {

        setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-border-width: 0px");

        plotRegion.setStyle("-fx-border-width: 1;-fx-border-color: black");

//        plotRegion.setBackground(Background.EMPTY);

//        getChildren().add(plotRegion);

        setCenter(plotRegion);
        setLeft(yAxisRegion);
        setBottom(xAxisRegion);

        xAxisRegion.setBackground(Background.fill(new Color(1, 0, 1, 0.2)));
        yAxisRegion.setBackground(Background.fill(new Color(0, 1, 1, 0.2)));
//        Rectangle rectangle=new Rectangle(50,50);
//        setLeft(rectangle);
//        rectangle.setFill(new Color(1,0,0,0.2));


        bindPlotRegionSize();

        bindAxesRegionsSize();

        setXNumeric(false);
        setYNumeric(false);

        setAxisSortingPolicy(AppAxisSortingPolicy.SortByDefault);



//        plotRegion.setBorder(Border.stroke(Color.BLACK));
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

    public void addSeries() {
        addSeries(seriesList.size());
    }

    public void addSeries(int seriesIndex) {

        seriesList.add(seriesIndex, FXCollections.observableList(new ArrayList<>()));

        Polygon newSeriesPlotArea = new Polygon();
        Polyline newSeriesPlotLine = new Polyline();
        Group seriesMarkerSet = new Group();

        seriesPlotAreaList.add(seriesIndex, newSeriesPlotArea);
        seriesPlotLineList.add(seriesIndex, newSeriesPlotLine);
        seriesMarkerSetList.add(seriesIndex, seriesMarkerSet);
        seriesLegendList.add(seriesIndex, new Text());
        plotRegion.getChildren().add(3 * seriesIndex, newSeriesPlotArea);
        plotRegion.getChildren().add(3 * seriesIndex + 1, newSeriesPlotLine);
        plotRegion.getChildren().add(3 * seriesIndex + 2, seriesMarkerSet);

    }

    public void removeData(int seriesIndex, int dataIndex) {
        seriesList.get(seriesIndex).remove(dataIndex);
        updateSeriesPreviewAtChart(seriesIndex);
    }

    public void removeData(int seriesIndex, AppData appData) {
        seriesList.get(seriesIndex).remove(appData);
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

    public void setAxisSortingPolicy(AppAxisSortingPolicy appAxisSortingPolicy) {
        this.appAxisSortingPolicy = appAxisSortingPolicy;
        dataComparator.setAppAxisSortingPolicy(appAxisSortingPolicy);
        updateAllSeriesPreviewsAtChart();
    }

    public void setXNumeric(boolean XNumeric) {
        isXNumeric = XNumeric;
    }

    public void setYNumeric(boolean YNumeric) {
        isYNumeric = YNumeric;
    }

    public void updateData(int seriesIndex, int dataIndex, String x, String y) {
        seriesList.get(seriesIndex).get(dataIndex).setX(x);
        seriesList.get(seriesIndex).get(dataIndex).setY(y);
        updateSeriesPreviewAtChart(seriesIndex);
    }

    void bindAxesRegionsSize() {
        xAxisRegion.minWidthProperty().bindBidirectional(plotRegionWidth);
        xAxisRegion.maxWidthProperty().bindBidirectional(plotRegionWidth);
        xAxisRegion.prefWidthProperty().bindBidirectional(plotRegionWidth);

        xAxisRegion.minHeightProperty().bindBidirectional(xAxisRegionHeight);
        xAxisRegion.maxHeightProperty().bindBidirectional(xAxisRegionHeight);
        xAxisRegion.prefHeightProperty().bindBidirectional(xAxisRegionHeight);

        yAxisRegion.minWidthProperty().bindBidirectional(yAxisRegionWidth);
        yAxisRegion.maxWidthProperty().bindBidirectional(yAxisRegionWidth);
        yAxisRegion.prefWidthProperty().bindBidirectional(yAxisRegionWidth);

        yAxisRegion.minHeightProperty().bindBidirectional(plotRegionHeight);
        yAxisRegion.maxHeightProperty().bindBidirectional(plotRegionHeight);
        yAxisRegion.prefHeightProperty().bindBidirectional(plotRegionHeight);

        xAxisRegion.translateXProperty().bindBidirectional(yAxisRegionWidth);
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

        plotRegionWidth.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        plotRegionHeight.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        rightPlotMargin.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        leftPlotMargin.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        topPlotMargin.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        bottomPlotMargin.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
    }

    void updateAllSeriesPreviewsAtChart() {
        for (int i = 0; i < seriesList.size(); i++) {
            updateSeriesPreviewAtChart(i);
        }
    }

    List<String> customXTicks=new ArrayList<>();
    List<String> customYTicks=new ArrayList<>();

    void updateSeriesPreviewAtChart(int seriesIndex) {

        ObservableList<AppData> series = seriesList.get(seriesIndex);
        Polyline seriesPlotLine = seriesPlotLineList.get(seriesIndex);
        Polygon seriesPlotArea = seriesPlotAreaList.get(seriesIndex);
        Group seriesMarkerSet = seriesMarkerSetList.get(seriesIndex);
        temp.clear();
        seriesMarkerSet.getChildren().clear();

        series.sort(dataComparator);

        tempDataXListSupplier = () -> series.stream().mapToDouble(appData -> {
            if (isXNumeric) {
                return Double.parseDouble(appData.getX());
            } else {
                return series.indexOf(appData);
            }
        });
        tempDataYListSupplier = () -> series.stream().mapToDouble(appData -> {
            if (isYNumeric) {
                return Double.parseDouble(appData.getY());
            } else {
                return series.indexOf(appData);
            }
        });

        double minX = tempDataXListSupplier.get().min().orElse(Double.POSITIVE_INFINITY);
        double maxX = tempDataXListSupplier.get().max().orElse(Double.NEGATIVE_INFINITY);
        double minY = tempDataYListSupplier.get().min().orElse(Double.POSITIVE_INFINITY);
        double maxY = tempDataYListSupplier.get().max().orElse(Double.NEGATIVE_INFINITY);

//        print("_______________________________");
//        print(appAxisSortingPolicy);
//        print(series);
//        print(tempDataXListSupplier.get().boxed());
//        tempDataXListSupplier.get().forEach(x->print("interp x = "+x));
//        tempDataYListSupplier.get().forEach(x->print("interp y = "+x));

        series.forEach(data -> {
            double x, y;
            if (maxX == minX) {
                x = plotRegionWidth.get() / 2;
            } else if (appAxisSortingPolicy.equals(AppAxisSortingPolicy.SortByY)) {
                x = (tempDataYListSupplier.get().toArray()[series.indexOf(data)] - minY) / (maxY - minY) * plotRegionWidth.get() * (1 - leftPlotMargin.get() - rightPlotMargin.get()) + leftPlotMargin.get() * plotRegionWidth.get();
            } else {
                x = (tempDataXListSupplier.get().toArray()[series.indexOf(data)] - minX) / (maxX - minX) * plotRegionWidth.get() * (1 - leftPlotMargin.get() - rightPlotMargin.get()) + leftPlotMargin.get() * plotRegionWidth.get();
            }
            if (maxY == minY) {
                y = plotRegionHeight.get() / 2;
            } else if (appAxisSortingPolicy.equals(AppAxisSortingPolicy.SortByY)) {
                y = (tempDataXListSupplier.get().toArray()[series.indexOf(data)] - minX) / (maxX - minX) * plotRegionHeight.get() * (1 - topPlotMargin.get() - bottomPlotMargin.get()) + plotRegionHeight.get() * topPlotMargin.get();
            } else {
                y = (maxY - tempDataYListSupplier.get().toArray()[series.indexOf(data)]) / (maxY - minY) * plotRegionHeight.get() * (1 - topPlotMargin.get() - bottomPlotMargin.get()) + plotRegionHeight.get() * topPlotMargin.get();
            }
            temp.add(x);
            temp.add(y);
            Circle c = new Circle(2, Color.RED);
            c.setTranslateX(x);
            c.setTranslateY(y);
            seriesMarkerSet.getChildren().add(c);

//            print(x+","+y);


        });

//        print(temp);

        seriesPlotLine.getPoints().setAll(temp);
        if (appAxisSortingPolicy.equals(AppAxisSortingPolicy.SortByY)) {
            seriesPlotArea.getPoints().setAll(plotRegionWidth.get() * leftPlotMargin.get(), plotRegionHeight.get() * (1 - bottomPlotMargin.get()), plotRegionWidth.get() * leftPlotMargin.get(), plotRegionHeight.get() * topPlotMargin.get());
        } else {
            seriesPlotArea.getPoints().setAll(plotRegionWidth.get() * (1 - rightPlotMargin.get()), plotRegionHeight.get() * (1 - bottomPlotMargin.get()), plotRegionWidth.get() * leftPlotMargin.get(), plotRegionHeight.get() * (1 - bottomPlotMargin.get()));
        }
        seriesPlotArea.getPoints().addAll(temp);
    }

}