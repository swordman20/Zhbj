package com.example.xwf.zhbj.pageleftmenu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.LeftMenuBasePager;
import com.example.xwf.zhbj.bean.NewsCenterBean;
import com.example.xwf.zhbj.pagercontent.pagercontenttab.TabPager;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hsia on 16/5/23.
 * E-mail: xiaweifeng@live.cn
 * //TODO:左侧菜单的新闻页面
 */
public class NewsPager extends LeftMenuBasePager {
    @Bind(R.id.pager)
    ViewPager pager;
    public final List<NewsCenterBean.NewsCenterMenu.NewsMenuTab> newsMenuTabList; //页签对应的数据
    public List<TabPager> tabPagerList; //tab的页面
    @Bind(R.id.indicator)
    TabPageIndicator indicator;


    public NewsPager(Activity mActivity, NewsCenterBean.NewsCenterMenu newsCenterMenu) {
        super(mActivity);
        newsMenuTabList = newsCenterMenu.getChildren();
    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.leftmenu_newspager, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //给viewpager设置数据之前先准备数据
        tabPagerList = new ArrayList<>();
        for (int i = 0; i < newsMenuTabList.size(); i++) {
            tabPagerList.add(new TabPager(mActivity, newsMenuTabList.get(i)));
        }

        Log.d(TAG, "initData: " + "新闻页面数据初始化了");
        LeftMenuPagerAdapter leftMenuPagerAdapter = new LeftMenuPagerAdapter();
        pager.setAdapter(leftMenuPagerAdapter);
        //给indicator设置数据
        indicator.setViewPager(pager);
    }

    class LeftMenuPagerAdapter extends PagerAdapter {
        //给indicator设置数据
        @Override
        public CharSequence getPageTitle(int position) {
            return newsMenuTabList.get(position).getTitle();
        }

        @Override
        public int getCount() {
            return tabPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabPager tabPager = tabPagerList.get(position);
            View view = tabPager.initView();
            container.addView(view);
            tabPager.initData();
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
