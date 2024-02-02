package com.boom.test;

import com.boom.appcharts.AppAxisChart;
import com.boom.appcharts.AppSeries;
import com.boom.apppaints.AppColor;
import com.boom.appshapes.AppEllipse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;


public class AppAxisChartTest extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        AppAxisChart appAxisChart=new AppAxisChart();

        appAxisChart.plotRegionWidth.set(400);
        appAxisChart.plotRegionHeight.set(400);

        appAxisChart.setTranslateX(100);
        appAxisChart.setTranslateY(100);

        container.getChildren().add(appAxisChart);


        double a = 7, b = 14;
        AppEllipse marker1 = new AppEllipse(a, b);
        AppEllipse marker2 = new AppEllipse(b, a);
        marker1.backgroundStyle.addFill(new AppColor(Color.ORANGE));
        marker2.backgroundStyle.addFill(new AppColor(Color.PINK));


        for(int hhh=1;hhh<2;hhh++) {

            print(" ================================ hhh = "+hhh);
            appAxisChart.removeAllSeries();

            AppSeries appSeries1 = new AppSeries();
            AppSeries appSeries2 = new AppSeries();

            appSeries1.plotArea.backgroundStyle.addFill(new AppColor(Color.valueOf("ff000022")));
            appSeries2.plotArea.backgroundStyle.addFill(new AppColor(Color.valueOf("00ff0022")));


            appSeries1.setMarkerShape(marker1);
            appSeries2.setMarkerShape(marker2);

            appAxisChart.addSeries(appSeries1);
            appAxisChart.addSeries(appSeries2);

            for (int i = 0; i < 11; i++) {
                appSeries1.addData(Math.random(), Math.random() );
                appSeries2.addData(Math.random(), Math.random() );
//                appSeries1.addData(i,i);
//                appSeries2.addData(Math.random(), Math.random() * 30);
            }

            container.setOnMouseDragged(mouseEvent -> {
                appAxisChart.plotRegionWidth.set(Math.random() * 50 + 300);
                appAxisChart.plotRegionHeight.set(Math.random() * 150 + 300);
            });

        }
    }


}


