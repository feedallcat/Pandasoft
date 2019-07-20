package com.apicom.pandasoft.network;

import com.apicom.pandasoft.model.JsonBody;
import com.apicom.pandasoft.model.NewsLike;
import com.apicom.pandasoft.model.NewsList;
import com.apicom.pandasoft.model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PandasoftService {
    @POST("v1/login")
    Call<JsonBody> login(@Body UserLogin userLogin);
    @GET("v1/news")
    Call<NewsList> getNewsList();
    @POST("v1/like")
    Call<JsonBody> likeNews();
}
