package com.example.dcoderproject.injection.component;


import com.example.dcoderproject.DecoderApplication;
import com.example.dcoderproject.injection.module.ActivityModule;
import com.example.dcoderproject.injection.module.AppModule;
import com.example.dcoderproject.injection.module.NetworkModule;
import com.example.dcoderproject.injection.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent {

    void inject(DecoderApplication application);
}
