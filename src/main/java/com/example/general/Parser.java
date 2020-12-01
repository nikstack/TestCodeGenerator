package com.example.general;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Parser<I, O> {
    private final static String UNSET = "<UNSET>";

    private String php;
    private String baseTemplate;
    private HashMap<String, String> testTemplates;
    private ArrayList<Test<I, O>> tests;

    private final String json;

    public Parser(String json) {
        this.json = json;
        reset();
    }

    private void reset() {
        php = UNSET;
        baseTemplate = UNSET;
        testTemplates = null;
        tests = null;
    }

    public boolean parse() throws IOException {
        testTemplates = new HashMap<>();
        tests = new ArrayList<>();

        String jsonContent = Files.readString(Path.of(json));
        JSONObject jo = new JSONObject(jsonContent);
        if (!(jo.has("use-config") && jo.has("configs")&& jo.has("tests"))) {
            reset();
            return false;
        }

        int configIndex = jo.getInt("use-config");
        JSONObject config = jo.getJSONArray("configs").getJSONObject(configIndex);

        if (!(config.has("php") && config.has("base-template") && config.has("test-templates"))) {
            reset();
            return false;
        }

        this.php = config.getString("php");
        this.baseTemplate = Files.readString(Path.of(config.getString("base-template")));

        JSONObject joTestTemplates = config.getJSONObject("test-templates");

        for (String key : joTestTemplates.keySet()) {
            String templateContent = Files.readString(Path.of(joTestTemplates.getString(key)));
            this.testTemplates.put(key, templateContent);
        }

        JSONArray joTests = jo.getJSONArray("tests");
        for (int i = 0; i < joTests.length(); i++) {
            JSONObject joTest = joTests.getJSONObject(i);
            if (!(joTest.has("name") && joTest.has("input") && joTest.has("output"))) {
                reset();
                return false;
            }

            String name = joTest.getString("name");
            I inputs = getInputs(joTest.getJSONArray("input"));
            O outputs = getOutputs(joTest.getJSONArray("output"));

            String template = "default";
            if (joTest.has("template")) {
                template = joTest.getString("template");
            }
            tests.add(new Test<>(name, inputs, outputs, template));
        }
        return true;
    }

    protected abstract I getInputs(JSONArray joInputs);

    protected abstract O getOutputs(JSONArray joOutputs);

    public String getPhp() {
        if (php.equals(UNSET)) {
            throw new IllegalStateException();
        }
        return php;
    }

    public String getBaseTemplate() {
        if (baseTemplate.equals(UNSET)) {
            throw new IllegalStateException();
        }
        return baseTemplate;
    }

    public HashMap<String, String> getTestTemplates() {
        if (testTemplates == null) {
            throw new IllegalStateException();
        }
        return new HashMap<>(testTemplates);
    }

    public ArrayList<Test<I, O>> getTests() {
        if (tests == null) {
            throw new IllegalStateException();
        }
        return new ArrayList<>(this.tests);
    }
}
