package com.bignerdranch.android.mybills;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

public class LandingActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private List<MonthView> mMonths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = findViewById(R.id.activity_landing_view_pager);

        mMonths = MonthViewDatabase.get(this).getMonthViews();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                MonthView month = mMonths.get(position);
                return MonthFragment.newInstance(month.getId());
            }

            @Override
            public int getCount() {
                return mMonths.size();
            }
        });

    }
}
