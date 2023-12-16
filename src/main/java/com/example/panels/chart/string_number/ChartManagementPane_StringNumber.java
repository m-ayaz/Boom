package com.example.panels.chart.string_number;

import com.example.exceptions.AppException;
import com.example.icons.MinusSignIcon;
import com.example.icons.PlusSignIcon;
import com.example.structures.AppExceptionEnum;
import com.example.structures.AppXYChart;
import com.example.structures.NodeTypeEnum;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChartManagementPane_StringNumber extends VBox {

    TabPane seriesList = new TabPane();
    AppXYChart<String, Number> appXYChart;
    Button addSeriesButton = new Button();
    Button removeSeriesButton = new Button();

    ColorPicker chartFillColor = new ColorPicker();

    ColorPicker chartStrokeColor = new ColorPicker();

    public ChartManagementPane_StringNumber() {

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
            XYChart.Series<String, Number> newSeries = appXYChart.addSeries();
            int seriesIndex = ((XYChart) appXYChart.getNode()).getData().indexOf(newSeries);
            Tab newTab = new Tab();
            SeriesManagementPane_StringNumber newDataPane;
            if (appXYChart.getType().equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
                newDataPane = new SeriesManagementPane_StringNumber(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), appXYChart.getSeriesAreaStyles().get(seriesIndex));
            } else if (appXYChart.getType().equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
                newDataPane = new SeriesManagementPane_StringNumber(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), null);
            } else if (appXYChart.getType().equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
                newDataPane = new SeriesManagementPane_StringNumber(appXYChart, newSeries, null, null);
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
                appXYChart.removeSeries(((XYChart) appXYChart.getNode()).getData().indexOf(((SeriesManagementPane_StringNumber) ((ScrollPane) tab.getContent()).getContent()).series));
                seriesList.getTabs().remove(tab);
                renameTabs();
            }
        });

    }

    public void registerChart(AppXYChart<String, Number> appXYChart) {
        this.appXYChart = appXYChart;
        seriesList.getTabs().clear();
        for (int i = 0; i < ((XYChart) appXYChart.getNode()).getData().size(); i++) {
            XYChart.Series<String, Number> series = ((XYChart<String, Number>) appXYChart.getNode()).getData().get(i);
            Tab tab = new Tab();
            SeriesManagementPane_StringNumber dataPane;
            if (appXYChart.getNode().getClass().getName().equals(AreaChart.class.getName())) {
                dataPane = new SeriesManagementPane_StringNumber(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), appXYChart.getSeriesAreaStyles().get(i));
            } else if (appXYChart.getNode().getClass().getName().equals(LineChart.class.getName())) {
                dataPane = new SeriesManagementPane_StringNumber(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), null);
            } else if (appXYChart.getNode().getClass().getName().equals(ScatterChart.class.getName())) {
                dataPane = new SeriesManagementPane_StringNumber(appXYChart, series, null, null);
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
