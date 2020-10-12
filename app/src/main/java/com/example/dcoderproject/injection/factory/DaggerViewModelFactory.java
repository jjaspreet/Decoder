package com.example.dcoderproject.injection.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class DaggerViewModelFactory implements ViewModelProvider.Factory {
    private static final String TAG = "ViewModelProvidersFactory";

    public Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public DaggerViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        Provider<? extends ViewModel> creator = creators.get(modelClass);
        // If viewModel has  not been created
        if (creator == null) {
            //Loop through the allowable keys aka allowed classes with the @ViewModelKey

            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                // If it is allowed , set the view model
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }

        // If this is not from one of the allowed keys
        if(creator == null){
            throw new IllegalArgumentException("unknown model class"+ modelClass);
        }

        // return the provider
        try{
            return (T)creator.get();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
