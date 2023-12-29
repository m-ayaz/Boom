package com.boom.test;

import com.boom.tools.Tools;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.*;


public class Test50 extends Application {

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


        Text text=new Text();
        Label label=new Label();
        Button button=new Button("بزن روش");

        container.addRow(0,text,label,button);

//        Font font=new Font();

        Font.getFamilies().forEach(Tools::print);

        Font font1=Font.font("XB Niloofar",50);


        button.setFont(font1);

//        label.setText("lasdklasd");

//        lab






    }





}
