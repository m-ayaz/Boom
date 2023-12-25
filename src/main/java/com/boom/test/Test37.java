package com.boom.test;

import javafx.application.Application;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.function.Function;

import static com.boom.tools.Tools.print;


public class Test37 extends Application {

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
    public void start(Stage stage) throws NoSuchMethodException {

//        Pane container = new Pane();
        Button button=new Button("Button");
        Scene scene = new Scene(button);
        stage.setScene(scene);
        stage.show();



//        container.getChildren().addAll(button);

        Random rnd=new Random();

        DoubleProperty x=new SimpleDoubleProperty();
        DoubleProperty y=squared(x.add(0));

//        Method method=new

        button.setOnAction(event -> {
            x.set(rnd.nextDouble()*10);
            print("=================================");
            print("x  = "+x);
            print("y  = "+y);
            print("y1 = "+Math.sqrt(x.get()));
        });








    }

    DoubleProperty sqrt(DoubleProperty x){
        DoubleProperty y=new SimpleDoubleProperty();
        x.addListener(v->y.set(Math.sqrt(x.get())));
        return y;
    }

    DoubleProperty squared(DoubleExpression x) {
        DoubleProperty y = new SimpleDoubleProperty();
        x.addListener((a, b, c) -> y.set(c.doubleValue() * c.doubleValue()));
        return y;
    }

//    DoubleProperty squared(DoubleProperty x, Function<Number,Number> function){
//        DoubleProperty y=new SimpleDoubleProperty();
//        x.addListener((ChangeListener<? super Number>)  (a, b, c)->y.set(function.apply(c).doubleValue()));
//        return y;
//    }
//        Circle cLeft=new Circle(),cRight=new Circle();
//
//        Random rnd=new Random();
//
//
//        double rLeft = 150 * rnd.nextDouble() + 150;
//        double rRight = 90 * rnd.nextDouble() + 90;
//        double distance = 1000;
//
//        cLeft.setRadius(rLeft);
//        cRight.setRadius(rRight);
//        cRight.setCenterX(distance);
//
//        cLeft.setFill(new Color(1,0,0,1));
//        cRight.setFill(new Color(0,0,1,1));
//
////        SVGPath svgPath=new SVGPath();
//        SVGPath svgPath=test(rLeft, rRight, distance, 1.1, 1.1, (Color) cLeft.getFill(), (Color) cRight.getFill());
//
//        container.getChildren().addAll(cLeft,cRight,svgPath);
//
////        for(int i=0;i<50;i++) {
////            print("i = "+i);
//
//
//
//
////        }
//
//
//
//
//        cLeft.setTranslateX(200);
//        cLeft.setTranslateY(300);
//        cRight.setTranslateX(200);
//        cRight.setTranslateY(300);
//        svgPath.setTranslateX(200);
//        svgPath.setTranslateY(300);
//
//
////        Circle circle=new Circle();
////        SVGPath svgPath=new SVGPath();
////
////        svgPath.setFill(Color.TRANSPARENT);
////        svgPath.setStroke(Color.BLACK);
////        circle.setFill(Color.TRANSPARENT);
////        circle.setStroke(Color.BLACK);
//////        container.getChildren().addAll(test(2),circle);
////
//////        svgPath.setRotate(45);
//////        svgPath.2
////
////        double r1=500;
////        double l1=600;
////        double l2=500;
////        double w=100;
////
////        double l1p=r1*r1/l1;
////        double h=Math.sqrt(1-r1*r1/l1/l1)*r1;
////        double l2p=l1-w/2*Math.sqrt(l1*l1/r1/r1-1);
////
////        circle.setRadius(r1/2);
////
////
////        svgPath.setContent("M%f,%f Q%f,%f %f,%f".formatted(l1p,h,l2p,w/2,l1+l2,w/2));
////
//////        svgPath
////
//////        print(svgPath.getContent());
//
//
//
//
////        double a=20;
////        Circle c1=new Circle(a);
////        Circle c2=new Circle(a);
////        Circle c3=new Circle(a);
////        Circle c4=new Circle(a);
////
////         CubicCurve quadCurve=new CubicCurve();
////        quadCurve.setFill(new Color(1,0,0,0.2));
////
//////        print(quadCurve.toString());
////
////        container.getChildren().addAll(quadCurve,c1,c2,c3,c4);
////
////        quadCurve.startXProperty().bind(c1.centerXProperty());
////        quadCurve.startYProperty().bind(c1.centerYProperty());
////
////        quadCurve.controlX1Property().bind(c2.centerXProperty());
////        quadCurve.controlY1Property().bind(c2.centerYProperty());
////
////        quadCurve.controlX2Property().bind(c3.centerXProperty());
////        quadCurve.controlY2Property().bind(c3.centerYProperty());
////
////        quadCurve.endXProperty().bind(c4.centerXProperty());
////        quadCurve.endYProperty().bind(c4.centerYProperty());
////
////
////
////        c1.setOnMouseDragged(mouseEvent -> {
////            print(uuid(50));
//////            print();
////            c1.setCenterX(mouseEvent.getX());
////            c1.setCenterY(mouseEvent.getY());
////        });
////
////        c2.setOnMouseDragged(mouseEvent -> {
//////            print();
////            c2.setCenterX(mouseEvent.getX());
////            c2.setCenterY(mouseEvent.getY());
////        });
////
////        c3.setOnMouseDragged(mouseEvent -> {
//////            print();
////            c3.setCenterX(mouseEvent.getX());
////            c3.setCenterY(mouseEvent.getY());
////        });
////
////        c4.setOnMouseDragged(mouseEvent -> {
//////            print();
////            c4.setCenterX(mouseEvent.getX());
////            c4.setCenterY(mouseEvent.getY());
////        });
////
////
//////        M300,200 h-150 a150,150 0 1,0 150,-150 z
////        c1.setCenterX(200);
////        c1.setCenterY(200);
////
////        c2.setCenterX(200);
////        c2.setCenterY(50);
//
////        double centerX;
////        double centerY;
////
////        double radiusX;
////        double radiusY;
////
////        double vOffset;
////        double hOffset;
////
////        double xAxisRotation;
////
////        boolean largeArcFlag;
////        boolean sweepFlag;
////
////        double x;
////        double y;
////
////        double a = 10;
////        Circle c1 = new Circle(a);
////        Circle c2 = new Circle(a);
////        Circle c3 = new Circle(a);
////        Circle c4 = new Circle(a);
////        Circle c5 = new Circle(a);
////
////        centerX = 400;
////        centerY = 400;
////        radiusX = 100;
////        radiusY = 100;
////        hOffset = 100;
////        vOffset = 50;
////        xAxisRotation = 40;
////        largeArcFlag = true;
////        sweepFlag = true;
////        x = 100;
////        y = 100;
////
////        container.getChildren().addAll(getSVGPath(centerX, centerY, radiusX, radiusY,
////                vOffset, hOffset, xAxisRotation, largeArcFlag, sweepFlag, x, y
////                , new Color(0, 0, 1, 0.4)), c1, c2, c3, c4, c5);
//////        container.getChildren().addAll(getSVGPath( 400,  400,
//////                100,  200, 0,  0, 0,
//////                true,  true, 100, 200, new Color(0,0,1,0.4)),c1,c2,c3,c4,c5);
////        c1.setCenterX(centerX);
////        c1.setCenterY(centerY);
////        c2.setCenterX(centerX + x);
////        c2.setCenterY(centerY + y);
////
////        String hh="M600,350 l 50,-25 " +
////                "a25,25 -45 0,1 50,25 l 70,-25 " +
////                "a25,50 -45 0,1 50,-25 l 50,-25 " +
////                "a25,75 -45 0,1 50,-25 l 50,-25 " +
////                "a25,100 -45 0,1 50,-25 l 50,-25";
////        SVGPath svgPath=new SVGPath();
////        svgPath.setContent(hh);
////        svgPath.setFill(Color.TRANSPARENT);
////        svgPath.setStroke(Color.RED);
////
////        container.getChildren().add(svgPath);
//////        c3.setCenterX(400-50);
//////        c3.setCenterY(400);
//////        c4.setCenterX(400);
//////        c4.setCenterY(400-50);
////        c5.setCenterX(400);
////        c5.setCenterY(400+50);
//
//
//    }
//
////    SVGPath getSVGPath(double centerX, double centerY,
////
////                       double radiusX, double radiusY,
////
////                       double vOffset, double hOffset,
////
////                       double xAxisRotation,
////
////                       boolean largeArcFlag, boolean sweepFlag,
////
////                       double x, double y, Color color) {
////        SVGPath svgPath = new SVGPath();
//////        svgPath.setContent("M%f,%f a%f,%f %f %d,%d %f,%f z".formatted(centerX, centerY, radiusX, radiusY, xAxisRotation, largeArcFlag ? 1 : 0, sweepFlag ? 1 : 0, x, y));
////        svgPath.setContent("M%f,%f h-%f v-%f a%f,%f %f %d,%d %f,%f z".formatted(centerX, centerY, hOffset, vOffset, radiusX, radiusY, xAxisRotation, largeArcFlag ? 1 : 0, sweepFlag ? 1 : 0, x, y));
////        svgPath.setFill(color);
////        return svgPath;
////    }
//
//    SVGPath test(double rLeft,double rRight,double distance,double factorLeft,double factorRight,Color colorLeft,Color colorRight){
//        double l1Left=rLeft*factorLeft;
//        double l2Left=rLeft;
//        double l1Right=rRight*factorRight;
//        double l2Right=rRight;
//        double w=30;
//        if(distance<l1Left+l2Left+l1Right+l2Right){
//            throw new RuntimeException("MindMap nodes are too close. Value = %f".formatted(distance-(l1Left+l2Left+l1Right+l2Right)));
//        }
//
//
//        double l1Leftp=rLeft*rLeft/l1Left;
//        double hLeft=Math.sqrt(1-rLeft*rLeft/l1Left/l1Left)*rLeft;
//        double l2Leftp=l1Left-w/2*Math.sqrt(l1Left*l1Left/rLeft/rLeft-1);
//
//        double l1Rightp=rRight*rRight/l1Right;
//        double hRight=Math.sqrt(1-rRight*rRight/l1Right/l1Right)*rRight;
//        double l2Rightp=l1Right-w/2*Math.sqrt(l1Right*l1Right/rRight/rRight-1);
//
//        SVGPath svgPath=new SVGPath();
//        svgPath.setContent(("M%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f z").formatted(
//                l1Leftp,hLeft,l2Leftp,w/2,l1Left+l2Left,w/2, distance-l1Right-l2Right,w/2,distance-l2Rightp,w/2,distance-l1Rightp,hRight,
//                distance-l1Rightp,-hRight,distance-l2Rightp,-w/2,distance-l1Right-l2Right,-w/2, l1Left+l2Left,-w/2,l2Leftp,-w/2,l1Leftp,-hLeft));
//        svgPath.setFill(new LinearGradient(rLeft,0,distance-rRight,0,false, CycleMethod.NO_CYCLE,
//                new Stop(0,colorLeft),new Stop(1,colorRight)));
//        svgPath.setStroke(Color.TRANSPARENT);
//        svgPath.setStrokeWidth(0);
//
//
//        return svgPath;
//    }


}
