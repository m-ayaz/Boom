package com.boom.appcharts;

import com.boom.appshapes.AppPolygon;
import com.boom.appshapes.AppPolyline;
import com.boom.structures.abstracts.AppNode;
import com.boom.test.AppDataComparator;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class AppSeries {

    public AppPolygon plotArea = new AppPolygon();
    public AppPolyline plotLine = new AppPolyline();
    public Group renderedMarkers = new Group();
    public SimpleObjectProperty<AppNode> markerShape = new SimpleObjectProperty<>();
    SimpleDoubleProperty minX = new SimpleDoubleProperty();
    SimpleDoubleProperty maxX = new SimpleDoubleProperty();
    SimpleDoubleProperty minY = new SimpleDoubleProperty();
    SimpleDoubleProperty maxY = new SimpleDoubleProperty();
//    SimpleDoubleProperty globalMinX = new SimpleDoubleProperty();
//    SimpleDoubleProperty globalMaxX = new SimpleDoubleProperty();
//    SimpleDoubleProperty globalMinY = new SimpleDoubleProperty();
//    SimpleDoubleProperty globalMaxY = new SimpleDoubleProperty();
//    AppDataComparator appDataComparator = new AppDataComparator();
    public ObservableList<double[]> dataList = FXCollections.observableList(new ArrayList<>());
//    public List<double[]> previewCoordinatesList = new ArrayList<>();
//    private DoubleBinding minXVisualLocation;
//    private DoubleBinding minYVisualLocation;
//    private DoubleBinding maxXVisualLocation;
//    private DoubleBinding maxYVisualLocation;

//    public Label getTitle() {
//        return title;
//    }
//
//    public AppNode getTitlePreview() {
//        return titlePreview;
//    }

    public final Label title=new Label();

//    public void setTitle(String title){
//        this.title.setText(title);
//    }
//
//    public void setTitlePreview(AppNode titlePreview) {
//        this.titlePreview = titlePreview;
//    }

    public AppNode titlePreview;


    public AppSeries() {

        markerShape.addListener((a, b, c) -> renderedMarkers.getChildren().replaceAll(node -> {
            Node newRenderedMarkerShape = markerShape.get().copy().getStyleableNode();
            newRenderedMarkerShape.setTranslateX(node.getTranslateX());
            newRenderedMarkerShape.setTranslateY(node.getTranslateY());
            return newRenderedMarkerShape;
        }));

        plotArea.backgroundStyle.setStrokeWidth(0);
        plotLine.backgroundStyle.setStrokeWidth(1);

        dataList.addListener((ListChangeListener< double[]>) change -> {
            updateMaxX();
            updateMinX();
            updateMaxY();
            updateMinY();
//            updatePreviewAtChart();
//            change.getTo()
//            change.
        });
    }

    public void addData(int dataIndex, double x, double y) {
        renderedMarkers.getChildren().add(dataIndex, markerShape.get().copy().getStyleableNode());
        dataList.add(dataIndex, new double[]{x, y});
//        updateMaxX();
//        updateMinX();
//        updateMaxY();
//        updateMinY();


//        updatePreviewAtChart();
    }

    public void addData(double x, double y) {
        addData(dataList.size(), x, y);
    }

    public void addManyData(int dataIndex, List<double[]> newAppDataList) {
        newAppDataList.forEach(doubles -> renderedMarkers.getChildren().add(dataIndex+newAppDataList.indexOf(doubles), markerShape.get().copy().getStyleableNode()));
        dataList.addAll(dataIndex, newAppDataList);
//        updateMaxX();
//        updateMinX();
//        updateMaxY();
//        updateMinY();
//        updatePreviewAtChart();

    }

//    public void bindDataBounds(SimpleDoubleProperty globalMinX, SimpleDoubleProperty globalMaxX, SimpleDoubleProperty globalMinY, SimpleDoubleProperty globalMaxY) {
//        this.globalMinX.bindBidirectional(globalMinX);
//        this.globalMaxX.bindBidirectional(globalMaxX);
//        this.globalMinY.bindBidirectional(globalMinY);
//        this.globalMaxY.bindBidirectional(globalMaxY);
//    }

//    public void bindVisualBounds(DoubleBinding minXVisualLocation, DoubleBinding minYVisualLocation, DoubleBinding maxXVisualLocation, DoubleBinding maxYVisualLocation) {
//        this.minXVisualLocation = minXVisualLocation;
//        this.minYVisualLocation = minYVisualLocation;
//        this.maxXVisualLocation = maxXVisualLocation;
//        this.maxYVisualLocation = maxYVisualLocation;
//        this.minXVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
//        this.maxXVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
//        this.minYVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
//        this.maxYVisualLocation.addListener((a, b, c) -> updatePreviewAtChart());
//    }

    public void removeData(int dataIndex) {
        renderedMarkers.getChildren().remove(dataIndex);
        dataList.remove(dataIndex);
//        updateMaxX();
//        updateMinX();
//        updateMaxY();
//        updateMinY();

//        updatePreviewAtChart();
    }

    public void setMarkerShape(AppNode markerShape) {
        this.markerShape.set(markerShape);
    }

//    public void updateData(int dataIndex, double x, double y) {
//        dataList.set(dataIndex, new double[]{x, y});
////        updateMaxX();
////        updateMinX();
////        updateMaxY();
////        updateMinY();
////        updatePreviewAtChart();
//    }

//    public void updatePreviewAtChart() {
//
//        previewCoordinatesList.clear();
//        if (dataList.size() == 1) {
//            previewCoordinatesList.add(new double[]{minXVisualLocation.get() / 2 + maxXVisualLocation.get() / 2, minYVisualLocation.get() / 2 + maxYVisualLocation.get() / 2});
//        } else {
//            dataList.forEach(doubles -> previewCoordinatesList.add(new double[]{
//                    (doubles[0] - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get(),
//                    (doubles[1] - globalMinY.get()) / (globalMaxY.get() - globalMinY.get()) * (maxYVisualLocation.get() - minYVisualLocation.get()) + minYVisualLocation.get()
//            }));
//            previewCoordinatesList.sort(appDataComparator);
//        }
//
//
//        plotArea.points.setAll(
//                (maxX.get() - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get(),
//                minYVisualLocation.get(),
//                (minX.get() - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get(),
//                minYVisualLocation.get()
//        );
//        previewCoordinatesList.forEach(doubles -> plotArea.points.addAll(doubles[0], doubles[1]));
//
//        plotLine.points.clear();
//        previewCoordinatesList.forEach(doubles -> plotLine.points.addAll(doubles[0], doubles[1]));
//
//        if (markerShape.get() != null) {
//            for (int i = 0; i < previewCoordinatesList.size(); i++) {
//                renderedMarkers.getChildren().get(i).setTranslateX(previewCoordinatesList.get(i)[0]);
//                renderedMarkers.getChildren().get(i).setTranslateY(previewCoordinatesList.get(i)[1]);
//            }
////            .forEach(doubles -> {
//////                Node newMarker = markerShape.get().copy().getStyleableNode();
//////                renderedMarkers.getChildren().add(newMarker);
////                newMarker.setTranslateX(doubles[0]);
////                newMarker.setTranslateY(doubles[1]);
////            });
//        }
//
//    }

    void updateMaxX() {
        maxX.set(dataList.stream().mapToDouble(doubles -> doubles[0]).max().orElse(Double.NEGATIVE_INFINITY));
    }

    void updateMaxY() {
        maxY.set(dataList.stream().mapToDouble(doubles -> doubles[1]).max().orElse(Double.NEGATIVE_INFINITY));
    }

    void updateMinX() {
        minX.set(dataList.stream().mapToDouble(doubles -> doubles[0]).min().orElse(Double.POSITIVE_INFINITY));
    }

    void updateMinY() {
        minY.set(dataList.stream().mapToDouble(doubles -> doubles[1]).min().orElse(Double.POSITIVE_INFINITY));
    }

    public Group getVisualLegend() {
        setVisualLegend();
        return visualLegend;
    }

    private final Group visualLegend=new Group();

    private void setVisualLegend() {
        visualLegend.getChildren().clear();
        visualLegend.getChildren().add(markerShape.get().getStyleableNode());
    }



//    public Group getVisualLegend(){
////        Label label=new Label();
////        label.textProperty().bindBidirectional(title);
//
////        //todo settle when titlePreview is null..
////        AppNode temp1;
////        titlePreview.addListener((a,b,c)->temp1=c);
//        if(titlePreview==null) {
//            titlePreview = new AppRectangle(50, 50, 0, 0);
//            titlePreview.backgroundStyle.addFill(new AppLinearGradient(new LinearGradient(
//                    0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
//                    new Stop(0, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                    new Stop(0.5, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                    new Stop(1, new Color(Math.random(), Math.random(), Math.random(), 1))
//            )));
//        }
//
//
//
//        visualLegend.getChildren().add(titlePreview.getStyleableNode());
//        Rectangle emptySpace=new Rectangle();
//        emptySpace.setWidth(gap);
//
//        HBox hBox=new HBox(titlePreview.getStyleableNode(),emptySpace,title);
//        hBox.setAlignment(Pos.CENTER);

//        return visualLegend;
//    }

}