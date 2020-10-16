package com.example.dcoderproject.view.main;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcoderproject.R;
import com.example.dcoderproject.core.NetworkState;
import com.example.dcoderproject.core.Status;
import com.example.dcoderproject.data.model.Info;
import com.example.dcoderproject.data.model.InfoEntity;
import com.example.dcoderproject.injection.factory.DaggerViewModelFactory;
import com.example.dcoderproject.view.base.BaseActivity;
import com.google.android.material.snackbar.Snackbar;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel> {
    public static final String TAG = "MainActivity";
    @Inject
    DaggerViewModelFactory viewModelFactory;
    @Inject
    String name;
    private RecyclerView recyclerView;
    private InfoAdapter infoAdapter;
    private Info infoList;
    private List<InfoEntity> dataList = new ArrayList<>();
    private List<InfoEntity> dbList = new ArrayList<>();
    private int pageNumber = 1;
    private int lastPage = 7;
    private LinearLayoutManager layoutManager;
    private ProgressBar progressBar, showLoadMoreProgressBar;
    private CoordinatorLayout coordinatorLayout;
    private boolean isLoading, isLastPage;
    private SearchView searchView;

    @Override
    public Integer getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel initViewModel() {
        return ViewModelProviders.of(
                this,
                viewModelFactory
        ).get(MainViewModel.class);
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        showLoadMoreProgressBar = findViewById(R.id.loadMoreProgressBar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("My Toolbar");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinateLayout);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        infoAdapter = new InfoAdapter(this, dataList);
        recyclerView.setAdapter(infoAdapter);

        viewModel.getDataFromServer(pageNumber);
    }

    @Override
    public void handlerViewModel() {

        viewModel.liveData.observe(this, new Observer<NetworkState<List<InfoEntity>>>() {
            @Override
            public void onChanged(NetworkState<List<InfoEntity>> listNetworkState) {
                if (listNetworkState.getStatus().equals(Status.RUNNING)) {
                    showLoading();
                }

                if (listNetworkState.getStatus().equals(Status.SUCCESS)) {
                    hideLoading();
                    viewModel.clearDb();
                    hideLoadMoreProgressBar();
                    dataList.addAll(listNetworkState.getData());
                    for(InfoEntity infoEntity : dataList){
                        viewModel.insertDataToDatabase(infoEntity);
                    }
                    infoAdapter.notifyDataSetChanged();
                }

                if (listNetworkState.getStatus().equals(Status.FAILED)) {
                    hideLoading();
                    viewModel.getdataFromDb();
                    if(listNetworkState.getError() instanceof UnknownHostException){
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "No Internet Connection", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }


                    Log.d(TAG, "onError: ");
                }
            }
        });

        viewModel.dbliveData.observe(this, new Observer<NetworkState<List<InfoEntity>>>() {
            @Override
            public void onChanged(NetworkState<List<InfoEntity>> listNetworkState) {

                if (listNetworkState.getStatus().equals(Status.RUNNING)) {
                    showLoading();
                }

                if (listNetworkState.getStatus().equals(Status.SUCCESS)) {
                    hideLoading();
                    hideLoadMoreProgressBar();
                    dbList.addAll(listNetworkState.getData());
                    dataList.addAll(dbList);
                    infoAdapter.notifyDataSetChanged();
                }

                if (listNetworkState.getStatus().equals(Status.FAILED)) {
                    hideLoading();
                    Log.d(TAG, "onError: ");
                }
            }
        });
    }

    @Override
    public void handleClicks() {

        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                if (dataList.size() > 9) {
                    showLoadMoreProgressBar();
                    viewModel.getDataFromServer(pageNumber++);
                }
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                infoAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.java:
                filterListForJava();
                break;

            case R.id.css:
                filterListForCSS();
                break;

            case R.id.python:
                filterListForPython();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterListForJava() {

    }

    private void filterListForCSS() {

    }

    private void filterListForPython() {

    }

    private void showLoadMoreProgressBar() {
        showLoadMoreProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoadMoreProgressBar() {
        showLoadMoreProgressBar.setVisibility(View.GONE);
    }
}
