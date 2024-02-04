package com.boom.appcharts;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.*;

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

public class AppAxisChart extends GridPane {

//    List<List<AppData>> seriesList = new ArrayList<>();


    public final SimpleDoubleProperty legendsContainerWidth = new SimpleDoubleProperty();
    public final SimpleDoubleProperty legendsContainerHeight = new SimpleDoubleProperty();
    public final SimpleDoubleProperty legendsContainerRelativeX = new SimpleDoubleProperty(1);
    public final SimpleDoubleProperty legendsContainerRelativeY = new SimpleDoubleProperty(1);
    public final SimpleDoubleProperty plotRegionWidth = new SimpleDoubleProperty();
    public final SimpleDoubleProperty plotRegionHeight = new SimpleDoubleProperty();
    public final SimpleDoubleProperty xAxisRegionHeight = new SimpleDoubleProperty();
    //todo: shape, fill, stroke and stroke width should change into AppNode and BackgroundStyle
    public final SimpleDoubleProperty yAxisRegionWidth = new SimpleDoubleProperty();
    public final SimpleDoubleProperty leftPlotMargin = new SimpleDoubleProperty(0.25);
    public final SimpleDoubleProperty rightPlotMargin = new SimpleDoubleProperty(0.25);
    public final SimpleIntegerProperty anchorX = new SimpleIntegerProperty(1);
    public final SimpleIntegerProperty anchorY = new SimpleIntegerProperty(1);
    public final SimpleStringProperty legendsContainerCSS = new SimpleStringProperty();
    public final Label title = new Label();
    //    List<Line> yTicksVisuals=new ArrayList<>();
    //    List<Double> temp = new ArrayList<>();
//    Supplier<DoubleStream> tempSeriesXListSupplier, tempSeriesYListSupplier;
    public final SimpleDoubleProperty topPlotMargin = new SimpleDoubleProperty(0.25);
    public final SimpleDoubleProperty bottomPlotMargin = new SimpleDoubleProperty(0.25);
    private final List<AppSeries> seriesList = new ArrayList<>();
    private final SimpleDoubleProperty globalMinX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty globalMaxX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty globalMinY = new SimpleDoubleProperty();
    private final SimpleDoubleProperty globalMaxY = new SimpleDoubleProperty();
    private final ObservableList<Double> yTickPositions = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<Double> xTickPositions = FXCollections.observableList(new ArrayList<>());
    //    private final List<String> xTickLabels = new ArrayList<>();
//    private final List<String> yTickLabels = new ArrayList<>();
    private final GridPane legendsContainer = new GridPane();
    private final Pane plotRegion = new Pane();
    //    AppDataComparator appDataComparator = new AppDataComparator();
    private final Pane xAxisRegion = new Pane();
    private final Pane titleContainer = new GridPane();
    private final Pane yAxisRegion = new Pane();
    private final Pane gridLinesPane = new Pane();
    //    double plotWidth, plotHeight;
//    double minXData = Double.POSITIVE_INFINITY, maxXData = Double.NEGATIVE_INFINITY,
//            minYData = Double.POSITIVE_INFINITY, maxYData = Double.NEGATIVE_INFINITY;
//    AppDataSortingPolicy appDataSortingPolicy= AppDataSortingPolicy.NoSort;
    //    Rectangle emptySpace=new Rectangle();
    //    boolean isAutoSetXTicks = true, isAutoSetYTicks = true;
    private final Group xGridLines = new Group();
    private final Group yGridLines = new Group();
    private final SimpleBooleanProperty xGridLinesVisible = new SimpleBooleanProperty(true);
    private final SimpleBooleanProperty yGridLinesVisible = new SimpleBooleanProperty(true);

//    public void setLegendsPaneAnchor(LegendAnchor legendsPaneAnchor) {
//        this.legendsPaneAnchor .set(legendsPaneAnchor);
//    }
    //    SimpleIntegerProperty numberOfUnNumerableXData = new SimpleIntegerProperty(0);
//    SimpleIntegerProperty numberOfUnNumerableYData = new SimpleIntegerProperty(0);
    //    SimpleIntegerProperty numberOfXTicks = new SimpleIntegerProperty(10);
//    SimpleIntegerProperty numberOfYTicks = new SimpleIntegerProperty(10);
    private final ObservableList<String> customXTicks = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<String> customYTicks = FXCollections.observableList(new ArrayList<>());

    private final ObservableList<Double> xGridLinesStrokeDashArray = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<Double> yGridLinesStrokeDashArray = FXCollections.observableList(new ArrayList<>());


    //    private final SimpleObjectProperty<LegendAnchor> legendsPaneAnchor=new SimpleObjectProperty<>(LegendAnchor.NORTHEAST);
    //    public void setAnchorX(int anchorX) {
//        this.anchorX.set(anchorX);
//    }
//
//    public void setAnchorY(int anchorY) {
//        this.anchorY.set(anchorY);
//    }
    //    double minX = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[0]).min().orElse(Double.POSITIVE_INFINITY);
//    double maxX = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[0]).max().orElse(Double.NEGATIVE_INFINITY);
//    double minY = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[1]).min().orElse(Double.POSITIVE_INFINITY);
//    double maxY = previewCoordinatesList.stream().mapToDouble(doubles -> doubles[1]).max().orElse(Double.NEGATIVE_INFINITY);
    double xTicksWidth = 30;
    //    double xTicksHeight = 20;
//    double yTicksWidth = 20;
    double yTicksHeight = 20;

    public AppAxisChart(double width, double height) {

        plotRegionWidth.set(width);
        plotRegionHeight.set(height);

        initializeChartPreview();

        legendsContainer.styleProperty().bindBidirectional(legendsContainerCSS);

//        VBox vBox;
//        vBox.ge


//        addRow(0, new HBox(), new HBox());

//        setCenter(plotRegion);
//        setLeft(yAxisRegion);
//        setBottom(new HBox(emptySpace,xAxisRegion));


        bindPlotRegionSize();

        bindLegendsRegionSize();

        bindAxesRegionsSize();

        bindYTicksVisuals();

        bindXTicksVisuals();

        bindGridLinesPaneSize();


        bindLegendsContainerLocation();

        bindGridLinesVisibilities();

//        Line line=new Line();

//        line.getSt
//        legendsPaneAnchor.addListener((a,b,c)->updateLegendsPositionsBindings());

//        setAnchorX(1);
//        setAnchorY(1);

//        anchorX.set(1);
//        anchorY.set(1);
//
//
//        legendsContainerRelativeX.set(1);
//        legendsContainerRelativeY.set(1);

        setCustomSize(legendsContainer, 150, 150);

//        legendsContainer.setBorder(Border.stroke(Color.BLACK));

        xAxisRegion.setBackground(Background.fill(new Color(1, 0, 1, 0.2)));
        yAxisRegion.setBackground(Background.fill(new Color(0, 1, 1, 0.2)));

//        legendsContainer.setBackground(Background.fill(Color.valueOf("ffffffff")));

        legendsContainerCSS.set("-fx-background-color: white;-fx-border-color: black");


        setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-border-width: 0px");

        //todo note to assign backgroundStyle to plotRegion in a Wrapper class of this object...
        plotRegion.setStyle("-fx-border-width: 1;-fx-border-color: black");

//        gridLinesPane.setBackground(Background.fill(new Color(1, 1, 0, 0.2)));

//        xAxisRegionHeight.set(200);


        ___TEMP___();

//        xGridLinesStrokeDashArray.setAll(10.,20.,20.,20.);


//        xGridLinesVisible.set(true);

//        setCustomSize(gridLinesPane,50,50);

//        legendsContainer.setTranslateX(450);

//        legendsContainer.translateXProperty().bind();

//        plotRegion.getChildren().


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
        Rectangle newEmptySpace=new Rectangle();
        newEmptySpace.widthProperty().bindBidirectional(legendTitleVisualMargin);
        legendsContainer.addRow(seriesIndex, appSeries.getVisualLegend(),newEmptySpace,appSeries.title);
    }


    public final SimpleDoubleProperty legendTitleVisualMargin=new SimpleDoubleProperty(0);


    public void removeAllSeries() {
        while (seriesList.size() != 0) {
            removeSeries(0);
        }
    }

    public void removeSeries(int seriesIndex) {
        seriesList.remove(seriesIndex);
        plotRegion.getChildren().remove(3 * seriesIndex + 2);
        plotRegion.getChildren().remove(3 * seriesIndex + 1);
        plotRegion.getChildren().remove(3 * seriesIndex);
        legendsContainer.getChildren().remove(3*seriesIndex+2);
        legendsContainer.getChildren().remove(3*seriesIndex+1);
        legendsContainer.getChildren().remove(3*seriesIndex);
    }

    public void setXGridLinesVisible(boolean xGridLinesVisible) {
        this.xGridLinesVisible.set(xGridLinesVisible);
    }

    public void setYGridLinesVisible(boolean yGridLinesVisible) {
        this.yGridLinesVisible.set(yGridLinesVisible);
    }

    void bindLegendsContainerLocation() {
        legendsContainer.translateXProperty().bind(legendsContainerRelativeX.multiply(plotRegionWidth).subtract(legendsContainerWidth.multiply(anchorX)));
        legendsContainer.translateYProperty().bind(legendsContainerRelativeY.multiply(-1).add(1).multiply(plotRegionWidth).subtract(legendsContainerHeight.multiply(anchorY.multiply(-1).add(1))));
    }

    void parseXOnScreen() {

    }

    private void ___TEMP___() {

//        plotRegionHeight.addListener((a,b,c)->print("Height prop = "+plotRegion.heightProperty().get()));

        gridLinesPane.getChildren().addAll(xGridLines, yGridLines);
//        xGridLines.setVisible(false);
        xTickPositions.addListener((ListChangeListener<Double>) change -> xGridLines.getChildren().setAll(xTickPositions.stream().map(d -> new Line(d, 0, d, plotRegionHeight.get())).toList()));
        yTickPositions.addListener((ListChangeListener<Double>) change -> yGridLines.getChildren().setAll(yTickPositions.stream().map(d -> new Line(0, d, plotRegionWidth.get(), d)).toList()));

        plotRegionWidth.addListener((a,b,c)-> yGridLines.getChildren().setAll(yTickPositions.stream().map(d -> new Line(0, d, plotRegionWidth.get(), d)).toList()));
        plotRegionHeight.addListener((a,b,c)->xGridLines.getChildren().setAll(xTickPositions.stream().map(d -> new Line(d, 0, d, plotRegionHeight.get())).toList()));

//        xGridLinesStrokeDashArray.addListener((ListChangeListener<Double>)change->{
//            xGridLines.getChildren().forEach(line -> ((Line)line).getStrokeDashArray().setAll(xGridLinesStrokeDashArray));
//        });
    }

    private void bindAxesRegionsSize() {
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

    private void bindGridLinesPaneSize() {
        gridLinesPane.minWidthProperty().bindBidirectional(plotRegionWidth);
        gridLinesPane.maxWidthProperty().bindBidirectional(plotRegionWidth);
        gridLinesPane.prefWidthProperty().bindBidirectional(plotRegionWidth);

        gridLinesPane.minHeightProperty().bindBidirectional(plotRegionHeight);
        gridLinesPane.maxHeightProperty().bindBidirectional(plotRegionHeight);
        gridLinesPane.prefHeightProperty().bindBidirectional(plotRegionHeight);
    }

    private void bindGridLinesVisibilities() {
        xGridLines.visibleProperty().bindBidirectional(xGridLinesVisible);
        yGridLines.visibleProperty().bindBidirectional(yGridLinesVisible);
    }

    private void bindLegendsRegionSize() {

        legendsContainer.prefWidthProperty().bindBidirectional(legendsContainerWidth);
        legendsContainer.minWidthProperty().bindBidirectional(legendsContainerWidth);
        legendsContainer.maxWidthProperty().bindBidirectional(legendsContainerWidth);

        legendsContainer.prefHeightProperty().bindBidirectional(legendsContainerHeight);
        legendsContainer.minHeightProperty().bindBidirectional(legendsContainerHeight);
        legendsContainer.maxHeightProperty().bindBidirectional(legendsContainerHeight);

    }

    /**
     * Binds plot region width and height to plotRegionWidth and plotRegionHeight. When plot region
     * width and height change, the location of all series data are updated.
     */
    private void bindPlotRegionSize() {

        plotRegion.minHeightProperty().bindBidirectional(plotRegionHeight);
        plotRegion.maxHeightProperty().bindBidirectional(plotRegionHeight);
        plotRegion.prefHeightProperty().bindBidirectional(plotRegionHeight);

        plotRegion.prefWidthProperty().bindBidirectional(plotRegionWidth);
        plotRegion.minWidthProperty().bindBidirectional(plotRegionWidth);
        plotRegion.maxWidthProperty().bindBidirectional(plotRegionWidth);



//        plotRegion.widthProperty().

    }

    private void bindXTicksVisuals() {
        leftPlotMargin.addListener((a, b, c) -> updateXTicks());
        rightPlotMargin.addListener((a, b, c) -> updateXTicks());
        plotRegionWidth.addListener((a, b, c) -> updateXTicks());
        globalMinX.addListener((a, b, c) -> updateXTicks());
        globalMaxX.addListener((a, b, c) -> updateXTicks());
    }

    private void bindYTicksVisuals() {
        topPlotMargin.addListener((a, b, c) -> updateYTicks());
        bottomPlotMargin.addListener((a, b, c) -> updateYTicks());
        plotRegionHeight.addListener((a, b, c) -> updateYTicks());
        globalMinY.addListener((a, b, c) -> updateYTicks());
        globalMaxY.addListener((a, b, c) -> updateYTicks());
    }

//    private void renderTicks(List<Double> tickPositions,List<String> tickLabels,double minTickLocation, double maxTickLocation, int numberOfTicks) {
//
//        int l1, l2;
//
//        double tickMarginDistance = Double.MAX_VALUE;
//        int bestK = Integer.MAX_VALUE;
//        int bestP = Integer.MAX_VALUE;
//        int bestL1 = Integer.MAX_VALUE;
//        int bestL2 = Integer.MAX_VALUE;
//        int bestNumberOfTicks = -1;
//
//        for (int k : new int[]{1, 2, 4, 5}) {
//            int pNess = (int) Math.ceil(Math.log10(1.0 * (numberOfTicks - 1) / k / (maxTickLocation - minTickLocation)));
//            for (int p = pNess; ; p--) {
//                l1 = (int) Math.floor(minTickLocation * k * Math.pow(10, p));
//                l2 = (int) Math.ceil(maxTickLocation * k * Math.pow(10, p));
//                if (l2 - l1 == 1) {
//                    break;
//                }
//                if (l2 - l1 + 1 <= numberOfTicks && l2 - l1 + 1 > bestNumberOfTicks) {
//                    if (minTickLocation - maxTickLocation + 1.0 * (l2 - l1) / k / Math.pow(10, p) <= tickMarginDistance) {
//                        tickMarginDistance = minTickLocation - maxTickLocation + 1.0 * (l2 - l1) / k / Math.pow(10, p);
//                        bestK = k;
//                        bestP = p;
//                        bestL1 = l1;
//                        bestL2 = l2;
//                        bestNumberOfTicks = l2 - l1 + 1;
//                    }
//                    break;
//                }
//            }
//        }
//
//        for (int j = bestL1; j <= bestL2; j++) {
//            double temp = 1.0 * j / bestK / Math.pow(10, bestP);
//            double temp1 = (temp - minTickLocation) / (maxTickLocation-minTickLocation) * (plotRegionWidth.multiply(rightPlotMargin.multiply(-1).add(1)).get() - plotRegionWidth.multiply(leftPlotMargin).get()) + plotRegionWidth.multiply(leftPlotMargin).get();
//            tickPositions.add(temp1);
////            xAxisRegion.getChildren().add(new Line(temp1, 0, temp1, 40));
//            tickLabels.add( getScientificRepresentation(temp, 3));
//
//        }
//    }

    /**
     * The following actions are executed during chart preview initialization:
     * 1-titleRegion, axes regions and plotRegion are added to chart preview.
     * 2- A new layer of grid lines region is added to the children of plotRegion.
     * 3- legendsContainer is added to the children of plotRegion (up to this step, legendsContainer always renders on top of all other plotRegion components).
     */
    private void initializeChartPreview() {

//        setAlignment(Pos.CENTER);

        titleContainer.getChildren().add(title);

//        title.setTex
//        title.setTextAlignment(TextAlignment.CENTER);
//        titleContainer.

//        titleContainer.setCenterShape(true);
//        titleContainer.set
//        titleContainer.setA

        addRow(0, new HBox(), titleContainer);
        addRow(1, yAxisRegion, plotRegion);
        addRow(2, new HBox(), xAxisRegion);

        plotRegion.getChildren().add(gridLinesPane);

        plotRegion.getChildren().add(legendsContainer);


    }

    private void updateXTicks() {

        xTickPositions.clear();
        xAxisRegion.getChildren().clear();

        if (seriesList.size() == 0) {
//            print("globalMinX = "+globalMinX.get());
//            print("globalMaxX = "+globalMaxX.get());
            return;
        }


        if (globalMinX.get() == globalMaxX.get()) {
//            print(uuid(400));
            double temp = globalMaxX.get();
            //todo change this
            double temp1 = temp;
//            double temp1= (plotRegionWidth.multiply(topPlotMargin).get() - plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get()) + plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get();
            xTickPositions.add(temp);
            xAxisRegion.getChildren().add(new Line(0, temp1, 40, temp1));
            xAxisRegion.getChildren().add(new Text(-40, temp1, temp + ""));
        } else {

            int numberOfTicks = (int) Math.floor((1 - leftPlotMargin.get() - rightPlotMargin.get()) * plotRegionWidth.get() / xTicksWidth);

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
                xTickPositions.add(temp1);
                xAxisRegion.getChildren().add(new Line(temp1, 0, temp1, 40));
                xAxisRegion.getChildren().add(new Text(temp1, 40, getScientificRepresentation(temp, 3)));

            }
        }


//        print(emptySpace.getWidth());
//        print(emptySpace.getHeight());

    }

    private void updateYTicks() {

        yTickPositions.clear();
        yAxisRegion.getChildren().clear();

        if (seriesList.size() == 0) {
//            print("globalMinY = "+globalMinY.get());
//            print("globalMaxY = "+globalMaxY.get());
            return;
        }


        if (globalMinY.get() == globalMaxY.get()) {
//            print(uuid(400));
            double temp = globalMaxY.get();
            double temp1 = (plotRegionHeight.multiply(topPlotMargin).get() - plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get()) + plotRegionHeight.multiply(bottomPlotMargin.multiply(-1).add(1)).get();
            yTickPositions.add(temp);
            yAxisRegion.getChildren().add(new Line(0, temp1, 40, temp1));
            yAxisRegion.getChildren().add(new Text(-40, temp1, temp + ""));
        } else {

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
                double temp1 = (temp - globalMinY.get()) / (globalMaxY.get() - globalMinY.get()) * plotRegionHeight.get() * (topPlotMargin.get() + bottomPlotMargin.get() - 1) + plotRegionHeight.get() * (1 - bottomPlotMargin.get());
                yTickPositions.add(temp1);
                yAxisRegion.getChildren().add(new Line(0, temp1, 40, temp1));
                yAxisRegion.getChildren().add(new Text(-40, temp1, getScientificRepresentation(temp, 3)));
            }
        }


    }


}