package com.example.internapplication.API;

import com.example.internapplication.Model.List;
import com.example.internapplication.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("api/login")
    Call<User> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("/api/users?page=2")
    Call<List> listofuser();
}
