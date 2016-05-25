package com.example.xwf.zhbj.pageleftmenu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.xwf.zhbj.MainActivity;
import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.LeftMenuBasePager;
import com.example.xwf.zhbj.bean.NewsCenterBean;
import com.example.xwf.zhbj.pagercontent.pagercontenttab.TabPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Hsia on 16/5/23.
 * E-mail: xiaweifeng@live.cn
 * //TODO:左侧菜单的新闻页面
 */
public class NewsPager extends LeftMenuBasePager implements ViewPager.OnPageChangeListener {
    @Bind(R.id.pager)
    ViewPager pager;
    public final List<NewsCenterBean.NewsCenterMenu.NewsMenuTab> newsMenuTabList; //页签对应的数据
    public List<TabPager> tabPagerList; //tab的页面
    @Bind(R.id.indicator)
    TabPageIndicator indicator;
    @Bind(R.id.ib_tab_next)
    ImageButton ibTabNext;
    public TopTabPagerAdapter topTabPagerAdapter;//顶部新闻对应的页签


    public NewsPager(Activity mActivity, NewsCenterBean.NewsCenterMenu newsCenterMenu) {
        super(mActivity);
        //这个集合就是子页面对应的标签页
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
        topTabPagerAdapter = new TopTabPagerAdapter();
        pager.setAdapter(topTabPagerAdapter);
        //给indicator设置数据
        indicator.setViewPager(pager);
        //给viewpager设置滑动监听,注意事件已经被indicator占用了，所以要个indicator设置滑动监听
//        pager.setOnPageChangeListener(this);
        indicator.setOnPageChangeListener(this);
    }

    //点击button切换下一个页面
    @OnClick(R.id.ib_tab_next)
    public void onClick() {
        pager.setCurrentItem(pager.getCurrentItem()+1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    //如果是第一个页面，可以侧拉栏可以用，是其他页面，侧栏了不可言
    @Override
    public void onPageSelected(int position) {
        //可用
        if (position == 0){
            ((MainActivity) mActivity).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
            //不可用
        }else {
            ((MainActivity) mActivity).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class TopTabPagerAdapter extends PagerAdapter {
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
