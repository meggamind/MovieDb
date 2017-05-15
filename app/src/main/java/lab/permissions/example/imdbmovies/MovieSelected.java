package lab.permissions.example.imdbmovies;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import lab.permissions.example.imdbmovies.data.MovieConstants;
import lab.permissions.example.imdbmovies.data.MovieItem;

/**
 * Created by aniket on 5/13/17.
 */

public class MovieSelected extends AppCompatActivity {
    private ImageView mMovieThumbnail;
    private ImageView mBackdrop;
    private TextView mOverView;
    private TextView mVote_average;
    private TextView mRelease_date;
    private TextView mOriginal_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_selected);

        mMovieThumbnail = (ImageView)findViewById(R.id.movie_thumbnail);
        mOverView = (TextView)findViewById(R.id.overview);
        mOriginal_title = (TextView) findViewById(R.id.original_title);
        mBackdrop = (ImageView) findViewById(R.id.backdrop);

        mVote_average = (TextView)findViewById(R.id.vote_average);
        mRelease_date = (TextView)findViewById(R.id.release_date);

        Intent intentThatStartedThisActivity = getIntent();
        MovieItem mMovieItem = (MovieItem) intentThatStartedThisActivity.getSerializableExtra("movieItemSelected");

        Log.i("Aniket", "backdrop: " + mMovieItem.getMovie_backdrop());

        Picasso.with(getApplicationContext())
                .load("http://image.tmdb.org/t/p/w185/\\" + mMovieItem.getMovie_backdrop())
                .error(R.drawable.ic_no_wifi)
                .placeholder(R.drawable.ic_loading)
                .into(mBackdrop);


        Picasso.with(getApplicationContext())
                .load("http://image.tmdb.org/t/p/w185/" + mMovieItem.getMovie_poster())
                .error(R.drawable.ic_no_wifi)
                .placeholder(R.drawable.ic_loading)
                .into(mMovieThumbnail);

        mOverView.setText(MovieConstants.makeStringBold("Summary:\n"));
        String summaryText = mMovieItem.getMovie_overview();
        if(summaryText!=null){
            mOverView.append(summaryText);
        }

        mOriginal_title.setText(MovieConstants.makeStringBold("Title: "));
        String originalTitleText = mMovieItem.getOriginalTitle();
        if(originalTitleText!=null){
            mOriginal_title.append(originalTitleText);
        }

        mVote_average.setText(MovieConstants.makeStringBold("User Rating: "));
        mVote_average.append(String.valueOf(mMovieItem.getMovie_vote()));

        mRelease_date.setText(MovieConstants.makeStringBold("Release Date: "));
        String releaseDateText = mMovieItem.getMovie_releaseDate();
        if (releaseDateText!=null) {
            mRelease_date.append(releaseDateText);
        }
    }
}
