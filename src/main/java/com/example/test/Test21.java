package com.example.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.example.tools.Tools.print;


public class Test21 extends Application {

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


        Rectangle rectangle=new Rectangle(0,0,800,400);

        container.getChildren().add(rectangle);


//        double focusAngle=0;
//        double focusDistance=10;
//        double centerX=200;
//        double centerY=200;
//        double radius=100;


        RadialGradient gradient1,gradient2;




        container.setOnMouseMoved(mouseEvent -> {

            double focusAngle;
            double focusDistance;
            double centerX=200;
            double centerY=200;
            double radius=100;

            double x=mouseEvent.getSceneX();
            double y=mouseEvent.getSceneY();

//            double x=mouseEvent.getSceneX()/rectangle.getBoundsInParent().getWidth();
//            double y=mouseEvent.getSceneY()/rectangle.getBoundsInParent().getHeight();

            double r=Math.sqrt((x-centerX)*(x-centerX)+(y-centerY)*(y-centerY));
            double th=Math.atan2(y-centerY,x-centerX)*180/Math.PI;

            focusAngle=th;
            focusDistance=0.5;

            print(r);


//            print("=======================");
//            print(r);
//            print(th);

//            rectangle

            rectangle.setFill(new RadialGradient(focusAngle,focusDistance,centerX,centerY,radius,false,
                    CycleMethod.NO_CYCLE,new Stop(0,Color.BLUE),new Stop(0.5,Color.GREEN),new Stop(1,Color.RED)));

//            print(mouseEvent.getSceneX());

        });

//        SettingsIcon h=new SettingsIcon(8,100,80,50,40,1,Color.BLACK);
//
//        container.getChildren().add(
//                h
//                );
//
//        h.setTranslateX(200);
//        h.setTranslateY(200);
//
//
////        int n = 8;
////
////        Pane cog=new StackPane();
////
//////        Shape.union()
////
////        double width = 40;
////        double height = 30;
////        double offset = 95;
////        double radius = 80;
////        double thickness=10;
////
////        double centerX=200;
////        double centerY=200;
////
////        double L=70;
////
//////        container.getChildren().add(new Line(50,50,200,300));
////
////        Shape cog1;
////
////        Circle circle=new Circle(radius);
////        circle.setFill(Color.TRANSPARENT);
////        circle.setStroke(Color.BLACK);
////        circle.setStrokeType(StrokeType.CENTERED);
////        circle.setStrokeWidth(thickness);
////
////        cog1=circle;
////
////        List<Line> lines = new ArrayList<>();
////        for (int i = 0; i < n; i++) {
////            lines.add(new Line(offset,0,offset+L,0));
////        }
////
////
////        for (int i = 0; i < n; i++) {
////            lines.get(i).setStroke(Color.BLACK);
////            lines.get(i).setStrokeWidth(thickness);
////            lines.get(i).getTransforms().add(new Rotate(360.0/n*i,0,0));
////            lines.get(i).setStrokeLineCap(StrokeLineCap.ROUND);
//////            cog1=Shape.union(cog1,lines.get(i));
////        }
////
////        cog1=Shape.union(cog1,lines.get(0));
////
//////        lines.forEach(line -> {
//////            int i=lines.indexOf(line);
//////
//////        });
////
////
////
////
////
//////        Shape p=Shape.union(circle,lines.get(0));
////
//////        p.s
//////        Circle circle1=new Circle(centerX,centerY,radius1);
//////        circle1.setFill(Color.WHITE);
////
////
//////        cog.getChildren().addAll(lines);
//////        cog.getChildren().addAll(circle);
////
////        container.getChildren().addAll(cog1);
////
////        cog1.setTranslateX(250);
////        cog1.setTranslateY(250);
//
////        cog.setBackground(Background.fill(Color.RED));
//
//
////        Rectangle r1=new Rectangle(0,0,200,200);
////        Rectangle r2=new Rectangle(100,100,200,200);
////
////        Union
//
////        Label label=new Label();
////        Rectangle r=new Rectangle(150,150);
////
////        container.getChildren().addAll(r,label);
////
////        label.setText(uuid(50));
////        label.setWrapText(true);
////
////        setCustomWidth(label,150);


    }
}