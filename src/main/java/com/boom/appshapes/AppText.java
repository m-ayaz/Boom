package com.boom.appshapes;

import javafx.geometry.VPos;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.boom.tools.Tools.print;

public final class AppText extends Text {

//    public TextArea tempText =new TextArea();

    Font font = new Font(0);

//    Rectangle border = new Rectangle();

    List<Double> selectorPositions;

    AtomicBoolean isKeyEventHandled = new AtomicBoolean();
//    ListProperty<String> s=new SimpleListProperty<>();
//    String currentLine;

    public AppText(double x, double y, String text) {

        bindPosition(x, y);

        setFont(new Font(20));

        setText(text);

        setOnMouseClicked(mouseEvent -> requestFocus());

        setOnKeyTyped(keyEvent -> {
            Set<String> keyExceptions = Set.of(KeyCode.ENTER.getChar(), KeyCode.ESCAPE.getChar());
            if (keyEvent.getCharacter().equals(KeyCode.BACK_SPACE.getChar())) {
                setText(getText().substring(0, getText().length() - 1));
                isKeyEventHandled.set(true);
            } else if (!keyExceptions.contains(keyEvent.getCharacter())) {
                print(keyEvent.getCharacter());
                setText(getText() + keyEvent.getCharacter());
                isKeyEventHandled.set(true);
            }
        });

        setOnKeyReleased(keyEvent -> {
            if (isKeyEventHandled.get()) {
                isKeyEventHandled.set(false);
                return;
            }
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                setText(getText() + "\n");
            }
        });

//        border.setFill(new Color(0, 0, 0, 0));
//        border.setStroke(new Color(1, 0, 0, 1));
    }


    public String getTeX() {
        return null;
    }

    public String getJSON() {
        return null;
    }


    public void reset() {

    }


    public void draw(double dragStartX, double dragStartY, double currentDragPosX, double currentDragPosY) {

    }


    public AppText copy() {
        return null;
    }

    void bindPosition(double x, double y) {
        setTextOrigin(VPos.TOP);
        setBoundsType(TextBoundsType.VISUAL);
        boundsInParentProperty().addListener((_1, _2, val) -> {
            setX(x - val.getWidth() / 2);
            setY(y - val.getHeight() / 2);
        });
    }

//    public Rectangle getBorder() {
//        return border;
//    }

}
