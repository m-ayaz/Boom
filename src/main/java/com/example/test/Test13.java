package com.example.test;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example.tools.Tools.*;


public class Test13 extends Application {
    @Override
    public void start(Stage stage) {




        Pane pane = new Pane();
        Button button=new Button("Button");
        pane.getChildren().add(button);


//        r.setTranslateX(50);
//        r.setTranslateY(50);






        Popup popup=new Popup();
//        popup.getContent().add(r);

        Pane popupPane=new Pane();

        Circle r=new Circle(50);
        r.setFill(Color.color(1,0,0,0.5));

        popupPane.getChildren().add(r);

        popup.getContent().add(popupPane);

//        Rectangle rect = new Rectangle (100, 40, 100, 100);
//        r.setArcHeight(50);
//        r.setArcWidth(50);
        r.setFill(Color.VIOLET);

        FadeTransition ft = new FadeTransition(Duration.millis(1000), popupPane);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);
//        ft.setAutoReverse(true);

        ft.setOnFinished(event->popup.hide());

        Color c=new Color(1,0,0,1);

//        popup.setAutoHide(true);

//        popupPane.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
//            print(uuid(20));
//        });
//
//        popup.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
//            print(uuid(10));
//        });
//
//        popupPane.focusVisibleProperty().addListener((a,b,c)->{
//            print(uuid(50));
//        });
//
//        popupPane.focusWithinProperty().addListener((a,b,c) -> {
//            print(uuid(30));
//        });
//
//        popup.().addListener((a,b,c)->{
//            print(uuid(50));
//        });

//        popup.focusWithinProperty().addListener((a,b,c) -> {
//            print(uuid(30));
//        });

//        popup.setAutoFix(true);
//        popup.seton
//        popup.s

//        popup.setOnAutoHide(event -> {
//            print("al;sl;als;als;als;asl");
//        });

//        button.setOnAction(event -> {
//            ft.play();
//
//        });
















        setCustomSize(pane,600,400);


        Scene scene = new Scene(pane);

        stage.setScene(scene);

        stage.show();

        popup.show(stage);

    }
    public static void main(String[] args) {
        launch();
    }




}

