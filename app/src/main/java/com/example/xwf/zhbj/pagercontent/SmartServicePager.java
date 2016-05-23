package com.example.xwf.zhbj.pagercontent;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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
        Log.d(TAG, "智慧服务 加载数据了 ");
        mTextViewTitle.setText("智慧服务");
        TextView tv = new TextView(mActivity);
        tv.setText("智慧服务");
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        mImageButtionLeftMenu.setVisibility(View.VISIBLE);
        mFrameLayoutContent.addView(tv);
    }
}
