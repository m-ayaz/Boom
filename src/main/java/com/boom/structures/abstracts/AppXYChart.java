package com.boom.structures.abstracts;

import com.boom.apppaints.AppColor;
import com.boom.configuration.Configs;
import com.boom.styles.CSSProperty;
import com.boom.styles.SeriesLineStyleProperty;
import com.boom.styles.SeriesMarkersStyleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import org.json.JSONObject;


import java.util.List;

import static com.boom.tools.Tools.*;

public  abstract   class AppXYChart<T1, T2> extends AppNode {

    // todo Only PieChart is an exception. Think of it later!

    @Override
    public boolean contains(double x,double y){
        return styleableNode.contains(styleableNode.parentToLocal(x,y));
    }

    protected Axis<T1> xAxisArea;
    protected Axis<T2> yAxisArea;
    protected Region plotArea;
    protected ReadOnlyObjectProperty<Bounds> plotAreaBounds;
    protected List<SeriesMarkersStyleProperty> seriesMarkersStyles;
    protected List<SeriesLineStyleProperty> seriesLineStyles;
    protected List<CSSProperty> seriesAreaStyles;

//    protected CSSProperty xAxisStyle = new CSSProperty();
//    protected CSSProperty yAxisStyle = new CSSProperty();
//    protected CSSProperty legendStyle = new CSSProperty();
    public AppXYChart(XYChart<T1, T2> xyChart, double width, double height) {

//        print(Configs.x);
//        Configs.x;
        super(xyChart,"-fx-background-color", "-fx-border-color", "-fx-border-width");
//        backgroundStyle = new BackgroundsProperty("-fx-background-color","-fx-border-color","-fx-border-width");
        xyChart.setPadding(new Insets(0));
        setWidth(width);
        setHeight(height);

        xyChart.lookup(".chart-plot-background").styleProperty().bind(backgroundStyle);

        backgroundStyle.addFill(0, new AppColor(Color.TRANSPARENT));
        backgroundStyle.addStroke(0, new AppColor(Color.TRANSPARENT));
        backgroundStyle.setStrokeWidth(0);

        plotArea = (Region) xyChart.lookup(".chart-plot-background");
        plotAreaBounds = plotArea.boundsInParentProperty();

        bindBorder(xyChart);

        xAxisArea = xyChart.getXAxis();
        yAxisArea = xyChart.getYAxis();

//        print(styleableNode.getBoundsInLocal());
//        print(plotArea.getBoundsInLocal());
//        styleableNode.boundsInLocalProperty().addListener((a,b,c)->{
//            print("node local = "+c);
//        });
//        styleableNode.boundsInParentProperty().addListener((a,b,c)->{
//            print("node parent = "+c);
//        });
//        plotArea.boundsInLocalProperty().addListener((a,b,c)->{
//            print("plot local = "+c);
//        });
//        plotArea.boundsInParentProperty().addListener((a,b,c)->{
//            print("plot parent = "+c);
//        });


    }

    public abstract XYChart.Data<T1, T2> addData(T1 x, T2 y, int seriesIndex, int dataIndex);

    public XYChart.Data<T1, T2> addData(T1 x, T2 y, int seriesIndex) {
        return addData(x, y, seriesIndex, ((XYChart<T1, T2>) styleableNode).getData().get(seriesIndex).getData().size());
    }

    public abstract XYChart.Series<T1, T2> addSeries(int index);

    public XYChart.Series<T1, T2> addSeries() {
        return addSeries(((XYChart<T1, T2>) styleableNode).getData().size());
    }

    public double getHeight() {
        return  ((Region) styleableNode).getPrefHeight();
    }

    public void setHeight(double height) {
        setCustomHeight((Region) styleableNode,height);
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
//        print("==================================================");
//        print(Math.min(dragStartX, currentDragPosX)+" , "+ Math.min(dragStartY, currentDragPosY));
//        print(Math.abs(currentDragPosX - dragStartX)+" , "+ Math.abs(currentDragPosY - dragStartY));
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        setWidth(Math.abs(currentDragPosX - dragStartX));
        setHeight(Math.abs(currentDragPosY - dragStartY));
    }

//    @Override
//    protected void bindBorder(Node binder) {
//
//    }

    @Override
    public String getSVGClones(int tabIndent) {
        return null;
    }

    public double getWidth() {
        return ((Region) styleableNode).getPrefWidth();
    }

    public void setWidth(double width) {
        setCustomWidth((Region) styleableNode,width);
    }

//    @Override
//    public JSONObject toJSON() {
//        return null;
//    }

//    @Override
//    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
//        node.setVisible(true);
//        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
//        setCustomSize((Region) node, Math.abs(currentDragPosX - dragStartX), Math.abs(currentDragPosY - dragStartY));
//    }
//
//    @Override
//    public void setBackgroundColor(Paint paint) {
//
//    }
//
//    @Override
//    public void setBorderColor(Paint paint) {
//
//    }

    public CSSProperty getBackgroundStyle() {
        return backgroundStyle;
    }

    public List<CSSProperty> getSeriesAreaStyles() {
        return seriesAreaStyles;
    }

    public List<SeriesLineStyleProperty> getSeriesLineStyles() {
        return seriesLineStyles;
    }

    public List<SeriesMarkersStyleProperty> getSeriesMarkersStyles() {
        return seriesMarkersStyles;
    }

    public abstract void removeSeries(int index);

//    public void setBackgroundColor(Color color) {
////        backgroundStyle.fill.set(color);
//    }
//
//    public void setBorderColor(Color color) {
////        backgroundStyle.stroke.set(color);
//    }
//
//    @Override
//    public void setBorderDashArray(List<Double> borderDashArray) {
//
//    }
//
//    @Override
//    public void setBorderWidth(double borderWidth) {
////        ((Region) node).setBorder(Border.stroke());
//    }

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


//@Override
//    protected void bindBorder(Node binder) {
////    print(uuid(789));
//        border.setFill(Color.TRANSPARENT);
//        border.setStroke(Color.BLACK);
//        border.setMouseTransparent(true);
//        border.setStrokeWidth(1);
//        border.getStrokeDashArray().addAll(5.0, 6.0, 6.0, 6.0);
//        border.setVisible(false);
////        if(binder.getClass().getName().equals(Line.class.getName())) {
////            print(binder.getClass().getName());
////            print(binder.getBoundsInParent());
////        }
//        border.setX(binder.getBoundsInParent().getMinX());
//        border.setY(binder.getBoundsInParent().getMinY());
//        border.setWidth(binder.getBoundsInParent().getWidth());
//        border.setHeight(binder.getBoundsInParent().getHeight());
//        binder.boundsInParentProperty().addListener((a, b, c) -> {
////            if(binder.getClass().getName().equals(Line.class.getName())) {
////                print(binder.getClass().getName());
////                print(c);
////            }
//            border.setX(c.getMinX()+plotAreaBounds.get().getMinX());
//            border.setY(c.getMinY()+plotAreaBounds.get().getMinX());
//            border.setWidth(plotAreaBounds.get().getWidth());
//            border.setHeight(plotAreaBounds.get().getHeight());
//        });
//    }


}