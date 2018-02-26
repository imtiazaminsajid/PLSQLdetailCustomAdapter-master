package com.example.sayed.plsqldetailcustomadapter;

import java.util.ArrayList;

/**
 * Created by nurud on 9/27/2017.
 */

public class Movie {
    private int movieID;
    private String movieName;
    private String movieYear;
    private int movieImg;


    public Movie(int movieID, String movieName, String movieYear, int movieImg) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieYear = movieYear;
        this.movieImg = movieImg;
    }

    public Movie() {
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public Movie(String movieName, String movieYear) {
        this.movieName = movieName;
        this.movieYear = movieYear;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public int getMovieImg() {
        return movieImg;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public void setMovieImg(int movieImg) {
        this.movieImg = movieImg;
    }

}
