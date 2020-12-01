package com.example.poker;

import com.example.general.Parser;
import org.json.JSONArray;

import java.util.ArrayList;

public class PokerParser extends Parser<String[], String[]> {

    public PokerParser(String json) {
        super(json);
    }

    @Override
    protected String[] getInputs(JSONArray joInputs) {
        ArrayList<String> inputs = new ArrayList<>();
        for (int i = 0; i < joInputs.length(); i++) {
            String input = joInputs.getString(i);
            inputs.add(input);
        }
        return inputs.toArray(new String[0]);
    }

    @Override
    protected String[] getOutputs(JSONArray joOutputs) {
        if (joOutputs.length() != 2) {
            throw new RuntimeException();
        }
        return new String[]{joOutputs.getString(0), joOutputs.getString(1)};
    }
}
