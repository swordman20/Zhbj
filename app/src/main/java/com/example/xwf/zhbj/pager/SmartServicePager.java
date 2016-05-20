package com.example.xwf.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.xwf.zhbj.base.BasePager;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:文件描述
 */
public class SmartServicePager extends BasePager {

    public SmartServicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();
        mTextViewTitle.setText("智慧服务");
        TextView tv = new TextView(mActivity);
        tv.setText("智慧服务");
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        mFrameLayoutContent.addView(tv);
    }
}
