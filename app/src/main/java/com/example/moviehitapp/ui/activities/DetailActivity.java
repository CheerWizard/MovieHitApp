package com.example.moviehitapp.ui.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviehitapp.R;
import com.example.moviehitapp.business_logic.data.Movie;
import com.example.moviehitapp.constants.ImageUrl;

import java.util.Objects;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {
    @BindView(R.id.title) TextView nameOfMovie;
    @BindView(R.id.plotsynopsis) TextView plotSynopsis;
    @BindView(R.id.userrating) TextView userRating;
    @BindView(R.id.releasedate) TextView releaseDate;
    @BindView(R.id.thumbnail_image_header) ImageView imageView;

    @Override
    protected int layoutRes() {
        return R.layout.activity_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra(getString(R.string.movie))) {
            //get data from intent
            final Movie movie = getIntent().getParcelableExtra(getString(R.string.movie));
            final String poster = ImageUrl.w500 + movie.getPosterPath();
            //use Glide library here
            Glide.with(this)
                    .load(poster)
                    .placeholder(R.drawable.load)
                    .into(imageView);
            //update visual components
            nameOfMovie.append("\n" + movie.getOriginalTitle());
            plotSynopsis.append("\n" + movie.getOverview());
            userRating.append(" " + String.valueOf(movie.getVoteAverage()));
            releaseDate.append(" " + movie.getReleaseDate());
        }
        else Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
    }
}
