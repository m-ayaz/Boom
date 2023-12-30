package com.boom.appshapes;

import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import static com.boom.tools.Tools.deepCopy;

public final class AppPolyline extends AppLineShape {
    public ObservableList<Double> points;

    public AppPolyline(double... points){
        super(new Polyline(points));
        this.points=((Polyline) styleableNode).getPoints();
    }

    @Override
    public AppNode copy() {
        if (points.size() <= 3 || (points.size() == 4 && points.get(0).equals(points.get(2)) && points.get(1).equals(points.get(3)))) {
            return null;
        }
        double[] xs = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            xs[i] = points.get(i);
        }
        AppPolyline newAppPolyline = new AppPolyline(xs);
        deepCopy(affineTransform, newAppPolyline.affineTransform);
        deepCopy(backgroundStyle, newAppPolyline.backgroundStyle);
        return newAppPolyline;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        points.setAll(dragStartX,dragStartY,  currentDragPosX,  currentDragPosY);
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
        jsonObject.put("affine",affineTransform.toArray(MatrixType.MT_2D_2x3));
        jsonObject.put("backgroundStyle",backgroundStyle.toJSON());
        jsonObject.put("points", points);
        return jsonObject;
    }
}
