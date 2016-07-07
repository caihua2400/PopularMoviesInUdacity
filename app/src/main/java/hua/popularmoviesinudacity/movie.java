package hua.popularmoviesinudacity;

import java.io.Serializable;

/**
 * Created by caihua2300 on 06/07/2016.
 */
public class movie implements Serializable {
    private String title;
    private int image;
    private String overView;
    private String date;
    private float rate;

    public movie(String date, int image, String overView, float rate, String title) {
        this.date = date;
        this.image = image;
        this.overView = overView;
        this.rate = rate;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public int getImage() {
        return image;
    }

    public String getOverView() {
        return overView;
    }

    public float getRate() {
        return rate;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
