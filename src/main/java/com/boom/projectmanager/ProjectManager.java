package com.boom.projectmanager;

import com.boom.structures.abstracts.AppNode;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.boom.tools.Tools.print;

public class ProjectManager {

    public static JSONArray exportProjectAsJSON(List<AppNode> validObjects) {
        JSONArray jsonScript = new JSONArray();
        validObjects.forEach(obj -> jsonScript.put(obj.toJSON()));
        return jsonScript;
    }

    public static StringBuilder exportProjectAsTeX(List<AppNode> validObjects) {
//        print("exportProjectAsTeX" );
        StringBuilder texScript = new StringBuilder();
        texScript.append("% Generated with LaTeX Drawer (Free Edition)");
        texScript.append("\n\\documentclass{report}");
        texScript.append("\n\\usepackage{tikz,pgfplots}");
        texScript.append("\n\\begin{document}");
        texScript.append("\n\\begin{tikzpicture}[transform canvas={cm={1,0,0,-1,(0,0)}}]");
        for (AppNode validObject : validObjects) {
//            texScript.append("\n").append(validObject.toTeX());
//            print(validObject.toTeX());
        }
        texScript.append("\n\\end{tikzpicture}");
        texScript.append("\n\\end{document}");
//        print(texScript);
        return texScript;
    }

    public static StringBuilder exportProjectAsSVG(List<AppNode> validObjects) {
        return null;
    }

    public static org.json.simple.JSONArray importProjectFromJSON(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (org.json.simple.JSONArray) parser.parse(new FileReader(filePath));
    }



}















