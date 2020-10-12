package com.example.dcoderproject.injection.component;


import android.app.Application;

import com.example.dcoderproject.DecoderApplication;
import com.example.dcoderproject.injection.module.ActivityModule;
import com.example.dcoderproject.injection.module.AppModule;
import com.example.dcoderproject.injection.module.NetworkModule;
import com.example.dcoderproject.injection.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        ViewModelModule.class,
        ActivityModule.class})
public interface AppComponent {

     void inject(DecoderApplication application);
}
