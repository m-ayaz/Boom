package com.boom.test;

import com.boom.controllers.eventhandlers.AppMouseEventHandler;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicTreeUI;

import static com.boom.tools.Tools.print;


public class Test33 extends Application {

    public static void main(String[] args) {
        launch();
    }

    String x;

    Button rectButton=new Button("Rectangle");
    Button ellButton=new Button("Ellipse");
    Button arcButton=new Button("Arc");

    @Override
    public void start(Stage stage) {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


//        Rectangle rectangle=new Rectangle(0,0,100,100);
//
//        Rectangle rectangle1=new Rectangle(100,100,100,100);



        rectButton.setOnAction(event -> x="rect");
        ellButton.setOnAction(event -> x="ell");
        arcButton.setOnAction(event -> x="arc");

        Rectangle rectangle;
        Ellipse ellipse;
        Arc arc;
//        Polyline polyline=new Polyline();

        HBox hBox=new HBox(rectButton,ellButton,arcButton);
//        setCustomHeight(hBox,100);

        container.getChildren().addAll(hBox);

//        Cursor cursor=container.getCursor();

//        Mouse
//        ActionEvent actionEvent=new ActionEvent();
//        MouseEvent mouseEvent;
        StringProperty h=new SimpleStringProperty();

        AppMouseEventHandler appMouseEventHandler=new AppMouseEventHandler(h);

//        EventHandler<MouseEvent> eventHandler= mouseEvent -> {
//            mousePositionX=mouseEvent.getX();
//            mousePositionY=mouseEvent.getY();
////            mouseEvent.get
////                mouseEvent.get
//        };
//        actionEvent.

        container.setOnMouseMoved(appMouseEventHandler);
        container.setOnMouseDragged(appMouseEventHandler);
        container.setOnMouseClicked(appMouseEventHandler);



//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMousePressed(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseDragged(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseClicked(mouseEvent -> print(mouseEvent.getClickCount()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));
//        container.setOnMouseReleased(mouseEvent -> print(mouseEvent.getEventType()));




    }










}

