package com.example.panels.chart.number_string;

import com.example.exceptions.AppException;
import com.example.icons.MinusSignIcon;
import com.example.icons.PlusSignIcon;
import com.example.structures.enums.AppExceptionEnum;
import com.example.structures.abstracts.AppXYChart;
import com.example.structures.enums.NodeTypeEnum;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChartManagementPane_NumberString extends VBox {

    TabPane seriesList = new TabPane();
    AppXYChart<Number, String> appXYChart;
    Button addSeriesButton = new Button();
    Button removeSeriesButton = new Button();

    ColorPicker chartFillColor = new ColorPicker();

    ColorPicker chartStrokeColor = new ColorPicker();

    public ChartManagementPane_NumberString() {

        super();

        getChildren().addAll(
                new HBox(new Label("Chart fill color : "), chartFillColor),
                new HBox(new Label("Chart stroke color : "), chartStrokeColor),
                new HBox(new Label("Add or remove series : "), addSeriesButton, removeSeriesButton),
                seriesList
        );

        addSeriesButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeSeriesButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));


//        getChildren.a(1,seriesList);

//        getChildren().addAll(addSeries,removeSeries,seriesList);

        addSeriesButton.setOnAction(event -> {
            XYChart.Series<Number, String> newSeries = appXYChart.addSeries();
            int seriesIndex = ((XYChart) appXYChart.getRegion()).getData().indexOf(newSeries);
            Tab newTab = new Tab();
            SeriesManagementPane_NumberString newDataPane;
            if (appXYChart.getType().equals(NodeTypeEnum.AreaChart_NS.getNodeType())) {
                newDataPane = new SeriesManagementPane_NumberString(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), appXYChart.getSeriesAreaStyles().get(seriesIndex));
            } else if (appXYChart.getType().equals(NodeTypeEnum.LineChart_NS.getNodeType())) {
                newDataPane = new SeriesManagementPane_NumberString(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), null);
            } else if (appXYChart.getType().equals(NodeTypeEnum.ScatterChart_NS.getNodeType())) {
                newDataPane = new SeriesManagementPane_NumberString(appXYChart, newSeries, null, null);
            } else {
                throw new AppException(AppExceptionEnum.ChartTypeNotRegistered);
            }
            ScrollPane newContainer = new ScrollPane();
            newContainer.setContent(newDataPane);
            newTab.setContent(newContainer);
            newTab.setClosable(false);
            seriesList.getTabs().add(newTab);
            renameTabs();
        });


        removeSeriesButton.setOnAction(event -> {
            Tab tab = seriesList.getSelectionModel().getSelectedItem();
            if (tab != null) {
                appXYChart.removeSeries(((XYChart) appXYChart.getRegion()).getData().indexOf(((SeriesManagementPane_NumberString) ((ScrollPane) tab.getContent()).getContent()).series));
                seriesList.getTabs().remove(tab);
                renameTabs();
            }
        });

    }

    public void registerChart(AppXYChart<Number, String> appXYChart) {
        this.appXYChart = appXYChart;
        seriesList.getTabs().clear();
        for (int i = 0; i < ((XYChart) appXYChart.getRegion()).getData().size(); i++) {
            XYChart.Series<Number, String> series = ((XYChart<Number, String>) appXYChart.getRegion()).getData().get(i);
            Tab tab = new Tab();
            SeriesManagementPane_NumberString dataPane;
            if (appXYChart.getRegion().getClass().getName().equals(AreaChart.class.getName())) {
                dataPane = new SeriesManagementPane_NumberString(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), appXYChart.getSeriesAreaStyles().get(i));
            } else if (appXYChart.getRegion().getClass().getName().equals(LineChart.class.getName())) {
                dataPane = new SeriesManagementPane_NumberString(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), null);
            } else if (appXYChart.getRegion().getClass().getName().equals(ScatterChart.class.getName())) {
                dataPane = new SeriesManagementPane_NumberString(appXYChart, series, null, null);
            } else {
                throw new AppException(AppExceptionEnum.ChartTypeNotRegistered);
            }
            ScrollPane container = new ScrollPane();
            container.setContent(dataPane);
            tab.setContent(container);
            tab.setClosable(false);
            seriesList.getTabs().add(tab);
        }
        renameTabs();

//        chartFillColor.setValue(appXYChart.getBackgroundStyle().fill.get());
//        chartFillColor.setOnAction(event -> appXYChart.getBackgroundStyle().fill.set(chartFillColor.getValue()));
//        chartStrokeColor.setValue(appXYChart.getBackgroundStyle().stroke.get());
//        chartStrokeColor.setOnAction(event -> appXYChart.getBackgroundStyle().stroke.set(chartStrokeColor.getValue()));


    }

    void renameTabs() {
        seriesList.getTabs().forEach(tab -> tab.setText("Series#" + (seriesList.getTabs().indexOf(tab) + 1)));
    }


}
