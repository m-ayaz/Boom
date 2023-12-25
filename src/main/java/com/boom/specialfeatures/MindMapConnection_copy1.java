package com.boom.specialfeatures;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import static com.boom.tools.Tools.print;

/**
 * MindMap nodes are Circles, without transforms.
 */
public class MindMapConnection_copy1 extends SVGPath {

//    public Circle circle1=new Circle(), circle2=new Circle();

    public DoubleProperty rLeft=new SimpleDoubleProperty();
    public DoubleProperty rRight=new SimpleDoubleProperty();



//    double rLeft;
//    double rRight;
//    double distance;
//    DoubleProperty factorLeft=new SimpleDoubleProperty();
//    DoubleProperty factorRight=new SimpleDoubleProperty();
public DoubleProperty factorLeft=new SimpleDoubleProperty();
    public DoubleProperty factorRight=new SimpleDoubleProperty();

    public DoubleBinding l1Left =rLeft.multiply(factorLeft);
    public DoubleBinding l2Left = rLeft.multiply(1);
    public DoubleBinding l1Right =rRight.multiply(factorRight);
    public DoubleBinding l2Right =rRight.multiply(1);

//    l2Left.bind(circle1.radiusProperty());
//        l1Right.bind(circle2.radiusProperty().multiply(factorRight));
//        l2Right.bind(circle2.radiusProperty());

    public DoubleProperty middleThickness = new SimpleDoubleProperty();

    public DoubleBinding l1Leftp = rLeft.multiply(rLeft).divide(l1Left);
    public DoubleBinding hLeft = sqrt(rLeft.multiply(rLeft).divide(l1Left).divide(l1Left).negate().add(1)).multiply(rLeft);
    public DoubleBinding l2Leftp = l1Left.subtract(middleThickness.divide(2).multiply(sqrt(l1Left.multiply(l1Left).divide(rLeft).divide(rLeft).subtract(1))));

    public DoubleBinding l1Rightp = rRight.multiply(rRight).divide(l1Right);
    public DoubleBinding hRight = sqrt(rRight.multiply(rRight).divide(l1Right).divide(l1Right).negate().add(1)).multiply(rRight);
    public DoubleBinding l2Rightp = l1Right.subtract(middleThickness.divide(2).multiply(sqrt(l1Right.multiply(l1Right).divide(rRight).divide(rRight).subtract(1))));

//    public DoubleBinding l1Rightp = squared(rRight).divide(l1Right);
//    public DoubleBinding hRight = sqrt(squared(rRight).divide(squared(l1Right)).negate().add(1)).multiply(rRight);
//    public DoubleBinding l2Rightp = l1Right.subtract(middleThickness.divide(2).multiply(sqrt(squared(l1Right).divide(squared(rRight)).subtract(1))));

//    public DoubleProperty l1Rightp = new SimpleDoubleProperty();
//    public DoubleProperty hRight = new SimpleDoubleProperty();
//    public DoubleProperty l2Rightp = new SimpleDoubleProperty();

    public DoubleProperty centerX1=new SimpleDoubleProperty();
    public DoubleProperty centerY1=new SimpleDoubleProperty();
    public DoubleProperty centerX2=new SimpleDoubleProperty();
    public DoubleProperty centerY2=new SimpleDoubleProperty();

    public DoubleProperty distance=sqrt((centerX1.subtract(centerX2)).multiply(centerX1.subtract(centerX2)).add((centerY1.subtract(centerY2)).multiply((centerY1.subtract(centerY2)))));




    public Rotate rotate=new Rotate();
    public Translate translate=new Translate();

//    public void customize(){}

    public MindMapConnection_copy1(DoubleProperty radius1, DoubleProperty radius2, DoubleProperty centerX1, DoubleProperty centerY1, DoubleProperty centerX2, DoubleProperty centerY2, DoubleProperty factorLeft, DoubleProperty factorRight) {
//        this.circle1 = circle1;
//        this.circle2 = circle2;

        getTransforms().addAll(translate,rotate);

        rLeft.bindBidirectional(radius1);
        rRight.bindBidirectional(radius2);
        this.centerX1.bindBidirectional(centerX1);
        this.centerY1.bindBidirectional(centerY1);
        this.centerX2.bindBidirectional(centerX2);
        this.centerY2.bindBidirectional(centerY2);
        this.factorLeft.bindBidirectional(factorLeft);
        this.factorRight.bindBidirectional(factorRight);


//        l1Left.bind(circle1.radiusProperty().multiply(factorLeft));


        middleThickness.set(30);

//        rLeft=circle1.radiusProperty();
//        rRight=circle2.radiusProperty();

//        distance=sqrt(squared(circle1.centerXProperty().divide(circle2.centerXProperty())).add(squared(circle1.centerYProperty().divide(circle2.centerYProperty()))));

        translate.xProperty().bind(this.centerX1);
        translate.yProperty().bind(this.centerY1);

        rotate.pivotXProperty().bind(this.centerX1);
        rotate.pivotYProperty().bind(this.centerY1);

        rotate.angleProperty().bind(atan2Deg(this.centerY2.subtract(this.centerY1),this.centerX2.subtract(this.centerX1)));

        rotate.angleProperty().addListener((a,b,c)->{
            print("angle = "+c.doubleValue());
        });



//        Rotate rotate;

//        rotate.

//        distance.


//        if(distance<l1Left+l2Left+l1Right+l2Right){
//            throw new RuntimeException("MindMap nodes are too close. Value = %f".formatted(distance-(l1Left+l2Left+l1Right+l2Right)));
//        }

//        Function<Number,Number> function=(x)->{return x;};
//
//        function.apply(8);

//        Function<DoubleProperty, DoubleProperty> function = (x) -> x;
//        function.apply()



//        l1Leftp.bind(squared(rLeft).divide(l1Left));
//        hLeft.bind(sqrt(squared(rLeft).divide(squared(l1Left)).negate().add(1)).multiply(rLeft));
//        l2Leftp .bind(l1Left.subtract(middleThickness.divide(2).multiply(sqrt(squared(l1Left).divide(squared(rLeft)).subtract(1)))));

//        l1Rightp.bind(squared(rRight).divide(l1Right));
//        hRight.bind(sqrt(squared(rRight).divide(squared(l1Right)).negate().add(1)).multiply(rRight));
//        l2Rightp .bind(l1Right.subtract(middleThickness.divide(2).multiply(sqrt(squared(l1Right).divide(squared(rRight)).subtract(1)))));



//        print(squared(rLeft).get());
//        print(l1Leftp.get());
//        contentProperty().bind();

//        l1Rightp = rRight * rRight / l1Right;
//        hRight = Math.sqrt(1 - rRight * rRight / l1Right / l1Right) * rRight;
//        l2Rightp = l1Right - w / 2 * Math.sqrt(l1Right * l1Right / rRight / rRight - 1);

//        SVGPath svgPath = new SVGPath();
//        svgPath.setContent(("M%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f z").formatted(
//                l1Leftp, hLeft, l2Leftp, w / 2, l1Left + l2Left, w / 2, distance - l1Right - l2Right, w / 2, distance - l2Rightp, w / 2, distance - l1Rightp, hRight,
//                distance - l1Rightp, -hRight, distance - l2Rightp, -w / 2, distance - l1Right - l2Right, -w / 2, l1Left + l2Left, -w / 2, l2Leftp, -w / 2, l1Leftp, -hLeft));
//        svgPath.setFill(new LinearGradient(rLeft, 0, distance - rRight, 0, false, CycleMethod.NO_CYCLE,
//                new Stop(0, colorLeft), new Stop(1, colorRight)));
        setStroke(Color.TRANSPARENT);
        setStrokeWidth(0);

        update();
        l1Left.addListener((a,b,c)->update());
        l1Right.addListener((a,b,c)->update());
        l2Left.addListener((a,b,c)->update());
        hLeft.addListener((a,b,c)->update());
        l2Rightp.addListener((a,b,c)->update());
        l2Leftp.addListener((a,b,c)->update());
        l1Leftp.addListener((a,b,c)->update());
        hRight.addListener((a,b,c)->update());
        distance.addListener((a,b,c)->update());
        l2Right.addListener((a,b,c)->update());
        l1Rightp.addListener((a,b,c)->update());
        middleThickness.addListener((a, b, c)->update());
//        circle1.fillProperty().addListener((a,b,c)->update());
//        circle2.fillProperty().addListener((a,b,c)->update());
    }


    DoubleProperty sqrt(DoubleExpression x) {
        DoubleProperty y = new SimpleDoubleProperty();
        x.addListener( (a, b, c) -> y.set(Math.sqrt(c.doubleValue())));
        return y;
    }

//    DoubleProperty squared(DoubleExpression x) {
//
//        DoubleProperty y = new SimpleDoubleProperty();
//        x.addListener((a, b, c) -> {
//            print("&&&&&&&&&&&&&");
//            print(c);
//            y.setValue(c.doubleValue() * c.doubleValue());
//        });
//        return y;
//    }

    DoubleProperty atan2Deg(DoubleExpression y,DoubleExpression x){
        DoubleProperty z=new SimpleDoubleProperty();
        y.addListener((a,b,c)->z.setValue(Math.atan2(y.doubleValue(),x.doubleValue())*180/Math.PI));
        x.addListener((a,b,c)->z.setValue(Math.atan2(y.doubleValue(),x.doubleValue())*180/Math.PI));
        return z;
    }


//    DoubleProperty squared(DoubleBinding x) {
//        DoubleProperty y = new SimpleDoubleProperty();
//        x.addListener((a, b, c) -> y.set(c.doubleValue() * c.doubleValue()));
//        return y;
//    }

//    DoubleProperty

    void update(){
        setContent(("M%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f L%f,%f Q%f,%f %f,%f z").formatted(
                l1Leftp.get(), hLeft.get(), l2Leftp.get(), middleThickness.get() / 2, l1Left.get() + l2Left.get(), middleThickness.get() / 2, distance.get() - l1Right.get() - l2Right.get(), middleThickness.get() / 2, distance.get() - l2Rightp.get(), middleThickness.get() / 2, distance.get() - l1Rightp.get(), hRight.get(),
                distance.get() - l1Rightp.get(), -hRight.get(), distance.get() - l2Rightp.get(), -middleThickness.get() / 2, distance.get() - l1Right.get() - l2Right.get(), -middleThickness.get() / 2, l1Left.get() + l2Left.get(), -middleThickness.get() / 2, l2Leftp.get(), -middleThickness.get() / 2, l1Leftp.get(), -hLeft.get()));

        setFill(Color.rgb(0,0,0,0.3));
//        setFill(new LinearGradient(rLeft.get(), 0, distance.get() - rRight.get(), 0, false, CycleMethod.NO_CYCLE,
//                new Stop(0, (Color) circle1.getFill()), new Stop(1, (Color) circle2.getFill())));
    }

}
