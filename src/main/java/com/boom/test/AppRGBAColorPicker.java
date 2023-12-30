package com.boom.test;

import com.boom.apppaints.AppColor;
import com.boom.apppaints.AppLinearGradient;
import com.boom.appshapes.AppRectangle;
import com.boom.tools.Chessboard;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import static com.boom.configuration.Configs.*;
import static com.boom.tools.Tools.print;
import static com.boom.tools.Tools.setCustomSize;

public class AppRGBAColorPicker extends Pane {


    AppRectangle redChanger, greenChanger, blueChanger, alphaChanger;

    RGBAFieldIndicator redIndicator;
    RGBAFieldIndicator greenIndicator;
    RGBAFieldIndicator blueIndicator;
    RGBAFieldIndicator alphaIndicator;

    AppLinearGradient redChangerBackground, greenChangerBackground, blueChangerBackground, alphaChangerBackground;


    DoubleProperty red = new SimpleDoubleProperty();
    DoubleProperty green = new SimpleDoubleProperty();
    DoubleProperty blue = new SimpleDoubleProperty();
    DoubleProperty alpha = new SimpleDoubleProperty();


    Chessboard opacityChessboard = new Chessboard(RGBA_COLOR_PICKER_BIT_LENGTH, NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y, NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X, Color.valueOf("00000099"), Color.valueOf("00000022"));

    AppColor appColor;

    public void registerColor(AppColor appColor) {
        this.appColor = appColor;
        double tempRed = ((Color) appColor.get()).getRed();
        double tempGreen = ((Color) appColor.get()).getGreen();
        double tempBlue = ((Color) appColor.get()).getBlue();
        double tempAlpha = ((Color) appColor.get()).getOpacity();
        red.set(tempRed);
        green.set(tempGreen);
        blue.set(tempBlue);
        alpha.set(tempAlpha);
    }

    public AppRGBAColorPicker(  ) {
        super();
        setBackground(Background.fill(Color.valueOf("00000022")));

        setCustomSize(this, RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X + RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN + RGBA_COLOR_PICKER_BIT_LENGTH_RIGHT_MARGIN, 4 * RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y + RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + RGBA_COLOR_PICKER_BIT_LENGTH_BOTTOM_MARGIN + 3 * RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN);


        redChanger = new AppRectangle(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X, RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y);
        greenChanger = new AppRectangle(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X, RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y);
        blueChanger = new AppRectangle(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X, RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y);
        alphaChanger = new AppRectangle(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X, RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y);

        redChanger.affineTransform.prependTranslation(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN, RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN);
        greenChanger.affineTransform.prependTranslation(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN, RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y + RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN);
        blueChanger.affineTransform.prependTranslation(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN, RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + 2 * RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y + 2 * RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN);
        alphaChanger.affineTransform.prependTranslation(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN, RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + 3 * RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y + 3 * RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN);
        opacityChessboard.setTranslateX(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN);
        opacityChessboard.setTranslateY(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + 3 * RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y + 3 * RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN);


        redChangerBackground = new AppLinearGradient(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.TRANSPARENT), new Stop(1, Color.TRANSPARENT)));
        greenChangerBackground = new AppLinearGradient(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.TRANSPARENT), new Stop(1, Color.TRANSPARENT)));
        blueChangerBackground = new AppLinearGradient(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.TRANSPARENT), new Stop(1, Color.TRANSPARENT)));
        alphaChangerBackground = new AppLinearGradient(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.TRANSPARENT), new Stop(1, Color.TRANSPARENT)));

        redChanger.backgroundStyle.addFill(redChangerBackground);
        greenChanger.backgroundStyle.addFill(greenChangerBackground);
        blueChanger.backgroundStyle.addFill(blueChangerBackground);
        alphaChanger.backgroundStyle.addFill(alphaChangerBackground);

        redChanger.backgroundStyle.setStrokeWidth(0.001);
        greenChanger.backgroundStyle.setStrokeWidth(0.001);
        blueChanger.backgroundStyle.setStrokeWidth(0.001);
        alphaChanger.backgroundStyle.setStrokeWidth(0.001);

        redIndicator = new RGBAFieldIndicator(COLOR_INDICATOR_TRIANGLE_WIDTH, 2 * COLOR_INDICATOR_TRIANGLE_HEIGHT + RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y, COLOR_INDICATOR_TRIANGLE_HEIGHT);
        greenIndicator = new RGBAFieldIndicator(COLOR_INDICATOR_TRIANGLE_WIDTH, 2 * COLOR_INDICATOR_TRIANGLE_HEIGHT + RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y, COLOR_INDICATOR_TRIANGLE_HEIGHT);
        blueIndicator = new RGBAFieldIndicator(COLOR_INDICATOR_TRIANGLE_WIDTH, 2 * COLOR_INDICATOR_TRIANGLE_HEIGHT + RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y, COLOR_INDICATOR_TRIANGLE_HEIGHT);
        alphaIndicator = new RGBAFieldIndicator(COLOR_INDICATOR_TRIANGLE_WIDTH, 2 * COLOR_INDICATOR_TRIANGLE_HEIGHT + RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y, COLOR_INDICATOR_TRIANGLE_HEIGHT);

        redIndicator.setTranslateX(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN);
        redIndicator.setTranslateY(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y / 2);

        greenIndicator.setTranslateX(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN);
        greenIndicator.setTranslateY(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + 3 * RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y / 2 + RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN);

        blueIndicator.setTranslateX(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN);
        blueIndicator.setTranslateY(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + 5 * RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y / 2 + RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN * 2);

        alphaIndicator.setTranslateX(RGBA_COLOR_PICKER_BIT_LENGTH_LEFT_MARGIN);
        alphaIndicator.setTranslateY(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN + 7 * RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_Y / 2 + RGBA_COLOR_PICKER_BIT_LENGTH_MIDDLE_MARGIN * 3);


        getChildren().addAll(redChanger.getStyleableNode(), greenChanger.getStyleableNode(), blueChanger.getStyleableNode(), opacityChessboard, alphaChanger.getStyleableNode(), redIndicator, greenIndicator, blueIndicator, alphaIndicator);

        redChanger.getStyleableNode().setOnMouseMoved(mouseEvent -> red.set(redChanger.getStyleableNode().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY()).getX() / RGBA_COLOR_PICKER_BIT_LENGTH / NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X));
        greenChanger.getStyleableNode().setOnMouseMoved(mouseEvent -> green.set(greenChanger.getStyleableNode().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY()).getX() / RGBA_COLOR_PICKER_BIT_LENGTH / NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X));
        blueChanger.getStyleableNode().setOnMouseMoved(mouseEvent -> blue.set(blueChanger.getStyleableNode().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY()).getX() / RGBA_COLOR_PICKER_BIT_LENGTH / NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X));
        alphaChanger.getStyleableNode().setOnMouseMoved(mouseEvent -> alpha.set(alphaChanger.getStyleableNode().sceneToLocal(mouseEvent.getSceneX(), mouseEvent.getSceneY()).getX() / RGBA_COLOR_PICKER_BIT_LENGTH / NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X));

        initialize(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN);

    }

    void initialize(double RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN) {
        redIndicator.translateXProperty().bind(red.multiply(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X).add(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN));
        greenIndicator.translateXProperty().bind(green.multiply(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X).add(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN));
        blueIndicator.translateXProperty().bind(blue.multiply(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X).add(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN));
        alphaIndicator.translateXProperty().bind(alpha.multiply(RGBA_COLOR_PICKER_BIT_LENGTH * NUMBER_OF_RGBA_COLOR_PICKER_BITS_PER_X).add(RGBA_COLOR_PICKER_BIT_LENGTH_TOP_MARGIN));

        red.addListener((a, b, c) -> update("red"));
        green.addListener((a, b, c) -> update("green"));
        blue.addListener((a, b, c) -> update("blue"));
        alpha.addListener((a, b, c) -> update("alpha"));

    }

    void update(String x) {
        print(x + " = " + red.get() + "," + green.get() + "," + blue.get() + "," + alpha.get());
        appColor.set(new Color(red.get(), green.get(), blue.get(), alpha.get()));
        redChangerBackground.getAppStop(0).appColor.set(new Color(0, green.get(), blue.get(), 1));
        redChangerBackground.getAppStop(1).appColor.set(new Color(1, green.get(), blue.get(), 1));
        greenChangerBackground.getAppStop(0).appColor.set(new Color(red.get(), 0, blue.get(), 1));
        greenChangerBackground.getAppStop(1).appColor.set(new Color(red.get(), 1, blue.get(), 1));
        blueChangerBackground.getAppStop(0).appColor.set(new Color(red.get(), green.get(), 0, 1));
        blueChangerBackground.getAppStop(1).appColor.set(new Color(red.get(), green.get(), 1, 1));
        alphaChangerBackground.getAppStop(0).appColor.set(new Color(red.get(), green.get(), blue.get(), 0));
        alphaChangerBackground.getAppStop(1).appColor.set(new Color(red.get(), green.get(), blue.get(), 1));
    }


}