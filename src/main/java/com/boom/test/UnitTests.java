package com.boom.test;

import com.boom.appcharts.baseclasses.AppSeries;
import javafx.application.Application;
import javafx.stage.Stage;
import org.testng.annotations.Test;

import static com.boom.tools.Tools.print;

public class UnitTests {

    @Test
    public void runAppSeriesTest() {

        AppSeries appSeries1 = new AppSeries();
        AppSeries appSeries2 = new AppSeries();

        double newX,newY;

        int n = 3;
        print("%%%%%%%% Stuffing data... %%%%%%%%");
        for (int i = 0; i < n; i++) {
            newX=Math.random();
            newY=Math.random();
            print("_________________________");
//            print("minX = %f , maxX = %f".formatted(appSeries1.minX.get(), appSeries1.maxX.get()));
//            print("minY = %f , maxY = %f".formatted(appSeries1.minY.get(), appSeries1.maxY.get()));
////            print("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
////
////            print("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
////            print("(%f,%f) is being added...".formatted(newX,newY));
//            appSeries1.dataList.add(new double[]{newX,newY});
////            appSeries2.dataList.add(new double[]{Math.random(), Math.random()});
//            print("minX = %f , maxX = %f".formatted(appSeries1.minX.get(), appSeries1.maxX.get()));
//            print("minY = %f , maxY = %f".formatted(appSeries1.minY.get(), appSeries1.maxY.get()));
        }
        print("%%%%%%%% Depleting data... %%%%%%%%");
        for (int i = 0; i < n; i++) {
            print("_________________________");
//            print("minX = %f , maxX = %f".formatted(appSeries1.minX.get(), appSeries1.maxX.get()));
//            print("minY = %f , maxY = %f".formatted(appSeries1.minY.get(), appSeries1.maxY.get()));
//            appSeries1.dataList.remove(0);
//            print("minX = %f , maxX = %f".formatted(appSeries1.minX.get(), appSeries1.maxX.get()));
//            print("minY = %f , maxY = %f".formatted(appSeries1.minY.get(), appSeries1.maxY.get()));
        }

    }

//    @Override
//    public void start(Stage stage) throws Exception {
//
//    }

//    @Test
//    public void runChartExtremeValuesTest(){
//
//        AppAxisChart appAxisChart=new AppAxisChart(0,0);
//        AppSeries appSeries1=new AppSeries();
//        AppSeries appSeries2=new AppSeries();
//
//
//        appAxisChart.addSeries(appSeries1);
//        appAxisChart.addSeries(appSeries2);
//
//    }


}