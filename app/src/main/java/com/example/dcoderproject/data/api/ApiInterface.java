package com.example.dcoderproject.data.api;

import com.example.dcoderproject.data.model.Info;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("data/{page_number}")
    Single<Info> getInfoFromServer(@Path("page_number") int page_number);
}
