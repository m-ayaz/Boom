package com.example.test;

import com.example.apppaints.AppLinearGradient;
import com.example.appshapes.AppRectangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import static com.example.tools.Tools.print;


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

        AppRectangle appRectangle=new AppRectangle(300,300);

        container.getChildren().add(appRectangle.getRegion());

        appRectangle.backgroundStyle.addFill(0,new AppLinearGradient(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT),new Stop(1,Color.valueOf("0000ff88")))));
        appRectangle.backgroundStyle.addFill(0,new AppLinearGradient(new LinearGradient(0,1,1,0,true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.TRANSPARENT),new Stop(1,Color.valueOf("ff000088")))));

        print(appRectangle.toSVG());



//        XMLParserConfiguration xmlParserConfiguration=new XMLParserConfiguration();
//        xmlParserConfiguration.getcDataTagName().add

//        @XMLRootElement

//        XML xml=new XML();
//        OutputStream x=new DataOutputStream();
//        xml.
//        InputStream inputStream=new SequenceInputStream();
//        XMLEncoder xmlEncoder=new XMLEncoder();
//        XMLDecoder xmlDecoder=new XMLDecoder();

































    }
}