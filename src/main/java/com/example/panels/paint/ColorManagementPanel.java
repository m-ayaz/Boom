package com.example.panels.paint;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

import static com.example.tools.Tools.print;
import static com.example.tools.Tools.setSize;

public class ColorManagementPanel extends Popup {


//    ObjectProperty<Color> colorProperty = new SimpleObjectProperty<>();

    GridPane container=new GridPane();

    Rectangle colorPreview = new Rectangle(40, 40);

    ColorPicker colorPicker = new ColorPicker();


    Slider redValueBar = new Slider();
    Slider greenValueBar = new Slider();
    Slider blueValueBar = new Slider();
    Slider alphaValueBar = new Slider();


    TextField redValueField = new TextField();
    TextField greenValueField = new TextField();
    TextField blueValueField = new TextField();
    TextField alphaValueField = new TextField();


    TextField rgbaField = new TextField();

//    double previousDragPosX,previousDragPosY;
//    double currentDragPosX,currentDragPosY;


    public ColorManagementPanel(ObjectProperty<Color> colorProperty){

        super();

        getContent().add(container);


        setAutoHide(true);
        setHideOnEscape(true);
        setAutoFix(true);

//        container.setOnMousePressed(mouseEvent -> {
//            previousDragPosX=mouseEvent.getScreenX();
//            previousDragPosY=mouseEvent.getScreenY();
//        });
//
//        Circle v=new Circle();
//        v.setFill(new Color(1,0,0,1));
//
//        container.setOnMouseDragged(mouseEvent -> {
//
//            currentDragPosX=mouseEvent.getScreenX();
//            currentDragPosY= mouseEvent.getScreenY();;
//            container.setTranslateX(container.getTranslateX()+currentDragPosX-previousDragPosX);
//            container.setTranslateY(container.getTranslateY()+currentDragPosY-previousDragPosY);
//            previousDragPosX=currentDragPosX;
//                    previousDragPosY=currentDragPosY;
//        });

        int a=100;

        container.setBackground(Background.fill(Color.rgb(a,a,a,1)));


        setSize(redValueBar,200,40);
//        setSize(this,600,600);
//        setRow

        container.addRow(0,redValueBar,redValueField);
        container.addRow(1,greenValueBar,greenValueField);
        container.addRow(2,blueValueBar,blueValueField);
        container.addRow(3,alphaValueBar,alphaValueField);
        container.addRow(4,colorPicker,rgbaField);
        container.addRow(5,colorPreview);

        colorProperty.bindBidirectional(colorPicker.valueProperty());




        setRedValueBarBehavior(colorProperty);
        setRedValueFieldBehavior(colorProperty);
        setGreenValueBarBehavior(colorProperty);
        setGreenValueFieldBehavior(colorProperty);
        setBlueValueBarBehavior(colorProperty);
        setBlueValueFieldBehavior(colorProperty);
        setAlphaValueBarBehavior(colorProperty);
        setAlphaValueFieldBehavior(colorProperty);
        setColorPickerBehavior(colorProperty);
        setRGBAFieldBehavior(colorProperty);

        if(colorProperty.get()!=null) {
            redValueBar.setValue(colorProperty.get().getRed() * 255);
            greenValueBar.setValue(colorProperty.get().getGreen() * 255);
            blueValueBar.setValue(colorProperty.get().getBlue() * 255);
            alphaValueBar.setValue(colorProperty.get().getOpacity() * 255);
            redValueField.setText(String.valueOf(colorProperty.get().getRed() * 255));
            greenValueField.setText(String.valueOf(colorProperty.get().getGreen() * 255));
            blueValueField.setText(String.valueOf(colorProperty.get().getBlue() * 255));
            alphaValueField.setText(String.valueOf(colorProperty.get().getOpacity() * 255));
            colorProperty.set(colorProperty.get());
            colorPreview.setFill(colorProperty.get());
            colorPicker.setValue(colorProperty.get());
        }
    }

    void setRedValueBarBehavior(ObjectProperty<Color> colorProperty) {
        redValueBar.setMin(0);
        redValueBar.setMax(255);
        redValueBar.setOnMouseDragged(mouseEvent -> {
            redValueField.setText(String.valueOf(redValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setGreenValueBarBehavior(ObjectProperty<Color> colorProperty) {
        greenValueBar.setMin(0);
        greenValueBar.setMax(255);
        greenValueBar.setOnMouseDragged(mouseEvent -> {
            greenValueField.setText(String.valueOf(greenValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setBlueValueBarBehavior(ObjectProperty<Color> colorProperty) {
        blueValueBar.setMin(0);
        blueValueBar.setMax(255);
        blueValueBar.setOnMouseDragged(mouseEvent -> {
            blueValueField.setText(String.valueOf(blueValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setAlphaValueBarBehavior(ObjectProperty<Color> colorProperty) {
        alphaValueBar.setMin(0);
        alphaValueBar.setMax(255);
        alphaValueBar.setOnMouseDragged(mouseEvent -> {
            alphaValueField.setText(String.valueOf(alphaValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setRedValueFieldBehavior(ObjectProperty<Color> colorProperty) {
        redValueField.setOnKeyTyped(keyEvent -> {
            double val;
            try {
                val = Double.parseDouble(redValueField.getText());
                if (val <= 255 && val >= 0) {
                    redValueBar.setValue(Double.parseDouble(redValueField.getText()));
                }
            } catch (Exception e) {
                print(e);
            }
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setGreenValueFieldBehavior(ObjectProperty<Color> colorProperty) {
        greenValueField.setOnKeyTyped(keyEvent -> {
            double val;
            try {
                val = Double.parseDouble(greenValueField.getText());
                if (val <= 255 && val >= 0) {
                    greenValueBar.setValue(Double.parseDouble(greenValueField.getText()));
                }
            } catch (Exception e) {
                print(e);
            }
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setBlueValueFieldBehavior(ObjectProperty<Color> colorProperty) {
        blueValueField.setOnKeyTyped(keyEvent -> {
            double val;
            try {
                val = Double.parseDouble(blueValueField.getText());
                if (val <= 255 && val >= 0) {
                    blueValueBar.setValue(Double.parseDouble(blueValueField.getText()));
                }
            } catch (Exception e) {
                print(e);
            }
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setAlphaValueFieldBehavior(ObjectProperty<Color> colorProperty) {
        alphaValueField.setOnKeyTyped(keyEvent -> {
            double val;
            try {
                val = Double.parseDouble(alphaValueField.getText());
                if (val <= 255 && val >= 0) {
                    alphaValueBar.setValue(Double.parseDouble(alphaValueField.getText()));
                }
            } catch (Exception e) {
                print(e);
            }
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorProperty.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
        });
    }

    void setColorPickerBehavior(ObjectProperty<Color> colorProperty){
        colorPicker.setOnAction(event -> {
            Color c=colorPicker.getValue();
            redValueBar.setValue(c.getRed()*255);
            greenValueBar.setValue(c.getGreen()*255);
            blueValueBar.setValue(c.getBlue()*255);
            alphaValueBar.setValue(c.getOpacity()*255);
            redValueField.setText(String.valueOf(c.getRed()*255));
            greenValueField.setText(String.valueOf(c.getGreen()*255));
            blueValueField.setText(String.valueOf(c.getBlue()*255));
            alphaValueField.setText(String.valueOf(c.getOpacity()*255));
            colorProperty.set(c);
            colorPreview.setFill(c);
        });
    }
    
    void setRGBAFieldBehavior(ObjectProperty<Color> colorProperty){
        rgbaField.setOnKeyTyped(keyEvent -> {
            try{
                Color c=Color.valueOf(rgbaField.getText());
                redValueBar.setValue(c.getRed()*255);
                greenValueBar.setValue(c.getGreen()*255);
                blueValueBar.setValue(c.getBlue()*255);
                alphaValueBar.setValue(c.getOpacity()*255);
                redValueField.setText(String.valueOf(c.getRed()*255));
                greenValueField.setText(String.valueOf(c.getGreen()*255));
                blueValueField.setText(String.valueOf(c.getBlue()*255));
                alphaValueField.setText(String.valueOf(c.getOpacity()*255));
                colorProperty.set(c);
                colorPreview.setFill(c);
            }catch (Exception e){
                print(e);
            }
        });
    }

}
