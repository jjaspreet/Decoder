package com.example.dcoderproject.view.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dcoderproject.core.NetworkState;
import com.example.dcoderproject.core.Status;
import com.example.dcoderproject.data.model.Info;
import com.example.dcoderproject.data.model.InfoEntity;
import com.example.dcoderproject.domain.MainUseCase;
import com.example.dcoderproject.view.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends BaseViewModel {
    public static final String TAG = "ABC";

    public MutableLiveData<NetworkState<List<InfoEntity>>> liveData = new MutableLiveData<NetworkState<List<InfoEntity>>>();
    private MainUseCase mainUseCase;

    @Inject
    MainViewModel(MainUseCase mainUseCase) {
        this.mainUseCase = mainUseCase;
    }

    public void getDataFromServer(int pageNumber) {

        liveData.setValue(new NetworkState<List<InfoEntity>>(Status.RUNNING, null, null));

        Disposable disposable = mainUseCase.getDataFromServer(pageNumber)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<InfoEntity>>() {
                    @Override
                    public void onSuccess(List<InfoEntity> infoEntities) {
                        Log.d(TAG, "onSuccess: ");

                        liveData.setValue(new NetworkState<List<InfoEntity>>(Status.RUNNING, infoEntities, null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);

                        liveData.setValue(new NetworkState<List<InfoEntity>>(Status.RUNNING, null, e));
                    }
                });
        compositeDisposable.add(disposable);
    }
}
