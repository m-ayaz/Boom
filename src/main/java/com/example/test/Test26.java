package com.example.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Random;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.setCustomSize;


public class Test26 extends Application {

    public static void setSize(Region region, double width, double height) {
        region.setMaxSize(width, height);
        region.setPrefSize(width, height);
        region.setMinSize(width, height);
    }

    public static void main(String[] args) {
        launch();
    }

    boolean x = false;

    Translate translate = new Translate();

    @Override
    public void start(Stage stage) {

        Affine affine = new Affine();
        Random rnd = new Random();

        // Initialize and show the main canvas.
        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        Ellipse shape = new Ellipse(0, 0, 200, 100);
        Region region = new Region();
        shape.setFill(Color.TRANSPARENT);
        shape.setStroke(Color.BLACK);
        shape.getTransforms().add(affine);
        region.getTransforms().add(affine);
        shape.setStrokeWidth(1);
        shape.setStrokeType(StrokeType.OUTSIDE);
        region.setMouseTransparent(true);
        region.setBackground(Background.fill(Color.valueOf("ff000011")));

//        Arc arc = new Arc();
//
//        container.getChildren().add(arc);

        container.setOnMouseReleased(mouseEvent -> {
            print(mouseEvent.isPrimaryButtonDown() + " , " + mouseEvent.isSecondaryButtonDown());
        });

//        Line line=new Line(50,50,150,150);

//        container.getChildren().add(line);

//        line.setStrokeWidth(50);

//        line.setStyle("-fx-stroke: rgba(255,0,0,0.5),rgba(0,0,255,0.5);");



//        line.setStyle("-fx-stroke: %s,%s;".formatted(
//                (new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,new Stop(0,Color.TRANSPARENT),new Stop(1,Color.rgb(255,0,0,0.5))).toString().replace("0x","#")),
//                (new LinearGradient(0,1,1,0,true, CycleMethod.NO_CYCLE,new Stop(0,Color.TRANSPARENT),new Stop(1,Color.rgb(0,0,255,0.5))).toString().replace("0x","#"))
//        ));


//arc.setStartAngle(0);
//arc


//        region.getTransforms().add(translate);
//
//        translate.setX(shape.getBoundsInParent().getMinX());
//        translate.setY(shape.getBoundsInParent().getMinY());
//
////        shape.boundsInParentProperty().addListener((a,b,c)->{
////            print(c);
////        });
//
//        container.getChildren().addAll(shape,region);

        Region region1=new Region();

        container.getChildren().add(region1);

//        setCustomSize(region1,200,200);
//        region1.setStyle("-fx-border-color: rgba(255,0,0,0.2),rgba(0,0,255,0.2);-fx-border-width: 20;");
//
//        affine.prependTranslation(250,250);
//
//        setCustomSize(region,shape.getBoundsInLocal().getWidth(),shape.getBoundsInLocal().getHeight());
//
////        affine.prependRotation(rnd.nextDouble()*20+10);
//
//        shape.setOnMouseMoved(mouseEvent -> {
//
//            shape.setRadiusX(70+50*rnd.nextDouble());
//            shape.setRadiusY(50+30*rnd.nextDouble());
//
//        });
//
//        shape.boundsInLocalProperty().addListener((a,b,c)->{
//            setCustomSize(region,c.getWidth(),c.getHeight());
////            translate.setX(shape.getBoundsInParent().getMinX());
////            translate.setY(shape.getBoundsInParent().getMinY());
//        });

//        Polyline polyline=new Polyline();
//        LabeledText labeledText=new LabeledText();
//        Text
//        LabeledText
//        QuadCurve;
//        CubicCurve;

//        region.setStyle("-fx-");


//        Random rnd=new Random();
//
////        Rotate rotate=new Rotate();
////        affine.append(rotate);
//
//        container.getChildren().addAll(r,region);
//        r.setOnMouseMoved(mouseEvent -> {
////            r.setRadiusX(100);
////            r.setRadiusY(50);
////            r.setRadiusX(50.+rnd.nextDouble()*50);
////            r.setRadiusY(50+rnd.nextDouble()*50);
//            affine.prepend(new Rotate(rnd.nextDouble()*1));
////            rotate.setAngle(rnd.nextDouble()*30);
////            print(r.getBoundsInLocal());
////            print(region.getPrefWidth());
////            print(region.getWidth());
//        });
//
//
//
//
////        Rectangle rectangle=new Rectangle();
////        container.getChildren().add(rectangle);
////        rectangle.setMouseTransparent(true);
////        rectangle.setFill(new Color(0,0,0,0.1));
//
//
//
////        r.setTranslateX(100);
////        r.setTranslateY(100);
//
//
//        region.setShape(r);
//        region.setStyle("-fx-background-color: %s,%s".formatted(
//                (new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,new Stop(0,Color.TRANSPARENT),new Stop(1,Color.valueOf("0000ff88")))).toString().replace("0x","#"),
//                (new LinearGradient(0,1,1,0,true, CycleMethod.NO_CYCLE,new Stop(0,Color.TRANSPARENT),new Stop(1,Color.valueOf("ff000088")))).toString().replace("0x","#")));
//
//
//        Translate translate=new Translate();
//        region.getTransforms().add(translate);
//
//        r.boundsInParentProperty().addListener((a,b,c)->{
////            print(uuid(50));
//            print(c);
////            rectangle.setX(c.getMinX());
////            rectangle.setY(c.getMinY());
////            rectangle.setWidth(c.getWidth());
////            rectangle.setHeight(c.getHeight());
////            region.setTranslateX(c.getMinX());
////            region.setTranslateY(c.getMinY());
////            if(translate.getX()==0) {
////                print(uuid(50));
////                print(c.getMinX());
//            if(!x) {
//                translate.setX(c.getMinX());
//                translate.setY(c.getMinY());
//                x=true;
//            }
////print(c.getMinY());
////            translate.setX(-50);
////            translate.setY(-50);
////            }
//        });
//
//        r.setOnMouseDragged(mouseEvent -> affine.prependTranslation(1,1));
//
////        translate.setX(-r.getBoundsInParent().getCenterX());
////        translate.setY(-r.getBoundsInParent().getCenterY());
////        region.
//        setCustomSize(region,r.getBoundsInLocal().getWidth(),r.getBoundsInLocal().getHeight());
////        region.setTranslateX(r.getBoundsInParent().getCenterX()-100);
////        region.setTranslateY(r.getBoundsInParent().getCenterY()-100);
//        r.boundsInLocalProperty().addListener((a,b,c)->{
//            print(uuid(20));
//            setCustomSize(region,c.getWidth(),c.getHeight());
//            x=false;
//        });
//
//
//        region.setMouseTransparent(true);


//        affine.append(new Translate(100,100));


//        rectangle.set

//        region.


//        Arc arc=new Arc();
//
//        container.getChildren().add(arc);
//
//        arc.setCenterX(50);
//        arc.setCenterY(50);
//        arc.setRadiusX(50);
//        arc.setRadiusY(50);
//        arc.setLength(200);
//        arc.setStartAngle(0);
//
//        arc.setFill(Color.GREENYELLOW);
//
//        arc.setStroke(Color.BLACK);
//
//        arc.setStrokeWidth(4);
//
//        arc.setType(ArcType.ROUND);
//
//        CubicCurve cubicCurve=new CubicCurve();
//        container.getChildren().add(cubicCurve);
//
//        cubicCurve.setFill(Color.AZURE);


    }
}

