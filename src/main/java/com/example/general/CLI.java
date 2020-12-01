package com.example.general;

import java.util.Scanner;

public class CLI {
    public String getJsonFileNameInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Path to json: [poker-tests.json] ");
        return scanner.nextLine();
    }

    public void showMessage(Controller.Message msg) {
        String output = "ERROR: ";
        switch (msg) {
            case SUCCESS:
                output = "Done";
                break;
            case FILE_NOT_FOUND:
                output += "File Not Found";
            case WRONG_JSON_INPUT:
                output += "Wrong Json Input";
                break;
            default:
                output = "Unknown Error";
        }
        System.out.println(output);
    }
}
