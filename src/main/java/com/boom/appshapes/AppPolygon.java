package com.boom.appshapes;

import com.boom.controllers.MainCanvasItemsHandler;
import com.boom.controllers.SelectedObjectsController;
import com.boom.structures.abstracts.AppAreaShape;
import com.boom.structures.abstracts.AppNode;
import com.boom.structures.abstracts.AppPaint;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.MatrixType;
import org.json.JSONObject;

import java.util.stream.Collectors;

import static com.boom.tools.Tools.*;

public  class AppPolygon extends AppAreaShape {

    public ObservableList<Double> points;

    public AppPolygon(double... points){
        super(new Polygon(points));
        this.points=((Polygon) shape).getPoints();
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
        AppPolygon newAppPolygon = new AppPolygon(xs);
        deepCopy(affineTransform, newAppPolygon.affineTransform);
        deepCopy(backgroundStyle, newAppPolygon.backgroundStyle);
        return newAppPolygon;
    }

    @Override
    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {
        styleableNode.setVisible(true);
        points.setAll(dragStartX,dragStartY,  currentDragPosX,  currentDragPosY);
    }

    @Override
    public String getSVGClones(int tabIndent) {
        double[] dissectedTransform = dissectAffineTransform(affineTransform);
        StringBuilder stringBuilder = new StringBuilder();
        for (AppPaint appPaint : backgroundStyle.getFillArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<polygon points=\"%s\" fill=\"url(#%s)\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(points.stream().map(p1->p1+(points.indexOf(p1)%2==0?",":" ")).collect(Collectors.joining("")), appPaint.id, affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        for (AppPaint appPaint : backgroundStyle.getStrokeArray()) {
            stringBuilder.append("\n").append("\t".repeat(tabIndent)).append("<polygon points=\"%s\" fill=\"transparent\" stroke=\"url(#%s)\" stroke-width=\"%f\" transform=\"translate(%f,%f) rotate(%f) scale(%f,%f) rotate(%f)\"/>".formatted(points.stream().map(p1->p1+(points.indexOf(p1)%2==0?",":" ")).collect(Collectors.joining("")), appPaint.id, backgroundStyle.getStrokeWidth(), affineTransform.getTx(), affineTransform.getTy(), dissectedTransform[0], dissectedTransform[1], dissectedTransform[2], dissectedTransform[3]));
        }
        return stringBuilder.toString();
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        jsonObject.put("id", id);
        jsonObject.put("affine",arrayToList(affineTransform.toArray(MatrixType.MT_2D_2x3)));
        jsonObject.put("backgroundStyle",backgroundStyle.toJSON());
        jsonObject.put("points", points);
        return jsonObject;
    }

}
