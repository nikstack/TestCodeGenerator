package com.example.poker;

import com.example.general.Controller;
import com.example.general.Generator;
import com.example.general.Parser;
import com.example.general.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class PokerController extends Controller<String[], String[]> {
    public static void main(String[] args) {
        new PokerController();
    }


    @Override
    public Parser<String[], String[]> getParser(String jsonFileName) {
        return new PokerParser(jsonFileName);
    }

    @Override
    public Generator<String[], String[]> getGenerator(
            ArrayList<Test<String[], String[]>> tests, String baseTemplate, HashMap<String, String> testTemplates) {
        return new PokerGenerator(tests, baseTemplate, testTemplates);
    }
}
