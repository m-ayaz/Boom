package com.boom.test;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;

public class Test19 extends Application {

    Stage popup=new Stage();

    @Override
    public void start(Stage primaryStage) {

//        Button showPopup = new Button("Show popup");
        Pane root = new Pane();
        Scene scene = new Scene(root, 350, 120);
        primaryStage.setScene(scene);
        primaryStage.show();



        fff fffg=new fff();

        fffg.show();






//        showPopup.setOnAction(e -> {
//
////            popup.initModality(Modality.NONE);
////            popup.initOwner(Stage.getWindows().get(0));
//
//            popup.show();
//        });


//        Pane r=new Pane();
//        r.setBackground(Background.fill(Color.RED));
//
//        Scene scene1 = new Scene(r, 120, 40);
//        popup.setScene(scene1);
//
//
//        popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//
//            if (! isNowFocused) {
////                popup.hide();
//            }
//        });
//
////        popup.setFullScreen(true);
//
////popup.getIcons().add(new );
//
////        popup.
//
//        FadeTransition fadeTransition=new FadeTransition();
//        fadeTransition.setDuration(Duration.millis(1000));
//        fadeTransition.setFromValue(1);
//        fadeTransition.setToValue(0);
//        fadeTransition.setNode(r);
//
//
////        fadeTransition.
////        fadeTransition.
//
//
////        primaryStage.
//
//
//
//
//
//        popup.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class fff extends Stage{

    FadeTransition fadeTransition=new FadeTransition();
    Pane r=new Pane();

    Scene scene1 = new Scene(r, 120, 40);

    public fff(){

        initStyle(StageStyle.UNDECORATED);

        setAlwaysOnTop(true);

        r.setBackground(Background.fill(Color.RED));


        setScene(scene1);


        focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {

        if (! isNowFocused) {
//            requestFocus();
            fadeTransition.play();
//                hide();
        }
    });

        fadeTransition.setOnFinished(event -> {
            hide();
        });

//        popup.setFullScreen(true);

//popup.getIcons().add(new );

//        popup.


        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setNode(r);


//        fadeTransition.
//        fadeTransition.


//        primaryStage.





}}