package com.example.anhki.tradingbook.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.anhki.tradingbook.R;
import com.example.anhki.tradingbook.Adapter.ViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_baseline_home_white_18dp,
            R.drawable.ic_baseline_home_black_18dp,
            R.drawable.ic_baseline_face_white_18dp,
            R.drawable.ic_baseline_face_black_18dp,
            R.drawable.ic_outline_more_horiz_white_18dp,
            R.drawable.ic_outline_more_horiz_black_18dp
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            switch (i){
                case 0:
                    tabLayout.getTabAt(i).setIcon(tabIcons[0]);
                    break;
                case 1:
                    tabLayout.getTabAt(i).setIcon(tabIcons[3]);
                    break;
                case 2:
                    tabLayout.getTabAt(i).setIcon(tabIcons[5]);
                    break;
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    if (tabLayout.getTabAt(i).isSelected())
                    {
                        switch (i){
                            case 0:
                                tabLayout.getTabAt(i).setIcon(tabIcons[0]);
                                break;
                            case 1:
                                tabLayout.getTabAt(i).setIcon(tabIcons[2]);
                                break;
                            case 2:
                                tabLayout.getTabAt(i).setIcon(tabIcons[4]);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    if (tabLayout.getTabAt(i).isSelected() == false)
                    {
                        switch (i){
                            case 0:
                                tabLayout.getTabAt(i).setIcon(tabIcons[1]);
                                break;
                            case 1:
                                tabLayout.getTabAt(i).setIcon(tabIcons[3]);
                                break;
                            case 2:
                                tabLayout.getTabAt(i).setIcon(tabIcons[5]);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    if (tabLayout.getTabAt(i).isSelected())
                    {
                        switch (i){
                            case 0:
                                tabLayout.getTabAt(i).setIcon(tabIcons[0]);
                                break;
                            case 1:
                                tabLayout.getTabAt(i).setIcon(tabIcons[2]);
                                break;
                            case 2:
                                tabLayout.getTabAt(i).setIcon(tabIcons[4]);
                                break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
