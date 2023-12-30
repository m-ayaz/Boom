package com.boom.test;

import com.boom.apppaints.AppColor;
import com.boom.appshapes.AppRectangle;
import com.boom.tools.Chessboard;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;


public class Test58 extends Application {

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

//        AppRectangle appRectangle=new AppRectangle(20,20);
//
//        appRectangle.backgroundStyle.addFill(new AppColor(new Color(1,0,0,1)));
//        appRectangle.affineTransform.prependScale(10,10);
//        appRectangle.backgroundStyle.setStrokeWidth(0.001);
//
//        container.getChildren().addAll(appRectangle.getStyleableNode());


//        container.getChildren().addAll(new Chessboard(10,30,10,Color.valueOf("00000099"),Color.valueOf("00000022")));

//        SimpleDoubleProperty g=new SimpleDoubleProperty(0);
//
//        g.addListener((a,b,c)->{
//            print(c);
//        });

//        g.setValue(0);

//        g.



//        Rectangle rectangle=new Rectangle(300,300,Color.valueOf("00000033"));
//        rectangle.setMouseTransparent(true);


//        container.getChildren().addAll(new AppRGBAColorPicker(300, 300, 30, 30, 30, 30, 30, 20,10));

//        RGBAFieldIndicator rgbaFieldIndicator=new RGBAFieldIndicator(20,100,10);
//        container.getChildren().addAll(rgbaFieldIndicator);
//        rgbaFieldIndicator.setTranslateX(300);
//        rgbaFieldIndicator.setTranslateY(300);



    }

    int rem(int x,int y){
        return x-y*(x/y);
    }

}





