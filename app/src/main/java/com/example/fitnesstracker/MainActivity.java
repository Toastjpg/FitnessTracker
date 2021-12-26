package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
        Toolbar toolbar = findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);

        calorieTracker = new CalorieTracker();

//        textView = findViewById(R.id.counter_text);
//        counter = new Counter(3000);
//        updateCounterText();
        populateListView();
    }

    private void populateListView() {
        CalorieListAdapter listAdapter = new CalorieListAdapter(this, R.layout.layout_list_view_adapter, calorieTracker.getEntryList());
        ListView listView = findViewById(R.id.calorieListVIew);
        listView.setAdapter(listAdapter);
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
            handleNewEntryDialogue();
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

    private void handleNewEntryDialogue() {
        View dialogView = getLayoutInflater().inflate(R.layout.layout_calorie_counter_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a new entry")
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .setNegativeButton("CANCEL", null);

        // Bypass default dialog button dismissal, exit only on cancel or valid input
        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(dialogInterface -> {
            Button ok = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            ok.setOnClickListener(view ->{
                EditText nameEditText = dialogView.findViewById(R.id.dialogEditTextTitle);
                EditText countEditText = dialogView.findViewById(R.id.dialogEditTextCount);

                // validate edit text inputs
                if (validateTextInputs(nameEditText, countEditText)){
                    Toast.makeText(MainActivity.this, "INPUT SUCCESS", Toast.LENGTH_SHORT).show();
                    calorieTracker.addEntry(
                            nameEditText.getText().toString(),
                            Integer.parseInt(countEditText.getText().toString())
                    );
                    populateListView();
                    alertDialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this, "INVALID INPUT", Toast.LENGTH_SHORT).show();
                }
            });
            Button cancel = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            cancel.setOnClickListener(view ->{
                Toast.makeText(MainActivity.this, "DIALOG CANCELED", Toast.LENGTH_SHORT).show();
                alertDialog.cancel();
            });
        });
        alertDialog.show();
    }

    private boolean validateTextInputs(EditText nameEditText, EditText countEditText) {
        return !TextUtils.isEmpty(nameEditText.getText().toString()) && !TextUtils.isEmpty(countEditText.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateListView();
    }
}

