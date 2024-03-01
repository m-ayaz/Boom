package com.boom.structures.abstracts;


import com.boom.appcharts.baseclasses.AppLegendRegion;
import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.appcharts.baseclasses.AppTitleRegion;
import com.boom.test.AppDataComparator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

// todo: disallow addition of duplicate series or data

/**
 * All data across all series are of double types.
 * <p>
 * (anchorX,anchorY)={
 * (0,0) if SOUTHWEST,
 * (0,1) if NORTHWEST,
 * (1,0) if SOUTHEAST,
 * (1,1) if NORTHEAST.
 * }
 *
 * Common properties among 2D charts are:
 * 1- legend (anchor and relative position)
 * 2- width and height
 */

public abstract class App2DChart extends Pane {

    public final SimpleDoubleProperty width = new SimpleDoubleProperty();
    public final SimpleDoubleProperty height = new SimpleDoubleProperty();
    public final SimpleDoubleProperty appLegendRelativeX = new SimpleDoubleProperty(1);
    public final SimpleDoubleProperty appLegendRelativeY = new SimpleDoubleProperty(1);
    public final SimpleIntegerProperty appLegendAnchorX = new SimpleIntegerProperty(0);
    public final SimpleIntegerProperty appLegendAnchorY = new SimpleIntegerProperty(1);
    public final AppLegendRegion legendRegion = new AppLegendRegion();
    protected final List<AppSeries> seriesList = new ArrayList<>();
    public final AppTitleRegion titleRegion = new AppTitleRegion(width);
    protected final AppDataComparator appDataComparator = new AppDataComparator();


    public App2DChart(double width, double height) {

        this.width.set(width);
        this.height.set(height);

        bindPlotRegionSize();

        bindAppLegendLocation();

    }

    public final void addSeries(AppSeries appSeries) {
        addSeries(seriesList.size(), appSeries);
    }

    public abstract void addSeries(int seriesIndex, AppSeries appSeries);

    public abstract void addData(int seriesIndex,int dataIndex,double[] data);

    public void addData(int seriesIndex,double[] data){
        addData(seriesIndex,seriesList.get(seriesIndex).getDataListSize(),data);
    }

    public final StringProperty getAppLegendStyleProperty() {
        return legendRegion.styleProperty();
    }

    public final SimpleDoubleProperty getAppLegendTitleVisualMarginProperty() {
        return legendRegion.titleVisualMargin;
    }

    public final void removeAllSeries() {
        while (seriesList.size() != 0) {
            removeSeries(0);
        }
    }


    public abstract void removeSeries(int seriesIndex);

    public abstract void removeData(int seriesIndex,int dataIndex);

    protected final void updateAllSeriesPreviewsAtChart() {
        seriesList.forEach(this::updateSeriesPreviewAtChart);
    }

    protected abstract void updateSeriesPreviewAtChart(AppSeries appSeries);

    private void bindAppLegendLocation() {
        legendRegion.translateXProperty().bind(appLegendRelativeX.multiply(width).subtract(legendRegion.widthProperty().multiply(appLegendAnchorX)));
        legendRegion.translateYProperty().bind(appLegendRelativeY.multiply(-1).add(1).multiply(width).subtract(legendRegion.heightProperty().multiply(appLegendAnchorY.multiply(-1).add(1))));
    }

    /**
     * Binds plot region width and height to plotRegionWidth and plotRegionHeight. When plot region
     * width and height change, the location of all series data are updated.
     */
    private void bindPlotRegionSize() {
        minHeightProperty().bind(height);
        maxHeightProperty().bind(height);
        prefHeightProperty().bind(height);
        prefWidthProperty().bind(width);
        minWidthProperty().bind(width);
        maxWidthProperty().bind(width);

    }

    /**
     * The following actions are executed during chart preview initialization:
     * 1-titleRegion, axes regions and plotRegion are added to chart preview.
     * 2- A new layer of grid lines region is added to the children of plotRegion.
     * 3- appLegend is added to the children of plotRegion (up to this step, appLegend always renders on top of all other plotRegion components).
     */
    protected abstract void initializeChartPreview();


}