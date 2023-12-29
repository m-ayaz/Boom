package com.boom.appshapes;

import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;

public class AppQuadCurve extends AppLineShape {

    public DoubleProperty startX, startY, controlX, controlY, endX, endY;

    public AppQuadCurve(double startX,double startY,double controlX,double controlY,double endX,double endY) {
        super(new QuadCurve(startX, startY, controlX, controlY, endX, endY));
        this.startX=((QuadCurve) styleableNode).startXProperty();
        this.startY=((QuadCurve) styleableNode).startYProperty();
        this.controlX=((QuadCurve) styleableNode).controlXProperty();
        this.controlY=((QuadCurve) styleableNode).controlYProperty();
        this.endX=((QuadCurve) styleableNode).endXProperty();
        this.endY=((QuadCurve) styleableNode).endYProperty();
    }

    @Override
    public AppNode copy() {
        if (startX.get() == startY.get() && endX.get() == endY.get())
            return null;
        AppQuadCurve  newAppQuadCurve= new AppQuadCurve(startX.get(), startY.get(),controlX.get(),controlY.get(), endX.get(), endY.get());
        deepCopy(affineTransform, newAppQuadCurve.affineTransform);
        deepCopy(backgroundStyle, newAppQuadCurve.backgroundStyle);
        return newAppQuadCurve;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        startX.set(dragStartX);
        startY.set(dragStartY);
        endX.set(currentDragPosX);
        endY.set(currentDragPosY);
    }

    @Override
    public String getSVGClones(int tabIndent) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("affine", affineTransform.toArray(MatrixType.MT_2D_2x3));
        jsonObject.put("backgroundStyle", backgroundStyle.toJSON());
        jsonObject.put("startX", startX.get());
        jsonObject.put("startY", startY.get());
        jsonObject.put("controlX", controlX.get());
        jsonObject.put("controlY", controlY.get());
        jsonObject.put("endX", endX.get());
        jsonObject.put("endY", endY.get());
        return jsonObject;
    }
}
