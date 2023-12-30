package com.boom.specialfeatures.mindmap;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * firstChildOffset: first child connection angle offset from parent connection angle
 * lastChildOffset: last child connection angle offset from parent connection angle
 */
public class TreeMindMapNode extends Circle {

    // todo make child distances and child angles an array, not a single number.

     DoubleProperty parentCenterX;
     DoubleProperty parentCenterY;
     DoubleBinding angleWithParent;
     DoubleProperty distanceToParent;

    public ObservableList<TreeMindMapNode> getChildren() {
        return children;
    }

    //    ObservableList<DoubleProperty> childrenConnectionAngles = FXCollections.observableList(new ArrayList<>());
    //    String parentId;
//    String id = uuid(100);
    //    List<String> childrenIds=new ArrayList<>();
    ObservableList<TreeMindMapNode> children = FXCollections.observableList(new ArrayList<>());
     DoubleProperty offsetAngleWithFirstChild;
     DoubleProperty offsetAngleWithLastChild;
     DoubleProperty distanceToEveryChild;
//    ObservableList<DoubleProperty> childrenConnectionDistances = FXCollections.observableList(new ArrayList<>());



    public TreeMindMapNode(DoubleProperty offsetAngleWithFirstChild, DoubleProperty offsetAngleWithLastChild, DoubleProperty distanceToEveryChild) {
        this.offsetAngleWithFirstChild = offsetAngleWithFirstChild;
        this.offsetAngleWithLastChild = offsetAngleWithLastChild;
        this.distanceToEveryChild = distanceToEveryChild;
    }

    public TreeMindMapNode(DoubleProperty offsetAngleWithFirstChild, DoubleProperty offsetAngleWithLastChild, DoubleProperty distanceToEveryChild,double centerX,double centerY,double startAngle) {
        this.offsetAngleWithFirstChild = offsetAngleWithFirstChild;
        this.offsetAngleWithLastChild = offsetAngleWithLastChild;
        this.distanceToEveryChild = distanceToEveryChild;
        angleWithParent=(new SimpleDoubleProperty(0)).add(startAngle);
        setCenterX(centerX);
        setCenterY(centerY);
    }

    public void addChild( TreeMindMapNode child) {
        children.add( child);
        child. parentCenterX=centerXProperty();
        child. parentCenterY=centerYProperty();
        child. distanceToParent=distanceToEveryChild;
//        print("______________________________________");
//        children.forEach(child1->print("child %d = %f,%f".formatted(children.indexOf(child1),child1.getCenterX(),child1.getCenterY())));

        int n=children.size();

//        print("______________________________");
        if(n==1){
//            child.assignToParentNode(centerXProperty(), centerYProperty(), distanceToEveryChild, angleWithParent.add(180));
//            child.assignToParentNode(this);
//            child.

            child. angleWithParent=angleWithParent.add(0);
//            print(children.get(0).angleWithParent.get());
        }else{
            for(int i=0;i<n;i++) {
//                children.get(i).assignToParentNode(centerXProperty(), centerYProperty(), distanceToEveryChild, angleWithParent.add(offsetAngleWithFirstChild).add(offsetAngleWithLastChild.subtract(offsetAngleWithFirstChild).multiply(1.0*i/(n-1))));
                children.get(i).angleWithParent=angleWithParent.add(offsetAngleWithFirstChild).add(offsetAngleWithLastChild.subtract(offsetAngleWithFirstChild).multiply(1.0*i/(n-1))).add(180);
//                print(children.get(i).angleWithParent.get());
            }
        }
        children.forEach(this::bindCenterCoordinates);
//        bindCenterCoordinates(child);
//        children.forEach(child1->print(child1.angleWithParent.get()));
//        print("child = "+child.angleWithParent.get());


    }

//    public void assignToParentNode(TreeMindMapNode parentNode) {
//        this.parentCenterX=parentNode.centerXProperty();
//        this.parentCenterY=parentNode.centerYProperty();
//        this.distanceToParent =parentNode.distanceToEveryChild;
//        if(this.angleWithParent==null){
//            throw new AppException(AppExceptionEnum.UnexpectedError);
//        }
//
//        bindCenterCoordinates();
//    }
















//    public void assignToParentNode(DoubleProperty parentCenterX, DoubleProperty parentCenterY, DoubleProperty parentConnectionDistance, DoubleBinding parentConnectionAngle) {
//
//
//        this.angleWithParent = parentConnectionAngle;
//        this.distanceToParent = parentConnectionDistance;
//        this.parentCenterX = parentCenterX;
//        this.parentCenterY = parentCenterY;
//
//        bindCenterCoordinates();
//
//        print("__________________");
//        print(parentCenterX.get());
//        print(parentCenterY.get());
//        print(getCenterX());
//        print(getCenterY());
//
//
//
//
////        setOnMouseDragged(mouseEvent -> {
////            parentConnectionDistance.set(Math.sqrt(Math.pow(parentCenterX.get() - mouseEvent.getX(), 2) + Math.pow(parentCenterY.get() - mouseEvent.getY(), 2)));
////            parentConnectionAngle.set(Math.atan2(parentCenterY.get() - mouseEvent.getY(), parentCenterX.get() - mouseEvent.getX()));
////        });
//    }

    void bindCenterCoordinates(TreeMindMapNode child){
        updateX(child);
        updateY(child);
        child.parentCenterX.addListener((ignored, Old, New) -> updateX(child));
        child.parentCenterY.addListener((ignored, Old, New) -> updateY(child));
        child.angleWithParent.addListener((ignored, Old, New) -> {
            updateX(child);
            updateY(child);
        });
        child.distanceToParent.addListener((ignored, Old, New) -> {
            updateX(child);
            updateY(child);
        });
    }

    void updateX(TreeMindMapNode child) {
        child.setCenterX(child.parentCenterX.doubleValue() + child.distanceToParent.doubleValue() * Math.cos(child.angleWithParent.doubleValue()*Math.PI/180));
    }

    void updateY(TreeMindMapNode child) {
        child. setCenterY(child.parentCenterY.doubleValue() + child.distanceToParent.doubleValue() * Math.sin(child.angleWithParent.doubleValue()*Math.PI/180));
    }
    
    public List<MindMapConnection> getConnections(){
        List<MindMapConnection> connections=new ArrayList<>();
        for(TreeMindMapNode child: children){
            connections.add(new MindMapConnection(
                     radiusProperty(),  child.radiusProperty(), centerXProperty(),
                     centerYProperty(),  child.centerXProperty(),  child.centerYProperty(),
                     new SimpleDoubleProperty(10),  new SimpleDoubleProperty(1.05),
                    new SimpleDoubleProperty(1.05), (Color) getStroke(), (Color) child.getStroke()
            ));
        }
        return connections;
    }

}