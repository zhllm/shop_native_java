package com.example.anative.ui.home;

import com.example.anative.beans.BaseBean;
import com.example.anative.beans.Goods;
import com.example.anative.network.RetrofitClient;
import com.example.anative.network.service.GoodsService;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

// 从服务端获取
public class HomeModel implements HomeContract.IHomeModel {

    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {
        return RetrofitClient.getInstance()
                .getService(GoodsService.class)
                .getGoods();
    }


}
