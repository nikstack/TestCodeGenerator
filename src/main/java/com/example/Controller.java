package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Controller {
    private String jsonFileName;
    private String templateFileName;
    private String phpFileName;

    private final static String DEFAULT_JSON_FILENAME =
            "/Users/niklas/IdeaProjects/TestCodeGenerator/src/main/resources/poker-tests.json";
    private final static String DEFAULT_TEMPLATE_FILENAME =
            "/Users/niklas/IdeaProjects/TestCodeGenerator/src/main/resources/poker-tests-template.txt";
    private final static String DEFAULT_PHP_FILENAME =
            "/Users/niklas/IdeaProjects/TestCodeGenerator/src/main/resources/poker-tests.php";

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
//        controller.loadInput();

        controller.jsonFileName = DEFAULT_JSON_FILENAME;
        controller.templateFileName = DEFAULT_TEMPLATE_FILENAME;
        controller.phpFileName = DEFAULT_PHP_FILENAME;

        String jsonContent = Files.readString(Path.of(controller.jsonFileName));
        String templateContent = Files.readString(Path.of(controller.templateFileName));

        Generator generator = new Generator(jsonContent, templateContent);
        String phpContent = generator.run();

        Files.write(Path.of(controller.phpFileName), phpContent.getBytes());
    }

    private void loadInput() {
        InputData inputData = new CLI().getInput();
        jsonFileName = inputData.getJsonFileName();
        templateFileName = inputData.getTemplateFileName();
        phpFileName = inputData.getPhpFileName();

        if (jsonFileName.equals("")) {
            jsonFileName = "poker-tests.jsonFileName";
        }
        if (phpFileName.equals("")) {
            phpFileName = "EvaluatorTest.php";
        }
        if (templateFileName.equals("")) {
            templateFileName = "poker-tests-templateFileName.txt";
        }
    }
}
