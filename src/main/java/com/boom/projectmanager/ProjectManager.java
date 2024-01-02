package com.boom.projectmanager;

import com.boom.structures.abstracts.AppNode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        StringBuilder svgScript=new StringBuilder();
        svgScript.append("<svg width=\"1100\" height=\"1000\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">");
        svgScript.append("\n\t<defs>");
        validObjects.forEach(appNode -> svgScript.append(appNode.backgroundStyle.fillsToSVG(2)));
        validObjects.forEach(appNode -> svgScript.append(appNode.backgroundStyle.strokesToSVG(2)));
        svgScript.append("\n\t</defs>");
        validObjects.forEach(appNode -> svgScript.append(appNode.getSVGClones(1)));
        svgScript.append("\n</svg>");
        return svgScript;
    }

    public static List<AppNode> importProjectFromJSON(JSONArray jsonArray) {
        List<AppNode> appNodes=new ArrayList<>();
        jsonArray.forEach(j->appNodes.add(AppNode.parseJSON((JSONObject) j)));
        return appNodes;
    }



}















