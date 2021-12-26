package com.example.fitnesstracker.model;

import java.util.ArrayList;

public class CalorieTracker {
    private final ArrayList<Entry> entryList;

    public CalorieTracker(){
        this.entryList = new ArrayList<>();
    }

    // Add entry
    public void addEntry(String name, int numCalories){
        Entry newEntry = new Entry(name, numCalories);
        entryList.add(newEntry);
    }

    public Entry getEntry(int index){
        return entryList.get(index);
    }

    public ArrayList<Entry> getEntryList(){
        return entryList;
    }

    // Remove entry from index
    public boolean removeEntry(int index){
        if (index > 0 && index <= entryList.size()){
            entryList.remove(index);
            return true;
        }
        return false;
    }

    // Clear all
    public void clearEntryList(){
        entryList.clear();
    }
}
