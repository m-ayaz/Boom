package com.example.panels.paint;

import com.example.apppaints.AppColor;
import com.example.apppaints.AppLinearGradient;
import com.example.apppaints.AppRadialGradient;
import com.example.icons.LinearGradientIcon;
import com.example.icons.RadialGradientIcon;
import com.example.icons.SolidColorIcon;
import com.example.styles.CSSProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;

import static com.example.tools.Tools.setCustomSize;


public class PaintManagementPanel extends VBox {

    CSSProperty backgroundsProperty;


     VBox paintsPane = new VBox();
     ObservableList<Node> paintsPaneChildren = paintsPane.getChildren();
    Button primaryAddColorButton = new Button();
    Button primaryAddLinearGradientButton = new Button();
    Button primaryAddRadialGradientButton = new Button();
     Label primaryEmptySpace = new Label();

    double infoWidth=200;
    double buttonWidth=40;
    double buttonHeight=40;
    
    public PaintManagementPanel(){

        super();



        getChildren().add(paintsPane);



        setPrimaryAddColorButton(infoWidth,buttonWidth,buttonHeight);
        setPrimaryAddLinearGradientButton(infoWidth,buttonWidth,buttonHeight);
        setPrimaryAddRadialGradientButton(infoWidth,buttonWidth,buttonHeight);

        setGraphics(infoWidth,buttonWidth,buttonHeight);




    }
    
    public void registerBackground(CSSProperty backgroundsProperty) {
        this.backgroundsProperty=backgroundsProperty;
        paintsPaneChildren.setAll(new HBox(primaryEmptySpace, primaryAddColorButton,primaryAddLinearGradientButton,primaryAddRadialGradientButton));
        for (int i = 0; i < backgroundsProperty.getFillArray().size(); i++) {
            PaintField paintField = new PaintField(paintsPaneChildren, backgroundsProperty,backgroundsProperty.getFillArray().get(i),infoWidth,buttonWidth,buttonHeight);
            paintsPaneChildren.add(paintField);
        }
    }


    void setPrimaryAddColorButton(double infoWidth,double buttonWidth,double buttonHeight) {
        primaryEmptySpace.setVisible(false);
        primaryAddColorButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddColorButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddColorButton.setOnAction(event -> {
            AppColor newAppColor=new AppColor(Color.TRANSPARENT);
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppColor,infoWidth,buttonWidth,buttonHeight);
            backgroundsProperty.addFill(0,newAppColor);
            paintsPaneChildren.add(1, newPaintField);
        });
    }

    void setPrimaryAddLinearGradientButton(double infoWidth,double buttonWidth,double buttonHeight) {
        primaryEmptySpace.setVisible(false);
        primaryAddLinearGradientButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddLinearGradientButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddLinearGradientButton.setOnAction(event -> {
            AppLinearGradient newAppLinearGradient=new AppLinearGradient(new LinearGradient(0,0,0,0,true, CycleMethod.NO_CYCLE));
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppLinearGradient,infoWidth,buttonWidth,buttonHeight);
            backgroundsProperty.addFill(0,newAppLinearGradient);
            paintsPaneChildren.add(1, newPaintField);
        });
    }

    void setPrimaryAddRadialGradientButton(double infoWidth,double buttonWidth,double buttonHeight) {
        primaryEmptySpace.setVisible(false);
        primaryAddRadialGradientButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddRadialGradientButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddRadialGradientButton.setOnAction(event -> {
            AppRadialGradient newAppRadialGradient=new AppRadialGradient(new RadialGradient(0,0,0,0,0,true, CycleMethod.NO_CYCLE));
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppRadialGradient,infoWidth,buttonWidth,buttonHeight);
            backgroundsProperty.addFill(0,newAppRadialGradient);
            paintsPaneChildren.add(1, newPaintField);
        });
    }

    void setGraphics(double infoWidth,double buttonWidth,double buttonHeight){
        setCustomSize(primaryEmptySpace,infoWidth,buttonHeight);
        setCustomSize(primaryAddColorButton,buttonWidth,buttonHeight);
        setCustomSize(primaryAddLinearGradientButton,buttonWidth,buttonHeight);
        setCustomSize(primaryAddRadialGradientButton,buttonWidth,buttonHeight);
        primaryAddColorButton.setGraphic(new SolidColorIcon(buttonWidth*0.6,buttonHeight*0.6,new Color(0.7,0.2,0.5,1)));
        primaryAddLinearGradientButton.setGraphic(new LinearGradientIcon(buttonWidth*0.6,buttonHeight*0.6,new LinearGradient(0,0,1,0,true,CycleMethod.NO_CYCLE,new Stop(0,Color.RED),new Stop(1,Color.BLUE))));
        primaryAddRadialGradientButton.setGraphic(new RadialGradientIcon(buttonWidth*0.6,buttonHeight*0.6,new RadialGradient(0,0,0.5,0.5,0.6,true,CycleMethod.NO_CYCLE,new Stop(0,Color.BLUE),new Stop(1,Color.RED))));
    }

}
