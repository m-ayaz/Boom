package com.boom.appcharts.baseclasses;

import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppPolygon;
import com.boom.appshapes.AppPolyline;
import com.boom.appshapes.AppRectangle;
import com.boom.structures.abstracts.AppNode;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;

public class AppSeries {

    public final Text title = new Text();
    public AppPolygon plotArea = new AppPolygon();
    public AppPolyline plotLine = new AppPolyline();
    public Group renderedMarkers = new Group();
    public SimpleObjectProperty<AppNode> markerShape = new SimpleObjectProperty<>();
    public ObservableList<double[]> dataList = FXCollections.observableList(new ArrayList<>());
    public SimpleDoubleProperty minX = new SimpleDoubleProperty(Double.POSITIVE_INFINITY);
    public SimpleDoubleProperty maxX = new SimpleDoubleProperty(Double.NEGATIVE_INFINITY);
    public SimpleDoubleProperty minY = new SimpleDoubleProperty(Double.POSITIVE_INFINITY);
    public SimpleDoubleProperty maxY = new SimpleDoubleProperty(Double.NEGATIVE_INFINITY);
    private final SimpleObjectProperty<AppNode> visualLegend = new SimpleObjectProperty<>();

//    public final CSSProperty markerBackgroundStyle=new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");

    public AppSeries() {


        markerShape.addListener((a, b, c) -> {
            buildVisualLegend();
            renderedMarkers.getChildren().replaceAll(node -> {
                AppNode newRenderedMarkerShape = markerShape.get().copy();
                newRenderedMarkerShape.affineTransform.prependTranslation(node.getTranslateX(), node.getTranslateY());
                return newRenderedMarkerShape.styleableNode;
            });
        });

        plotArea.backgroundStyle.setStrokeWidth(0);
        plotLine.backgroundStyle.setStrokeWidth(1);

//        minX.addListener((a,b,c)->{
//            print("changed from %f to %f".formatted(b.doubleValue(),c.doubleValue()));
//        });

        dataList.addListener((ListChangeListener<double[]>) change -> {
//            print("uuid(30)");
//            print(minX.get());
//            print(maxX.get());
//            print(minY.get());
//            print(maxY.get());

            updateMinX();
//            print("uuid(40)");
            updateMinY();
            updateMaxX();
            updateMaxY();
//            print(minX.get());
//            print(maxX.get());
//            print(minY.get());
//            print(maxY.get());
        });

//        todo temp
//        renderedMarkers.getChildren().addListener((ListChangeListener<? super Node>) change -> {
//            print(uuid(50));
//            change.getList().forEach(node -> print(node.getTransforms()));
//        });
    }

    public void addData(int dataIndex, double x, double y) {
        AppNode newMarkerShape = markerShape.get().copy();
        newMarkerShape.backgroundStyle.bindBidirectional(markerShape.get().backgroundStyle);
        renderedMarkers.getChildren().add(dataIndex, newMarkerShape.styleableNode);
        dataList.add(dataIndex, new double[]{x, y});
    }

    public void addData(double x, double y) {
        addData(dataList.size(), x, y);
    }

    public void addManyData(int dataIndex, List<double[]> newAppDataList) {
        newAppDataList.forEach(doubles -> renderedMarkers.getChildren().add(dataIndex + newAppDataList.indexOf(doubles), markerShape.get().copy().styleableNode));
        dataList.addAll(dataIndex, newAppDataList);
    }

    public SimpleObjectProperty<AppNode> getVisualLegend() {
//        print("la;sla;l;a");
        buildVisualLegend();
//        print("la;sla;l;a");
        return visualLegend;
    }

    public void removeData(int dataIndex) {
        renderedMarkers.getChildren().remove(dataIndex);
        dataList.remove(dataIndex);
    }

    public void setMarkerShape(AppNode markerShape) {
        this.markerShape.set(markerShape);
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
//        print("aaaaaaaaa");
        if(markerShape.get()==null||markerShape.get().copy()==null){
            visualLegend.set(new AppEllipse(0,0));
        }else {
            visualLegend.set(markerShape.get().copy());
            visualLegend.get().backgroundStyle.bindBidirectional(markerShape.get().backgroundStyle);
            switch (markerShape.get().getType()){
                case "Ellipse"-> visualLegend.get().affineTransform.prependTranslation(
                        ((AppEllipse) visualLegend.get()).radiusX.get(),
                        ((AppEllipse) visualLegend.get()).radiusY.get()
                );
                case "Rectangle"-> visualLegend.get().affineTransform.prependTranslation(
                        ((AppRectangle) visualLegend.get()).width.get() / 2,
                        ((AppRectangle) visualLegend.get()).height.get() / 2
                );
            }
        }
//        print("bbbb");

//        print(markerShape.get().backgroundStyle);

//        print(visualLegend.get());


//        print("ccc");

    }

}