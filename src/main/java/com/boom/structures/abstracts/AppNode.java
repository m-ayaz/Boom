package com.boom.structures.abstracts;

import com.boom.appshapes.AppEllipse;
import com.boom.appshapes.AppRectangle;
import com.boom.configuration.Configs;
import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppExceptionEnum;
import com.boom.structures.enums.NodeTypeEnum;
import com.boom.styles.CSSProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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

    public abstract void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY , double pressX , double pressY , double releaseX , double releaseY , double clickX , double clickY , double x , double y)    ;


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

    public static AppNode parseJSON(JSONObject jsonObject){
        AppNode appNode;
        if(jsonObject.get("type").equals(NodeTypeEnum.Rectangle.getNodeType())){
            appNode=new AppRectangle( jsonObject.getDouble("width"),  jsonObject.getDouble("height"));
            appNode.affineTransform.append(parseAffine(jsonObject.getJSONArray("affine")));
            appNode.backgroundStyle.setFromJSON(jsonObject.getJSONObject("backgroundStyle"));
            ((AppRectangle)appNode).arcWidth.set(jsonObject.getDouble("arcWidth"));
            ((AppRectangle)appNode).arcHeight.set(jsonObject.getDouble("arcHeight"));
            return appNode;
        }else if(jsonObject.get("type").equals(NodeTypeEnum.Ellipse.getNodeType())){
            appNode=new AppEllipse( jsonObject.getDouble("radiusX"),  jsonObject.getDouble("radiusY"));
            appNode.affineTransform.append(parseAffine(jsonObject.getJSONArray("affine")));
            appNode.backgroundStyle.setFromJSON(jsonObject.getJSONObject("backgroundStyle"));
            return appNode;
        }else{
            throw new AppException(AppExceptionEnum.AppNodeNotRegistered);
        }
    }


}