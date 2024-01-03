package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.setCustomSize;


public class Test66 extends Application {

    public static void main(String[] args) {
        launch();
    }
    Random rnd=new Random();

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

//        XYChart

        BubbleChart<Number,Number> chart=new BubbleChart<>(new NumberAxis(),new NumberAxis());

        for(int i=0;i<1;i++){
            chart.getData().add(new XYChart.Series<>());
            for(int j=0;j<10;j++){
                chart.getData().get(i).getData().add(new XYChart.Data<>(rnd.nextDouble(), rnd.nextDouble()));
            }
        }

        container.getChildren().add(chart);

        setCustomSize(chart,500,350);

        chart.getTransforms().add(new Scale(2,2));

//        chart.getXAxis().setLa
//        SVGPath svgPath=new SVGPath();
//
//        svgPath.setContent("M 100 300\n" +
//                "A 200 100 0 0 1 300 400\n" +
////                "L 250 300\n" +
////                "A 50 50 0 0 0 150 300\n" +
////                "L 100 300\n" +
//                "Z");
//
//        container.getChildren().add(svgPath);
//
//        svgPath.setFill(new Color(1,0,0,0.7));
//        QuadCurve quadCurve=new QuadCurve();
//
//        double x=500,y=350,r=300;
//
//        Circle circle=new Circle(x,y,r);
////        Circle circle1=new Circle(300,400,10);
////        Circle circle2=new Circle(600,600,10);
//        circle.setFill(new Color(1,0,0,0.5));
//
//        quadCurve.setFill(Color.TRANSPARENT);
//        quadCurve.setStroke(new Color(0,0,1,0.5));
//
//        quadCurve.setStrokeWidth(10);
//
//        double th=1;
//        quadCurve.setStartX(x+r*Math.cos(th));
//        quadCurve.setStartY(y+r*Math.sin(th));
//        quadCurve.setEndX(x+r*Math.cos(th));
//        quadCurve.setEndY(y-r*Math.sin(th));
//
//        quadCurve.setControlX(x+r/Math.cos(th)-110);
//        quadCurve.setControlY(y);
//
//        container.getChildren().addAll(circle,quadCurve);
//
//
//
//
//
////        Circle circle=new Circle(5,Color.BLUE);
//
////        String x="asasas";
////        Rectangle rectangle=new Rectangle(0,0,0,0,0,0);
//
////        container.getChildren().add(rectangle);
//
//
////        rectangle.
////switch (x){
////    case "asasas"->{
////print("a");
////    }
////    case "asasas1"->{
////
////    }
////}
//
//
////        String string="Some Text";
////
////        Text text=new Text(string);
////        TextArea textArea=new TextArea(string);
////
////        Rectangle rectangle1=new Rectangle();
////        Rectangle rectangle2=new Rectangle();
////
////        text.setFont(new Font(50));
////
////        container.getChildren().add(textArea);
//////        container.getChildren().add(text);
////        container.getChildren().addAll(rectangle1,rectangle2,circle);
////        text.setTranslateX(100);
////        text.setTranslateY(100);
////        text.setCaretBias(false);
//////        text.set
////
////        textArea.setFont(new Font(100));
////
////        text.setBoundsType(TextBoundsType.VISUAL);
////
////        JSONArray jsonArray=new JSONArray();
//
////        List<Double> a=jsonArray.m(u->Double.parseDouble(u.toString()));
//
////        textArea.setStyle("-fx-label-padding: 0");
//
////        textArea.setStyle("  -fx-padding: -5px;\n" +
////                "    -fx-border-insets: -5px;\n" +
////                "    -fx-background-insets: -5px;");
////        TextFormatter<Rectangle> h= new TextFormatter<>(new Rectangle());
//
////        h.
//
////        textArea.setTextFormatter();
//
////        textArea.setScrollTop(0);
////        textArea.setScrollLeft(0);
//

//        textArea.setScrollTop(1000);
//        textArea.setBo










//        rectangle1.setFill(new Color(1,0,0,0.1));
//
//        rectangle1.setX(textArea.getBoundsInParent().getMinX());
//        rectangle1.setY(textArea.getBoundsInParent().getMinY());
//        rectangle1.setWidth(textArea.getBoundsInParent().getWidth());
//        rectangle1.setHeight(textArea.getBoundsInParent().getHeight());
//        circle.setCenterX(rectangle1.getBoundsInParent().getMinX());
//        circle.setCenterY(rectangle1.getBoundsInParent().getMaxY());
//
//        textArea.boundsInParentProperty().addListener((a,b,c)->{
//            print(c);
//            rectangle1.setX(c.getMinX());
//            rectangle1.setY(c.getMinY());
//            rectangle1.setWidth(c.getWidth());
//            rectangle1.setHeight(c.getHeight());
//
//        });

    }


}
