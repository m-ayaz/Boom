package com.example.test;

import com.example.apppaints.AppColorPicker;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.setSize;


public class Test14 extends Application {
    @Override
    public void start(Stage stage) {




        Pane pane = new Pane();

        setSize(pane,600,600);


        Scene scene = new Scene(pane);

        stage.setScene(scene);

        stage.show();


        AppColorPicker appColorPicker=new AppColorPicker(Color.ALICEBLUE);

        pane.getChildren().add(appColorPicker);

        print(Color.ALICEBLUE);









    }
    public static void main(String[] args) {
        launch();
    }




}

