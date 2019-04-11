package com.example.moviehitapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviehitapp.annotations.Adapter;
import com.example.moviehitapp.constants.ImageUrl;
import com.example.moviehitapp.R;
import com.example.moviehitapp.business_logic.data.Movie;
import com.example.moviehitapp.constants.SortOrder;
import com.example.moviehitapp.ui.activities.DetailActivity;
import com.example.moviehitapp.utils.MoviesSorter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
@Adapter
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private final List<Movie> movieList = new ArrayList<>();
    private Context context;

    public MoviesAdapter(Context context) {
        this.context = context;
        setHasStableIds(true);
    }

    public void updateData(List<Movie> updatedMovieList) {
        movieList.clear();
        if (updatedMovieList != null) {
            movieList.addAll(MoviesSorter.sort(updatedMovieList , SortOrder.BY_MOVIES_ORIGINAL_TITLE));
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i){
        viewHolder.bind(movieList.get(i));
    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //bindView annotation will automatically identify the particular views
        @BindView(R.id.title) TextView title;
        @BindView(R.id.userrating) TextView user_rating;
        @BindView(R.id.thumbnail) ImageView thumbnail;
        //global var
        private Movie movie;

        private MyViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener((v) -> {if (movie != null) {
                final int i = getAdapterPosition();
                if (i != RecyclerView.NO_POSITION) {
                    final Movie clickedDataItem = movieList.get(i);
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(context.getString(R.string.movie), clickedDataItem);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(context , context.getString(R.string.you_clicked) + " " + clickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                }
            }});
        }

        private void bind(@NonNull Movie movie) {
            this.movie = movie;
            title.setText(movie.getOriginalTitle());
            final String vote = Double.toString(movie.getVoteAverage());
            user_rating.setText(vote);
            final String poster_url = ImageUrl.w185 + movie.getPosterPath();
            //here we use Glide library for visualizing in gallery style
            Glide.with(context)
                    .load(poster_url)
                    .placeholder(R.drawable.load)
                    .into(thumbnail);
        }
    }
}
