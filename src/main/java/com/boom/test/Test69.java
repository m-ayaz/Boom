package com.boom.test;

import com.boom.appcharts.AppAxisChart;
import com.boom.appcharts.AppData;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.setCustomSize;


public class Test69 extends Application {

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

        Polyline polyline=new Polyline();

        container.getChildren().add(polyline);




        Group group=new Group();

        container.getChildren().add(group);

        Circle c1=new Circle(10,Color.RED);
        Circle c2=new Circle(10,Color.RED);
        Circle c3=new Circle(10,Color.RED);



        group.getChildren().addAll(c1,c2,c3);
//        group.getChildren().add(new Circle(50,50,10,Color.RED));
//        group.getChildren().add(new Circle(300,100,10,Color.RED));

        c1.setTranslateX(20);
        c1.setTranslateY(20);

        c2.setTranslateX(50);
        c2.setTranslateY(50);

        c3.setTranslateX(300);
        c3.setTranslateY(100);

//
//        group.getChildren().add()

//        AppAxisChart appAxisChart=new AppAxisChart();
//        container.getChildren().add(appAxisChart);
//
//        setCustomSize(appAxisChart,500,500);
//        appAxisChart.plotRegionWidth.set(400);
//        appAxisChart.plotRegionHeight.set(400);
//
////        appAxisChart.setBackground(Background.fill(new Color(0,0,1,0.2)));
//        appAxisChart.setBorder(Border.stroke(Color.BLACK));
//
//        appAxisChart.addSeries();
//        appAxisChart.addSeries();
//
//        List<AppData> appDataList =new ArrayList<>();
//        List<AppData> appDataList1 =new ArrayList<>();
//        for(int i=0;i<100;i++){
//            appDataList.add(new AppData(rnd.nextDouble(0.5,1) + "", rnd.nextDouble(0.5,1) + ""));
//            appDataList1.add(new AppData(rnd.nextDouble(0.5,1) + "", rnd.nextDouble(0.5,1) + ""));
//        }
//
//        appAxisChart.addManyData(0,0, appDataList);
//        appAxisChart.addManyData(1,0, appDataList1);
//
//        appAxisChart.seriesPlotAreaList.get(0).setFill(new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,
//                new Stop(0,Color.valueOf("ff000088")),new Stop(1,Color.valueOf("0000ff88"))));
//        appAxisChart.seriesPlotAreaList.get(1).setFill(new LinearGradient(0,0,0,1,true, CycleMethod.NO_CYCLE,
//                new Stop(0,Color.valueOf("ffff0088")),new Stop(1,Color.valueOf("00ff0088"))));
//
//        container.setOnMouseMoved(mouseEvent -> {
//            appAxisChart.plotRegionWidth.set(300+100*rnd.nextDouble());
//            appAxisChart.plotRegionHeight.set(300+100*rnd.nextDouble());
//        });
//



    }


}
