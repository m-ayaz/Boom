package com.example.structures;

import com.example.styles.BackgroundsProperty;
import com.example.styles.SeriesLineStyleProperty;
import com.example.styles.SeriesMarkersStyleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Translate;

import java.util.List;

import static com.example.tools.Tools.setSize;

public  abstract class AppXYChart<T1, T2> extends AppNode {

    // todo Only PieChart is an exception. Think of it later!

    protected Axis<T1> xAxisArea;
    protected Axis<T2> yAxisArea;
    protected Region plotArea;
    protected ReadOnlyObjectProperty<Bounds> plotAreaBounds;
    protected List<SeriesMarkersStyleProperty> seriesMarkersStyles;
    protected List<SeriesLineStyleProperty> seriesLineStyles;
    protected List<BackgroundsProperty> seriesAreaStyles;
    protected BackgroundsProperty backgroundStyle = new BackgroundsProperty("-fx-background-color","-fx-border-color","-fx-border-width");
    protected StringProperty xAxisStyle = new SimpleStringProperty();
    protected StringProperty yAxisStyle = new SimpleStringProperty();
    protected StringProperty legendStyle = new SimpleStringProperty();
    public AppXYChart(XYChart<T1, T2> xyChart, double width, double height) {

        super(xyChart);
        xyChart.setPadding(new Insets(0));
        setSize(xyChart, width, height);

        xyChart.lookup(".chart-plot-background").styleProperty().bind(backgroundStyle);

        plotArea = (Region) xyChart.lookup(".chart-plot-background");
        plotAreaBounds = plotArea.boundsInParentProperty();


        xAxisArea = xyChart.getXAxis();
        yAxisArea = xyChart.getYAxis();


    }

    public abstract XYChart.Data<T1, T2> addData(T1 x, T2 y, int seriesIndex, int dataIndex);

    public XYChart.Data<T1, T2> addData(T1 x, T2 y, int seriesIndex) {
        return addData(x, y, seriesIndex, ((XYChart<T1, T2>) node).getData().get(seriesIndex).getData().size());
    }

    public abstract XYChart.Series<T1, T2> addSeries(int index);

    public XYChart.Series<T1, T2> addSeries() {
        return addSeries(((XYChart<T1, T2>) node).getData().size());
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        node.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        setSize((Region) node, Math.abs(currentDragPosX - dragStartX), Math.abs(currentDragPosY - dragStartY));
    }

    @Override
    public void setBackgroundColor(Paint paint) {

    }

    @Override
    public void setBorderColor(Paint paint) {

    }

    public BackgroundsProperty getBackgroundStyle() {
        return backgroundStyle;
    }

    public List<BackgroundsProperty> getSeriesAreaStyles() {
        return seriesAreaStyles;
    }

    public List<SeriesLineStyleProperty> getSeriesLineStyles() {
        return seriesLineStyles;
    }

    public List<SeriesMarkersStyleProperty> getSeriesMarkersStyles() {
        return seriesMarkersStyles;
    }

    public abstract void removeSeries(int index);

    public void setBackgroundColor(Color color) {
//        backgroundStyle.fill.set(color);
    }

    public void setBorderColor(Color color) {
//        backgroundStyle.stroke.set(color);
    }

    @Override
    public void setBorderDashArray(List<Double> borderDashArray) {

    }

    @Override
    public void setBorderWidth(double borderWidth) {
//        ((Region) node).setBorder(Border.stroke());
    }

//    @Override
//    protected void bindProperties() {
//        border.setFill(new Color(0, 0, 0, 0));
//        border.setStroke(new Color(0, 0, 0, 1));
//        border.setMouseTransparent(true);
//        border.setStrokeWidth(1);
//        border.getStrokeDashArray().addAll(5.0, 6.0, 6.0, 6.0);
//        border.setVisible(false);
//        node.boundsInParentProperty().removeListener((_1, _2, newVal) -> {
//            border.setX(newVal.getMinX());
//            border.setY(newVal.getMinY());
//            border.setWidth(newVal.getWidth());
//            border.setHeight(newVal.getHeight());
//        });
//        node.lookup(".chart-plot-background").boundsInParentProperty().addListener((_1, _2, newVal) -> {
//            border.setX(newVal.getMinX());
//            border.setY(newVal.getMinY());
//            border.setWidth(newVal.getWidth());
//            border.setHeight(newVal.getHeight());
//        });
//        node.getTransforms().clear();
//        node.lookup(".chart-plot-background").getTransforms().clear();
//        node.getTransforms().add(affineTransform);
//        node.lookup(".chart-plot-background").getTransforms().add(affineTransform);
//    }


}