package com.example.panels.paint;

import com.example.apppaints.AppColor;
import com.example.apppaints.AppLinearGradient;
import com.example.apppaints.AppRadialGradient;
import com.example.structures.abstracts.AppPaint;
import com.example.exceptions.AppException;
import com.example.icons.*;
import com.example.structures.enums.AppExceptionEnum;
import com.example.structures.enums.PaintTypeEnum;
import com.example.styles.CSSProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import static com.example.tools.Tools.*;

public class PaintField extends GridPane {

    Button addColorButton = new Button();
    Button addLinearGradientButton = new Button();
    Button addRadialGradientButton = new Button();
    
    Button removeButton = new Button();

    Button settingsButton = new Button();

    Label infoLabel = new Label();


    Label emptySpace = new Label();

    PaintField(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty, AppPaint appPaint, double infoWidth, double buttonWidth, double buttonHeight) {

        super();

        setRemoveButtonBehavior(paintsPaneChildren, cssProperty, appPaint);
        setAddColorButtonBehavior(paintsPaneChildren, cssProperty,infoWidth,buttonWidth,buttonHeight);
        setAddLinearGradientButtonBehavior(paintsPaneChildren, cssProperty,infoWidth,buttonWidth,buttonHeight);
        setAddRadialGradientButtonBehavior(paintsPaneChildren, cssProperty,infoWidth,buttonWidth,buttonHeight);
        setSettingsButtonBehavior(appPaint);
        setInfoLabelBehavior(appPaint);

        addRow(0, infoLabel, new HBox(settingsButton, removeButton));
        addRow(1, emptySpace, new HBox(addColorButton, addLinearGradientButton,addRadialGradientButton));

        setGraphics(infoWidth, buttonWidth, buttonHeight);

    }
    
    void setAddRadialGradientButtonBehavior(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty, double infoWidth, double buttonWidth, double buttonHeight){
        addRadialGradientButton.setOnAction(event -> {
            AppRadialGradient newAppRadialGradient = new AppRadialGradient(new RadialGradient(0,0,0,0,0,true, CycleMethod.NO_CYCLE),uuid(50));
            PaintField newPaintField = new PaintField(paintsPaneChildren, cssProperty, newAppRadialGradient,infoWidth,buttonWidth,buttonHeight);
            cssProperty.addFill(paintsPaneChildren.indexOf(this), newAppRadialGradient);
            paintsPaneChildren.add(paintsPaneChildren.indexOf(this) + 1, newPaintField);
        });
        addRadialGradientButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add new radial gradient."));
        addRadialGradientButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }


    void setAddColorButtonBehavior(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty, double infoWidth, double buttonWidth, double buttonHeight) {
        addColorButton.setOnAction(event -> {
            AppColor newAppColor = new AppColor(Color.TRANSPARENT,uuid(50));
            PaintField newPaintField = new PaintField(paintsPaneChildren, cssProperty, newAppColor,infoWidth,buttonWidth,buttonHeight);
            cssProperty.addFill(paintsPaneChildren.indexOf(this), newAppColor);
            paintsPaneChildren.add(paintsPaneChildren.indexOf(this) + 1, newPaintField);
        });
        addColorButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add new color."));
        addColorButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setRemoveButtonBehavior(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty, AppPaint appPaint) {
        removeButton.setOnAction(event -> {
            cssProperty.removeFill(appPaint);
            paintsPaneChildren.remove(this);
        });
        removeButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Remove this stop"));
        removeButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setSettingsButtonBehavior(AppPaint appPaint) {
        settingsButton.setOnAction(event -> {
            if (appPaint.getType().equals(PaintTypeEnum.Color.getPaintType())) {
                ColorManagementPanel colorManagementPanel = new ColorManagementPanel((AppColor) appPaint);
                colorManagementPanel.show(Stage.getWindows().get(Stage.getWindows().size()-1));
            } else if (appPaint.getType().equals(PaintTypeEnum.LinearGradient.getPaintType())) {
                LinearGradientManagementPanel linearGradientManagementPanel = new LinearGradientManagementPanel((AppLinearGradient) appPaint);
                linearGradientManagementPanel.show(Stage.getWindows().get(Stage.getWindows().size()-1));
            } else if (appPaint.getType().equals(PaintTypeEnum.RadialGradient.getPaintType())) {
                RadialGradientManagementPanel radialGradientManagementPanel = new RadialGradientManagementPanel((AppRadialGradient) appPaint);
                radialGradientManagementPanel.show(Stage.getWindows().get(Stage.getWindows().size()-1));
            } else {
                throw new AppException(AppExceptionEnum.UnexpectedError);
            }

        });
    }

    void setAddLinearGradientButtonBehavior(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty, double infoWidth, double buttonWidth, double buttonHeight){
        addLinearGradientButton.setOnAction(event -> {
            AppLinearGradient newAppLinearGradient = new AppLinearGradient(new LinearGradient(0,0,0,0,true, CycleMethod.NO_CYCLE),uuid(50));
            PaintField newPaintField = new PaintField(paintsPaneChildren, cssProperty, newAppLinearGradient,infoWidth,buttonWidth,buttonHeight);
            cssProperty.addFill(paintsPaneChildren.indexOf(this), newAppLinearGradient);
            paintsPaneChildren.add(paintsPaneChildren.indexOf(this) + 1, newPaintField);
        });
        addLinearGradientButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add new gradient."));
        addLinearGradientButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setGraphics(double infoWidth,double buttonWidth,double buttonHeight) {
        setCustomSize(addColorButton,buttonWidth,buttonHeight);
        setCustomSize(addLinearGradientButton,buttonWidth,buttonHeight);
        setCustomSize(addRadialGradientButton,buttonWidth,buttonHeight);
        setCustomSize(removeButton,buttonWidth,buttonHeight);
        setCustomSize(settingsButton,buttonWidth,buttonHeight);
        setCustomSize(emptySpace,infoWidth,buttonHeight);
        setCustomWidth(infoLabel,infoWidth);
        infoLabel.setWrapText(true);
        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
        addColorButton.setGraphic(new SolidColorIcon(buttonWidth*0.6,buttonHeight*0.6,new Color(0.7,0.2,0.5,1)));
        addLinearGradientButton.setGraphic(new LinearGradientIcon(buttonWidth*0.6,buttonHeight*0.6,new LinearGradient(0,0,1,0,true,CycleMethod.NO_CYCLE,new Stop(0,Color.RED),new Stop(1,Color.BLUE))));
        addRadialGradientButton.setGraphic(new RadialGradientIcon(buttonWidth*0.6,buttonHeight*0.6,new RadialGradient(0,0,0.5,0.5,0.6,true,CycleMethod.NO_CYCLE,new Stop(0,Color.BLUE),new Stop(1,Color.RED))));
        settingsButton.setGraphic(new SettingsIcon(8,100,80,50,40,15,Color.BLACK));
    }

    void setInfoLabelBehavior(AppPaint appPaint){
        if (appPaint.getType().equals(PaintTypeEnum.Color.getPaintType())) {
            infoLabel.setText("Solid");
        } else if (appPaint.getType().equals(PaintTypeEnum.LinearGradient.getPaintType())) {
            infoLabel.setText("LinearGradient");
        } else if (appPaint.getType().equals(PaintTypeEnum.RadialGradient.getPaintType())) {
            infoLabel.setText("RadialGradient");
        } else {
            throw new AppException(AppExceptionEnum.UnexpectedError);
        }
    }

}