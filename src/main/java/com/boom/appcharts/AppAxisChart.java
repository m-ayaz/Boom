package com.boom.appcharts;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.*;

// todo: disallow addition of duplicate series or data

/**
 * All data across all series are double Strings. They can be converted into doubles if interpretable as such.
 */

public class AppAxisChart extends GridPane {

//    List<List<AppData>> seriesList = new ArrayList<>();


    private final List<AppSeries> seriesList = new ArrayList<>();

    public SimpleDoubleProperty plotRegionWidth = new SimpleDoubleProperty();
    public SimpleDoubleProperty plotRegionHeight = new SimpleDoubleProperty();
    public SimpleDoubleProperty xAxisRegionHeight = new SimpleDoubleProperty();

    //todo: shape, fill, stroke and stroke width should change into AppNode and BackgroundStyle
    public SimpleDoubleProperty yAxisRegionWidth = new SimpleDoubleProperty();
    public SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty(0.1);
    public SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty(0.1);
    Pane legendsContainer = new Pane();

    public SimpleDoubleProperty globalMinX = new SimpleDoubleProperty();
    public SimpleDoubleProperty globalMaxX = new SimpleDoubleProperty();
    public SimpleDoubleProperty globalMinY = new SimpleDoubleProperty();
    public SimpleDoubleProperty globalMaxY = new SimpleDoubleProperty();


    //    boolean isAutoSetXTicks = true, isAutoSetYTicks = true;
    private Group xGridLines = new Group();
    private Group yGridLines = new Group();
    Pane plotRegion = new Pane();
    //    AppDataComparator appDataComparator = new AppDataComparator();
    Pane xAxisRegion = new Pane();
    Pane yAxisRegion = new Pane();
    //    double plotWidth, plotHeight;
//    double minXData = Double.POSITIVE_INFINITY, maxXData = Double.NEGATIVE_INFINITY,
//            minYData = Double.POSITIVE_INFINITY, maxYData = Double.NEGATIVE_INFINITY;
//    AppDataSortingPolicy appDataSortingPolicy= AppDataSortingPolicy.NoSort;

//    List<Double> temp = new ArrayList<>();
//    Supplier<DoubleStream> tempSeriesXListSupplier, tempSeriesYListSupplier;

//    SimpleIntegerProperty numberOfXTicks = new SimpleIntegerProperty(10);
//    SimpleIntegerProperty numberOfYTicks = new SimpleIntegerProperty(10);
    List<String> customXTicks = new ArrayList<>();
    List<String> customYTicks = new ArrayList<>();
    double xTicksWidth = 30;
//    double xTicksHeight = 20;
//    double yTicksWidth = 20;
    double yTicksHeight = 20;


//    List<Line> yTicksVisuals=new ArrayList<>();

//    SimpleIntegerProperty numberOfUnNumerableXData = new SimpleIntegerProperty(0);
//    SimpleIntegerProperty numberOfUnNumerableYData = new SimpleIntegerProperty(0);

//    double minX = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[0]).min().orElse(Double.POSITIVE_INFINITY);
//    double maxX = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[0]).max().orElse(Double.NEGATIVE_INFINITY);
//    double minY = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[1]).min().orElse(Double.POSITIVE_INFINITY);
//    double maxY = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[1]).max().orElse(Double.NEGATIVE_INFINITY);

//    Rectangle emptySpace=new Rectangle();

    public AppAxisChart() {


        setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-border-width: 0px");

        //todo need to assign backgroundStyle to plotRegion...
        plotRegion.setStyle("-fx-border-width: 1;-fx-border-color: black");

        addRow(0,new HBox(),new HBox());
        addRow(1,yAxisRegion,plotRegion);
        addRow(2,new HBox(),xAxisRegion);
//        setCenter(plotRegion);
//        setLeft(yAxisRegion);
//        setBottom(new HBox(emptySpace,xAxisRegion));

        xAxisRegion.setBackground(Background.fill(new Color(1, 0, 1, 0.2)));
        yAxisRegion.setBackground(Background.fill(new Color(0, 1, 1, 0.2)));

        bindPlotRegionSize();

        bindAxesRegionsSize();

        bindYTicksVisuals();

        bindXTicksVisuals();






//        xAxisRegion.setBackground(Background.fill(Color.valueOf("ff000022")));
//        plotRegionHeight.addListener((a,b,c)->{
//            print(c);
//        });

    }

    public void addSeries(AppSeries appSeries) {
        addSeries(seriesList.size(), appSeries);
    }

    public void addSeries(int seriesIndex, AppSeries appSeries) {
        appSeries.bindDataBounds(globalMinX, globalMaxX, globalMinY, globalMaxY);
        appSeries.bindVisualBounds(
                plotRegionWidth.multiply(leftPlotMargin),
                plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)),
                plotRegionWidth.multiply(rightPlotMargin.multiply(-1).add(1)),
                plotRegionHeight.multiply(topPlotMargin)
        );
        seriesList.add(seriesIndex, appSeries);
        appSeries.localMinX.addListener((a, b, c) -> globalMinX.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.localMinX.get()).min().orElse(Double.POSITIVE_INFINITY)));
        appSeries.localMaxX.addListener((a, b, c) -> globalMaxX.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.localMaxX.get()).max().orElse(Double.NEGATIVE_INFINITY)));
        appSeries.localMinY.addListener((a, b, c) -> globalMinY.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.localMinY.get()).min().orElse(Double.POSITIVE_INFINITY)));
        appSeries.localMaxY.addListener((a, b, c) -> globalMaxY.set(seriesList.stream().mapToDouble(appSeries1 -> appSeries1.localMaxY.get()).max().orElse(Double.NEGATIVE_INFINITY)));
        plotRegion.getChildren().add(3 * seriesIndex, appSeries.plotArea.getStyleableNode());
        plotRegion.getChildren().add(3 * seriesIndex + 1, appSeries.plotLine.getStyleableNode());
        plotRegion.getChildren().add(3 * seriesIndex + 2, appSeries.renderedMarkers);
    }

    public void removeAllSeries() {
        while(seriesList.size()!=0) {
            removeSeries(0);
        }
    }

    public void removeSeries(int seriesIndex) {
        seriesList.remove(seriesIndex);
        plotRegion.getChildren().remove(3 * seriesIndex + 2);
        plotRegion.getChildren().remove(3 * seriesIndex + 1);
        plotRegion.getChildren().remove(3 * seriesIndex);

    }


    void bindAxesRegionsSize() {
//        xAxisRegion.minWidthProperty().bindBidirectional(plotRegionWidth);
//        xAxisRegion.maxWidthProperty().bindBidirectional(plotRegionWidth);
//        xAxisRegion.prefWidthProperty().bindBidirectional(plotRegionWidth);

        xAxisRegion.minHeightProperty().bindBidirectional(xAxisRegionHeight);
        xAxisRegion.maxHeightProperty().bindBidirectional(xAxisRegionHeight);
        xAxisRegion.prefHeightProperty().bindBidirectional(xAxisRegionHeight);

        yAxisRegion.minWidthProperty().bindBidirectional(yAxisRegionWidth);
        yAxisRegion.maxWidthProperty().bindBidirectional(yAxisRegionWidth);
        yAxisRegion.prefWidthProperty().bindBidirectional(yAxisRegionWidth);

//        yAxisRegion.minHeightProperty().bindBidirectional(plotRegionHeight);
//        yAxisRegion.maxHeightProperty().bindBidirectional(plotRegionHeight);
//        yAxisRegion.prefHeightProperty().bindBidirectional(plotRegionHeight);

//        emptySpace.widthProperty().bindBidirectional(yAxisRegionWidth);
//        emptySpace.heightProperty().bindBidirectional(xAxisRegionHeight);

//        xAxisRegion.translateXProperty().bindBidirectional(yAxisRegionWidth);
//        xAxisRegion.translateYProperty().bind(yAxisRegionWidth.add(100));
    }

    /**
     * Binds plot region width and height to plotRegionWidth and plotRegionHeight. When plot region
     * width and height change, the location of all series data are updated.
     */
    void bindPlotRegionSize() {

        plotRegion.prefWidthProperty().bindBidirectional(plotRegionWidth);
        plotRegion.minWidthProperty().bindBidirectional(plotRegionWidth);
        plotRegion.maxWidthProperty().bindBidirectional(plotRegionWidth);

        plotRegion.prefHeightProperty().bindBidirectional(plotRegionHeight);
        plotRegion.minHeightProperty().bindBidirectional(plotRegionHeight);
        plotRegion.maxHeightProperty().bindBidirectional(plotRegionHeight);

    }

    public List<Double> yTicksPositions=new ArrayList<>();
    public List<Double> xTicksPositions=new ArrayList<>();

    void bindYTicksVisuals(){
        topPlotMargin.addListener((a,b,c)->updateYTicks());
        bottomPlotMargin.addListener((a,b,c)->updateYTicks());
        plotRegionHeight.addListener((a,b,c)->updateYTicks());
        globalMinY.addListener((a,b,c)->updateYTicks());
        globalMaxY.addListener((a,b,c)->updateYTicks());
    }



    public void updateYTicks(){

        if(seriesList.size()==0){
//            print("globalMinY = "+globalMinY.get());
//            print("globalMaxY = "+globalMaxY.get());
            return;
        }

        yTicksPositions.clear();
        yAxisRegion.getChildren().clear();

        if(globalMinY.get()==globalMaxY.get()){
//            print(uuid(400));
            double temp=globalMaxY.get();
            double temp1= (plotRegionHeight.multiply(topPlotMargin).get() - plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get()) + plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get();
            yTicksPositions.add(temp);
            yAxisRegion.getChildren().add(new Line(0,temp1,40,temp1));
            yAxisRegion.getChildren().add(new Text(-40,temp1,temp+""));
        }else {

            int numberOfTicks = (int) Math.floor((1 - topPlotMargin.get() - bottomPlotMargin.get()) * plotRegionHeight.get() / yTicksHeight);

//            print("numberOfTicks = " + numberOfTicks);

            int l1, l2;

            double tickMarginDistance = Double.MAX_VALUE;
            int bestK = Integer.MAX_VALUE;
            int bestP = Integer.MAX_VALUE;
            int bestL1 = Integer.MAX_VALUE;
            int bestL2 = Integer.MAX_VALUE;
            int bestNumberOfTicks = -1;

            double maxTick, minTick;
            for (int k : new int[]{1, 2, 4, 5}) {
//                int pNess;


                maxTick = globalMaxY.get();
                minTick = globalMinY.get();

                int pNess = (int) Math.ceil(Math.log10(1.0 * (numberOfTicks - 1) / k / (maxTick - minTick)));
                for (int p = pNess; ; p--) {

                    l1 = (int) Math.floor(minTick * k * Math.pow(10, p));
                    l2 = (int) Math.ceil(maxTick * k * Math.pow(10, p));
//                print(l2+" , "+l1);
////                print(globalMinY.get()+" ,,,,,,,,,,,,,,,,,,,,,,, "+globalMaxY.get());
//                print(maxTick+"------------------"+minTick);
//                print(k);
//                print(p);

//                print(seriesList.get(0).localMinY.get()+" ,,,,,,,,,,,,,,,,,,,,, "+seriesList.get(0).localMaxY.get());

//                print(seriesList.get(0).appDataList.stream().map(doubles->doubles[0]+" , "+doubles[1]+"\n").toList());
//                print("globalMinY = "+globalMinY.get());
//                print("globalMaxY = "+globalMaxY.get());
//                    print("appSeries1.appDataList = " + seriesList.get(0).appDataList.stream().map(doubles -> "(" + doubles[0] + " , " + doubles[1] + ")").toList());
//                print(seriesList.size());

                    if (l2 - l1 == 1) {
                        break;
                    }
//                if(l2-l1==0){
//                    break;
//                }
//                actualNumberOfTicks=l2-l1+1;
                    if (l2 - l1 + 1 <= numberOfTicks && l2 - l1 + 1 > bestNumberOfTicks) {
//                    print("______________________");
//                    print("L1 , L2 = " + l1 + " , " + l2);
//                    print(l2 - l1 + 1 <= n);
//                    for (int j = l1; j <= l2; j++) {
//                        print(1.0 * j / k / Math.pow(10, p));
//                    }
                        if (minTick - maxTick + 1.0 * (l2 - l1) / k / Math.pow(10, p) <= tickMarginDistance) {
                            tickMarginDistance = minTick - maxTick + 1.0 * (l2 - l1) / k / Math.pow(10, p);
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

//            print(uuid(20));


            for (int j = bestL1; j <= bestL2; j++) {
                double temp = 1.0 * j / bestK / Math.pow(10, bestP);
                double temp1 = (temp - globalMinY.get()) / (globalMaxY.get() - globalMinY.get()) * plotRegionHeight.get()*(topPlotMargin.get() + bottomPlotMargin.get()-1) + plotRegionHeight.get()*(1-bottomPlotMargin.get());
                yTicksPositions.add(temp);
                yAxisRegion.getChildren().add(new Line(0, temp1, 40, temp1));
                yAxisRegion.getChildren().add(new Text(-40, temp1, getScientificRepresentation(temp,3)));
            }
        }



    }










    void bindXTicksVisuals(){
        leftPlotMargin.addListener((a,b,c)->updateXTicks());
        rightPlotMargin.addListener((a,b,c)->updateXTicks());
        plotRegionWidth.addListener((a,b,c)->updateXTicks());
        globalMinX.addListener((a,b,c)->updateXTicks());
        globalMaxX.addListener((a,b,c)->updateXTicks());
    }

    void renderTicks(double minTick,double maxTick,int numberOfTicks){

    }

    public void updateXTicks(){

        if(seriesList.size()==0){
//            print("globalMinX = "+globalMinX.get());
//            print("globalMaxX = "+globalMaxX.get());
            return;
        }

        xTicksPositions.clear();
        xAxisRegion.getChildren().clear();

        if(globalMinX.get()==globalMaxX.get()){
//            print(uuid(400));
            double temp=globalMaxX.get();
            //todo change this
            double temp1=temp;
//            double temp1= (plotRegionWidth.multiply(topPlotMargin).get() - plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get()) + plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get();
            xTicksPositions.add(temp);
            xAxisRegion.getChildren().add(new Line(0,temp1,40,temp1));
            xAxisRegion.getChildren().add(new Text(-40,temp1,temp+""));
        }else {

            int numberOfTicks = (int) Math.floor((1 -leftPlotMargin.get() - rightPlotMargin.get()) * plotRegionWidth.get() / xTicksWidth);

//            print(numberOfTicks);

//            print("numberOfTicks = " + numberOfTicks);

            int l1, l2;

            double tickMarginDistance = Double.MAX_VALUE;
            int bestK = Integer.MAX_VALUE;
            int bestP = Integer.MAX_VALUE;
            int bestL1 = Integer.MAX_VALUE;
            int bestL2 = Integer.MAX_VALUE;
            int bestNumberOfTicks = -1;

            double maxTick, minTick;
            for (int k : new int[]{1, 2, 4, 5}) {
//                int pNess;


                maxTick = globalMaxX.get();
                minTick = globalMinX.get();

                int pNess = (int) Math.ceil(Math.log10(1.0 * (numberOfTicks - 1) / k / (maxTick - minTick)));
                for (int p = pNess; ; p--) {

                    l1 = (int) Math.floor(minTick * k * Math.pow(10, p));
                    l2 = (int) Math.ceil(maxTick * k * Math.pow(10, p));
//                print(l2+" , "+l1);
////                print(globalMinX.get()+" ,,,,,,,,,,,,,,,,,,,,,,, "+globalMaxX.get());
//                print(maxTick+"------------------"+minTick);
//                print(k);
//                print(p);

//                print(seriesList.get(0).localMinX.get()+" ,,,,,,,,,,,,,,,,,,,,, "+seriesList.get(0).localMaxX.get());

//                print(seriesList.get(0).appDataList.stream().map(doubles->doubles[0]+" , "+doubles[1]+"\n").toList());
//                print("globalMinX = "+globalMinX.get());
//                print("globalMaxX = "+globalMaxX.get());
//                    print("appSeries1.appDataList = " + seriesList.get(0).appDataList.stream().map(doubles -> "(" + doubles[0] + " , " + doubles[1] + ")").toList());
//                print(seriesList.size());

                    if (l2 - l1 == 1) {
                        break;
                    }
//                if(l2-l1==0){
//                    break;
//                }
//                actualNumberOfTicks=l2-l1+1;
                    if (l2 - l1 + 1 <= numberOfTicks && l2 - l1 + 1 > bestNumberOfTicks) {
//                    print("______________________");
//                    print("L1 , L2 = " + l1 + " , " + l2);
//                    print(l2 - l1 + 1 <= n);
//                    for (int j = l1; j <= l2; j++) {
//                        print(1.0 * j / k / Math.pow(10, p));
//                    }
                        if (minTick - maxTick + 1.0 * (l2 - l1) / k / Math.pow(10, p) <= tickMarginDistance) {
                            tickMarginDistance = minTick - maxTick + 1.0 * (l2 - l1) / k / Math.pow(10, p);
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

//            print(uuid(20));

//            xAxisRegion.getChildren().clear();
            for (int j = bestL1; j <= bestL2; j++) {
                double temp = 1.0 * j / bestK / Math.pow(10, bestP);
                //todo investigate this. xTicks are not rendered at correct position.
//                plotRegionWidth.multiply(leftPlotMargin),
//                        plotRegionWidth.multiply(rightPlotMargin.multiply(-1).add(1)),
                double temp1 = (temp - globalMinX.get()) / (globalMaxX.get() - globalMinX.get()) * (plotRegionWidth.multiply(rightPlotMargin.multiply(-1).add(1)).get() - plotRegionWidth.multiply(leftPlotMargin).get()) + plotRegionWidth.multiply(leftPlotMargin).get();
                xTicksPositions.add(temp);
                xAxisRegion.getChildren().add(new Line( temp1,0,  temp1,40));
                xAxisRegion.getChildren().add(new Text( temp1,40, getScientificRepresentation(temp,3)));

            }
        }


//        print(emptySpace.getWidth());
//        print(emptySpace.getHeight());

    }



    
    
    
    
    
    
    
    
    
    
    










}