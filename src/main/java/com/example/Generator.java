package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Generator {
    private final String jsonContent;
    private final String templateContent;

    public Generator(String jsonContent, String templateContent) {
        this.jsonContent = jsonContent;
        this.templateContent = templateContent;
    }

    public String run() {

        JSONObject jo = new JSONObject(jsonContent);
        JSONArray tests = jo.getJSONArray("tests");

        String classCode = getClassCode(jo.getString("namespace"), jo.getJSONArray("uses"),
                jo.getString("className"));
        String testsCode = getAllTestsCode(tests, templateContent);

        return "<?php\n\n\n" + classCode + testsCode + "\n}";
    }

    private String getClassCode(String namespace, JSONArray uses, String className) {
        StringBuilder classCodeBuilder = new StringBuilder();
        classCodeBuilder.append("namespace ").append(namespace).append(";\n\n\n");
        for (int i = 0; i < uses.length(); i++) {
            String use = uses.getString(i);
            classCodeBuilder.append("use ").append(use).append(";\n");
        }
        classCodeBuilder.append("\n\nclass ").append(className).append(" {\n\n");
        return classCodeBuilder.toString();
    }

    private String getAllTestsCode(final JSONArray tests, final String testTemplate) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tests.length(); i++) {
            JSONObject test = tests.getJSONObject(i);
            builder.append(getSingleTestCode(test, testTemplate));
        }
        return builder.toString();
    }

    private String getSingleTestCode(JSONObject test, String content) {
        content = content.replaceFirst("<name>", "test" + test.get("name").toString());
        content = getWithFilledInput(test.getJSONArray("input"), content);
        content = content.replaceFirst("<output>", test.get("output").toString());
        return content;
    }

    private String getWithFilledInput(JSONArray cards, String content) {
        for (int i = 0; i < cards.length(); i++) {
            String cardRepresentation = cards.get(i).toString();
            String[] cardRepSplit = cardRepresentation.split("/");
            if (cardRepSplit.length != 2) {
                return "ERROR";
            }
            String suit = cardRepSplit[0].trim();
            String rank = cardRepSplit[1].trim();
            content = content.replaceFirst("<suit>", suit);
            content = content.replaceFirst("<rank>", rank);
        }
        return content;
    }
}
