package com.boom.panels.paint;

import com.boom.apppaints.AppColor;
import com.boom.apppaints.AppLinearGradient;
import com.boom.apppaints.AppRadialGradient;
import com.boom.icons.LinearGradientIcon;
import com.boom.icons.RadialGradientIcon;
import com.boom.icons.SolidColorIcon;
import com.boom.styles.CSSProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.Font;

import static com.boom.configuration.Configs.*;
import static com.boom.tools.Tools.setCustomSize;


public class PaintManagementPanel extends VBox {

    CSSProperty backgroundsProperty;


    VBox paintsPane = new VBox();
    ObservableList<Node> paintsPaneChildren = paintsPane.getChildren();
    Button primaryAddColorButton = new Button();
    Button primaryAddLinearGradientButton = new Button();
    Button primaryAddRadialGradientButton = new Button();
    Label primaryEmptySpace = new Label();

    public PaintManagementPanel() {

        super();


        getChildren().add(paintsPane);


        setPrimaryAddColorButton();
        setPrimaryAddLinearGradientButton();
        setPrimaryAddRadialGradientButton();

        setGraphics();

        primaryEmptySpace.setFont(new Font(17));


    }

    public void registerBackground(CSSProperty backgroundsProperty) {
        this.backgroundsProperty = backgroundsProperty;

        paintsPaneChildren.setAll(new HBox(primaryEmptySpace, new VBox(new HBox(primaryAddColorButton, primaryAddLinearGradientButton), primaryAddRadialGradientButton)));

        for (int i = 0; i < backgroundsProperty.getFillArray().size(); i++) {
            PaintField paintField = new PaintField(paintsPaneChildren, backgroundsProperty, backgroundsProperty.getFillArray().get(i));
            paintsPaneChildren.add(paintField);
        }
    }


    void setPrimaryAddColorButton() {
        primaryAddColorButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setText("Add new color."));
        primaryAddColorButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setText(""));
        primaryAddColorButton.setOnAction(event -> {
            AppColor newAppColor = new AppColor(Color.TRANSPARENT);
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppColor);
            backgroundsProperty.addFill(0, newAppColor);
            paintsPaneChildren.add(1, newPaintField);
        });
    }

    void setPrimaryAddLinearGradientButton() {
        primaryAddLinearGradientButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setText("Add linear gradient."));
        primaryAddLinearGradientButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setText(""));
        primaryAddLinearGradientButton.setOnAction(event -> {
            AppLinearGradient newAppLinearGradient = new AppLinearGradient(new LinearGradient(0, 0, 0, 0, true, CycleMethod.NO_CYCLE));
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppLinearGradient);
            backgroundsProperty.addFill(0, newAppLinearGradient);
            paintsPaneChildren.add(1, newPaintField);
        });
    }

    void setPrimaryAddRadialGradientButton() {
        primaryAddRadialGradientButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setText("Add radial gradient."));
        primaryAddRadialGradientButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setText(""));
        primaryAddRadialGradientButton.setOnAction(event -> {
            AppRadialGradient newAppRadialGradient = new AppRadialGradient(new RadialGradient(0, 0, 0, 0, 0, true, CycleMethod.NO_CYCLE));
            PaintField newPaintField = new PaintField(paintsPaneChildren, backgroundsProperty, newAppRadialGradient);
            backgroundsProperty.addFill(0, newAppRadialGradient);
            paintsPaneChildren.add(1, newPaintField);
        });
    }

    void setGraphics() {
        setCustomSize(primaryEmptySpace, PAINT_MANAGEMENT_PANEL_EMPTY_SPACE_WIDTH, 2 * PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(primaryAddColorButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(primaryAddLinearGradientButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        setCustomSize(primaryAddRadialGradientButton, PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT);
        primaryAddColorButton.setGraphic(new SolidColorIcon(PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH * 0.6, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT * 0.6, new Color(0.7, 0.2, 0.5, 1)));
        primaryAddLinearGradientButton.setGraphic(new LinearGradientIcon(PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH * 0.6, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT * 0.6, new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.RED), new Stop(1, Color.BLUE))));
        primaryAddRadialGradientButton.setGraphic(new RadialGradientIcon(PAINT_MANAGEMENT_PANEL_BUTTON_WIDTH * 0.6, PAINT_MANAGEMENT_PANEL_BUTTON_HEIGHT * 0.6, new RadialGradient(0, 0, 0.5, 0.5, 0.6, true, CycleMethod.NO_CYCLE, new Stop(0, Color.BLUE), new Stop(1, Color.RED))));
    }

}