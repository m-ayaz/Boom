package com.boom.appcharts.baseclasses;


import com.boom.structures.abstracts.App2DChart;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.getScientificRepresentation;
import static com.boom.tools.Tools.print;

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

    public int getSeriesIndex(AppSeries appSeries){
        return seriesList.indexOf(appSeries);
    }

    //todo: shape, fill, stroke and stroke width should change into AppNode and BackgroundStyle
    public final SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty(0.05);
    public final SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty(0.05);
    public final SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty(0.1);
    public final SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty(0.1);
    private final SimpleDoubleProperty globalMinX = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty globalMaxX = new SimpleDoubleProperty(1);
    private final SimpleDoubleProperty globalMinY = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty globalMaxY = new SimpleDoubleProperty(1);
    public final AppXAxisRegion xAxisRegion = new AppXAxisRegion(width);
    public final AppYAxisRegion yAxisRegion = new AppYAxisRegion(height);
    public final AppGridLines appGridLines = new AppGridLines(width, height, xAxisRegion.ticks, yAxisRegion.ticks);
    private final DoubleBinding minXVisualLocation = width.multiply(leftPlotMargin);
    private final DoubleBinding minYVisualLocation = height.multiply(bottomPlotMargin.multiply(-1).add(1));
    private final DoubleBinding maxXVisualLocation = width.multiply(rightPlotMargin.multiply(-1).add(1));
    private final DoubleBinding maxYVisualLocation = height.multiply(topPlotMargin);
    double xTickLabelsWidth = 30;
    double yTickLabelsHeight = 20;

//    public List<AppPolygon> seriesAreaList = new ArrayList<>();
//    public List<AppPolyline> seriesLineList = new ArrayList<>();
//    public List<Group> seriesRenderedMarkersList = new ArrayList<>();


    public AppAxisChart(double width, double height) {
        super(width, height);

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


        xAxisRegion.translateYProperty().bind(this.height);
        yAxisRegion.translateXProperty().bind(yAxisRegion.widthProperty().multiply(-1));

//        globalMinX.addListener((a,b,c)->{
//            if(c.doubleValue()==Double.POSITIVE_INFINITY){
//                globalMinX.set(0);
//            }
//        });
//        globalMaxX.addListener((a,b,c)->{
//            if(c.doubleValue()==Double.NEGATIVE_INFINITY){
//                globalMaxX.set(1);
//            }
//        });
//        globalMinY.addListener((a,b,c)->{
//            if(c.doubleValue()==Double.POSITIVE_INFINITY){
//                globalMinY.set(0);
//            }
//        });
//        globalMaxY.addListener((a,b,c)->{
//            if(c.doubleValue()==Double.NEGATIVE_INFINITY){
//                globalMaxY.set(1);
//            }
//        });


//        //todo temp test
//
        xAxisRegion.setBackground(Background.fill(new Color(1, 0, 1, 0.2)));
        yAxisRegion.setBackground(Background.fill(new Color(0, 1, 1, 0.2)));
//
//
//        legendRegion.setStyle("-fx-background-color: white;-fx-border-color: black");
//
//
//        setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-border-width: 0px");
//
//        //todo note to assign backgroundStyle to plotRegion in a Wrapper class of this object...
//        setStyle("-fx-border-width: 1;-fx-border-color: black;-fx-background-color: transparent");
//
////        xAxisRegion.label.setText(uuid(50));
////        yAxisRegion.label.setText(uuid(50));
//
        appGridLines.xGridLinesStrokeDashArray.setAll(10., 5.);
        appGridLines.yGridLinesStrokeDashArray.setAll(10., 5.);
//
////        globalMinX.addListener((a,b,c)->{
////            print("globalMinX = "+globalMinX.get());
////        });


    }

    @Override
    public void addSeries(int seriesIndex, AppSeries appSeries) {
        seriesList.add(seriesIndex, appSeries);
        appSeries.changeIndicator.addListener((a, b, c) -> {
            globalMinX.set(seriesList.stream().filter(AppSeries::isNotEmpty).mapToDouble(AppSeries::getMinX).min().orElse(0));
//            print(seriesList.size());
            globalMinY.set(seriesList.stream().filter(AppSeries::isNotEmpty).mapToDouble(AppSeries::getMinY).min().orElse(0));
//            print(seriesList.size());
            globalMaxX.set(seriesList.stream().filter(AppSeries::isNotEmpty).mapToDouble(AppSeries::getMaxX).max().orElse(1));
//            print(seriesList.size());
            globalMaxY.set(seriesList.stream().filter(AppSeries::isNotEmpty).mapToDouble(AppSeries::getMaxY).max().orElse(1));
//            print(seriesList.size());
            updateSeriesPreviewAtChart(appSeries);
        });
        getChildren().add(3 * seriesIndex, appSeries.plotArea.wrappedNode);
        getChildren().add(3 * seriesIndex + 1, appSeries.plotLine.wrappedNode);
        getChildren().add(3 * seriesIndex + 2, appSeries.renderedMarkers);
        legendRegion.addSeries(seriesIndex, appSeries.getVisualLegend(), appSeries.title);
    }

    @Override
    public void addData(int seriesIndex, int dataIndex, double[] data) {
        seriesList.get(seriesIndex).addData(data[0],data[1]);
    }

    public double[] getUpdatedTicks(double minD, double maxD, double minDVisualLocation, double maxDVisualLocation, double ticksVisualMargin) {
        if (minD == maxD) {
            return new double[]{0, 0, maxD, 0};
        } else {

            int numberOfTicks = (int) Math.floor(Math.abs(maxDVisualLocation - minDVisualLocation) / ticksVisualMargin);

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

            if (bestL1 == Double.MAX_VALUE || bestL2 == Double.MAX_VALUE || bestK == Double.MAX_VALUE || bestP == Double.MAX_VALUE) {
                return new double[]{0, 0, 0, 0};
            } else {
                return new double[]{bestL1, bestL2, bestK, bestP};
            }

        }
    }

    @Override
    public void removeSeries(int seriesIndex) {
        seriesList.remove(seriesIndex);
        getChildren().remove(3 * seriesIndex + 2);
        getChildren().remove(3 * seriesIndex + 1);
        getChildren().remove(3 * seriesIndex);
        legendRegion.removeSeries(seriesIndex);
    }

    @Override
    public void removeData(int seriesIndex, int dataIndex) {
        seriesList.get(seriesIndex).removeData(dataIndex);
    }

    /**
     * The following actions are executed during chart preview initialization:
     * 1-titleRegion, axes regions and plotRegion are added to chart preview.
     * 2- A new layer of grid lines region is added to the children of plotRegion.
     * 3- appLegend is added to the children of plotRegion (up to this step, appLegend always renders on top of all other plotRegion components).
     */
    @Override
    protected void initializeChartPreview() {
        getChildren().addAll(titleRegion, xAxisRegion, yAxisRegion, appGridLines, legendRegion);
    }



    @Override
    protected void updateSeriesPreviewAtChart(AppSeries appSeries) {

        print("yyyyyyyyyyyyyyyyyyyyyyy");
        List<double[]> dataListCopy = appSeries.getSortedCopyOfDataList(appDataComparator);

        if (dataListCopy.size() == 0) {
            return;
        }

        List<double[]> previewCoordinatesList = new ArrayList<>();

        if (dataListCopy.size() == 1) {
            previewCoordinatesList.add(new double[]{minXVisualLocation.get() / 2 + maxXVisualLocation.get() / 2, minYVisualLocation.get() / 2 + maxYVisualLocation.get() / 2});
        } else {
            dataListCopy.forEach(doubles -> previewCoordinatesList.add(new double[]{parseXOnScreen(doubles[0]), parseYOnScreen(doubles[1])}));
        }

        appSeries.plotArea.points.setAll(parseXOnScreen(dataListCopy.get(dataListCopy.size() - 1)[0]), height.get(), parseXOnScreen(dataListCopy.get(0)[0]), height.get());
        previewCoordinatesList.forEach(doubles -> appSeries.plotArea.points.addAll(doubles[0], doubles[1]));

        appSeries.plotLine.points.clear();
        previewCoordinatesList.forEach(doubles -> appSeries.plotLine.points.addAll(doubles[0], doubles[1]));

        if (!appSeries.isMarkerShapeNull()) {
            for (int i = 0; i < previewCoordinatesList.size(); i++) {
                appSeries.renderedMarkers.getChildren().get(i).setTranslateX(previewCoordinatesList.get(i)[0]);
                appSeries.renderedMarkers.getChildren().get(i).setTranslateY(previewCoordinatesList.get(i)[1]);
            }
        }

        print("Gooooool");

    }

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

    private double parseXOnScreen(double x) {
        return (x - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (maxXVisualLocation.get() - minXVisualLocation.get()) + minXVisualLocation.get();
    }

    private double parseYOnScreen(double y) {
        return (y - globalMinY.get()) / (globalMaxY.get() - globalMinY.get()) * (maxYVisualLocation.get() - minYVisualLocation.get()) + minYVisualLocation.get();
    }

    private void updateXTicks() {

        xAxisRegion.ticks.clear();

        double[] ticksInfo = getUpdatedTicks(globalMinX.get(), globalMaxX.get(), leftPlotMargin.get() * width.get(), (1 - rightPlotMargin.get()) * width.get(), xTickLabelsWidth);

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

        double[] ticksInfo = getUpdatedTicks(globalMinY.get(), globalMaxY.get(), (1 - bottomPlotMargin.get()) * height.get(), topPlotMargin.get() * height.get(), yTickLabelsHeight);

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

    public AppSeries getSeries(int seriesIndex){
        return seriesList.get(seriesIndex);
    }

    public int getNumberOfSeries(){
        return seriesList.size();
    }

}