package com.boom.test;

import com.boom.apppaints.AppLinearGradient;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppLine;
import com.boom.appshapes.AppRectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import static com.boom.tools.Tools.*;


public class Test23 extends Application {

    public static void setSize(Region region, double width, double height) {
        region.setMaxSize(width, height);
        region.setPrefSize(width, height);
        region.setMinSize(width, height);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        // Initialize and show the main canvas.
        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        AppRectangle appRectangle = new AppRectangle(300, 300);
        AppRectangle appRectangle1 = new AppRectangle(300, 300);

        AppEllipse appEllipse = new AppEllipse(100, 200);

        AppLine appLine = new AppLine(0, 0, 500, 500);


//        Line line=new Line(0,0,200,200);

        container.getChildren().addAll(appRectangle.getStyleableNode(),
                appRectangle1.getStyleableNode(), appEllipse.getStyleableNode(), appLine.getStyleableNode());

        appRectangle.backgroundStyle.removeAllFills();
        appRectangle1.backgroundStyle.removeAllFills();
        appEllipse.backgroundStyle.removeAllFills();
        appLine.backgroundStyle.removeAllFills();

        appRectangle.backgroundStyle.removeAllStrokes();
        appRectangle1.backgroundStyle.removeAllStrokes();
        appEllipse.backgroundStyle.removeAllStrokes();
        appLine.backgroundStyle.removeAllStrokes();

        appRectangle.backgroundStyle.addFill(0, new AppLinearGradient(new LinearGradient(0, 0, 300, 300, false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.valueOf("0000ff88")))));
        appRectangle.backgroundStyle.addFill(0, new AppLinearGradient(new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.valueOf("ff000088")))));

        appRectangle1.backgroundStyle.addFill(0, new AppLinearGradient(new LinearGradient(0, 0, 300, 300, false, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.valueOf("ffff0088")))));
        appRectangle1.backgroundStyle.addFill(0, new AppLinearGradient(new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.valueOf("00ff0088")))));

        appEllipse.backgroundStyle.addFill(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.valueOf("ff00ff88")))));
        appEllipse.backgroundStyle.addFill(0, new AppLinearGradient(new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.valueOf("00ffff88")))));

//        appLine.backgroundStyle.addFill(0,new AppColor(Color.rgb(255,0,0,0.5),uuid(50)));
        appLine.backgroundStyle.addStroke(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.valueOf("0000ff88")))));
        appLine.backgroundStyle.addStroke(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.valueOf("ff000088")), new Stop(1, Color.TRANSPARENT))));
        appLine.backgroundStyle.setStrokeWidth(10);





//        appLine.tttt().setStroke(Color.BLACK);
//
//        ((Line)appLine.tttt()).setEndX(200);
//        ((Line)appLine.tttt()).setEndY(200);

//        print(appLine.tttt());

        appRectangle.backgroundStyle.setStrokeWidth(10);
        appRectangle1.backgroundStyle.setStrokeWidth(10);
        appRectangle.backgroundStyle.addStroke(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.BLACK))));
        appRectangle1.backgroundStyle.addStroke(0, new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(1, Color.BLACK))));
//        appRectangle1.backgroundStyle.addStroke(0,new AppColor(Color.BLACK,uuid(50)));

        Region r = new Region();
        r.setBackground(Background.fill(Color.RED));

        container.getChildren().add(r);

        r.setShape(new Line(1, 2, 3, 4));


        setCustomSize(r, 200, 200);
//        for(AppPaint appPaint:appRectangle.backgroundStyle.getFillArray()){
//            print(((AppLinearGradient)appPaint).getAppStop(0));
//        }
//
//        print(appRectangle.backgroundStyle.getFillArray());

//        AppStop appStop=new AppStop(new Stop(0.2,Color.BLACK));
//
////        appStop.appColor.se
//
//        print(appStop.get());


//        appRectangle1.backgroundStyle.addFill(0,new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.TRANSPARENT),new Stop(1,Color.RED))));


        appRectangle.affineTransform.prependRotation(20);
        appRectangle.affineTransform.prependScale(1, 1.4);
        appRectangle.affineTransform.prependTranslation(200, 100);

//        print(appRectangle.affineTransform);

//        print(appRectangle.toSVG());
//        print(appRectangle1.toSVG());
        print("\t<defs>" +
                String.join("", appRectangle.backgroundStyle.fillsToSVG(2)) +
                String.join("", appRectangle.backgroundStyle.strokesToSVG(2)) +
                String.join("", appRectangle1.backgroundStyle.fillsToSVG(2)) +
                String.join("", appRectangle1.backgroundStyle.strokesToSVG(2)) +
                String.join("", appEllipse.backgroundStyle.fillsToSVG(2)) +
                String.join("", appEllipse.backgroundStyle.strokesToSVG(2)) +
                String.join("", appLine.backgroundStyle.fillsToSVG(2)) +
                String.join("", appLine.backgroundStyle.strokesToSVG(2)) +
                "\t\n</defs>" +
                appRectangle.getSVGClones(2) +
                appRectangle1.getSVGClones(2) +
                appEllipse.getSVGClones(2) +
                appLine.getSVGClones(2));

//        for(int i=0;appRectangle.backgroundStyle.getFillArray().size())

//        Affine x=appRectangle.affineTransform;

//        print(x.determinant());


//        Random rnd=new Random();
//
//        for(int i=0;i<10;i++) {
//            Affine u = new Affine();
//            u.append(new Scale(1, 2));
//            u.append(new Rotate(45));
//
////        u.append(new Rotate(30));
//            u.setMxx(rnd.nextDouble());
//            u.setMxy(rnd.nextDouble());
//            u.setMyx(rnd.nextDouble());
//            u.setMyy(rnd.nextDouble());
//
//
//            double[] p = dissectAffineTransform(u);
//
//
//            Affine newU = new Affine(), newU1 = new Affine();
//
//            newU.append(new Rotate(p[0]));
//            newU.append(new Scale(p[1], p[2]));
//            newU.append(new Rotate(p[3]));
//
//
//
////        newU1.prepend(new Rotate(p[2]*180/Math.PI));
////        newU1.prepend(new Scale(p[0],p[1]));
////        newU1.prepend(new Rotate(p[3]*180/Math.PI));
//
//            print("th1 = " + p[0]);
//            print("sx,sy = " + p[1] + "," + p[2]);
//            print("th2 = " + p[3]);
//
//
////        print(u);
//            print(u + " , " + newU);
//        }
//
//

//         print(Math.acos(0));


//        appRectangle.affineTransform.

//        print(appRectangle.affineTransform.);


//        XMLParserConfiguration xmlParserConfiguration=new XMLParserConfiguration();
//        xmlParserConfiguration.getCDataTagName().add

//        @XMLRootElement

//        XML xml=new XML();
//        OutputStream x=new DataOutputStream();
//        xml.
//        InputStream inputStream=new SequenceInputStream();
//        XMLEncoder xmlEncoder=new XMLEncoder();
//        XMLDecoder xmlDecoder=new XMLDecoder();
//        print(appRectangle.backgroundStyle.toJSON());


    }
}