package com.example.general;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Generator<I, O> {
    private final ArrayList<Test<I, O>> tests;
    private final String baseTemplate;
    private final HashMap<String, String> testTemplates;

    public Generator(ArrayList<Test<I, O>> tests, String baseTemplate, HashMap<String, String> testTemplates) {
        this.tests = tests;
        this.baseTemplate = baseTemplate;
        this.testTemplates = testTemplates;
    }

    public String run() {
        String output = this.baseTemplate;
        String testsCode = getAllTestsCode();
        output = output.replace(getInsertIntoBaseVar(), testsCode);
        return output;
    }

    private String getAllTestsCode() {
        StringBuilder builder = new StringBuilder();
        for (Test<I, O> test : tests) {
            String templateCode = testTemplates.get(test.getTemplate());
            builder.append(getSingleTestCode(test, templateCode));
        }
        return builder.toString();
    }

    public abstract String getSingleTestCode(Test<I, O> test, String templateCode);

    protected abstract String getInsertIntoBaseVar();
}
