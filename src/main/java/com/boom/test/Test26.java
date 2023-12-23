package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.print;


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

        Polygon polygon=new Polygon();
        Polyline polyline=new Polyline();

//        polygon.

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

        //Records Icon
        SVGPath recordsIcon = new SVGPath();
        recordsIcon.setContent("m72.808594 48h-64.960938c-4.332031.003906-7.84374975 3.515625-7.847656 7.847656v384.304688c.00390625 4.332031 3.515625 7.84375 7.847656 7.847656h288.3125c4.332032-.003906 7.84375-3.515625 7.847656-7.847656v-384.304688c-.003906-4.332031-3.515624-7.84375-7.847656-7.847656h-64.953125c-3.828125 18.613281-20.199219 31.976562-39.199219 32h-80c-19-.023438-35.371093-13.386719-39.199218-32zm215.199218 360h-272v-16h272zm0-32h-272v-16h272zm0-32h-272v-16h272zm0-32h-272v-16h272zm0-32h-272v-16h272zm-183.160156-175.960938c3.742188.394532 6.699219 3.34375 7.113282 7.082032l5.382812 48.4375 11.808594-19.679688c1.640625-2.726562 4.746094-4.222656 7.902344-3.804687s5.761718 2.664062 6.640624 5.726562l8.90625 31.136719 15.8125-47.464844c1.023438-3.074218 3.796876-5.230468 7.027344-5.460937 3.230469-.226563 6.28125 1.511719 7.726563 4.414062l13.785156 27.574219h91.054687v16h-96c-3.046874.015625-5.839843-1.699219-7.199218-4.425781l-7.359375-14.726563-17.890625 53.679688c-1.136719 3.304687-4.261719 5.511718-7.757813 5.472656-3.507812-.074219-6.558593-2.425781-7.519531-5.800781l-10.9375-38.269531-14.519531 24.191406c-1.769531 2.941406-5.214844 4.425781-8.566407 3.691406-3.351562-.730469-5.863281-3.523438-6.242187-6.933594l-4-35.789062-20.480469 61.4375c-1.050781 3.152344-3.9375 5.324218-7.253906 5.464844h-.273438c-3.199218 0-6.089843-1.90625-7.351562-4.847657l-21.921875-51.144531h-26.726563v-16h32c3.199219 0 6.089844 1.90625 7.351563 4.847656l15.710937 36.65625 25.34375-76c1.195313-3.5625 4.695313-5.832031 8.433594-5.464844zm0 0");

//        recordsIcon.setStyle("-fx-fill: rgba(255,0,0,0.5),rgba(0,0,255,0.5)");

        container.getChildren().addAll(region1,recordsIcon);

//        polygon.get

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

