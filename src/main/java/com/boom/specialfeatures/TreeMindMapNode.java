package com.boom.specialfeatures;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * firstChildOffset: first child connection angle offset from parent connection angle
 * lastChildOffset: last child connection angle offset from parent connection angle
 */
public class TreeMindMapNode extends Circle {

    // todo make child distances and child angles an array, not a single number.

    public DoubleExpression parentCenterX;
    public DoubleExpression parentCenterY;
    ObservableList<DoubleExpression> childrenConnectionAngles = FXCollections.observableList(new ArrayList<>());
    //    String parentId;
//    String id = uuid(100);
    //    List<String> childrenIds=new ArrayList<>();
    ObservableList<TreeMindMapNode> children = FXCollections.observableList(new ArrayList<>());
    DoubleProperty firstChildOffset;
    DoubleProperty lastChildOffset;
    DoubleProperty childDistance;
    ObservableList<DoubleExpression> childrenConnectionDistances = FXCollections.observableList(new ArrayList<>());

    public TreeMindMapNode(DoubleProperty firstChildOffset, DoubleProperty lastChildOffset, DoubleProperty childDistance) {
        this.firstChildOffset = firstChildOffset;
        this.lastChildOffset = lastChildOffset;
        this.childDistance = childDistance;
    }

    public void addChild( TreeMindMapNode child) {
        int n=children.size()+1;
        if(n==1){

        }else{
            children.add( child);
            childrenConnectionAngles.add();
            child.setParentNode(centerXProperty(), centerYProperty(), childDistance, );
        }
        children.add(index, child);
//        child.setParentNode(cen);
        child.setParentNode(centerXProperty(), centerYProperty(), childDistance, );
    }

    public void setParentNode(DoubleExpression parentCenterX, DoubleExpression parentCenterY, DoubleProperty parentConnectionDistance, DoubleProperty parentConnectionAngle) {

//        Sphere h;
//        Sphere h;

        this.parentConnectionAngle = parentConnectionAngle;
        this.parentConnectionDistance = parentConnectionDistance;
        this.parentCenterX = parentCenterX;
        this.parentCenterY = parentCenterY;
        updateX();
        updateY();
        parentCenterX.addListener((ignored, Old, New) -> updateX());
        parentCenterY.addListener((ignored, Old, New) -> updateY());
        parentConnectionAngle.addListener((ignored, Old, New) -> {
            updateX();
            updateY();
        });
        parentConnectionDistance.addListener((ignored, Old, New) -> {
            updateX();
            updateY();
        });


        setOnMouseDragged(mouseEvent -> {
            parentConnectionDistance.set(Math.sqrt(Math.pow(parentCenterX.get() - mouseEvent.getX(), 2) + Math.pow(parentCenterY.get() - mouseEvent.getY(), 2)));
            parentConnectionAngle.set(Math.atan2(parentCenterY.get() - mouseEvent.getY(), parentCenterX.get() - mouseEvent.getX()));
        });
    }

    void updateX() {
        setCenterX(parentCenterX.doubleValue() - parentConnectionDistance.doubleValue() * Math.cos(parentConnectionAngle.doubleValue()));
    }

    void updateY() {
        setCenterY(parentCenterY.doubleValue() - parentConnectionDistance.doubleValue() * Math.sin(parentConnectionAngle.doubleValue()));
    }

}