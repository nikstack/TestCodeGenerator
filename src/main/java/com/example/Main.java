package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Path to json: [poker-tests.json] ");
        String json = scanner.nextLine();
        System.out.print("Path to php: [EvaluatorTest.php] ");
        String dest = scanner.nextLine();
        System.out.print("Path to template: [poker-tests-template.txt] ");
        String template = scanner.nextLine();
        if (json.equals("")) {
            json = "poker-tests.json";
        }
        if (dest.equals("")) {
            dest = "EvaluatorTest.php";
        }
        if (template.equals("")) {
            template = "poker-tests-template.txt";
        }

        String jsonContent = Files.readString(Path.of(json));
        String testTemplate = Files.readString(Path.of(template));
        StringBuilder outputBuilder = new StringBuilder("<?php\n\n\n");

        JSONObject jo = new JSONObject(jsonContent);
        outputBuilder.append(getClassCode(jo.getString("namespace"), jo.getJSONArray("uses"), jo.getString("className")));

        JSONArray tests = jo.getJSONArray("tests");
        outputBuilder.append(getTestCode(tests, testTemplate));
        outputBuilder.append("\n}");

        Files.write(Path.of(dest), outputBuilder.toString().getBytes());
    }

    private static String getClassCode(String namespace, JSONArray uses, String className) {
        StringBuilder classCodeBuilder = new StringBuilder();
        classCodeBuilder.append("namespace ").append(namespace).append(";\n\n\n");
        for (int i = 0; i < uses.length(); i++) {
            String use = uses.getString(i);
            classCodeBuilder.append("use ").append(use).append(";\n");
        }
        classCodeBuilder.append("\n\nclass ").append(className).append(" {\n\n");
        return classCodeBuilder.toString();
    }

    private static String getTestCode(JSONArray tests, String testTemplate) {
        StringBuilder testCodeBuilder = new StringBuilder();
        for (int i = 0; i < tests.length(); i++) {
            JSONObject test = tests.getJSONObject(i);
            String testCode = String.format(testTemplate, test.get("name"), test.get("input"), test.get("output"));
            System.out.println(testCode);
            testCodeBuilder.append(testCode);
        }
        return testCodeBuilder.toString();
    }
}
