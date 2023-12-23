package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;


public class Test31 extends Application {

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

//        Rectangle rectangle=new Rectangle();

        Arc arc=new Arc();
        Arc arc1=new Arc();
        Arc arc2=new Arc();
        arc.setStartAngle(135);
        arc.setRadiusX(100);
        arc.setRadiusY(100);
        arc.setCenterX(0);
        arc.setCenterY(0);
        arc.setLength(90);
        arc.setFill(Color.TRANSPARENT);
        arc.setType(ArcType.ROUND);

        arc1.setStartAngle(135);
        arc1.setRadiusX(100);
        arc1.setRadiusY(100);
        arc1.setCenterX(0);
        arc1.setCenterY(0);
        arc1.setLength(90);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setType(ArcType.OPEN);

        arc2.setStartAngle(135);
        arc2.setRadiusX(100);
        arc2.setRadiusY(100);
        arc2.setCenterX(0);
        arc2.setCenterY(0);
        arc2.setLength(90);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.CHORD);

        arc.setStroke(Color.BLACK);
        arc1.setStroke(Color.BLACK);
        arc2.setStroke(Color.BLACK);

//        print(arc.computeAreaInScreen());
//        print(arc1.computeAreaInScreen());
//        print(arc2.computeAreaInScreen());


//        Rectangle rectangle=new Rectangle(1,1);

        container.getChildren().addAll(arc,arc1,arc2);

//        arc.setFill(Color.rgb(255,0,0,0.5));
//        arc1.setFill(Color.rgb(255,0,0,0.5));
//        arc2.setFill(Color.rgb(255,0,0,0.5));

        double a=200;

        arc.setTranslateX(a);
        arc.setTranslateY(a);

        arc1.setTranslateX(2*a);
        arc1.setTranslateY(a);

        arc2.setTranslateX(a);
        arc2.setTranslateY(2*a);

        print(arc.getBoundsInLocal());
        print(arc1.getBoundsInLocal());
        print(arc2.getBoundsInLocal());


//        arc.se





    }
}

