package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppLineShape;
import com.boom.structures.abstracts.AppNode;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
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
    public void configureOnMouseEvent(MouseEvent mouseEvent, MainCanvasItemsHandler mainCanvasItemsHandler, SelectedObjectsController selectedObjectsController, double moveX, double moveY, double dragX, double dragY, double pressX, double pressY, double releaseX, double releaseY, double clickX, double clickY, double x, double y) {
        styleableNode.setVisible(true);
        if (points.size()<=2){
            points.setAll(x,y);
        }
        if ( mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)&&mouseEvent.getClickCount()==1) {
            points.addAll(x,y);
        } else if ( mouseEvent.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            selectedObjectsController.unselectAll();
            if(points.size()>=2) {
                points.set(points.size() - 2, x);
                points.set(points.size() - 1, y);
            }
        } else if ( mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)&& mouseEvent.getClickCount()>=2) {
            mainCanvasItemsHandler.copyToMainCanvas(this);
            points.clear();
        }
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
