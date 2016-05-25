package com.example.xwf.zhbj.pagercontent;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.BasePager;
import com.example.xwf.zhbj.view.BlankPager;

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
        mFrameLayoutContent.removeAllViews();
        View view = new BlankPager(mActivity).blankView();
        mFrameLayoutContent.addView(view);
    }
}
