package com.boom.test;

import com.boom.appcharts.number_number.AppLineChart_NumberNumber;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppRectangle;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;


public class Test34 extends Application {

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


        double a=20;
        Circle c1=new Circle(a);
        Circle c2=new Circle(a);
        Circle c3=new Circle(a);
        Circle c4=new Circle(a);

         CubicCurve quadCurve=new CubicCurve();
        quadCurve.setFill(new Color(1,0,0,0.2));

//        print(quadCurve.toString());

        container.getChildren().addAll(quadCurve,c1,c2,c3,c4);

        quadCurve.startXProperty().bind(c1.centerXProperty());
        quadCurve.startYProperty().bind(c1.centerYProperty());

        quadCurve.controlX1Property().bind(c2.centerXProperty());
        quadCurve.controlY1Property().bind(c2.centerYProperty());

        quadCurve.controlX2Property().bind(c3.centerXProperty());
        quadCurve.controlY2Property().bind(c3.centerYProperty());

        quadCurve.endXProperty().bind(c4.centerXProperty());
        quadCurve.endYProperty().bind(c4.centerYProperty());



        c1.setOnMouseDragged(mouseEvent -> {
            print(uuid(50));
//            print();
            c1.setCenterX(mouseEvent.getX());
            c1.setCenterY(mouseEvent.getY());
        });

        c2.setOnMouseDragged(mouseEvent -> {
//            print();
            c2.setCenterX(mouseEvent.getX());
            c2.setCenterY(mouseEvent.getY());
        });

        c3.setOnMouseDragged(mouseEvent -> {
//            print();
            c3.setCenterX(mouseEvent.getX());
            c3.setCenterY(mouseEvent.getY());
        });

        c4.setOnMouseDragged(mouseEvent -> {
//            print();
            c4.setCenterX(mouseEvent.getX());
            c4.setCenterY(mouseEvent.getY());
        });


//        M300,200 h-150 a150,150 0 1,0 150,-150 z
        c1.setCenterX(200);
        c1.setCenterY(200);

        c2.setCenterX(200);
        c2.setCenterY(50);

        SVGPath svgPath=new SVGPath();
        svgPath.setContent("M500,500 h-150 a150,150 0 1,1 150,-150 z");
        container.getChildren().addAll(svgPath);
        svgPath.setFill(Color.BLUE);


//        container.setOnMo


//
////        Rectangle rectangle=new Rectangle(0,0,100,100);
////
////        Rectangle rectangle1=new Rectangle(100,100,100,100);
//
//
//
//        rectButton.setOnAction(event -> h.set("rect"));
//        ellButton.setOnAction(event -> h.set("ell"));
//        arcButton.setOnAction(event -> h.set("arc"));
//        lcnnButton.setOnAction(event -> h.set("lcnn"));
//
////        Rectangle rectangle;
////        Ellipse ellipse;
////        Arc arc;
////        Polyline polyline=new Polyline();
//
//        HBox hBox=new HBox(rectButton,ellButton,arcButton,lcnnButton);
////        setCustomHeight(hBox,100);
//
//        container.getChildren().addAll(hBox);
//
////        Cursor cursor=container.getCursor();
//
////        Mouse
////        ActionEvent actionEvent=new ActionEvent();
////        MouseEvent mouseEvent;
//
////        h.set("rect");
//
//        AppRectangle appRectangle=new AppRectangle(0,0);
//        AppEllipse appEllipse=new AppEllipse(0,0);
//        AppLineChart_NumberNumber appLineChart_numberNumber=new AppLineChart_NumberNumber(0,0);
//
////        AppMouseEventHandler1 appMouseEventHandler1 =new AppMouseEventHandler1(appRectangle,appEllipse,appLineChart_numberNumber,h,container.getChildren());
//
//        container.getChildren().addAll(appRectangle.getStyleableNode(),appEllipse.getStyleableNode(),appLineChart_numberNumber.getStyleableNode());
//
////        EventHandler<MouseEvent> eventHandler= mouseEvent -> {
////            mousePositionX=mouseEvent.getX();
////            mousePositionY=mouseEvent.getY();
//////            mouseEvent.get
//////                mouseEvent.get
////        };
////        actionEvent.
//
////        container.setOnMouseMoved(appMouseEventHandler1);
////        container.setOnMouseDragged(appMouseEventHandler1);
////        container.setOnMouseClicked(appMouseEventHandler1);
////        container.setOnMousePressed(appMouseEventHandler1);
////        container.setOnMouseReleased(appMouseEventHandler1);
//
//
//
////        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
////        container.setOnMousePressed(mouseEvent -> print(mouseEvent.getEventType()));
////        container.setOnMouseDragged(mouseEvent -> print(mouseEvent.getEventType()));
////        container.setOnMouseClicked(mouseEvent -> print(mouseEvent.getClickCount()));
////        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
////        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
////        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//
//
//

    }










}

