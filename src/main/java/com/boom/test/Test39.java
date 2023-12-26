package com.boom.test;

import com.boom.specialfeatures.MindMapConnection;
import com.boom.specialfeatures.TreeMindMapNode;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Test39 extends Application {

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

    @Override
    public void start(Stage stage) {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        DoubleProperty firstChildOffset=new SimpleDoubleProperty(90);
        DoubleProperty lastChildOffset=new SimpleDoubleProperty(270);
        DoubleProperty childDistance=new SimpleDoubleProperty(50);

        TreeMindMapNode parent=new TreeMindMapNode(firstChildOffset,lastChildOffset,childDistance);
        parent.setParentNode(new SimpleDoubleProperty(200), new SimpleDoubleProperty(200),new SimpleDoubleProperty(0),new SimpleDoubleProperty(0));
        parent.setRadius(100);
        parent.setFill(new Color(1,0,0,0.3));



        container.getChildren().addAll(parent);

int n=5;

        DoubleProperty dist=new SimpleDoubleProperty(50);
        DoubleProperty ang=new SimpleDoubleProperty(45);

        List<TreeMindMapNode> children=new ArrayList<>();
        for(int i=0;i<n;i++) {
            children.add(new TreeMindMapNode(firstChildOffset, lastChildOffset, childDistance));
//            children.get(i).
        }
        child.setParentNode(parent.centerXProperty(),parent.centerYProperty(),dist,ang);
        child.setRadius(70);
        child.setFill(new Color(0,0,1,0.3));

        container.getChildren().addAll(child);

        child.setOnMouseDragged(mouseEvent -> {
            child.parentConnectionDistance.set(Math.sqrt(Math.pow(child.parentCenterX.get()-mouseEvent.getX(),2)+Math.pow(child.parentCenterY.get()-mouseEvent.getY(),2)));
            child.parentConnectionAngle.set(Math.atan2(child.parentCenterY.get()-mouseEvent.getY(),child.parentCenterX.get()-mouseEvent.getX()));
        });


    }



}
