package com.serhii.autorizace;

/**
 * Created by Serhii on 18.03.2017.
 */

public class Post {
    private String title;
    private String text;

    public Post(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return  title;
    }

    public String getText() {
        return  text;
    }
}
