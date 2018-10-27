package com.example.anhki.tradingbook.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.anhki.tradingbook.View.Fragment.FragmentUser;
import com.example.anhki.tradingbook.View.Fragment.FragmentAdmin;
import com.example.anhki.tradingbook.View.Fragment.FragmentProduct;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentProduct.newInstance();
            case 1:
                return new FragmentUser();
            default:
                return new FragmentAdmin();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
