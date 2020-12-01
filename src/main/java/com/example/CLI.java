package com.example;

import com.example.InputData;

import java.util.Scanner;

public class CLI {
    public InputData getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Path to json: [poker-tests.json] ");
        String json = scanner.nextLine();
        System.out.print("Path to php: [EvaluatorTest.php] ");
        String dest = scanner.nextLine();
        System.out.print("Path to template: [poker-tests-template.txt] ");
        String template = scanner.nextLine();

        return new InputData() {
            @Override
            public String getJsonFileName() {
                return json;
            }

            @Override
            public String getTemplateFileName() {
                return dest;
            }

            @Override
            public String getPhpFileName() {
                return template;
            }
        };
    }
}
