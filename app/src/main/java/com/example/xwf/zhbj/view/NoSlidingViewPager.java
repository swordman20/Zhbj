package com.example.xwf.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Hsia on 16/5/22.
 * E-mail: xiaweifeng@live.cn
 * //TODO:自定义Viewpager重写onTouchEvent表示不拦截滑动事件
 * onInterceptTouchEvent 表示不拦截事件
 */
public class NoSlidingViewPager extends ViewPager {
    public NoSlidingViewPager(Context context) {
        super(context);
    }

    public NoSlidingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
