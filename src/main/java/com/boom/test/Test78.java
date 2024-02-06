package com.boom.test;

import com.boom.appshapes.AppRectangle;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test78 extends Application {

    public static void main(String[] args) {
        launch();
    }

    Random rnd = new Random();
//    DataComparator dataComparator = new DataComparator();

    @Override
    public void start(Stage stage) throws Exception {

        GridPane container = new GridPane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        AppRectangle appRectangle=new AppRectangle(200,200,0,0);






    }


}

