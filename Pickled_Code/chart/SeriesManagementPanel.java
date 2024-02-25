package com.boom.panels.chart;

import com.boom.appcharts.AppAxisChartWrapper;
//import com.boom.structures.abstracts.AppXYChart;
import com.boom.structures.abstracts.SeriesManagementPaneBase;
import com.boom.styles.CSSProperty;
import com.boom.styles.SeriesLineStyleProperty;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.boom.tools.Tools.print;

@SuppressWarnings("unchecked")
public  class SeriesManagementPanel extends SeriesManagementPaneBase {

    XYChart.Series<Number, Number> series;

    AppAxisChartWrapper appAxisChartWrapper;


    public SeriesManagementPanel(AppAxisChartWrapper appAxisChartWrapper, XYChart.Series<Number, Number> series, SeriesLineStyleProperty lineStyleProperty, CSSProperty areaStyleProperty) {



        super(lineStyleProperty, areaStyleProperty);

        print("bbbbbbbbbbbbbbbbbbb");

        this.series = series;

        this.appAxisChartWrapper = appAxisChartWrapper;

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
            DataField dataField = new DataField(dataSetPaneChildren, appAxisChartWrapper, series, series.getData().get(i));
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
            int seriesIndex = ((XYChart<Number,Number>) appXYChart.styleableNode).getData().indexOf(series);
            if (dataSetPaneChildren.size() == 1) {
                newData = appXYChart.addData(0, 0, seriesIndex, 0);
            } else {
                newData = appXYChart.addData(series.getData().get(0).getXValue(), series.getData().get(0).getYValue(), seriesIndex, 0);
            }
            DataField newDataField = new DataField(dataSetPaneChildren, appAxisChartWrapper, series, newData);
            dataSetPaneChildren.add(1, newDataField);
        });
    }

}
