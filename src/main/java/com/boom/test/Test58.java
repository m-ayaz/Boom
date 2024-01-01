package com.boom.test;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;


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


        LineChart<Number,Number> lineChart=new LineChart<>(new NumberAxis(),new NumberAxis());

        Rectangle rectangle=new Rectangle();
        Rectangle rectangle1=new Rectangle();
        Rectangle rectangle2=new Rectangle();
        Rectangle rectangle3=new Rectangle();

        container.getChildren().addAll(rectangle,rectangle1,rectangle2,rectangle3,lineChart);

        rectangle.setFill(new Color(1,0,0,0.1));

        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: rgba(0,0,255,0.2)");



        Affine affine=new Affine();

        lineChart.getTransforms().add(affine);

        lineChart.setPadding(new Insets(0));
        lineChart.getXAxis().setPadding(new Insets(0));
        lineChart.getYAxis().setPadding(new Insets(0));
        ((Region)lineChart.lookup(".chart-plot-background")).setPadding(new Insets(0));

        rectangle.getTransforms().add(affine);



//        DoubleProperty x=new SimpleDoubleProperty();

        ReadOnlyObjectProperty<Bounds> plotBounds=lineChart.lookup(".chart-plot-background").boundsInParentProperty();
        ReadOnlyObjectProperty<Bounds> xAxisBounds=lineChart.getXAxis().boundsInParentProperty();
        ReadOnlyObjectProperty<Bounds> yAxisBounds=lineChart.getYAxis().boundsInParentProperty();

//        print(plotBounds.get());
//        print(xAxisBounds.get());
//        print(yAxisBounds.get());

        rectangle1.setFill(new Color(0,1,0,0.2));
        rectangle2.setFill(new Color(1,0,1,0.2));

        xAxisBounds.addListener((a,b,c)->{
//            print(c);
            rectangle1.setTranslateX(c.getMinX());
            rectangle1.setTranslateY(c.getMinY());
            rectangle1.setWidth(c.getWidth());
            rectangle1.setHeight(c.getHeight());
        });


        yAxisBounds.addListener((a,b,c)->{
//            print(c);
            rectangle2.setTranslateX(c.getMinX());
            rectangle2.setTranslateY(c.getMinY());
            rectangle2.setWidth(c.getWidth());
            rectangle2.setHeight(c.getHeight());
        });

        plotBounds.addListener((a,b,c)->{
//            print(c);
            rectangle.setTranslateX(c.getMinX());
            rectangle.setTranslateY(c.getMinY());
            rectangle.setWidth(c.getWidth());
            rectangle.setHeight(c.getHeight());
            print(c);
        });








//        rectangle.setWidth(200);
//        rectangle.setHeight(200);

        setCustomSize(lineChart,200,200);

//        affine.prependTranslation(100,100);
//
//        affine.prependScale(1.2,1.3);


//        localBounds

//        localBounds.

//        lineChart.prefWidthProperty().

//        affine.prependScale();





//        LineChart<Number,Number> x;

//        print(x.getClass().getName());






    }


}





