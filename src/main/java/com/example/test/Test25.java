package com.example.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.uuid;


public class Test25 extends Application {

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


        TestText text=new TestText();
        text.setText("askldsadjk");
        text.setFont(new Font(80));
//        text.setStrokeWidth(10);

        text.setTranslateX(200);
        text.setTranslateY(200);


//        text.setText("alksdklsadkl");

        container.getChildren().add(text);

        Rectangle r=new Rectangle(text.getBoundsInParent().getMinX(),
                text.getBoundsInParent().getMinY(),
                text.getBoundsInParent().getWidth(),
                text.getBoundsInParent().getHeight());

        r.setFill(Color.TRANSPARENT);
        r.setStroke(Color.BLACK);

//        r.lookup("")

//        container.getChildren().add(r);


        text.focusedProperty().addListener((a,b,c)->{
            print(c);
        });

        text.setOnMouseClicked(mouseEvent -> {
            text.setCaretPosition(8);
//            text.setCare
//            print(text.getCaretPosition());
            print("slakdlsadkld");
            text.requestFocus();
        });

        text.setOnKeyTyped(keyEvent -> print(uuid(50)));

//        container.setOnMouseClicked(mouseEvent -> container.requestFocus());







//        javafx.scene.shape.Rectangle rectangle=new Rectangle(100,100);
//        container.getChildren().add(rectangle);




//        AppRectangle appRectangle=new AppRectangle(300,300);
//
//        container.getChildren().add(appRectangle.getRegion());
//
//        appRectangle.backgroundStyle.addFill(0,new AppLinearGradient(new LinearGradient(0,0,300,300,false, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.TRANSPARENT),new Stop(1,Color.valueOf("0000ff88")))));
//        appRectangle.backgroundStyle.addFill(0,new AppLinearGradient(new LinearGradient(0,1,1,0,true, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.TRANSPARENT),new Stop(1,Color.valueOf("ff000088")))));
//
//        appRectangle.backgroundStyle.setStrokeWidth(10);
//        appRectangle.backgroundStyle.addStroke(0,new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.TRANSPARENT),new Stop(1,Color.BLACK))));
//
//
//        appRectangle.affineTransform.prependRotation(20);
//        appRectangle.affineTransform.prependScale(1,1.4);
//        appRectangle.affineTransform.prependTranslation(200,100);
//
////        print(appRectangle.affineTransform);
//
////        print(appRectangle.toSVG());
//
//        Affine x=appRectangle.affineTransform;
//
////        print(x.determinant());
//
//
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
//
////         print(Math.acos(0));
//
//
//
//
////        appRectangle.affineTransform.
//
////        print(appRectangle.affineTransform.);
//
//
//
////        XMLParserConfiguration xmlParserConfiguration=new XMLParserConfiguration();
////        xmlParserConfiguration.getCDataTagName().add
//
////        @XMLRootElement
//
////        XML xml=new XML();
////        OutputStream x=new DataOutputStream();
////        xml.
////        InputStream inputStream=new SequenceInputStream();
////        XMLEncoder xmlEncoder=new XMLEncoder();
////        XMLDecoder xmlDecoder=new XMLDecoder();
//
//
//






























    }
}

class TestText extends Text{

    public Rectangle border=new Rectangle();

    TestText(){
        super();
    }

}