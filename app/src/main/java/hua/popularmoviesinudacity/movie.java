package hua.popularmoviesinudacity;

import java.io.Serializable;

/**
 * Created by caihua2300 on 06/07/2016.
 */
public class movie implements Serializable {
    private String path;
    private String title;
    private String overview;
    private String vote_average;
    private String release_date;

    public movie(String overview, String path, String release_date, String title, String vote_average) {
        this.overview = overview;
        this.path = path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getPath() {
        return path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
