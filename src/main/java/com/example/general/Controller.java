package com.example.general;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Controller<I, O> {
    CLI cli = new CLI();

    public enum Message {
        SUCCESS, FILE_NOT_FOUND, WRONG_JSON_INPUT
    }

    public Controller() {
        String jsonFileName = getJsonFileName();
        Parser<I, O> parser = getParser(jsonFileName);
        try {
            if (!parser.parse()) {
                showOutput(Message.WRONG_JSON_INPUT);
            }
        } catch (IOException e) {
            showOutput(Message.FILE_NOT_FOUND);
        }

        Generator<I, O> generator = getGenerator(parser.getTests(), parser.getBaseTemplate(), parser.getTestTemplates());
        String phpContent = generator.run();

        try {
            Files.write(Path.of(parser.getPhp()), phpContent.getBytes());
        } catch (IOException e) {
            showOutput(Message.FILE_NOT_FOUND);
        }
        showOutput(Message.SUCCESS);
    }

    private String getJsonFileName() {
        String jsonFileName = getDefaultJsonFilename();
        if (!jsonFileName.equals("")) {
            return jsonFileName;
        }
        jsonFileName = cli.getJsonFileNameInput();
        if (jsonFileName.equals("")) {
            jsonFileName = "_tests.json";
        }
        return jsonFileName;
    }

    private void showOutput(Message msg) {
        cli.showMessage(msg);
    }

    protected String getDefaultJsonFilename() {
        return "";
    }

    protected abstract Parser<I, O> getParser(String jsonFileName);

    protected abstract Generator<I, O> getGenerator(
            ArrayList<Test<I, O>> tests, String baseTemplate, HashMap<String, String> testTemplates);

}
