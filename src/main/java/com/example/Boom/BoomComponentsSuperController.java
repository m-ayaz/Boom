package com.example.Boom;

import com.example.appcharts.number_number.AppAreaChart_NumberNumber;
import com.example.appcharts.number_number.AppLineChart_NumberNumber;
import com.example.appcharts.number_number.AppScatterChart_NumberNumber;
import com.example.appcharts.number_string.AppAreaChart_NumberString;
import com.example.appcharts.number_string.AppLineChart_NumberString;
import com.example.appcharts.number_string.AppScatterChart_NumberString;
import com.example.appcharts.string_number.AppAreaChart_StringNumber;
import com.example.appcharts.string_number.AppLineChart_StringNumber;
import com.example.appcharts.string_number.AppScatterChart_StringNumber;
import com.example.appshapes.AppEllipse;
import com.example.appshapes.AppLine;
import com.example.appshapes.AppRectangle;
import com.example.configuration.Configs;
import com.example.controllers.DynamicDragRectangle;
import com.example.controllers.MainCanvasItemsHandler;
import com.example.controllers.eventhandlers.MainCanvasMouseHandler;
import com.example.panels.chart.number_number.ChartManagementPane_NumberNumber;
import com.example.panels.chart.number_string.ChartManagementPane_NumberString;
import com.example.panels.chart.string_number.ChartManagementPane_StringNumber;
import com.example.exceptions.AppException;
import com.example.icons.*;
import com.example.indicators.LittleAreaChartOnCursor;
import com.example.indicators.LittleBarChartOnCursor;
import com.example.indicators.LittleLineChartOnCursor;
import com.example.indicators.LittleScatterChartOnCursor;
import com.example.indicators.LittleEllipseOnCursor;
import com.example.indicators.LittleLineOnCursor;
import com.example.indicators.LittleRectangleOnCursor;
import com.example.structures.AppExceptionEnum;
import com.example.structures.AppNode;
import com.example.structures.AppXYChart;
import com.example.structures.NodeTypeEnum;
import javafx.beans.property.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Affine;
import javafx.stage.FileChooser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.example.projectmanager.ProjectManager.*;
import static com.example.tools.Tools.*;

public class BoomComponentsSuperController {

    DoubleProperty dragStartPosX = new SimpleDoubleProperty();
    DoubleProperty dragStartPosY = new SimpleDoubleProperty();
    DoubleProperty currentPosX = new SimpleDoubleProperty();
    DoubleProperty currentPosY = new SimpleDoubleProperty();
    DoubleProperty previousPosX = new SimpleDoubleProperty();
    DoubleProperty previousPosY = new SimpleDoubleProperty();
    StringProperty tempObjectName = new SimpleStringProperty();
    List<Double> parsedStrokeDashArray = new ArrayList<>();

    DynamicDragRectangle dynamicDragRectangle = new DynamicDragRectangle();
    AppEllipse tempEllipse = new AppEllipse(0, 0);
    AppRectangle tempRectangle = new AppRectangle(0, 0);
    AppLine tempLine = new AppLine(0, 0, 0, 0);

    AppLineChart_NumberNumber tempLineChart_NN = new AppLineChart_NumberNumber(0, 0);
    AppLineChart_NumberString tempLineChart_NS = new AppLineChart_NumberString(0, 0);
    AppLineChart_StringNumber tempLineChart_SN = new AppLineChart_StringNumber(0, 0);

    AppAreaChart_NumberNumber tempAreaChart_NN = new AppAreaChart_NumberNumber(0, 0);
    AppAreaChart_NumberString tempAreaChart_NS = new AppAreaChart_NumberString(0, 0);
    AppAreaChart_StringNumber tempAreaChart_SN = new AppAreaChart_StringNumber(0, 0);

//    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
//    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);


    AppScatterChart_NumberNumber tempScatterChart_NN = new AppScatterChart_NumberNumber(0, 0);
    AppScatterChart_NumberString tempScatterChart_NS = new AppScatterChart_NumberString(0, 0);
    AppScatterChart_StringNumber tempScatterChart_SN = new AppScatterChart_StringNumber(0, 0);
    //    AppText tempText = new AppText(0, 0, "");
    LittleLineChartOnCursor littleLineChartOnCursor = new LittleLineChartOnCursor();
    LittleBarChartOnCursor littleBarChartOnCursor = new LittleBarChartOnCursor();
    LittleScatterChartOnCursor littleScatterChartOnCursor = new LittleScatterChartOnCursor();
    LittleAreaChartOnCursor littleAreaChartOnCursor = new LittleAreaChartOnCursor();
    LittleEllipseOnCursor littleEllipseOnCursor = new LittleEllipseOnCursor();
    LittleRectangleOnCursor littleRectangleOnCursor = new LittleRectangleOnCursor();
    LittleLineOnCursor littleLineOnCursor = new LittleLineOnCursor();

    ChartManagementPane_NumberNumber chartManagementPane_NN = new ChartManagementPane_NumberNumber();
    ChartManagementPane_NumberString chartManagementPane_NS = new ChartManagementPane_NumberString();
    ChartManagementPane_StringNumber chartManagementPane_SN = new ChartManagementPane_StringNumber();
    //    LittleTextOnCursor littleTextOnCursor = new LittleTextOnCursor();
    MainCanvasItemsHandler mainCanvasItemsHandler;
    boolean isCopyRequested;
    boolean isCutRequested;
    ObservableList<Node> mainCanvasChildren;
    Line rotationHandle = new Line();
    RotationIcon rotationIcon = new RotationIcon();
    List<ScalingIcon> scalingIcons = new ArrayList<>();
    Circle rotationFixedPoint = new Circle(10);
    Circle scalingFixedPoint = new Circle(10);
    List<AppNode> canvasPermanentObjects = new ArrayList<>();
    ObjectProperty<Cursor> mainCanvasCursor;
    double selectedObjectsCurrentCenterOfMassPosX;
    double selectedObjectsNewCenterOfMassPosX;
    double selectedObjectsCurrentCenterOfMassPosY;
    double selectedObjectsNewCenterOfMassPosY;

    @FXML
    private BorderPane appWindow;

    @FXML
    private Button areaChartButton;

    @FXML
    private Button barChartButton;

    @FXML
    private ScrollPane chartTabContainer;

    @FXML
    private Tab chartsTab;

    @FXML
    private Label cursorPositionLabel;

    @FXML
    private Button ellipseButton;

    @FXML
    private Tab fileTab;

    @FXML
    private Tab fileTab1;

    @FXML
    private Tab fileTab11;

    @FXML
    private ColorPicker fillSolidColorPicker;

    @FXML
    private Button lineButton;

    @FXML
    private Button lineChartButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button loadButton1;

    @FXML
    private Button loadButton11;

    @FXML
    private SplitPane mainAppPlayGround;

    @FXML
    private AnchorPane mainCanvas;

    @FXML
    private ScrollPane mainCanvasHolder;

    @FXML
    private TextField objectProp1Input;

    @FXML
    private Label objectProp1Label;

    @FXML
    private TextField objectProp2Input;

    @FXML
    private Label objectProp2Label;

    @FXML
    private AnchorPane objectPropertiesPane;

    @FXML
    private Button pieChartButton;

    @FXML
    private Button rectangleButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button saveButton1;

    @FXML
    private Button saveButton11;

    @FXML
    private Button scatterChartButton;

    @FXML
    private TextField selectedObjectsCenterXInput;

    @FXML
    private TextField selectedObjectsCenterYInput;

    @FXML
    private Button selectorButton;

    @FXML
    private Button shapesRectangleButton111;

    @FXML
    private Button shapesRectangleButton12;

    @FXML
    private Tab shapesTab;

    @FXML
    private TextField strokeDashArrayInput;

    @FXML
    private ColorPicker strokeSolidColorPicker;

    @FXML
    private TextField strokeWidthInput;

    @FXML
    private Button textButton;

    @FXML
    private Button triangleButton;

    @FXML
    private Button visualArtsGradientColorButton;

    @FXML
    private Tab visualArtsTab;

    @FXML
    private ComboBox<String> xAxisType;

    @FXML
    private ComboBox<String> yAxisType;


    @FXML
    void appWindowOnKeyTyped(KeyEvent event) {
        // todo temporary. Change the key.
        if (event.getCode().getChar().equals("K")) {
//            mainAppPlayGround.setDividerPositions(1 - objectPropertiesPane.getPrefWidth() * 1.1 / mainAppPlayGround.getWidth());
//            if(mainAppPlayGround.getDividerPositions()[0]==0) {
            mainAppPlayGround.setDividerPositions(1 - 330 / mainAppPlayGround.getWidth());
//            }else{
//                mainAppPlayGround.setDividerPositions(1);
//            }
        }
    }

    @FXML
    void areaChartButtonOnAction(ActionEvent event) throws AppException {
        switch (xAxisType.getValue() + yAxisType.getValue()) {
            case "NumberNumber" -> tempObjectName.set(NodeTypeEnum.AreaChart_NN.getNodeType());
            case "NumberString" -> tempObjectName.set(NodeTypeEnum.AreaChart_NS.getNodeType());
            case "StringNumber" -> tempObjectName.set(NodeTypeEnum.AreaChart_SN.getNodeType());
            case "StringString" -> throw new AppException(AppExceptionEnum.InvalidXYAxisType);
            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
        }
    }

    @FXML
    void barChartButtonOnAction(ActionEvent event) throws AppException {
        switch (xAxisType.getValue() + yAxisType.getValue()) {
            case "NumberNumber", "StringString" -> throw new AppException(AppExceptionEnum.InvalidXYAxisType);
            case "NumberString" -> tempObjectName.set(NodeTypeEnum.BarChart_NS.getNodeType());
            case "StringNumber" -> tempObjectName.set(NodeTypeEnum.BarChart_SN.getNodeType());
            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
        }
    }

    void bindVisibilities() {
        objectProp1Input.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
        objectProp2Input.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
        objectProp1Label.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
        objectProp2Label.visibleProperty().bind(mainCanvasItemsHandler.getSelectedObjectsController().bufferSizeProperty().isEqualTo(1));
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
    void ellipseButtonOnAction(ActionEvent event) {
        tempObjectName.set(NodeTypeEnum.Ellipse.getNodeType());
    }

    @FXML
    void initialize() {

        Configs.setDefaultConfig();

//        print("\n"+"\t".repeat(0)+"text");
//        print("\n"+"\t".repeat(1)+"text");
//        print("\n"+"\t".repeat(2)+"text");
//        print("\n"+"\t".repeat(3)+"text");
//        print("\n"+"\t".repeat(4)+"text");


//        Configs.test();


//        Axis<Number> x;
//        NumberAxis y;

        mainCanvas.setPrefWidth(3000);
        mainCanvas.setPrefHeight(3000);

        mainCanvasChildren = mainCanvas.getChildren();

        mainCanvasCursor = mainCanvas.cursorProperty();

        instantiateObjects();

        settleIcons();

        strokeDashArrayInput.focusedProperty().addListener((_1, _2, newValue) -> {
            if (!newValue) {
                strokeDashArrayInput.setText(Arrays.stream(strokeDashArrayInput.getText().split(",")).filter(obj -> obj.matches("[\\d.]+")).collect(Collectors.joining()));
                parsedStrokeDashArray = Arrays.stream(strokeDashArrayInput.getText().split(",")).filter(obj -> obj.matches("[\\d.]+")).map(Double::parseDouble).toList();
            }
        });

        mainCanvasItemsHandler = new MainCanvasItemsHandler(mainCanvasChildren, canvasPermanentObjects,
                rotationHandle, rotationIcon, scalingIcons, rotationFixedPoint, scalingFixedPoint,
                dynamicDragRectangle, tempEllipse, tempRectangle, tempLine, tempLineChart_NN,
                tempLineChart_NS, tempLineChart_SN, tempAreaChart_NN, tempAreaChart_NS, tempAreaChart_SN,
                tempScatterChart_NN, tempScatterChart_NS, tempScatterChart_SN, littleLineChartOnCursor,
//                 littleBarChartOnCursor,
                littleScatterChartOnCursor,
                littleAreaChartOnCursor,
                littleEllipseOnCursor,
                littleRectangleOnCursor,
                littleLineOnCursor);

        tempObjectName.set(NodeTypeEnum.DynamicDragRectangle.getNodeType());

        bindVisibilities();

        setUpObjectsSizeInput();

        setUpObjectsCenterOfMassInput();

        fillSolidColorPicker.setOnAction(event -> mainCanvasItemsHandler.applyFillColorChangesToSelectedObjects(fillSolidColorPicker.getValue()));

        strokeSolidColorPicker.setOnAction(event -> mainCanvasItemsHandler.applyStrokeColorChangesToSelectedObjects(strokeSolidColorPicker.getValue()));

        new MainCanvasMouseHandler(mainCanvas, cursorPositionLabel,
                tempObjectName, dynamicDragRectangle, littleLineChartOnCursor,
                littleScatterChartOnCursor, littleAreaChartOnCursor, littleEllipseOnCursor,
                littleRectangleOnCursor, littleLineOnCursor, mainCanvasItemsHandler, currentPosX,
                currentPosY, previousPosX, previousPosY, dragStartPosX, dragStartPosY, fillSolidColorPicker,
                strokeSolidColorPicker, strokeWidthInput, parsedStrokeDashArray, scalingIcons,
                rotationIcon, canvasPermanentObjects);

        xAxisType.getItems().addAll("Number", "String");
        yAxisType.getItems().addAll("Number", "String");

        xAxisType.setValue("Number");
        yAxisType.setValue("Number");

//        xAxisType.setOnAction(event -> {
//            tempObjectName.set(tempObjectName.get().split("_")[0]+xAxisType.getValue().equals());
//        });
//        xAxisType.setOnAction(event -> {
////            print(uuid(20));
////            if(xAxisType.getValue().eq)
//
////                tempObjectName.set(tempObjectName.get().substring(0,tempObjectName.get().s));
//        });
//
//        print("12345678".substring(0,8));

//        tempObjectName.s











        Random rnd=new Random();
        AppAreaChart_NumberNumber areaChart=new AppAreaChart_NumberNumber(400,400);

//        mainCanvasItemsHandler.addToMainCanvas(areaChart);

        for(int j=0;j<2;j++) {
            areaChart.addSeries(j);
            for (int i = 0; i < 5; i++) {
                areaChart.addData(i, rnd.nextDouble(), j);
            }
        }

        AreaChart<Number,Number> areaChart1=new AreaChart<>(new NumberAxis(),new NumberAxis());

//        Rectangle areaChart1=new Rectangle();

        for(int i=0;i<10;i++){
            areaChart1.getData().add(new XYChart.Series<>());
            for(int j=0;j<10;j++){
                areaChart1.getData().get(i).getData().add(new XYChart.Data<>(rnd.nextDouble(),rnd.nextDouble()));
            }
        }

        Circle circle=new Circle(20);
        circle.setFill(Color.RED);

        circle.setTranslateX(300);
        circle.setTranslateY(300);

//        mainCanvasChildren.addAll(areaChart1,circle);

        areaChart1.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 0%, rgba(255,0,255,1),rgba(255,255,0,1));");

        setSize(areaChart1,400,400);
//        areaChart1.setWidth(400);
//        areaChart1.setHeight(400);

//        areaChart1.setTranslateX(100);
//        areaChart1.setTranslateY(100);

        Affine affine=new Affine();

        affine.appendTranslation(100,100);

        areaChart1.getTransforms().add(affine);

        areaChart1.setOnMouseMoved(mouseEvent -> {
            affine.appendRotation(1,200,200);
//            areaChart1.setRo;
        });

//        areaChart1.setStyle("-fx-background-color: rgba(0,0,255,0.5),linear-gradient(from 0% 0% to 100% 0%, rgba(255,0,255,0.2),rgba(255,255,0,0.2))," +
//                "linear-gradient(from 0% 0% to  0% 100%, rgba(0,255,0,0.2),rgba(255,0,0,0.2));");


//        linear-gradient(from 0px 0px to 0px 4px, derive(-fx-background, -4%), derive(-fx-background, 10%));
//
//        for(int j=0;j<20;j++) {
//            print("===============");
//            print(areaChart.getSeriesAreaStyles().get(j).fill.get());
//            print(areaChart.getSeriesAreaStyles().get(j).stroke.get());
//        }


//        mainCanvasChildren.forEach(obj -> {
//            if (obj.visibleProperty().get()) print(obj);
//        });


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
        tempObjectName.set(NodeTypeEnum.Line.getNodeType());
    }

    @FXML
    void lineChartButtonOnAction(ActionEvent event) throws AppException {
        switch (xAxisType.getValue() + yAxisType.getValue()) {
            case "NumberNumber" -> tempObjectName.set(NodeTypeEnum.LineChart_NN.getNodeType());
            case "NumberString" -> tempObjectName.set(NodeTypeEnum.LineChart_NS.getNodeType());
            case "StringNumber" -> tempObjectName.set(NodeTypeEnum.LineChart_SN.getNodeType());
            case "StringString" -> throw new AppException(AppExceptionEnum.InvalidXYAxisType);
            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
        }
    }

    @FXML
    void loadButtonOnAction(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
        String filePath = fileChooser.showOpenDialog(new ContextMenu()).getAbsolutePath();
        JSONArray jsonArray = importProjectFromJSON(filePath);
        mainCanvasItemsHandler.selectAll();
        mainCanvasItemsHandler.removeSelectedObjectsFromMainCanvas();
        JSONObject jsonString;
//            print(jsonArray);
        for (Object obj : jsonArray) {
            jsonString = (JSONObject) obj;
            if (jsonString.get("object").equals(NodeTypeEnum.Ellipse.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseEllipseFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.Rectangle.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseRectangleFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.Line.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseLineFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.LineChart_NN.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseLineChart_NNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.LineChart_NS.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseLineChart_NSFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseLineChart_SNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.AreaChart_NN.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseAreaChart_NNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.AreaChart_NS.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseAreaChart_NSFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseAreaChart_SNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseScatterChart_NNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.ScatterChart_NS.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseScatterChart_NSFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
                mainCanvasItemsHandler.addToMainCanvas(parseScatterChart_SNFromJSON(jsonString));
//
//            } else if (jsonString.get("object").equals(NodeTypeEnum.BarChart_NS.getNodeType())) {
//
//            } else if (jsonString.get("object").equals(NodeTypeEnum.BarChart_SN.getNodeType())) {
//
            } else {
                throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
            }
        }
    }

    @FXML
    void loadButtonOnAction1(ActionEvent event) {

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
            mainCanvasItemsHandler.pasteCopiedSelectedObjects(currentPosX.get(), currentPosY.get());
        } else if (pasteShortcut.match(event) && isCutRequested) {
            mainCanvasItemsHandler.pasteCutSelectedObjects(currentPosX.get(), currentPosY.get());
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
    void mainCanvasOnKeyPressed(KeyEvent event) {
    }

    @FXML
    void mainCanvasOnKeyReleased(KeyEvent event) {
//        print("key released on main canvas.");
    }

    @FXML
    void mainCanvasOnKeyTyped(KeyEvent event) {
//        print("key typed on main canvas.");
    }

    @FXML
    void objectProp1InputOnKeyTyped(KeyEvent event) {
        AppNode selectedShape = mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().get(0);
        if (selectedShape.type.equals(NodeTypeEnum.Ellipse.getNodeType())) {
            ((AppEllipse) selectedShape).setRadiusX(Double.parseDouble(objectProp1Input.getText()));
        } else if (selectedShape.type.equals(NodeTypeEnum.Rectangle.getNodeType())) {
            ((AppRectangle) selectedShape).setWidth(Double.parseDouble(objectProp1Input.getText()));
        } else if (selectedShape.type.equals(NodeTypeEnum.Line.getNodeType())) {
            ((AppLine) selectedShape).setStartX(Double.parseDouble(objectProp1Input.getText()));
        }
    }

    @FXML
    void objectProp2InputOnKeyTyped(KeyEvent event) {
        AppNode selectedShape = mainCanvasItemsHandler.getSelectedObjectsController().getBuffer().get(0);
        if (selectedShape.type.equals(NodeTypeEnum.Ellipse.getNodeType())) {
            ((AppEllipse) selectedShape).setRadiusY(Double.parseDouble(objectProp2Input.getText()));
        } else if (selectedShape.type.equals(NodeTypeEnum.Rectangle.getNodeType())) {
            ((AppRectangle) selectedShape).setHeight(Double.parseDouble(objectProp2Input.getText()));
        } else if (selectedShape.type.equals(NodeTypeEnum.Line.getNodeType())) {
            ((AppLine) selectedShape).setStartY(Double.parseDouble(objectProp2Input.getText()));
        }
    }

    @FXML
    void pieChartButtonOnAction(ActionEvent event) {

    }

    @FXML
    void rectangleButtonOnAction(ActionEvent event) {
        tempObjectName.set(NodeTypeEnum.Rectangle.getNodeType());
    }

    @FXML
    void saveButtonOnAction(ActionEvent event) {


//        ImageIO.write(SwingFXUtils.fromFXImage(image, null),
//                "png", file);


        try {

            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter jsonExtension = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            FileChooser.ExtensionFilter texExtension = new FileChooser.ExtensionFilter("TeX files (*.tex)", "*.tex");
            FileChooser.ExtensionFilter pngExtension = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");

            fileChooser.getExtensionFilters().add(jsonExtension);
            fileChooser.getExtensionFilters().add(texExtension);
//            fileChooser.getExtensionFilters().add(pngExtension);

            File saveFile = fileChooser.showSaveDialog(null);

            PrintWriter printWriter = new PrintWriter(saveFile);

            if (fileChooser.getSelectedExtensionFilter().equals(jsonExtension)) {
                printWriter.println(exportProjectAsJSON(canvasPermanentObjects));
            } else if (fileChooser.getSelectedExtensionFilter().equals(texExtension)) {
                printWriter.println(exportProjectAsTeX(canvasPermanentObjects));
            } else if (fileChooser.getSelectedExtensionFilter().equals(pngExtension)) {
                SnapshotParameters snapshotParameters = new SnapshotParameters();
                snapshotParameters.setFill(new Color(0, 0, 0, 0.1));
//                mainCanvas.snapshot(snapshotParameters, new WritableImage((int) mainCanvas.getWidth(), (int) mainCanvas.getHeight()));
//                mainCanvas.snapshot(snapshotParameters, null);
                WritableImage image = mainCanvas.snapshot(new SnapshotParameters(), null);
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", saveFile);
            } else {
                throw new AppException(AppExceptionEnum.UnknownFileExtension);
            }
            printWriter.close();
        } catch (Exception ignored) {
            print(ignored);
        }
    }

    @FXML
    void saveButtonOnAction2(ActionEvent event) {

    }

    @FXML
    void scatterChartButtonOnAction(ActionEvent event) throws AppException {
        switch (xAxisType.getValue() + yAxisType.getValue()) {
            case "NumberNumber" -> tempObjectName.set(NodeTypeEnum.ScatterChart_NN.getNodeType());
            case "NumberString" -> tempObjectName.set(NodeTypeEnum.ScatterChart_NS.getNodeType());
            case "StringNumber" -> tempObjectName.set(NodeTypeEnum.ScatterChart_SN.getNodeType());
            case "StringString" -> throw new AppException(AppExceptionEnum.InvalidXYAxisType);
            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
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
//        dynamicDragRectangle.reset();
        tempObjectName.set(NodeTypeEnum.DynamicDragRectangle.getNodeType());
        mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
        littleEllipseOnCursor.hide();
        littleRectangleOnCursor.hide();
        littleLineOnCursor.hide();
        littleLineChartOnCursor.hide();
        littleBarChartOnCursor.hide();
        littleScatterChartOnCursor.hide();
        littleAreaChartOnCursor.hide();
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

//            print(change);
            if (change.getList().size() == 1) {
                AppNode selectedShape = change.getList().get(0);
//                print(selectedShape.type);
//                print(NodeTypeEnum.LineChart_NN.getNodeType());
                if (selectedShape.type.equals(NodeTypeEnum.Ellipse.getNodeType())) {
                    objectProp1Label.setText("Radius (X)");
                    objectProp2Label.setText("Radius (Y)");
                    objectProp1Input.setText("" + ((AppEllipse) selectedShape).getRadiusX());
                    objectProp2Input.setText("" + ((AppEllipse) selectedShape).getRadiusY());
                } else if (selectedShape.type.equals(NodeTypeEnum.Rectangle.getNodeType())) {
                    objectProp1Label.setText("Width");
                    objectProp2Label.setText("Height");
                    objectProp1Input.setText("" + ((AppRectangle) selectedShape).getWidth());
                    objectProp2Input.setText("" + ((AppRectangle) selectedShape).getHeight());
                } else if (selectedShape.type.equals(NodeTypeEnum.Line.getNodeType())) {
                    objectProp1Label.setText("Start (X)");
                    objectProp2Label.setText("Start (Y)");
                    objectProp1Input.setText("" + ((AppLine) selectedShape).getStartX());
                    objectProp2Input.setText("" + ((AppLine) selectedShape).getStartY());
                } else if (selectedShape.type.equals(NodeTypeEnum.LineChart_NN.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.AreaChart_NN.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
                    objectProp1Label.setText("Start (X)");
                    objectProp2Label.setText("Start (Y)");
                    try {
                        chartManagementPane_NN.registerChart((AppXYChart<Number, Number>) selectedShape);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

//                    chartTabContainer.getContent()..clear();
                    chartTabContainer.setContent(chartManagementPane_NN);
//                    objectProp1Input.setText("" + ((AppLineChart_NumberNumber) selectedShape).getStartX());
//                    objectProp2Input.setText("" + ((AppLine) selectedShape).getStartY());
                } else if (selectedShape.type.equals(NodeTypeEnum.LineChart_NS.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.AreaChart_NS.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.ScatterChart_NS.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.BarChart_NS.getNodeType())) {
                    objectProp1Label.setText("Start (X)");
                    objectProp2Label.setText("Start (Y)");
//                    ChartManagementPane_NumberString chartManagementPane = new ChartManagementPane_NumberString();
                    try {
                        chartManagementPane_NS.registerChart((AppXYChart<Number, String>) selectedShape);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

//                    chartTabContainer.getChildren().clear();
//                    chartTabContainer.getChildren().add(chartManagementPane);
                    chartTabContainer.setContent(chartManagementPane_NS);
//                    objectProp1Input.setText("" + ((AppLineChart_NumberNumber) selectedShape).getStartX());
//                    objectProp2Input.setText("" + ((AppLine) selectedShape).getStartY());
                } else if (selectedShape.type.equals(NodeTypeEnum.LineChart_SN.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.AreaChart_SN.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.ScatterChart_SN.getNodeType()) ||
                        selectedShape.type.equals(NodeTypeEnum.BarChart_SN.getNodeType())) {
                    objectProp1Label.setText("Start (X)");
                    objectProp2Label.setText("Start (Y)");
//                    ChartManagementPane_StringNumber chartManagementPane = new ChartManagementPane_StringNumber();
                    try {
                        chartManagementPane_SN.registerChart((AppXYChart<String, Number>) selectedShape);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

//                    chartTabContainer.getChildren().clear();
//                    chartTabContainer.getChildren().add(chartManagementPane_numberNumber);
                    chartTabContainer.setContent(chartManagementPane_SN);
//                    objectProp1Input.setText("" + ((AppLineChart_NumberNumber) selectedShape).getStartX());
//                    objectProp2Input.setText("" + ((AppLine) selectedShape).getStartY());
                }
//                else if (selectedShape.getClass().getSimpleName().equals(ElementTypes.AppLineChart.getSimpleName())) {
//                }else if (selectedShape.getClass().getSimpleName().equals(ElementTypes.AppBarChart.getSimpleName())) {
//                }else if (selectedShape.getClass().getSimpleName().equals(ElementTypes.AppScatterChart.getSimpleName())) {
//                }else if (selectedShape.getClass().getSimpleName().equals(ElementTypes.AppAreaChart.getSimpleName())) {
//                }

//                chartDataPane.


            }
        });
    }

    void settleIcons() {
        lineChartButton.setGraphic(new LineChartIcon());
        areaChartButton.setGraphic(new AreaChartIcon());
        barChartButton.setGraphic(new BarChartIcon());
        scatterChartButton.setGraphic(new ScatterChartIcon());
        pieChartButton.setGraphic(new PieChartIcon());
        ellipseButton.setGraphic(new EllipseIcon(25, 13, new Color(1, 0, 0, 0.2), new Color(0, 0, 0, 1), 1));
        rectangleButton.setGraphic(new RectangleIcon(50, 26, new Color(0, 0, 1, 0.2), new Color(0, 0, 0, 1), 1));
    }

    @FXML
    void shapesRectangleButtonOnAction1(ActionEvent event) {

    }

    @FXML
    void shapesRectangleButtonOnAction2(ActionEvent event) {

    }

    @FXML
    void strokeDashArrayInputOnKeyTyped(KeyEvent event) {

        int eventCharacterASCIICode = event.getCharacter().charAt(0);

        if (eventCharacterASCIICode != (int) ',' && eventCharacterASCIICode != (int) '.' && (eventCharacterASCIICode < (int) '0' || eventCharacterASCIICode > (int) '9')) {
            strokeDashArrayInput.setText(strokeDashArrayInput.getText().replace(event.getCharacter(), ""));
        }
    }

    @FXML
    void strokeWidthInputOnKeyTyped(KeyEvent event) {
        // If the input RGBA code is not a number or a number not within 0 to 255, automatically correct it.
        try {
            if (Double.parseDouble(strokeWidthInput.getText()) < 0)
                strokeWidthInput.setText("1");
        } catch (NumberFormatException e) {
            strokeWidthInput.setText("1");
        }

        // Apply changes to selected objects.
        mainCanvasItemsHandler.applyStrokeWidthChangesToSelectedObjects(Double.parseDouble(strokeWidthInput.getText()));
    }

    @FXML
    void textButtonOnAction(ActionEvent event) {

    }

    @FXML
    void triangleButtonOnAction(ActionEvent event) {

    }

    @FXML
    void visualArtsGradientColorButtonOnAction(ActionEvent event) {

    }


    @FXML
    void visualArtsGradientColorButtonOnMouseDragOver(MouseDragEvent event) {

    }


}