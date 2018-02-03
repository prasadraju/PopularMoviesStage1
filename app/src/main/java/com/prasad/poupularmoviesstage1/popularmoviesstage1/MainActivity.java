package com.prasad.poupularmoviesstage1.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.prasad.poupularmoviesstage1.popularmoviesstage1.data.MoviesData;
import com.prasad.poupularmoviesstage1.popularmoviesstage1.data.MoviesDataParsing;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,WebserviceCallback{


    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.movies_grid);

        gridView.setOnItemClickListener(this);

      //calling themoviedb webservice.
        callWebservice(Constants.MOVIES_POPULAR);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.toprated) {

            getSupportActionBar().setTitle("TopRated Movies");
            callWebservice(Constants.MOVIES_TOPRATED);



        }else if(id==R.id.popularity){
            getSupportActionBar().setTitle("Pop Movies");

            callWebservice(Constants.MOVIES_POPULAR);

        }

        return super.onOptionsItemSelected(item);
    }

    private  void callWebservice(String moviesType){

        if(ConnectionDetector.isConnected(this)){
            PostTask postTask=new PostTask(this,this);
            postTask.execute(Constants.THEMOVIEDB_URL+moviesType+Constants.API_KEY);
        }else{
            Toast.makeText(this, Constants.NO_INTERNET, Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       MoviesData data= (MoviesData) parent.getItemAtPosition(position);

        Logger.log("click data::"+data.getOriginal_title());

        Intent intent=new Intent(this,MovieDetailsActivity.class);
        intent.putExtra("movieData",  data);

        startActivity(intent);


    }


    @Override
    public void postResult(String postResult) {

        Logger.log("post result:::" + postResult);


        MoviesDataParsing moviesDataParsing=new MoviesDataParsing();
        moviesDataParsing.setParsingresult(postResult);
        MoviesDataAdapter adapter=new MoviesDataAdapter(this,moviesDataParsing.getMoviesDataArrayList());
        gridView.setAdapter(adapter);


    }


}
