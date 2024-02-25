package com.boom.test.chartcomponentstests;

import com.boom.appcharts.baseclasses.AppGridLines;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Random;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;

public class AppGridLinesExportTest extends Application {

    public static void main(String[] args) {
        launch();
    }

    Random random=new Random();

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        ObservableMap<Double,String > xTicks= FXCollections.observableMap(new HashMap<>());
        ObservableMap<Double,String > yTicks= FXCollections.observableMap(new HashMap<>());
        SimpleDoubleProperty width=new SimpleDoubleProperty(200);
        SimpleDoubleProperty height=new SimpleDoubleProperty(200);

        AppGridLines appGridLines=new AppGridLines(width,height,xTicks,yTicks);

        container.getChildren().add(appGridLines);

        appGridLines.xGridLinesVisible.set(true);
        appGridLines.yGridLinesVisible.set(true);

        appGridLines.xGridLinesStrokeDashArray.setAll(10.0,5.0);
        appGridLines.yGridLinesStrokeDashArray.setAll(10.0,5.0);

        container.setOnMouseDragged(mouseEvent -> {
            width.set(200+Math.random()*100);
            height.set(200+Math.random()*100);

//            appGridLines.affineTransform.prependRotation(30*random.nextDouble()-15);

            print("width , height = "+width.get()+" , "+height.get());

            int nX=random.nextInt(4,10);
            int nY=random.nextInt(4,10);

            xTicks.clear();
            yTicks.clear();

            for(int i=0;i<nX;i++) {
                xTicks.put(1.0*i/(nX-1)*width.get(),uuid(20));
            }
            for(int i=0;i<nY;i++) {
                yTicks.put(1.0*i/(nY-1)*height.get(),uuid(20));
            }

            print("___________________________________________________________");

            print("<svg width=\"1100\" height=\"1100\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");

//            print("<defs>");
//            print(
//                    String.join(",", appLegendRegion.appSeriesVisualLegends.stream().map(appNodeSimpleObjectProperty ->
//                            appNodeSimpleObjectProperty.get().backgroundStyle.fillsToSVG(0)
//                    ).toList()) +
//                            String.join(",", appLegendRegion.appSeriesVisualLegends.stream().map(appNodeSimpleObjectProperty ->
//                                    appNodeSimpleObjectProperty.get().backgroundStyle.strokesToSVG(0)
//                            ).toList())
//            );
//            print("</defs>");

            print(appGridLines.toSVG(0));

            print("</svg>");

        });


//        appGridLines.

//        AppSeries appSeries1 = new AppSeries();
//        AppSeries appSeries2 = new AppSeries();
//        AppSeries appSeries3 = new AppSeries();
//        AppSeries appSeries4 = new AppSeries();
//
////        appSeries1.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));
////        appSeries2.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));
////        appSeries3.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));
////        appSeries4.bindVisualBounds((new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0),(new SimpleDoubleProperty()).add(0));
//
//        AppEllipse marker1 = new AppEllipse(20, 30);
//        AppEllipse marker2 = new AppEllipse(20, 30);
//        AppEllipse marker3 = new AppEllipse(20, 30);
//        AppEllipse marker4 = new AppEllipse(20, 30);
////        marker1.affineTransform.prependTranslation(20,20);
////        marker2.affineTransform.prependTranslation(20,20);
////        marker3.affineTransform.prependTranslation(20,20);
////        marker4.affineTransform.prependTranslation(20,20);
//        marker1.backgroundStyle.setFill(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(0.5, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(1, new Color(Math.random(), Math.random(), Math.random(), 1))
//        )));
//        marker2.backgroundStyle.setFill(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(0.5, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(1, new Color(Math.random(), Math.random(), Math.random(), 1))
//        )));
//        marker3.backgroundStyle.setFill(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(0.5, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(1, new Color(Math.random(), Math.random(), Math.random(), 1))
//        )));
//        marker4.backgroundStyle.setFill(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(0.5, new Color(Math.random(), Math.random(), Math.random(), 1)),
//                new Stop(1, new Color(Math.random(), Math.random(), Math.random(), 1))
//        )));
//        appSeries1.setMarkerShape(marker1);
//        appSeries2.setMarkerShape(marker2);
//        appSeries3.setMarkerShape(marker3);
//        appSeries4.setMarkerShape(marker4);
//
//        for (int i = 0; i < 10; i++) {
//            appSeries1.addData(i, Math.random(), Math.random());
//            appSeries2.addData(i, Math.random(), Math.random());
//            appSeries3.addData(i, Math.random(), Math.random());
//            appSeries4.addData(i, Math.random(), Math.random());
//        }
//
//        appSeries1.title.setText(uuid(20));
//        appSeries2.title.setText(uuid(20));
//        appSeries3.title.setText(uuid(20));
//        appSeries4.title.setText(uuid(20));
//
//        appSeries1.title.setFont(Font.font(50));
//        appSeries2.title.setFont(Font.font(50));
//        appSeries3.title.setFont(Font.font(50));
//        appSeries4.title.setFont(Font.font(50));
//
//
////        AppRectangle
////        appSeries1.setMarkerShape();
//
//        AppLegendRegion appLegendRegion = new AppLegendRegion();
//
//        appLegendRegion.addSeries(0, appSeries1.getVisualLegend(), appSeries1.title);
//        appLegendRegion.addSeries(1, appSeries2.getVisualLegend(), appSeries2.title);
//        appLegendRegion.addSeries(2, appSeries3.getVisualLegend(), appSeries3.title);
//        appLegendRegion.addSeries(3, appSeries4.getVisualLegend(), appSeries4.title);
//
//        container.getChildren().add(appLegendRegion);
//
//        container.setOnMouseDragged(mouseEvent -> {
////            appLegendRegion.topMargin.set(Math.random() / 10 + 0.1);
////            appLegendRegion.bottomMargin.set(Math.random() / 10 + 0.1);
////            appLegendRegion.leftMargin.set(Math.random() / 10 + 0.1);
////            appLegendRegion.rightMargin.set(Math.random() / 10 + 0.1);
//
////            appLegendRegion.affineTransform.prependScale(Math.random()*0.2+0.8,Math.random()*0.2+0.8);
//
////            appLegendRegion.affineTransform.prependRotation(Math.random()*30-15);
//
////            print(appLegendRegion.toJSON());
////            print(appLegendRegion.toSVG(0));
////
////            print("width = "+appLegendRegion.getWidth());
////            print("height = "+appLegendRegion.getHeight());
////
////            print("container width = "+appLegendRegion.container.getWidth());
////            print("container height = "+appLegendRegion.container.getHeight());
//
////            print(appLegendRegion.container.getChildren().get(0).getBoundsInParent());
////            print(appLegendRegion.container.getChildren().get(3).getBoundsInParent());
////            print(appLegendRegion.container.getChildren().get(6).getBoundsInParent());
////            print(appLegendRegion.container.getChildren().get(9).getBoundsInParent());
//
////            print(appLegendRegion.container.getChildren().size());
////
////            print(appLegendRegion.nw);
//
//
//            print("___________________________________________________________");
//
//            print("<svg width=\"1100\" height=\"1100\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
//
//            print("<defs>");
//            print(
//                    String.join(",", appLegendRegion.appSeriesVisualLegends.stream().map(appNodeSimpleObjectProperty ->
//                            appNodeSimpleObjectProperty.get().backgroundStyle.fillsToSVG(0)
//                    ).toList()) +
//                            String.join(",", appLegendRegion.appSeriesVisualLegends.stream().map(appNodeSimpleObjectProperty ->
//                                    appNodeSimpleObjectProperty.get().backgroundStyle.strokesToSVG(0)
//                            ).toList())
//            );
//            print("</defs>");
//
//            print(appLegendRegion.toSVG(0));
//
//            print("</svg>");
//
////            appLegendRegion.appSeriesTitles.forEach(title->{
////                print(title.getBoundsInParent());
////            });
////            for (int i = 0; i < appLegendRegion.appSeriesVisualLegends.size(); i++) {
////                AppNode appNode=appLegendRegion.appSeriesVisualLegends.get(i).get().copy();
////                print(appNode.affineTransform);
////                print(appNode.styleableNode.getBoundsInParent());
//////                print(appLegendRegion.container.getBoundsInParent().getMinX()+" , "+appLegendRegion.container.getBoundsInParent().getMinY());
////                print(appLegendRegion.nw.getWidth()+" , "+appLegendRegion.nw.getHeight());
//////                AppNode tempAppNode=appLegendRegion.container.getChildren().get(i);
////            }
//        });
//
//
//
//        appLegendRegion.backgroundStyle.addFill(new AppColor(new Color(1,0,1,0.2)));
////
//////        appLegendRegion.
////
////
////        appLegendRegion.appSeriesVisualLegends.forEach(appNodeSimpleObjectProperty -> {
////            print(appNodeSimpleObjectProperty.get().backgroundStyle.fillsToSVG(0));
////        });
////
////        appLegendRegion.appSeriesVisualLegends.forEach(appNodeSimpleObjectProperty -> {
////            print(appNodeSimpleObjectProperty.get().backgroundStyle.strokesToSVG(0));
////        });
////
////        appLegendRegion.appSeriesVisualLegends.forEach(appNodeSimpleObjectProperty -> {
//////            print(appNodeSimpleObjectProperty.get().border.getX());
////            print(appNodeSimpleObjectProperty.get().toSVG(0));
////        });
////
////        print("width = "+appLegendRegion.getWidth());
////        print("height = "+appLegendRegion.getHeight());
////
////        print("container width = "+appLegendRegion.container.getWidth());
////        print("container height = "+appLegendRegion.container.getHeight());
////
////
////        print(appLegendRegion.nw+" , "+appLegendRegion.nw.getHeight());
////        print(appLegendRegion.se.getWidth()+" , "+appLegendRegion.se.getHeight());
////        print();
//
//
////        appLegendRegion


//        GridPane

//        print(appLegendRegion.toJSON());

//        print(appLegendRegion.toSVG(0));
//        appLegend.topMargin.set(20);
    }
}