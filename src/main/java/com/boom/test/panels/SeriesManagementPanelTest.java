package com.boom.test.panels;

import com.boom.appcharts.baseclasses.AppSeries;
import com.boom.appshapes.AppEllipse;
import com.boom.panels.chart.SeriesManagementPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;

public class SeriesManagementPanelTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HBox container = new HBox();
        Scene scene = new Scene(container, 800, 600);
        stage.setScene(scene);
        stage.show();

        AppSeries appSeries = new AppSeries();

//        print("phae 1");
        appSeries.setMarkerShape(new AppEllipse(1,1));
//        print("phae 2");
        for (int i = 0; i < 1; i++) {
//            print("phae 2."+i);
            appSeries.addData(Math.random(), Math.random());
        }
//        print("phae 3");
//        print(appSeries.markerShape.get());
        print(appSeries.renderedMarkers.getChildren());

        SeriesManagementPanel seriesManagementPanel=new SeriesManagementPanel();


        container.getChildren().add(seriesManagementPanel);

        seriesManagementPanel.registerSeries(appSeries);

        appSeries.changeIndicator.addListener((a,b,c)-> print(appSeries));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
