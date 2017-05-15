package lab.permissions.example.imdbmovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.Serializable;
import java.net.URL;
import java.util.Vector;

import lab.permissions.example.imdbmovies.data.MovieConstants;
import lab.permissions.example.imdbmovies.data.MovieItem;
import lab.permissions.example.imdbmovies.utilities.MovieDataJsonUtils;
import lab.permissions.example.imdbmovies.utilities.MovieUrlBuilder;
import lab.permissions.example.imdbmovies.utilities.NetworkUtils;

/**
 * Created by aniket on 5/13/17.
 */

public class MovieGrid extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler, ActionBar.TabListener{
    private RecyclerView mRecyclerView;
    private TextView mErrorMessageDisplay;
    private MovieAdapter mMovieAdapter;
    public static ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        // Now we can setup the adapter of the RecyclerView and toggle the visibility.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movie_list);

        // Used to display errors amd will be hidden id there are no errors
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        mRecyclerView.setHasFixedSize(true);

        /*  Adapter is responsible for linking our weather data with the views.
            Will display our movie data
        */

        mMovieAdapter = new MovieAdapter(getApplicationContext(),this);
        mRecyclerView.setAdapter(mMovieAdapter);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        setupTabs();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){
        //Called when a tab is selected
        int nTabSelected = tab.getPosition();
        switch (nTabSelected) {
            case 0:
                loadMovieData(MovieConstants.FETCH_POPULAR_MOVIES);
                break;
            case 1:
                loadMovieData(MovieConstants.FETCH_TOPRATED_MOVIES);
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Called when a tab unselected.
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Called when a tab is selected again.
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }



    private void loadMovieData(String sortBy){
        showMovieDataView();
        new FetchMovieTask1().execute(sortBy);
    }

    @Override
    public void onClick(MovieItem movieItem){
        Context context = this;
        Intent movieSelectedIntent = new Intent(context, MovieSelected.class);
        movieSelectedIntent.putExtra("movieItemSelected", (Serializable)movieItem);
        startActivity(movieSelectedIntent);
    }

    private void showMovieDataView(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    private void showErrorMessage(){
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    public class FetchMovieTask1 extends AsyncTask<String, Void, Vector<ContentValues>> {
        Vector<ContentValues> simpleJsonMovieData = new Vector<ContentValues>();
        @Override
        public void onPreExecute() {
            super.onPreExecute();
            MovieGrid.mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Vector<ContentValues> doInBackground(String... params){
            if(params.length ==0){
                return null;
            }
            URL movieRequestUrl = MovieUrlBuilder.buildUrl(params[0]);
            try{
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                Log.i("Aniket", "jsonMovieResponse" + jsonMovieResponse);
                simpleJsonMovieData = MovieDataJsonUtils.getSimpleMovieDataStringsFromJson(jsonMovieResponse, "popular");
                return simpleJsonMovieData;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Vector<ContentValues> movieData) {
            mProgressBar.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                    mMovieAdapter.setMovieData(movieData);
//                Log.i("Aniket", "here in onPostExecute");
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
    }


    private void setupTabs(){


        final ActionBar actionBar = getSupportActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.addTab(actionBar.newTab().setText("Popular Movies").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Top Rated Movies").setTabListener(this));
    }
}
