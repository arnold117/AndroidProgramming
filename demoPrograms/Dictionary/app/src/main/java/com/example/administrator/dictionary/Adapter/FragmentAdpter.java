package com.example.administrator.dictionary.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;



public class FragmentAdpter extends FragmentPagerAdapter{

    //总页面的集合
    List<Fragment> fragments;
    String[] strings = new String[]{"英语写译","翻译写英"};
    /**
     * 构造方法
     * @param fm
     * @param list
     */
    public FragmentAdpter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragments = list;
    }

    /**
     * 获取当前页面
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * 获取总页面数
     * @return
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 获取当前标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }
}
