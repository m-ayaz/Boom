package com.boom.panels.chart.number_number;

import com.boom.exceptions.AppException;
import com.boom.icons.MinusSignIcon;
import com.boom.icons.PlusSignIcon;
import com.boom.structures.abstracts.AppXYChart;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.enums.NodeTypeEnum;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.boom.tools.Tools.print;

@SuppressWarnings("unchecked")
public final class ChartManagementPanel_NumberNumber extends VBox {

    TabPane seriesList = new TabPane();
    AppXYChart<Number, Number> appXYChart;
    Button addSeriesButton = new Button();
    Button removeSeriesButton = new Button();

    ColorPicker chartFillColor = new ColorPicker();

    ColorPicker chartStrokeColor = new ColorPicker();

    GridPane generalChartPropertiesPane=new GridPane();

    public ChartManagementPanel_NumberNumber() {

        super();

        generalChartPropertiesPane.addRow(0,new Label("Chart fill color : "), chartFillColor);
        generalChartPropertiesPane.addRow(1,new Label("Chart stroke color : "), chartStrokeColor);
        generalChartPropertiesPane.addRow(2,new Label("Add or remove series : "), new HBox(addSeriesButton, removeSeriesButton));

        getChildren().addAll(generalChartPropertiesPane, seriesList);

        addSeriesButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeSeriesButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));

        addSeriesButton.setOnAction(event -> {
            XYChart.Series<Number, Number> newSeries = appXYChart.addSeries();
            int seriesIndex = ((XYChart<Number,Number>) appXYChart.getStyleableNode()).getData().indexOf(newSeries);
            Tab newTab = new Tab();
            SeriesManagementPanel_NumberNumber newDataPane;
            if (appXYChart.getType().equals(NodeTypeEnum.AreaChart_NN.getNodeType())) {
                newDataPane = new SeriesManagementPanel_NumberNumber(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), appXYChart.getSeriesAreaStyles().get(seriesIndex));
            } else if (appXYChart.getType().equals(NodeTypeEnum.LineChart_NN.getNodeType())) {
                newDataPane = new SeriesManagementPanel_NumberNumber(appXYChart, newSeries, appXYChart.getSeriesLineStyles().get(seriesIndex), null);
            } else if (appXYChart.getType().equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
                newDataPane = new SeriesManagementPanel_NumberNumber(appXYChart, newSeries, null, null);
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
                appXYChart.removeSeries(((XYChart<Number, Number>) appXYChart.getStyleableNode()).getData().indexOf(((SeriesManagementPanel_NumberNumber) ((ScrollPane) tab.getContent()).getContent()).series));
                seriesList.getTabs().remove(tab);
                renameTabs();
            }
        });

    }

    public void registerChart(AppXYChart<Number, Number> appXYChart) {
//        print("_________________________________________");
//        print(appXYChart.getType());
//        print(NodeTypeEnum.LineChart_NN.getNodeType());
//        print(appXYChart.getType().equals(NodeTypeEnum.LineChart_NN.getNodeType()));
        this.appXYChart = appXYChart;
        seriesList.getTabs().clear();
        for (int i = 0; i < ((XYChart<Number, Number>) appXYChart.getStyleableNode()).getData().size(); i++) {
            XYChart.Series<Number, Number> series = ((XYChart<Number, Number>) appXYChart.getStyleableNode()).getData().get(i);
            Tab tab = new Tab();
            SeriesManagementPanel_NumberNumber dataPane;
            if (appXYChart.getType().equals(NodeTypeEnum.AreaChart_NN.getNodeType())||
                    appXYChart.getType().equals(NodeTypeEnum.AreaChart_NS.getNodeType())||
                    appXYChart.getType().equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
                dataPane = new SeriesManagementPanel_NumberNumber(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), appXYChart.getSeriesAreaStyles().get(i));
            } else if (appXYChart.getType().equals(NodeTypeEnum.LineChart_NN.getNodeType())||
                    appXYChart.getType().equals(NodeTypeEnum.LineChart_NS.getNodeType())||
                    appXYChart.getType().equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
                print("ccccccccc");
                dataPane = new SeriesManagementPanel_NumberNumber(appXYChart, series, appXYChart.getSeriesLineStyles().get(i), null);
            } else if (appXYChart.getType().equals(NodeTypeEnum.ScatterChart_NN.getNodeType())||
                    appXYChart.getType().equals(NodeTypeEnum.ScatterChart_NS.getNodeType())||
                    appXYChart.getType().equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
                dataPane = new SeriesManagementPanel_NumberNumber(appXYChart, series, null, null);
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

    }

    void renameTabs() {
        seriesList.getTabs().forEach(tab -> tab.setText("Series#" + (seriesList.getTabs().indexOf(tab) + 1)));
    }


}