package com.anubhav.aro.dailymotivationalquotesnonmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anubhav on 19/03/18.
 */

public class Contents {

    @SerializedName("quotes")
    private List<Quotes> quotesList = null;

    public List<Quotes> getQuotesList() {
        return quotesList;
    }

    public void setQuotesList(List<Quotes> quotesList) {
        this.quotesList = quotesList;
    }
}
