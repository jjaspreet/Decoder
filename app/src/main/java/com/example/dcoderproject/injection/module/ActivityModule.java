package com.example.dcoderproject.injection.module;

import com.example.dcoderproject.injection.scope.ActivityScope;
import com.example.dcoderproject.view.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract  MainActivity contributeMainActivity();

}
