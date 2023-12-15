package com.example.apppaints;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.example.tools.Tools.print;

public class AppColorPicker extends TabPane {

    ObjectProperty<Color> colorProperty=new SimpleObjectProperty<>();

//    ObservableValue<Color> colorObservableValue=new SimpleObjectProperty<>();

    Rectangle colorPreview=new Rectangle(40,40);

    ColorPicker colorPicker=new ColorPicker();

    ScrollBar redValueBar=new ScrollBar();
    ScrollBar greenValueBar=new ScrollBar();
    ScrollBar blueValueBar=new ScrollBar();
    ScrollBar alphaValueBar=new ScrollBar();

    TextField redValueField=new TextField();
    TextField greenValueField=new TextField();
    TextField blueValueField=new TextField();
    TextField alphaValueField=new TextField();

    TextField rgbaField=new TextField();

//    DoubleProperty red=new SimpleDoubleProperty();
//    DoubleProperty green=new SimpleDoubleProperty();
//    DoubleProperty blue=new SimpleDoubleProperty();
//    DoubleProperty alpha=new SimpleDoubleProperty(1);


Tab colorPickerTab=new Tab();
    Tab rgbaTab=new Tab();
    Tab hseTab=new Tab();

    Pane colorPickerPane=new Pane();
    GridPane rgbaPane=new GridPane();
    Pane hsePane=new Pane();

    public AppColorPicker(Color color1){

       super();

//        Popup h;



//        h.getOwnerWindow();

//        FadeTransition y=new FadeTransition(Duration.millis(1000),h.getOwnerWindow());


        getTabs().addAll(colorPickerTab,rgbaTab,hseTab);
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        setTabDragPolicy(TabDragPolicy.FIXED);


        colorPickerPane.getChildren().add(colorPicker);

        colorPickerTab.setText("colorPicker");
        rgbaTab.setText("RGBA");
        hseTab.setText("HSE");

        colorPickerTab.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(t1){
                colorPicker.setValue(colorProperty.get());
            }
        });
        rgbaTab.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(t1){
                print(colorProperty.get().getRed());
                print(colorProperty.get().getGreen());
                print(colorProperty.get().getBlue());
                print(colorProperty.get().getOpacity());
                redValueBar.setValue(colorProperty.get().getRed()*255.0);
                greenValueBar.setValue(colorProperty.get().getGreen()*255.0);
                blueValueBar.setValue(colorProperty.get().getBlue()*255.0);
                alphaValueBar.setValue(colorProperty.get().getOpacity()*255.0);
            }
        });





        rgbaPane.addRow(0,redValueBar,redValueField);
        rgbaPane.addRow(1,greenValueBar,greenValueField);
        rgbaPane.addRow(2,blueValueBar,blueValueField);
        rgbaPane.addRow(3,alphaValueBar,alphaValueField);
        rgbaPane.addRow(4,colorPreview);

//        colorPreview.setFill(Color.BLUE);



        redValueBar.setMin(0);
        redValueBar.setMax(255);
        greenValueBar.setMin(0);
        greenValueBar.setMax(255);
        blueValueBar.setMin(0);
        blueValueBar.setMax(255);
        alphaValueBar.setMin(0);
        alphaValueBar.setMax(255);

//        redValueBar.setBlockIncrement(1);
//        redValueBar.setUnitIncrement(1);

//        red

        redValueBar.valueProperty().addListener((observableValue, number, t1) -> {
            redValueField.setText(t1.toString());
//            print(redValueField);
            colorProperty.set(Color.color(redValueBar.getValue()/255,greenValueBar.getValue()/255,blueValueBar.getValue()/255,alphaValueBar.getValue()/255));
        });

        greenValueBar.valueProperty().addListener((observableValue, number, t1) -> {
            greenValueField.setText(t1.toString());
            colorProperty.set(Color.color(redValueBar.getValue()/255,greenValueBar.getValue()/255,blueValueBar.getValue()/255,alphaValueBar.getValue()/255));
        });

        blueValueBar.valueProperty().addListener((observableValue, number, t1) -> {
            blueValueField.setText(t1.toString());
            colorProperty.set(Color.color(redValueBar.getValue()/255,greenValueBar.getValue()/255,blueValueBar.getValue()/255,alphaValueBar.getValue()/255));
        });

        alphaValueBar.valueProperty().addListener((observableValue, number, t1) -> {
            alphaValueField.setText(t1.toString());
            colorProperty.set(Color.color(redValueBar.getValue()/255,greenValueBar.getValue()/255,blueValueBar.getValue()/255,alphaValueBar.getValue()/255));
        });

        redValueField.setText(String.valueOf(redValueBar.getValue()));
        greenValueField.setText(String.valueOf(greenValueBar.getValue()));
        blueValueField.setText(String.valueOf(blueValueBar.getValue()));
        alphaValueField.setText(String.valueOf(alphaValueBar.getValue()));

        redValueField.setOnKeyTyped(keyEvent -> {
            try {
//                if(Double.parseDouble(redValueField.getText())<=255&&Double.parseDouble(redValueField.getText())>=0) {
                    redValueBar.setValue(Double.parseDouble(redValueField.getText()));
//                }
            } catch (NumberFormatException e) {
                print(e);
            }
        });
        greenValueField.setOnKeyTyped(keyEvent -> {
            try {
                greenValueBar.setValue(Double.parseDouble(greenValueField.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
        blueValueField.setOnKeyTyped(keyEvent -> {
            try {
                blueValueBar.setValue(Double.parseDouble(blueValueField.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
        alphaValueField.setOnKeyTyped(keyEvent -> {
            try {
                alphaValueBar.setValue(Double.parseDouble(alphaValueField.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });

        redValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(!t1){
                redValueField.setText(String.valueOf(redValueBar.getValue()));
            }
        });
        greenValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(!t1){
                greenValueField.setText(String.valueOf(greenValueBar.getValue()));
            }
        });
        blueValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(!t1){
                blueValueField.setText(String.valueOf(blueValueBar.getValue()));
            }
        });
        alphaValueField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(!t1){
                alphaValueField.setText(String.valueOf(alphaValueBar.getValue()));
            }
        });




//        alphaValueField.textProperty().addListener((observableValue, s, t1) -> {
//
//        });

//        colorPreview.fillProperty().bind(colorProperty);


//        colorPreview.fillProperty().;

//        colorPreview.strokeProperty().bind(colorProperty);

//        colorPreview.fillProperty().addListener((observableValue, paint, t1) -> {
//            print(uuid(50));
//        });

        colorProperty.addListener((observableValue, color, t1) -> {
            print(t1);
            colorPreview.setFill(t1);
        });








        colorPickerTab.setContent(colorPickerPane);
        rgbaTab.setContent(rgbaPane);
        hseTab.setContent(hsePane);

        colorProperty.set(color1);





//        red

//        redValueField.commitValue();

//        colorObservableValue.ma

//        getChildren().addAll(
//                colorPreview,
//                colorPicker,
//                new HBox(redValueBar,redValueField),
//                new HBox(greenValueBar,greenValueField),
//                new HBox(blueValueBar,blueValueField),
//                new HBox(alphaValueBar,alphaValueField)
//        );
//

//
//        colorPicker.valueProperty().bindBidirectional(colorProperty);
//
////        colorProperty.addListener((a,b,c)->print("prop = "+c));
//
////        red.bind(colorProperty.map(color->color.getRed()*255));
////        green.bind(colorProperty.map(color->color.getGreen()*255));
////        blue.bind(colorProperty.map(color->color.getBlue()*255));
////        alpha.bind(colorProperty.map(Color::getOpacity));
////        redValueBar.valueProperty().bindBidirectional();
//
//        red.bindBidirectional(redValueBar.valueProperty());
//        green.bindBidirectional(greenValueBar.valueProperty());
//        blue.bindBidirectional(blueValueBar.valueProperty());
//        alpha.bindBidirectional(alphaValueBar.valueProperty());
//
//
////        redValueBar.valueProperty().bindBidirectional(col);
//
////        alphaValueBar.setValue(1);
//
////        colorPicker.valueProperty().addListener((a,b,c)->update());
////        redValueBar.valueProperty().addListener((a,b,c)->update());
////        greenValueBar.valueProperty().addListener((a,b,c)->update());
////        blueValueBar.valueProperty().addListener((a,b,c)->update());
//
////        Property<Color> h=new DoubleProperty()
//
////        red.bindBidirectional(redValueBar.valueProperty());
////        green.bind(greenValueBar.valueProperty().divide(255));
////        blue.bind(blueValueBar.valueProperty().divide(255));
////        alpha.bind(alphaValueBar.valueProperty());
//
////        colorProperty.bi
////
////        colorPreview.fillProperty().bind(colorProperty);
//
////        redValueBar.valueProperty().addListener((a,b,c)-> red.set(c.doubleValue()/255));
////        greenValueBar.valueProperty().addListener((a,b,c)-> green.set(c.doubleValue()/255));
////        blueValueBar.valueProperty().addListener((a,b,c)-> blue.set(c.doubleValue()/255));
////        alphaValueBar.valueProperty().addListener((a,b,c)-> alpha.set(c.doubleValue()));
//
////        colorPicker.valueProperty().addListener((a,b,c)->{
//            red.set(c.getRed());
//            green.set(c.getGreen());
//            blue.set(c.getBlue());
//            alpha.set(c.getOpacity());
//        });
//
//
//        red.addListener((a,b,c)-> update());
//        blue.addListener((a,b,c)->update());
//        green.addListener((a,b,c)->update());
//        alpha.addListener((a,b,c)->update());

//        colo

//        colorPicker.set

    }

//    void update(){
//        colorProperty.set(new Color(red.get(),green.get(),blue.get(),alpha.get()));
////        color=;
////        print(color);
////        colorPreview.setFill(color);
//    }

//    void update(){
//
//        colorPicker.setValue(Color.color(red.get(),green.get(),blue.get(),alpha.get()));
//        redValueBar.setValue(255*red.get());
//        greenValueBar.setValue(255*green.get());
//        blueValueBar.setValue(255*blue.get());
//        alphaValueBar.setValue(alpha.get());
//        redValueField.setText(red.getValue().toString());
//        greenValueField.setText(green.getValue().toString());
//        blueValueField.setText(blue.getValue().toString());
//        alphaValueField.setText(alpha.getValue().toString());
//
//    }

}
