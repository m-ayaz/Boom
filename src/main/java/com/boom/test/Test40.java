package com.boom.test;

import com.boom.specialfeatures.TreeMindMapNode;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test40 extends Application {

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

        GridPane container = new GridPane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        container.addRow(0,new Rectangle(50,50,Color.RED));

        Box box=new Box();
        box.setWidth(200);
        box.setHeight(200);
        box.setDepth(200);

        PhongMaterial phongMaterial=new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.rgb(255,0,0,0.2));
        phongMaterial.setSpecularColor(Color.rgb(0,0,255,0.2));
        box.setMaterial(phongMaterial);

//        PhongMaterial material=new PhongMaterial();

//        material.

//        box.setMaterial(Material);

        container.addRow(1,new Rectangle(),box);

        PerspectiveCamera camera=new PerspectiveCamera();
//        camera.set.setFieldOfView(20);

        scene.setCamera(camera);

        box.setTranslateX(200);
        box.setTranslateY(200);

        Slider slider=new Slider();

        container.addRow(2,new Rectangle(),slider);

        slider.setMin(0);
        slider.setMax(360);

//        box.setRotationAxis(new Point3D(150,150,0));
        Rotate rotate=new Rotate();
        box.getTransforms().add(rotate);

        rotate.setPivotZ(200);

        slider.setOnMouseDragged(mouseEvent -> {
            rotate.setAngle(slider.getValue());

//            new Rotate();
//            print(slider.getValue());
//            camera.setTranslateY(slider.getValue());
//            box.setRotate(slider.getValue());
        });

//        camera.setTranslateX(slider.getValue());






//        TreeMindMapNode parent=new TreeMindMapNode();
//        parent.setParentNode(new SimpleDoubleProperty(200), new SimpleDoubleProperty(200),new SimpleDoubleProperty(0),new SimpleDoubleProperty(0));
//
//        container.getChildren().addAll(parent);
//
//        parent.setRadius(100);
//        parent.setFill(new Color(1,0,0,0.3));
//
//        DoubleProperty dist=new SimpleDoubleProperty(100);
//        DoubleProperty ang=new SimpleDoubleProperty(45);
//
//        TreeMindMapNode child=new TreeMindMapNode();
//        child.setParentNode(parent.centerXProperty(),parent.centerYProperty(),dist,ang);
//        child.setRadius(70);
//        child.setFill(new Color(0,0,1,0.3));
//
//        container.getChildren().addAll(child);
//
//        child.setOnMouseDragged(mouseEvent -> {
//            child.parentConnectionDistance.set(Math.sqrt(Math.pow(child.parentCenterX.get()-mouseEvent.getX(),2)+Math.pow(child.parentCenterY.get()-mouseEvent.getY(),2)));
//            child.parentConnectionAngle.set(Math.atan2(child.parentCenterY.get()-mouseEvent.getY(),child.parentCenterX.get()-mouseEvent.getX()));
//        });


    }



}
