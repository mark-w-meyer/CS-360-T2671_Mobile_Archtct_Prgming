package com.zybooks.mark_meyer_cs360_t2671_event_tracker_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    // user database columns
    public static final String TABLE_NAME = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PW = "USER_PW";
    public static final String COLUMN_USER_STATE = "USER_STATE";
    public static final String COLUMN_ID = "ID";

    // event database columns
    public static final String EVENT_TABLE = "EVENT_TABLE";
    public static final String COLUMN_E_ID = "E_ID";
    public static final String COLUMN_E_POS = "E_POS";
    public static final String COLUMN_E_NAME = "E_NAME";
    public static final String COLUMN_E_DATE = "E_DATE";
    public static final String COLUMN_E_TIME = "E_TIME";
    public static final String COLUMN_E_NOTES = "E_NOTES";

    public Database(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    // called the first time a database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME + " " +
                                         "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                             + COLUMN_E_POS + " INTEGER, "
                                             + COLUMN_USER_NAME + " TEXT, "
                                             + COLUMN_USER_PW + " TEXT, "
                                             + COLUMN_USER_STATE + " BOOL, "
                                             + COLUMN_E_ID + " INTEGER )";

        String createEventTable = "CREATE TABLE " + EVENT_TABLE + " " +
                                              "(" + COLUMN_E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                  + COLUMN_E_NAME + " TEXT, "
                                                  + COLUMN_E_DATE + " TEXT, "
                                                  + COLUMN_E_TIME + " TEXT, "
                                                  + COLUMN_E_NOTES + " TEXT )";

        db.execSQL(createUserTable);
        db.execSQL(createEventTable);
    }

    // called if database version number changes, prevents user apps from breaking when database design changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    /**----------------------BEGIN USER OPERATIONS-----------------------*/
    public boolean addUser(UserCredentials credentials) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, credentials.getUsername());
        cv.put(COLUMN_USER_PW, credentials.getPassword());
        cv.put(COLUMN_USER_STATE, credentials.isActive());

        Log.d("sql app", "adding data " + credentials.toString() + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteUser(UserCredentials credentials) {
        // find user in database. if found, delete user and return true
        // if user not found, return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + credentials.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<UserCredentials> getAllUsers() {

        List<UserCredentials> returnList = new ArrayList<>();

        // get data from database
        String queryString = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through cursor (result set) and create new user objects,
            // put them in return list
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userPW = cursor.getString(2);
                boolean userActive = cursor.getInt(3) == 1 ? true: false;

                UserCredentials credentials = new UserCredentials(userID, userName, userPW, userActive);
                returnList.add(credentials);

            } while(cursor.moveToNext());

        } else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnList;
    }
    /**----------------------END USER OPERATIONS-----------------------*/

    /**----------------------BEGIN EVENT OPERATIONS-----------------------*/

    public boolean addEvent(EventManager eventManager) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_E_POS, eventManager.getE_position());
        cv.put(COLUMN_E_NAME, eventManager.getE_name());
        cv.put(COLUMN_E_DATE, eventManager.getE_date());
        cv.put(COLUMN_E_TIME, eventManager.getE_time());
        cv.put(COLUMN_E_NOTES, eventManager.getE_notes());

        Log.d("sql app", "adding data " + eventManager.toString() + " to " + EVENT_TABLE);

        long result = db.insert(EVENT_TABLE, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteEvent(EventManager eventManager) {
        // find event in database. if found, delete event and return true
        // if event not found, return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + EVENT_TABLE + " WHERE " + COLUMN_E_ID + " = " + eventManager.getE_Id();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<EventManager> getAllUserEvents() {

        List<EventManager> returnEventList = new ArrayList<>();

        // get data from database
        String queryString = "SELECT * FROM " + EVENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through cursor (result set) and create new user objects,
            // put them in return list
            do {
                int e_position   = cursor.getInt(0);
                String e_name    = cursor.getString(1);
                String e_date    = cursor.getString(2);
                String e_time    = cursor.getString(3);
                String e_notes   = cursor.getString(4);

                EventManager event_mgr = new EventManager(e_position, e_name, e_date, e_time, e_notes);
                returnEventList.add(event_mgr);

            } while(cursor.moveToNext());

        } else {
            // failure. do not add anything to the list.
        }

        cursor.close();
        db.close();
        return returnEventList;
    }
}
