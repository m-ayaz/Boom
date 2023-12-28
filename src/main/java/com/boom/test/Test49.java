package com.boom.test;

import com.boom.icons.PolygonIcon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;


public class Test49 extends Application {

    public static void main(String[] args) {
        launch();
    }

//    boolean x=true;

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        CubicCurve cubicCurve=new CubicCurve();
        container.getChildren().addAll(cubicCurve);

//        Line line=new Line();
//        line.setF

        Region region=new Region();

        cubicCurve.setStroke(new Color(1,0,1,1));
        cubicCurve.setFill(new Color(1,0,1,0));

        cubicCurve.setStrokeLineCap(StrokeLineCap.ROUND);

        cubicCurve.setStrokeWidth(10);
        cubicCurve.setStartX(400);
        cubicCurve.setStartY(200);


        cubicCurve.setControlX1(300);
        cubicCurve.setControlY1(230);

        cubicCurve.setControlX2(500);
        cubicCurve.setControlY2(260);

        cubicCurve.setEndX(400);
        cubicCurve.setEndY(290);

        region.setShape(cubicCurve);

        container.getChildren().add(region);

        setCustomSize(region,500,500);
        region.setTranslateX(200);
        region.setTranslateY(200);

        Circle circle1=new Circle(200,200,10);
        Circle circle2=new Circle(200,200,10);

        region.setBackground(Background.fill(Color.valueOf("ff000055")));
        region.setBorder(Border.stroke(Color.BLACK));

        container.getChildren().addAll(circle1,circle2);

        circle1.setFill(Color.BLACK);
        circle2.setFill(Color.BLACK);

//        cubicCurve.

//        cubicCurve.setf
//        Path path;
//        path.
//        circle1.setRadius();




    }

    final class ChessBoard extends GridPane {

        public ChessBoard( double length, int n, Color boldColor, Color liteColor){
            super();
            double bitLength=length/n;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    Rectangle rectangle=new Rectangle(bitLength,bitLength);
                    add(rectangle,i,j);
                    if((i+j)%2==0){
                        rectangle.setFill(boldColor);
                    }else{
                        rectangle.setFill(liteColor);
                    }
                }
            }
        }

    }

    class MyException extends RuntimeException{
        public MyException(){
//            RuntimeException b=new RuntimeException();
            super();
//            setStackTrace(new StackTraceElement[]{new StackTraceElement("asdasd","sklad","asa",3)});
//            printStackTrace();
            for(int i=0;i< getStackTrace().length;i++){
                print("____________________________________________________________________");
                print(getStackTrace()[i].toString());
            }
//            print(getStackTrace());
//            print(getCause());
//            getMessage();
//            getLocalizedMessage();
//            getS
        }
    }



}
