package com.boom.test.panels;

import com.boom.appcharts.AppAxisChartWrapper;
import com.boom.appcharts.baseclasses.AppAxisChart;
import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppRectangle;
import com.boom.panels.chart.ChartManagementPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChartManagementPanelTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HBox container=new HBox();
        Scene scene=new Scene(container,800,600);
        stage.setScene(scene);
        stage.show();

        AppAxisChartWrapper appAxisChartWrapper=new AppAxisChartWrapper(300,300);

        AppSeries appSeries1=new AppSeries();
        AppSeries appSeries2=new AppSeries();

        ((AppAxisChart) appAxisChartWrapper.wrappedNode).addSeries(appSeries1);
        ((AppAxisChart) appAxisChartWrapper.wrappedNode).addSeries(appSeries2);

        appSeries1.setMarkerShape(new AppRectangle(10,10,0,0));
        appSeries2.setMarkerShape(new AppEllipse(10,10));

        for(int i=0;i<3;i++) {
            appSeries1.addData(Math.random(), Math.random());
        }
        for(int i=0;i<3;i++) {
            appSeries2.addData(Math.random(), Math.random());
        }

//        appSeries1.markerShape.set();

        Rectangle emp=new Rectangle(100,300);
        emp.setFill(Color.TRANSPARENT);

        ChartManagementPanel chartManagementPanel=new ChartManagementPanel();
        container.getChildren().addAll(chartManagementPanel,emp,appAxisChartWrapper.wrappedNode);

        chartManagementPanel.registerChart(appAxisChartWrapper);

//        appSeries1.dataList.
//        appAxisChartWrapper.a



    }

    public static void main(String[] args) {
        launch(args);
    }

}
