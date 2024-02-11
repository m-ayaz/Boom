package com.boom.test;

import com.boom.appshapes.AppRectangle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test78 extends Application {

    public static void main(String[] args) {
        launch();
    }

    Random rnd = new Random();
//    DataComparator dataComparator = new DataComparator();

    @Override
    public void start(Stage stage) throws Exception {

        GridPane container = new GridPane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        double a=80;
        AppRectangle appRectangle1=new AppRectangle(200,200,a,a);
        AppRectangle appRectangle2=new AppRectangle(200,200,a,a);
        AppRectangle appRectangle3=new AppRectangle(200,200,a,a);
        AppRectangle appRectangle4=new AppRectangle(200,200,a,a);

//        Pane pane=new Pane(),pane1=new Pane();
//        pane.getChildren().add(appRectangle4.styleableNode);
//        pane1.getChildren().add(pane);



        container.addRow(0,appRectangle1.styleableNode,appRectangle2.styleableNode);
        container.addRow(1,appRectangle3.styleableNode,appRectangle4.styleableNode);

//        appRectangle4.affineTransform.prependRotation(45);

//        Group group=new Group();
//        group.getChildren().add(new Pane());



//        SVGPath path=new SVGPath();
//        path.setContent();
//        SVGPath

//        pane.setShape;

//        appRectangle4.styleableNode.boundsInParentProperty().addListener((a1,b,c)->{
////            c.
////            print(c.getMinX());
//            print(c.getCenterX()+" , "+c.getCenterY());
////            print(c.getCenterY());
//            appRectangle4.affineTransform.prependTranslation(
//                    appRectangle4.styleableNode.getBoundsInLocal().getCenterX()-c.getCenterX(),
//                    appRectangle4.styleableNode.getBoundsInLocal().getCenterY()-c.getCenterY()
//            );
//        });



//        print(appRectangle4.styleableNode.boundsInParentProperty().get().getCenterX()+" ,,,,,,,,,,,,,,,,,,,, "+
//                appRectangle4.bounds.getCenterY());

//        print(appRectangle4.styleableNode.getBoundsInParent().getCenterX()+" ,,,,,,,,,,,,,,,,,,,, "+
//                appRectangle4.styleableNode.getBoundsInParent().getCenterY());






    }


}

