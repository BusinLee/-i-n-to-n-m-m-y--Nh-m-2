package com.example.anhki.tradingbook.View.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.View.TrangChu.Fragment.FragmentAdmin;
import com.example.anhki.tradingbook.View.View.TrangChu.Fragment.FragmentProduct;
import com.example.anhki.tradingbook.View.View.TrangChu.Fragment.FragmentUser;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    
    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> listTitleFragment = new ArrayList<String>();
    
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment.add(new FragmentProduct());
        listFragment.add(new FragmentUser());
        listFragment.add(new FragmentAdmin());
        
        listTitleFragment.add("");
        listTitleFragment.add("");
        listTitleFragment.add("");
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitleFragment.get(position);
    }
}
