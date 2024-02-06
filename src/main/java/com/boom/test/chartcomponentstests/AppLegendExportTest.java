package com.boom.test.chartcomponentstests;

import com.boom.appcharts.AppLegendRegion;
import com.boom.appcharts.AppSeries;
import com.boom.apppaints.AppLinearGradient;
import com.boom.appshapes.AppRectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;

public class AppLegendExportTest extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        AppSeries appSeries1=new AppSeries();
        AppSeries appSeries2=new AppSeries();
        AppSeries appSeries3=new AppSeries();
        AppSeries appSeries4=new AppSeries();

//        appSeries1.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));
//        appSeries2.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));
//        appSeries3.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));
//        appSeries4.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));

        AppRectangle marker1=new AppRectangle(20,20,0,0);
        AppRectangle marker2=new AppRectangle(20,20,0,0);
        AppRectangle marker3=new AppRectangle(20,20,0,0);
        AppRectangle marker4=new AppRectangle(20,20,0,0);
        marker1.backgroundStyle.addFill(new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                new Stop(0,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(0.5,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(1,new Color(Math.random(),Math.random(),Math.random(),1))
        )));
        marker2.backgroundStyle.addFill(new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                new Stop(0,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(0.5,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(1,new Color(Math.random(),Math.random(),Math.random(),1))
        )));
        marker3.backgroundStyle.addFill(new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                new Stop(0,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(0.5,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(1,new Color(Math.random(),Math.random(),Math.random(),1))
        )));
        marker4.backgroundStyle.addFill(new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                new Stop(0,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(0.5,new Color(Math.random(),Math.random(),Math.random(),1)),
                new Stop(1,new Color(Math.random(),Math.random(),Math.random(),1))
        )));
        appSeries1.setMarkerShape(marker1);
        appSeries2.setMarkerShape(marker2);
        appSeries3.setMarkerShape(marker3);
        appSeries4.setMarkerShape(marker4);

        for(int i=0;i<10;i++){
            appSeries1.addData(i,Math.random(),Math.random());
            appSeries2.addData(i,Math.random(),Math.random());
            appSeries3.addData(i,Math.random(),Math.random());
            appSeries4.addData(i,Math.random(),Math.random());
        }

        appSeries1.title.setText(uuid(20));
        appSeries2.title.setText(uuid(20));
        appSeries3.title.setText(uuid(20));
        appSeries4.title.setText(uuid(20));


//        AppRectangle
//        appSeries1.setMarkerShape();

        AppLegendRegion appLegendRegion =new AppLegendRegion();

        appLegendRegion.addSeries(0,appSeries1.getVisualLegend(),appSeries1.title);
        appLegendRegion.addSeries(1,appSeries2.getVisualLegend(),appSeries2.title);
        appLegendRegion.addSeries(2,appSeries3.getVisualLegend(),appSeries3.title);
        appLegendRegion.addSeries(3,appSeries4.getVisualLegend(),appSeries4.title);

        container.getChildren().add(appLegendRegion);

        container.setOnMouseDragged(mouseEvent -> {
            appLegendRegion.topMargin.set(Math.random()/10+0.1);
            appLegendRegion.bottomMargin.set(Math.random()/10+0.1);
            appLegendRegion.leftMargin.set(Math.random()/10+0.1);
            appLegendRegion.rightMargin.set(Math.random()/10+0.1);

            print(appLegendRegion.toJSON());
            print(appLegendRegion.toSVG(0));
        });


//        GridPane

//        print(appLegendRegion.toJSON());

//        print(appLegendRegion.toSVG(0));
//        appLegend.topMargin.set(20);
    }
}
