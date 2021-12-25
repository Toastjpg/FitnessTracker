package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.fitnesstracker.model.Counter;

public class MainActivity extends AppCompatActivity {
    private Counter counter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.counter_text);
        counter = new Counter(3000);
        updateCounterText();
    }

    public void updateCounterText(){
        int count = counter.getCount();
        textView.setText(String.valueOf(count));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_calorie_counter, menu);
        return true;
    }

    @SuppressLint("InflateParams")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_calorie_entry){
            new AlertDialog.Builder(this)
                    .setTitle("Add a new entry")
                    .setView(getLayoutInflater().inflate(R.layout.layout_calorie_counter_dialog, null))
                    .setPositiveButton("OK", (dialog, i) -> {
                        // validate edit text inputs
                        // send inputs to model class here
                        dialog.dismiss();
                    })
                    .setNegativeButton("CANCEL", (dialog, i) -> {
                        dialog.cancel();
                    })
                    .show();

            return true;
        }
        else if (id == R.id.calorie_history){
            Toast.makeText(this, "CLICKED ON HISTORY", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("InflateParams")
    private void createDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a new entry");
        builder.setView(getLayoutInflater().inflate(R.layout.layout_calorie_counter_dialog, null));

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            // handle ok click
        });
        builder.setNegativeButton("CANCEL", (dialogInterface, i) -> {
            // handle cancel
        });

        AlertDialog dialog = builder.create();
    }
}