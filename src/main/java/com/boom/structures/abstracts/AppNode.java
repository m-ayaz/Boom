package com.boom.structures.abstracts;

import com.boom.configuration.Configs;
import com.boom.styles.CSSProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import org.json.JSONObject;

import static com.boom.tools.Tools.uuid;


public abstract class AppNode {

    public Affine affineTransform = new Affine();
    public Rectangle border = new Rectangle();
    public CSSProperty backgroundStyle;
    protected Node styleableNode;
    protected String type;
    public final String id = uuid(Configs.ID_LENGTH);

    public AppNode(Node styleableNode, String fillColorFX, String strokeColorFX, String strokeWidthFX) {
        this.styleableNode = styleableNode;
        styleableNode.getTransforms().add(affineTransform);
        backgroundStyle = new CSSProperty(fillColorFX, strokeColorFX, strokeWidthFX);
        styleableNode.styleProperty().bind(backgroundStyle);
    }

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