package com.boom.test;

import com.boom.appshapes.AppLine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

import static com.boom.tools.Tools.setCustomSize;


public class Test29 extends Application {

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

        Affine affine=new Affine();

        Rectangle shape=new Rectangle(700,500);
        Region fill=new Region();
        Region stroke=new Region();

        shape.getTransforms().add(affine);
        fill.getTransforms().add(affine);
        stroke.getTransforms().add(affine);

        container.getChildren().addAll(shape,fill,stroke);

        setCustomSize(fill,700,500);
        setCustomSize(stroke,700,500);

        fill.setBackground(Background.fill(Color.rgb(255,0,0,0.3)));
        stroke.setStyle("-fx-background-color: transparent;-fx-border-color: rgba(0,0,255,0.3);-fx-border-width: 50px;-fx-stroke-type: inside");


//        stroke.setBackground(Background.EMPTY);
//        stroke.setBorder(Border.stroke(Color.BLACK));

        shape.setFill(Color.TRANSPARENT);
        shape.setStrokeWidth(0);

        affine.prepend(new Translate(100,100));



//        ellipse.setFill();



//        AppLine appLine=new AppLine(300,300);
//
//        container.getChildren().addAll(appLine.getNode(),appLine.getRegion______________temp());

//        appLine.affineTransform.prependTranslation(50,50);

//        Region region=new Region();
//
//        container.getChildren().add(region);

//        region







//        Line line=new Line(100,100,300,300);
//
//        line.setStrokeWidth(50);
//
////        container.getChildren().add(line);
//
//        line.setStrokeLineCap(StrokeLineCap.ROUND);
//
//        Polyline polyline=new Polyline();
//        Polyline polyline1=new Polyline();
//
//        container.getChildren().addAll(polyline,polyline1);
//        polyline.setStroke(Color.rgb(0,0,0,0.3));
//        polyline1.setStroke(Color.RED);
//        polyline1.setStrokeWidth(5);
//        polyline.setStrokeWidth(50);
//
//        List<Double> points=Arrays.asList(50.,50.,50.,400.,400.,400.,400.,50.,50.,50.);
////        List<Double> points=Arrays.asList(50.,50.,400.,400.,750.,50.);
//
//        polyline.getPoints().addAll(points);
//        polyline1.getPoints().addAll(points);
//
//
//
//        polyline.setStrokeLineCap(StrokeLineCap.ROUND);
//
//        polyline.setStrokeLineJoin(StrokeLineJoin.ROUND);
//
//        polyline.setStrokeType(StrokeType.CENTERED);
//
////        polyline.setStrokeMiterLimit(8);
//
//
//
//
//
//
//
//
//
//






//        polyline.setStrokeType(StrokeType.INSIDE);



    }
}

