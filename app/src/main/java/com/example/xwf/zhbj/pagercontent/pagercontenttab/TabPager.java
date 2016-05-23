package com.example.xwf.zhbj.pagercontent.pagercontenttab;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.xwf.zhbj.base.LeftMenuBasePager;
import com.example.xwf.zhbj.bean.NewsCenterBean;

/**
 * Created by Hsia on 16/5/24.
 * E-mail: xiaweifeng@live.cn
 * //TODO:新闻中心页面的tab页面
 */
public class TabPager extends LeftMenuBasePager {

    public TextView textView;
    public final String title;

    public TabPager(Activity activity, NewsCenterBean.NewsCenterMenu.NewsMenuTab newsMenuTab) {
        super(activity);
        title = newsMenuTab.getTitle();
    }

    @Override
    public View initView() {
        textView = new TextView(mActivity);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText(title);
    }
}
