package com.example.dcoderproject.data.repository;

import com.example.dcoderproject.data.api.ApiInterface;
import com.example.dcoderproject.data.model.Info;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private ApiInterface apiInterface;

    @Inject
    public MainRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Single<List<Info.Data>> getDataFromServer(int pageNumber){
        return apiInterface.getInfoFromServer(pageNumber)
                .map(new Function<Info, List<Info.Data>>() {
                    @Override
                    public List<Info.Data> apply(Info info) {
                        List<Info.Data> data =  info.getData();
                        System.out.println(data);
                        return data;
                    }
                });
    }

}
