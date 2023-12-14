package com.example.test;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.tools.Tools.setSize;
import static com.example.tools.Tools.uuid;
import static java.lang.Math.floor;

//import javax.imageio.ImageIO;

public class Test12 extends Application {
    @Override
    public void start(Stage stage) {


        List<Circle> rectangleList=new ArrayList<>();

        int n=7;
        for(int i=0;i<n;i++){
            Circle r=new Circle(200);
            rectangleList.add(r);
            r.setTranslateX(200);
            r.setTranslateY(200);

            Random rnd=new Random();

            Stop[] stops=new Stop[]{new Stop(0,Color.TRANSPARENT),new Stop(1,new Color(floor(((double) i % 8) / 4), floor(((double) i % 4) / 2), i % 2, 0.5))};


//
//            for(int i=0;i<n;i++) {
//                stops[i] = new Stop((double) i/(n-1), new Color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble(), 1));
//            }

            LinearGradient gradient=new LinearGradient(0.5,0.5,0.5+0.5*Math.cos(2*Math.PI*i/n),0.5+0.5*Math.sin(2*Math.PI*i/n),true, CycleMethod.NO_CYCLE,stops);

            r.setFill(gradient);
        }




//        int n=20;

//        Stop[] stops=new Stop[n];
//
//        Random rnd=new Random();
//
//        for(int i=0;i<n;i++) {
//            stops[i] = new Stop((double) i/(n-1), new Color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble(), 1));
//        }
//        stops[1]=new Stop(0.5,new Color(0,0,1,1));
//        stops[2]=new Stop(1,new Color(0,1,0,1));

//        Path p=new Path();

//        List<Stop> x;
//        x.toArray();

//        RadialGradient gradient1=new RadialGradient();

//        LinearGradient gradient=new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,stops);

//        rectangleList.setFill(gradient);




        Pane pane = new Pane();

        setSize(pane,600,400);

        pane.getChildren().addAll(rectangleList);

        Scene scene = new Scene(pane);

        stage.setScene(scene);

        stage.show();

//        SnapshotParameters snapshotParameters=new SnapshotParameters();
//        snapshotParameters.



//        WritableImage x=pane.snapshot(new SnapshotParameters(),null);

//        x.

//        File file = new File("C:\\Users\\mostafa\\Desktop\\%s.jpg".formatted(uuid(10)));
//
//        try {
//            ImageIO.write(SwingFXUtils.fromFXImage(x, null), "jpg", file);
//        }catch (IOException e) {
//            // TODO: handle exception here
//        }



//        p(5);


    }
    public static void main(String[] args) {
        launch();
    }

//    void p(@NamedArg("asklask") double x){
//
//    }



}

