package com.boom.test;

import com.boom.apppaints.AppColor;
import com.boom.appshapes.AppRectangle;
import com.boom.panels.paint.ColorManagementPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test57 extends Application {

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

//        Rectangle rectangle=new Rectangle(300,300,Color.valueOf("00000033"));
//        rectangle.setMouseTransparent(true);

        AppRGBAColorPicker appRGBAColorPicker=new AppRGBAColorPicker(  );



        Random rnd=new Random();

        double red=rnd.nextDouble()/2+0.5;
        double green=rnd.nextDouble()/2+0.5;
        double blue=rnd.nextDouble()/2+0.5;
        double alpha=rnd.nextDouble()/2+0.5;

        print("red  = "+red);
        print("green = "+green);
        print("blue = "+blue);
        print("alpha = "+alpha);

        AppRectangle appRectangle=new AppRectangle(300,300);
        AppColor appColor=new AppColor(new Color(red,green,blue,1));

        appRectangle.backgroundStyle.addFill(appColor);

        appRGBAColorPicker.registerColor(appColor);

//        ColorManagementPanel colorManagementPanel=new ColorManagementPanel(appColor);
//
//        colorManagementPanel.show(stage);

//        colorManagementPanel

//        appColor.addListener((a,b,c)->print("va = "+c));



        container.getChildren().addAll(appRGBAColorPicker,appRectangle.getStyleableNode());
        appRectangle.getStyleableNode().setTranslateX(300);

//        RGBAFieldIndicator rgbaFieldIndicator=new RGBAFieldIndicator(20,100,10);
//        container.getChildren().addAll(rgbaFieldIndicator);
//        rgbaFieldIndicator.setTranslateX(300);
//        rgbaFieldIndicator.setTranslateY(300);



    }

    int rem(int x,int y){
        return x-y*(x/y);
    }

}





