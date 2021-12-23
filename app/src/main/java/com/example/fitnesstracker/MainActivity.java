package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnesstracker.model.Counter;

public class MainActivity extends AppCompatActivity {
    private Counter counter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.counter_text);
        counter = new Counter(0);
        Button increment = findViewById(R.id.button_increment);
        Button decrement = findViewById(R.id.button_decrement);
        updateTextView();

        increment.setOnClickListener(view -> {
            counter.increment(1);
            updateTextView();
        });

        decrement.setOnClickListener(view -> {
            counter.decrement(1);
            updateTextView();
        });
    }

    public void updateTextView(){
        int count = counter.getCalorieCount();
        textView.setText(String.valueOf(count));
    }
}