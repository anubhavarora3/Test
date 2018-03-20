package com.anubhav.aro.dailymotivationalquotesnonmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anubhav on 19/03/18.
 */

public class Quotes {

    @SerializedName("quote")
    private String quote;
    @SerializedName("background")
    private String background;
    @SerializedName("date")
    private String date;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
