package com.boom.icons;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public class ProposedAppIcon extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane=new Pane();
        Scene scene=new Scene(pane,200,200);
        stage.setScene(scene);
        showIcon(pane);
        stage.setResizable(false);
        stage.show();




    }

    public static void main(String[] args){
        launch();
    }

    void showIcon(Pane pane){
        List<Circle> circles = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            circles.add(new Circle(50, 0, 50));
            circles.get(i).getTransforms().add(new Rotate((double) 360 * i / 7, 0, 0));
            circles.get(i).setStrokeWidth(0);
            circles.get(i).setFill(new Color(floor(((double) i % 8) / 4), floor(((double) i % 4) / 2), i % 2, 0.5));
        }
        circles.forEach(circle -> circle.setTranslateX(100));
        circles.forEach(circle -> circle.setTranslateY(100));
        pane.getChildren().addAll(circles);
    }

}
