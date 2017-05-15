package lab.permissions.example.imdbmovies.data;

import android.net.Uri;
import android.provider.BaseColumns;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import java.security.PublicKey;

import lab.permissions.example.imdbmovies.BuildConfig;

/**
 * Created by aniket on 5/13/17.
 */

public class MovieConstants {


    public static final String FETCH_POPULAR_MOVIES = "popularity";
    public static final String FETCH_TOPRATED_MOVIES = "vote_average";
    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/discover/movie?sort_by=";
    public static final String ENG_LANG = "&language=en";
    public static final String API_KEY = "api_key";
    public static final String KEY = BuildConfig.THE_MOVIE_DATABASE_API_KEY;
    public static final String DESC_ORDER = ".desc";

    // Json fields required
    public static final String MOVIEDB_RESULTS = "results";
    public static final String MOVIEDB_ID = "id";
    public static final String MOVIEDB_OVERVIEW = "overview";
    public static final String MOVIEDB_POSTER_PATH = "poster_path";
    public static final String MOVIEDB_BACKDROP_PATH = "backdrop_path";
    public static final String MOVIEDB_RELEASE_DATE = "release_date";
    public static final String MOVIEDB_POPULARITY = "popularity";
    public static final String MOVIEDB_TITLE = "title";
    public static final String MOVIEDB_ORIGINAL_TITLE = "original_title";
    public static final String MOVIEDB_VOTE_AVERAGE = "vote_average";
    public static final String MOVIEDB_VOTE_COUNT = "vote_count";

    public static String getBaseUrlFor(String sortBy){
       if(sortBy == FETCH_POPULAR_MOVIES){
           return MOVIE_BASE_URL + FETCH_POPULAR_MOVIES;
       }else{
           return MOVIE_BASE_URL + FETCH_TOPRATED_MOVIES;
       }
    }

    public static SpannableStringBuilder makeStringBold(String stringToConvert){
        final SpannableStringBuilder spReleaseDate = new SpannableStringBuilder(stringToConvert);
        spReleaseDate.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, stringToConvert.length(), 0);
        return spReleaseDate;
    }
}
