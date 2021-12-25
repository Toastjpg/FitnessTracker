package com.example.fitnesstracker.model;

// Simple counter class that increments and decrements an integer
public class Counter {
    private int count;

    public Counter(int calorieCount) {
        this.count = calorieCount;
    }

    public void decrement(int val){
        if (count >= val){
            count -= val;
        }
    }

    public void increment(int val){
        count += val;
    }

    public int getCount(){
        return count;
    }
}
