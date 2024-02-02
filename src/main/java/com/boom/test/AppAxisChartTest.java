package com.boom.test;

import com.boom.appcharts.AppAxisChart;
import com.boom.appcharts.AppSeries;
import com.boom.apppaints.AppColor;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppRectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;


public class AppAxisChartTest extends Application {

    public static void main(String[] args) {
        launch();
    }
    Random rnd=new Random();
//    DataComparator dataComparator=new DataComparator();

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
//        AppSeries appSeries3=new AppSeries();

            appAxisChart.addSeries(appSeries1);
            appAxisChart.addSeries(appSeries2);
//        appAxisChart.addSeries(appSeries3);



//        appSeries1.minXVisualLocation.set(0);
//        appSeries1.maxYVisualLocation.set(0);
//
//        appSeries1.maxXVisualLocation.set(400);
//        appSeries1.minYVisualLocation.set(400);
//
//        appSeries2.minXVisualLocation.set(0);
//        appSeries2.maxYVisualLocation.set(0);
//
//        appSeries2.maxXVisualLocation.set(400);
//        appSeries2.minYVisualLocation.set(400);

//        appSeries1.addData(new AppData("-7","-6.4"));
//        appSeries1.addData(new AppData("2","1.4"));
//        appSeries1.addData(new AppData("6","2.4"));

//            print(appSeries1.markerShape);
//            print(appSeries2.markerShape);

            for (int i = 0; i < 11; i++) {
                appSeries1.addData(Math.random(), Math.random() );
                appSeries2.addData(Math.random(), Math.random() );
//                appSeries1.addData(i,i);
//                appSeries2.addData(Math.random(), Math.random() * 30);
            }

//            AppRectangle appRectangle = new AppRectangle(20, 20, 0, 0);
//            appRectangle.backgroundStyle.addFill(new AppColor(Color.valueOf("00ff0022")));
//            appRectangle.affineTransform.prependTranslation(-10, -10);
//            appRectangle.affineTransform.prependRotation(45);
//            appSeries1.setMarkerShape(appRectangle);

//            print(appAxisChart.globalMinY.get());
//            print(appAxisChart.globalMaxY.get());


//            print("77777777777777777777777777777777777777777777");



//            print("appSeries1.appDataList = "+appSeries1.appDataList.stream().map(doubles -> "("+doubles[0]+" , "+doubles[1]+")").toList());







//        if(true){
//            throw new AppException(AppExceptionEnum.UnexpectedError);
//        }

//        appSeries1.addData(new AppData("-4","5"));
//        appSeries1.addData(new AppData("-2","1"));
//        appSeries1.addData(new AppData("0","3"));
//        appSeries1.addData(new AppData("0","0"));
//        appSeries1.addData(new AppData("1","1"));
//
//        appSeries2.addData(new AppData("0","2"));
////        appSeries2.addData(new AppData("-1","1"));
////        appSeries2.addData(new AppData("1","3"));
//        appSeries2.addData(new AppData("2","0"));


//        print("+++++++++++++++++++++++");
//        print(appAxisChart.globalMinX.get()+" , "+appAxisChart.globalMaxX.get());
//        print(appAxisChart.globalMinY.get()+" , "+appAxisChart.globalMaxY.get());
//
//        print("------------------------ after data");
//
//
//        print(appSeries1.plotArea.points);


//        appSeries2.addData(new AppData("-2.34","-7.4"));
//        appSeries2.addData(new AppData("1.34","3.4"));
//        appSeries2.addData(new AppData("5","2.4"));

//        print(appAxisChart);


            container.setOnMouseDragged(mouseEvent -> {
                appAxisChart.plotRegionWidth.set(Math.random() * 50 + 300);
                appAxisChart.plotRegionHeight.set(Math.random() * 150 + 300);
//            appAxisChart.updateYTicks();
            });

//            print(uuid(200));


//            appAxisChart.updateYTicks();

//            print(appAxisChart.yTicksPositions);


//        print(appAxisChart.setXNumeric(true)?"X = OK":"X = NOT OK");
//        print(appAxisChart.setYNumeric(true)?"Y = OK":"Y = NOT OK");


//        for (int i = 0; i < appSeries1.plotLine.points.size(); i+=2) {
//            print(appSeries1.plotLine.points.get(i)+" , "+appSeries1.plotLine.points.get(i+1));
//        }
////        print("______________________________");
////        appSeries1.plotArea.points.forEach(p->print(p+" , "));
//
//        print("______________________________");
//
//        for (int i = 0; i < appSeries2.plotLine.points.size(); i+=2) {
//            print(appSeries2.plotLine.points.get(i)+" , "+appSeries2.plotLine.points.get(i+1));
//        }
//        print("______________________________");
//        appSeries2.plotArea.points.forEach(p->print(p+" , "));
//        appSeries1.previewCoordinatesList.forEach(doubles -> print(doubles[0]+" , "+doubles[1]));


//        appSeries1.updatePreviewAtChart();
//        appSeries2.updatePreviewAtChart();


//        print(appSeries1.globalMinX);
//        print(appSeries1.globalMaxX);
//        print(appSeries1.globalMinY);
//        print(appSeries1.globalMaxY);
//
//        print(appSeries2.globalMinX);
//        print(appSeries2.globalMaxX);
//        print(appSeries2.globalMinY);
//        print(appSeries2.globalMaxY);


//        appAxisChart.addSeries(0);

//        appAxisChart.


//        List<SimpleDoubleProperty> list=new ArrayList<>();
//
//        SimpleDoubleProperty x1=new SimpleDoubleProperty();
//        SimpleDoubleProperty x2=new SimpleDoubleProperty();
////        SimpleDoubleProperty x1=new SimpleDoubleProperty();
//
//        list.add(x1);
//        list.add(x2);
//
//        SimpleDoubleProperty k=list.stream().sorted().toList().get(0);
//
//        k.addListener((a,b,c)->{
//            print("___________________");
//            print("x1 = "+x1.get());
//            print("x2 = "+x2.get());
//            print("min = "+k.getValue());
//        });
//
//        container.setOnMouseDragged(mouseEvent -> {
//            x1.set(Math.random());
//            x2.set(Math.random());
//        });
//
//
//
//    }
//
//    class Comp implements Comparator<SimpleDoubleProperty>{
//
//        @Override
//        public int compare(SimpleDoubleProperty o1, SimpleDoubleProperty o2) {
//            print("compare = "+o1.getValue().compareTo(o2.getValue()));
//            return o1.getValue().compareTo(o2.getValue());
//        }
//        }
        }
    }


}


