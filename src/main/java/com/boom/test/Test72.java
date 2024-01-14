package com.boom.test;

import com.boom.appcharts.AppAxisChart;
import com.boom.appcharts.AppData;
import com.boom.structures.enums.AppAxisSortingPolicy;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Test72 extends Application {

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

        AppAxisChart appAxisChart=new AppAxisChart();
        AppAxisChart appAxisChart1=new AppAxisChart();
        container.getChildren().addAll(appAxisChart);

//        setCustomSize(appAxisChart,500,500);
//        setCustomSize(appAxisChart1,500,500);
        appAxisChart.plotRegionWidth.set(400);
        appAxisChart.plotRegionHeight.set(400);

        appAxisChart1.plotRegionWidth.set(400);
        appAxisChart1.plotRegionHeight.set(400);

        appAxisChart.xAxisRegionHeight.set(50);
        appAxisChart.yAxisRegionWidth.set(50);

        appAxisChart1.setTranslateX(400);

//        appAxisChart.setBackground(Background.fill(new Color(0,0,1,0.2)));
//        appAxisChart.setBorder(Border.stroke(Color.BLACK));
//        appAxisChart1.setBorder(Border.stroke(Color.BLACK));

        appAxisChart.addSeries();
        appAxisChart1.addSeries();
//        appAxisChart.addSeries();

        List<AppData> appDataList =new ArrayList<>();
//        appDataList.add(new AppData("0","0"));
//        appDataList.add(new AppData("1","1"));
//        appDataList.add(new AppData("2","0"));
        for(int i=0;i<9;i++){
            appDataList.add(new AppData((i+rnd.nextDouble()/1000)+"as",(i+rnd.nextDouble()/0.2)+""));
//            appDataList1.add(new AppData((i+rnd.nextDouble()/0.2)+"",(i+rnd.nextDouble()/0.2)+""));
//            appDataList.add(new AppData(rnd.nextDouble() + "", rnd.nextDouble() + ""));
//            appDataList1.add(new AppData(rnd.nextDouble() + "", rnd.nextDouble() + ""));
        }

        appAxisChart.addManyData(0,0, appDataList);
        appAxisChart1.addManyData(0,0, appDataList);
//        appAxisChart.addManyData(1,0, appDataList1);

        appAxisChart.seriesPlotAreaList.get(0).setFill(new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,
                new Stop(0,Color.valueOf("ff000088")),new Stop(1,Color.valueOf("0000ff88"))));
        appAxisChart1.seriesPlotAreaList.get(0).setFill(new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,
                new Stop(0,Color.valueOf("ff000088")),new Stop(1,Color.valueOf("0000ff88"))));
//        appAxisChart.seriesPlotAreaList.get(1).setFill(new LinearGradient(0,0,0,1,true, CycleMethod.NO_CYCLE,
//                new Stop(0,Color.valueOf("ffff0088")),new Stop(1,Color.valueOf("00ff0088"))));

//        container.setOnMouseDragged(mouseEvent -> {
//            appAxisChart.plotRegionWidth.set(300+100*rnd.nextDouble());
//            appAxisChart.plotRegionHeight.set(300+100*rnd.nextDouble());
//        });

        appAxisChart.setXNumeric(false);
        appAxisChart.setYNumeric(true);
        appAxisChart.setAxisSortingPolicy(AppAxisSortingPolicy.SortByX);

        appAxisChart1.setXNumeric(false);
        appAxisChart1.setYNumeric(true);
        appAxisChart1.setAxisSortingPolicy(AppAxisSortingPolicy.SortByY);




    }


}
