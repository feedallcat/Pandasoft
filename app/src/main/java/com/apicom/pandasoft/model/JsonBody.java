package com.apicom.pandasoft.model;

import com.google.gson.annotations.SerializedName;

public class JsonBody {
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("access_token")
    private String access_token;
    @SerializedName("refresh_token")
    private String refresh_token;
    @SerializedName("expires_in")
    private Integer expires_in;

    public JsonBody(Integer status,String message,String access_token, String refresh_token, Integer expires_in) {
        this.setStatus(status);
        this.setMessage(message);
        this.setAccess_token(access_token);
        this.setRefresh_token(refresh_token);
        this.setExpires_in(expires_in);
    }

    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
