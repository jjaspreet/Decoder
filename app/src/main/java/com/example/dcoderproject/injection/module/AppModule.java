package com.example.dcoderproject.injection.module;

import com.example.dcoderproject.DecoderApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private DecoderApplication application;


    public AppModule(DecoderApplication application){
      this.application = application;
    }

    @Provides
    @Singleton
    public DecoderApplication provideMySafetipinApp(){
        return application;
    }



    @Provides
    static String getName(){
        return "Jaspreet Singh";
    }
}
