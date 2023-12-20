package com.example.test;

import com.example.apppaints.AppColor;
import com.example.apppaints.AppLinearGradient;
import com.example.structures.abstracts.AppPaint;
import com.example.styles.CSSProperty;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.tools.Tools.*;


public class Test14 extends Application {
    @Override
    public void start(Stage stage) {




        VBox pane = new VBox();

        setCustomSize(pane,600,600);


        Scene scene = new Scene(pane);

        stage.setScene(scene);

        stage.show();


        ObjectProperty<Color> colorProperty=new SimpleObjectProperty<>();



//        pane.getChildren().add(appColorPicker);


        Rectangle r=new Rectangle(100,100,200,300);

        pane.getChildren().add(r);

        CSSProperty backgroundsProperty=new CSSProperty("-fx-fill","-fx-stroke","-fx-stroke-width");

        r.styleProperty().bind(backgroundsProperty);

        List<AppPaint> h=new ArrayList<>();

        h.add(createNew());
        h.add(new AppColor(new Color(1,1,1,1),uuid(50)));

        h.forEach(appPaint -> print(appPaint.getClass().getName()));


//        r.fillProperty().bind(colorProperty);

//        pane.getChildren().add(appColorPicker);

        Button b=new Button("click!");

        pane.getChildren().add(b);

        backgroundsProperty.addFill(0, createNew());

        b.setOnAction(event -> {
            backgroundsProperty.removeFill(0);
            for (int i=0;i<1;i++) {
                backgroundsProperty.addFill(0, createNew());
            }
        });

        print(backgroundsProperty);

//        Random rnd=new Random();
//
//        AppLinearGradient appLinearGradient=new AppLinearGradient(new LinearGradient(0,0,1,1,
//                true, CycleMethod.NO_CYCLE,
//                new Stop(0,new Color(rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),0.3 )),
//                new Stop(1,new Color(rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),0.3 ))));


//        backgroundsProperty.addFill(1,createNew());
//        b.setOnAction(event -> {
//
////            r.setFill(new Color(0.5,0.5,0.5,1));
//        });
//
//        ColorManagementPanel appColorPicker=new ColorManagementPanel(colorProperty);

//        appColorPicker.show(stage);


//        Slider slider=new Slider();
//
//        pane.getChildren().add(slider);
//
//        print(Color.valueOf("fabcfa37"));


//        slider.setMax(1);
//        slider.setMin(0);
//
//        slider.valueProperty().addListener((a,b,c)->{
//            print(uuid(20));
//        });
//
//        slider.setOnMouseDragged(mouseEvent -> {
//            print(uuid(40));
//        });







    }
    public static void main(String[] args) {
        launch();
    }

    AppLinearGradient createNew(){

        Random rnd=new Random();
        return new AppLinearGradient(new LinearGradient(0,0,1,1,
                true, CycleMethod.NO_CYCLE,
                new Stop(0,new Color(rnd.nextDouble()/2+0.5,rnd.nextDouble()/2,rnd.nextDouble(),0.3 )),
                new Stop(1,new Color(rnd.nextDouble()/2,rnd.nextDouble()/2+0.5,rnd.nextDouble(),0.3 ))),uuid(50));
    }



}

