package com.example.sayed.plsqldetailcustomadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie>{

    private ArrayList<Movie> movies;
    private Context context;

    public MovieAdapter(@NonNull Context context, ArrayList<Movie>movies) {
        super(context, R.layout.raw_layout, movies);
        this.context=context;
        this.movies=movies;
    }

    public class ViewHolder{
        public TextView movieName, movieYear;
        public ImageView movieImg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;
        if (convertView == null){

            viewHolder = new ViewHolder();
            convertView= inflater.inflate(R.layout.raw_layout, parent, false);
            viewHolder.movieName = convertView.findViewById(R.id.movieNameID);
            viewHolder.movieYear = convertView.findViewById(R.id.movieYearID);
            viewHolder.movieImg = convertView.findViewById(R.id.movieImgID);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.movieName.setText(movies.get(position).getMovieName());
        viewHolder.movieYear.setText(movies.get(position).getMovieYear());
        viewHolder.movieImg.setImageResource(movies.get(position).getMovieImg());


        return convertView;
    }
}