package com.boom.panels.paint;

import com.boom.apppaints.AppColor;
import com.boom.apppaints.AppLinearGradient;
import com.boom.apppaints.AppRadialGradient;
import com.boom.structures.abstracts.AppPaint;
import com.boom.exceptions.AppException;
import com.boom.icons.*;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.styles.CSSProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.boom.configuration.Configs.*;
import static com.boom.tools.Tools.*;

public  class PaintField extends GridPane {

    Button addColorButton = new Button();
    Button addLinearGradientButton = new Button();
    Button addRadialGradientButton = new Button();

    Button removeButton = new Button();

    Button settingsButton = new Button();

    Label infoLabel = new Label();


    Label emptySpace = new Label();

    PaintField(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty, AppPaint appPaint) {

        super();

        setRemoveButtonBehavior(paintsPaneChildren, cssProperty, appPaint);
        setAddColorButtonBehavior(paintsPaneChildren, cssProperty);
        setAddLinearGradientButtonBehavior(paintsPaneChildren, cssProperty);
        setAddRadialGradientButtonBehavior(paintsPaneChildren, cssProperty);
        setSettingsButtonBehavior(appPaint);
        setInfoLabelBehavior(appPaint);

        addRow(0, infoLabel, new HBox(settingsButton, removeButton));
        addRow(1, emptySpace, new VBox(new HBox(addColorButton, addLinearGradientButton), addRadialGradientButton));

        setGraphics();

    }

    void setAddRadialGradientButtonBehavior(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty) {
        addRadialGradientButton.setOnAction(event -> {
            AppRadialGradient newAppRadialGradient = new AppRadialGradient(new RadialGradient(0, 0, 0, 0, 0, true, CycleMethod.NO_CYCLE));
            PaintField newPaintField = new PaintField(paintsPaneChildren, cssProperty, newAppRadialGradient);
            cssProperty.addFill(paintsPaneChildren.indexOf(this), newAppRadialGradient);
            paintsPaneChildren.add(paintsPaneChildren.indexOf(this) + 1, newPaintField);
        });
        addRadialGradientButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add radial gradient."));
        addRadialGradientButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }


    void setAddColorButtonBehavior(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty) {
        addColorButton.setOnAction(event -> {
            AppColor newAppColor = new AppColor(Color.TRANSPARENT);
            PaintField newPaintField = new PaintField(paintsPaneChildren, cssProperty, newAppColor);
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
        removeButton.setOnMouseEntered(mouseEvent -> {
            temp = infoLabel.getText();
            infoLabel.setText("Remove this paint.");
        });
        removeButton.setOnMouseExited(mouseEvent -> infoLabel.setText(temp));
    }

    String temp;

    void setSettingsButtonBehavior(AppPaint appPaint) {
        settingsButton.setOnAction(event -> {
            if (appPaint.type.equals(Color.class.getName())) {
                ColorManagementPanel colorManagementPanel = new ColorManagementPanel((AppColor) appPaint);
                colorManagementPanel.show(Stage.getWindows().get(Stage.getWindows().size() - 1));
            } else if (appPaint.type.equals(LinearGradient.class.getName())) {
                LinearGradientManagementPanel linearGradientManagementPanel = new LinearGradientManagementPanel((AppLinearGradient) appPaint);
                linearGradientManagementPanel.show(Stage.getWindows().get(Stage.getWindows().size() - 1));
            } else if (appPaint.type.equals(RadialGradient.class.getName())) {
                RadialGradientManagementPanel radialGradientManagementPanel = new RadialGradientManagementPanel((AppRadialGradient) appPaint);
                radialGradientManagementPanel.show(Stage.getWindows().get(Stage.getWindows().size() - 1));
            } else {
                throw new AppException(AppExceptionEnum.UnexpectedError);
            }
        });
        settingsButton.setOnMouseEntered(mouseEvent -> {
            temp = infoLabel.getText();
            infoLabel.setText("Configure this paint.");
        });
        settingsButton.setOnMouseExited(mouseEvent -> infoLabel.setText(temp));
    }

    void setAddLinearGradientButtonBehavior(ObservableList<Node> paintsPaneChildren, CSSProperty cssProperty) {
        addLinearGradientButton.setOnAction(event -> {
            AppLinearGradient newAppLinearGradient = new AppLinearGradient(new LinearGradient(0, 0, 0, 0, true, CycleMethod.NO_CYCLE));
            PaintField newPaintField = new PaintField(paintsPaneChildren, cssProperty, newAppLinearGradient);
            cssProperty.addFill(paintsPaneChildren.indexOf(this), newAppLinearGradient);
            paintsPaneChildren.add(paintsPaneChildren.indexOf(this) + 1, newPaintField);
        });
        addLinearGradientButton.setOnMouseEntered(mouseEvent -> emptySpace.setText("Add linear gradient."));
        addLinearGradientButton.setOnMouseExited(mouseEvent -> emptySpace.setText(""));
    }

    void setGraphics() {
        setCustomSize(addColorButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(addLinearGradientButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(addRadialGradientButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(removeButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(settingsButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(emptySpace, PAINT_MANAGEMENT_PANEL_EMPTY_SPACE_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(infoLabel, PAINT_MANAGEMENT_PANEL_EMPTY_SPACE_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
//        infoLabel.setWrapText(true);
//        emptySpace.setWrapText(true);
        removeButton.setGraphic(new MinusSignIcon(3, 20, new Color(1, 0, 0, 1), new Color(0, 0, 0, 1), 0.3));
        addColorButton.setGraphic(new SolidColorIcon(PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH * 0.6, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT * 0.6, new Color(0.7, 0.2, 0.5, 1)));
        addLinearGradientButton.setGraphic(new LinearGradientIcon(PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH * 0.6, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT * 0.6, new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.RED), new Stop(1, Color.BLUE))));
        addRadialGradientButton.setGraphic(new RadialGradientIcon(PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH * 0.6, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT * 0.6, new RadialGradient(0, 0, 0.5, 0.5, 0.6, true, CycleMethod.NO_CYCLE, new Stop(0, Color.BLUE), new Stop(1, Color.RED))));
        settingsButton.setGraphic(new SettingsIcon(8, 100, 80, 50, 40, 15, Color.BLACK));

        infoLabel.setFont(new Font(17));
        emptySpace.setFont(new Font(17));
    }

    void setInfoLabelBehavior(AppPaint appPaint) {
        if (appPaint.type.equals(Color.class.getName())) {
            infoLabel.setText("Solid");
        } else if (appPaint.type.equals(LinearGradient.class.getName())) {
            infoLabel.setText("LinearGradient");
        } else if (appPaint.type.equals(RadialGradient.class.getName())) {
            infoLabel.setText("RadialGradient");
        } else {
            throw new AppException(AppExceptionEnum.UnexpectedError);
        }
    }

}