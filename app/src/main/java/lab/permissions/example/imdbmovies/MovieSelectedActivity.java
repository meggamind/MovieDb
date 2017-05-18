package lab.permissions.example.imdbmovies;

import android.os.Bundle;
import butterknife.BindView;
import android.content.Intent;
import android.widget.TextView;
import butterknife.ButterKnife;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import android.support.v7.app.AppCompatActivity;
import lab.permissions.example.imdbmovies.data.MovieItem;
import lab.permissions.example.imdbmovies.data.MovieConstants;

/**
 * Created by aniket on 5/13/17.
 */

public class MovieSelectedActivity extends AppCompatActivity {
    @BindView(R.id.movie_thumbnail) ImageView mMovieThumbnail;
    @BindView(R.id.backdrop) ImageView mBackdrop;
    @BindView(R.id.overview) TextView mOverView;
    @BindView(R.id.vote_average) TextView mVote_average;
    @BindView(R.id.release_date) TextView mRelease_date;
    @BindView(R.id.original_title) TextView mOriginal_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_selected);
        ButterKnife.bind(this);

        Intent intentThatStartedThisActivity = getIntent();
        MovieItem mMovieItem = (MovieItem) intentThatStartedThisActivity.getParcelableExtra("movieItemSelected");

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
