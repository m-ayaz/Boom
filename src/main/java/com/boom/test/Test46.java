package com.boom.test;

import com.boom.tools.Chessboard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Test46 extends Application {

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

        Rectangle rectangle = new Rectangle(50, 50, 500, 500);
        Rectangle rectangle1 = new Rectangle(650, 50, 500, 500);

        Chessboard p = new Chessboard(40, 16,32, Color.valueOf("00000088"), Color.valueOf("00000033"));

        container.getChildren().addAll(p,rectangle, rectangle1);

        rectangle.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));

        rectangle1.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.55, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT), new Stop(0.2, Color.YELLOW),
                new Stop(0.4, Color.valueOf("0000ff88")), new Stop(0.6, Color.GREEN), new Stop(0.8, Color.RED)
                , new Stop(1, Color.valueOf("ff5599cc"))
        ));


    }




}
