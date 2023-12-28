package com.boom.appshapes;

import com.boom.structures.abstracts.AppAreaShape;
import com.boom.structures.abstracts.AppNode;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import org.json.JSONObject;

public final class AppPolygon extends AppAreaShape {

    ObservableList<Double> points;

    public AppPolygon(){
        super(new Polygon());
        points=((Polygon) shape).getPoints();
    }

    @Override
    public AppNode copy() {
        return null;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {

    }

    @Override
    public String getSVGClones(int tabIndent) {
        return null;
    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

}
