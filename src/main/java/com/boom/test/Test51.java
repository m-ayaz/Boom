package com.boom.test;

import com.boom.tools.Chessboard;
import com.boom.tools.Tools;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;


public class Test51 extends Application {

    public static void main(String[] args) {
        launch();
    }

//    boolean x=true;

    @Override
    public void start(Stage stage) throws Exception {

        GridPane container = new GridPane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        container.getChildren().add(new Chessboard(100,5,10,Color.BLACK,Color.WHEAT));







    }}






