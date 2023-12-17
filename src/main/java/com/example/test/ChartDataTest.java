package com.example.test;

import com.example.appcharts.number_number.AppAreaChart_NumberNumber;
import com.example.appcharts.number_string.AppAreaChart_NumberString;
import com.example.appcharts.string_number.AppScatterChart_StringNumber;
import com.example.configuration.Configs;
import com.example.panels.chart.number_number.ChartManagementPane_NumberNumber;
import com.example.panels.chart.number_string.ChartManagementPane_NumberString;
import com.example.panels.chart.string_number.ChartManagementPane_StringNumber;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.tools.Tools.*;

public class ChartDataTest extends Application {
    @Override
    public void start(Stage stage_NN) {

        Configs.setDefaultConfig();

        Random rnd=new Random();

        double a=400,b=400;

        AppAreaChart_NumberNumber lineChart1=new AppAreaChart_NumberNumber(a,a);
        AppAreaChart_NumberString lineChart2=new AppAreaChart_NumberString(a,a);
        AppScatterChart_StringNumber lineChart3=new AppScatterChart_StringNumber(a,a);



        lineChart1.addSeries();
        lineChart2.addSeries();
        lineChart3.addSeries();



//        setSize(lineChart1,a,a);
//        setSize(lineChart2,a,a);
//        setSize(lineChart3,a,a);
        for(int j=0;j<9;j++) {
            lineChart1.addSeries(j);
            lineChart2.addSeries(j);
            lineChart3.addSeries(j);
            for (int iii = 0; iii < 10; iii++) {
//                print(iii+","+j);
                lineChart1.addData(rnd.nextDouble(), rnd.nextDouble(), j);
                lineChart2.addData(rnd.nextDouble(), uuid(5), j);
                lineChart3.addData(uuid(5), rnd.nextDouble(), j);
//            lineChart2.getData().get(0).getData().add(new XYChart.Data<>(rnd.nextDouble(), uuid(10)));
//            lineChart3.getData().get(0).getData().add(new XYChart.Data<>(uuid(10),rnd.nextDouble()));
            }

//            Color c=Color.valueOf("ff00ff");
//            print(c.getOpacity());
        }

//        for(int j=0;j<20;j++) {
//            lineChart1.addSeries(j);
//        }

        ChartManagementPane_NumberNumber chartSeriesPane_NN=new ChartManagementPane_NumberNumber();
        ChartManagementPane_NumberString chartSeriesPane_NS=new ChartManagementPane_NumberString();
        ChartManagementPane_StringNumber chartSeriesPane_SN=new ChartManagementPane_StringNumber();


        setCustomSize(chartSeriesPane_NN,b,b);
        setCustomSize(chartSeriesPane_NS,b,b);
        setCustomSize(chartSeriesPane_SN,b,b);

        chartSeriesPane_NN.registerChart(lineChart1);
        chartSeriesPane_NS.registerChart(lineChart2);
        chartSeriesPane_SN.registerChart(lineChart3);


        VBox vBox_NN=new VBox(chartSeriesPane_NN,lineChart1.getNode());
        VBox vBox_NS=new VBox(chartSeriesPane_NS,lineChart2.getNode());
        VBox vBox_SN=new VBox(chartSeriesPane_SN,lineChart3.getNode());





        Scene scene_NN=new Scene(vBox_NN);
        Scene scene_NS=new Scene(vBox_NS);
        Scene scene_SN=new Scene(vBox_SN);
//        Scene scene_SS=new Scene(vBox_SS);



        Stage stage_SN=new Stage();
        Stage stage_NS=new Stage();


        stage_NN.setScene(scene_NN);
        stage_NS.setScene(scene_NS);
        stage_SN.setScene(scene_SN);

        stage_NN.setTitle("Number Number");
        stage_NS.setTitle("Number String");
        stage_SN.setTitle("String Number");

        stage_NN.show();

//        lineChart1.node..fill.set(Color.RED);
//        stage_NS.show();
//        stage_SN.show();

//        lineChart1.node.lookup(".chart-plot-background").setStyle("-fx-border-color: red;");
        ObservableList<Axis.TickMark<Number>> g=((XYChart<Number,Number>)lineChart1.getNode()).getXAxis().getTickMarks();

//        ;

//        Collectors.(",");

//        Color c=Color.RED;

//        print(c.);



        print("{"+g.stream().map(obj->obj.getValue().toString()).collect(Collectors.joining())+"}");

        print("{"+((XYChart<Number,Number>)lineChart1.getNode()).getData().get(0).getData().stream().map(obj->"("+obj.getXValue()+","+obj.getYValue()+")").collect(Collectors.joining())+"}");

        ((XYChart<Number,Number>)lineChart1.getNode()).getXAxis().getTickMarks().forEach(obj->{
//            print("====================================");
//            print(obj.getLabel());
//            print(obj.getValue());
//            obj.setLabel(obj.getValue().toString());
//            print();
//            print(obj.getPosition());
        });
//        print(((XYChart<Number,Number>)lineChart1.node).getXAxis().getTickMarks());


        print(((XYChart<Number,Number>)lineChart1.getNode()).getData().get(0).getData());

        XYChart.Series<Number,Number> series= ((XYChart<Number,Number>)lineChart1.getNode()).getData().get(0);

        print(series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()));
        print(series.getData().sorted().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()));
        print(series.getData().stream().map(obj -> "(" + obj.getXValue() + "," + obj.getYValue() + ")").collect(Collectors.joining()));

//        print("=====================");
//        print(((Path)((XYChart<Number,Number>)lineChart1.node).getData().get(0).getNode().lookup(".chart-series-area-fill")).getFill());
//        print(((Path)((XYChart<Number,Number>)lineChart1.node).getData().get(0).getNode().lookup(".chart-series-area-fill")).getStroke());
//        print("=====================");
//        print(((Path)((XYChart<Number,Number>)lineChart1.node).getData().get(0).getNode().lookup(".chart-series-area-line")).getFill());
//        print(((Path)((XYChart<Number,Number>)lineChart1.node).getData().get(0).getNode().lookup(".chart-series-area-line")).getStroke());
//        lineChart1.

//        print(series.get);
        List<String> k=new ArrayList<>();
        for(int j=0;j<((XYChart<Number, Number>) lineChart1.getNode()).getData().size();j++){
            print("=================");
            print(((Path) ((XYChart<Number, Number>) lineChart1.getNode()).getData().get(j).getNode().lookup(".chart-series-area-line")).getStroke());
            print(((Path) ((XYChart<Number, Number>) lineChart1.getNode()).getData().get(j).getNode().lookup(".chart-series-area-fill")).getFill());
            k.add(((Path) ((XYChart<Number, Number>) lineChart1.getNode()).getData().get(j).getNode().lookup(".chart-series-area-line")).getStroke().toString());
        }

        print(k.size());
        print(k);

//        HashMap<String,String> u=new HashMap<>();
//
//        u.put("a","b");
//
//        String j="asas";
//        switch (j){
//            case u.get("a")->{int x=1;}
//
//            case "u.get(1)-"->{int yyy=4;}
//
//        }


    }

    public static void main(String[] args){
        launch(args);
    }

}
