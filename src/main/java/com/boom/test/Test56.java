package com.boom.test;

import com.boom.controllers.eventhandlers.mousehandler.AppMouseEventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test56 extends Application {

    public static void main(String[] args) {
        launch();
    }

//    boolean x=true;

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        List<Rectangle> rectangles=new ArrayList<>();

        double w=100;
        double h=100;

        for(int i=0;i<27;i++){
            print("_________________________________");
            print(rem(i,3));
            print(rem(i/3,3));
            print(rem(i/9,3));
//            print(rem(i/9,3));
//            print(rem(i/3,9));
//            print(rem(rem(i,27),3));
            rectangles.add(new Rectangle(w,h, new Color(rem(i,3)/2.,
            rem(i/3,3)/2.,
            rem(i/9,3)/2.,1)));
        }



        for(int i=0;i<27;i++){
            rectangles.get(i).setX(rem(i,5)*w);
            rectangles.get(i).setY((i/5)*h);

            print(rectangles.get(i).getFill());
//            print(rectangles.get(i));
        }

        container.getChildren().addAll(rectangles);

//        for(int i=0;i<9;i++){
//
//        }


//        AppMouseEventHandler handler1=new AppMouseEventHandler(10);
//        AppMouseEventHandler handler2=new AppMouseEventHandler(20);
//
//        container.setOnMouseClicked(handler1);
//        container.setOnMouseMoved(handler2);
//
////        container.setOnMouse

//        Polygon polygon=new Polygon();
//
//        container.getChildren().add(polygon);
//
//        polygon.getPoints().addAll(300.,300.,300.,450.,450.,450.);




//        LineChart<Number,Number> lineChart=new LineChart<>(new NumberAxis(),new NumberAxis());
//
//        container.getChildren().add(lineChart);
//
//        setCustomSize(lineChart,400,400);
//
//        lineChart.getTransforms().add(new Translate(100,100));

//        Region region=new Region();
//        setCustomSize(region,400,400);
//        Ellipse ellipse=new Ellipse(200,200,200,200);
//        container.getChildren().add(region);
//        region.setBackground(Background.fill(new Color(1,0,0,0.2)));
//
//        container.setOnMouseMoved(mouseEvent -> {
//
//            print(region.contains(region.localToParent(mouseEvent.getX(),mouseEvent.getY()))&&
//                    ellipse.contains(region.localToParent(mouseEvent.getX(),mouseEvent.getY())));
//        });
//
//        region.setShape(ellipse);

//        Rectangle rectangle=new Rectangle(100,100,200,200);
//        rectangle.setFill(new Color(1,0,0,0.2));
//        Circle circle=new Circle(20);
//        circle.setFill(Color.BLACK);
//
//        container.getChildren().addAll(rectangle,circle);
//
//        rectangle.getTransforms().add(new Rotate(45,100,100));
//
//        print(rectangle.localToParent(200,200));
//
//        circle.setCenterX(rectangle.localToParent(200,200).getX());
//        circle.setCenterY(rectangle.localToParent(200,200).getY());



//        Ellipse ellipse=new Ellipse(300,300,200,100);
//        ellipse.setFill(Color.valueOf("00000088"));
//
//        container.getChildren().add(ellipse);
//
//        container.setOnMouseMoved(mouseEvent -> {
////            double x=mouseEvent.getX();
////            double y=mouseEvent.getY();
//            double x=mouseEvent.getSceneX();
//            double y=mouseEvent.getSceneY();
////            double x=mouseEvent.getScreenX();
////            double y=mouseEvent.getScreenY();
//            print("___________________________________");
//            print(ellipse.contains(ellipse.localToParent(x,y)));
//            print(ellipse.contains(ellipse.localToScene(x,y)));
//            print(ellipse.contains(ellipse.localToScreen(x,y)));
//            print(ellipse.contains(ellipse.parentToLocal(x,y)));
//            print(ellipse.contains(ellipse.sceneToLocal(x,y)));
//            print(ellipse.contains(ellipse.screenToLocal(x,y)));
//        });
//
//        Affine affine=new Affine();
//        affine.append(new Rotate(45,300,300));
//        affine.append(new Scale(1.5,1.3));
//
//        ellipse.getTransforms().add(affine);
//
//        print(ellipse.getLocalToParentTransform());
//        print(ellipse.getLocalToSceneTransform());
//        print(affine);

//        container.getChildren().add(new Chessboard(100,5,10,Color.BLACK,Color.WHEAT));

//        LittleCubicCurveOnCursor littleQuadCurveOnCursor=new LittleCubicCurveOnCursor();
//        container.getChildren().add(littleQuadCurveOnCursor);
//        littleQuadCurveOnCursor.show(300,300);


    }

    int rem(int x,int y){
        return x-y*(x/y);
    }

}





