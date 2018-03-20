package com.anubhav.aro.dailymotivationalquotesnonmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anubhav on 19/03/18.
 */

public class QuoteObject {

    @SerializedName("contents")
    private Contents contents;

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }
}
