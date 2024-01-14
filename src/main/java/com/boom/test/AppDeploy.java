package com.boom.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.boom.tools.Tools.print;


public class AppDeploy  {

    public static void main(String[] args) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "jpackage " +
                "--input D:\\MyProjects\\Boom\\out\\artifacts\\Boom_jar " +
                "--type exe " +
                "--name Boom " +
                "--main-jar Boom.jar " +
                "--dest D:\\MyProjects\\Deployments " +
                "--app-version 1.0.3 " +
                "--win-dir-chooser " +
                " --win-help-url https://github.com " +
                "--win-shortcut-prompt " +
                "--win-shortcut " +
                "--win-per-user-install " +
                "--verbose");
        print(builder.command().get(2));
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }

    }



}
