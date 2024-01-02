package com.boom.controllers;

import com.boom.icons.RotationIcon;
import com.boom.icons.ScalingIcon;
import com.boom.structures.abstracts.AppNode;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Affine;

import java.util.ArrayList;
import java.util.List;

import static com.boom.tools.Tools.*;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;

public class SelectedObjectsController {
    final short rotationIconOffset = 30;
    ObservableList<AppNode> buffer = FXCollections.observableList(new ArrayList<>());

    IntegerProperty bufferSize = new SimpleIntegerProperty(0);

    public double getMinX() {
        return minX.get();
    }

//    public DoubleProperty minXProperty() {
//        return minX;
//    }

    public double getMaxX() {
        return maxX.get();
    }

//    public DoubleProperty maxXProperty() {
//        return maxX;
//    }

    public double getMinY() {
        return minY.get();
    }

//    public DoubleProperty minYProperty() {
//        return minY;
//    }

    public double getMaxY() {
        return maxY.get();
    }

//    public DoubleProperty maxYProperty() {
//        return maxY;
//    }

    DoubleProperty minX = new SimpleDoubleProperty(0);
    DoubleProperty maxX = new SimpleDoubleProperty(0);
    DoubleProperty minY = new SimpleDoubleProperty(0);
    DoubleProperty maxY = new SimpleDoubleProperty(0);
    DoubleProperty centerX = new SimpleDoubleProperty(0);
    DoubleProperty centerY = new SimpleDoubleProperty(0);
    //    public DoubleProperty width=new SimpleDoubleProperty();
//    public DoubleProperty height=new SimpleDoubleProperty();
    double pressPosX;
    double pressPosY;
    double previousPosX;
    double previousPosY;
    double currentPosX;
    double currentPosY;
    double scalingFixedPointX;
    double scalingFixedPointY;
    double scalingFactorX;
    double scalingFactorY;
    List<Affine> oldAffineTransforms;

    public SelectedObjectsController(RotationIcon rotationIcon, List<ScalingIcon> scalingIcons, Circle rotationFixedPoint) {

//        width.bind(maxX.subtract(minX));
//        height.bind(maxY.subtract(minY));

        centerX.bind(minX.add(maxX).divide(2));
        centerY.bind(minY.add(maxY).divide(2));

        bindPositions(rotationIcon, scalingIcons);

        bindVisibilities(rotationIcon, scalingIcons, rotationFixedPoint);

        rotationFixedPoint.setOnMousePressed(mouseEvent -> {
            currentPosX = mouseEvent.getX();
            currentPosY = mouseEvent.getY();
        });

        rotationFixedPoint.setOnMouseDragged(mouseEvent -> {
            previousPosX = currentPosX;
            previousPosY = currentPosY;
            currentPosX = mouseEvent.getX();
            currentPosY = mouseEvent.getY();
            rotationFixedPoint.setCenterX(rotationFixedPoint.getCenterX() + currentPosX - previousPosX);
            rotationFixedPoint.setCenterY(rotationFixedPoint.getCenterY() + currentPosY - previousPosY);
        });

        bufferSize.addListener((a, b, c) -> {

//            if(bufferSize.get()==1){
//                print((AppLine) buffer.get(0));
//                print();
//            }
//            print(buffer.get(0));

            minX.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getX()).min().orElse(Double.POSITIVE_INFINITY));
            maxX.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getX() + obj1.border.getWidth()).max().orElse(Double.NEGATIVE_INFINITY));
            minY.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getY()).min().orElse(Double.POSITIVE_INFINITY));
            maxY.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getY() + obj1.border.getHeight()).max().orElse(Double.NEGATIVE_INFINITY));

            buffer.forEach(obj -> obj.getStyleableNode().boundsInParentProperty().addListener((_1, oldVal, newVal) -> {
                minX.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getX()).min().orElse(Double.POSITIVE_INFINITY));
                maxX.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getX() + obj1.border.getWidth()).max().orElse(Double.NEGATIVE_INFINITY));
                minY.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getY()).min().orElse(Double.POSITIVE_INFINITY));
                maxY.set(buffer.stream().mapToDouble(obj1 -> obj1.border.getY() + obj1.border.getHeight()).max().orElse(Double.NEGATIVE_INFINITY));
            }));

            buffer.forEach(obj -> obj.getStyleableNode().setOnMousePressed(mouseEvent -> {
                currentPosX = mouseEvent.getSceneX();
                currentPosY = mouseEvent.getSceneY();
            }));

            buffer.forEach(obj -> obj.getStyleableNode().setOnMouseDragged(mouseEvent -> {
                previousPosX = currentPosX;
                previousPosY = currentPosY;
                currentPosX = mouseEvent.getSceneX();
                currentPosY = mouseEvent.getSceneY();
//                print("dsfjkdsfj");
                buffer.forEach(obj1 -> {
//                    print(uuid(20));
                    obj1.affineTransform.prependTranslation(currentPosX - previousPosX, currentPosY - previousPosY);
                });
                rotationIcon.setVisible(false);
                updateFixedPointPosition(rotationFixedPoint);
            }));

            buffer.forEach(obj -> obj.getStyleableNode().setOnMouseReleased(mouseEvent -> {
                rotationIcon.setVisible(true);
                updateFixedPointPosition(rotationFixedPoint);
            }));
            updateFixedPointPosition(rotationFixedPoint);

//
//            if(bufferSize.get()==2){
////                print(((Line) buffer.get(0).getStyleableNode()));
////                print(buffer.get(0).getStyleableNode().getOnMouseDragged());
//                buffer.get(0).getStyleableNode().setOnMouseMoved(mouseEvent -> {
//                    print(uuid(10));
//                });
//                buffer.get(1).getStyleableNode().setOnMouseMoved(mouseEvent -> {
//                    print(uuid(20));
//                });
//            }


        });

        rotationIcon.setOnMousePressed(mouseEvent -> {
            currentPosX = mouseEvent.getX();
            currentPosY = mouseEvent.getY();
        });

        rotationIcon.setOnMouseDragged(mouseEvent -> {
            previousPosX = currentPosX;
            previousPosY = currentPosY;
            currentPosX = mouseEvent.getX();
            currentPosY = mouseEvent.getY();
            buffer.forEach(obj -> obj.affineTransform.prependRotation(
                    atan2(currentPosY - rotationFixedPoint.getCenterY(), currentPosX - rotationFixedPoint.getCenterX()) * 180 / PI -
                            atan2(previousPosY - rotationFixedPoint.getCenterY(), previousPosX - rotationFixedPoint.getCenterX()) * 180 / PI,
                    rotationFixedPoint.getCenterX(), rotationFixedPoint.getCenterY()));
            rotationIcon.setVisible(false);
        });

        rotationIcon.setOnMouseReleased(mouseEvent -> {
            updateFixedPointPosition(rotationFixedPoint);
            rotationIcon.setVisible(true);
        });

        scalingIcons.forEach(scalingIcon -> {

            int objIndex = scalingIcons.indexOf(scalingIcon);
            boolean hasScalingFactorX = true;
            boolean hasScalingFactorY = true;
            switch (objIndex) {
                case 1, 5 -> hasScalingFactorY = false;
                case 3, 7 -> hasScalingFactorX = false;
            }

            boolean finalHasScalingFactorX = hasScalingFactorX;
            boolean finalHasScalingFactorY = hasScalingFactorY;

            scalingIcon.setOnMousePressed(mouseEvent -> {
                currentPosX = mouseEvent.getX();
                currentPosY = mouseEvent.getY();
                scalingFixedPointX = 2 * centerX.get() - scalingIcon.getCenterX();
                scalingFixedPointY = 2 * centerY.get() - scalingIcon.getCenterY();
                oldAffineTransforms = new ArrayList<>();
                buffer.forEach(obj1 -> oldAffineTransforms.add(deepCopy(obj1.affineTransform)));
                pressPosX = mouseEvent.getX();
                pressPosY = mouseEvent.getY();
            });

            scalingIcon.setOnMouseDragged(mouseEvent -> {

                previousPosX = currentPosX;
                previousPosY = currentPosY;
                currentPosX = mouseEvent.getX();
                currentPosY = mouseEvent.getY();

                scalingFactorX = finalHasScalingFactorX ? (currentPosX - scalingFixedPointX) / (pressPosX - scalingFixedPointX) : 1;
                scalingFactorY = finalHasScalingFactorY ? (currentPosY - scalingFixedPointY) / (pressPosY - scalingFixedPointY) : 1;

                buffer.forEach(obj1 -> {
                    Affine newObjTransform = deepCopy(oldAffineTransforms.get(buffer.indexOf(obj1)));
                    newObjTransform.prependScale(scalingFactorX, scalingFactorY, scalingFixedPointX, scalingFixedPointY);
                    obj1.affineTransform.setToTransform(newObjTransform);
                });

                rotationIcon.setVisible(false);
            });

            scalingIcon.setOnMouseReleased(mouseEvent -> {
                updateFixedPointPosition(rotationFixedPoint);
                rotationIcon.setVisible(true);
            });
        });


    }

    public IntegerProperty bufferSizeProperty() {
        return bufferSize;
    }

    public ObservableList<AppNode> getBuffer() {
        return buffer;
    }

    public DoubleProperty centerXProperty() {
        return centerX;
    }

    public DoubleProperty centerYProperty() {
        return centerY;
    }

    public boolean isSelected(AppNode appStructure) {
        return buffer.contains(appStructure);
    }

    public void reverseSelection(AppNode appNode) {
        if (isSelected(appNode)) {
            unselect(appNode);
        } else {
            select(appNode);
        }
    }

    public void select(AppNode appNode) {
        if (!buffer.contains(appNode)) {
            buffer.add(appNode);
            appNode.border.setVisible(true);
        }
        bufferSize.setValue(buffer.size());
    }

//    public void selectAll(){
//
//    }

    public void unselect(AppNode appNode) {
        buffer.remove(appNode);
        appNode.border.setVisible(false);
        bufferSize.setValue(buffer.size());
    }

    public void unselectAll() {
        buffer.forEach(obj -> obj.border.setVisible(false));
        buffer.clear();
        bufferSize.setValue(0);
    }

    void bindPositions(RotationIcon rotationIcon, List<ScalingIcon> scalingIcons) {

        rotationIcon.centerXProperty().bind(minX.add(maxX).divide(2));
        rotationIcon.centerYProperty().bind(minY.subtract(rotationIconOffset));

        scalingIcons.get(0).centerXProperty().bind(minX);
        scalingIcons.get(0).centerYProperty().bind(minY);

        scalingIcons.get(1).centerXProperty().bind(minX);
        scalingIcons.get(1).centerYProperty().bind(minY.add(maxY).divide(2));

        scalingIcons.get(2).centerXProperty().bind(minX);
        scalingIcons.get(2).centerYProperty().bind(maxY);

        scalingIcons.get(3).centerXProperty().bind(minX.add(maxX).divide(2));
        scalingIcons.get(3).centerYProperty().bind(maxY);

        scalingIcons.get(4).centerXProperty().bind(maxX);
        scalingIcons.get(4).centerYProperty().bind(maxY);

        scalingIcons.get(5).centerXProperty().bind(maxX);
        scalingIcons.get(5).centerYProperty().bind(minY.add(maxY).divide(2));

        scalingIcons.get(6).centerXProperty().bind(maxX);
        scalingIcons.get(6).centerYProperty().bind(minY);

        scalingIcons.get(7).centerXProperty().bind(minX.add(maxX).divide(2));
        scalingIcons.get(7).centerYProperty().bind(minY);


    }

    void bindVisibilities(RotationIcon rotationIcon, List<ScalingIcon> scalingIcons, Circle rotationFixedPoint) {
        scalingIcons.forEach(obj -> obj.visibleProperty().bind(rotationIcon.visibleProperty()));
        rotationFixedPoint.visibleProperty().bind(bufferSize.isNotEqualTo(0));
    }

    void updateFixedPointPosition(Circle rotationFixedPoint) {
        rotationFixedPoint.setCenterX(minX.get() / 2 + maxX.get() / 2);
        rotationFixedPoint.setCenterY(minY.get() / 2 + maxY.get() / 2);
    }


}