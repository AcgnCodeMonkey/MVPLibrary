package com.xujl.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ViewPager mViewPager;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.vp);
        final RadioGroup radioGroup = findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this);
        mFragments = new ArrayList<>();
        addFragments();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem (int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount () {
                return mFragments.size();
            }
        });
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected (int position) {
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    ((RadioButton) radioGroup.getChildAt(i)).setChecked(i == position);
                }
            }

            @Override
            public void onPageScrollStateChanged (int state) {

            }
        });
    }

    private void addFragments () {
        for (int i = 1; i < 5; i++) {
            Fragment fragment = new TestFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", "" + i);
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }

    }

    @Override
    public void onCheckedChanged (RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb3:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb4:
                mViewPager.setCurrentItem(3);
                break;
            default:

                break;

        }
    }
}
