package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;


public class MindMapTest extends Application {

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

    @Override
    public void start(Stage stage) {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


        Circle c1=new Circle(300,300,10);
        Circle c2=new Circle(500,700,10);
        Circle c3=new Circle(1000,400,10);
        Circle c4=new Circle(600,200,10);

        SVGPath svg = new SVGPath();
        svg.setContent("M300,300 Q1000,400 600,200");
        svg.setFill(Color.valueOf("00000033"));

        container.getChildren().addAll(svg,c1,c2,c3,c4);

        c1.setFill(Color.RED);
        c2.setFill(Color.RED);
        c3.setFill(Color.RED);
        c4.setFill(Color.RED);

//        c1.setRadius(0,0,5);

//        QuadCurve g;
//        SVGPath j;
//        j.


//        KeyEvent keyEvent;
//        keyEvent.
//        Consumer<Double,Integer> consumer;
//        Consumer<Double> consumer=(x)->{print(x+9);};
//        Supplier<Double> supplier=()->1.0;

//        supplier

//        consumer.accept(8.);

//        print();


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



//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMousePressed(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseDragged(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseClicked(mouseEvent -> print(mouseEvent.getClickCount()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));




    }










}

