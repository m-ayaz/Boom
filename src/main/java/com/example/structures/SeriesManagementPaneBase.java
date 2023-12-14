package com.example.structures;

import com.example.icons.PlusSignIcon;
import com.example.styles.BackgroundsProperty;
import com.example.styles.SeriesLineStyleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static com.example.tools.Tools.*;

public abstract class SeriesManagementPaneBase extends VBox {

    protected VBox dataSetPane = new VBox();
    protected ObservableList<Node> dataSetPaneChildren = dataSetPane.getChildren();
    protected GridPane seriesProperties = new GridPane();
    protected TextArea seriesName = new TextArea();
    protected ColorPicker areaStrokeColor = new ColorPicker();
    protected ColorPicker areaFillColor = new ColorPicker();
    protected ColorPicker lineColor = new ColorPicker();
    protected TextField lineWidth=new TextField();
    protected Button loadDataFromFileButton = new Button("Load data from file");
    protected Button primaryAddButton = new Button();
    protected Label primaryEmptySpace = new Label("Add new data");


    protected SeriesManagementPaneBase(SeriesLineStyleProperty lineStyleProperty, BackgroundsProperty areaStyleProperty) {

        super();

        setGraphics();

        setSizes(lineStyleProperty==null,areaStyleProperty==null);

        seriesProperties.addRow(0, loadDataFromFileButton);
        seriesProperties.addRow(1, new Label("Legend : "), seriesName);
        if (lineStyleProperty != null) {
            seriesProperties.addRow(2, new Label("Line color  : "), lineColor);
            seriesProperties.addRow(3, new Label("Line width  : "), lineWidth);
            lineColor.setValue(lineStyleProperty.color.get());
            lineColor.setOnAction(event -> lineStyleProperty.color.set(lineColor.getValue()));
            lineWidth.setText(String.valueOf(lineStyleProperty.width.get()));
            lineWidth.focusedProperty().addListener((a, b, c) -> {
                if (!c) {
                    lineWidth.setText(String.valueOf(lineStyleProperty.width.get()));
                }
            });
            lineWidth.setOnKeyTyped(keyEvent -> {
                try {
                    lineStyleProperty.width.set(Double.parseDouble(lineWidth.getText()));
                } catch (NumberFormatException e) {
                    print(e);
                }
            });
        }
        if (areaStyleProperty != null) {
            seriesProperties.addRow(4, new Label("Area fill color    : "), areaFillColor);
            seriesProperties.addRow(5, new Label("Area stroke color  : "), areaStrokeColor);
//            areaFillColor.setValue(areaStyleProperty.fill.get());
//            areaFillColor.setOnAction(event -> areaStyleProperty.fill.set(areaFillColor.getValue()));
//            areaStrokeColor.setValue(areaStyleProperty.stroke.get());
//            areaStrokeColor.setOnAction(event -> areaStyleProperty.stroke.set(areaStrokeColor.getValue()));
        }

        getChildren().addAll(seriesProperties, dataSetPane);

        dataSetPaneChildren.addListener((ListChangeListener< Node>) change->autosize());

    }

    void setGraphics() {
        primaryAddButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

    public abstract void setLoadDataFromFile();

    public abstract void setPaneBySeries();

    void setSizes(boolean isLineNull, boolean isAreaNull) {
        setSize(primaryAddButton, 40, 40);
        setSize(primaryEmptySpace, 200, 40);
        setSize(loadDataFromFileButton, 100, 40);
        setSize(seriesName, 100, 40);
        if (!isLineNull) {
            setSize(lineColor, 100, 40);
        }
        if (!isAreaNull) {
            setSize(areaFillColor, 100, 40);
            setSize(areaStrokeColor, 100, 40);
        }
    }

}
