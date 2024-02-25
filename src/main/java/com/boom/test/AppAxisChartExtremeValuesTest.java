package com.boom.test;

import com.boom.appcharts.baseclasses.AppAxisChart;
import com.boom.appcharts.baseclasses.AppSeries;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;


public class AppAxisChartExtremeValuesTest extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        AppAxisChart appAxisChart=new AppAxisChart(400,400);

        container.getChildren().add(appAxisChart);
        appAxisChart.setTranslateX(200);
        appAxisChart.setTranslateY(200);

        AppSeries appSeries1=new AppSeries();
        AppSeries appSeries2=new AppSeries();

        appAxisChart.addSeries(appSeries1);
        appAxisChart.addSeries(appSeries2);

        double newX1,newY1,newX2,newY2;

        print("%%%%%%%% Stuffing data... %%%%%%%%");

        for(int i=0;i<5;i++){
            print("______________________________________ i = "+i);
            print("MinX = %f , maxX = %f".formatted(appAxisChart.globalMinX.get(),appAxisChart.globalMaxX.get()));
            print("MinY = %f , maxY = %f".formatted(appAxisChart.globalMinY.get(),appAxisChart.globalMaxY.get()));
            print(appAxisChart.xAxisRegion.ticks.keySet().stream().sorted().toList());
            print(appAxisChart.yAxisRegion.ticks.keySet().stream().sorted().toList());
            newX1=Math.random();
            newY1=Math.random();
            newX2=Math.random();
            newY2=Math.random();
            print("(%f,%f) is being added...".formatted(newX1,newY1));
            print("(%f,%f) is being added...".formatted(newX2,newY2));
            appSeries1.dataList.add(new double[]{newX1,newY1});
            appSeries2.dataList.add(new double[]{newX2,newY2});
            print("MinX = %f , maxX = %f".formatted(appAxisChart.globalMinX.get(),appAxisChart.globalMaxX.get()));
            print("MinY = %f , maxY = %f".formatted(appAxisChart.globalMinY.get(),appAxisChart.globalMaxY.get()));
            print(appAxisChart.xAxisRegion.ticks.keySet().stream().sorted().toList());
            print(appAxisChart.yAxisRegion.ticks.keySet().stream().sorted().toList());
        }


        print("%%%%%%%% Depleting data... %%%%%%%%");

        for(int i=0;i<3;i++){
            print("______________________________________ i = "+i);
            print("MinX = %f , maxX = %f".formatted(appAxisChart.globalMinX.get(),appAxisChart.globalMaxX.get()));
            print("MinY = %f , maxY = %f".formatted(appAxisChart.globalMinY.get(),appAxisChart.globalMaxY.get()));
            print(appAxisChart.xAxisRegion.ticks.keySet().stream().sorted().toList());
            print(appAxisChart.yAxisRegion.ticks.keySet().stream().sorted().toList());
//            print(appAxisChart.appGridLines.xGridLinesStrokeDashArray);
//            print(appAxisChart.appGridLines.yGridLinesStrokeDashArray);
            appSeries1.dataList.remove(0);
            appSeries2.dataList.remove(0);
//            print(appSeries1.dataList);
//            print(appSeries2.dataList);
            print("MinX = %f , maxX = %f".formatted(appAxisChart.globalMinX.get(),appAxisChart.globalMaxX.get()));
            print("MinY = %f , maxY = %f".formatted(appAxisChart.globalMinY.get(),appAxisChart.globalMaxY.get()));
            print("X ticks = "+appAxisChart.xAxisRegion.ticks.keySet().stream().sorted().toList());
            print("Y ticks = "+appAxisChart.yAxisRegion.ticks.keySet().stream().sorted().toList());

//            for (double updatedTick : appAxisChart.getUpdatedTicks(appAxisChart.globalMinX.get(), appAxisChart.globalMaxX.get(), 360, 40, 20)) {
//                print(updatedTick);
//            }
////            print(appAxisChart.appGridLines.xGridLinesStrokeDashArray);
////            print(appAxisChart.appGridLines.yGridLinesStrokeDashArray);
        }





//        appAxisChart.





//        appAxisChart.setTranslateX(100);
//        appAxisChart.setTranslateY(100);
//
//        container.getChildren().add(appAxisChart);
//
//
//        double a = 14, b = 14;
//        AppEllipse marker1 = new AppEllipse(a, b);
//        AppEllipse marker2 = new AppEllipse(b, a);
//        marker1.backgroundStyle.addFill(new AppColor(Color.ORANGE));
//        marker2.backgroundStyle.addFill(new AppColor(Color.PINK));
//
////        marker1.affineTransform.prependTranslation(a,b);
////        marker2.affineTransform.prependTranslation(a,b);
//
//
//        for(int hhh=1;hhh<2;hhh++) {
//
//            print(" ================================ hhh = "+hhh);
//            appAxisChart.removeAllSeries();
//
//            AppSeries appSeries1 = new AppSeries();
//            AppSeries appSeries2 = new AppSeries();
//
//            appSeries1.plotArea.backgroundStyle.addFill(new AppColor(Color.valueOf("ff000022")));
//            appSeries2.plotArea.backgroundStyle.addFill(new AppColor(Color.valueOf("00ff0022")));
//
//
//
//
//            appSeries1.setMarkerShape(marker1);
//            appSeries2.setMarkerShape(marker2);
//
//            appAxisChart.addSeries(appSeries1);
////            appAxisChart.addSeries(appSeries2);
//
////            print(appSeries2.minX+" , "+appSeries2.maxX);
//
//            for (int i = 0; i < 1; i++) {
//                print("___________");
//                appSeries1.addData(Math.random()*0+i,Math.random());
////                appSeries2.addData(Math.random()*0+i,Math.random());
//            }
//
//            print("__________________________________________________________");
//
//            for (int i = 0; i < 1; i++) {
//                print("___________");
//                appSeries1.removeData(0);
////                appSeries1.addData(Math.random()*0+i,Math.random());
////                appSeries2.addData(Math.random()*0+i,Math.random());
//            }
//
////            appSeries
//
//
//
//            print(marker1.backgroundStyle);
//
//            container.setOnMouseDragged(mouseEvent -> {
//
//                double w=Math.floor(Math.random()*50)+300;
//                double h=Math.floor(Math.random()*150)+300;
//
//
//
//                appAxisChart.width.set(w);
//                appAxisChart.height.set(h);
//
//                appSeries1.title.setText(uuid(10));
//                appSeries2.title.setText(uuid(10));
//
//                appAxisChart.titleRegion.title.setText(uuid(20));
//
//                appAxisChart.getAppLegendTitleVisualMarginProperty().set(20*Math.random());
//
////                appAxisChart.xAx
//
////                appSeries1.dataList.set(0,new double[]{Math.random(),1+Math.random()});
//
//
//
//                print("width , height = "+w+" , "+h);
//
////                print(appAxisChart.legendRegion.widthProperty().get()+" , "+appAxisChart.legendRegion.heightProperty().get());
//
////                marker1.backgroundStyle.setFill(1,new AppColor(new Color(Math.random(),Math.random(),Math.random(),1)));
//
////                print(marker1.backgroundStyle);
//
//                AppRectangle appNode=new AppRectangle(28,28,0,0);
//                appNode.backgroundStyle.addFill(new AppColor(new Color(Math.random(),Math.random(),Math.random(),1)));
//                appNode.affineTransform.prependTranslation(-14,-14);
//
//                appSeries1.setMarkerShape(appNode);
//
//
////                print(appAxisChart.appGridLines.getChildren());
//
////                print(appAxisChart.appGridLines.toSVG(0));
//
//
////                print(appAxisChart.xAxisRegion.getChildren());
//
////                marker1.backgroundStyle.setFill(1,new AppColor(new Color(Math.random(),Math.random(),Math.random(),1)));
//
//
//
////                print(appAxisChart.appLegend.widthProperty().get());
//
////                print(appAxisChart.appLegend.bottomMargin.get());
////                print(appAxisChart.appLegend.container.widthProperty().get());
//
////                print(appAxisChart.appLegend.nw.widthProperty().get());
////                print(appAxisChart.appLegend.se.widthProperty().get());
//
////                print(appAxisChart.appLegend.width.get());
//            });
//
//        }


//        print(appAxisChart.legendRegion.getPrefWidth()+" , "+appAxisChart.legendRegion.getPrefHeight());

    }



}


