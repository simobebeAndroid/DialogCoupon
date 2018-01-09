package com.example.tanapatphongubon.couponslide.model;

/**
 * Created by tanapatphongubon on 8/1/2018 AD.
 */

public class CouponModel {
    private int couponId;
    private String couponImage;
    private String couponCode;

    public CouponModel() {
    }

    public CouponModel(int couponId, String couponImage, String couponCode) {
        this.couponId = couponId;
        this.couponImage = couponImage;
        this.couponCode = couponCode;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCouponImage() {
        return couponImage;
    }

    public void setCouponImage(String couponImage) {
        this.couponImage = couponImage;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
