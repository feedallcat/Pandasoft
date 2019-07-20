package com.apicom.pandasoft.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsModel implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("create")
    private String create;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("detail")
    private String detail;

    public NewsModel(Integer id, String uuid,String create, String title, String image,String detail) {
        this.setId(id);
        this.setUuid(uuid);
        this.setCreate(create);
        this.setTitle(title);
        this.setImage(image);
        this.setDetail(detail);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
