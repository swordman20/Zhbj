package com.example.xwf.zhbj.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.xwf.zhbj.R;

import butterknife.Bind;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:正文页面的基类
 */
public class BasePager {
    public Activity mActivity;
    public View rootView;
    public TextView mTextViewTitle;
    public FrameLayout mFrameLayoutContent;
    public ImageButton mImageButtionLeftMenu;
//    @Bind(R.id.ib_left_menu)
//    public ImageButton mImageButtionLeftMenu;
//    @Bind(R.id.tv_title)
//    public TextView mTextViewTitle;
//    public @Bind(R.id.fl_content)
//    FrameLayout mFrameLayoutContent;

    public BasePager(Activity activity) {
        this.mActivity = activity;
        //初始化View
        rootView = initView();
    }

    public View initView() {
        View basePagerView = View.inflate(mActivity, R.layout.base_pager, null);
        mTextViewTitle = ((TextView) basePagerView.findViewById(R.id.tv_title));
        mFrameLayoutContent = ((FrameLayout) basePagerView.findViewById(R.id.fl_content));
        mImageButtionLeftMenu = ((ImageButton) basePagerView.findViewById(R.id.ib_left_menu));
        //初始化控件
        return basePagerView;
    }

    /**
     * 初始化数据, 子类覆盖此方法, 实现自己的数据初始化
     */
    public void initData() {

    }
}
