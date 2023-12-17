package com.example.test;

import com.example.apppaints.AppLinearGradient;
import com.example.configuration.Configs;
import com.example.panels.paint.LinearGradientManagementPanel;
import com.example.styles.BackgroundsProperty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.example.tools.Tools.setCustomSize;

public class LGTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Configs.setDefaultConfig();

        Stop[] stops=new Stop[]{new Stop(0,Color.RED),new Stop(0.5,Color.GREEN),new Stop(1,Color.BLUE)};
//        stops=new Stop[]{};
        LinearGradient x=new LinearGradient(0,0,1,0,true, CycleMethod.REPEAT,stops);

//        print(x);

        AppLinearGradient appLinearGradient=new AppLinearGradient(x);

//        print(appLinearGradient.get());

        LinearGradientManagementPanel pane=new LinearGradientManagementPanel(appLinearGradient);

        Rectangle r=new Rectangle(100,100);

//        pane.main.getChildren().add(r);

        BackgroundsProperty b=new BackgroundsProperty("-fx-fill","-fx-stroke","-fx-stroke-width");

        b.addFill(0,appLinearGradient);

        r.styleProperty().bind(b);

//        Slider slider=new Slider();
//
//        pane.main.getChildren().add(slider);

//        print(slider.lookup(".slider"));

//        slider.
//        pane.getC

//        r.styleProperty().addListener((a,b1,c)->{
//            print(c);
//        });

//        appLinearGradient.getPaintProperty().addListener((a,b1,c)->print(c));

//        print(b.get());

//        LinearGradient linearGradient=new LinearGradient(0,0,1,1,true,CycleMethod.NO_CYCLE,stops);
//
//        Rectangle rectangle=new Rectangle(200,200);
//
//        rectangle.setFill(linearGradient);




        pane.main.getChildren().add(0,r);
//        b.addListener(change->{
//            print(change);
//        });

//        appLinearGradient.getPaintProperty().addListener((observableValue, paint, t1) -> {
//            print(uuid(50));
//        });

//        appLinearGradient.get().add
//
//        b.fillArray.add(appLinearGradient);

//        print(appLinearGradient.hh.get());
//
//        r.styleProperty().bind(appLinearGradient.hh);
//
//        ObservableList<AppLinearGradient> array= FXCollections.observableList(new ArrayList<>());
//        array.add(appLinearGradient);
//
//        array.addListener((ListChangeListener<? super AppLinearGradient>) event->{
//            print(uuid(50));
//        });

//        Color c=Color.RED;
//
//
//
//        r.setOnMouseMoved(mouseEvent -> {
//            Random rnd=new Random();
////            c.
////            c=new Color(rnd.nextDouble(), 0,0,1);
//        });

//        FXCollections.

//        appLinearGradient

//        b.addListener((a,b1,c)->{
//            print(uuid(50));
//        });






















//        setSize(pane,800,600);

//        stage.initStyle(StageStyle.UNDECORATED);

//        Ale

        Pane pane1=new Pane();

        setCustomSize(pane1,500,500);

        Scene scene=new Scene(pane1);

        stage.setScene(scene);

        stage.show();



//        ColorPicker colorPicker;

//        colorPicker.set



//        setSize();
//        pane1.setOnMouseClicked(mouseEvent -> {
////            if()
////            pane.show(stage,mouseEvent.getScreenX(),mouseEvent.getScreenY());
//        });


        pane.show(stage);










//        Stop[] stops=new Stop[]{};
//        Stop[] stops1=new Stop[]{new Stop(0,Color.RED),new Stop(1,Color.BLUE)};

//        print(x.toString());



//        print(x.getStops().remove(0));


//        BackgroundsProperty backgroundsProperty=new BackgroundsProperty("-fx-fill","-fx-stroke","-fx-stroke-width");
//        backgroundsProperty.addFill(0,new AppColor(Color.RED));
//        backgroundsProperty.addFill(1,new AppColor(Color.GREEN));
//        backgroundsProperty.addFill(2,new AppLinearGradient(x));
//        backgroundsProperty.addFill(3,new AppColor(Color.BLUE));
//
////        print((new AppLinearGradient(x)).get());
//
//
//
//        pane.registerBackgrounds(backgroundsProperty);
//
//
//        Rectangle r=new Rectangle(200,200);
////        pane.getChildren().add(r);
//
//        Color c=Color.color(0.5,0.3,0.7,1);
//
////        print(c.toString());
////
//        r.setStyle("-fx-fill: #804db2ff".formatted());

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

//    class sss extends

}
