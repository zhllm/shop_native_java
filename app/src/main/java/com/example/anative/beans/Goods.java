package com.example.anative.beans;


import java.util.List;

public class Goods {

    private int goodsId;
    private int spanSize;
    private List<String> banners;
    private String imageUrl;
    private String text;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
    public int getGoodsId() {
        return goodsId;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
    public int getSpanSize() {
        return spanSize;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }
    public List<String> getBanners() {
        return banners;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", spanSize=" + spanSize +
                ", banners=" + banners +
                ", imageUrl='" + imageUrl + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}