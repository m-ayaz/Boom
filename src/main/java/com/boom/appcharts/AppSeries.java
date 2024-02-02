package com.boom.appcharts;

import com.boom.appshapes.AppPolygon;
import com.boom.appshapes.AppPolyline;
import com.boom.structures.abstracts.AppNode;
import com.boom.test.AppDataComparator;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class AppSeries {

    public AppPolygon plotArea = new AppPolygon();
    public AppPolyline plotLine = new AppPolyline();
    public Group renderedMarkers = new Group();
    public SimpleObjectProperty<AppNode> markerShape = new SimpleObjectProperty<>();
    SimpleDoubleProperty localMinX = new SimpleDoubleProperty();
    SimpleDoubleProperty localMaxX = new SimpleDoubleProperty();
    SimpleDoubleProperty localMinY = new SimpleDoubleProperty();
    SimpleDoubleProperty localMaxY = new SimpleDoubleProperty();
    SimpleDoubleProperty globalMinX = new SimpleDoubleProperty();
    SimpleDoubleProperty globalMaxX = new SimpleDoubleProperty();
    SimpleDoubleProperty globalMinY = new SimpleDoubleProperty();
    SimpleDoubleProperty globalMaxY = new SimpleDoubleProperty();
    AppDataComparator appDataComparator = new AppDataComparator();
    public List<double[]> appDataList = new ArrayList<>();
    public List<double[]> previewCoordinatesList = new ArrayList<>();
    private DoubleBinding minXVisualLocation;
    private DoubleBinding minYVisualLocation;
    private DoubleBinding maxXVisualLocation;
    private DoubleBinding maxYVisualLocation;

    public AppSeries() {

        markerShape.addListener((a, b, c) -> renderedMarkers.getChildren().replaceAll(node -> {
            Node newRenderedMarkerShape = markerShape.get().copy().getStyleableNode();
            newRenderedMarkerShape.setTranslateX(node.getTranslateX());
            newRenderedMarkerShape.setTranslateY(node.getTranslateY());
            return newRenderedMarkerShape;
        }));

        plotArea.backgroundStyle.setStrokeWidth(0);
        plotLine.backgroundStyle.setStrokeWidth(1);
    }

    public void addData(int dataIndex, double x, double y) {
        appDataList.add(dataIndex, new double[]{x, y});
        updateLocalMaxX();
        updateLocalMinX();
        updateLocalMaxY();
        updateLocalMinY();
        renderedMarkers.getChildren().add(dataIndex, markerShape.get().copy().getStyleableNode());
        updatePreviewAtChart();
    }

    public void addData(double x, double y) {
        addData(appDataList.size(), x, y);
    }

    public void addManyData(int dataIndex, List<double[]> newAppDataList) {
        appDataList.addAll(dataIndex, newAppDataList);
        updateLocalMaxX();
        updateLocalMinX();
        updateLocalMaxY();
        updateLocalMinY();
        updatePreviewAtChart();

    }

    public void bindDataBounds(SimpleDoubleProperty globalMinX, SimpleDoubleProperty globalMaxX, SimpleDoubleProperty globalMinY, SimpleDoubleProperty globalMaxY) {
        this.globalMinX.bindBidirectional(globalMinX);
        this.globalMaxX.bindBidirectional(globalMaxX);
        this.globalMinY.bindBidirectional(globalMinY);
        this.globalMaxY.bindBidirectional(globalMaxY);
    }

    public void bindVisualBounds(DoubleBinding minXVisualLocation, DoubleBinding minYVisualLocation, DoubleBinding maxXVisualLocation, DoubleBinding maxYVisualLocation) {
        this.minXVisualLocation = minXVisualLocation;
        this.minYVisualLocation = minYVisualLocation;
        this.maxXVisualLocation = maxXVisualLocation;
        this.maxYVisualLocation = maxYVisualLocation;
        this.minXVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
        this.maxXVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
        this.minYVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
        this.maxYVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
    }

    public void removeData(int dataIndex) {
        appDataList.remove(dataIndex);
        updateLocalMaxX();
        updateLocalMinX();
        updateLocalMaxY();
        updateLocalMinY();
        renderedMarkers.getChildren().remove(dataIndex);
        updatePreviewAtChart();
    }

    public void setMarkerShape(AppNode markerShape) {
        this.markerShape.set(markerShape);
    }

    public void updateData(int dataIndex, double x, double y) {
        appDataList.set(dataIndex, new double[]{x, y});
        updateLocalMaxX();
        updateLocalMinX();
        updateLocalMaxY();
        updateLocalMinY();
        updatePreviewAtChart();
    }

    public void updatePreviewAtChart() {

        previewCoordinatesList.clear();
        if (appDataList.size() == 1) {
            previewCoordinatesList.add(new double[]{minXVisualLocation.get() / 2 + maxXVisualLocation.get() / 2, minYVisualLocation.get() / 2 + maxYVisualLocation.get() / 2});
        } else {
            appDataList.forEach(doubles -> previewCoordinatesList.add(new double[]{
                    (doubles[0] - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get(),
                    (doubles[1] - globalMinY.get()) / (globalMaxY.get() - globalMinY.get()) * (maxYVisualLocation.get() - minYVisualLocation.get()) + minYVisualLocation.get()
            }));
            previewCoordinatesList.sort(appDataComparator);
        }


        plotArea.points.setAll(
                (localMaxX.get() - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get(),
                minYVisualLocation.get(),
                (localMinX.get() - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get(),
                minYVisualLocation.get()
        );
        previewCoordinatesList.forEach(doubles -> plotArea.points.addAll(doubles[0], doubles[1]));

        plotLine.points.clear();
        previewCoordinatesList.forEach(doubles -> plotLine.points.addAll(doubles[0], doubles[1]));

        if (markerShape.get() != null) {
            for (int i = 0; i < previewCoordinatesList.size(); i++) {
                renderedMarkers.getChildren().get(i).setTranslateX(previewCoordinatesList.get(i)[0]);
                renderedMarkers.getChildren().get(i).setTranslateY(previewCoordinatesList.get(i)[1]);
            }
//            .forEach(doubles -> {
////                Node newMarker = markerShape.get().copy().getStyleableNode();
////                renderedMarkers.getChildren().add(newMarker);
//                newMarker.setTranslateX(doubles[0]);
//                newMarker.setTranslateY(doubles[1]);
//            });
        }

    }

    void updateLocalMaxX() {
        localMaxX.set(appDataList.stream().mapToDouble(doubles -> doubles[0]).max().orElse(Double.NEGATIVE_INFINITY));
    }

    void updateLocalMaxY() {
        localMaxY.set(appDataList.stream().mapToDouble(doubles -> doubles[1]).max().orElse(Double.NEGATIVE_INFINITY));
    }

    void updateLocalMinX() {
        localMinX.set(appDataList.stream().mapToDouble(doubles -> doubles[0]).min().orElse(Double.POSITIVE_INFINITY));
    }

    void updateLocalMinY() {
        localMinY.set(appDataList.stream().mapToDouble(doubles -> doubles[1]).min().orElse(Double.POSITIVE_INFINITY));
    }

}