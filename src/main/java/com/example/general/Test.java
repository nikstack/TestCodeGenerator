package com.example.general;

public class Test<I, O> {
    private final String name;
    private final I inputs;
    private final O output;
    private final String template;

    public Test(String name, I inputs, O output, String template) {

        this.name = name;
        this.inputs = inputs;
        this.output = output;
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public I getInputs() {
        return this.inputs;
    }

    public O getOutput() {
        return output;
    }

    public String getTemplate() {
        return template;
    }
}
