package me.maximpestryakov.model;

import com.google.gson.annotations.SerializedName;

public class TinkoffResponse<T> {

    public static final String RESULT_OK = "OK";

    public static final String RESULT_INTERNAL_ERROR = "INTERNAL_ERROR";

    @SerializedName("resultCode")
    private String resultCode;

    @SerializedName("plainMessage")
    private String plainMessage;

    @SerializedName("payload")
    private T payload;

    public String getResultCode() {
        return resultCode;
    }

    public String getPlainMessage() {
        return plainMessage;
    }

    public T getPayload() {
        return payload;
    }
}
