package lab.permissions.example.imdbmovies.data;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by aniket on 5/14/17.
 */

public class MovieItem implements Serializable{
    private String title;
    private String movie_poster;
    private String movie_backdrop;
    private String movie_overview;
    private float vote_average;
    private String release_date;
    private String original_title;

    public String getTitle(){return title;}
    public void setTitle(String title){
        this.title = title;
    }

    public String getMovie_poster(){
        return movie_poster;
    }
    public void setMovie_poster(String movie_poster){
        this.movie_poster = movie_poster;
    }

    public String getMovie_overview(){
        return movie_overview;
    }
    public void setMovie_overview(String movie_overview){
        this.movie_overview = movie_overview;
    }

    public float getMovie_vote(){
        return vote_average;
    }
    public void setMovie_vote(float vote_average){
        this.vote_average = vote_average;
    }

    public String getMovie_releaseDate(){
        return release_date;
    }
    public void setMovie_releaseDate(String release_date){
        this.release_date = release_date;
    }

    public String getMovie_backdrop(){
        return movie_backdrop;
    }
    public void setMovie_backdrop(String movie_backdrop){
        this.movie_backdrop = movie_backdrop;
    }

    public String getOriginalTitle(){return original_title;}
    public void setOriginalTitle(String original_title){
        this.original_title = original_title;
    }

}
