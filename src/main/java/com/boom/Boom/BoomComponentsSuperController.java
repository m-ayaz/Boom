package com.boom.Boom;

//import com.boom.appcharts.number_number.AppAreaChart_NumberNumber;
//import com.boom.appcharts.number_number.AppLineChart_NumberNumber;
//import com.boom.appcharts.number_number.AppScatterChart_NumberNumber;
//import com.boom.appcharts.number_string.AppAreaChart_NumberString;
//import com.boom.appcharts.number_string.AppLineChart_NumberString;
//import com.boom.appcharts.number_string.AppScatterChart_NumberString;
//import com.boom.appcharts.string_number.AppAreaChart_StringNumber;
//import com.boom.appcharts.string_number.AppLineChart_StringNumber;
//import com.boom.appcharts.string_number.AppScatterChart_StringNumber;

import com.boom.appcharts.AppAxisChartWrapper;
import com.boom.apppaints.AppLinearGradient;
import com.boom.apppaints.AppRadialGradient;
import com.boom.appshapes.*;
import com.boom.configuration.Configs;
import com.boom.controllers.DynamicDragRectangle;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.controllers.eventhandlers.mousehandler.MainCanvasMouseHandler;
import com.boom.exceptions.AppException;
import com.boom.icons.*;
import com.boom.indicators.*;
import com.boom.panels.chart.ChartManagementPanel;
import com.boom.panels.paint.PaintManagementPanel;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.enums.AppNodeTypeEnum;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import org.json.JSONArray;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.boom.configuration.Configs.*;
import static com.boom.projectmanager.ProjectManager.*;
import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;

public class BoomComponentsSuperController {

    StringProperty tempObjectName = new SimpleStringProperty();
    List<Double> parsedStrokeDashArray = new ArrayList<>();
    DynamicDragRectangle dynamicDragRectangle = new DynamicDragRectangle();
    AppEllipse tempEllipse = new AppEllipse(0, 0);
    AppPolyline tempPolyline = new AppPolyline();
    AppPolygon tempPolygon = new AppPolygon();
    AppArc tempArc = new AppArc(0, 0, 0, 270, ArcType.ROUND);
    AppRectangle tempRectangle = new AppRectangle(0, 0, 0, 0);
    AppLine tempLine = new AppLine(0, 0, 0, 0);
    AppQuadCurve tempQuadCurve = new AppQuadCurve(0, 0, 0, 0, 0, 0);
    AppCubicCurve tempCubicCurve = new AppCubicCurve(0, 0, 0, 0, 0, 0, 0, 0);
    AppAxisChartWrapper tempAppAxisChartWrapper = new AppAxisChartWrapper(0, 0);
    LittleAppAxisChartWrapperOnCursor littleAppAxisChartWrapperOnCursor = new LittleAppAxisChartWrapperOnCursor();
    LittleLineChartOnCursor littleLineChartOnCursor = new LittleLineChartOnCursor();
    LittleBarChartOnCursor littleBarChartOnCursor = new LittleBarChartOnCursor();
    LittleScatterChartOnCursor littleScatterChartOnCursor = new LittleScatterChartOnCursor();
    LittleAreaChartOnCursor littleAreaChartOnCursor = new LittleAreaChartOnCursor();
    LittleEllipseOnCursor littleEllipseOnCursor = new LittleEllipseOnCursor();
    LittlePolylineOnCursor littlePolylineOnCursor = new LittlePolylineOnCursor();
    LittlePolygonOnCursor littlePolygonOnCursor = new LittlePolygonOnCursor();
    LittleArcOnCursor littleArcOnCursor = new LittleArcOnCursor();
    LittleRectangleOnCursor littleRectangleOnCursor = new LittleRectangleOnCursor();
    LittleLineOnCursor littleLineOnCursor = new LittleLineOnCursor();
    LittleQuadCurveOnCursor littleQuadCurveOnCursor = new LittleQuadCurveOnCursor();
    LittleCubicCurveOnCursor littleCubicCurveOnCursor = new LittleCubicCurveOnCursor();

    //    com.boom.panels.chart.number_number.ChartManagementPanel chartManagementPanel_NN = new com.boom.panels.chart.number_number.ChartManagementPanel();
//    ChartManagementPanel_NumberString chartManagementPanel_NS = new ChartManagementPanel_NumberString();
//    ChartManagementPanel_StringNumber chartManagementPanel_SN = new ChartManagementPanel_StringNumber();
    LittleTextOnCursor littleTextOnCursor = new LittleTextOnCursor();
    MainCanvasItemsHandler mainCanvasItemsHandler;
    boolean isCopyRequested;
    boolean isCutRequested;
    ObservableList<Node> mainCanvasChildren;
    Line rotationHandle = new Line();
    RotationIcon rotationIcon = new RotationIcon();
    List<ScalingIcon> scalingIcons = new ArrayList<>(8);
    Circle rotationFixedPoint = new Circle(10);
    Circle scalingFixedPoint = new Circle(10);
    List<AppNode> validObjects = new ArrayList<>();
    ObjectProperty<Cursor> mainCanvasCursor;
    double selectedObjectsCurrentCenterOfMassPosX;
    double selectedObjectsNewCenterOfMassPosX;
    double selectedObjectsCurrentCenterOfMassPosY;
    double selectedObjectsNewCenterOfMassPosY;
    PaintManagementPanel paintManagementPanel = new PaintManagementPanel();
    SelectedObjectsController selectedObjectsController;
    MainCanvasMouseHandler mainCanvasMouseHandler;
    FileChooser fileChooser = new FileChooser();
    Pane rasterCanvas = new Pane();
    FileChooser.ExtensionFilter jsonExtension = new FileChooser.ExtensionFilter("JSON Files (*.json)", "*.json");
    FileChooser.ExtensionFilter texExtension = new FileChooser.ExtensionFilter("Boom TeX Files (*.tex)", "*.tex");
    FileChooser.ExtensionFilter pngExtension = new FileChooser.ExtensionFilter("Portable Network Graphics Files (*.png)", "*.png");
    FileChooser.ExtensionFilter bmpExtension = new FileChooser.ExtensionFilter("Bitmap Files (*.bmp)", "*.bmp");
    FileChooser.ExtensionFilter jpegExtension = new FileChooser.ExtensionFilter("Joint Photographic Experts Group Files (*.jpeg)", "*.jpeg");
    FileChooser.ExtensionFilter svgExtension = new FileChooser.ExtensionFilter("Boom Scalable Vector Graphics Files (*.svg)", "*.svg");
    @FXML
    private ScrollPane chartTabContainer;
    @FXML
    private Label cursorPositionLabel;
    @FXML
    private Button ellipseButton;
    @FXML
    private SplitPane mainAppPlayGround;
    @FXML
    private AnchorPane mainCanvas;
    @FXML
    private TextField objectProp1Input;
    @FXML
    private Label objectProp1Label;
    @FXML
    private TextField objectProp2Input;
    @FXML
    private Label objectProp2Label;
    @FXML
    private Button rectangleButton;
    @FXML
    private TextField selectedObjectsCenterXInput;
    @FXML
    private TextField selectedObjectsCenterYInput;
    @FXML
    private ScrollPane visualEffectsTab;
    @FXML
    private Button arcButton;
    @FXML
    private Button lineButton;
    @FXML
    private Button polygonButton;
    @FXML
    private Button cubicCurveButton;
    @FXML
    private Button quadCurveButton;
    @FXML
    private Button polylineButton;

    @FXML
    void appAxisChartButtonOnAction(ActionEvent event) {
        tempObjectName.set("AppAxisChartWrapper");
    }

//    @FXML
//    void loadButtonOnAction1(ActionEvent event) {
//
//    }

    @FXML
    void appWindowOnKeyTyped(KeyEvent event) {

        // todo temporary. Change the key.
        if (event.getCode().getChar().equals("K")) {
//            mainAppPlayGround.setDividerPositions(1 - objectPropertiesPane.getPrefWidth() * 1.1 / mainAppPlayGround.getWidth());
//            if(mainAppPlayGround.getDividerPositions()[0]==0) {
            mainAppPlayGround.setDividerPositions(1 - 400 / mainAppPlayGround.getWidth());
//            }else{
//                mainAppPlayGround.setDividerPositions(1);
//            }
        }
    }

    @FXML
    void arcButtonOnAction(ActionEvent event) {
        tempObjectName.set("Arc");
    }

    void bindVisibilities() {
        objectProp1Input.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
        objectProp2Input.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
        objectProp1Label.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
        objectProp2Label.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
//        paintManagementPanel.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
//        mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().addListener((a, b, c) -> {
//            if (c.equals(1) && mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().get(0).getClass().getSuperclass().getSimpleName().equals(ElementTypes.AppXYChartStructure.getNodeType())) {
//                chartDataPane.setVisible(true);
//                print(seriesList);
//                AppXYChart xyChart = (AppXYChart) mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().get(0);
//                seriesList.getItems().clear();
//                for (int i = 0; i < ((XYChart) xyChart.node).getData().size(); i++) {
//                    seriesList.getItems().add(new StringBuilder("Series" + i));
//                }
////                seriesList.setIt
////                seriesList.
//            } else {
//                chartDataPane.setVisible(false);
//            }
//        });
//        chartDataPane.visibleProperty().bind());
    }

    @FXML
    void cubicCurveButtonOnAction(ActionEvent event) {
        tempObjectName.set("CubicCurve");
    }

    @FXML
    void ellipseButtonOnAction(ActionEvent event) {
        tempObjectName.set("Ellipse");
    }

    @FXML
    void initialize() {

        Configs.setDefaultConfig();

        mainCanvas.setPrefWidth(3000);
        mainCanvas.setPrefHeight(3000);

        mainCanvasChildren = mainCanvas.getChildren();

        instantiateObjects();

        settleIcons();

        selectedObjectsController = new SelectedObjectsController(rotationIcon, scalingIcons, rotationFixedPoint);

        mainCanvasItemsHandler = new MainCanvasItemsHandler(mainCanvasChildren, validObjects,
                rotationHandle, rotationIcon, scalingIcons, rotationFixedPoint, scalingFixedPoint,
                dynamicDragRectangle, tempEllipse, tempPolyline, tempCubicCurve, tempQuadCurve,
                tempPolygon, tempArc, tempRectangle, tempLine, tempAppAxisChartWrapper,
                littleLineChartOnCursor, littleBarChartOnCursor, littleScatterChartOnCursor, littleAreaChartOnCursor,
                littleEllipseOnCursor, littleAppAxisChartWrapperOnCursor, littlePolylineOnCursor,
                littleCubicCurveOnCursor, littleQuadCurveOnCursor, littlePolygonOnCursor, littleArcOnCursor,
                littleRectangleOnCursor, littleLineOnCursor, selectedObjectsController);

        tempObjectName.set(AppNodeTypeEnum.DynamicDragRectangle.getNodeType());

        bindVisibilities();

        setUpObjectsSizeInput();

        setUpObjectsCenterOfMassInput();

        mainCanvasMouseHandler = new MainCanvasMouseHandler(mainCanvasChildren, validObjects,
                selectedObjectsController, rotationHandle, rotationFixedPoint, scalingFixedPoint, cursorPositionLabel,
                tempObjectName, dynamicDragRectangle, tempEllipse, tempPolyline, tempCubicCurve, tempQuadCurve,
                tempPolygon, tempArc, tempRectangle, tempLine, tempAppAxisChartWrapper,
                littleAppAxisChartWrapperOnCursor, littleEllipseOnCursor, littlePolylineOnCursor,
                littleCubicCurveOnCursor, littleQuadCurveOnCursor, littlePolygonOnCursor, littleArcOnCursor,
                littleRectangleOnCursor, littleLineOnCursor, mainCanvasItemsHandler, parsedStrokeDashArray,
                scalingIcons, rotationIcon);

        mainCanvas.setOnMouseMoved(mainCanvasMouseHandler);
        mainCanvas.setOnMouseDragged(mainCanvasMouseHandler);
        mainCanvas.setOnMouseClicked(mainCanvasMouseHandler);
        mainCanvas.setOnMousePressed(mainCanvasMouseHandler);
        mainCanvas.setOnMouseReleased(mainCanvasMouseHandler);

        visualEffectsTab.setContent(paintManagementPanel);
        paintManagementPanel.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));

        fileChooser.getExtensionFilters().addAll(jsonExtension, texExtension, svgExtension
//                ,pngExtension, bmpExtension
        );

        chartTabContainer.setContent(chartManagementPanel);

        print("Done initialization!");

        AppAxisChartWrapper appAxisChartWrapper=new AppAxisChartWrapper(200,200);

        mainCanvasItemsHandler.addToMainCanvas(appAxisChartWrapper);

        appAxisChartWrapper.affineTransform.prependTranslation(100,100);


//        AppLinearGradient appLinearGradient = new AppLinearGradient(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
//                new Stop(0, new Color(1, 0, 0, 1)), new Stop(1, new Color(0, 0, 1, 1))));
//
//        AppRectangle appRectangle = new AppRectangle(200, 200, 0, 0);
//        appRectangle.backgroundStyle.addFill(appLinearGradient);
//        AppEllipse appEllipse = new AppEllipse(200, 200);
//        appEllipse.backgroundStyle.addFill(new AppRadialGradient(new RadialGradient(0, 1, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
//                new Stop(1, new Color(0, 1, 0, 1)), new Stop(0, new Color(1, 1, 0, 1)))));
//
//
//        appRectangle.affineTransform.prependTranslation(400, 200);
//        appEllipse.affineTransform.prependTranslation(200, 200);
//
//        Random random = new Random();
//
//        appRectangle.affineTransform.prependScale(random.nextDouble() + 1, random.nextDouble() + 1);
//        appEllipse.affineTransform.prependScale(random.nextDouble() + 1, random.nextDouble() + 1);
//        appEllipse.affineTransform.prependRotation(random.nextDouble() * 45);
//
//        mainCanvasItemsHandler.addToMainCanvas(appRectangle);
//        mainCanvasItemsHandler.addToMainCanvas(appEllipse);

    }

    void instantiateObjects() {

        // Instantiate in memory.
        for (short i = 0; i < 8; i++) {
            scalingIcons.add(new ScalingIcon(10, 6, 10, 10, -45 * (i - 1), new Color(0.5, 0, 0, 1), new Color(0.5, 0, 0, 1), 0.2));
        }

        // Set up styles.
        rotationHandle.getStrokeDashArray().setAll(10.0, 5.0);

        // Set up colors.
        rotationFixedPoint.setFill(new Color(1, 0, 0, 1));
        rotationFixedPoint.setStroke(new Color(1, 0, 0, 1));
        scalingFixedPoint.setFill(new Color(0, 0, 1, 1));
        scalingFixedPoint.setStroke(new Color(0, 0, 1, 1));
    }

    @FXML
    void lineButtonOnAction(ActionEvent event) {
        tempObjectName.set("Line");
    }

    @FXML
    void loadButtonOnAction(ActionEvent event) throws Exception {
        mainCanvasItemsHandler.selectAll();
        mainCanvasItemsHandler.removeSelectedObjectsFromMainCanvas();
        fileChooser.getExtensionFilters().add(jsonExtension);
        String filePath = fileChooser.showOpenDialog(new ContextMenu()).getAbsolutePath();
        JSONArray jsonArray = new JSONArray(Files.readString(Path.of(filePath)));
        List<AppNode> appNodes = importProjectFromJSON(jsonArray);
        appNodes.forEach(appNode -> mainCanvasItemsHandler.addToMainCanvas(appNode));
    }

    @FXML
    void mainCanvasHolderOnKeyPressed(KeyEvent event) {
    }

    @FXML
    void mainCanvasHolderOnKeyReleased(KeyEvent event) {

        KeyCombination cutShortcut = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY);
        KeyCombination copyShortcut = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
        KeyCombination pasteShortcut = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY);
        KeyCombination selectAllShortcut = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_ANY);
        KeyCombination zoomIn = new KeyCodeCombination(KeyCode.ADD, KeyCombination.CONTROL_ANY);
        KeyCombination zoomOut = new KeyCodeCombination(KeyCode.SUBTRACT, KeyCombination.CONTROL_ANY);

        print(event.getCode());

        if (event.getCode().equals(KeyCode.DELETE)) {
            mainCanvasItemsHandler.removeSelectedObjectsFromMainCanvas();
            isCutRequested = false;
            isCopyRequested = false;
        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
            mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
            dynamicDragRectangle.reset();
            isCutRequested = false;
            isCopyRequested = false;
        } else if (cutShortcut.match(event)) {
            isCutRequested = true;
            isCopyRequested = false;
        } else if (copyShortcut.match(event)) {
            isCopyRequested = true;
            isCutRequested = false;
        } else if (pasteShortcut.match(event) && isCopyRequested) {
            mainCanvasItemsHandler.pasteCopiedSelectedObjects(mainCanvasMouseHandler.getX(), mainCanvasMouseHandler.getY());
        } else if (pasteShortcut.match(event) && isCutRequested) {
            mainCanvasItemsHandler.pasteCutSelectedObjects(mainCanvasMouseHandler.getX(), mainCanvasMouseHandler.getY());
        } else if (selectAllShortcut.match(event)) {
            mainCanvasItemsHandler.selectAll();
        } else if (zoomIn.match(event)) {
//            applyZoom(1.41);
        } else if (zoomOut.match(event)) {
//            applyZoom(1 / 1.41);
        }


        //temporary
//        if (KeyCode.J.equals(event.getCode())) {
////            print("appLabel = "+appLabel.getText());
//        }

    }

    @FXML
    void mainCanvasHolderOnKeyTyped(KeyEvent event) {
    }

    @FXML
    void objectProp1InputOnKeyTyped(KeyEvent event) {
//        AppNode selectedShape = mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().get(0);
//        if (selectedShape.getType().equals(NodeTypeEnum.Ellipse.getNodeType())) {
//            selectedShape.setWidth(Double.parseDouble(objectProp1Input.getText()));
//        } else if (selectedShape.getType().equals(NodeTypeEnum.Rectangle.getNodeType())) {
//            selectedShape.setWidth(Double.parseDouble(objectProp1Input.getText()));
//        } else if (selectedShape.getType().equals(NodeTypeEnum.Line.getNodeType())) {
//            selectedShape.setWidth(Double.parseDouble(objectProp1Input.getText()));
//        }
    }

    @FXML
    void objectProp2InputOnKeyTyped(KeyEvent event) {
//        AppNode selectedShape = mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().get(0);
//        if (selectedShape.getType().equals(NodeTypeEnum.Ellipse.getNodeType())) {
//            selectedShape.setHeight(Double.parseDouble(objectProp2Input.getText()));
//        } else if (selectedShape.getType().equals(NodeTypeEnum.Rectangle.getNodeType())) {
//            selectedShape.setHeight(Double.parseDouble(objectProp2Input.getText()));
//        } else if (selectedShape.getType().equals(NodeTypeEnum.Line.getNodeType())) {
//            selectedShape.setHeight(Double.parseDouble(objectProp2Input.getText()));
//        }
    }

    @FXML
    void polygonButtonOnAction(ActionEvent event) {
        tempObjectName.set("Polygon");
    }

    @FXML
    void polylineButtonOnAction(ActionEvent event) {
        tempObjectName.set("Polyline");
    }

    @FXML
    void quadCurveButtonOnAction(ActionEvent event) {
        tempObjectName.set("QuadCurve");
    }

    @FXML
    void rectangleButtonOnAction(ActionEvent event) {
        tempObjectName.set("Rectangle");
    }

    /**
     * Saves current canvas to file.
     * If svg format is selected,............
     *
     * @param event
     */
    @FXML
    void saveButtonOnAction(ActionEvent event) {

        try {
            File saveFile = fileChooser.showSaveDialog(null);
            PrintWriter printWriter = new PrintWriter(saveFile);
            if (fileChooser.getSelectedExtensionFilter().equals(jsonExtension)) {
                printWriter.println(exportProjectAsJSON(validObjects));
            } else if (fileChooser.getSelectedExtensionFilter().equals(texExtension)) {
                printWriter.println(exportProjectAsTeX(validObjects));
            } else if (fileChooser.getSelectedExtensionFilter().equals(svgExtension)) {
                List<AppNode> validObjectsCopy = new ArrayList<>();
                validObjects.forEach(appNode -> {
                    validObjectsCopy.add(appNode.copy());
                    selectedObjectsController.select(appNode);
                });
                validObjectsCopy.forEach(appNode -> appNode.affineTransform.prependTranslation(-selectedObjectsController.getMinX(), -selectedObjectsController.getMinY()));
                printWriter.println(exportProjectAsSVG(validObjectsCopy, selectedObjectsController.getMaxX() - selectedObjectsController.getMinX(), selectedObjectsController.getMaxY() - selectedObjectsController.getMinY()));
                selectedObjectsController.unselectAll();
            } else if (fileChooser.getSelectedExtensionFilter().equals(pngExtension)) {
                rasterCanvas.getChildren().setAll(validObjects.stream().map(appNode -> appNode.copy().wrappedNode).collect(Collectors.toList()));
//                SnapshotParameters snapshotParameters = new SnapshotParameters();
//                snapshotParameters.setFill(new Color(0, 0, 0, 0));
//                mainCanvas.snapshot(snapshotParameters, new WritableImage((int) mainCanvas.getWidth(), (int) mainCanvas.getHeight()));
//                mainCanvas.snapshot(snapshotParameters, null);
                WritableImage image = rasterCanvas.snapshot(new SnapshotParameters(), null);
                print(rasterCanvas);
                print(image);
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", saveFile);
            } else if (fileChooser.getSelectedExtensionFilter().equals(bmpExtension)) {
                rasterCanvas.getChildren().setAll(validObjects.stream().map(appNode -> appNode.copy().wrappedNode).collect(Collectors.toList()));
//                SnapshotParameters snapshotParameters = new SnapshotParameters();
//                snapshotParameters.setFill(new Color(0, 0, 0, 0));
//                mainCanvas.snapshot(snapshotParameters, new WritableImage((int) mainCanvas.getWidth(), (int) mainCanvas.getHeight()));
//                mainCanvas.snapshot(snapshotParameters, null);
                WritableImage image = rasterCanvas.snapshot(new SnapshotParameters(), null);
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "bmp", saveFile);
            } else {
                throw new AppException(AppExceptionEnum.UnknownFileExtension);
            }
            printWriter.close();
        } catch (Exception e) {
            print(e);
        }
    }

    @FXML
    void selectedObjectsCenterXInputOnKeyTyped(KeyEvent event) {
//        selectedObjectsCurrentCenterOfMassPosX = mainCanvasItemsHandler.getSelectedObjectsController().centerXProperty().get();
//        try {
//            selectedObjectsNewCenterOfMassPosX = Double.parseDouble(selectedObjectsCenterXInput.getText());
//            mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().forEach(obj -> obj.affineTransform.prependTranslation(selectedObjectsNewCenterOfMassPosX - selectedObjectsCurrentCenterOfMassPosX, 0));
//        } catch (Exception ignored) {
//
//        }
    }

    @FXML
    void selectedObjectsCenterYInputOnKeyTyped(KeyEvent event) {
//        selectedObjectsCurrentCenterOfMassPosY = mainCanvasItemsHandler.getSelectedObjectsController().centerYProperty().get();
//        try {
//            selectedObjectsNewCenterOfMassPosY = Double.parseDouble(selectedObjectsCenterYInput.getText());
//            mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().forEach(obj -> obj.affineTransform.prependTranslation(0, selectedObjectsNewCenterOfMassPosY - selectedObjectsCurrentCenterOfMassPosY));
//        } catch (Exception ignored) {
//
//        }

    }

    @FXML
    void selectorButtonOnAction(ActionEvent event) {
        tempObjectName.set(AppNodeTypeEnum.DynamicDragRectangle.getNodeType());
        mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
        littleEllipseOnCursor.hide();
        littleRectangleOnCursor.hide();
        littleLineOnCursor.hide();
        littleLineChartOnCursor.hide();
        littleBarChartOnCursor.hide();
        littleScatterChartOnCursor.hide();
        littleAreaChartOnCursor.hide();
        littleQuadCurveOnCursor.hide();
        littleAppAxisChartWrapperOnCursor.hide();
    }

    void setUpObjectsCenterOfMassInput() {
        mainCanvasItemsHandler.getSelectedObjectsController().centerXProperty().addListener((a, b, c) -> {
            selectedObjectsCenterXInput.setText(String.valueOf(c));
        });
        mainCanvasItemsHandler.getSelectedObjectsController().centerYProperty().addListener((a, b, c) -> {
            selectedObjectsCenterYInput.setText(String.valueOf(c));
        });
        selectedObjectsCenterXInput.focusedProperty().addListener((_1, _2, newVal) -> {
            if (newVal) {
                selectedObjectsCenterXInput.setText(String.valueOf(mainCanvasItemsHandler.getSelectedObjectsController().centerXProperty().get()));
            } else {
                selectedObjectsCurrentCenterOfMassPosX = mainCanvasItemsHandler.getSelectedObjectsController().centerXProperty().get();
                try {
                    selectedObjectsNewCenterOfMassPosX = Double.parseDouble(selectedObjectsCenterXInput.getText());
                    mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().forEach(obj -> {
                        obj.affineTransform.prependTranslation(selectedObjectsNewCenterOfMassPosX - selectedObjectsCurrentCenterOfMassPosX, 0);
                    });
                } catch (Exception ignored) {

                }
            }
        });
        selectedObjectsCenterYInput.focusedProperty().addListener((_1, _2, newVal) -> {
            if (newVal) {
                selectedObjectsCenterYInput.setText(String.valueOf(mainCanvasItemsHandler.getSelectedObjectsController().centerYProperty().get()));
            } else {
                selectedObjectsCurrentCenterOfMassPosY = mainCanvasItemsHandler.getSelectedObjectsController().centerYProperty().get();
                try {
                    selectedObjectsNewCenterOfMassPosY = Double.parseDouble(selectedObjectsCenterYInput.getText());
                    mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().forEach(obj -> obj.affineTransform.prependTranslation(0, selectedObjectsNewCenterOfMassPosY - selectedObjectsCurrentCenterOfMassPosY));
                } catch (Exception ignored) {

                }
            }
        });
    }

    void setUpObjectsSizeInput() {
        mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().addListener((ListChangeListener<AppNode>) change -> {


            chartTabContainer.setVisible(false);

//            print(change);
            if (change.getList().size() == 1) {
                AppNode selectedShape = change.getList().get(0);
//                print(selectedShape.getType());
//                print(uuid(10));

                paintManagementPanel.registerBackground(selectedShape.backgroundStyle);
//                paintManagementPanel.setVisible(true);
//                print(selectedShape.type);
//                print(NodeTypeEnum.LineChart_NN.getNodeType());
                switch (selectedShape.getType()) {

                    case "Ellipse" -> {
                        objectProp1Label.setText("Radius (X)");
                        objectProp2Label.setText("Radius (Y)");
                        objectProp1Input.setText("" + ((AppEllipse) selectedShape).radiusX.get());
                        objectProp2Input.setText("" + ((AppEllipse) selectedShape).radiusY.get());
                    }
                    case "Rectangle" -> {
                        objectProp1Label.setText("Width");
                        objectProp2Label.setText("Height");
                        objectProp1Input.setText("" + ((AppRectangle) selectedShape).width.get());
                        objectProp2Input.setText("" + ((AppRectangle) selectedShape).height.get());
                    }
                    case "Line" -> {
//                    objectProp1Label.setText("Start (X)");
//                    objectProp2Label.setText("Start (Y)");
//                    objectProp1Input.setText("" + ((AppLine) selectedShape).startX.get());
//                    objectProp2Input.setText("" + ((AppLine) selectedShape).startY.get());
                    }
                    case "AppAxisChartWrapper" -> {
                        objectProp1Label.setText("Start (X)");
                        objectProp2Label.setText("Start (Y)");
                        try {
                            chartManagementPanel.registerChart((AppAxisChartWrapper) selectedShape);
                        } catch (Exception e) {
                            print(e);
                        }
                        chartTabContainer.setVisible(true);
                    }


                    default -> chartTabContainer.setVisible(false);
                }
            }
        });
    }

    ChartManagementPanel chartManagementPanel=new ChartManagementPanel();

    void settleIcons() {
//        lineChartButton.setGraphic(new LineChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
//        areaChartButton.setGraphic(new AreaChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
//        barChartButton.setGraphic(new BarChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
//        scatterChartButton.setGraphic(new ScatterChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
//        pieChartButton.setGraphic(new PieChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
        ellipseButton.setGraphic(new EllipseIcon(SHAPE_BUTTON_ICON_WIDTH / 2, SHAPE_BUTTON_ICON_HEIGHT / 2, Color.valueOf(ELLIPSE_BUTTON_ICON_COLOR), Color.BLACK, 1));
        arcButton.setGraphic(new ArcIcon(SHAPE_BUTTON_ICON_WIDTH / 2, SHAPE_BUTTON_ICON_HEIGHT / 2, 45, 270, Color.valueOf(ARC_BUTTON_ICON_COLOR), Color.BLACK, 1));
        rectangleButton.setGraphic(new RectangleIcon(SHAPE_BUTTON_ICON_WIDTH, SHAPE_BUTTON_ICON_HEIGHT, Color.valueOf(RECTANGLE_BUTTON_ICON_COLOR), Color.BLACK, 1));
        polygonButton.setGraphic(new PolygonIcon(SHAPE_BUTTON_ICON_WIDTH, SHAPE_BUTTON_ICON_HEIGHT, Color.valueOf(POLYGON_BUTTON_ICON_COLOR), Color.BLACK, 1));
        lineButton.setGraphic(new LineIcon(0, 0, 36, 36, Color.valueOf(LINE_BUTTON_ICON_COLOR), CURVE_BUTTON_ICONS_STROKE_WIDTH));
        quadCurveButton.setGraphic(new QuadCurveIcon(0, 0, 36, 0, 36, 36, Color.TRANSPARENT, Color.valueOf(QUAD_CURVE_BUTTON_ICON_COLOR), CURVE_BUTTON_ICONS_STROKE_WIDTH));
        cubicCurveButton.setGraphic(new CubicCurveIcon(0, 0, 12 + 50, 0, 24 - 50, 36, 36, 36, Color.TRANSPARENT, Color.valueOf(CUBIC_CURVE_BUTTON_ICON_COLOR), CURVE_BUTTON_ICONS_STROKE_WIDTH));
        polylineButton.setGraphic(new PolylineIcon(30, 30, Color.TRANSPARENT, Color.rgb(255, 128, 0, 1), CURVE_BUTTON_ICONS_STROKE_WIDTH));
    }

    @FXML
    void textButtonOnAction(ActionEvent event) {

    }


}
