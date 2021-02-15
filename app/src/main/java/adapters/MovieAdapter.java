package adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;

import java.util.List;

import models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    // Usually involves inflating a layout from XML (in our case item_movie)and returning it inside the view holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder"); // To give us a better idea of whats happening behind the scenes with recyclerview
        // we create these log statements on these key methods onCreateViewHolder and onBindViewHolder

        // static method from LayoutInflator class which takes in a context. and we inflate the item movie xml
        // this gonna return to us a view
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        // wrap our view inside a view holder
        return new ViewHolder(movieView);
    }


    @Override
    // involves populating the data into the item (view) through (view) holder
    // below contains the position of the data which we will put into the view contained inside the viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position); // To give us a better idea of whats happening behind the scenes with recyclerview
        // we create these log statements on these key methods onCreateViewHolder and onBindViewHolder
        // for onBind we also pass in position for when this method was called

        // Get the movie at the passed in position
        Movie movie = movies.get(position);       
        // Bind the movie data into the view holder
        holder.bind(movie);

    }

    @Override
    // return the total count of items in the list
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvOverview= itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie)
        {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //If phone is in landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // imageUrl= back drop image
                imageUrl= movie.getBackdropPath();
            }
            else{
                // else imageUrl= poster image
                imageUrl= movie.getPosterPath();
            }
           // Glide.with(context).load(movie.getPosterPath()).into(ivPoster);   This was when we only used posterpath for portrait mode
            Glide.with(context).load(imageUrl).into(ivPoster); // Here the imageUrl will change depending if phone in portrait or landscape

        }
    }
}
