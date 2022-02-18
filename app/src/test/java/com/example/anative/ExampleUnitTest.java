package com.example.anative;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.anative.beans.BaseBean;
import com.example.anative.beans.Goods;
import com.example.anative.network.RetrofitClient;
import com.example.anative.network.service.GoodsService;

import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;
import retrofit2.Retrofit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        GoodsService goodsService = RetrofitClient.getInstance().getService(GoodsService.class);
        goodsService.getGoods().subscribe(new Consumer<BaseBean<List<Goods>>>() {
            @Override
            public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                System.out.println(listBaseBean.getData().toString());
            }
        });
    }
}