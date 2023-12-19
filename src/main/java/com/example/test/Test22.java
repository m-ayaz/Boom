package com.example.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.json.XML;
import org.json.XMLParserConfiguration;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

import static com.example.tools.Tools.print;

import javax.xml.*;


public class Test22 extends Application {

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