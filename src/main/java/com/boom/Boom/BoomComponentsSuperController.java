package com.boom.Boom;

import com.boom.appcharts.number_number.AppAreaChart_NumberNumber;
import com.boom.appcharts.number_number.AppLineChart_NumberNumber;
import com.boom.appcharts.number_number.AppScatterChart_NumberNumber;
import com.boom.appcharts.number_string.AppAreaChart_NumberString;
import com.boom.appcharts.number_string.AppLineChart_NumberString;
import com.boom.appcharts.number_string.AppScatterChart_NumberString;
import com.boom.appcharts.string_number.AppAreaChart_StringNumber;
import com.boom.appcharts.string_number.AppLineChart_StringNumber;
import com.boom.appcharts.string_number.AppScatterChart_StringNumber;
import com.boom.appshapes.*;
import com.boom.configuration.Configs;
import com.boom.controllers.DynamicDragRectangle;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.controllers.eventhandlers.mousehandler.MainCanvasMouseHandler;
import com.boom.exceptions.AppException;
import com.boom.icons.*;
import com.boom.indicators.*;
import com.boom.panels.chart.number_number.ChartManagementPane_NumberNumber;
import com.boom.panels.chart.number_string.ChartManagementPane_NumberString;
import com.boom.panels.chart.string_number.ChartManagementPane_StringNumber;
import com.boom.panels.paint.PaintManagementPanel;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.abstracts.AppXYChart;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.enums.NodeTypeEnum;
import javafx.beans.property.*;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.boom.configuration.Configs.*;
import static com.boom.projectmanager.ProjectManager.*;
import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.uuid;

public class BoomComponentsSuperController {

    DoubleProperty currentPosX = new SimpleDoubleProperty();
    DoubleProperty currentPosY = new SimpleDoubleProperty();
    StringProperty tempObjectName = new SimpleStringProperty();
    List<Double> parsedStrokeDashArray = new ArrayList<>();

    DynamicDragRectangle dynamicDragRectangle = new DynamicDragRectangle();
    AppEllipse tempEllipse = new AppEllipse(0, 0);
    AppPolyline tempPolyline=new AppPolyline();
    AppPolygon tempPolygon = new AppPolygon();
    AppArc tempArc = new AppArc(0, 0, 0, 270, ArcType.ROUND);
    AppRectangle tempRectangle = new AppRectangle(0, 0);
    AppLine tempLine = new AppLine(0, 0, 0, 0);
    AppQuadCurve tempQuadCurve = new AppQuadCurve(0, 0, 0, 0, 0, 0);
    AppCubicCurve tempCubicCurve = new AppCubicCurve(0, 0, 0, 0, 0, 0, 0, 0);

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
    LittlePolylineOnCursor littlePolylineOnCursor=new LittlePolylineOnCursor();
    LittlePolygonOnCursor littlePolygonOnCursor = new LittlePolygonOnCursor();
    LittleArcOnCursor littleArcOnCursor = new LittleArcOnCursor();
    LittleRectangleOnCursor littleRectangleOnCursor = new LittleRectangleOnCursor();
    LittleLineOnCursor littleLineOnCursor = new LittleLineOnCursor();
    LittleQuadCurveOnCursor littleQuadCurveOnCursor = new LittleQuadCurveOnCursor();
    LittleCubicCurveOnCursor littleCubicCurveOnCursor = new LittleCubicCurveOnCursor();

    ChartManagementPane_NumberNumber chartManagementPane_NN = new ChartManagementPane_NumberNumber();
    ChartManagementPane_NumberString chartManagementPane_NS = new ChartManagementPane_NumberString();
    ChartManagementPane_StringNumber chartManagementPane_SN = new ChartManagementPane_StringNumber();
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
    @FXML
    private Button areaChartButton;
    @FXML
    private Button barChartButton;
    @FXML
    private ScrollPane chartTabContainer;
    @FXML
    private Label cursorPositionLabel;
    @FXML
    private Button ellipseButton;
    @FXML
    private Button lineChartButton;
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
    private Button pieChartButton;
    @FXML
    private Button rectangleButton;
    @FXML
    private Button scatterChartButton;
    @FXML
    private TextField selectedObjectsCenterXInput;
    @FXML
    private TextField selectedObjectsCenterYInput;
    @FXML
    private ScrollPane visualEffectsTab;
    @FXML
    private ComboBox<String> xAxisType;
    @FXML
    private ComboBox<String> yAxisType;
    @FXML
    private BorderPane appWindow;
    @FXML
    private Button arcButton;
    @FXML
    private Tab chartsTab;
    @FXML
    private Tab fileTab;
    @FXML
    private Tab fileTab1;
    @FXML
    private Tab fileTab11;
    @FXML
    private Button lineButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button loadButton1;
    @FXML
    private Button loadButton11;
    @FXML
    private ScrollPane mainCanvasHolder;
    @FXML
    private Button polygonButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button saveButton1;
    @FXML
    private Button saveButton11;
    @FXML
    private Button selectorButton;
    @FXML
    private Tab shapesTab;
    @FXML
    private Button textButton;
    @FXML
    private Button triangleButton;
    @FXML
    private Button cubicCurveButton;
    @FXML
    private Button quadCurveButton;

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
        tempObjectName.set(NodeTypeEnum.Arc.getNodeType());
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
        print(uuid(1000));
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
        tempObjectName.set(NodeTypeEnum.CubicCurve.getNodeType());
    }

    @FXML
    void ellipseButtonOnAction(ActionEvent event) {
        tempObjectName.set(NodeTypeEnum.Ellipse.getNodeType());
    }

    @FXML
    void initialize() {

//        System.out.println("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"100\" height=\"100%\">");
//        System.out.println("<circle cx=\"50\" cy=\"50\" r=\"30\" fill=\"red\">");
//        System.out.println("</svg>");

        Configs.setDefaultConfig();

        mainCanvas.setPrefWidth(3000);
        mainCanvas.setPrefHeight(3000);

        mainCanvasChildren = mainCanvas.getChildren();

//        mainCanvasCursor = mainCanvas.cursorProperty();

        instantiateObjects();

        settleIcons();

        selectedObjectsController = new SelectedObjectsController(rotationIcon, scalingIcons, rotationFixedPoint);

        mainCanvasItemsHandler = new MainCanvasItemsHandler(mainCanvasChildren, validObjects,
                rotationHandle, rotationIcon, scalingIcons, rotationFixedPoint, scalingFixedPoint,
                dynamicDragRectangle, tempEllipse,tempPolyline, tempCubicCurve, tempQuadCurve, tempPolygon, tempArc, tempRectangle, tempLine, tempLineChart_NN,
                tempLineChart_NS, tempLineChart_SN, tempAreaChart_NN, tempAreaChart_NS, tempAreaChart_SN,
                tempScatterChart_NN, tempScatterChart_NS, tempScatterChart_SN, littleLineChartOnCursor,
                littleBarChartOnCursor, littleScatterChartOnCursor, littleAreaChartOnCursor,
                littleEllipseOnCursor,littlePolylineOnCursor, littleCubicCurveOnCursor, littleQuadCurveOnCursor, littlePolygonOnCursor, littleArcOnCursor, littleRectangleOnCursor,
                littleLineOnCursor, selectedObjectsController);

        tempObjectName.set(NodeTypeEnum.DynamicDragRectangle.getNodeType());

        bindVisibilities();

        setUpObjectsSizeInput();

        setUpObjectsCenterOfMassInput();

        MainCanvasMouseHandler mainCanvasMouseHandler = new MainCanvasMouseHandler(
                mainCanvasChildren,
                validObjects,
                selectedObjectsController,
                rotationHandle,
                rotationFixedPoint,
                scalingFixedPoint,
                cursorPositionLabel,
                tempObjectName,
                dynamicDragRectangle,
                tempEllipse,
                tempPolyline,
                tempCubicCurve,
                tempQuadCurve,
                tempPolygon,
                tempArc,
                tempRectangle,
                tempLine,
                tempLineChart_NN,
                tempLineChart_NS,
                tempLineChart_SN,
                tempAreaChart_NN,
                tempAreaChart_NS,
                tempAreaChart_SN,
////    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
////    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);
                tempScatterChart_NN,
                tempScatterChart_NS,
                tempScatterChart_SN,
                littleLineChartOnCursor,
                littleBarChartOnCursor,
                littleScatterChartOnCursor,
                littleAreaChartOnCursor,
                littleEllipseOnCursor,
                littlePolylineOnCursor,
                littleCubicCurveOnCursor,
                littleQuadCurveOnCursor,
                littlePolygonOnCursor,
                littleArcOnCursor,
                littleRectangleOnCursor,
                littleLineOnCursor,
                mainCanvasItemsHandler,
                //                                  ColorPicker fillSolidColorPicker,
//                                  ColorPicker strokeSolidColorPicker,
//                                  TextField strokeWidthInput,
                parsedStrokeDashArray,
                scalingIcons,


                //
////    AppBarChart_NumberString tempBarChart_NS = new AppBarChart_NumberString(0,0);
////    AppBarChart_StringNumber tempBarChart_SN = new AppBarChart_StringNumber(0,0);
//
                rotationIcon);

        mainCanvas.setOnMouseMoved(mainCanvasMouseHandler);
        mainCanvas.setOnMouseDragged(mainCanvasMouseHandler);
        mainCanvas.setOnMouseClicked(mainCanvasMouseHandler);
        mainCanvas.setOnMousePressed(mainCanvasMouseHandler);
        mainCanvas.setOnMouseReleased(mainCanvasMouseHandler);

        xAxisType.getItems().addAll("Number", "String");
        yAxisType.getItems().addAll("Number", "String");

        xAxisType.setValue("Number");
        yAxisType.setValue("Number");


        visualEffectsTab.setContent(paintManagementPanel);
        paintManagementPanel.setVisible(false);


//        mainAppPlayGround.setBackground(Background.fill(Color.valueOf("00000055")));

//        AppEllipse appEllipse = new AppEllipse(200, 100);
//        mainCanvasItemsHandler.addToMainCanvas(appEllipse);
//        appEllipse.affineTransform.prependTranslation(0, 0);
//
//        AppLine appLine=new AppLine(0,0,200,200);
//        appRectangle.affineTransform.appendTranslation(200,200);
//
//        mainCanvasItemsHandler.addToMainCanvas(appLine);
//        mainCanvasItemsHandler.addToMainCanvas(appRectangle);
//        print(appLine.border);

//        LinearGradientIndicator lgi=new LinearGradientIndicator();
//
//        mainCanvas.getChildren().add(lgi);
//
//        lgi.start.setCenterX(50);
//        lgi.start.setCenterY(50);
//
//        lgi.end.setCenterX(100);
//        lgi.end.setCenterY(100);
//
//        lgi.visibleProperty.set(true);

//        Menu menu=new Menu("asl;als;");
//        MenuItem menuItem1=new CustomMenuItem(new Button("asasas"));
//        MenuItem menuItem2=new CheckMenuItem("sadlsadklad",new Button("akslakls"));
//        MenuItem menuItem3=new RadioMenuItem("aaaa",new Button("al"));
//        MenuItem menuItem4=new SeparatorMenuItem();
//        MenuItem menuItem5=new MenuItem("asasas",new Button("asasas"));
//        menu.getItems().addAll(menuItem1,menuItem2,menuItem3,menuItem4,menuItem5);
//
//        tempMenuBar.getMenus().add(menu);
//
//        menuItem1.setGraphic(new Rectangle(50,50,Color.RED));
//
//        tempMenuBar.setBackground(Background.fill(Color.valueOf("00000099")));
//        tempMenuBar.getMenus().forEach(m);


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
//                mainCanvasItemsHandler.addToMainCanvas(parseEllipseFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.Rectangle.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseRectangleFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.Line.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseLineFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.LineChart_NN.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(+parseLineChart_NNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.LineChart_NS.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseLineChart_NSFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.LineChart_SN.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseLineChart_SNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.AreaChart_NN.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseAreaChart_NNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.AreaChart_NS.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseAreaChart_NSFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.AreaChart_SN.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseAreaChart_SNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseScatterChart_NNFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.ScatterChart_NS.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseScatterChart_NSFromJSON(jsonString));
            } else if (jsonString.get("object").equals(NodeTypeEnum.ScatterChart_SN.getNodeType())) {
//                mainCanvasItemsHandler.addToMainCanvas(parseScatterChart_SNFromJSON(jsonString));
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
    void pieChartButtonOnAction(ActionEvent event) {

    }

    @FXML
    void polygonButtonOnAction(ActionEvent event) {
        tempObjectName.set(NodeTypeEnum.Polygon.getNodeType());
    }

    @FXML
    void quadCurveButtonOnAction(ActionEvent event) {
        tempObjectName.set(NodeTypeEnum.QuadCurve.getNodeType());
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
            FileChooser.ExtensionFilter texExtension = new FileChooser.ExtensionFilter("Boom TeX files (*.tex)", "*.tex");
            FileChooser.ExtensionFilter pngExtension = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            FileChooser.ExtensionFilter svgExtension = new FileChooser.ExtensionFilter("Boom SVG files (*.svg)", "*.svg");



            fileChooser.getExtensionFilters().addAll(jsonExtension, texExtension
//                    ,svgExtension,pngExtension
            );

            File saveFile = fileChooser.showSaveDialog(null);

            PrintWriter printWriter = new PrintWriter(saveFile);

            if (fileChooser.getSelectedExtensionFilter().equals(jsonExtension)) {
                printWriter.println(exportProjectAsJSON(validObjects));
            } else if (fileChooser.getSelectedExtensionFilter().equals(texExtension)) {
                printWriter.println(exportProjectAsTeX(validObjects));
            } else if (fileChooser.getSelectedExtensionFilter().equals(svgExtension)) {
                printWriter.println(exportProjectAsSVG(validObjects));
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
        } catch (Exception e) {
            print(e);
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
        tempObjectName.set(NodeTypeEnum.DynamicDragRectangle.getNodeType());
        mainCanvasItemsHandler.getSelectedObjectsController().unselectAll();
        littleEllipseOnCursor.hide();
        littleRectangleOnCursor.hide();
        littleLineOnCursor.hide();
        littleLineChartOnCursor.hide();
        littleBarChartOnCursor.hide();
        littleScatterChartOnCursor.hide();
        littleAreaChartOnCursor.hide();
        littleQuadCurveOnCursor.hide();
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

                paintManagementPanel.registerBackground(selectedShape.backgroundStyle);
                paintManagementPanel.setVisible(true);
//                print(selectedShape.type);
//                print(NodeTypeEnum.LineChart_NN.getNodeType());
                if (selectedShape.getType().equals(NodeTypeEnum.Ellipse.getNodeType())) {
                    objectProp1Label.setText("Radius (X)");
                    objectProp2Label.setText("Radius (Y)");
                    objectProp1Input.setText("" + ((AppEllipse) selectedShape).radiusX.get());
                    objectProp2Input.setText("" + ((AppEllipse) selectedShape).radiusY.get());
                } else if (selectedShape.getType().equals(NodeTypeEnum.Rectangle.getNodeType())) {
                    objectProp1Label.setText("Width");
                    objectProp2Label.setText("Height");
                    objectProp1Input.setText("" + ((AppRectangle) selectedShape).width.get());
                    objectProp2Input.setText("" + ((AppRectangle) selectedShape).height.get());
                } else if (selectedShape.getType().equals(NodeTypeEnum.Line.getNodeType())) {
//                    objectProp1Label.setText("Start (X)");
//                    objectProp2Label.setText("Start (Y)");
//                    objectProp1Input.setText("" + ((AppLine)selectedShape).get());
//                    objectProp2Input.setText("" + ((AppLine)selectedShape).getHeight());
                } else if (selectedShape.getType().equals(NodeTypeEnum.LineChart_NN.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.AreaChart_NN.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.ScatterChart_NN.getNodeType())) {
                    objectProp1Label.setText("Start (X)");
                    objectProp2Label.setText("Start (Y)");
                    print(uuid(100));
                    try {
                        chartManagementPane_NN.registerChart((AppXYChart<Number, Number>) selectedShape);
                    } catch (Exception e) {
                        print(e);
//                        throw new RuntimeException(e);
                    }


//                    chartTabContainer.getContent()..clear();
                    chartTabContainer.setContent(chartManagementPane_NN);
//                    objectProp1Input.setText("" + ((AppLineChart_NumberNumber) selectedShape).getStartX());
//                    objectProp2Input.setText("" + ((AppLine) selectedShape).getStartY());
                } else if (selectedShape.getType().equals(NodeTypeEnum.LineChart_NS.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.AreaChart_NS.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.ScatterChart_NS.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.BarChart_NS.getNodeType())) {
                    objectProp1Label.setText("Start (X)");
                    objectProp2Label.setText("Start (Y)");
                    try {
                        chartManagementPane_NS.registerChart((AppXYChart<Number, String>) selectedShape);
                    } catch (Exception e) {
                        print(e);
//                        throw new RuntimeException(e);
                    }

//                    chartTabContainer.getChildren().clear();
//                    chartTabContainer.getChildren().add(chartManagementPane);
                    chartTabContainer.setContent(chartManagementPane_NS);
//                    objectProp1Input.setText("" + ((AppLineChart_NumberNumber) selectedShape).getStartX());
//                    objectProp2Input.setText("" + ((AppLine) selectedShape).getStartY());
                } else if (selectedShape.getType().equals(NodeTypeEnum.LineChart_SN.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.AreaChart_SN.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.ScatterChart_SN.getNodeType()) ||
                        selectedShape.getType().equals(NodeTypeEnum.BarChart_SN.getNodeType())) {
                    objectProp1Label.setText("Start (X)");
                    objectProp2Label.setText("Start (Y)");
                    try {
                        chartManagementPane_SN.registerChart((AppXYChart<String, Number>) selectedShape);
                    } catch (Exception e) {
                        print(e);
//                        throw new RuntimeException(e);
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


            } else {
                chartManagementPane_NN.setVisible(false);
                chartManagementPane_NS.setVisible(false);
                chartManagementPane_SN.setVisible(false);
                paintManagementPanel.setVisible(false);
            }
        });
    }

    void settleIcons() {
        lineChartButton.setGraphic(new LineChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
        areaChartButton.setGraphic(new AreaChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
        barChartButton.setGraphic(new BarChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
        scatterChartButton.setGraphic(new ScatterChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
        pieChartButton.setGraphic(new PieChartIcon(CHART_BUTTON_WIDTH,CHART_BUTTON_HEIGHT));
        ellipseButton.setGraphic(new EllipseIcon(SHAPE_BUTTON_ICON_WIDTH / 2, SHAPE_BUTTON_ICON_HEIGHT / 2, Color.valueOf(ELLIPSE_BUTTON_ICON_COLOR), Color.BLACK, 1));
        arcButton.setGraphic(new ArcIcon(SHAPE_BUTTON_ICON_WIDTH / 2, SHAPE_BUTTON_ICON_HEIGHT / 2, 45, 270, Color.valueOf(ARC_BUTTON_ICON_COLOR), Color.BLACK, 1));
        rectangleButton.setGraphic(new RectangleIcon(SHAPE_BUTTON_ICON_WIDTH, SHAPE_BUTTON_ICON_HEIGHT, Color.valueOf(RECTANGLE_BUTTON_ICON_COLOR), Color.BLACK, 1));
        polygonButton.setGraphic(new PolygonIcon(SHAPE_BUTTON_ICON_WIDTH, SHAPE_BUTTON_ICON_HEIGHT, Color.valueOf(POLYGON_BUTTON_ICON_COLOR), Color.BLACK, 1));
        lineButton.setGraphic(new LineIcon(0, 0, 36, 36, Color.valueOf(LINE_BUTTON_ICON_COLOR), CURVE_BUTTON_ICONS_STROKE_WIDTH));
        quadCurveButton.setGraphic(new QuadCurveIcon(0, 0, 36, 0, 36, 36, Color.TRANSPARENT, Color.valueOf(QUAD_CURVE_BUTTON_ICON_COLOR), CURVE_BUTTON_ICONS_STROKE_WIDTH));
        cubicCurveButton.setGraphic(new CubicCurveIcon(0, 0, 12+50, 0, 24-50, 36, 36, 36, Color.TRANSPARENT, Color.valueOf(CUBIC_CURVE_BUTTON_ICON_COLOR), CURVE_BUTTON_ICONS_STROKE_WIDTH));
        polylineButton.setGraphic(new PolylineIcon(30,30,Color.TRANSPARENT,Color.rgb(255,128,0,1),CURVE_BUTTON_ICONS_STROKE_WIDTH));
    }

    @FXML
    void textButtonOnAction(ActionEvent event) {

    }

    @FXML
    private Button polylineButton;

    @FXML
    void polylineButtonOnAction(ActionEvent event) {
        tempObjectName.set(NodeTypeEnum.Polyline.getNodeType());
    }


}