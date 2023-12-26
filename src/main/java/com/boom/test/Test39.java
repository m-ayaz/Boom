package com.boom.test;

import com.boom.specialfeatures.TreeMindMapNode;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.print;


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

        DoubleProperty firstChildOffset=new SimpleDoubleProperty(0);
        DoubleProperty lastChildOffset=new SimpleDoubleProperty(300);
        DoubleProperty childDistance=new SimpleDoubleProperty(300);

        TreeMindMapNode parent=new TreeMindMapNode(firstChildOffset,lastChildOffset,childDistance,400,400,0);
//        parent.assignToParentNode(new SimpleDoubleProperty(400), new SimpleDoubleProperty(400),new SimpleDoubleProperty(0),(new SimpleDoubleProperty(0)).add(0));
        parent.setRadius(100);
        parent.setFill(new Color(1,0,0,1));
//        parent.setCenterX(400);
//        parent.setCenterY(400);



        container.getChildren().addAll(parent);




        int n=3;

//        DoubleProperty dist=new SimpleDoubleProperty(50);
//        DoubleProperty ang=new SimpleDoubleProperty(45);

        List<TreeMindMapNode> children=new ArrayList<>();
        for(int i=0;i<n;i++) {
            children.add(new TreeMindMapNode(firstChildOffset, lastChildOffset, childDistance));
//            children.get(i).
        }



        children.forEach(child->{
            child.setRadius(50);
            child.setFill(new Color(0,0,1,0.3));
            parent.addChild(child);
//            child.centerXProperty().addListener((a,b,c)->{
//                print("child %d = %f , %f".formatted(children.indexOf(child),child.getCenterX(),child.getCenterY()));
//            });
//            child.centerYProperty().addListener((a,b,c)->{
//                print("child %d = %f , %f".formatted(children.indexOf(child),child.getCenterX(),child.getCenterY()));
//            });
        });
//        child.setParentNode(parent.centerXProperty(),parent.centerYProperty(),dist,ang);
//        child.setRadius(70);
//        child.setFill(new Color(0,0,1,0.3));

        container.getChildren().addAll(children);
        container.getChildren().addAll(parent.getConnections());

        parent.getChildren().forEach(child->print(child.angleWithParent));



//        parent.getChildren().forEach(child-> {
//            print(""+child.getCenterX() + " , " + child.getCenterY())
//        });

//        parent.getChildren().forEach(child-> {
////            child.add
//        });


//
//        children.forEach(child->print(child.getCenterX()+" , "+child.getCenterY()));

//        child.setOnMouseDragged(mouseEvent -> {
//            child.parentConnectionDistance.set(Math.sqrt(Math.pow(child.parentCenterX.get()-mouseEvent.getX(),2)+Math.pow(child.parentCenterY.get()-mouseEvent.getY(),2)));
//            child.parentConnectionAngle.set(Math.atan2(child.parentCenterY.get()-mouseEvent.getY(),child.parentCenterX.get()-mouseEvent.getX()));
//        });


    }



}
