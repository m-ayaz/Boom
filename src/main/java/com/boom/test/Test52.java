package com.boom.test;

import com.boom.tools.Chessboard;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.awt.*;

import static com.boom.tools.Tools.print;


public class Test52 extends Application {

    public static void main(String[] args) {
        launch();
    }

//    boolean x=true;

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        Ellipse ellipse=new Ellipse(300,300,200,100);
        ellipse.setFill(Color.valueOf("00000088"));

        container.getChildren().add(ellipse);

        container.setOnMouseMoved(mouseEvent -> {
//            double x=mouseEvent.getX();
//            double y=mouseEvent.getY();
            double x=mouseEvent.getSceneX();
            double y=mouseEvent.getSceneY();
//            double x=mouseEvent.getScreenX();
//            double y=mouseEvent.getScreenY();
            print("___________________________________");
            print(ellipse.contains(ellipse.localToParent(x,y)));
            print(ellipse.contains(ellipse.localToScene(x,y)));
            print(ellipse.contains(ellipse.localToScreen(x,y)));
            print(ellipse.contains(ellipse.parentToLocal(x,y)));
            print(ellipse.contains(ellipse.sceneToLocal(x,y)));
            print(ellipse.contains(ellipse.screenToLocal(x,y)));
        });

        Affine affine=new Affine();
        affine.append(new Rotate(45,300,300));
        affine.append(new Scale(1.5,1.3));

        ellipse.getTransforms().add(affine);

        print(ellipse.getLocalToParentTransform());
        print(ellipse.getLocalToSceneTransform());
        print(affine);

//        container.getChildren().add(new Chessboard(100,5,10,Color.BLACK,Color.WHEAT));


    }

}





