package com.example.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Random;

import static com.example.tools.Tools.print;

public class Test11 extends Application {
    @Override
    public void start(Stage stage)
    {

        // set title for the stage
//        stage.setTitle("Creating popup");

        // create a button
//        Button button = new Button("button");

        // create a tile pane
        TilePane tilepane = new TilePane();

        // create a label
        Label label = new Label("This is a Popup");

        // create a popup
        Popup popup = new Popup();

//        // set background
//        label.setStyle(" -fx-background-color: white;");

        // add the label
        popup.getContent().add(label);

//        // set size of label
//        label.setMinWidth(80);
//        label.setMinHeight(50);

        // action event
//        EventHandler<ActionEvent> event =
//                new EventHandler<ActionEvent>() {
//
//                    public void handle(ActionEvent e)
//                    {
//                        if (!popup.isShowing())
//                            popup.show(stage);
//                        else
//                            popup.hide();
//                    }
//                };

        // when button is pressed
//        button.setOnAction(event1->{if (!popup.isShowing())
//            popup.show(stage);
//        else
//            popup.hide();});

        // add button
//        tilepane.getChildren().add(button);

        // create a scene
        Scene scene = new Scene(tilepane, 200, 200);

        scene.setFill(Color.TRANSPARENT);

        tilepane.setBackground(Background.fill(Color.TRANSPARENT));

//        stage.

        // set the scene
        stage.setScene(scene);

        stage.show();

        popup.show(stage);


    }
    public static void main(String[] args) {
        launch();
    }




}

