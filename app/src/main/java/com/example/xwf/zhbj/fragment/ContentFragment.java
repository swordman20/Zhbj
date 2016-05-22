package com.example.xwf.zhbj.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.BaseFragment;
import com.example.xwf.zhbj.base.BasePager;
import com.example.xwf.zhbj.pager.HomePager;
import com.example.xwf.zhbj.pager.NewsCenterPager;
import com.example.xwf.zhbj.pager.SettingPager;
import com.example.xwf.zhbj.pager.SmartServicePager;
import com.example.xwf.zhbj.pager.ZhengWuPager;
import com.example.xwf.zhbj.view.NoSlidingViewPager;

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

    @Override
    public View initView() {
        View contentView = View.inflate(mActivity, R.layout.fragment_content, null);
        return contentView;
    }

    @Override
    public void initData() {
        super.initData();
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

        //设置RadioGroup
        mRadioButtonGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置第一个页面
        mRadioButtonHome.setChecked(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
            BasePager basePager = basePagerList.get(position);
            View view = basePager.initView();
            container.addView(view);
            //调用初始化数据
            basePager.initData();
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
                    break;
                case R.id.rb_newcenter:
                    mViewPagerContent.setCurrentItem(1);
                    break;
                case R.id.rb_service:
                    mViewPagerContent.setCurrentItem(2);
                    break;
                case R.id.rb_zhengwu:
                    mViewPagerContent.setCurrentItem(3);
                    break;
                case R.id.rb_setting:
                    mViewPagerContent.setCurrentItem(4);
                    break;
            }
        }
    }
}
