package com.example.panels.paint;

import com.example.apppaints.AppColor;
import com.example.apppaints.AppLinearGradient;
import com.example.panels.chart.number_number.DataField_NumberNumber;
import com.example.styles.BackgroundsProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;


public class PaintManagementPanel extends VBox {

    BackgroundsProperty backgroundsProperty;


     VBox paintsPane = new VBox();
     ObservableList<Node> paintsPaneChildren = paintsPane.getChildren();
    Button primaryAddColorButton = new Button("color");
    Button primaryAddLinearGradientButton = new Button("lin-grad");
     Label primaryEmptySpace = new Label();
    
    
    public PaintManagementPanel(){

        super();

        paintsPaneChildren.add(new HBox(primaryEmptySpace, primaryAddColorButton,primaryAddLinearGradientButton));
        
    }
    
//    public void registerBackground(BackgroundsProperty backgroundsProperty){
//
//        this.backgroundsProperty=backgroundsProperty;
//
//    }



    public void registerBackground(BackgroundsProperty backgroundsProperty) {
        this.backgroundsProperty=backgroundsProperty;
        for (int i = 0; i < backgroundsProperty.getFillArray().size(); i++) {
            PaintField paintField = new PaintField(paintsPaneChildren, backgroundsProperty,backgroundsProperty.getFillArray().get(i));
            paintsPaneChildren.add(paintField);
        }
    }


    void setPrimaryAddColorButton() {
        primaryEmptySpace.setVisible(false);
        primaryAddColorButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddColorButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddColorButton.setOnAction(event -> {
            AppColor newAppColor=new AppColor(Color.TRANSPARENT);
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppColor);
            backgroundsProperty.addFill(0,newAppColor);
            paintsPaneChildren.add(1, newPaintField);
        });
    }

    void setPrimaryAddLinearGradientButton() {
        primaryEmptySpace.setVisible(false);
        primaryAddLinearGradientButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddLinearGradientButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddLinearGradientButton.setOnAction(event -> {
            AppLinearGradient newAppLinearGradient=new AppLinearGradient(new LinearGradient(0,0,0,0,true, CycleMethod.NO_CYCLE));
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppLinearGradient);
            backgroundsProperty.addFill(0,newAppLinearGradient);
            paintsPaneChildren.add(1, newPaintField);
        });
    }
//    Button addColorButton=new Button("color");
//    Button addColorButton=new Button("color");

//    VBox backgroundsPane = new VBox();
//
//    ObservableList<Node> children = backgroundsPane.getChildren();
//
//    public PaintManagementPanel() {
//        super();
//        setBackground(Background.fill(Color.rgb(255, 0, 0, 0.1)));
//        getChildren().add(backgroundsPane);
//    }
//
//    public void registerBackgrounds(BackgroundsProperty backgrounds) {
//        this.backgrounds = backgrounds;
//        backgrounds.getFillArray().forEach(paint -> {
////            if (paint.getClass().getName().equals(AppColor.class.getName())) {
////                children.add(new ColorField(backgrounds.getFillArray(), children, (AppColor) paint));
////            } else if (paint.getClass().getName().equals(AppLinearGradient.class.getName())) {
////                children.add(new LinearGradientField(backgrounds.getFillArray(), children, (AppLinearGradient) paint));
////            }
//        });
//    }

}
