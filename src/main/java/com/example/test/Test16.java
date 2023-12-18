package com.example.test;

import com.example.apppaints.AppColor;
import com.example.panels.paint.ColorManagementPanel;
import com.example.styles.CSSProperty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Test16 extends Application {

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
        GridPane container = new GridPane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


        Rectangle r=new Rectangle(200,200);

        AppColor appColor=new AppColor(Color.TRANSPARENT);

        CSSProperty b=new CSSProperty("-fx-fill","-fx-stroke","-fx-stroke-width");
        b.addFill(0,appColor);

        r.styleProperty().bind(b);


        ColorManagementPanel colorManagementPanel=new ColorManagementPanel(appColor);


        colorManagementPanel.show(Stage.getWindows().get(0));







        container.getChildren().addAll(r);

//        // Instantiate a Pane, a LineChart and two Shapes (a Rectangle and a Circle).
//        Pane pane = new Pane();
//        Pane pane2 = new Pane();
//        LineChart<Number, Number> lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());
//        Rectangle rectangle = new Rectangle();
//        Button button = new Button();
//
//        // Add some labels for better addressing the Objects.
//        double fontSize = 30;
//        Label label1 = new Label("Pane");
//        Label label2 = new Label("LineChart");
//        Label label3 = new Label("Button");
//        Label label4 = new Label("Rectangle :(");
//        label1.setFont(new Font(fontSize));
//        label2.setFont(new Font(fontSize));
//        label3.setFont(new Font(fontSize));
//        label4.setFont(new Font(fontSize));
//
//        // Add the aforementioned objects into main canvas.
//        container.addRow(0, label1, label2, label3, label4);
//        container.addRow(1, pane, lineChart, button, rectangle);
//
//        // Insert some arbitrary data into LineChart.
//        lineChart.getData().add(new XYChart.Series<>());
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(1, 4));
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(2, 2));
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(3, 3));
//        lineChart.getData().get(0).getData().add(new XYChart.Data<>(4, 1));
//
//        // Set sizes.
//        setSize(pane, 200, 200);
//        setSize(pane2, 200, 200);
//        setSize(lineChart, 200, 200);
//        setSize(button, 200, 200);
//        rectangle.setWidth(200);
//        rectangle.setHeight(200);
//
//
//        // Apply a style to the Pane, LineChart, Button and Rectangle.
//        String style = "linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #00000000 0.0%, #ff000088 100.0%)," +
//                "linear-gradient(from 0.0% 0.0% to 0.0% 100.0%, #00000000 0.0%, #0000ff88 100.0%)";
//        pane.setStyle("-fx-background-color: " + style);
//        pane2.setStyle("-fx-background-color: " + style);
//        lineChart.setStyle("-fx-background-color: " + style);
//        button.setStyle("-fx-background-color: " + style);
//
//        // Here, I have a problem :(
//        rectangle.setStyle("-fx-fill: " + style);


    }


}

