package com.example.fitnesstracker.model;

// Object representing a meal entry in the fitness log
public class Entry {
    private String title;
    private int numCalories;

    public Entry(String name, int numCalories){
        this.title = name;
        this.numCalories = numCalories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getNumCalories() {
        return numCalories;
    }

    public void setNumCalories(int numCalories){
        this.numCalories = numCalories;
    }
}
