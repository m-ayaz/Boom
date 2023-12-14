package com.example.test;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.util.Random;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.uuid;

public class Test10 extends Application {
    @Override
    public void start(Stage stage) {

        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());


        Random rnd = new Random();

        for (int j = 0; j < 3; j++) {
            barChart.getData().add(new XYChart.Series<>());
            for (int i = 0; i < 3; i++) {

                XYChart.Data<String, Number> d = new XYChart.Data<>(uuid(5), rnd.nextDouble());
                barChart.getData().get(j).getData().add(d);
            }
        }


        StringProperty css = new SimpleStringProperty();

//        lineChart.styleProperty().bind(css);

        barChart.setOnMouseMoved(mouseEvent -> {
//            css.set("-fx-background-color: rgb(%f,%f,%f);".formatted(rnd.nextDouble() * 255, rnd.nextDouble() * 255, rnd.nextDouble() * 255));
            css.set("-fx-fill: rgb(%f,%f,%f);".formatted(rnd.nextDouble() * 255, rnd.nextDouble() * 255, rnd.nextDouble() * 255));
//            xx.set("-fx-fill: CHART_COLOR_1;");
        });

//        barChart.getData().get(0).getData().get(1).getNode().setStyle("-fx-background-color: blue;-fx-shape: \"M150 0 L75 200 L225 200 Z\";");

//        print();
//        StringProperty xx = new SimpleStringProperty();
//        barChart.getData().get(0).setStyle("-fx-background-color: red;");
//        print();

//        barChart.getData().get(0).getNode().lookup(".chart-series-area-fill").styleProperty().bind(css);

        Pane pane = new Pane();

        pane.getChildren().add(barChart);

        Scene scene = new Scene(pane);

        stage.setScene(scene);

        stage.show();

//        barChart.lookup(".chart-bar").setStyle("-fx-background-color: red");
//
//        print(barChart.getData().get(0).getNode());




    }
    public static void main(String[] args) {
        launch();
    }





}

