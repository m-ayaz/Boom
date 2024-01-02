package com.boom.test;

import com.boom.apppaints.AppLinearGradient;
import com.boom.apppaints.AppRadialGradient;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppRectangle;
import com.boom.tools.Chessboard;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.stream.Collectors;

import static com.boom.tools.Tools.*;


public class Test62 extends Application {

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

        QuadCurve line=new QuadCurve(0,0,400,0,400,400);

        line.setStrokeWidth(10);

        Region region=new Region();
        region.setShape(line);
        region.setStyle("-fx-border-width: 100;-fx-border-color: red;-fx-background-color: blue;");
//        region.setStyle("-fx-stroke-width: 100;-fx-stroke: red;-fx-fill: blue;");
        setCustomSize(region,200,200);

//        region.setTranslateX();


        container.setOnMouseMoved(mouseEvent -> {
            r.setTranslateX(mouseEvent.getX()-a);
            r.setTranslateY(mouseEvent.getY()-a);

            if(line.contains(mouseEvent.getX(),mouseEvent.getY())){
                print(uuid(10));
            }
        });

        line.setFill(Color.TRANSPARENT);

        container.getChildren().addAll(line);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(10);

//        line.
//        line.setM
//        line.lookup(".fill").setMouseTransparent(true);
//        r.setStroke(Color.BLUE);
//        r.setStrokeWidth(1);
//        r.setStroke(Color.RED);





    }
    double a=100;
    Rectangle r=new Rectangle(2*a,2*a);

//    Bounds


}
