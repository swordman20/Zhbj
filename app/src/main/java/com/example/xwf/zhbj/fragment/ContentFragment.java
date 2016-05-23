package com.example.xwf.zhbj.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xwf.zhbj.MainActivity;
import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.BaseFragment;
import com.example.xwf.zhbj.base.BasePager;
import com.example.xwf.zhbj.pagercontent.HomePager;
import com.example.xwf.zhbj.pagercontent.NewsCenterPager;
import com.example.xwf.zhbj.pagercontent.SettingPager;
import com.example.xwf.zhbj.pagercontent.SmartServicePager;
import com.example.xwf.zhbj.pagercontent.ZhengWuPager;
import com.example.xwf.zhbj.view.NoSlidingViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:正文中心的Fragment
 */
public class ContentFragment extends BaseFragment {
    @Bind(R.id.vp_content)
    NoSlidingViewPager mViewPagerContent;
    @Bind(R.id.rb_home)
    RadioButton mRadioButtonHome;
    @Bind(R.id.rb_newcenter)
    RadioButton mRadioButtonNewcenter;
    @Bind(R.id.rb_service)
    RadioButton mRadioButtonService;
    @Bind(R.id.rb_zhengwu)
    RadioButton mRadioButtonZhengwu;
    @Bind(R.id.rb_setting)
    RadioButton mRadioButtonSetting;
    @Bind(R.id.rg_group)
    RadioGroup mRadioButtonGroup;
    public List<BasePager> basePagerList;
    public BasePager basePager;

    @Override
    public View initView() {
        View contentView = View.inflate(mActivity, R.layout.fragment_content, null);
        ButterKnife.bind(this, contentView);
        return contentView;
    }


    @Override
    public void initData() {
        //准备Viewpager的数据
        basePagerList = new ArrayList<>();
        basePagerList.add(new HomePager(mActivity));
        basePagerList.add(new NewsCenterPager(mActivity));
        basePagerList.add(new SmartServicePager(mActivity));
        basePagerList.add(new ZhengWuPager(mActivity));
        basePagerList.add(new SettingPager(mActivity));

        //初始化Viewpager数据
        MyPagerAdapter myPagerAdapter =  new MyPagerAdapter();
        mViewPagerContent.setAdapter(myPagerAdapter);
        //解决Viewpager预加载问题
        mViewPagerContent.setOnPageChangeListener(new MyOnPageChangeListener());
        //设置RadioGroup
        //radiobutton 选择
        mRadioButtonGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

//        设置第一个页面
        mRadioButtonGroup.check(R.id.rb_home);

    }


    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return basePagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            basePager = basePagerList.get(position);
            View view = basePager.initView();
            container.addView(view);
            //加载第一个页面的数据
            if (position == 0){
                basePager.initData();
            }
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * radiogroup的事件监听
     */
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    mViewPagerContent.setCurrentItem(0);
                    //设置左侧侧拉栏不可用
                    slidingEnable(false);
                    break;
                case R.id.rb_newcenter:
                    mViewPagerContent.setCurrentItem(1);
                    slidingEnable(true);
                    break;
                case R.id.rb_service:
                    mViewPagerContent.setCurrentItem(2);
                    slidingEnable(true);
                    break;
                case R.id.rb_zhengwu:
                    mViewPagerContent.setCurrentItem(3);
                    slidingEnable(true);
                    break;
                case R.id.rb_setting:
                    slidingEnable(false);
                    mViewPagerContent.setCurrentItem(4);
                    break;
            }
        }
    }

    /**
     * 设置左侧侧拉栏不可用
     * @param value
     */
    private void slidingEnable(boolean value) {
        MainActivity mActivity =  (MainActivity) this.mActivity;
        SlidingMenu slidingMenu = mActivity.getSlidingMenu();
        if (value) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    /**
     * viewpager页面的监听事件
     */
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //当页面被选中时，加载数据
        @Override
        public void onPageSelected(int position) {
            //根据postion加载页面
            ContentFragment.this.basePager = basePagerList.get(position);
            //调用初始化数据
            ContentFragment.this.basePager.initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
