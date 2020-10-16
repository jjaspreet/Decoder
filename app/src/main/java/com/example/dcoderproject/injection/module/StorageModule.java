package com.example.dcoderproject.injection.module;

import androidx.room.Room;

import com.example.dcoderproject.BuildConfig;
import com.example.dcoderproject.DecoderApplication;
import com.example.dcoderproject.data.database.InfoDataBase;
import com.example.dcoderproject.data.database.dao.InfoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    public InfoDataBase provideInfoDataBase(DecoderApplication application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                InfoDataBase.class,
                BuildConfig.DATABASE_NAME
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public InfoDao provideInfoDao(InfoDataBase infoDataBase){
        return infoDataBase.infoDao();
    }
}




