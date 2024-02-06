package com.boom.appcharts;

import com.boom.appshapes.AppPolygon;
import com.boom.appshapes.AppPolyline;
import com.boom.structures.abstracts.AppNode;
import com.boom.styles.CSSProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;

public class AppSeries {

    public final Label title = new Label();
    public AppPolygon plotArea = new AppPolygon();
    public AppPolyline plotLine = new AppPolyline();
    public Group renderedMarkers = new Group();
    public SimpleObjectProperty<AppNode> markerShape = new SimpleObjectProperty<>();
    public ObservableList<double[]> dataList = FXCollections.observableList(new ArrayList<>());
    SimpleDoubleProperty minX = new SimpleDoubleProperty();
    SimpleDoubleProperty maxX = new SimpleDoubleProperty();
    SimpleDoubleProperty minY = new SimpleDoubleProperty();
    SimpleDoubleProperty maxY = new SimpleDoubleProperty();
    private final SimpleObjectProperty<AppNode> visualLegend = new SimpleObjectProperty<>();

//    public final CSSProperty markerBackgroundStyle=new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");

    public AppSeries() {


        markerShape.addListener((a, b, c) -> {
            buildVisualLegend();
//            updateRenderedMarkers();
//            print(uuid(50));
//            updateRenderedMarkers();
            renderedMarkers.getChildren().replaceAll(node -> {
                AppNode newRenderedMarkerShape = markerShape.get().copy();
//                print(node.getTranslateX()+" , "+node.getTranslateY());
                newRenderedMarkerShape.affineTransform.prependTranslation(node.getTranslateX(), node.getTranslateY());
                return newRenderedMarkerShape.getStyleableNode();
            });
        });

//        markerShape.addListener((a, b, c) -> renderedMarkers.getChildren().replaceAll(node -> {
//            Node newRenderedMarkerShape = markerShape.get().copy().getStyleableNode();
//            newRenderedMarkerShape.setTranslateX(node.getTranslateX());
//            newRenderedMarkerShape.setTranslateY(node.getTranslateY());
//            return newRenderedMarkerShape;
//        }));

        plotArea.backgroundStyle.setStrokeWidth(0);
        plotLine.backgroundStyle.setStrokeWidth(1);

        dataList.addListener((ListChangeListener<double[]>) change -> {
            updateMaxX();
            updateMinX();
            updateMaxY();
            updateMinY();
        });

//        todo temp
//        renderedMarkers.getChildren().addListener((ListChangeListener<? super Node>) change -> {
//            print(uuid(50));
//            change.getList().forEach(node -> print(node.getTransforms()));
//        });
    }

    public void addData(int dataIndex, double x, double y) {
        AppNode newMarkerShape=markerShape.get().copy();
        newMarkerShape.backgroundStyle.bindBidirectional(markerShape.get().backgroundStyle);
        renderedMarkers.getChildren().add(dataIndex, newMarkerShape.getStyleableNode());
        dataList.add(dataIndex, new double[]{x, y});
    }

    public void addData(double x, double y) {
        addData(dataList.size(), x, y);
    }

    public void addManyData(int dataIndex, List<double[]> newAppDataList) {
        newAppDataList.forEach(doubles -> renderedMarkers.getChildren().add(dataIndex + newAppDataList.indexOf(doubles), markerShape.get().copy().getStyleableNode()));
        dataList.addAll(dataIndex, newAppDataList);
    }

    public SimpleObjectProperty<AppNode> getVisualLegend() {
        buildVisualLegend();
        return visualLegend;
    }

    public void removeData(int dataIndex) {
        renderedMarkers.getChildren().remove(dataIndex);
        dataList.remove(dataIndex);
    }

    public void setMarkerShape(AppNode markerShape) {
        this.markerShape.set(markerShape);
//        markerShape.backgroundStyle.bindBidirectional(markerShape.backgroundStyle);
//        markerShape.backgroundStyle.addListener((a, b, c) -> {
//            buildVisualLegend();
//            updateRenderedMarkers();
//        });
    }

    private void updateMaxX() {
        maxX.set(dataList.stream().mapToDouble(doubles -> doubles[0]).max().orElse(Double.NEGATIVE_INFINITY));
    }

    private void updateMaxY() {
        maxY.set(dataList.stream().mapToDouble(doubles -> doubles[1]).max().orElse(Double.NEGATIVE_INFINITY));
    }

    private void updateMinX() {
        minX.set(dataList.stream().mapToDouble(doubles -> doubles[0]).min().orElse(Double.POSITIVE_INFINITY));
    }

    private void updateMinY() {
        minY.set(dataList.stream().mapToDouble(doubles -> doubles[1]).min().orElse(Double.POSITIVE_INFINITY));
    }

    private void buildVisualLegend() {
        visualLegend.set(markerShape.get());
    }

//    private void updateRenderedMarkers() {
//        renderedMarkers.getChildren().replaceAll(node -> {
//            AppNode newRenderedMarkerShape = markerShape.get().copy();
//            newRenderedMarkerShape.affineTransform.prependTranslation(node.getTranslateX(), node.getTranslateY());
//            return newRenderedMarkerShape.getStyleableNode();
//        });
//    }
}