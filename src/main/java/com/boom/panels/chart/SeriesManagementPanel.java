package com.boom.panels.chart;

import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.icons.PlusSignIcon;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.boom.tools.Tools.setCustomSize;

public  class SeriesManagementPanel extends VBox {

    private AppSeries appSeries;

    private final GridPane seriesProperties = new GridPane();
    private final TextArea seriesNameTextArea = new TextArea();


    private final VBox dataSetPane = new VBox();
    private final ObservableList<Node> dataSetPaneChildren = dataSetPane.getChildren();

    private final ColorPicker areaStrokeColor = new ColorPicker();
    private final ColorPicker areaFillColor = new ColorPicker();
    private final ColorPicker lineColor = new ColorPicker();
    private final TextField lineWidth = new TextField();
    private final Button loadDataFromFileButton = new Button("Load data from file");
    private final Button primaryAddButton = new Button();
    private final Label primaryEmptySpace = new Label("Add new data");

//    AppAxisChartWrapper appAxisChartWrapper;

    private void setGraphics() {
        primaryAddButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

    private void setLayout() {
        getChildren().addAll(seriesProperties, dataSetPane);
        seriesProperties.addRow(0, loadDataFromFileButton);
        seriesProperties.addRow(1, new Label("Legend : "), seriesNameTextArea);
    }

    private void setSizes(boolean isLineNull, boolean isAreaNull) {
        setCustomSize(primaryAddButton, 40, 40);
        setCustomSize(primaryEmptySpace, 200, 40);
        setCustomSize(loadDataFromFileButton, 100, 40);
        setCustomSize(seriesNameTextArea, 100, 40);
        if (!isLineNull) {
            setCustomSize(lineColor, 100, 40);
        }
        if (!isAreaNull) {
            setCustomSize(areaFillColor, 100, 40);
            setCustomSize(areaStrokeColor, 100, 40);
        }
    }


    public SeriesManagementPanel() {

        super();

        setLayout();

        setGraphics();

        setSizes(true, true);

//        setSizes(lineStyleProperty==null,areaStyleProperty==null);
//
//
//        if (lineStyleProperty != null) {
//            seriesProperties.addRow(2, new Label("Line color  : "), lineColor);
//            seriesProperties.addRow(3, new Label("Line width  : "), lineWidth);
//            lineColor.setValue(lineStyleProperty.color.get());
//            lineColor.setOnAction(event -> lineStyleProperty.color.set(lineColor.getValue()));
//            lineWidth.setText(String.valueOf(lineStyleProperty.width.get()));
//            lineWidth.focusedProperty().addListener((a, b, c) -> {
//                if (!c) {
//                    lineWidth.setText(String.valueOf(lineStyleProperty.width.get()));
//                }
//            });
//            lineWidth.setOnKeyTyped(keyEvent -> {
//                try {
//                    lineStyleProperty.width.set(Double.parseDouble(lineWidth.getText()));
//                } catch (NumberFormatException e) {
//                    print(e);
//                }
//            });
//        }
//        if (areaStyleProperty != null) {
//            seriesProperties.addRow(4, new Label("Area fill color    : "), areaFillColor);
//            seriesProperties.addRow(5, new Label("Area stroke color  : "), areaStrokeColor);
////            areaFillColor.setValue(areaStyleProperty.fill.get());
////            areaFillColor.setOnAction(event -> areaStyleProperty.fill.set(areaFillColor.getValue()));
////            areaStrokeColor.setValue(areaStyleProperty.stroke.get());
////            areaStrokeColor.setOnAction(event -> areaStyleProperty.stroke.set(areaStrokeColor.getValue()));
//        }


        dataSetPaneChildren.addListener((ListChangeListener<Node>) change -> autosize());


//        super(lineStyleProperty, areaStyleProperty);

//        this.appSeries = appSeries;


        setPrimaryAddButton();

//        registerSeries();

        setLoadDataFromFile();

    }


    private void setLoadDataFromFile() {
        loadDataFromFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv"));
            String filePath = fileChooser.showOpenDialog(new ContextMenu()).getAbsolutePath();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                appSeries.removeAllData();
                bufferedReader.lines().forEach(data -> {
                    try {
                        appSeries.addData(Double.parseDouble(data.split(",")[0]), Double.parseDouble(data.split(",")[1]));
                    } catch (Exception ignored) {

                    }
                });
            } catch (FileNotFoundException ignored) {
            }
        });
    }


    public void registerSeries(AppSeries appSeries) {
        this.appSeries = appSeries;
        seriesNameTextArea.setText(appSeries.title.getText());
        seriesNameTextArea.setOnKeyTyped(keyEvent -> appSeries.title.setText(seriesNameTextArea.getText()));
        dataSetPaneChildren.setAll(new HBox(primaryEmptySpace, primaryAddButton));
        for (int i = 0; i < appSeries.getNumberOfData(); i++) {
            dataSetPaneChildren.add(new DataField(dataSetPaneChildren, appSeries, i));
        }
    }


    protected void setPrimaryAddButton() {
        dataSetPaneChildren.add(new HBox(primaryEmptySpace, primaryAddButton));
        primaryEmptySpace.setVisible(false);
        primaryAddButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddButton.setOnAction(event -> {
            appSeries.addData(0,
                    dataSetPaneChildren.size() == 1 ? 0 : appSeries.getData(0)[0],
                    dataSetPaneChildren.size() == 1 ? 0 : appSeries.getData(0)[1]
            );
            dataSetPaneChildren.add(1, new DataField(dataSetPaneChildren, appSeries, 0));
        });
    }

}
