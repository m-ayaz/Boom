package com.example.panels.chart.number_number;

import com.example.structures.AppXYChart;
import com.example.structures.SeriesManagementPaneBase;
import com.example.styles.CSSProperty;
import com.example.styles.SeriesLineStyleProperty;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SeriesManagementPane_NumberNumber extends SeriesManagementPaneBase {

    XYChart.Series<Number, Number> series;

    AppXYChart<Number, Number> appXYChart;


    public SeriesManagementPane_NumberNumber(AppXYChart<Number, Number> appXYChart, XYChart.Series<Number, Number> series, SeriesLineStyleProperty lineStyleProperty, CSSProperty areaStyleProperty) {

        super(lineStyleProperty, areaStyleProperty);

        this.series = series;

        this.appXYChart = appXYChart;

        seriesName.setText(series.getName());

        seriesName.setOnKeyTyped(keyEvent -> series.setName(seriesName.getText()));

        setPrimaryAddButton();

        registerSeries();

        setLoadDataFromFile();

    }


    @Override
    public void setLoadDataFromFile() {
        loadDataFromFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv"));
            String filePath = fileChooser.showOpenDialog(new ContextMenu()).getAbsolutePath();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                series.getData().clear();
                bufferedReader.lines().forEach(data -> {
                    try {
                        series.getData().add(new XYChart.Data<>(
                                Double.valueOf(data.split(",")[0]),
                                Double.valueOf(data.split(",")[1])));
                    } catch (Exception ignored) {

                    }
                });
            } catch (FileNotFoundException ignored) {
            }
        });
    }

    @Override
    public void registerSeries() {
        for (int i = 0; i < series.getData().size(); i++) {
            DataField_NumberNumber dataField = new DataField_NumberNumber(dataSetPaneChildren, appXYChart, series, series.getData().get(i));
            dataSetPaneChildren.add(dataField);
        }
    }


    @Override
    protected void setPrimaryAddButton() {
        dataSetPaneChildren.add(new HBox(primaryEmptySpace, primaryAddButton));
        primaryEmptySpace.setVisible(false);
        primaryAddButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddButton.setOnAction(event -> {
            XYChart.Data<Number, Number> newData;
            int seriesIndex = ((XYChart) appXYChart.getRegion()).getData().indexOf(series);
            if (dataSetPaneChildren.size() == 1) {
                newData = appXYChart.addData(0, 0, seriesIndex, 0);
            } else {
                newData = appXYChart.addData(series.getData().get(0).getXValue(), series.getData().get(0).getYValue(), seriesIndex, 0);
            }
            DataField_NumberNumber newDataField = new DataField_NumberNumber(dataSetPaneChildren, appXYChart, series, newData);
            dataSetPaneChildren.add(1, newDataField);
        });
    }

}
