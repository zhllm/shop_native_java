package com.example.anative.ui.home;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.anative.R;
import com.example.anative.beans.Goods;
import com.example.anative.ui.base.BaseFragment;
import com.example.anative.ui.home.adapter.HomeRecycleViewAdapter;
import com.example.anative.ui.home.adapter.HomeSpanSizeLookUp;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeContract.IHomeView {

    private HomeRecycleViewAdapter homeRecycleViewAdapter;
    private HomePresenter homePresenter;
    private HomeSpanSizeLookUp homeSpanSIze;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        swipeRefreshLayout = find(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView recyclerView = find(R.id.home_recycle_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        List<Goods> goods = new ArrayList<>();
        homeSpanSIze = new HomeSpanSizeLookUp(goods);
        gridLayoutManager.setSpanSizeLookup(homeSpanSIze);
        recyclerView.setLayoutManager(gridLayoutManager);
        homeRecycleViewAdapter =
                new HomeRecycleViewAdapter(getActivity(), goods);
        recyclerView.setAdapter(homeRecycleViewAdapter);
        homePresenter = new HomePresenter(this);
        homePresenter.getData();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        homePresenter.getData();
    }

    @Override
    public void getGoodsSuccess(List<Goods> goods) {
        Log.d("hello", String.valueOf(goods.size()));
        homeSpanSIze.setGoods(goods);
        homeRecycleViewAdapter.setGoods(goods);
    }

    @Override
    public void getGoodsFailure(Throwable throwable) {

    }
}
