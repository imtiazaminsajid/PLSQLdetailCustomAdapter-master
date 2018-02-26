package com.example.sayed.plsqldetailcustomadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

/**
 * Created by nurud on 9/27/2017.
 */

public class MovieDatabaseSource {
    private MovieDatabaseHelper movieDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MovieDatabaseSource(Context context) {
        movieDatabaseHelper = new MovieDatabaseHelper(context);
    }

    public void open(){
        sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }
    public boolean addMovie(Movie movie){
        this.open();
        ContentValues values = new ContentValues();
        values.put(movieDatabaseHelper.COL_NAME, movie.getMovieName());
        values.put(movieDatabaseHelper.COL_YEAR, movie.getMovieYear());
        long row_id = sqLiteDatabase.insert(movieDatabaseHelper.TABLE_MOVIE, null, values);
        this.close();
        if (row_id> 0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        this.open();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+MovieDatabaseHelper.TABLE_MOVIE, null);
        Cursor cursor = sqLiteDatabase.query(movieDatabaseHelper.TABLE_MOVIE, null,
                null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {

                int id = cursor.getInt(cursor.getColumnIndex(MovieDatabaseHelper.COL_ID));
                String movieName= cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COL_NAME));
                String movieYear = cursor.getString(cursor.getColumnIndex(MovieDatabaseHelper.COL_YEAR));
                Movie movie = new Movie(id, movieName, movieYear, R.mipmap.ic_launcher_round);
                movies.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return movies;
    }

    public boolean updateMovie(Movie movie, int rowId){
        this.open();
        ContentValues values = new ContentValues();
        values.put(MovieDatabaseHelper.COL_ID, movie.getMovieID());
        values.put(MovieDatabaseHelper.COL_NAME, movie.getMovieName());
        values.put(MovieDatabaseHelper.COL_YEAR, movie.getMovieYear());
        int updated = sqLiteDatabase.update(MovieDatabaseHelper.TABLE_MOVIE, values,
                MovieDatabaseHelper.COL_ID+" = ?",new String[]{Integer.toString(rowId)});

        if (updated>0){
            return true;
        }else {
            return false;
        }
    }


    public boolean deleteMovie(int rowId){
         this.open();
        int deleted = sqLiteDatabase.delete(MovieDatabaseHelper.TABLE_MOVIE, MovieDatabaseHelper.COL_ID+" = ?", new String[] {Integer.toString(rowId)});
        this.close();
        if (deleted>0){
            return true;
        }else{
            return false;
        }
    }
}
