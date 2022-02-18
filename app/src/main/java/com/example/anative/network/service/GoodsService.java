package com.example.anative.network.service;

import com.example.anative.beans.BaseBean;
import com.example.anative.beans.Goods;
import com.example.anative.beans.GoodsDetail;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoodsService {

    @GET("edu-lance/edu-lance.github.io/master/goods_list")
    Flowable<BaseBean<List<Goods>>> getGoods();

    @GET("edu-lance/edu-lance.github.io/master/goods_detail")
    Flowable<BaseBean<GoodsDetail>> getGoodsDetail(@Query("goodsId") int goodsId);
}
