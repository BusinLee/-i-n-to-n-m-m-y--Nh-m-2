package com.example.anhki.tradingbook.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.View.Fragment.Admin.AccReport;
import com.example.anhki.tradingbook.View.Fragment.Admin.NotProcess;
import com.example.anhki.tradingbook.View.Fragment.Admin.Processed;
import com.example.anhki.tradingbook.View.Fragment.Admin.Processing;

public class FragmentAdmin extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    int notprocess = 0, processing = 0;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home_admin, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.subtablayoutadmin);
        viewPager = (ViewPager) view.findViewById(R.id.subviewpageradmin);

        FragmentAdmin.SectionsPagerAdapter mSectionsPagerAdapter = new FragmentAdmin.SectionsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);

        return view;
    }
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return NotProcess.newInstance(1);
                case 1:
                    return Processing.newInstance(2);
                case 2:
                    return Processed.newInstance(3);
                default:
                    return AccReport.newInstance(4);
            }

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Chưa xử lý (" + String.valueOf(notprocess) + ")";
                case 1:
                    return "Đang xử lý (" + String.valueOf(processing) + ")";
                case 2:
                    return "Đã xử lý";
                default:
                    return "Tài khoản";
            }
        }
    }

}
