package com.boom.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.boom.tools.Tools.print;


public class Test43 extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws FileNotFoundException {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();

        String loc = "C:\\Users\\Mostafa\\Desktop\\a.json";
        File file = new File(loc);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
//            print("_____________________________");
//            print(content);
            print("_____________________________");
            JSONObject json = new JSONObject(content);
            print(json);


            print("_____________________________");
            JSONObject json2 = json.getJSONObject("background");
            print(json2);

            print("_____________________________");
            JSONArray json3 = json2.getJSONArray("matches");
            print(json3.getString(0));
//            print("_____________________________");
//            print(((HashMap)json2.get("mapType")).keySet());
//            JSONArray jarray = json2.getJSONArray("matches");
//            for (int i=0;i<jarray.length();i++){
//                System.out.println(jarray.getString(0));
//            }
        } catch (IOException e) {
            print(e);
//            e.printStackTrace();
        }

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
