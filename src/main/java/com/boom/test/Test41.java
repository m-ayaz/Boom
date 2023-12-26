package com.boom.test;

import com.boom.specialfeatures.TreeMindMapNode;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test41 extends Application {

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

//        DoubleProperty firstChildOffset=new SimpleDoubleProperty(100);
//        DoubleProperty lastChildOffset=new SimpleDoubleProperty(260);
//        DoubleProperty childDistance=new SimpleDoubleProperty(200);

//        TreeMindMapNode parent=new TreeMindMapNode(firstChildOffset,lastChildOffset,childDistance);
//        parent.setParentNode(new SimpleDoubleProperty(400), new SimpleDoubleProperty(400),new SimpleDoubleProperty(0),(new SimpleDoubleProperty(0)).add(0));
//        parent.setRadius(100);
//        parent.setFill(new Color(1,0,0,1));

        TreeMindMapNode layer1=new TreeMindMapNode(new SimpleDoubleProperty(45),new SimpleDoubleProperty(315),new SimpleDoubleProperty(300),500,500,90);
        layer1.setFill(Color.rgb(255,0,0,1));
        layer1.setRadius(130);
//        layer1.assignToParentNode(new SimpleDoubleProperty(600),new SimpleDoubleProperty(400),new SimpleDoubleProperty(0),(new SimpleDoubleProperty(90)).add(0));

        List<TreeMindMapNode> layer2s=new ArrayList<>();
        int n2=4;
        int[] p=new int[]{7,6,4,10};
        for(int i=0;i<n2;i++){
            layer2s.add(new TreeMindMapNode(new SimpleDoubleProperty(360./(p[i]+1)),new SimpleDoubleProperty(360.-360./(p[i]+1)),new SimpleDoubleProperty(170)));
            layer2s.get(i).setFill(Color.rgb(0,255,0,1));
            layer2s.get(i).setRadius(70);
            layer1.addChild(layer2s.get(i));
        }

        print(layer2s.get(0).angleWithParent);
        print(layer2s.get(1).angleWithParent);
        print(layer2s.get(2).angleWithParent);
        print(layer2s.get(3).angleWithParent);

        List<TreeMindMapNode> layer3s=new ArrayList<>();
//        TreeMindMapNode temp;

//        int[] p=new int[]{2,0,0,0};
        for(int i=0;i<p.length;i++) {
            for (int j = 0; j < p[i]; j++) {
                TreeMindMapNode temp=new TreeMindMapNode(new SimpleDoubleProperty(90), new SimpleDoubleProperty(180), new SimpleDoubleProperty(200));
                layer3s.add(temp);
                temp.setFill(Color.rgb(0, 0, 255, 1));
                temp.setRadius(30);
                layer2s.get(i).addChild(temp);
//                layer2s.get()
//                layer2s
//                layer3s.get(i).assignToParentNode(layer1.centerXProperty(), layer1.centerYProperty(), new SimpleDoubleProperty(200), (new SimpleDoubleProperty(-45)).add(1.0 * i / n2 * 360));
            }
        }

//        print(MouseInfo.getPointerInfo().getDevice().getDisplayModes());
//
//        for(DisplayMode d:MouseInfo.getPointerInfo().getDevice().getDisplayModes()){
//            print(d);
//        }

//        MouseInfo.getPointerInfo().getDevice().setDisplayMode(MouseInfo.getPointerInfo().getDevice().getDisplayModes()[7]);

//        print(MouseInfo.getPointerInfo().getDevice().getType());

//        KeyboardFocusManager.getCurrentKeyboardFocusManager();
//        HttpCookie httpCookie=new HttpCookie("","");
//        httpCookie.getDiscard().
//        MouseInfo.getPointerInfo().getLocation().move(50,50);

//        setDisplayMode

        container.getChildren().add(layer1);
        container.getChildren().addAll(layer2s);
        container.getChildren().addAll(layer3s);

        container.getChildren().addAll(layer1.getConnections());
        container.getChildren().addAll(layer2s.get(0).getConnections());
        container.getChildren().addAll(layer2s.get(1).getConnections());
        container.getChildren().addAll(layer2s.get(2).getConnections());
        container.getChildren().addAll(layer2s.get(3).getConnections());

//        Pa/


//        container.getChildren().addAll(parent);
//
//int n=4;
//
////        DoubleProperty dist=new SimpleDoubleProperty(50);
////        DoubleProperty ang=new SimpleDoubleProperty(45);
//
//        List<TreeMindMapNode> children=new ArrayList<>();
//        for(int i=0;i<n;i++) {
//            children.add(new TreeMindMapNode(firstChildOffset, lastChildOffset, childDistance));
////            children.get(i).
//        }
//
//
//
//        children.forEach(child->{
//            child.setRadius(50);
//            child.setFill(new Color(0,0,1,1));
//            parent.addChild(child);
//        });
////        child.setParentNode(parent.centerXProperty(),parent.centerYProperty(),dist,ang);
////        child.setRadius(70);
////        child.setFill(new Color(0,0,1,0.3));
//
//        container.getChildren().addAll(children);
//        container.getChildren().addAll(parent.getConnections());
////
////        children.forEach(child->print(child.getCenterX()+" , "+child.getCenterY()));
//
////        child.setOnMouseDragged(mouseEvent -> {
////            child.parentConnectionDistance.set(Math.sqrt(Math.pow(child.parentCenterX.get()-mouseEvent.getX(),2)+Math.pow(child.parentCenterY.get()-mouseEvent.getY(),2)));
////            child.parentConnectionAngle.set(Math.atan2(child.parentCenterY.get()-mouseEvent.getY(),child.parentCenterX.get()-mouseEvent.getX()));
////        });


    }



}
