package com.example.xwf.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Hsia on 16/5/25.
 * E-mail: xiaweifeng@live.cn
 * //TODO:自定义横向滑动的Viewpager
 */
public class HorizontalViewPager extends ViewPager {

    public int downX;
    public int downY;
    public int moveX;
    public int moveY;

    public HorizontalViewPager(Context context) {
        super(context);
    }

    public HorizontalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 1、上下滑动不拦截事件
     * 2、左右滑动，并且是第一个页面或最后一个页面不拦截事件
     * 3、其他都拦截
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //请求父元素不要拦截我的事件
//        getParent().requestDisallowInterceptTouchEvent(true);
        int action = ev.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                downY = (int) ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                moveX = (int) ev.getX();
                moveY = (int) ev.getY();
                int diffX = moveX - downX;
                int diffY = moveY - downY;
                //横着滑动,请求不要拦截我的事件，自己处理
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    //从左向右滑动，并且是第一个页面不拦截 move>0
                    if (diffX>0 && getCurrentItem()==0){
                        Log.d("Hsia", "第一个页面");
                        getParent().requestDisallowInterceptTouchEvent(false);
                        Log.d("Hsia", "最后页面");
                        //当前是最后一个页面 并且是从右向左滑动，不拦截
                    }else if(getCurrentItem() == getAdapter().getCount()-1 && diffX<0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }else {
                        //左右滑动其他都拦截
                        getParent().requestDisallowInterceptTouchEvent(true);
                        Log.d("Hsia", "左右滑动");
                    }
                }else {
                    //竖向滑动，上层布局可以拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }



        return super.dispatchTouchEvent(ev);
    }
}
