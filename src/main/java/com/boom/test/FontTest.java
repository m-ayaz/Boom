package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.boom.tools.Tools.print;


public class FontTest extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {

        ScrollPane container = new ScrollPane();
        Scene scene = new Scene(container,800,600);
        stage.setScene(scene);
        stage.show();

        GridPane fontsPane=new GridPane();
        container.setContent(fontsPane);

        String text="This is a sample text";
        String faText="این یک متن نمونه است";

//        List<String> fontNames=Font.getFamilies();

//        print(Font.loadFonts(new FileInputStream("C:\\Users\\Mostafa\\Desktop\\a.ttf"),13));

        File fontsDir=new File("C:\\Users\\Mostafa\\AppData\\Local\\Microsoft\\Windows\\Fonts");

        List<File> fonts= Arrays.stream(Objects.requireNonNull(fontsDir.listFiles())).toList();
//        print(file.listFiles());

//        List<File> fonts= Arrays.stream(file.listFiles()).filter(file1 -> file1.getAbsoluteFile().isFile()).toList();
//
////        print(fonts.size());
//
//        for (int i = 0; i < fonts.size(); i++) {
//            print(fonts.get(i));
//        }

//        for (int i = 0; i < file.listFiles().length; i++) {
//            print(file.list()[i]);
//        }
//        print(file.listFiles().length);


//        DirectoryChooser directoryChooser=new DirectoryChooser();
//        directoryChooser.

//        fontNames.forEach(fontName->print(Font.font(fontName,50)));

//        print(fontNames.size());

//        print(Font.loadFont("asas",4));


//        Font.

//        print(Font.font().);

//        ComboBox<Label> comboBox=new ComboBox<>();


//        fontsPane.addRow(0,);

//        fontsPane.addRow(0,comboBox);



        print(fonts.size());
        fonts.forEach(fontFile->{
            try {

                Font font=Font.loadFont(new FileInputStream(fontFile),50);

                Text text1 = new Text(text);
                Text text3 = new Text(faText);
                Text text2 = new Text(font.getName());
                Rectangle emptySpace = new Rectangle(10, 20);
                Rectangle emptySpace1 = new Rectangle(10, 20);
                emptySpace.setFill(Color.TRANSPARENT);
                emptySpace1.setFill(Color.TRANSPARENT);

                text1.setFont(font);
                text2.setFont(font);
                text3.setFont(font);
//            text1.setFontSmoothingType(FontSmoothingType.LCD);
//            emptySpace.setFill(Color.BLANCHEDALMOND);
//            text1.setFont(Font.font(fontName,50));
//            print(Font.font(fontName));


//            try {
//                Font font=Font.loadFont(new FileInputStream("C:\\Users\\Mostafa\\Desktop\\a.ttf"),50);
//                text1.setFont(font);
//                text3.setFont(font);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }

//            text1.setStyle("-fx-font-family: \"%s\";-fx-font: 50 \"%s\"".formatted(Font.font(fontName).getFamily(),Font.font(fontName).getName()));
//            text3.setStyle("-fx-font-family: \"%s\";-fx-font: 50 \"%s\"".formatted(Font.font(fontName).getFamily(),Font.font(fontName).getName()));
//            print(Font.font("110_Besmellah"));
//            text3.setFont(Font.font(Font.loadFonts("System Regular").getFamily(),50));
//            text3.setFont(Font.font(fontName,50));
                text2.setFont(Font.font(50));
                fontsPane.addRow(fonts.indexOf(fontFile), text2, emptySpace, text1, emptySpace1, text3);

                text1.setSelectionStart(3);
                text1.setSelectionEnd(9);
                text1.setSelectionFill(Color.RED);
                text1.setCaretPosition(8);
//            text1.se;
//            text1.setS


//            Label label=new Label(fontName);
//            label.setFont(Font.font(50));
//            comboBox.getItems().add(label);
//            label.setTextFill(Color.BURLYWOOD);
            }catch (Exception e){
                print(e);
            }
        });


//        Text text1;
//        Label label;
//        TextField textField;
//        TextArea textArea;

//        text1.setSelectionStart();

//        comboBox.getItems().add(uuid(20));
//        comboBox.getItems().add(uuid(20));
//        comboBox.getItems().add(uuid(20));
//        comboBox.getItems().add(uuid(20));
//        comboBox.getItems().add(uuid(20));

//        Comparator<Double> comparator = Comparator.naturalOrder();
//
//        List<Double> x = Arrays.asList(Math.random(),Math.random());
//        x.sort(comparator);
//
//
//
////        x.set(0, 1.);
////        x.set(1, 100.);
//
////        print(x);
////
////        print(getScientificRepresentation(x.get(1)-x.get(0),20));
////
////        print("_____________________________________");
////        print(getScientificRepresentation(x.get(0),0)[0]);
////        print(getScientificRepresentation(x.get(1),0)[0]);
////
////        print(Math.floor(getScientificRepresentation(x.get(0),0)[0]));
////        print(Math.ceil(getScientificRepresentation(x.get(1),0)[0]));
//
//        double topMargin = .1, bottomMargin = .1;
//        double height = 100, h = 10;
//
//        int n = (int) Math.floor((1 - topMargin - bottomMargin) * height / h);
//
////        n=10;
////        print(n);
////
////        print(x.get(0));
//
//        print(x);
//        print("n = "+n);
////        print("____________________");
////        _TEMP1_(x.get(0),x.get(1));
//
//        double m = x.get(0), M = x.get(1);
//
//        int l1, l2;
//
//        double tickMarginDistance=Double.MAX_VALUE;
//        int bestK=Integer.MAX_VALUE;
//        int bestP=Integer.MAX_VALUE;
//        int bestL1=Integer.MAX_VALUE;
//        int bestL2=Integer.MAX_VALUE;
//        int bestNumberOfTicks=0;
//
//        for (int k : new int[]{1, 2, 4, 5}) {
//            int pNess = (int) Math.ceil(Math.log10(1.0 * (n - 1) / k / (M - m)));
//            for (int p = pNess; ; p--) {
//                l1 = (int) Math.floor(m * k * Math.pow(10, p));
//                l2 = (int) Math.ceil(M * k * Math.pow(10, p));
//                if(l2-l1==1){
//                    break;
//                }
////                actualNumberOfTicks=l2-l1+1;
//                if (l2-l1+1 <= n && l2-l1+1>bestNumberOfTicks) {
////                    print("______________________");
////                    print("L1 , L2 = " + l1 + " , " + l2);
//////                    print(l2 - l1 + 1 <= n);
////                    for (int j = l1; j <= l2; j++) {
////                        print(1.0 * j / k / Math.pow(10, p));
////                    }
//                    if(m-M+1.0 *(l2- l1) / k / Math.pow(10, p)<=tickMarginDistance){
//                        tickMarginDistance=m-M+1.0 *(l2- l1) / k / Math.pow(10, p);
//                        bestK=k;
//                        bestP=p;
//                        bestL1=l1;
//                        bestL2=l2;
//                        bestNumberOfTicks=l2-l1+1;
//                    }
////                    break;
//                }
//            }
//        }
//
//        print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
////        print(bestK);
////        print(bestP);
////        print(bestL1);
////        print(bestL2);
//        for(int j=bestL1;j<=bestL2;j++){
//            print(1.0*j/bestK/Math.pow(10,bestP));
//        }
//
////        print("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//



    }


}

