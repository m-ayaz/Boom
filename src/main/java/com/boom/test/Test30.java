package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.GregorianCalendar;
import java.util.Random;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;


public class Test30 extends Application {

//    public static void setSize(Region region, double width, double height) {
//        region.setMaxSize(width, height);
//        region.setPrefSize(width, height);
//        region.setMinSize(width, height);
//    }

    boolean x=false;

    public static void main(String[] args) {
        launch();
    }

//    boolean x = false;

//    Translate translate = new Translate();

    @Override
    public void start(Stage stage) {

        Affine affine = new Affine();
        Random rnd = new Random();


        // Initialize and show the main canvas.
        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


        Arc rectangle=new Arc();
        rectangle.setStartAngle(135);
        rectangle.setLength(90);
        rectangle.setRadiusX(100);
        rectangle.setRadiusY(100);

        rectangle.setType(ArcType.ROUND);


        Translate translate=new Translate();

        Region region=new Region();

        rectangle.boundsInLocalProperty().addListener((a,b,c)->{
            setCustomSize(region,c.getWidth(),c.getHeight());
            translate.setX(c.getMinX());
            translate.setY(c.getMinY());
        });

//        rectangle.setFill(Color.TRANSPARENT);
//        rectangle.setStrokeWidth(0);
//        rectangle.setStroke(Color.TRANSPARENT);


        region.setMouseTransparent(true);

        region.getTransforms().add(affine);
        rectangle.getTransforms().add(affine);

        rectangle.setFill(Color.valueOf("ff000088"));
//        region.setBackground(Background.fill(Color.valueOf("0000ff88")));
        region.setStyle("-fx-background-color: rgba(255,0,0,0.3);-fx-border-color: rgba(0,0,255,0.5);-fx-border-width: 10px;");

        container.getChildren().addAll(rectangle,region);

        region.setShape(rectangle);

//        setCustomSize(region,rectangle.getBoundsInLocal().getWidth(),rectangle.getBoundsInLocal().getHeight());










        region.getTransforms().add(translate);


//        rectangle.boundsInParentProperty().addListener((a,b,c)->{
//            print("parent = "+c);
////            print(rectangle.getBoundsInLocal());
////            if(!x) {
////                translate.setX(rectangle.getBoundsInLocal().getMinX());
////                translate.setY(rectangle.getBoundsInLocal().getMinY());
////                x=true;
////            }
//        });

        affine.setToTransform(new Translate(400,400));


//        rectangle.bou





//        rectangle.boundsInLocalProperty().addListener();






        rectangle.setOnMouseMoved(mouseEvent -> {
            rectangle.setRadiusX(200.+100.*rnd.nextDouble());
            rectangle.setRadiusY(100.+100.*rnd.nextDouble());
//            rectangle.setStartAngle(rnd.nextDouble()*90);
//            x=false;
            affine.prepend(new Rotate(rnd.nextDouble()*1));
//            Pane pane;
//            pane.
//            affine.prepend(new Scale(0.8+rnd.nextDouble()*1.2,0.8+rnd.nextDouble()*1.2));
        });







//        Rectangle rectangle=new Rectangle();
//
//        Arc arc=new Arc();
//        Arc arc1=new Arc();
//        Arc arc2=new Arc();
//        arc.setStartAngle(135);
//        arc.setRadiusX(1);
//        arc.setRadiusY(1);
//        arc.setCenterX(300);
//        arc.setCenterY(300);
//        arc.setLength(90);
//        arc.setFill(Color.TRANSPARENT);
//        arc.setType(ArcType.ROUND);
//
//        arc1.setStartAngle(135);
//        arc1.setRadiusX(1);
//        arc1.setRadiusY(1);
//        arc1.setCenterX(300);
//        arc1.setCenterY(300);
//        arc1.setLength(90);
//        arc1.setFill(Color.TRANSPARENT);
//        arc1.setType(ArcType.ROUND);
//
//        arc2.setStartAngle(135);
//        arc2.setRadiusX(1);
//        arc2.setRadiusY(1);
//        arc2.setCenterX(300);
//        arc2.setCenterY(300);
//        arc2.setLength(90);
//        arc2.setFill(Color.TRANSPARENT);
//        arc2.setType(ArcType.ROUND);
//
////        Rectangle rectangle=new Rectangle(1,1);
//
//
////        arc.se
//
//        Region region=new Region();
//
//
//
//        region.setShape(arc);
//
//        region.setBackground(Background.fill(Color.rgb(255,0,0,0.2)));
//
////        rectangle.setX(arc.getBoundsInParent().getMinX());
////        rectangle.setY(arc.getBoundsInParent().getMinY());
////        rectangle.setWidth(arc.getBoundsInParent().getWidth());
////        rectangle.setHeight(arc.getBoundsInParent().getHeight());
//
//        region.setTranslateX(100);
//        region.setTranslateY(100);
//
//        setCustomSize(region,200,200);
//
//
//
//        container.getChildren().addAll(arc,arc1,arc2,region,rectangle);
//
//        rectangle.setFill(Color.TRANSPARENT);
//        rectangle.setStroke(Color.BLACK);





    }
}

