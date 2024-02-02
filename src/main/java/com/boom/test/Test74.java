package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.boom.tools.Tools.print;


public class Test74 extends Application {

    public static void main(String[] args) {
        launch();
    }

    Random rnd = new Random();
//    DataComparator dataComparator = new DataComparator();

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
//        stage.show();

        Comparator<Double> comparator = Comparator.naturalOrder();

        List<Double> x = Arrays.asList(Math.random(),Math.random());
        x.sort(comparator);



//        x.set(0, 1.);
//        x.set(1, 100.);

//        print(x);
//
//        print(getScientificRepresentation(x.get(1)-x.get(0),20));
//
//        print("_____________________________________");
//        print(getScientificRepresentation(x.get(0),0)[0]);
//        print(getScientificRepresentation(x.get(1),0)[0]);
//
//        print(Math.floor(getScientificRepresentation(x.get(0),0)[0]));
//        print(Math.ceil(getScientificRepresentation(x.get(1),0)[0]));

        double topMargin = .1, bottomMargin = .1;
        double height = 100, h = 10;

        int n = (int) Math.floor((1 - topMargin - bottomMargin) * height / h);

//        n=10;
//        print(n);
//
//        print(x.get(0));

        print(x);
        print("n = "+n);
//        print("____________________");
//        _TEMP1_(x.get(0),x.get(1));

        double m = x.get(0), M = x.get(1);

        int l1, l2;

        double tickMarginDistance=Double.MAX_VALUE;
        int bestK=Integer.MAX_VALUE;
        int bestP=Integer.MAX_VALUE;
        int bestL1=Integer.MAX_VALUE;
        int bestL2=Integer.MAX_VALUE;
        int bestNumberOfTicks=0;

        for (int k : new int[]{1, 2, 4, 5}) {
            int pNess = (int) Math.ceil(Math.log10(1.0 * (n - 1) / k / (M - m)));
            for (int p = pNess; ; p--) {
                l1 = (int) Math.floor(m * k * Math.pow(10, p));
                l2 = (int) Math.ceil(M * k * Math.pow(10, p));
                if(l2-l1==1){
                    break;
                }
//                actualNumberOfTicks=l2-l1+1;
                if (l2-l1+1 <= n && l2-l1+1>bestNumberOfTicks) {
//                    print("______________________");
//                    print("L1 , L2 = " + l1 + " , " + l2);
////                    print(l2 - l1 + 1 <= n);
//                    for (int j = l1; j <= l2; j++) {
//                        print(1.0 * j / k / Math.pow(10, p));
//                    }
                    if(m-M+1.0 *(l2- l1) / k / Math.pow(10, p)<=tickMarginDistance){
                        tickMarginDistance=m-M+1.0 *(l2- l1) / k / Math.pow(10, p);
                        bestK=k;
                        bestP=p;
                        bestL1=l1;
                        bestL2=l2;
                        bestNumberOfTicks=l2-l1+1;
                    }
//                    break;
                }
            }
        }

        print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        print(bestK);
//        print(bestP);
//        print(bestL1);
//        print(bestL2);
        for(int j=bestL1;j<=bestL2;j++){
            print(1.0*j/bestK/Math.pow(10,bestP));
        }

//        print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");




    }


}

