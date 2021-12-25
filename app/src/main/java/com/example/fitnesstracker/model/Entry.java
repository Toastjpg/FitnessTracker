package com.example.fitnesstracker.model;

// Object representing a meal entry in the fitness log
public class Entry {
    private String name;
    private int numCalories;

    public Entry(String name, int numCalories){
        this.name = name;
        this.numCalories = numCalories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getNumCalories() {
        return numCalories;
    }

    public void setNumCalories(int numCalories){
        this.numCalories = numCalories;
    }
}
