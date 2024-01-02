package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.FormatStringConverter;
import org.json.JSONArray;

import java.text.MessageFormat;
import java.util.List;

import static com.boom.tools.Tools.*;


public class Test63 extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
        Circle circle=new Circle(5,Color.BLUE);



        String string="Some Text";

        Text text=new Text(string);
        TextArea textArea=new TextArea(string);

        Rectangle rectangle1=new Rectangle();
        Rectangle rectangle2=new Rectangle();

        text.setFont(new Font(50));

        container.getChildren().add(textArea);
//        container.getChildren().add(text);
        container.getChildren().addAll(rectangle1,rectangle2,circle);
        text.setTranslateX(100);
        text.setTranslateY(100);
        text.setCaretBias(false);
//        text.set

        textArea.setFont(new Font(100));

        text.setBoundsType(TextBoundsType.VISUAL);

        JSONArray jsonArray=new JSONArray();

//        List<Double> a=jsonArray.m(u->Double.parseDouble(u.toString()));

//        textArea.setStyle("-fx-label-padding: 0");

//        textArea.setStyle("  -fx-padding: -5px;\n" +
//                "    -fx-border-insets: -5px;\n" +
//                "    -fx-background-insets: -5px;");
//        TextFormatter<Rectangle> h= new TextFormatter<>(new Rectangle());

//        h.

//        textArea.setTextFormatter();

//        textArea.setScrollTop(0);
//        textArea.setScrollLeft(0);


//        textArea.setScrollTop(1000);
//        textArea.setBo










        rectangle1.setFill(new Color(1,0,0,0.1));

        rectangle1.setX(textArea.getBoundsInParent().getMinX());
        rectangle1.setY(textArea.getBoundsInParent().getMinY());
        rectangle1.setWidth(textArea.getBoundsInParent().getWidth());
        rectangle1.setHeight(textArea.getBoundsInParent().getHeight());
        circle.setCenterX(rectangle1.getBoundsInParent().getMinX());
        circle.setCenterY(rectangle1.getBoundsInParent().getMaxY());

        textArea.boundsInParentProperty().addListener((a,b,c)->{
            print(c);
            rectangle1.setX(c.getMinX());
            rectangle1.setY(c.getMinY());
            rectangle1.setWidth(c.getWidth());
            rectangle1.setHeight(c.getHeight());

        });

    }


}
