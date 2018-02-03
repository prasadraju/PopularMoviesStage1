package com.prasad.poupularmoviesstage1.popularmoviesstage1.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kishorevarma on 18/03/16.
 */
public class MoviesDataParsing {





    ArrayList<MoviesData> moviesDataArrayList;

       public MoviesDataParsing() {


           moviesDataArrayList=new ArrayList<MoviesData>();
       }

    public void setParsingresult(String result){


        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray= jsonObject.getJSONArray("results");

            for (int index=0;index<jsonArray.length();index++){

                JSONObject movieJsonObject=jsonArray.getJSONObject(index);
                MoviesData moviesData=new MoviesData();

                moviesData.setPoster_path(movieJsonObject.getString("poster_path"));
                moviesData.setOverview(movieJsonObject.getString("overview"));
                moviesData.setRelease_date(movieJsonObject.getString("release_date"));
                moviesData.setOriginal_title(movieJsonObject.getString("original_title"));
                moviesData.setVote_average(movieJsonObject.getDouble("vote_average"));
                moviesData.setPopularity(movieJsonObject.getDouble("popularity"));
                moviesDataArrayList.add(moviesData);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public  ArrayList<MoviesData> getMoviesDataArrayList(){

        return  moviesDataArrayList;
    }




}
