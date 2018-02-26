package com.example.sayed.plsqldetailcustomadapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MovieDetailsActivity extends AppCompatActivity {

    private String movieName, movieYear;
    private int movieImage, rowID;
    private TextView nameTV, yearTV;
    private ImageView movieImg;
    private MovieDatabaseSource movieDatabaseSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        nameTV = (TextView) findViewById(R.id.movieNameDetailET);
        yearTV = (TextView) findViewById(R.id.movieYearDetailET);
        movieImg = (ImageView) findViewById(R.id.movieDetailImgID);

        movieDatabaseSource =new MovieDatabaseSource(this);
        movieName = getIntent().getStringExtra("name");
        movieYear = getIntent().getStringExtra("year");
        movieImage = getIntent().getIntExtra("movieImage",R.mipmap.ic_launcher);
        rowID = getIntent().getIntExtra("rowID", 0);

        nameTV.setText(movieName);
        yearTV.setText(movieYear);
        movieImg.setImageResource(movieImage);
    }

    public void editMovie(View view) {
        startActivity(new Intent(this, MainActivity.class).putExtra("id", rowID)
        .putExtra("name", movieName).putExtra("year", movieYear));
    }

    public void deleteMovie(View view) {
        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("Delete Item");
        alert.setMessage("Are you sure to delete this item?");
        alert.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean status = movieDatabaseSource.deleteMovie(rowID);
                if (status) {
                    Toast.makeText(MovieDetailsActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MovieDetailsActivity.this, MovieListActiveity.class));
                }else {
                    Toast.makeText(MovieDetailsActivity.this, "Could Not Delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Cancel", null);
        alert.show();


    }
}
