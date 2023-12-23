package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test28 extends Application {

//    public static void setSize(Region region, double width, double height) {
//        region.setMaxSize(width, height);
//        region.setPrefSize(width, height);
//        region.setMinSize(width, height);
//    }

    public static void main(String[] args) {
        launch();
    }

//    boolean x = false;

//    Translate translate = new Translate();

    @Override
    public void start(Stage stage) {

//        Affine affine = new Affine();
//        Random rnd = new Random();

        // Initialize and show the main canvas.
        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        Line line=new Line(100,100,300,300);

        line.setStrokeWidth(50);

//        container.getChildren().add(line);

        line.setStrokeLineCap(StrokeLineCap.ROUND);

        Polyline polyline=new Polyline();
        Polyline polyline1=new Polyline();

        container.getChildren().addAll(polyline,polyline1);
        polyline.setStroke(Color.rgb(0,0,0,0.3));
        polyline1.setStroke(Color.RED);
        polyline1.setStrokeWidth(5);
        polyline.setStrokeWidth(50);

        List<Double> points=Arrays.asList(50.,50.,50.,400.,400.,400.,400.,50.,50.,50.);
//        List<Double> points=Arrays.asList(50.,50.,400.,400.,750.,50.);

        polyline.getPoints().addAll(points);
        polyline1.getPoints().addAll(points);



        polyline.setStrokeLineCap(StrokeLineCap.ROUND);

        polyline.setStrokeLineJoin(StrokeLineJoin.ROUND);

        polyline.setStrokeType(StrokeType.CENTERED);

//        polyline.setStrokeMiterLimit(8);
















//        polyline.setStrokeType(StrokeType.INSIDE);



    }
}

