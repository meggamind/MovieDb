/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lab.permissions.example.imdbmovies.utilities;

import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;
import android.content.ContentValues;

import lab.permissions.example.imdbmovies.data.MovieConstants;

/**
 * Created by aniket on 5/13/17.
 */

public final class MovieDataJsonUtils {
    public static Context mContext = null;

    public MovieDataJsonUtils(Context context) {
        mContext = context;
    }

    public static Vector<ContentValues> getSimpleMovieDataStringsFromJson(String movieListJsonStr, String whatToDo)
            throws JSONException {
        Vector<ContentValues> cVVector  = new Vector<ContentValues>();
        try {
            JSONObject movieDataJson = new JSONObject(movieListJsonStr);
            JSONArray movieListArray = movieDataJson.getJSONArray(MovieConstants.MOVIEDB_RESULTS);
            cVVector = new Vector<ContentValues>(movieListArray.length());

            for (int i = 0; i < movieListArray.length(); i++) {
                int id;
                String overview;
                String poster_path;
                String backdrop_path;
                String release_date;
                String popularity;
                String title;
                String original_title;
                String vote_average;
                String vote_count;

                JSONObject movieInfo = movieListArray.getJSONObject(i);

                id = movieInfo.getInt(MovieConstants.MOVIEDB_ID);
                overview = movieInfo.getString(MovieConstants.MOVIEDB_OVERVIEW);
                poster_path = movieInfo.getString(MovieConstants.MOVIEDB_POSTER_PATH);
                backdrop_path = movieInfo.getString(MovieConstants.MOVIEDB_BACKDROP_PATH);
                release_date = movieInfo.getString(MovieConstants.MOVIEDB_RELEASE_DATE);
                popularity = movieInfo.getString(MovieConstants.MOVIEDB_POPULARITY);
                title = movieInfo.getString(MovieConstants.MOVIEDB_TITLE);
                vote_average = movieInfo.getString(MovieConstants.MOVIEDB_VOTE_AVERAGE);
                original_title = movieInfo.getString(MovieConstants.MOVIEDB_ORIGINAL_TITLE);

                ContentValues movieValues = new ContentValues();

                movieValues.put(MovieConstants.MOVIEDB_ID, id);
                movieValues.put(MovieConstants.MOVIEDB_OVERVIEW, overview);
                movieValues.put(MovieConstants.MOVIEDB_POSTER_PATH, poster_path);
                movieValues.put(MovieConstants.MOVIEDB_BACKDROP_PATH, backdrop_path);
                movieValues.put(MovieConstants.MOVIEDB_RELEASE_DATE, release_date);
                movieValues.put(MovieConstants.MOVIEDB_TITLE, title);
                movieValues.put(MovieConstants.MOVIEDB_ORIGINAL_TITLE, original_title);
                movieValues.put(MovieConstants.MOVIEDB_VOTE_AVERAGE, vote_average);

                cVVector.add(movieValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cVVector;
    }
}