package com.apicom.pandasoft.model;

public class NewsLike {
    private Integer newsId;

    public NewsLike(Integer newsId){
        this.newsId = newsId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}
