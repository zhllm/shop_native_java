package com.example.anative.ui.home;

import com.example.anative.beans.BaseBean;
import com.example.anative.beans.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class LocalModel implements HomeContract.IHomeModel{
    // 从本地获取

    @Override
    public Flowable<BaseBean<List<Goods>>> getData() {

        return null;
    }
}
