package com.boom.panels.paint;

import com.boom.apppaints.AppRadialGradient;
import com.boom.apppaints.AppStop;
import com.boom.icons.PlusSignIcon;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;

public class RadialGradientManagementPanel extends Popup {

    ScrollPane scrollMain=new ScrollPane();

    public VBox main=new VBox();

    VBox stopsPane = new VBox();

    ObservableList<Node> stopsPaneChildren = stopsPane.getChildren();

    Button primaryAddButton = new Button();
    TextField centerX = new TextField();
    TextField centerY = new TextField();
    TextField focusAngle = new TextField();
    TextField focusDistance = new TextField();
    TextField radius = new TextField();
    CheckBox isProportional = new CheckBox("isProportional");
    Label primaryEmptySpace = new Label();
    GridPane rgProperties = new GridPane();
    Label centerXLabel = new Label("Center (X)");
    Label centerYLabel = new Label("Start (Y)");
    Label focusAngleLabel = new Label("Focus angle (deg)");
    Label focusDistanceLabel = new Label("Focus distance (relative))");
    Label radiusLabel = new Label("Radius");
    public RadialGradientManagementPanel(AppRadialGradient appRadialGradient) {

        super();

//        showAndWait();
//        requestFocus();
//        Stage g;
//        g.initOwner();
//        setSt

//        print(this.getN);

//        this.

//        scrollMain

        setCustomSize(scrollMain,400,600);

        setHideOnEscape(true);
        setAutoHide(true);
        setAutoFix(true);

        rgProperties.addRow(0, new VBox(centerXLabel, centerX), new Rectangle(), new VBox(focusAngleLabel, focusAngle));
        rgProperties.addRow(1, new VBox(centerYLabel, centerY), new Rectangle(), new VBox(focusDistanceLabel, focusDistance));
        rgProperties.addRow(2, new VBox(radiusLabel, radius));
        rgProperties.addRow(3, isProportional);


//        Scene scene=new Scene(scrollMain);
//        setScene(scene);
        getContent().add(scrollMain);

        scrollMain.setContent(main);

        main.getChildren().addAll(rgProperties, stopsPane);

        stopsPaneChildren.addAll(new HBox(primaryEmptySpace, primaryAddButton));

        primaryEmptySpace.setVisible(false);

        setGraphics();

        setFocusAngleBehavior(appRadialGradient);
        setFocusDistanceBehavior(appRadialGradient);
        setCenterXBehavior(appRadialGradient);
        setCenterYBehavior(appRadialGradient);
        setRadiusBehavior(appRadialGradient);

        setPrimaryAddButton(appRadialGradient);
        registerRadialGradient(appRadialGradient);

        setIsProportionalBehavior(appRadialGradient);

    }

    public void registerRadialGradient(AppRadialGradient AppRadialGradient) {
        for (int i = 0; i < AppRadialGradient.getStopsSize(); i++) {
            StopField stopField = new StopField(stopsPaneChildren, AppRadialGradient, AppRadialGradient.getAppStop(i));
            stopsPaneChildren.add(stopField);
        }
    }

    void setCenterXBehavior(AppRadialGradient AppRadialGradient) {
        centerX.setText(AppRadialGradient.centerX.getValue().toString());
        centerX.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                centerX.setText(AppRadialGradient.centerX.getValue().toString());
            }
        });
        centerX.setOnKeyTyped(keyEvent -> {
            try {
                AppRadialGradient.centerX.set(Double.parseDouble(centerX.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setCenterYBehavior(AppRadialGradient AppRadialGradient) {
        centerY.setText(AppRadialGradient.centerY.getValue().toString());
        centerY.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                centerY.setText(AppRadialGradient.centerY.getValue().toString());
            }
        });
        centerY.setOnKeyTyped(keyEvent -> {
            try {
                AppRadialGradient.centerY.set(Double.parseDouble(centerY.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setGraphics() {
        setCustomSize(primaryAddButton, 30, 30);
        primaryAddButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

    void setIsProportionalBehavior(AppRadialGradient AppRadialGradient) {
        isProportional.setSelected(true);
        AppRadialGradient.isProportional.bind(isProportional.selectedProperty());
    }

    void setPrimaryAddButton(AppRadialGradient AppRadialGradient) {
        primaryAddButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddButton.setOnAction(event -> {
            AppStop newAppStop;
//            print("stopsPaneChildren = "+stopsPaneChildren.size());
            if (stopsPaneChildren.size() == 1) {
                newAppStop = new AppStop(new Stop(0, Color.WHITE));
            } else {
                newAppStop = new AppStop(new Stop(AppRadialGradient.getAppStop(0).offset.get(), (Color) AppRadialGradient.getAppStop(0).appColor.get()));
            }
            StopField newStopField = new StopField(stopsPaneChildren, AppRadialGradient, newAppStop);
            AppRadialGradient.addAppStop(0,newAppStop);
            stopsPaneChildren.add(1, newStopField);
        });
    }

    void setFocusAngleBehavior(AppRadialGradient AppRadialGradient) {
        focusAngle.setText(AppRadialGradient.focusAngle.getValue().toString());
        focusAngle.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                focusAngle.setText(AppRadialGradient.focusAngle.getValue().toString());
            }
        });
        focusAngle.setOnKeyTyped(keyEvent -> {
            try {
                AppRadialGradient.focusAngle.set(Double.parseDouble(focusAngle.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setRadiusBehavior(AppRadialGradient AppRadialGradient) {
        radius.setText(AppRadialGradient.radius.getValue().toString());
        radius.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                radius.setText(AppRadialGradient.radius.getValue().toString());
            }
        });
        radius.setOnKeyTyped(keyEvent -> {
            try {
                AppRadialGradient.radius.set(Double.parseDouble(radius.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setFocusDistanceBehavior(AppRadialGradient AppRadialGradient) {
        focusDistance.setText(AppRadialGradient.focusDistance.getValue().toString());
        focusDistance.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                focusDistance.setText(AppRadialGradient.focusDistance.getValue().toString());
            }
        });
        focusDistance.setOnKeyTyped(keyEvent -> {
            try {
                AppRadialGradient.focusDistance.set(Double.parseDouble(focusDistance.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }


}
