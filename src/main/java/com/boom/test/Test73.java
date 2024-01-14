package com.boom.test;

import com.boom.appcharts.AppAxisChart;
import com.boom.appcharts.AppData;
import com.boom.structures.enums.AppAxisSortingPolicy;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.*;


public class Test73 extends Application {

    public static void main(String[] args) {
        launch();
    }
    Random rnd=new Random();
    DataComparator dataComparator=new DataComparator();

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        Text text0=new Text();
        Text text1=new Text();
        Text text2=new Text();
        Text text3=new Text();
        Text text4=new Text();
        Text text5=new Text();
        Text text6=new Text();
        Text text7=new Text();
        Text text8=new Text();
        Text text9=new Text();

        text0.setTranslateX(200);
        text0.setTranslateY(200);



        text0.setText("1e-4");

        container.getChildren().addAll(text0,text1);

        double x=0.0000489765451;

        for(int i=0;i<12;i++) {
            print(getScientificRepresentation(x, i));
        }

//        print(Math.floor(Math.log10(x)));

//        print(Math.(x));

//        for(int i=0;i<200;i++) {
////            text.setFont(Font.font(i));
//
////            print(text.getBoundsInParent().getHeight());
//        }

//        print();

//        AppAxisChart appAxisChart=new AppAxisChart();
//        AppAxisChart appAxisChart1=new AppAxisChart();
//        container.getChildren().addAll(appAxisChart);
//
////        setCustomSize(appAxisChart,500,500);
////        setCustomSize(appAxisChart1,500,500);
//        appAxisChart.plotRegionWidth.set(400);
//        appAxisChart.plotRegionHeight.set(400);
//
//        appAxisChart1.plotRegionWidth.set(400);
//        appAxisChart1.plotRegionHeight.set(400);
//
//        appAxisChart.xAxisRegionHeight.set(50);
//        appAxisChart.yAxisRegionWidth.set(50);
//
//        appAxisChart1.setTranslateX(400);
//
////        appAxisChart.setBackground(Background.fill(new Color(0,0,1,0.2)));
////        appAxisChart.setBorder(Border.stroke(Color.BLACK));
////        appAxisChart1.setBorder(Border.stroke(Color.BLACK));
//
//        appAxisChart.addSeries();
//        appAxisChart1.addSeries();
////        appAxisChart.addSeries();
//
//        List<AppData> appDataList =new ArrayList<>();
////        appDataList.add(new AppData("0","0"));
////        appDataList.add(new AppData("1","1"));
////        appDataList.add(new AppData("2","0"));
//        for(int i=0;i<9;i++){
//            appDataList.add(new AppData((i+rnd.nextDouble()/1000)+"as",(i+rnd.nextDouble()/0.2)+""));
////            appDataList1.add(new AppData((i+rnd.nextDouble()/0.2)+"",(i+rnd.nextDouble()/0.2)+""));
////            appDataList.add(new AppData(rnd.nextDouble() + "", rnd.nextDouble() + ""));
////            appDataList1.add(new AppData(rnd.nextDouble() + "", rnd.nextDouble() + ""));
//        }
//
//        appAxisChart.addManyData(0,0, appDataList);
//        appAxisChart1.addManyData(0,0, appDataList);
////        appAxisChart.addManyData(1,0, appDataList1);
//
//        appAxisChart.seriesPlotAreaList.get(0).setFill(new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,
//                new Stop(0,Color.valueOf("ff000088")),new Stop(1,Color.valueOf("0000ff88"))));
//        appAxisChart1.seriesPlotAreaList.get(0).setFill(new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,
//                new Stop(0,Color.valueOf("ff000088")),new Stop(1,Color.valueOf("0000ff88"))));
////        appAxisChart.seriesPlotAreaList.get(1).setFill(new LinearGradient(0,0,0,1,true, CycleMethod.NO_CYCLE,
////                new Stop(0,Color.valueOf("ffff0088")),new Stop(1,Color.valueOf("00ff0088"))));
//
////        container.setOnMouseDragged(mouseEvent -> {
////            appAxisChart.plotRegionWidth.set(300+100*rnd.nextDouble());
////            appAxisChart.plotRegionHeight.set(300+100*rnd.nextDouble());
////        });
//
//        appAxisChart.setXNumeric(false);
//        appAxisChart.setYNumeric(true);
//        appAxisChart.setAxisSortingPolicy(AppAxisSortingPolicy.SortByX);
//
//        appAxisChart1.setXNumeric(false);
//        appAxisChart1.setYNumeric(true);
//        appAxisChart1.setAxisSortingPolicy(AppAxisSortingPolicy.SortByY);


//        DoubleBinding doubleBinding=new DoubleBinding() {
//
//
//
//            @Override
//            protected double computeValue() {
//                return 0;
//            }
//        };

        DoubleProperty x1=new SimpleDoubleProperty();
        sqrtProperty y=new sqrtProperty(x1);

        Button button=new Button("akjldklasjdkladjklsajdlasdj");
        setCustomSize(button,200,200);

        container.getChildren().add(button);

        button.setOnAction(event -> {
            double h=rnd.nextInt(20);
            print(h);
            x1.set(h);
            print(y.computeValue());
        });


























    }


}

class sqrtProperty extends DoubleBinding{

    DoubleProperty x;

    public sqrtProperty(DoubleProperty x){
        this.x=x;
    }

    @Override
    protected double computeValue() {
        return Math.sqrt(x.doubleValue());
    }
}