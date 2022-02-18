package com.example.anative.ui.home;

import com.example.anative.beans.BaseBean;
import com.example.anative.beans.Goods;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomeContract.IHomePresenter {
    private final HomeContract.IHomeModel homeModel;
    private final HomeContract.IHomeView homeView;

    public HomePresenter(HomeContract.IHomeView homeView) {
        homeModel = new HomeModel();
        this.homeView = homeView;
    }

    @Override
    public void getData() {
        homeModel.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Goods>>>() {
                    @Override
                    public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                        homeView.getGoodsSuccess(listBaseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        homeView.getGoodsFailure(throwable);
                    }
                });
    }
}
