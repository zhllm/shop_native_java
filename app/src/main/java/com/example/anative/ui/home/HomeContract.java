package com.example.anative.ui.home;

import com.example.anative.beans.BaseBean;
import com.example.anative.beans.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HomeContract {
    interface IHomePresenter {
        void getData();

    }

    interface IHomeModel {
        Flowable<BaseBean<List<Goods>>> getData();
    }

    interface IHomeView {
        void getGoodsSuccess(List<Goods> goods);
        void getGoodsFailure(Throwable throwable);
    }

}
