package com.prasad.poupularmoviesstage1.popularmoviesstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.prasad.poupularmoviesstage1.popularmoviesstage1.data.MoviesData;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class MovieDetailsActivity extends AppCompatActivity {



    TextView movieTitle;
    ImageView poster_icon;//iv
    TextView releaseDate;
    TextView popularity;
    TextView  voteAvarage;
    TextView   markAsFav;
    TextView overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);

        MoviesData moviesData= (MoviesData) getIntent().getSerializableExtra("movieData");

        Logger.log("click data::"+moviesData.getOriginal_title());

        initWidgets();
        setDataToWidgets(moviesData);

    }

    private void initWidgets(){

        movieTitle= (TextView) findViewById(R.id.movieTitle);
        poster_icon= (ImageView) findViewById(R.id.poster_icon);//iv
        releaseDate= (TextView) findViewById(R.id.releaseDate);
        popularity= (TextView) findViewById(R.id.popularity);
        voteAvarage= (TextView) findViewById(R.id.voteAvarage);
        markAsFav= (TextView) findViewById(R.id.markAsFav);


        overview= (TextView) findViewById(R.id.overview);


//        markAsFav.setBackgroundColor(getResources().getColor(R.color.movieTitleBg));
    }
    private void setDataToWidgets(MoviesData moviesData){

        Picasso.with(this).load(Constants.POSTERMAIN_PATH + moviesData.getPoster_path())
                .placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(poster_icon);

        movieTitle.setText(moviesData.getOriginal_title());

        releaseDate.setText(moviesData.getRelease_date());
        popularity.setText(""+moviesData.getPopularity());
        voteAvarage.setText(""+moviesData.getVote_average()+"/10");

        overview.setText( moviesData.getOverview());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
