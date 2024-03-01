package com.boom.test;

import com.boom.apppaints.AppLinearGradient;
import com.boom.apppaints.AppRadialGradient;
import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppRectangle;
import com.boom.tools.Chessboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.stream.Collectors;

import static com.boom.tools.Tools.print;


public class Test61 extends Application {

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

        AppRectangle rectangle = new AppRectangle(300, 300,0,0);
        AppRectangle rectangle1 = new AppRectangle(300, 300,0,0);
        AppRectangle rectangle2 = new AppRectangle(300, 300,0,0);

        AppEllipse ellipse = new AppEllipse(150, 150);
        AppEllipse ellipse1 = new AppEllipse(150, 150);
        AppEllipse ellipse2 = new AppEllipse(150, 150);

        rectangle.affineTransform.prependTranslation(50, 50);
        rectangle1.affineTransform.prependTranslation(400, 50);
        rectangle2.affineTransform.prependTranslation(750, 50);

        ellipse.affineTransform.prependTranslation(150 + 50, 550);
        ellipse1.affineTransform.prependTranslation(150 + 400, 550);
        ellipse2.affineTransform.prependTranslation(150 + 750, 550);

//        container.getChildren().addAll(p,rectangle, rectangle1,rectangle2);

        Chessboard p = new Chessboard(25, 30, 44, Color.valueOf("00000088"), Color.valueOf("00000033"));

        container.getChildren().add(p);

        AppLinearGradient appLinearGradient = new AppLinearGradient(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));

        AppRadialGradient appRadialGradient = new AppRadialGradient(new RadialGradient(0, 0, 0.5, 0.5, 0.55, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));

        AppRadialGradient appRadialGradient1 = new AppRadialGradient(new RadialGradient(-45, 1, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));

        AppLinearGradient appLinearGradient1 = new AppLinearGradient(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));
        AppRadialGradient appRadialGradient2 = new AppRadialGradient(new RadialGradient(0, 0, 0.5, 0.5, 0.45, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));
        AppRadialGradient appRadialGradient3 = new AppRadialGradient(new RadialGradient(-45, 1, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));




        rectangle.backgroundStyle.addFill(appLinearGradient);

        rectangle1.backgroundStyle.addFill(appRadialGradient);

        rectangle2.backgroundStyle.addFill(appRadialGradient1);

        ellipse.backgroundStyle.addFill(appLinearGradient1);

        ellipse1.backgroundStyle.addFill(appRadialGradient2);

        ellipse2.backgroundStyle.addFill(appRadialGradient3);

        double a=10;
        rectangle.backgroundStyle.setStrokeWidth(a);
        rectangle1.backgroundStyle.setStrokeWidth(a);
        rectangle2.backgroundStyle.setStrokeWidth(a);
        ellipse.backgroundStyle.setStrokeWidth(a);
        ellipse1.backgroundStyle.setStrokeWidth(a);
        ellipse2.backgroundStyle.setStrokeWidth(a);


        container.getChildren().addAll(rectangle.wrappedNode, rectangle1.wrappedNode, rectangle2.wrappedNode, ellipse.wrappedNode, ellipse1.wrappedNode, ellipse2.wrappedNode);

        print("\n\t<defs>" +
                rectangle.backgroundStyle.fillsToSVG(2) +
                rectangle1.backgroundStyle.fillsToSVG(2) +
                rectangle2.backgroundStyle.fillsToSVG(2) +
                ellipse.backgroundStyle.fillsToSVG(2) +
                ellipse1.backgroundStyle.fillsToSVG(2) +
                ellipse2.backgroundStyle.fillsToSVG(2) +
                rectangle.backgroundStyle.strokesToSVG(2) +
                rectangle1.backgroundStyle.strokesToSVG(2) +
                rectangle2.backgroundStyle.strokesToSVG(2) +
                ellipse.backgroundStyle.strokesToSVG(2) +
                ellipse1.backgroundStyle.strokesToSVG(2) +
                ellipse2.backgroundStyle.strokesToSVG(2) +
                "\n\t</defs>" +
                p.toSVG(1) +
                rectangle.toSVG(1) +
                rectangle1.toSVG(1) +
                rectangle2.toSVG(1) +
                ellipse.toSVG(1) +
                ellipse1.toSVG(1) +
                ellipse2.toSVG(1));

        Polygon polygon=new Polygon(0,1,2,4,10,20);

        print(polygon.getPoints().stream().map(p1->p1+(polygon.getPoints().indexOf(p1)%2==0?",":" ")).collect(Collectors.joining("")));

//        print(rectangle.backgroundStyle.fillsToSVG(1));
//        print(rectangle.backgroundStyle.strokesToSVG(1));

//        print(rectangle.backgroundStyle.getStrokeWidth());

//        rectangle.backgroundStyle.setStrokeWidth(10);

//        print("___________________________________________________");
//        print(rectangle.styleableNode.getStyle());
//        print(rectangle.backgroundStyle);
//        rectangle.styleableNode.setStyle("-fx-background-color: red");



//        print(Color.TRANSPARENT);


    }


}
