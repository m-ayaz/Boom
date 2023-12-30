package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test42 extends Application {

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
Random rnd=new Random();

    int r=0;

    double angle;

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


        Circle circle1=new Circle(500,350,20);
        Circle circle2=new Circle(300,350,20);
        Line line=new Line();

        double length=250;

        line.startXProperty().bindBidirectional(circle1.centerXProperty());
        line.startYProperty().bindBidirectional(circle1.centerYProperty());

        line.endXProperty().bindBidirectional(circle2.centerXProperty());
        line.endYProperty().bindBidirectional(circle2.centerYProperty());

        container.setOnMouseMoved(mouseEvent -> {
            angle=Math.atan2(mouseEvent.getY()-circle1.getCenterY(), mouseEvent.getX()-circle1.getCenterX());
//            print(angle+2*r*Math.PI);
//            if(angle<0)
            circle2.setCenterX(circle1.getCenterX()+length*Math.cos(Math.exp(1./(Math.PI-angle))));
            circle2.setCenterY(circle1.getCenterY()+length*Math.sin(Math.exp(1./(Math.PI-angle))));
        });



        container.getChildren().addAll(circle1,circle2,line);

//        JSONObject jsonObject=new JSONObject();
//
//        List<Double> doubles= Arrays.asList(1.0,1.1,1.6,1.6,4.0,3.0);

//        jsonObject.put("alsaskalsk",doubles);
//
//        FileChooser fileChooser = new FileChooser();
//
//
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
//
//        File saveFile = fileChooser.showSaveDialog(null);
//
//        PrintWriter printWriter = new PrintWriter(saveFile);
//
//            printWriter.println(jsonObject);






    }



}
