package com.example.sayed.plsqldetailcustomadapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nurud on 9/27/2017.
 */

public class MovieDatabaseHelper extends SQLiteOpenHelper{

    //Database Main
    public static final String DATABASE_NAME="Movie Databse";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_MOVIE="tbl_movie";
    public static final String COL_ID="tbl_id";
    public static final String COL_YEAR="tbl_year";
    public static final String COL_NAME="tbl_name";
    //Create Table
    public static final String CREATE_MOVIE_TABLE="create table "+TABLE_MOVIE+"("+COL_ID+
            " integer primary key autoincrement, "+COL_NAME+" text, "+COL_YEAR+" text);";


    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist "+TABLE_MOVIE);
        onCreate(sqLiteDatabase);
    }
}
