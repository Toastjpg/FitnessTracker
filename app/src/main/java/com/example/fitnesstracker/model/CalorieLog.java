package com.example.fitnesstracker.model;

import java.util.ArrayList;

public class CalorieLog {
    private final ArrayList<CalorieEntry> calorieEntryList;

    public CalorieLog(){
        this.calorieEntryList = new ArrayList<>();
    }

    // Add entry
    public void addEntry(String name, int numCalories){
        CalorieEntry newCalorieEntry = new CalorieEntry(name, numCalories);
        calorieEntryList.add(newCalorieEntry);
    }

    public CalorieEntry getEntry(int index){
        return calorieEntryList.get(index);
    }

    // Remove entry from index
    public boolean removeEntry(int index){
        if (index > 0 && index <= calorieEntryList.size()){
            calorieEntryList.remove(index);
            return true;
        }
        return false;
    }

    // Clear all
    public void clearEntryList(){
        calorieEntryList.clear();
    }
}
