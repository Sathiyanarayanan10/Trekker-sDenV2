package com.example.trekkersdenv2;

public class treklocations {
    private final String name;
    private final String state;

    public treklocations(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public String getName(){
        return name;
    }
    public String getState(){
        return state;
    }
}
