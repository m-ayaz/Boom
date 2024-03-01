package com.boom.appcharts;


import com.boom.appcharts.baseclasses.AppAxisChart;
import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.apppaints.AppColor;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppNode;
import com.boom.styles.CSSProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;
import static com.boom.tools.Tools.print;

//todo: disallow addition of duplicate series or data

public class AppAxisChartWrapper extends AppNode {

    public int getSeriesIndex(AppSeries appSeries){
        return ((AppAxisChart) wrappedNode).getSeriesIndex(appSeries);
    }

    public int getNumberOfSeries(){
        return ((AppAxisChart) wrappedNode).getNumberOfSeries();
    }

    public AppSeries getSeries(int seriesIndex){
        return ((AppAxisChart) wrappedNode).getSeries(seriesIndex);
    }

    public void addData(int seriesIndex,int dataIndex,double[] data){
        ((AppAxisChart) wrappedNode).addData(seriesIndex,dataIndex,data);
    }

    public void removeData(int seriesIndex, int dataIndex){
        ((AppAxisChart) wrappedNode).removeData(seriesIndex,dataIndex);
    }

    public SimpleDoubleProperty width, height;

    public CSSProperty legendsContainerBackgroundStyle = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");


    public  SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty();
    public  SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty();
    public  SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty();
    public  SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty();
    public  SimpleDoubleProperty appLegendRelativeX = new SimpleDoubleProperty(1);
    public  SimpleDoubleProperty appLegendRelativeY = new SimpleDoubleProperty(1);
    public  SimpleIntegerProperty appLegendAnchorX = new SimpleIntegerProperty(0);
    public  SimpleIntegerProperty appLegendAnchorY = new SimpleIntegerProperty(1);
//    public final SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty();
//    public final SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty();
//    public final SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty();
//    public final SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty();

    public void addSeries(AppSeries appSeries){
        ((AppAxisChart) wrappedNode).addSeries(appSeries);
    }

    public void addSeries(int seriesIndex,AppSeries appSeries){
        ((AppAxisChart) wrappedNode).addSeries(seriesIndex,appSeries);
    }

    public void removeSeries(int seriesIndex){
        ((AppAxisChart) wrappedNode).removeSeries(seriesIndex);
    }

    public AppAxisChartWrapper(double width, double height) {
        super(new AppAxisChart(width, height), "-fx-background-color", "-fx-border-color", "-fx-border-width");
        ((AppAxisChart) wrappedNode).getAppLegendStyleProperty().bind(legendsContainerBackgroundStyle);

        this.type = AppAxisChartWrapper.class.getSimpleName();

        bindBorder(wrappedNode);
        bindPropsToChart();

        backgroundStyle.addStroke(new AppColor(Color.BLACK));
        backgroundStyle.setStrokeWidth(1);
    }

    private void bindPropsToChart() {
//        leftPlotMargin.bindBidirectional(((AppAxisChart) styleableNode).leftPlotMargin);
//        rightPlotMargin.bindBidirectional(((AppAxisChart) styleableNode).rightPlotMargin);
//        topPlotMargin.bindBidirectional(((AppAxisChart) styleableNode).topPlotMargin);
//        bottomPlotMargin.bindBidirectional(((AppAxisChart) styleableNode).bottomPlotMargin);
        width = ((AppAxisChart) wrappedNode).width;
        height = ((AppAxisChart) wrappedNode).height;
        leftPlotMargin = ((AppAxisChart) wrappedNode).leftPlotMargin;
        rightPlotMargin = ((AppAxisChart) wrappedNode).rightPlotMargin;
        topPlotMargin = ((AppAxisChart) wrappedNode).topPlotMargin;
        bottomPlotMargin = ((AppAxisChart) wrappedNode).bottomPlotMargin;
        appLegendRelativeX = ((AppAxisChart) wrappedNode).appLegendRelativeX;
        appLegendRelativeY = ((AppAxisChart) wrappedNode).appLegendRelativeY;
        appLegendAnchorX = ((AppAxisChart) wrappedNode).appLegendAnchorX;
        appLegendAnchorY = ((AppAxisChart) wrappedNode).appLegendAnchorY;
    }

    @Override
    public void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY, double pressX, double pressY, double releaseX, double releaseY, double clickX, double clickY, double x, double y) {
        if (configStep == 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            configStep++;
        } else if (configStep == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            draw(pressX, pressY, moveX, moveY);
        } else if (configStep == 1 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            mainCanvasItemsHandler.copyToMainCanvas(this);
            configStep = 0;
        }
    }

    @Override
    public AppAxisChartWrapper copy() {

        if (width.get() == 0 || height.get() == 0) {
            return null;
        }

//        print("width = %f , height = %f".formatted(width.get(),height.get()));

        AppAxisChartWrapper newAppAxisChartWrapper = new AppAxisChartWrapper(0,0);
        newAppAxisChartWrapper.width.set(width.get());
        newAppAxisChartWrapper.height.set(height.get());
        deepCopy(affineTransform, newAppAxisChartWrapper.affineTransform);
        deepCopy(backgroundStyle, newAppAxisChartWrapper.backgroundStyle);
        deepCopy(legendsContainerBackgroundStyle, newAppAxisChartWrapper.legendsContainerBackgroundStyle);

//        print("x grid visible = "+((AppAxisChart)newAppAxisChartWrapper.styleableNode).appGridLines.xGridLinesVisible);
//        print("y grid visible = "+((AppAxisChart)newAppAxisChartWrapper.styleableNode).appGridLines.yGridLinesVisible);
//
//
//        print(((AppAxisChart)newAppAxisChartWrapper.styleableNode).appGridLines.xGridLines.getChildren());
//        print(((AppAxisChart)newAppAxisChartWrapper.styleableNode).appGridLines.yGridLines.getChildren());
//        print(this.);
//        return newAppRectangle;

        return newAppAxisChartWrapper;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        wrappedNode.setVisible(true);
        affineTransform.setToTransform(new Translate(Math.min(dragStartX, currentDragPosX), Math.min(dragStartY, currentDragPosY)));
        width.set(Math.abs(currentDragPosX - dragStartX));
        height.set(Math.abs(currentDragPosY - dragStartY));
    }

    @Override
    public String toSVG(int tabIndent) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        return wrappedNode.contains(wrappedNode.parentToLocal(x, y));
    }

    @Override
    public String toTeX() {
        return null;
    }
}