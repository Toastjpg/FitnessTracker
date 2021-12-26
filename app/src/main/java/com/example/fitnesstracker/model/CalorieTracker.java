package com.example.fitnesstracker.model;

import java.util.ArrayList;

public class CalorieTracker {
    private ArrayList<Entry> entryList;

    public CalorieTracker(){
        this.entryList = new ArrayList<>();
    }

    // Add entry
    public void addEntry(Entry entry){
        entryList.add(entry);
    }

    public Entry getEntry(int index){
        return entryList.get(index);
    }

    public ArrayList<Entry> getEntryList(){
        return entryList;
    }

    public void setEntryList(ArrayList<Entry> entryList){
        this.entryList = entryList;
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

    public int getCalorieCount(){
        int count = 0;
        for(Entry entry : entryList){
            count += entry.getNumCalories();
        }
        return count;
    }
}
