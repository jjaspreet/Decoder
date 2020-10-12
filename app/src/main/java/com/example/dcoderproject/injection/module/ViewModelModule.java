package com.example.dcoderproject.injection.module;

import androidx.lifecycle.ViewModel;

import com.example.dcoderproject.injection.scope.ViewModelKey;
import com.example.dcoderproject.view.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindsLoginViewModel(MainViewModel viewModel);
}
