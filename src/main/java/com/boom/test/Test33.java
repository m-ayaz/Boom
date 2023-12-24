package com.boom.test;

import com.boom.appcharts.number_number.AppLineChart_NumberNumber;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppRectangle;
//import com.boom.controllers.eventhandlers.AppMouseEventHandler1;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;


public class Test33 extends Application {

    public static void main(String[] args) {
        launch();
    }

//    String x;

    Button rectButton=new Button("Rectangle");
    Button ellButton=new Button("Ellipse");
    Button arcButton=new Button("Arc");

    Button lcnnButton=new Button("LineChart_NN");

    StringProperty h=new SimpleStringProperty();

    @Override
    public void start(Stage stage) {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


//        Rectangle rectangle=new Rectangle(0,0,100,100);
//
//        Rectangle rectangle1=new Rectangle(100,100,100,100);



        rectButton.setOnAction(event -> h.set("rect"));
        ellButton.setOnAction(event -> h.set("ell"));
        arcButton.setOnAction(event -> h.set("arc"));
        lcnnButton.setOnAction(event -> h.set("lcnn"));

//        Rectangle rectangle;
//        Ellipse ellipse;
//        Arc arc;
//        Polyline polyline=new Polyline();

        HBox hBox=new HBox(rectButton,ellButton,arcButton,lcnnButton);
//        setCustomHeight(hBox,100);

        container.getChildren().addAll(hBox);

//        Cursor cursor=container.getCursor();

//        Mouse
//        ActionEvent actionEvent=new ActionEvent();
//        MouseEvent mouseEvent;

//        h.set("rect");

        AppRectangle appRectangle=new AppRectangle(0,0);
        AppEllipse appEllipse=new AppEllipse(0,0);
        AppLineChart_NumberNumber appLineChart_numberNumber=new AppLineChart_NumberNumber(0,0);

//        AppMouseEventHandler1 appMouseEventHandler1 =new AppMouseEventHandler1(appRectangle,appEllipse,appLineChart_numberNumber,h,container.getChildren());

        container.getChildren().addAll(appRectangle.getStyleableNode(),appEllipse.getStyleableNode(),appLineChart_numberNumber.getStyleableNode());

//        EventHandler<MouseEvent> eventHandler= mouseEvent -> {
//            mousePositionX=mouseEvent.getX();
//            mousePositionY=mouseEvent.getY();
////            mouseEvent.get
////                mouseEvent.get
//        };
//        actionEvent.

//        container.setOnMouseMoved(appMouseEventHandler1);
//        container.setOnMouseDragged(appMouseEventHandler1);
//        container.setOnMouseClicked(appMouseEventHandler1);
//        container.setOnMousePressed(appMouseEventHandler1);
//        container.setOnMouseReleased(appMouseEventHandler1);



//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMousePressed(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseDragged(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseClicked(mouseEvent -> print(mouseEvent.getClickCount()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));




    }










}

