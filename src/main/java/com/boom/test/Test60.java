package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import static com.boom.tools.Tools.setCustomSize;


public class Test60 extends Application {

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

        LineChart<Number,String> lineChart=new LineChart<>(new NumberAxis(),new CategoryAxis());
        lineChart.getData().add(new XYChart.Series<>());
        lineChart.getData().get(0).getData().add(new XYChart.Data<>(1,"2"));
        lineChart.getData().get(0).getData().add(new XYChart.Data<>(3,"5"));
        lineChart.getData().get(0).getData().add(new XYChart.Data<>(4,"6"));
        lineChart.getData().get(0).getData().add(new XYChart.Data<>(2,"as"));


        container.getChildren().add(lineChart);

        lineChart.setTranslateX(100);
        lineChart.setTranslateY(100);

        setCustomSize(lineChart,300,300);


        Rectangle rectangle=new Rectangle();

//        container.getChildren().add(rectangle);

        lineChart.boundsInLocalProperty().addListener((a,b,c)->{
            rectangle.setX(c.getMinX());
            rectangle.setY(c.getMaxY());
            rectangle.setWidth(c.getWidth());
            rectangle.setHeight(c.getHeight());
        });

        lineChart.setOnMouseMoved(mouseEvent -> {

            lineChart.getTransforms().add(new Rotate(1));

        });
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(1,2));
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(1,2));
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(1,2));
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(1,2));



//        lineChart.getXAxis().

//        Test test=new Test();


//        container.setOnMouseMoved(test);



//        Rectangle rectangle=new Rectangle(300,300,Color.valueOf("00000033"));
//        rectangle.setMouseTransparent(true);

//        AppRGBAColorPicker appRGBAColorPicker=new AppRGBAColorPicker(  );
//
//
//
//        Random rnd=new Random();
//
//        double red=rnd.nextDouble()/2+0.5;
//        double green=rnd.nextDouble()/2+0.5;
//        double blue=rnd.nextDouble()/2+0.5;
//        double alpha=rnd.nextDouble()/2+0.5;
//
//        print("red  = "+red);
//        print("green = "+green);
//        print("blue = "+blue);
//        print("alpha = "+alpha);
//
//        AppRectangle appRectangle=new AppRectangle(300,300);
//        AppColor appColor=new AppColor(new Color(red,green,blue,1));
//
//        appRectangle.backgroundStyle.addFill(appColor);
//
//        appRGBAColorPicker.registerColor(appColor);
//
////        ColorManagementPanel colorManagementPanel=new ColorManagementPanel(appColor);
////
////        colorManagementPanel.show(stage);
//
////        colorManagementPanel
//
////        appColor.addListener((a,b,c)->print("va = "+c));
//
//
//
//        container.getChildren().addAll(appRGBAColorPicker,appRectangle.getStyleableNode());
//        appRectangle.getStyleableNode().setTranslateX(300);
//
////        RGBAFieldIndicator rgbaFieldIndicator=new RGBAFieldIndicator(20,100,10);
////        container.getChildren().addAll(rgbaFieldIndicator);
////        rgbaFieldIndicator.setTranslateX(300);
////        rgbaFieldIndicator.setTranslateY(300);



    }


}






