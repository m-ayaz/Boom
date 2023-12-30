package com.boom.test;

import com.boom.apppaints.AppColor;
import com.boom.apppaints.AppLinearGradient;
import com.boom.configuration.Configs;
import com.boom.panels.paint.PaintManagementPanel;
import com.boom.styles.CSSProperty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import static com.boom.tools.Tools.setCustomSize;

public class NodeColorTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Configs.setDefaultConfig();


        VBox vBox = new VBox();

//        setSize(vBox, 800, 600);

        Scene scene = new Scene(vBox);

        stage.setScene(scene);

        stage.show();

//        Stop[] stops=new Stop[]{};
//        Stop[] stops1=new Stop[]{new Stop(0,Color.RED),new Stop(1,Color.BLUE)};
        LinearGradient x = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED), new Stop(0.5, Color.GREEN), new Stop(1, Color.BLUE));
//        print(x.toString());


//        print(x.getStops().remove(0));


        CSSProperty backgroundsProperty = new CSSProperty("-fx-background-color", "-fx-border-color", "-fx-border-width");
        backgroundsProperty.addFill(0, new AppColor(new Color(1,0,0,0.3)));
        backgroundsProperty.addFill(1, new AppColor(new Color(0,1,0,0.3)));
        backgroundsProperty.addFill(2, new AppLinearGradient(x));
        backgroundsProperty.addFill(3, new AppColor(new Color(0,0,1,0.3)));

//        print((new AppLinearGradient(x)).get());
        Pane pane1=new Pane();

        pane1.styleProperty().bind(backgroundsProperty);

//        rectangle.setStyle("-fx-fill:#ff00ff33,linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #ff0000ff 0.0%, #008000ff 50.0%, #0000ffff 100.0%) ;");

//        print(rectangle.getStyle());

        setCustomSize(pane1,400,400);


        PaintManagementPanel pane = new PaintManagementPanel();

        pane.registerBackground(backgroundsProperty);

//        pane.registerBackgrounds(backgroundsProperty);


//        Rectangle r=new Rectangle(200,200);
//        pane.getChildren().add(r);

        Color c = Color.color(0.5, 0.3, 0.7, 1);

//        print(c.toString());
//
//        r.setStyle("-fx-fill: #804db2ff".formatted());

//        print("linear-gradient(from 0.0%% 0.0%% to 100.0%% 0.0%%, #ff0000ff 0.0%%, #008000ff 50.0%%, #0000ffff 100.0%%)".formatted());

//        print(x.toString().replaceAll("0x","#"));

        vBox.getChildren().addAll(pane, pane1);

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

    public static void main(String[] args) {
        launch(args);
    }

}
