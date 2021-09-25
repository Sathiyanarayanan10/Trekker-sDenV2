package com.example.trekkersdenv2;

public class treklocations {
    private final String name;
    private final String state;
    private final String location;

    public treklocations(String name, String state,String location) {
        this.name = name;
        this.state = state;
        this.location = location;
    }

    public String getName(){
        return name;
    }
    public String getState(){
        return state;
    }

    public String getLocation() {return location;}
}
