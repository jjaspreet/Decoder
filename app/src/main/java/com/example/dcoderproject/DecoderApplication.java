package com.example.dcoderproject;

import android.app.Activity;
import android.app.Application;

import com.example.dcoderproject.injection.component.AppComponent;
import com.example.dcoderproject.injection.component.DaggerAppComponent;
import com.example.dcoderproject.injection.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class DecoderApplication extends Application implements HasActivityInjector {

    private AppComponent component;

    @Inject
    public DispatchingAndroidInjector<Activity> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initDI();
    }

    private void initDI() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }
}
