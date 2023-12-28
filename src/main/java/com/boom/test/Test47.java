package com.boom.test;

import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppExceptionEnum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static com.boom.tools.Tools.print;


public class Test47 extends Application {

    public static void main(String[] args) {
        launch();
    }

//    boolean x=true;

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        Rectangle rectangle=new Rectangle(50,50,500,500);

        container.getChildren().add(rectangle);

        // create a input stream
        FileInputStream input = new FileInputStream("C:\\Users\\Mostafa\\Desktop\\a.png");

        // create a image
        Image image = new Image(input);

        ImagePattern imagePattern=new ImagePattern(image,0,0,0.2,0.25,true);



        rectangle.setFill(imagePattern);

//        throw new AppException(AppExceptionEnum.UnexpectedError);

        print(AppExceptionEnum.getEnum(AppExceptionEnum.AppNodeNotRegistered.toString()));
//        Rectangle rectangle1=new Rectangle(650,50,500,500);
//
//        ChessBoard p=new ChessBoard(600,16,Color.valueOf("00000088"),Color.valueOf("00000033"));
//        p.setTranslateX(607);
////        p.setTranslateY(600);
//
//        container.getChildren().addAll(new ChessBoard(600,16,Color.valueOf("00000088"),Color.valueOf("00000033")),rectangle);
//        container.getChildren().addAll(p,rectangle1);
//
//        rectangle.setFill(new LinearGradient(0,0,1,0,true,CycleMethod.NO_CYCLE,
//                new Stop(0,Color.TRANSPARENT),new Stop(0.2,Color.YELLOW),
//                new Stop(0.4,Color.valueOf("0000ff88")),new Stop(0.6,Color.GREEN),new Stop(0.8,Color.RED)
//                ,new Stop(1,Color.valueOf("ff5599cc"))
//                ));
//
//        rectangle1.setFill(new RadialGradient(0,0,0.5,0.5,0.55,true,CycleMethod.NO_CYCLE,
//                new Stop(0,Color.TRANSPARENT),new Stop(0.2,Color.YELLOW),
//                new Stop(0.4,Color.valueOf("0000ff88")),new Stop(0.6,Color.GREEN),new Stop(0.8,Color.RED)
//                ,new Stop(1,Color.valueOf("ff5599cc"))
//        ));



//        double a=200,b=400;
//
//        Rectangle rectangle=new Rectangle(a,a,b,b);
//        Rectangle rectangle1=new Rectangle(a,a,b,b);
//
//        container.getChildren().addAll(rectangle,rectangle1);
//
////        rectangle.setStroke(Color.BLACK);
//
////        rectangle.setFill(new LinearGradient(0,0,1,0,true,CycleMethod.REPEAT,
////                new Stop(0,Color.BLUE),new Stop(1,Color.RED)));
//
//        rectangle.setFill(new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.RED),new Stop(1,Color.TRANSPARENT)));
//        rectangle1.setFill(new LinearGradient(0,1,0,0,true, CycleMethod.NO_CYCLE,
//                new Stop(1, Color.BLUE),new Stop(0,Color.TRANSPARENT)));
//
//        container.setOnMouseClicked(mouseEvent -> {
//            print("clicked");
//            container.getChildren().clear();
//            if(x) {
//
//                container.getChildren().addAll(rectangle1, rectangle);
//            } else
//             {
//
//                container.getChildren().addAll(rectangle,rectangle1);
//            }
//
//            x=!x;
//        });



//        Rectangle rectangle=new Rectangle();
//
//        container.getChildren().add(rectangle);


//        LineChart<Number,Number> x=new LineChart<>(new NumberAxis(),new NumberAxis());
//
//        container.getChildren().add(x);
//
//        setCustomSize(x,400,400);

//        Scene


//        AppRectangle appRectangle=new AppRectangle(200,300);
//
//        print(appRectangle.toJSON());
//
//        Affine affine=new Affine();
//
//        Random rnd=new Random();
//
//        affine.prepend(new Rotate(50*rnd.nextDouble(),50*rnd.nextDouble(),50*rnd.nextDouble()));
//
//        print(affine);
//
////        Color
//
//        for(double u: affine.toArray(MatrixType.MT_2D_2x3)){
//            print(u);
//        }
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("asasas",affine.toArray(MatrixType.MT_2D_2x3));
//
//        print(jsonObject);
//
//        AppLinearGradient appLinearGradient=new AppLinearGradient(
//                new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
//                        new Stop(0, Color.TRANSPARENT),new Stop(1, Color.RED)));
//
//
////        print(appLinearGradient.toJSON());
//
////        print();
//
////        print(JSONObject.wrap("{\"asdasd\": 1}"));
//
//
//
////        Affine.affine(affine.toArray(MatrixType.MT_2D_2x3));
////        JSONArray jsonArray=new JSONArray();
////        jsonArray.pu
////        print(affine.toArray(MatrixType.MT_2D_3x3));
//
////        Files fileReader=new ("C:\\Users\\Mostafa\\Desktop\\a.json");
////        fileReader.
//
////        print(Files.readString(Path.of("C:\\Users\\Mostafa\\Desktop\\a.json")));
////        JSONObject jsonArray=new JSONObject(Files.readString(Path.of("C:\\Users\\Mostafa\\Desktop\\a.json")));
//////        fileReader.close();
////
////        print(jsonArray.get(0).getString(0));
//
////        String loc = "C:\\Users\\Mostafa\\Desktop\\a.json";
////        File file = new File(loc);
////        try {
////            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
//////            print("_____________________________");
//////            print(content);
////            print("_____________________________");
////            JSONObject json = new JSONObject(content);
////            print(json);
////
////
////            print("_____________________________");
////            JSONObject json2 = json.getJSONObject("background");
////            print(json2);
////
////            print("_____________________________");
////            JSONArray json3 = json2.getJSONArray("matches");
////            print(json3.getString(0));
////
//////            PrintWriter;
//////            FileWriter;
//////            FileOutputStream
////            JSONArray jsonArray=new JSONArray();
////            jsonArray.put(json);
//            jsonArray.put(json);
//
////            json.put
//
//            print(jsonArray);
//
//            PrintWriter fileWriter=new PrintWriter("C:\\Users\\Mostafa\\Desktop\\%s.json".formatted(uuid(10)));
//            fileWriter.println(json);
//            fileWriter.close();
////            print("_____________________________");
////            print(((HashMap)json2.get("mapType")).keySet());
////            JSONArray jarray = json2.getJSONArray("matches");
////            for (int i=0;i<jarray.length();i++){
////                System.out.println(jarray.getString(0));
////            }
//        } catch (IOException e) {
//            print(e);
////            e.printStackTrace();
//        }

//        JSONObject jsonObject=new JSONObject();
//
//        List<Double> doubles= Arrays.asList(1.0,1.1,1.6,1.6,4.0,3.0);
//
//        jsonObject.put("alsaskalsk",doubles);
//
//        FileChooser fileChooser = new FileChooser();
//
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
//
//        File saveFile = fileChooser.showSaveDialog(null);
//
//        PrintWriter printWriter = new PrintWriter(saveFile);
//
//        printWriter.println(jsonObject);





    }

    final class ChessBoard extends GridPane {

        public ChessBoard( double length, int n, Color boldColor, Color liteColor){
            super();
            double bitLength=length/n;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    Rectangle rectangle=new Rectangle(bitLength,bitLength);
                    add(rectangle,i,j);
                    if((i+j)%2==0){
                        rectangle.setFill(boldColor);
                    }else{
                        rectangle.setFill(liteColor);
                    }
                }
            }
        }

    }

    class MyException extends RuntimeException{
        public MyException(){
//            RuntimeException b=new RuntimeException();
            super();
//            setStackTrace(new StackTraceElement[]{new StackTraceElement("asdasd","sklad","asa",3)});
//            printStackTrace();
            for(int i=0;i< getStackTrace().length;i++){
                print("____________________________________________________________________");
                print(getStackTrace()[i].toString());
            }
//            print(getStackTrace());
//            print(getCause());
//            getMessage();
//            getLocalizedMessage();
//            getS
        }
    }



}
