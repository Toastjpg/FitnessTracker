package com.example.fitnesstracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fitnesstracker.model.Entry;

import java.util.ArrayList;

public class CalorieDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "calorie.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "CALORIE_TRACKER_TABLE";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_COUNT = "CALORIE_COUNT";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_COUNT + " INT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public CalorieDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public boolean addToDatabase(Entry entry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, entry.getTitle());
        cv.put(COLUMN_COUNT, entry.getNumCalories());

        long newRowId = db.insert(TABLE_NAME, null, cv);

        db.close();
        return newRowId != -1;
    }

    public boolean deleteFromDatabase(int position){
        String queryString = "ID in (SELECT ID FROM " + TABLE_NAME + " LIMIT 1 OFFSET " + position + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        int numDeleted = db.delete(TABLE_NAME, queryString, null);
        System.out.println(numDeleted);
        db.close();
        // Expect to delete single row at position offset
        return numDeleted == 1;
    }

    public void clearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public ArrayList<Entry> getFromDatabase(){
        ArrayList<Entry> entryArrayList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                String title = cursor.getString(1);
                int count = cursor.getInt(2);

                Entry entry = new Entry(title, count);
                entryArrayList.add(entry);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return entryArrayList;
    }
}
