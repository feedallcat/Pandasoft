package com.apicom.pandasoft.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsList extends JsonBody {

    @SerializedName("data")
    private List<NewsModel> data;

    public NewsList(Integer status, String message, String access_token, String refresh_token, Integer expires_in, List<NewsModel> data) {
        super(status, message, access_token, refresh_token, expires_in);
        this.data = data;
    }

    public List<NewsModel> getData() {
        return data;
    }

    public void setData(List<NewsModel> data) {
        this.data = data;
    }
}
