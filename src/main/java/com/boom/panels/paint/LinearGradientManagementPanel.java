package com.boom.panels.paint;

import com.boom.icons.PlusSignIcon;
import com.boom.apppaints.AppLinearGradient;
import com.boom.apppaints.AppStop;
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

public class LinearGradientManagementPanel extends Popup {

    ScrollPane scrollMain=new ScrollPane();

    public VBox main=new VBox();

    VBox stopsPane = new VBox();

    ObservableList<Node> stopsPaneChildren = stopsPane.getChildren();

    Button primaryAddButton = new Button();
    TextField startX = new TextField();
    TextField startY = new TextField();
    TextField endX = new TextField();
    TextField endY = new TextField();
    CheckBox isProportional = new CheckBox("isProportional");
    Label primaryEmptySpace = new Label();
    GridPane lgProperties = new GridPane();
    Label startXLabel = new Label("Start (X)");
    Label startYLabel = new Label("Start (Y)");
    Label endXLabel = new Label("End (X)");
    Label endYLabel = new Label("End (Y)");
    public LinearGradientManagementPanel(AppLinearGradient appLinearGradient) {

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

        lgProperties.addRow(0, new VBox(startXLabel, startX), new Rectangle(), new VBox(endXLabel, endX));
        lgProperties.addRow(1, new VBox(startYLabel, startY), new Rectangle(), new VBox(endYLabel, endY));
        lgProperties.addRow(2, isProportional);


//        Scene scene=new Scene(scrollMain);
//        setScene(scene);
        getContent().add(scrollMain);

        scrollMain.setContent(main);

        main.getChildren().addAll(lgProperties, stopsPane);

        stopsPaneChildren.addAll(new HBox(primaryEmptySpace, primaryAddButton));

        primaryEmptySpace.setVisible(false);

        setGraphics();

        setStartXBehavior(appLinearGradient);
        setStartYBehavior(appLinearGradient);
        setEndXBehavior(appLinearGradient);
        setEndYBehavior(appLinearGradient);

        setPrimaryAddButton(appLinearGradient);
        registerLinearGradient(appLinearGradient);

        setIsProportionalBehavior(appLinearGradient);

    }

    public void registerLinearGradient(AppLinearGradient appLinearGradient) {
        for (int i = 0; i < appLinearGradient.getStopsSize(); i++) {
            StopField stopField = new StopField(stopsPaneChildren, appLinearGradient, appLinearGradient.getAppStop(i));
            stopsPaneChildren.add(stopField);
        }
    }

    void setEndXBehavior(AppLinearGradient appLinearGradient) {
        endX.setText(appLinearGradient.endX.getValue().toString());
        endX.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                endX.setText(appLinearGradient.endX.getValue().toString());
            }
        });
        endX.setOnKeyTyped(keyEvent -> {
            try {
                appLinearGradient.endX.set(Double.parseDouble(endX.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setEndYBehavior(AppLinearGradient appLinearGradient) {
        endY.setText(appLinearGradient.endY.getValue().toString());
        endY.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                endY.setText(appLinearGradient.endY.getValue().toString());
            }
        });
        endY.setOnKeyTyped(keyEvent -> {
            try {
                appLinearGradient.endY.set(Double.parseDouble(endY.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setGraphics() {
        setCustomSize(primaryAddButton, 30, 30);
        primaryAddButton.setGraphic(new PlusSignIcon(10, 3, new Color(0, 0.7, 0, 1), new Color(0, 0, 0, 1), 0.3));
    }

    void setIsProportionalBehavior(AppLinearGradient appLinearGradient) {
        isProportional.setSelected(true);
        appLinearGradient.isProportional.bind(isProportional.selectedProperty());
    }

    void setPrimaryAddButton(AppLinearGradient appLinearGradient) {
        primaryAddButton.setOnMouseEntered(mouseEvent -> primaryEmptySpace.setVisible(true));
        primaryAddButton.setOnMouseExited(mouseEvent -> primaryEmptySpace.setVisible(false));
        primaryAddButton.setOnAction(event -> {
            AppStop newAppStop;
//            print("stopsPaneChildren = "+stopsPaneChildren.size());
            if (stopsPaneChildren.size() == 1) {
                newAppStop = new AppStop(new Stop(0, Color.WHITE));
            } else {
                newAppStop = new AppStop(new Stop(appLinearGradient.getAppStop(0).offset.get(), (Color) appLinearGradient.getAppStop(0).appColor.get()));
            }
            StopField newStopField = new StopField(stopsPaneChildren, appLinearGradient, newAppStop);
            appLinearGradient.addAppStop(0,newAppStop);
            stopsPaneChildren.add(1, newStopField);
        });
    }

    void setStartXBehavior(AppLinearGradient appLinearGradient) {
        startX.setText(appLinearGradient.startX.getValue().toString());
        startX.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                startX.setText(appLinearGradient.startX.getValue().toString());
            }
        });
        startX.setOnKeyTyped(keyEvent -> {
            try {
                appLinearGradient.startX.set(Double.parseDouble(startX.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }

    void setStartYBehavior(AppLinearGradient appLinearGradient) {
        startY.setText(appLinearGradient.startY.getValue().toString());
        startY.focusedProperty().addListener((a, b, c) -> {
            if (!c) {
                startY.setText(appLinearGradient.startY.getValue().toString());
            }
        });
        startY.setOnKeyTyped(keyEvent -> {
            try {
                appLinearGradient.startY.set(Double.parseDouble(startY.getText()));
            } catch (NumberFormatException e) {
                print(e);
            }
        });
    }


}
