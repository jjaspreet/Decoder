package com.example.dcoderproject.domain;

import com.example.dcoderproject.data.model.Info;
import com.example.dcoderproject.data.model.InfoEntity;
import com.example.dcoderproject.data.repository.MainRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class MainUseCase {

    private MainRepository repository;

    @Inject
    public MainUseCase(MainRepository mainRepository) {
        this.repository = mainRepository;
    }

    public Single<List<InfoEntity>> getDataFromServer(int pageNumber) {
        return repository.getDataFromServer(pageNumber)
                .map(new Function<List<Info.Data>, List<InfoEntity>>() {
                    @Override
                    public List<InfoEntity> apply(List<Info.Data> data) {
                        List<InfoEntity> infoEntityList = new ArrayList<>();
                        for (Info.Data entity : data) {
                            InfoEntity infoEntity = new InfoEntity();
                            infoEntity.setUsername(entity.getUsername());
                            infoEntity.setTitle(entity.getTitle());
                            infoEntity.setDescription(entity.getDescription());
                            infoEntity.setStars(entity.getStars().getNumber());
                            infoEntity.setForks(entity.getForks().getNumber());
                            infoEntityList.add(infoEntity);
                        }
                        return infoEntityList;
                    }
                });
    }
}
