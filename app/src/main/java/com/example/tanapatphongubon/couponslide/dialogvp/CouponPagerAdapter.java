package com.example.tanapatphongubon.couponslide.dialogvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


public class CouponPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public CouponPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {

        return fragmentList.size();
    }

    public String getNumberOfCoupon(int currentPosition){
        return (++currentPosition)+"/"+ getCount();
    }

    public boolean canPrevious(int currentPosition){
        return getCount() > currentPosition;
    }

    public boolean canNext(int currentPosition){
        return currentPosition < getCount() - 1;
    }

}
