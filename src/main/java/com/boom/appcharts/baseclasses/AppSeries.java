package com.boom.appcharts.baseclasses;

import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppPolygon;
import com.boom.appshapes.AppPolyline;
import com.boom.appshapes.AppRectangle;
import com.boom.exceptions.AppException;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.test.AppDataComparator;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.print;

public class AppSeries {

    public final Text title = new Text();
    private final SimpleObjectProperty<AppNode> markerShape = new SimpleObjectProperty<>();
    //Every change in data is reflected in this variable, to make it visible to the outside world.
    public final SimpleBooleanProperty changeIndicator = new SimpleBooleanProperty(false);
    private final SimpleObjectProperty<AppNode> visualLegend = new SimpleObjectProperty<>();
    private final List<double[]> dataList = new ArrayList<>();
    public AppPolygon plotArea = new AppPolygon();
    public AppPolyline plotLine = new AppPolyline();
    public Group renderedMarkers = new Group();
    private double minX = Double.POSITIVE_INFINITY;
    private double maxX = Double.NEGATIVE_INFINITY;
    private double minY = Double.POSITIVE_INFINITY;
    private double maxY = Double.NEGATIVE_INFINITY;


    public AppSeries() {

        markerShape.addListener((a,b,c)->{
            renderedMarkers.getChildren().clear();
            if(c.copy()!=null){
                for (int i = 0; i < dataList.size(); i++) {
                    renderedMarkers.getChildren().add(markerShape.get().copy().wrappedNode);
                }
            }
        });


//        markerShape.addListener((a, b, c) -> {
//            print("changed marker shape");
//            buildVisualLegend();
//            renderedMarkers.getChildren().clear();
//            for (double[] data : dataList) {
//                AppNode newRenderedMarkerShape = markerShape.get().copy();
//                newRenderedMarkerShape.affineTransform.prependTranslation(data[0], data[1]);
//                renderedMarkers.getChildren().add(newRenderedMarkerShape.wrappedNode);
//            }
////            renderedMarkers.getChildren().replaceAll(node -> {
////                AppNode newRenderedMarkerShape = markerShape.get().copy();
////                newRenderedMarkerShape.affineTransform.prependTranslation(node.getTranslateX(), node.getTranslateY());
////                return newRenderedMarkerShape.wrappedNode;
////            });
//        });

        plotArea.backgroundStyle.setStrokeWidth(0);
        plotLine.backgroundStyle.setStrokeWidth(1);

//        minX.addListener((a,b,c)->{
//            print("changed from %f to %f".formatted(b.doubleValue(),c.doubleValue()));
//        });

//        dataList.addListener((ListChangeListener<double[]>) change -> {
//            updateMinX();
//            updateMinY();
//            updateMaxX();
//            updateMaxY();
//        });


        changeIndicator.addListener((a, b, c) -> {
            updateMinX();
            updateMinY();
            updateMaxX();
            updateMaxY();
            print("add 3");
//            print("");
        });

//        todo temp
//        renderedMarkers.getChildren().addListener((ListChangeListener<? super Node>) change -> {
//            print(uuid(50));
//            change.getList().forEach(node -> print(node.getTransforms()));
//        });
    }

//    public void addManyData(int dataIndex, List<double[]> newAppDataList) {
//        newAppDataList.forEach(doubles -> renderedMarkers.getChildren().add(dataIndex + newAppDataList.indexOf(doubles), markerShape.get().copy().wrappedNode));
//        dataList.addAll(dataIndex, newAppDataList);
//    }

    /**
     * Adds data (x,y) at the location dataIndex of the series.
     *
     * @param dataIndex the index of the data to be modified.
     * @param x         the x coordinate of the data to be modified.
     * @param y         the y coordinate of the data to be modified.
     */
    public void addData(int dataIndex, double x, double y) {
        dataList.add(dataIndex, new double[]{x, y});
        if(!isMarkerShapeNull()) {
            renderedMarkers.getChildren().add(markerShape.get().copy().wrappedNode);
        }
        changeIndicator.set(!changeIndicator.get());
    }

    /**
     * Adds data (x,y) at the end of the series.
     *
     * @param x the x coordinate of the data to be modified.
     * @param y the y coordinate of the data to be modified.
     */
    public void addData(double x, double y) {
        print("hey there");
        dataList.add(new double[]{x, y});
        print("add 1");
        if(!isMarkerShapeNull()) {
            renderedMarkers.getChildren().add(markerShape.get().copy().wrappedNode);
        }
        print("add 2");
        changeIndicator.set(!changeIndicator.get());
        print(this);
        print("bye here");
    }

    public double[] getData(int dataIndex) {
        return dataList.get(dataIndex);
    }

    public int getDataListSize() {
        return dataList.size();
    }

//    public final CSSProperty markerBackgroundStyle=new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public int getNumberOfData() {
        return dataList.size();
    }

    public List<double[]> getSortedCopyOfDataList(AppDataComparator appDataComparator) {
        return dataList.stream().map(doubles -> new double[]{doubles[0], doubles[1]}).sorted(appDataComparator).toList();
    }

    public SimpleObjectProperty<AppNode> getVisualLegend() {
        buildVisualLegend();
        return visualLegend;
    }

    public boolean isMarkerShapeNull() {
        return markerShape.get()== null||markerShape.get().copy() == null;
    }

    public boolean isNotEmpty() {
        return dataList.size() != 0;
    }

    /**
     * Modifies the data located at dataIndex with a new pair of x and y.
     *
     * @param dataIndex the index of the data to be modified.
     * @param x         the x coordinate of the data to be modified.
     * @param y         the y coordinate of the data to be modified.
     */
    public void modifyData(int dataIndex, double x, double y) {
        dataList.set(dataIndex, new double[]{x, y});
        changeIndicator.set(!changeIndicator.get());
    }

    /**
     * Removes every data from the series.
     */
    public void removeAllData() {
        dataList.clear();
        renderedMarkers.getChildren().clear();
        changeIndicator.set(!changeIndicator.get());
    }

    /**
     * Removes the data located at dataIndex from the series.
     *
     * @param dataIndex the index of the data to be removed.
     */
    public void removeData(int dataIndex) {
        dataList.remove(dataIndex);
        if(!isMarkerShapeNull()) {
            renderedMarkers.getChildren().remove(0);
        }
        changeIndicator.set(!changeIndicator.get());
    }

    public void setMarkerShape(AppNode markerShape) {
        this.markerShape.set(markerShape);
    }

    /**
     * This method is used for test.
     *
     * @return data list of the series as a decorated string.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DataList = ");
        dataList.forEach(doubles -> stringBuilder.append("\n\t%f , %f".formatted(doubles[0], doubles[1])));
        return stringBuilder.toString();
    }

    private void buildVisualLegend() {
        if (markerShape.get() == null || markerShape.get().copy() == null) {
            visualLegend.set(new AppEllipse(0, 0));
        } else {
            visualLegend.set(markerShape.get().copy());
            visualLegend.get().backgroundStyle.bindBidirectional(markerShape.get().backgroundStyle);
            switch (markerShape.get().getType()) {
                case "Ellipse" -> visualLegend.get().affineTransform.prependTranslation(
                        ((AppEllipse) visualLegend.get()).radiusX.get(),
                        ((AppEllipse) visualLegend.get()).radiusY.get()
                );
                case "Rectangle" -> visualLegend.get().affineTransform.prependTranslation(
                        ((AppRectangle) visualLegend.get()).width.get() / 2,
                        ((AppRectangle) visualLegend.get()).height.get() / 2
                );
                default -> throw new AppException(AppExceptionEnum.NotRegisteredYet);
            }
        }
    }

    /**
     * Updates maxX, the max value of every x coordinate in the series.
     */
    private void updateMaxX() {
        maxX = dataList.stream().mapToDouble(doubles -> doubles[0]).max().orElse(Double.NEGATIVE_INFINITY);
    }

    /**
     * Updates maxY, the max value of every y coordinate in the series.
     */
    private void updateMaxY() {
        maxY = dataList.stream().mapToDouble(doubles -> doubles[1]).max().orElse(Double.NEGATIVE_INFINITY);
    }

    /**
     * Updates minX, the min value of every x coordinate in the series.
     */
    private void updateMinX() {
        minX = dataList.stream().mapToDouble(doubles -> doubles[0]).min().orElse(Double.POSITIVE_INFINITY);
    }

    /**
     * Updates minY, the min value of every y coordinate in the series.
     */
    private void updateMinY() {
        minY = dataList.stream().mapToDouble(doubles -> doubles[1]).min().orElse(Double.POSITIVE_INFINITY);
    }
}