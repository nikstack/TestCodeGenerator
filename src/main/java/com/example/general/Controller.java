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

    private final static String DEFAULT_JSON_FILENAME =
            "/Users/niklas/IdeaProjects/TestCodeGenerator/src/main/resources/poker-tests.json";

    public Controller() {
        String jsonFileName = loadInput();
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

    private String loadInput() {
        /*String jsonFileName = cli.getJsonFileNameInput();
        if (jsonFileName.equals("")) {
            jsonFileName = "poker-tests.json";
        }
        return jsonFileName;*/
        return DEFAULT_JSON_FILENAME;
    }

    private void showOutput(Message msg) {
        cli.showMessage(msg);
    }

    public abstract Parser<I, O> getParser(String jsonFileName);

    public abstract Generator<I, O> getGenerator(
            ArrayList<Test<I, O>> tests, String baseTemplate, HashMap<String, String> testTemplates);

}
