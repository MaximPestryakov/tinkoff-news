package me.maximpestryakov.tinkoffnews.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class News implements Serializable {

    @SerializedName("title")
    private Title title;

    @SerializedName("content")
    private String content;

    public Title getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static class Title implements Serializable {

        @SerializedName("id")
        private String id;

        @SerializedName("text")
        private String text;

        @SerializedName("publicationDate")
        private PublicationDate publicationDate;

        private static class PublicationDate implements Serializable {

            @SerializedName("milliseconds")
            private Long milliseconds;
        }

        public String getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public Long getPublicationDate() {
            return publicationDate.milliseconds;
        }
    }
}
