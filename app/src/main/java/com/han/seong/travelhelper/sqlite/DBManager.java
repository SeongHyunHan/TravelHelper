package com.han.seong.travelhelper.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager {
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    private static final int DATABASE_VERSION=1;
    //set db name
    private static final String DATABASE_NAME = "TravelHelper.db";
    //set table name
    private static final String TABLE_TRAVEL = "travel";
    private static final String TABLE_PEOPLE = "people";
    private static final String TABLE_COUNTRY = "country";
    private static final String TABLE_EXPENSE = "expense";

    //set travel table column name

    //set people table column name

    //set country table column name

    //set expense table column name

    //ddl statement (CREATE)

    //constructor
    public DBManager(Context context){
        openHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create DB (Inner Class)
    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    //Insert Data

    // Update Data

    // Select Data

    // Delete Data
}
