package lab.permissions.example.imdbmovies.utilities;

import java.net.URL;
import java.util.Vector;
import android.view.View;
import android.os.AsyncTask;
import android.content.ContentValues;
import lab.permissions.example.imdbmovies.MovieGridActivity;


/**
 * Created by aniket on 5/15/17.
 */

public class FetchMovieTask extends AsyncTask<Object, Void, Vector<ContentValues>> {
    Vector<ContentValues> simpleJsonMovieData = new Vector<ContentValues>();

    public interface OnMovieDataFetched{
        void onMovieDataFetched(Vector<ContentValues> movieData);
    }

    private OnMovieDataFetched delegate = null;


    public FetchMovieTask(OnMovieDataFetched asyncReponse){
        delegate = asyncReponse;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
        MovieGridActivity.mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Vector<ContentValues> doInBackground(Object... params){
        if(params.length ==0){
            return null;
        }
        URL movieRequestUrl = MovieUrlBuilder.buildUrl((String) params[0]);
        try{
            String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
            simpleJsonMovieData = MovieDataJsonUtils.getSimpleMovieDataStringsFromJson(jsonMovieResponse, "popular");
            return simpleJsonMovieData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Vector<ContentValues> movieData) {
        delegate.onMovieDataFetched(movieData);
    }
}