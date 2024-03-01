package com.boom.panels.chart;

import com.boom.appcharts.AppAxisChartWrapper;
import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.icons.MinusSignIcon;
import com.boom.icons.PlusSignIcon;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public  class ChartManagementPanel extends VBox {

    TabPane seriesList = new TabPane();
    Button addSeriesButton = new Button();
    Button removeSeriesButton = new Button();

    ColorPicker chartFillColor = new ColorPicker();

    ColorPicker chartStrokeColor = new ColorPicker();

    GridPane generalChartPropertiesPane = new GridPane();

    public ChartManagementPanel() {

        super();

        generalChartPropertiesPane.addRow(0, new Label("Chart fill color : "), chartFillColor);
        generalChartPropertiesPane.addRow(1, new Label("Chart stroke color : "), chartStrokeColor);
        generalChartPropertiesPane.addRow(2, new Label("Add or remove series : "), new HBox(addSeriesButton, removeSeriesButton));

        getChildren().addAll(generalChartPropertiesPane, seriesList);

        addSeriesButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
        removeSeriesButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));


    }

    public void registerChart(AppAxisChartWrapper appAxisChartWrapper) {

        seriesList.getTabs().clear();
        for (int i = 0; i < appAxisChartWrapper.getNumberOfSeries(); i++) {
            AppSeries appSeries = appAxisChartWrapper.getSeries(i);
            seriesList.getTabs().add(getDecoratedTabForAppSeries(appSeries));
        }

        renameTabs();

        addSeriesButton.setOnAction(event -> {
            AppSeries newAppSeries = new AppSeries();
            appAxisChartWrapper.addSeries(newAppSeries);
            seriesList.getTabs().add(getDecoratedTabForAppSeries(newAppSeries));
            renameTabs();
        });

        removeSeriesButton.setOnAction(event -> {
            Tab tab = seriesList.getSelectionModel().getSelectedItem();
            if (tab != null) {
                appAxisChartWrapper.removeSeries(seriesList.getTabs().indexOf(tab));
                seriesList.getTabs().remove(tab);
                renameTabs();
            }
        });


    }

    private void renameTabs() {
        seriesList.getTabs().forEach(tab -> tab.setText("Series#" + (seriesList.getTabs().indexOf(tab) + 1)));
    }

    private Tab getDecoratedTabForAppSeries(AppSeries appSeries) {
        Tab tab = new Tab();
        SeriesManagementPanel seriesManagementPanel = new SeriesManagementPanel();
        seriesManagementPanel.registerSeries(appSeries);
        ScrollPane container = new ScrollPane();
        container.setContent(seriesManagementPanel);
        tab.setContent(container);
        tab.setClosable(false);
        return tab;
    }


}