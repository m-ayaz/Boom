package com.boom.test;

import com.boom.appcharts.AppAxisChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test60 extends Application {

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

        AppAxisChart appAxisChart=new AppAxisChart();

        appAxisChart.addSeries();

        appAxisChart.addData(0,5.,6.);

        appAxisChart.addData(0,"5.",6.);

//        Stack
//        String l="as";
//
//        print(l instanceof Number);



//        AreaChart<Number,Number> areaChart=new AreaChart<>(new NumberAxis(),new NumberAxis());
////
////        container.getChildren().add(areaChart);
////
//
////
////        for(int i=0;i<1;i++){
////            areaChart.getData().add(new XYChart.Series<>());
////            for(int j=0;j<10;j++){
////                areaChart.getData().get(i).getData().add(new XYChart.Data<>(rnd.nextDouble(),rnd.nextDouble()));
////            }
////        }
////
////        Affine affine=new Affine();
////        areaChart.getTransforms().add(affine);
////
//////        setCustomHeight(areaChart.getXAxis(),20);
////        setCustomWidth(areaChart.getYAxis(),10);
//
//
////        areaChart.setOnMouseMoved(mouseEvent -> {
//////            affine.prependScale(1.02,1);
////            setCustomSize(areaChart,400+rnd.nextDouble()*150,400+rnd.nextDouble()*150);
////            print(areaChart.getYAxis().getWidth());
////            print(areaChart.getYAxis().getPrefWidth());
////        });
//
//        HHH hhh=new HHH(new NumberAxis(),new NumberAxis());
//
////        hhh.seriesAdded(new XYChart.Series<>(),0);
//
////        setCustomSize(hhh,200,200);
//
//        container.getChildren().setAll(hhh);
//
//        XYChart.Series<Number,Number> series=new XYChart.Series<>();

//        hhh.addSeries(series,0);
//
//        hhh.setOnMouseMoved(mouseEvent -> {
//            hhh.setTranslateX(rnd.nextDouble()*0);
//            hhh.addSeries(new XYChart.Series<>(),0);
//        });

//        hhh.getTransforms().add(new Translate(50,50));
//        hhh.addData(series,0,new XYChart.Data<>(rnd.nextDouble(), rnd.nextDouble()));
//        hhh.addData(series,0,new XYChart.Data<>(rnd.nextDouble(), rnd.nextDouble()));
//        hhh.addData(series,0,new XYChart.Data<>(rnd.nextDouble(), rnd.nextDouble()));
//        hhh.addData(series,0,new XYChart.Data<>(rnd.nextDouble(), rnd.nextDouble()));
//        hhh.addData(series,0,new XYChart.Data<>(rnd.nextDouble(), rnd.nextDouble()));

//        print(hhh.getChildrenUnmodifiable());





//        areaChart.getXAxis().set

//        print(((Chart)areaChart.getChildrenUnmodifiable().get(1)).);
//        AreaChart

//        areaChart.setCreateSymbols(false);


    }


}

class HHH<X,Y> extends XYChart<X,Y> {

    public HHH(Axis<X> axis, Axis<Y> axis1) {
        super(axis, axis1);
    }

    @Override
    protected void dataItemAdded(Series series, int i, Data data) {

    }

    @Override
    protected void dataItemRemoved(Data data, Series series) {

    }

    @Override
    protected void dataItemChanged(Data data) {

    }

    @Override
    protected void seriesAdded(Series series, int i) {

    }

    @Override
    protected void seriesRemoved(Series series) {

    }

    @Override
    protected void layoutPlotChildren() {

    }
}