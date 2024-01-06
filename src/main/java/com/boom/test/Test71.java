package com.boom.test;

import com.boom.appcharts.AppData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

import static com.boom.tools.Tools.*;


public class Test71 extends Application {

    public static void main(String[] args) {
        launch();
    }
    Random rnd=new Random();
    DataComparator dataComparator=new DataComparator();

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        List<String> strings= Arrays.asList("23","123","423","223");

        Supplier<DoubleStream> doubleStreamSupplier=()->strings.stream().mapToDouble(Double::parseDouble);
//        DoubleStream stream=strings.stream().flatMapToDouble(s -> s.compareTo("as"));

//        print(stream);

        print(doubleStreamSupplier.get().max());
        print(doubleStreamSupplier.get().min());

        print(doubleStreamSupplier.get());




    }


}

