package com.boom.test;

import com.boom.apppaints.AppLinearGradient;
import com.boom.appshapes.AppRectangle;
import com.boom.structures.abstracts.AppPaint;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test81 extends Application {

    public static void main(String[] args) {
        launch();
    }

    Random rnd = new Random();
//    DataComparator dataComparator = new DataComparator();

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


        AppRectangle appRectangle=new AppRectangle(0,0,0,0);
        print(appRectangle.id);

        AppPaint appPaint=new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                new Stop(0,Color.TRANSPARENT),new Stop(1,Color.TRANSPARENT)));
        print(appPaint.id);

//        Rectangle rectangle=new Rectangle(100,100,100,100);
//
//        rectangle.setFill(new Color(1,0,0,0.2));
//
//        Text label=new Text("uuid(50)");
//
//        label.setFont(Font.font("Times New Roman",50));
//
//        label.getTransforms().add(new Translate(100,100));
//
//
//        container.getChildren().addAll(rectangle,label);
//
//        container.setOnMouseDragged(mouseEvent -> {
//            label.setTranslateX(Math.random());
//            print(label.getBoundsInParent());
//        });
//
////        print(label.getLayoutBounds());
//
////        Affine affine=new Affine();
////
////        affine.prependRotation(Math.random()*80*0+80-40);
//////        affine.prependScale(1.3,1);
////
////        double[] x=dissectAffineTransform(affine);
////
////        print(affine);
////        print(x[0]+" , "+x[1]+" , "+x[2]+" , "+x[3]);
////
//////        byte h=50;
////
//////        print(h);
////
//////        double a=100;
//////        AppRectangle appRectangle1=new AppRectangle(200,200,a,a);
//////        AppRectangle appRectangle2=new AppRectangle(200,200,a,a);
//////        AppRectangle appRectangle3=new AppRectangle(200,200,a,a);
//////        AppRectangle appRectangle4=new AppRectangle(200,200,a,a);
//////
////////        Pane pane=new Pane(),pane1=new Pane();
////////        pane.getChildren().add(appRectangle4.styleableNode);
////////        pane1.getChildren().add(pane);
//////        BorderPane borderPane=new BorderPane();
//////        borderPane.setCenter(appRectangle4.styleableNode);
//////
//////
//////
//////        container.addRow(0,appRectangle1.styleableNode,appRectangle2.styleableNode);
//////        container.addRow(1,appRectangle3.styleableNode,borderPane);
//////
//////
////////        BorderPane
//////
//////
////////        container.setAlignment(Pos.CENTER);
//////
////////        GridPane gridPane=new GridPane();
//////        container.s
////
//////        appRectangle4.affineTransform.prependRotation(45);
////
//////        appRectangle4.affineTransform.prependTranslation(-appRectangle4.border.getX(),-appRectangle4.border.getY());
//////        appRectangle4.styleableNode.setTranslateY(-appRectangle4.border.getY());
////
//////        Group group=new Group();
//////        group.getChildren().add(new Pane());
////
////
////
//////        SVGPath path=new SVGPath();
//////        path.setContent();
//////        SVGPath
////
//////        pane.setShape;
////
//////        appRectangle4.styleableNode.boundsInParentProperty().addListener((a1,b,c)->{
////////            c.
////////            print(c.getMinX());
//////            print(c.getCenterX()+" , "+c.getCenterY());
////////            print(c.getCenterY());
//////            appRectangle4.affineTransform.prependTranslation(
//////                    appRectangle4.styleableNode.getBoundsInLocal().getCenterX()-c.getCenterX(),
//////                    appRectangle4.styleableNode.getBoundsInLocal().getCenterY()-c.getCenterY()
//////            );
//////        });
////
////
////
//////        print(appRectangle4.styleableNode.boundsInParentProperty().get().getCenterX()+" ,,,,,,,,,,,,,,,,,,,, "+
//////                appRectangle4.bounds.getCenterY());
////
//////        print(appRectangle4.styleableNode.getBoundsInParent().getCenterX()+" ,,,,,,,,,,,,,,,,,,,, "+
//////                appRectangle4.styleableNode.getBoundsInParent().getCenterY());
////
////
////
////
////

    }


}

