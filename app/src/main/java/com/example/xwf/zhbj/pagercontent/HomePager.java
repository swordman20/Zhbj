package com.example.xwf.zhbj.pagercontent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.BasePager;
import com.example.xwf.zhbj.view.BlankPager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:HomePager的基本实现
 */
public class HomePager extends BasePager {
    public TextView mTVHint;

    public HomePager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化数据的时候调用
     */
    @SuppressLint("NewApi")
    @Override
    public void initData() {
        mTextViewTitle.setText("智慧北京");
        mFrameLayoutContent.removeAllViews();
        View view = new BlankPager(mActivity).blankView();
        mFrameLayoutContent.addView(view);
        Log.d(TAG, "首页 加载数据了 ");
    }
}
