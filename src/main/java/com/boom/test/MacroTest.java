package com.boom.test;

import com.boom.exceptions.AppException;
import com.boom.structures.enums.AppExceptionEnum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.util.*;
import java.util.function.Function;

import static com.boom.tools.Tools.print;


public class MacroTest extends Application {

    public static void main(String[] args) {
        launch();
    }

//    DataComparator dataComparator = new DataComparator();

    @Override
    public void start(Stage stage) throws Exception {

        Pane container = new Pane();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();


        SVGPath svgPath=new SVGPath();
        svgPath.setContent("M200,200 L500,500");

        svgPath.setStroke(Color.RED);
        svgPath.setStrokeWidth(10);

        container.getChildren().add(svgPath);

        String x="vars var0 var1 var2 var3 var4 var5\nparams par0=4 par1=aksaldkla\nLINE var0 var1 var2 var3\n" +
                "LINE var2 var3 var4 var5";

        print(x);

        MacroControlledSVGPath macroControlledSvgPath =new MacroControlledSVGPath();
        macroControlledSvgPath.setMacro(x);

//        svgPathMacro.parseMacroLine(x.split("\n")[0]);

//        print("__________________________");
//        print(macroControlledSvgPath.variableNames);
//        print(macroControlledSvgPath.parameterNames);

//        svgPathMacro.parseMacroLine(x.split("\n")[2]);
//        svgPathMacro.parseMacroLine(x.split("\n")[3]);

//        print(svgPathMacro.objects.get(1).apply(new double[]{50,50,100,100,200,200}));

//        Function<double[],String> h=vars->String.join(" ", macroControlledSvgPath.objects.stream().map(stringFunction -> stringFunction.apply(vars)).toList());

        print(macroControlledSvgPath.apply(new double[]{50,50,100,100,200,200}));

        SVGPath svgPath1=macroControlledSvgPath.apply(new double[]{50*Math.random(),50*Math.random(),100*Math.random(),100*Math.random(),200*Math.random(),200*Math.random()});

        svgPath1.setStrokeWidth(10);
        svgPath1.setStroke(Color.BROWN);
        container.getChildren().add(svgPath1);




//        parseMacro(x);

    }

    void detectMacroVariables(){

    }

    void parseMacro(String macro,String commentIdentifier){
        String[] x=macro.split("\n");

        print(x);
    }




}

class MacroControlledSVGPath implements Function<double[], SVGPath> {

    public final List<String> variableNames = new ArrayList<>();
    public final List<String> parameterNames = new ArrayList<>();

    public final List<Function<double[],String>> objects=new ArrayList<>();

    @Override
    public SVGPath apply(double[] doubles) {
        SVGPath svgPath=new SVGPath();
        svgPath.setContent(String.join(" ", objects.stream().map(stringFunction -> stringFunction.apply(doubles)).toList()));
        return svgPath;
    }

    public String getMacro() {
        return macro;
    }

    private String macro;


    private final String commentIdentifier="%";

//    private SVGPathMacro(){
//
//    }

    public void setMacro(String macro) {
        this.macro = macro;
        parseMacro();
    }

    void parseMacro(){
        String[] macroLines=macro.split("\n");
        for (String macroLine : macroLines) {
            parseMacroLine(macroLine);
        }
//        print(macroLines);
    }

    //todo config this.
    private void verifyName(String name) throws AppException{
        if(false){
            throw new AppException(AppExceptionEnum.UnexpectedError);
        }
    }

    private void parseMacroLine(String macroLine){

        String[] analyzedMacroLine=macroLine.split(" ");
        switch (analyzedMacroLine[0]){
            case "vars"->{
                for (int i = 1; i < analyzedMacroLine.length; i++) {
                    verifyName(analyzedMacroLine[i]);
                    //todo no duplicate var!
                    if(variableNames.contains(analyzedMacroLine[i])){
                        throw new AppException(AppExceptionEnum.UnexpectedError);
                    }else{
                        variableNames.add(analyzedMacroLine[i]);
                    }
                }
//                return null;
            }
            case "params"->{
                for (int i = 1; i < analyzedMacroLine.length; i++) {
                    verifyName(analyzedMacroLine[i]);
                    //todo no duplicate param!
                    if(parameterNames.contains(analyzedMacroLine[i])){
                        throw new AppException(AppExceptionEnum.UnexpectedError);
                    }else{
                        parameterNames.add(analyzedMacroLine[i]);
                    }
                }
//                return null;
            }
            case "LINE"-> objects.add(
                    vars -> "M%f,%f L%f,%f".formatted(
                    vars[variableNames.indexOf(analyzedMacroLine[1])],
                    vars[variableNames.indexOf(analyzedMacroLine[2])],
                    vars[variableNames.indexOf(analyzedMacroLine[3])],
                    vars[variableNames.indexOf(analyzedMacroLine[4])]));
            default -> throw new AppException(AppExceptionEnum.UnexpectedError);
        }



    }


//    private void detectVariables(String macro) {
//
//    }

}

