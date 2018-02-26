package com.example.sayed.plsqldetailcustomadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieListActiveity extends AppCompatActivity {
    private ListView movieListView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;
    private MovieDatabaseSource movieDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_activeity);

        movieListView = (ListView) findViewById(R.id.movieListLVid );
        movieDatabaseSource = new MovieDatabaseSource(this);
        movies = movieDatabaseSource.getAllMovies();

        movieAdapter = new MovieAdapter(this, movies);
        movieListView.setAdapter(movieAdapter);
        
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String movieName = movies.get(i).getMovieName();
                String movieYear = movies.get(i).getMovieYear();
                int rowId = movies.get(i).getMovieID();
                int movieImage = movies.get(i).getMovieImg();
                startActivity(new Intent(MovieListActiveity.this, MovieDetailsActivity.class).putExtra("name", movieName)
                            .putExtra("year", movieYear).putExtra("movieImg", movieImage).putExtra("rowID",rowId));

            }
        });
    }
}
