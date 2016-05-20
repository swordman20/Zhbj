package com.example.xwf.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.xwf.zhbj.base.BasePager;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:HomePager的基本实现
 */
public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化数据的时候调用
     */
    @Override
    public void initData() {
        super.initData();
        mTextViewTitle.setText("智慧北京");
        TextView tv = new TextView(mActivity);
        tv.setText("首页");
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        mFrameLayoutContent.addView(tv);
    }
}
