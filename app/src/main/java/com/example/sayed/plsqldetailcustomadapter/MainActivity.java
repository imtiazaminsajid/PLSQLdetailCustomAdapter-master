package com.example.sayed.plsqldetailcustomadapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText movieNameET, moveYearET;
    private MovieDatabaseSource movieDatabaseSource;
    private Movie movie;
    //for movie Edit
    private String movieNameED, movieYearED;
    private int rowIdED;
    private Button movieAddBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieNameET= (EditText) findViewById(R.id.movieNameET);
        moveYearET = (EditText) findViewById(R.id.movieYearET);
        movieDatabaseSource = new MovieDatabaseSource(this);

        //for Edit Movie
        movieAddBT= (Button) findViewById(R.id.addMovieBT);
        rowIdED=getIntent().getIntExtra("id", 0);
        if(rowIdED>0){
            movieNameED = getIntent().getStringExtra("name");
            movieYearED = getIntent().getStringExtra("year");

            movieNameET.setText(movieNameED);
            moveYearET.setText(movieYearED);
            movieAddBT.setText("Update");
        }
    }

    public void addMovie(View view) {
        String name = movieNameET.getText().toString();
        String year = moveYearET.getText().toString();
        if(name.isEmpty()){
            movieNameET.setError("This find must not be Empty");
        }else if(year.isEmpty()){
            moveYearET.setError("This find must not be Empty");
        }else {
            if (rowIdED>0){
                movie = new Movie(rowIdED, name, year, R.mipmap.ic_launcher_round);
                boolean status = movieDatabaseSource.updateMovie(movie, rowIdED);
                if (status){
                    startActivity(new Intent(MainActivity.this, MovieListActiveity.class));
                }else {
                    Toast.makeText(this, "Faild to Update", Toast.LENGTH_SHORT).show();
                }
            }else {
                movie = new Movie(name, year);
                boolean status = movieDatabaseSource.addMovie(movie);
                if(status){
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, MovieListActiveity.class));
                }else
                    Toast.makeText(this, "Could not Save", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void movieList(View view) {
        startActivity(new Intent(MainActivity.this, MovieListActiveity.class));
    }
}
