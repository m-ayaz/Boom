package com.boom.panels.paint;

import com.boom.apppaints.AppColor;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;

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

//    public void registerColor()


    public ColorManagementPanel(AppColor appColor){

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


        setCustomSize(redValueBar,200,40);
//        setSize(this,600,600);
//        setRow

        container.addRow(0,redValueBar,redValueField);
        container.addRow(1,greenValueBar,greenValueField);
        container.addRow(2,blueValueBar,blueValueField);
        container.addRow(3,alphaValueBar,alphaValueField);
        container.addRow(4,colorPicker,rgbaField);
        container.addRow(5,colorPreview);

//        appColor.bindBidirectional(colorPicker.valueProperty());




        setRedValueBarBehavior(appColor);
        setRedValueFieldBehavior(appColor);
        setGreenValueBarBehavior(appColor);
        setGreenValueFieldBehavior(appColor);
        setBlueValueBarBehavior(appColor);
        setBlueValueFieldBehavior(appColor);
        setAlphaValueBarBehavior(appColor);
        setAlphaValueFieldBehavior(appColor);
        setColorPickerBehavior(appColor);
        setRGBAFieldBehavior(appColor);

        if(appColor.get() !=null) {
            redValueBar.setValue(((Color)appColor.get()).getRed() * 255);
            redValueField.setText(String.valueOf(((Color)appColor.get()).getRed() * 255));
            greenValueBar.setValue(((Color)appColor.get()).getGreen() * 255);
            greenValueField.setText(String.valueOf(((Color)appColor.get()).getGreen() * 255));
            blueValueBar.setValue(((Color)appColor.get()).getBlue() * 255);
            blueValueField.setText(String.valueOf(((Color)appColor.get()).getBlue() * 255));
            alphaValueBar.setValue(((Color)appColor.get()).getOpacity() * 255);
            alphaValueField.setText(String.valueOf(((Color)appColor.get()).getOpacity() * 255));
            colorPreview.setFill(appColor.get());
            colorPicker.setValue(((Color)appColor.get()));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        }
    }

    void setRedValueBarBehavior(AppColor appColor) {
        redValueBar.setMin(0);
        redValueBar.setMax(255);
        redValueBar.setOnMouseDragged(mouseEvent -> {
            redValueField.setText(String.valueOf(redValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setGreenValueBarBehavior(AppColor appColor) {
        greenValueBar.setMin(0);
        greenValueBar.setMax(255);
        greenValueBar.setOnMouseDragged(mouseEvent -> {
            greenValueField.setText(String.valueOf(greenValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setBlueValueBarBehavior(AppColor appColor) {
        blueValueBar.setMin(0);
        blueValueBar.setMax(255);
        blueValueBar.setOnMouseDragged(mouseEvent -> {
            blueValueField.setText(String.valueOf(blueValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setAlphaValueBarBehavior(AppColor appColor) {
        alphaValueBar.setMin(0);
        alphaValueBar.setMax(255);
        alphaValueBar.setOnMouseDragged(mouseEvent -> {
            alphaValueField.setText(String.valueOf(alphaValueBar.getValue()));
            colorPicker.setValue(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setRedValueFieldBehavior(AppColor appColor) {
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
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setGreenValueFieldBehavior(AppColor appColor) {
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
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setBlueValueFieldBehavior(AppColor appColor) {
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
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setAlphaValueFieldBehavior(AppColor appColor) {
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
            appColor.set(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            colorPreview.setFill(Color.color(redValueBar.getValue() / 255, greenValueBar.getValue() / 255, blueValueBar.getValue() / 255, alphaValueBar.getValue() / 255));
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }

    void setColorPickerBehavior(AppColor appColor){
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
            appColor.set(c);
            colorPreview.setFill(c);
            rgbaField.setText(appColor.getFormatted().replace("#",""));
        });
    }
    
    void setRGBAFieldBehavior(AppColor appColor){
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
                appColor.set(c);
                colorPreview.setFill(c);
            }catch (Exception e){
                print(e);
            }
        });
    }

}
