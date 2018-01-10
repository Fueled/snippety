package com.fueled.snippety.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fueled.snippety.sample.Constant;
import com.fueled.snippety.sample.fragment.DemoFragment;
import com.fueled.snippety.sample.fragment.HtmlFragment;
import com.fueled.snippety.sample.fragment.PageFragment;

public class SnippetyPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = {Constant.DEMO_FRAGMENT_TITLE, Constant.PAGE_FRAGMENT_TITLE, Constant.HTML_FRAGMENT_TITLE};

    public SnippetyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DemoFragment.newInstance();
            case 1:
                return PageFragment.newInstance();
            case 2:
                return HtmlFragment.newInstance();
            default:
                return DemoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}