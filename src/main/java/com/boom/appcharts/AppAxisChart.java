package com.boom.appcharts;


import com.boom.appshapes.AppPolygon;
import com.boom.appshapes.AppPolyline;
import com.boom.test.AppDataComparator;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.getScientificRepresentation;
import static com.boom.tools.Tools.uuid;

// todo: disallow addition of duplicate series or data

/**
 * All data across all series are double Strings. They can be converted into doubles if interpretable as such.
 * <p>
 * (anchorX,anchorY)={
 * (0,0) if SOUTHWEST,
 * (0,1) if NORTHWEST,
 * (1,0) if SOUTHEAST,
 * (1,1) if NORTHEAST.
 * }
 */

public class AppAxisChart extends App2DChart {

    //todo: shape, fill, stroke and stroke width should change into AppNode and BackgroundStyle
    public final SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty(0.05);
    public final SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty(0.05);
    public final SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty(0.1);
    public final SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty(0.1);
    private final SimpleDoubleProperty globalMinX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty globalMaxX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty globalMinY = new SimpleDoubleProperty();
    private final SimpleDoubleProperty globalMaxY = new SimpleDoubleProperty();
    private final AppXAxisRegion xAxisRegion = new AppXAxisRegion(width);
    private final AppYAxisRegion yAxisRegion = new AppYAxisRegion(height);
    private final AppGridLines appGridLines = new AppGridLines(width, height, xAxisRegion.ticks, yAxisRegion.ticks);
    private final DoubleBinding minXVisualLocation = width.multiply(leftPlotMargin);
    private final DoubleBinding minYVisualLocation = height.multiply(bottomPlotMargin.multiply(-1).add(1));
    private final DoubleBinding maxXVisualLocation = width.multiply(rightPlotMargin.multiply(-1).add(1));
    private final DoubleBinding maxYVisualLocation = height.multiply(topPlotMargin);
    double xTickLabelsWidth = 30;
    double yTickLabelsHeight = 20;

    public List<AppPolygon> seriesAreaList = new ArrayList<>();
    public List<AppPolyline> seriesLineList = new ArrayList<>();
    public List<Group> seriesRenderedMarkersList = new ArrayList<>();



    public AppAxisChart(double width, double height) {
        super(width,height);

        this.width.set(width);
        this.height.set(height);

        initializeChartPreview();

//        bindPlotRegionSize();


        bindYTicksVisuals();

        bindXTicksVisuals();

//        bindAppLegendLocation();


        minXVisualLocation.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        minYVisualLocation.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        maxXVisualLocation.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());
        maxYVisualLocation.addListener((a, b, c) -> updateAllSeriesPreviewsAtChart());


        //todo temp test

        xAxisRegion.setBackground(Background.fill(new Color(1, 0, 1, 0.2)));
        yAxisRegion.setBackground(Background.fill(new Color(0, 1, 1, 0.2)));


        legendRegion.setStyle("-fx-background-color: white;-fx-border-color: black");


        setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-border-width: 0px");

        //todo note to assign backgroundStyle to plotRegion in a Wrapper class of this object...
        plotRegion.setStyle("-fx-border-width: 1;-fx-border-color: black");

        xAxisRegion.label.setText(uuid(50));
        yAxisRegion.label.setText(uuid(50));

        appGridLines.xGridLinesStrokeDashArray.setAll(10.,5.);
        appGridLines.yGridLinesStrokeDashArray.setAll(10.,5.);
//        appGridLines.xGridLines.getChildren().forEach(node -> {
//            ((Line)node).setStroke(Color.valueOf("555555ff"));
//        });
//        appGridLines.yGridLines.getChildren().forEach(node -> {
//            ((Line)node).setStroke(Color.valueOf("555555ff"));
//        });


    }

//    public void addSeries(AppSeries appSeries) {
//        addSeries(seriesList.size(), appSeries);
//    }

    @Override
    public void addSeries(int seriesIndex, AppSeries appSeries) {
        seriesList.add(seriesIndex, appSeries);
        appSeries.dataList.addListener((ListChangeListener<double[]>) change -> updateSeriesPreviewAtChart(appSeries));
        appSeries.minX.addListener((a, b, c) -> globalMinX.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.minX.get()).min().orElse(Double.POSITIVE_INFINITY)));
        appSeries.maxX.addListener((a, b, c) -> globalMaxX.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.maxX.get()).max().orElse(Double.NEGATIVE_INFINITY)));
        appSeries.minY.addListener((a, b, c) -> globalMinY.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.minY.get()).min().orElse(Double.POSITIVE_INFINITY)));
        appSeries.maxY.addListener((a, b, c) -> globalMaxY.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.maxY.get()).max().orElse(Double.NEGATIVE_INFINITY)));
        plotRegion.getChildren().add(3 * seriesIndex, appSeries.plotArea.styleableNode);
        plotRegion.getChildren().add(3 * seriesIndex + 1, appSeries.plotLine.styleableNode);
        plotRegion.getChildren().add(3 * seriesIndex + 2, appSeries.renderedMarkers);
        legendRegion.addSeries(seriesIndex, appSeries.getVisualLegend(), appSeries.title);
    }

//    public StringProperty getAppLegendStyleProperty() {
//        return legendRegion.styleProperty();
//    }
//
//    public SimpleDoubleProperty getAppLegendTitleVisualMarginProperty() {
//        return legendRegion.titleVisualMargin;
//    }
//
//    public void removeAllSeries() {
//        while (seriesList.size() != 0) {
//            removeSeries(0);
//        }
//    }
@Override
    public void removeSeries(int seriesIndex) {
        seriesList.remove(seriesIndex);
        plotRegion.getChildren().remove(3 * seriesIndex + 2);
        plotRegion.getChildren().remove(3 * seriesIndex + 1);
        plotRegion.getChildren().remove(3 * seriesIndex);
        legendRegion.removeSeries(seriesIndex);
    }

//    public void updateAllSeriesPreviewsAtChart() {
//        seriesList.forEach(this::updateSeriesPreviewAtChart);
//    }

    @Override
    protected void updateSeriesPreviewAtChart(AppSeries appSeries) {

        List<double[]> dataListCopy = appSeries.dataList.stream().map(doubles -> new double[]{doubles[0], doubles[1]}).sorted(appDataComparator).toList();

        List<double[]> previewCoordinatesList = new ArrayList<>();

        if (appSeries.dataList.size() == 1) {
            previewCoordinatesList.add(new double[]{minXVisualLocation.get() / 2 + maxXVisualLocation.get() / 2, minYVisualLocation.get() / 2 + maxYVisualLocation.get() / 2});
        } else {
            dataListCopy.forEach(doubles -> previewCoordinatesList.add(new double[]{parseXOnScreen(doubles[0]), parseYOnScreen(doubles[1])}));
        }

        appSeries.plotArea.points.setAll(parseXOnScreen(dataListCopy.get(dataListCopy.size() - 1)[0]), height.get(), parseXOnScreen(dataListCopy.get(0)[0]), height.get());
        previewCoordinatesList.forEach(doubles -> appSeries.plotArea.points.addAll(doubles[0], doubles[1]));

        appSeries.plotLine.points.clear();
        previewCoordinatesList.forEach(doubles -> appSeries.plotLine.points.addAll(doubles[0], doubles[1]));

        if (appSeries.markerShape.get() != null) {
            for (int i = 0; i < previewCoordinatesList.size(); i++) {
                appSeries.renderedMarkers.getChildren().get(i).setTranslateX(previewCoordinatesList.get(i)[0]);
                appSeries.renderedMarkers.getChildren().get(i).setTranslateY(previewCoordinatesList.get(i)[1]);
            }
        }

    }

//    private void bindAppLegendLocation() {
//        legendRegion.translateXProperty().bind(appLegendRelativeX.multiply(width).subtract(legendRegion.widthProperty().multiply(appLegendAnchorX)));
//        legendRegion.translateYProperty().bind(appLegendRelativeY.multiply(-1).add(1).multiply(width).subtract(legendRegion.heightProperty().multiply(appLegendAnchorY.multiply(-1).add(1))));
//    }

//    /**
//     * Binds plot region width and height to plotRegionWidth and plotRegionHeight. When plot region
//     * width and height change, the location of all series data are updated.
//     */
//    private void bindPlotRegionSize() {
//        plotRegion.minHeightProperty().bindBidirectional(height);
//        plotRegion.maxHeightProperty().bindBidirectional(height);
//        plotRegion.prefHeightProperty().bindBidirectional(height);
//        plotRegion.prefWidthProperty().bindBidirectional(width);
//        plotRegion.minWidthProperty().bindBidirectional(width);
//        plotRegion.maxWidthProperty().bindBidirectional(width);
//
//    }

    private void bindXTicksVisuals() {
        leftPlotMargin.addListener((a, b, c) -> updateXTicks());
        rightPlotMargin.addListener((a, b, c) -> updateXTicks());
        width.addListener((a, b, c) -> updateXTicks());
        globalMinX.addListener((a, b, c) -> updateXTicks());
        globalMaxX.addListener((a, b, c) -> updateXTicks());
    }

    private void bindYTicksVisuals() {
        topPlotMargin.addListener((a, b, c) -> updateYTicks());
        bottomPlotMargin.addListener((a, b, c) -> updateYTicks());
        height.addListener((a, b, c) -> updateYTicks());
        globalMinY.addListener((a, b, c) -> updateYTicks());
        globalMaxY.addListener((a, b, c) -> updateYTicks());
    }

    /**
     * The following actions are executed during chart preview initialization:
     * 1-titleRegion, axes regions and plotRegion are added to chart preview.
     * 2- A new layer of grid lines region is added to the children of plotRegion.
     * 3- appLegend is added to the children of plotRegion (up to this step, appLegend always renders on top of all other plotRegion components).
     */
    @Override
    protected void initializeChartPreview() {

//        titleRegion.getChildren().add(tit);

        addRow(0, new HBox(), titleRegion);
        addRow(1, yAxisRegion, plotRegion);
        addRow(2, new HBox(), xAxisRegion);

        plotRegion.getChildren().add(appGridLines);

        plotRegion.getChildren().add(legendRegion);

    }

    private double parseXOnScreen(double x) {
        return (x - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get();
    }

    private double parseYOnScreen(double y) {
        return (y - globalMinY.get()) / (globalMaxY.get() - globalMinY.get()) * (maxYVisualLocation.get() - minYVisualLocation.get()) + minYVisualLocation.get();
    }

    private void updateXTicks() {

        xAxisRegion.ticks.clear();

        if (seriesList.size() == 0) {
            return;
        }

        double[] ticksInfo=getUpdatedTicks(globalMinX.get() , globalMaxX.get(), leftPlotMargin.get() * width.get(),(1 -  rightPlotMargin.get()) * width.get(),xTickLabelsWidth);

        if (globalMinX.get() == globalMaxX.get()) {
            double temp = globalMaxX.get();
            double temp1 = parseXOnScreen(temp);
            xAxisRegion.ticks.put(temp1, temp + "");
        } else {
            for (int j = (int) ticksInfo[0]; j <= ticksInfo[1]; j++) {
                double temp = 1.0 * j / ticksInfo[2] / Math.pow(10, ticksInfo[3]);
                double temp1 = parseXOnScreen(temp);
                xAxisRegion.ticks.put(temp1, getScientificRepresentation(temp, 3));
            }
        }


    }

    private void updateYTicks() {

        yAxisRegion.ticks.clear();

        if (seriesList.size() == 0) {
            return;
        }

        double[] ticksInfo=getUpdatedTicks(globalMinY.get() , globalMaxY.get(), (1 -  bottomPlotMargin.get()) * height.get(),topPlotMargin.get() * height.get(),yTickLabelsHeight);

        if (globalMinY.get() == globalMaxY.get()) {
            double temp = globalMaxY.get();
            double temp1 = parseYOnScreen(temp);
            yAxisRegion.ticks.put(temp1, temp + "");
        } else {
            for (int j = (int) ticksInfo[0]; j <= ticksInfo[1]; j++) {
                double temp = 1.0 * j / ticksInfo[2] / Math.pow(10, ticksInfo[3]);
                double temp1 = (temp - globalMinY.get()) / (globalMaxY.get() - globalMinY.get()) * height.get() * (topPlotMargin.get() + bottomPlotMargin.get() - 1) + height.get() * (1 - bottomPlotMargin.get());
                yAxisRegion.ticks.put(temp1, getScientificRepresentation(temp, 3));
            }
        }


    }

    private double[] getUpdatedTicks(double minD,double maxD,double minDVisualLocation,double maxDVisualLocation,double ticksVisualMargin){
        if (minD==maxD) {
            return new double[]{0,0,maxD,0};
        } else {

            int numberOfTicks = (int) Math.floor(Math.abs(maxDVisualLocation-minDVisualLocation)  / ticksVisualMargin);

            int l1, l2;

            double tickMarginDistance = Double.MAX_VALUE;
            double bestK = Double.MAX_VALUE;
            double bestP = Double.MAX_VALUE;
            double bestL1 = Double.MAX_VALUE;
            double bestL2 = Double.MAX_VALUE;
            int bestNumberOfTicks = -1;

            for (int k : new int[]{1, 2, 4, 5}) {

                int pNess = (int) Math.ceil(Math.log10(1.0 * (numberOfTicks - 1) / k / (maxD - minD)));
                for (int p = pNess; ; p--) {

                    l1 = (int) Math.floor(minD * k * Math.pow(10, p));
                    l2 = (int) Math.ceil(maxD * k * Math.pow(10, p));
                    if (l2 - l1 == 1) {
                        break;
                    }
                    if (l2 - l1 + 1 <= numberOfTicks && l2 - l1 + 1 > bestNumberOfTicks) {
                        double v = 1.0 * (l2 - l1) / k / Math.pow(10, p);
                        if (minD - maxD + v <= tickMarginDistance) {
                            tickMarginDistance = minD - maxD + v;
                            bestK = k;
                            bestP = p;
                            bestL1 = l1;
                            bestL2 = l2;
                            bestNumberOfTicks = l2 - l1 + 1;
                        }
                        break;
                    }
                }
            }

            return new double[]{bestL1,bestL2,bestK,bestP};

        }
    }


}













