package com.boom.structures.abstracts;

import com.boom.appshapes.*;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.interfaces.JSONSerializable;
import com.boom.structures.interfaces.SVGSerializable;
import com.boom.structures.interfaces.TeXSerializable;
import com.boom.styles.CSSProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import org.json.JSONObject;

import static com.boom.configuration.Configs.ID_LENGTH;
import static com.boom.tools.Tools.*;


public abstract class AppNode implements JSONSerializable, SVGSerializable , TeXSerializable {

    public final String id = uuid(ID_LENGTH);
    public final Affine affineTransform = new Affine();
    public CSSProperty backgroundStyle;
    public Rectangle border = new Rectangle();
    public Node styleableNode;
    protected String type;
    public Bounds bounds;
    protected int configStep = 0;


//    public AppNode clone() throws CloneNotSupportedException {
//        AppNode clone = (AppNode) super.clone();
//        return null;
//    }

    public AppNode(Node styleableNode, String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        this.styleableNode = styleableNode;
        backgroundStyle = new CSSProperty(fillColorFX, strokeColorFX, strokeWidthFX);

        styleableNode.getTransforms().add(affineTransform);
        styleableNode.styleProperty().bind(backgroundStyle);

        bounds=styleableNode.getBoundsInParent();

//        LineChart l;
//        l.getXAxis().set;


    }

    public static AppNode parseJSON(JSONObject jsonObject) {
        AppNode appNode;
        switch (jsonObject.getString("type")) {
            case "Rectangle" ->
                    appNode = new AppRectangle(jsonObject.getDouble("width"), jsonObject.getDouble("height"), jsonObject.getDouble("arcWidth"), jsonObject.getDouble("arcHeight"));
            case "Ellipse" ->
                    appNode = new AppEllipse(jsonObject.getDouble("radiusX"), jsonObject.getDouble("radiusY"));
            case "Arc" ->
                    appNode = new AppArc(jsonObject.getDouble("radiusX"), jsonObject.getDouble("radiusY"), jsonObject.getDouble("startAngle"), jsonObject.getDouble("length"), ArcType.valueOf(jsonObject.getString("arcType")));
            case "CubicCurve" ->
                    appNode = new AppCubicCurve(jsonObject.getDouble("startX"), jsonObject.getDouble("startY"), jsonObject.getDouble("controlX1"), jsonObject.getDouble("controlY1"), jsonObject.getDouble("controlX2"), jsonObject.getDouble("controlY2"), jsonObject.getDouble("endX"), jsonObject.getDouble("endY"));
            case "QuadCurve" ->
                    appNode = new AppQuadCurve(jsonObject.getDouble("startX"), jsonObject.getDouble("startY"), jsonObject.getDouble("controlX"), jsonObject.getDouble("controlY"), jsonObject.getDouble("endX"), jsonObject.getDouble("endY"));
            case "Line" ->
                    appNode = new AppLine(jsonObject.getDouble("startX"), jsonObject.getDouble("startY"), jsonObject.getDouble("endX"), jsonObject.getDouble("endY"));
            case "Polygon" -> appNode = new AppPolygon(arrayToArray(jsonObject.getJSONArray("points")));
            case "Polyline" -> appNode = new AppPolyline(arrayToArray(jsonObject.getJSONArray("points")));
            default -> throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
        }
        appNode.affineTransform.append(parseAffine(jsonObject.getJSONArray("affine")));
        appNode.backgroundStyle.setFromJSON(jsonObject.getJSONObject("backgroundStyle"));

        return appNode;

    }

    public abstract void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY, double pressX, double pressY, double releaseX, double releaseY, double clickX, double clickY, double x, double y);

    public abstract boolean contains(double x, double y);

    public abstract AppNode copy();

    public abstract void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY);

//    public Node styleableNode {
//        return styleableNode;
//    }

    public String getType() {
        return type;
    }

    public void hide() {
        styleableNode.setVisible(false);
    }

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


}