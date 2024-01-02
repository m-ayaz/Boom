package com.boom.structures.abstracts;

import com.boom.appshapes.*;
import com.boom.configuration.Configs;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.styles.CSSProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import org.json.JSONObject;

import static com.boom.tools.Tools.*;


public abstract class AppNode {

    public Affine affineTransform = new Affine();
    public Rectangle border = new Rectangle();
    public CSSProperty backgroundStyle;
    protected Node styleableNode;
    protected String type;
    public final String id = uuid(Configs.ID_LENGTH);

    public AppNode(Node styleableNode, String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        this.styleableNode = styleableNode;
        backgroundStyle = new CSSProperty(fillColorFX, strokeColorFX, strokeWidthFX);

        styleableNode.getTransforms().add(affineTransform);
        styleableNode.styleProperty().bind(backgroundStyle);


    }

    protected int configStep = 0;

    public abstract void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY, double pressX, double pressY, double releaseX, double releaseY, double clickX, double clickY, double x, double y);


    public abstract AppNode copy();

    public abstract void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY);

    public abstract String getSVGClones(int tabIndent);

    public Node getStyleableNode() {
        return styleableNode;
    }

    public String getType() {
        return type;
    }

    public void hide() {
        styleableNode.setVisible(false);
    }

    public abstract JSONObject toJSON();

    protected void bindBorder(Node binder) {
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        border.setMouseTransparent(true);
        border.setStrokeWidth(1);
        border.getStrokeDashArray().addAll(5.0, 6.0, 6.0, 6.0);
        border.setVisible(false);
        border.setX(binder.getBoundsInParent().getMinX());
        border.setY(binder.getBoundsInParent().getMinY());
        border.setWidth(binder.getBoundsInParent().getWidth());
        border.setHeight(binder.getBoundsInParent().getHeight());
        binder.boundsInParentProperty().addListener((a, b, c) -> {
            border.setX(c.getMinX());
            border.setY(c.getMinY());
            border.setWidth(c.getWidth());
            border.setHeight(c.getHeight());
        });
    }


    protected void modifyType(String newType) {
        this.type = newType;
    }

    public abstract boolean contains(double x, double y);

    public static AppNode parseJSON(JSONObject jsonObject) {
        AppNode appNode;
        switch (jsonObject.getString("type")) {
            case "Rectangle" -> appNode = new AppRectangle(jsonObject.getDouble("width"), jsonObject.getDouble("height"), jsonObject.getDouble("arcWidth"), jsonObject.getDouble("arcHeight"));
            case "Ellipse" -> appNode = new AppEllipse(jsonObject.getDouble("radiusX"), jsonObject.getDouble("radiusY"));
            case "Arc" -> appNode = new AppArc(jsonObject.getDouble("radiusX"), jsonObject.getDouble("radiusY"), jsonObject.getDouble("startAngle"), jsonObject.getDouble("length"), ArcType.valueOf(jsonObject.getString("arcType")));
            case "CubicCurve" -> appNode = new AppCubicCurve(jsonObject.getDouble("startX"), jsonObject.getDouble("startY"), jsonObject.getDouble("controlX1"), jsonObject.getDouble("controlY1"), jsonObject.getDouble("controlX2"), jsonObject.getDouble("controlY2"), jsonObject.getDouble("endX"), jsonObject.getDouble("endY"));
            case "QuadCurve" -> appNode = new AppQuadCurve(jsonObject.getDouble("startX"), jsonObject.getDouble("startY"), jsonObject.getDouble("controlX"), jsonObject.getDouble("controlY"), jsonObject.getDouble("endX"), jsonObject.getDouble("endY"));
            case "Line" -> appNode = new AppLine(jsonObject.getDouble("startX"), jsonObject.getDouble("startY"), jsonObject.getDouble("endX"), jsonObject.getDouble("endY"));
            case "Polygon" -> appNode = new AppPolygon(arrayToArray(jsonObject.getJSONArray("points")));
            case "Polyline" -> appNode = new AppPolyline(arrayToArray(jsonObject.getJSONArray("points")));
            default -> throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
        }
        appNode.affineTransform.append(parseAffine(jsonObject.getJSONArray("affine")));
        appNode.backgroundStyle.setFromJSON(jsonObject.getJSONObject("backgroundStyle"));

        return appNode;

    }


}