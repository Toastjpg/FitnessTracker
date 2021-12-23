package com.example.fitnesstracker.model;

public class Counter {
    int calorieCount;

    public Counter(int calorieCount) {
        this.calorieCount = calorieCount;
    }

    public void decrement(int val){
        if (calorieCount >= val){
            calorieCount -= val;
        }
    }

    public void increment(int val){
        calorieCount += val;
    }

    public int getCalorieCount(){
        return calorieCount;
    }
}
