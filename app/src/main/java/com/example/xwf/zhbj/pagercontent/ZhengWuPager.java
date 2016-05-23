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
public class ZhengWuPager extends BasePager {
    public ZhengWuPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "政务 加载数据了 ");
        mTextViewTitle.setText("政务");
        TextView tv = new TextView(mActivity);
        tv.setText("政务");
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        mImageButtionLeftMenu.setVisibility(View.VISIBLE);
        mFrameLayoutContent.addView(tv);
    }
}
