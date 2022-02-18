package com.example.anative.ui.home.adapter;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.anative.beans.Goods;

import java.util.List;

public class HomeSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {

    private final List<Goods> data;

    public HomeSpanSizeLookUp(List<Goods> data) {
        this.data = data;
    }

    @Override
    public int getSpanSize(int position) {
        Log.d("hello", String.valueOf(position));
        return data.get(position).getSpanSize();
    }

    public void setGoods(List<Goods> data) {
        this.data.clear();
        this.data.addAll(data);
    }
}
