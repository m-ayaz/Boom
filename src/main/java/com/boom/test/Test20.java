package com.boom.test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import static com.boom.tools.Tools.setCustomSize;


public class Test20 extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        // Initialize and show the main canvas.
        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();




//        Ellipse ellipse=new Ellipse();

//        Polygon polygon=new Polygon();
//        polygon.getPoints().addAll(1.0,0.0,0.0,Math.sqrt(3),-1.0,0.0);



        Region r=new Region();

//        Ellipse e=;
        r.setShape(new Ellipse(1,1));

//        e.setStrokeWidth(5);

        r.getTransforms().add(new Rotate(0,0,0));

        r.setBackground(Background.EMPTY);

        r.getBackground().getFills().add(new BackgroundFill(new Color(1,0,0,0.5),CornerRadii.EMPTY,new Insets(0)));
        r.getBackground().getFills().add(new BackgroundFill(new Color(0,0,1,0.5),CornerRadii.EMPTY,new Insets(0)));
//        r.getBackground().getFills().add(new BackgroundFill());

//        r.setRotate(60);

//        r.setShape(ellipse);

//        Color b;
//        b.

//        r.setBackground(Background.fill(Color.RED));
//        r.setBorder(Border.stroke(Color.BLUE));
////        r.getBorder().
//        r.setStyle(
////                "-fx-background-insets: 5px;" +
//                "-fx-background-color: red;  " +
////                "-fx-background-radius: 6px;\n" +
//                "-fx-border-color: blue;"+
////                "-fx-border-insets: 0px;"+
//                "-fx-border-width: 50px;"
////                "  -fx-background-image: url('http://icons.iconarchive.com/icons/appicns/simplified-app/64/appicns-Chrome-icon.png');\n" +
////                "  -fx-background-repeat: no-repeat;\n" +
////                "  -fx-background-position: center;"
//        );
//        r.set
//        r.setStyle("   -fx-background-color: red; \n" +
//                "  -fx-background-insets: 0 0 -1 0, 0, 1, 2;\n" +
//                "  -fx-background-radius: 30px, 30px, 20px, 10px;");


        container.getChildren().add(r);

        setCustomSize(r,400,200);
//        container.getChildren().add(ellipse);
//
//        r.setTranslateX(200);
//        r.setTranslateY(200);








    }
}