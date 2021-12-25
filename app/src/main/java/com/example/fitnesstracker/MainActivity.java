package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.fitnesstracker.model.CalorieTracker;
import com.example.fitnesstracker.model.Counter;

public class MainActivity extends AppCompatActivity {
    private Counter counter;
    private TextView textView;
    private CalorieTracker calorieTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);

        calorieTracker = new CalorieTracker();

//        textView = findViewById(R.id.counter_text);
//        counter = new Counter(3000);
//        updateCounterText();
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
            View view = getLayoutInflater().inflate(R.layout.layout_calorie_counter_dialog, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add a new entry")
                    .setView(view)
                    .setPositiveButton("OK", (dialog, i) -> {
                        EditText nameEditText = view.findViewById(R.id.dialogEditTextTitle);
                        EditText countEditText = view.findViewById(R.id.dialogEditTextCount);

                        // validate edit text inputs
                        if (validateTextInputs(nameEditText, countEditText)){
                            Toast.makeText(this, "INPUT SUCCESS", Toast.LENGTH_SHORT).show();

//                            calorieTracker.addEntry(
//                                    nameEditText.getText().toString(),
//                                    Integer.parseInt(countEditText.getText().toString())
//                            );
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("CANCEL", (dialog, i) -> {
                        dialog.cancel();
                    });

            builder.show();
            return true;
        }
        else if (id == R.id.calorie_history){
            Toast.makeText(this, "CLICKED ON HISTORY", Toast.LENGTH_SHORT).show();
            // Handle history activity launch here
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    private boolean validateTextInputs(EditText nameEditText, EditText countEditText) {
        return !TextUtils.isEmpty(nameEditText.getText().toString()) && !TextUtils.isEmpty(countEditText.getText().toString());
    }
}