package model;

import com.google.gson.annotations.SerializedName;

public class Title {

    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    @SerializedName("publicationDate")
    private PublicationDate publicationDate;

    private static class PublicationDate {

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
