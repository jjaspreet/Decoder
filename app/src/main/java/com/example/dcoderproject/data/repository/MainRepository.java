package com.example.dcoderproject.data.repository;

import com.example.dcoderproject.data.api.ApiInterface;
import com.example.dcoderproject.data.database.dao.InfoDao;
import com.example.dcoderproject.data.model.Info;
import com.example.dcoderproject.data.model.InfoEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private ApiInterface apiInterface;
    private InfoDao infoDao;



    @Inject
    public MainRepository(ApiInterface apiInterface, InfoDao infoDao) {
        this.apiInterface = apiInterface;
        this.infoDao = infoDao;
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


    public Single<List<InfoEntity>> getdataFromDb(){
        return infoDao.getAllAudits();
    }

    public void insertDataToDatabase(InfoEntity infoEntity){
        infoDao.insert(infoEntity);

    }

    public void deleteAllInfoFromDb(){
        infoDao.deleteAll();
    }

}
