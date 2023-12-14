package com.example.projectmanager;

import com.example.structures.AppNode;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.example.tools.Tools.print;

public class ProjectManager {

    public static JSONArray exportProjectAsJSON(List<AppNode> canvasPermanentObjects) {
        JSONArray jsonScript = new JSONArray();
        canvasPermanentObjects.forEach(obj -> jsonScript.put(obj.toJSON()));
        return jsonScript;
    }

    public static StringBuilder exportProjectAsTeX(List<AppNode> canvasPermanentObjects) {
//        print("exportProjectAsTeX" );
        StringBuilder texScript = new StringBuilder();
        texScript.append("% Generated with LaTeX Drawer (Free Edition)");
        texScript.append("\n\\documentclass{report}");
        texScript.append("\n\\usepackage{tikz,pgfplots}");
        texScript.append("\n\\begin{document}");
        texScript.append("\n\\begin{tikzpicture}[transform canvas={cm={1,0,0,-1,(0,0)}}]");
        for (AppNode canvasPermanentObject : canvasPermanentObjects) {
            texScript.append("\n").append(canvasPermanentObject.toTeX());
//            print(canvasPermanentObject.toTeX());
        }
        texScript.append("\n\\end{tikzpicture}");
        texScript.append("\n\\end{document}");
//        print(texScript);
        return texScript;
    }

    public static org.json.simple.JSONArray importProjectFromJSON(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (org.json.simple.JSONArray) parser.parse(new FileReader(filePath));
    }



}















