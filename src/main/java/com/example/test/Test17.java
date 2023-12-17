package com.example.test;

import com.example.icons.SettingsIcon;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.example.tools.Tools.setCustomWidth;
import static com.example.tools.Tools.uuid;


public class Test17 extends Application {

    public static void setSize(Region region, double width, double height) {
        region.setMaxSize(width, height);
        region.setPrefSize(width, height);
        region.setMinSize(width, height);
    }

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

        SettingsIcon h=new SettingsIcon(8,100,80,50,40,1,Color.BLACK);

        container.getChildren().add(
                h
                );

        h.setTranslateX(200);
        h.setTranslateY(200);


//        int n = 8;
//
//        Pane cog=new StackPane();
//
////        Shape.union()
//
//        double width = 40;
//        double height = 30;
//        double offset = 95;
//        double radius = 80;
//        double thickness=10;
//
//        double centerX=200;
//        double centerY=200;
//
//        double L=70;
//
////        container.getChildren().add(new Line(50,50,200,300));
//
//        Shape cog1;
//
//        Circle circle=new Circle(radius);
//        circle.setFill(Color.TRANSPARENT);
//        circle.setStroke(Color.BLACK);
//        circle.setStrokeType(StrokeType.CENTERED);
//        circle.setStrokeWidth(thickness);
//
//        cog1=circle;
//
//        List<Line> lines = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            lines.add(new Line(offset,0,offset+L,0));
//        }
//
//
//        for (int i = 0; i < n; i++) {
//            lines.get(i).setStroke(Color.BLACK);
//            lines.get(i).setStrokeWidth(thickness);
//            lines.get(i).getTransforms().add(new Rotate(360.0/n*i,0,0));
//            lines.get(i).setStrokeLineCap(StrokeLineCap.ROUND);
////            cog1=Shape.union(cog1,lines.get(i));
//        }
//
//        cog1=Shape.union(cog1,lines.get(0));
//
////        lines.forEach(line -> {
////            int i=lines.indexOf(line);
////
////        });
//
//
//
//
//
////        Shape p=Shape.union(circle,lines.get(0));
//
////        p.s
////        Circle circle1=new Circle(centerX,centerY,radius1);
////        circle1.setFill(Color.WHITE);
//
//
////        cog.getChildren().addAll(lines);
////        cog.getChildren().addAll(circle);
//
//        container.getChildren().addAll(cog1);
//
//        cog1.setTranslateX(250);
//        cog1.setTranslateY(250);

//        cog.setBackground(Background.fill(Color.RED));


//        Rectangle r1=new Rectangle(0,0,200,200);
//        Rectangle r2=new Rectangle(100,100,200,200);
//
//        Union

//        Label label=new Label();
//        Rectangle r=new Rectangle(150,150);
//
//        container.getChildren().addAll(r,label);
//
//        label.setText(uuid(50));
//        label.setWrapText(true);
//
//        setCustomWidth(label,150);


    }
}