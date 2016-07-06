package hua.popularmoviesinudacity;

import java.io.Serializable;

/**
 * Created by caihua2300 on 06/07/2016.
 */
public class movie implements Serializable {
    String title;
    int image;
    String overView;
    String date;
    float rate;

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
}
