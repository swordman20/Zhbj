package com.example.xwf.zhbj.pageleftmenu;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xwf.zhbj.base.LeftMenuBasePager;

/**
 * Created by Hsia on 16/5/23.
 * E-mail: xiaweifeng@live.cn
 * //TODO:文件描述
 */
public class ZhuanTiPager extends LeftMenuBasePager {
    public ZhuanTiPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mActivity);
        textView.setText("专题 页面");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "initData: "+"专题页面数据初始化了");
    }
}
