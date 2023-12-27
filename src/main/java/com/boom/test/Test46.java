package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.boom.tools.Tools.setCustomSize;


public class Test46 extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

//        Rectangle rectangle=new Rectangle();
//
//        container.getChildren().add(rectangle);


        LineChart<Number,Number> x=new LineChart<>(new NumberAxis(),new NumberAxis());

        container.getChildren().add(x);

        setCustomSize(x,400,400);



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



}
