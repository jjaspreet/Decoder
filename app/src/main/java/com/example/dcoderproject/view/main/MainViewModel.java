package com.example.dcoderproject.view.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dcoderproject.core.NetworkState;
import com.example.dcoderproject.core.Status;
import com.example.dcoderproject.data.model.InfoEntity;
import com.example.dcoderproject.domain.MainUseCase;
import com.example.dcoderproject.view.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {
    public static final String TAG = "ABC";

    public MutableLiveData<NetworkState<List<InfoEntity>>> liveData = new MutableLiveData<>();
    public MutableLiveData<NetworkState<List<InfoEntity>>> dbliveData = new MutableLiveData<>();
    private MainUseCase mainUseCase;

    @Inject
    MainViewModel(MainUseCase mainUseCase) {
        this.mainUseCase = mainUseCase;
    }

    public void getDataFromServer(int pageNumber) {

        liveData.setValue(new NetworkState<List<InfoEntity>>(Status.RUNNING, null, null));

        mainUseCase.getDataFromServer(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<InfoEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<InfoEntity> infoEntities) {
                        liveData.setValue(new NetworkState<List<InfoEntity>>(Status.SUCCESS, infoEntities, null));

                        Log.d(TAG, "onSuccess: " + infoEntities);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: ", e);
                        liveData.setValue(new NetworkState<List<InfoEntity>>(Status.FAILED, null, e));
                    }
                });
    }

    public void getdataFromDb(){
        mainUseCase.getdataFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<InfoEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<InfoEntity> infoEntities) {


                        dbliveData.setValue(new NetworkState<List<InfoEntity>>(Status.SUCCESS, infoEntities, null));

                        Log.d(TAG, "onSuccess: " + infoEntities);

                    }

                    @Override
                    public void onError(Throwable e) {

                        dbliveData.setValue(new NetworkState<List<InfoEntity>>(Status.SUCCESS, null, e));
                    }
                });
    }

    public void insertDataToDatabase(InfoEntity infoEntity){
        mainUseCase.insertDataToDatabase(infoEntity);
    }

    public void clearDb(){
        mainUseCase.clearDb();
    }
}
