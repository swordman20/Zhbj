package com.example.xwf.zhbj.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by Hsia on 16/5/23.
 * E-mail: xiaweifeng@live.cn
 * //TODO:左侧侧栏了页面的base页面
 */
public abstract class LeftMenuBasePager {
    public Activity mActivity;
    public View rootView;
    public static final String TAG = "Hsia";

    public LeftMenuBasePager(Activity activity) {
        super();
        this.mActivity = activity;
        rootView = initView();
    }

    /**
     * 因为左侧侧拉栏item对于的页面都不一样，所以每个继承自LeftMenuBasePager的页面都必须实现initView方法
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData(){

    }
}
