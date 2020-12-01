package com.example.poker;

import com.example.general.Generator;
import com.example.general.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class PokerGenerator extends Generator<String[], String[]> {
    public PokerGenerator(
            ArrayList<Test<String[], String[]>> tests, String baseTemplate, HashMap<String, String> testTemplates) {
        super(tests, baseTemplate, testTemplates);
    }

    @Override
    public String getSingleTestCode(Test<String[], String[]> test, String testCode) {
        testCode = testCode.replaceFirst("<name>", "test" + test.getName());
        testCode = getWithFilledInput(test.getInputs(), testCode);
        testCode = testCode.replaceFirst("<value>", test.getOutput()[0]);
        testCode = testCode.replaceFirst("<type>", test.getOutput()[1]);
        return testCode;
    }

    @Override
    protected String getInsertIntoBaseVar() {
        return "#<tests>";
    }

    private String getWithFilledInput(String[] testInputs, String testCode) {
        for (String input : testInputs) {
            String[] cardRepSplit = input.split("/");
            if (cardRepSplit.length != 2) {
                return "ERROR";
            }
            String suit = cardRepSplit[0].trim();
            String rank = cardRepSplit[1].trim();
            testCode = testCode.replaceFirst("<suit>", suit);
            testCode = testCode.replaceFirst("<rank>", rank);
        }
        return testCode;
    }
}
