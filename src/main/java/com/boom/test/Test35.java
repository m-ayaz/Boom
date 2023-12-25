package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;


public class Test35 extends Application {

    public static void main(String[] args) {
        launch();
    }


//    String x;

//    Button rectButton=new Button("Rectangle");
//    Button ellButton=new Button("Ellipse");
//    Button arcButton=new Button("Arc");
//
//    Button lcnnButton=new Button("LineChart_NN");
//
//    StringProperty h=new SimpleStringProperty();

//    double

    @Override
    public void start(Stage stage) {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


//        double a=20;
//        Circle c1=new Circle(a);
//        Circle c2=new Circle(a);
//        Circle c3=new Circle(a);
//        Circle c4=new Circle(a);
//
//         CubicCurve quadCurve=new CubicCurve();
//        quadCurve.setFill(new Color(1,0,0,0.2));
//
////        print(quadCurve.toString());
//
//        container.getChildren().addAll(quadCurve,c1,c2,c3,c4);
//
//        quadCurve.startXProperty().bind(c1.centerXProperty());
//        quadCurve.startYProperty().bind(c1.centerYProperty());
//
//        quadCurve.controlX1Property().bind(c2.centerXProperty());
//        quadCurve.controlY1Property().bind(c2.centerYProperty());
//
//        quadCurve.controlX2Property().bind(c3.centerXProperty());
//        quadCurve.controlY2Property().bind(c3.centerYProperty());
//
//        quadCurve.endXProperty().bind(c4.centerXProperty());
//        quadCurve.endYProperty().bind(c4.centerYProperty());
//
//
//
//        c1.setOnMouseDragged(mouseEvent -> {
//            print(uuid(50));
////            print();
//            c1.setCenterX(mouseEvent.getX());
//            c1.setCenterY(mouseEvent.getY());
//        });
//
//        c2.setOnMouseDragged(mouseEvent -> {
////            print();
//            c2.setCenterX(mouseEvent.getX());
//            c2.setCenterY(mouseEvent.getY());
//        });
//
//        c3.setOnMouseDragged(mouseEvent -> {
////            print();
//            c3.setCenterX(mouseEvent.getX());
//            c3.setCenterY(mouseEvent.getY());
//        });
//
//        c4.setOnMouseDragged(mouseEvent -> {
////            print();
//            c4.setCenterX(mouseEvent.getX());
//            c4.setCenterY(mouseEvent.getY());
//        });
//
//
////        M300,200 h-150 a150,150 0 1,0 150,-150 z
//        c1.setCenterX(200);
//        c1.setCenterY(200);
//
//        c2.setCenterX(200);
//        c2.setCenterY(50);

        double centerX;
        double centerY;

        double radiusX;
        double radiusY;

        double vOffset;
        double hOffset;

        double xAxisRotation;

        boolean largeArcFlag;
        boolean sweepFlag;

        double x;
        double y;

        double a = 10;
        Circle c1 = new Circle(a);
        Circle c2 = new Circle(a);
        Circle c3 = new Circle(a);
        Circle c4 = new Circle(a);
        Circle c5 = new Circle(a);

        centerX = 400;
        centerY = 400;
        radiusX = 100;
        radiusY = 100;
        hOffset = 100;
        vOffset = 50;
        xAxisRotation = 40;
        largeArcFlag = true;
        sweepFlag = true;
        x = 100;
        y = 100;

        container.getChildren().addAll(getSVGPath(centerX, centerY, radiusX, radiusY,
                vOffset, hOffset, xAxisRotation, largeArcFlag, sweepFlag, x, y
                , new Color(0, 0, 1, 0.4)), c1, c2, c3, c4, c5);
//        container.getChildren().addAll(getSVGPath( 400,  400,
//                100,  200, 0,  0, 0,
//                true,  true, 100, 200, new Color(0,0,1,0.4)),c1,c2,c3,c4,c5);
        c1.setCenterX(centerX);
        c1.setCenterY(centerY);
        c2.setCenterX(centerX + x);
        c2.setCenterY(centerY + y);

        String hh="M600,350 l 50,-25 " +
                "a25,25 -45 0,1 50,25 l 70,-25 " +
                "a25,50 -45 0,1 50,-25 l 50,-25 " +
                "a25,75 -45 0,1 50,-25 l 50,-25 " +
                "a25,100 -45 0,1 50,-25 l 50,-25";
        SVGPath svgPath=new SVGPath();
        svgPath.setContent(hh);
        svgPath.setFill(Color.TRANSPARENT);
        svgPath.setStroke(Color.RED);

        container.getChildren().add(svgPath);
//        c3.setCenterX(400-50);
//        c3.setCenterY(400);
//        c4.setCenterX(400);
//        c4.setCenterY(400-50);
//        c5.setCenterX(400);
//        c5.setCenterY(400+50);


    }

    SVGPath getSVGPath(double centerX, double centerY,

                       double radiusX, double radiusY,

                       double vOffset, double hOffset,

                       double xAxisRotation,

                       boolean largeArcFlag, boolean sweepFlag,

                       double x, double y, Color color) {
        SVGPath svgPath = new SVGPath();
//        svgPath.setContent("M%f,%f a%f,%f %f %d,%d %f,%f z".formatted(centerX, centerY, radiusX, radiusY, xAxisRotation, largeArcFlag ? 1 : 0, sweepFlag ? 1 : 0, x, y));
        svgPath.setContent("M%f,%f h-%f v-%f a%f,%f %f %d,%d %f,%f z".formatted(centerX, centerY, hOffset, vOffset, radiusX, radiusY, xAxisRotation, largeArcFlag ? 1 : 0, sweepFlag ? 1 : 0, x, y));
        svgPath.setFill(color);
        return svgPath;
    }


}
