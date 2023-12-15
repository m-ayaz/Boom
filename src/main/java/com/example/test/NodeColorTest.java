package com.example.test;

import com.example.configuration.Configs;
import com.example.panels.paint.PaintManagementPane;
import com.example.apppaints.AppColor;
import com.example.apppaints.AppLinearGradient;
import com.example.styles.BackgroundsProperty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.setSize;

public class NodeColorTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Configs.setDefaultConfig();

        PaintManagementPane pane=new PaintManagementPane();

        setSize(pane,800,600);

        Scene scene=new Scene(pane);

        stage.setScene(scene);

        stage.show();

//        Stop[] stops=new Stop[]{};
//        Stop[] stops1=new Stop[]{new Stop(0,Color.RED),new Stop(1,Color.BLUE)};
        Stop[] stops=new Stop[]{new Stop(0,Color.RED),new Stop(0.5,Color.GREEN),new Stop(1,Color.BLUE)};
        LinearGradient x=new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,stops);
//        print(x.toString());



//        print(x.getStops().remove(0));


        BackgroundsProperty backgroundsProperty=new BackgroundsProperty("-fx-fill","-fx-stroke","-fx-stroke-width");
        backgroundsProperty.addFill(0,new AppColor(Color.RED));
        backgroundsProperty.addFill(1,new AppColor(Color.GREEN));
        backgroundsProperty.addFill(2,new AppLinearGradient(x));
        backgroundsProperty.addFill(3,new AppColor(Color.BLUE));

//        print((new AppLinearGradient(x)).get());



        pane.registerBackgrounds(backgroundsProperty);


        Rectangle r=new Rectangle(200,200);
//        pane.getChildren().add(r);

        Color c=Color.color(0.5,0.3,0.7,1);

//        print(c.toString());
//
        r.setStyle("-fx-fill: #804db2ff".formatted());

//        print("linear-gradient(from 0.0%% 0.0%% to 100.0%% 0.0%%, #ff0000ff 0.0%%, #008000ff 50.0%%, #0000ffff 100.0%%)".formatted());

//        print(x.toString().replaceAll("0x","#"));



//        TableView<Number> tableView=new TableView<>();
//
//        pane.getChildren().add(tableView);
//
//        TableColumn<Number,Number> tableColumn=new TableColumn<>();
//
//        tableView.getColumns().add(tableColumn);
//
//        tableColumn.

//        RadialGradient h=new RadialGradient();


//        ColorProperty c=new ColorProperty(Color.RED);
//
//        print(c.getName());




    }

    public static void main(String[] args){
        launch(args);
    }

}
