package com.boom.appshapes;

import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import javafx.beans.property.DoubleProperty;
import javafx.scene.shape.CubicCurve;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;

public final class AppCubicCurve extends AppLineShape {

    public DoubleProperty startX, startY, controlX1, controlY1, controlX2, controlY2, endX, endY;

    public AppCubicCurve(double startX, double startY, double controlX1, double controlY1, double controlX2, double controlY2, double endX, double endY) {
        super(new CubicCurve(startX, startY, controlX1, controlY1, controlX2, controlY2, endX, endY));
        this.startX = ((CubicCurve) styleableNode).startXProperty();
        this.startY = ((CubicCurve) styleableNode).startYProperty();
        this.controlX1 = ((CubicCurve) styleableNode).controlX1Property();
        this.controlY1 = ((CubicCurve) styleableNode).controlY1Property();
        this.controlX2 = ((CubicCurve) styleableNode).controlX2Property();
        this.controlY2 = ((CubicCurve) styleableNode).controlY2Property();
        this.endX = ((CubicCurve) styleableNode).endXProperty();
        this.endY = ((CubicCurve) styleableNode).endYProperty();
    }

    @Override
    public AppNode copy() {
        if (startX.get() == startY.get() && endX.get() == endY.get())
            return null;
        AppCubicCurve newAppCubicCurve = new AppCubicCurve(startX.get(), startY.get(), controlX1.get(), controlY1.get(), controlX2.get(), controlY2.get(), endX.get(), endY.get());
        deepCopy(affineTransform, newAppCubicCurve.affineTransform);
        deepCopy(backgroundStyle, newAppCubicCurve.backgroundStyle);
        return newAppCubicCurve;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        startX.set(dragStartX);
        startY.set(dragStartY);
        controlX1.set(dragStartX / 3 * 2 + currentDragPosX / 3);
        controlY1.set(dragStartY / 3 * 2 + currentDragPosY / 3);
        controlX2.set(dragStartX / 3 + currentDragPosX / 3 * 2);
        controlY2.set(dragStartY / 3 + currentDragPosY / 3 * 2);
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
        jsonObject.put("controlX1", controlX1.get());
        jsonObject.put("controlY1", controlY1.get());
        jsonObject.put("controlX2", controlX2.get());
        jsonObject.put("controlY2", controlY2.get());
        jsonObject.put("endX", endX.get());
        jsonObject.put("endY", endY.get());
        return jsonObject;
    }
}
