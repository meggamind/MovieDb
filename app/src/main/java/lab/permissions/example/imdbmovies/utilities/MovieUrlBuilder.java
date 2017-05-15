package lab.permissions.example.imdbmovies.utilities;

import java.net.URL;
import android.net.Uri;
import android.util.Log;
import java.net.MalformedURLException;

import lab.permissions.example.imdbmovies.data.MovieConstants;

/**
 * Created by aniket on 5/13/17.
 */

public class MovieUrlBuilder {
    private static final String TAG = "MovieUrlBuilder";

    public static URL buildUrl(String sortBy) {
        String fetch_base_url = MovieConstants.getBaseUrlFor(sortBy) + MovieConstants.DESC_ORDER + MovieConstants.ENG_LANG;

        Uri builtUri = Uri.parse(fetch_base_url).buildUpon()
                .appendQueryParameter(MovieConstants.API_KEY, MovieConstants.KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url.toString());

        return url;
    }
}
