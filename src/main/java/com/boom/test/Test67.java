package com.boom.test;

import com.boom.appcharts.AppData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test67 extends Application {

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

        ObservableList<ObservableList<AppData>> dataList= FXCollections.observableList(new ArrayList<>());

        dataList.addListener((ListChangeListener<? super ObservableList<AppData>>) change->{
            print("change in series list = "+change);
        });

        ObservableList<AppData> appData1 =FXCollections.observableList(new ArrayList<>());
        ObservableList<AppData> appData2 =FXCollections.observableList(new ArrayList<>());
        ObservableList<AppData> appData3 =FXCollections.observableList(new ArrayList<>());

        appData1.addListener((ListChangeListener<? super AppData>) change -> {

            print("change in series 1 = "+change);

        });
        appData2.addListener((ListChangeListener<? super AppData>) change -> {

            print("change in series 2 = "+change);;

        });
        appData3.addListener((ListChangeListener<? super AppData>) change -> {

            print("change in series 3 = "+change);

        });

        dataList.add(appData1);
        dataList.add(appData2);
        dataList.add(appData3);

        appData1.add(new AppData("4,6","h"));

        LineChart x=new LineChart(new NumberAxis(),new NumberAxis());


//        x.getXAxis().set


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
