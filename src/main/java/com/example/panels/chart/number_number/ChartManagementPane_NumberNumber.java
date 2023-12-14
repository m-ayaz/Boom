package com.example.panels.chart.number_number;

import com.example.icons.MinusSignIcon;
import com.example.icons.PlusSignIcon;
import com.example.structures.AppXYChart;
import com.example.structures.AppExceptionEnum;
import com.example.structures.NodeTypeEnum;
import com.example.exceptions.AppException;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.tools.Tools.print;

public class ChartManagementPane_NumberNumber extends VBox {

    TabPane seriesList = new TabPane();
    AppXYChart<Number, Number> appXYChart;
    Button addSeriesButton = new Button();
    Button removeSeriesButton = new Button();

    ColorPicker chartFillColor = new ColorPicker();

    ColorPicker chartStrokeColor = new ColorPicker();

    GridPane generalChartPropertiesPane=new GridPane();

    public ChartManagementPane_NumberNumber() {

        super();

        generalChartPropertiesPane.addRow(0,new Label("Chart fill color : "), chartFillColor);
        generalChartPropertiesPane.addRow(1,new Label("Chart stroke color : "), chartStrokeColor);
        generalChartPropertiesPane.addRow(2,new Label("Add or remove series : "), new HBox(addSeriesButton, removeSeriesButton));

        getChildren().addAll(generalChartPropertiesPane, seriesList);

        addSeriesButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeSeriesButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));

//        getChildren.a(1,seriesList);

//        getChildren().addAll(addSeries,removeSeries,seriesList);

        addSeriesButton.setOnAction(event -> {
            XYChart.Series<Number, Number> newSeries = appXYChart.addSeries();
            int seriesIndex = ((XYChart) appXYChart.node).getData().indexOf(newSeries);
            Tab newTab = new Tab();
            SeriesManagementPane_NumberNumber newDataPane;
            if (appXYChart.type.equals(NodeTypeEnum.AreaChart_NN.getNodeType())) {
                newDataPane = new SeriesManagementPane_NumberNumber(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), appXYChart.getSeriesAreaStyles().get(seriesIndex));
            } else if (appXYChart.type.equals(NodeTypeEnum.LineChart_NN.getNodeType())) {
                newDataPane = new SeriesManagementPane_NumberNumber(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), null);
            } else if (appXYChart.type.equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
                newDataPane = new SeriesManagementPane_NumberNumber(appXYChart, newSeries, null, null);
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
                appXYChart.removeSeries(((XYChart) appXYChart.node).getData().indexOf(((SeriesManagementPane_NumberNumber) ((ScrollPane) tab.getContent()).getContent()).series));
                seriesList.getTabs().remove(tab);
                renameTabs();
            }
        });

    }

    public void registerChart(AppXYChart<Number, Number> appXYChart) {
        this.appXYChart = appXYChart;
        seriesList.getTabs().clear();
        for (int i = 0; i < ((XYChart) appXYChart.node).getData().size(); i++) {
            XYChart.Series<Number, Number> series = ((XYChart<Number, Number>) appXYChart.node).getData().get(i);
            Tab tab = new Tab();
            SeriesManagementPane_NumberNumber dataPane;
            if (appXYChart.node.getClass().getName().equals(AreaChart.class.getName())) {
                dataPane = new SeriesManagementPane_NumberNumber(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), appXYChart.getSeriesAreaStyles().get(i));
            } else if (appXYChart.node.getClass().getName().equals(LineChart.class.getName())) {
                dataPane = new SeriesManagementPane_NumberNumber(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), null);
            } else if (appXYChart.node.getClass().getName().equals(ScatterChart.class.getName())) {
                dataPane = new SeriesManagementPane_NumberNumber(appXYChart, series, null, null);
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