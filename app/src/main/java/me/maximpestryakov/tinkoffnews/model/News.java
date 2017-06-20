package me.maximpestryakov.tinkoffnews.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class News extends RealmObject {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String TEXT = "text";
    public static final String DATE = "date";
    public static final String PUBLICATION_DATE = "publicationDate";
    public static final String MILLISECONDS = "milliseconds";
    public static final String CONTENT = "content";

    @PrimaryKey
    private String id = "";

    private String title = "";

    private long date = 0;

    private String content = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
