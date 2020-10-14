package com.example.dcoderproject.view.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dcoderproject.R;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    public T viewModel;
    private FrameLayout loadingLayout;
    private ViewStub viewStub;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(R.layout.activity_base);
        loadingLayout = findViewById(R.id.loadingLayout);
        viewStub = findViewById(R.id.viewStub);
        viewStub.setLayoutResource(getLayoutRes());
        viewStub.inflate();
        hideLoading();
        viewModel = initViewModel();
        initView();
        handlerViewModel();
        handleClicks();

    }

    public void inject() {
        AndroidInjection.inject(this);
    }

    @LayoutRes
    public abstract Integer getLayoutRes();

    /**
     * override viewmodel
     */
    public abstract T initViewModel();

    /**
     * override view
     */
    public abstract void initView();

    /**
     * override viewmodel handler
     */
    public abstract void handlerViewModel();

    /*
     * handling the clicks
     */

    public abstract void handleClicks();

    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }
}
