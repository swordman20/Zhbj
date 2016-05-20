package com.example.xwf.zhbj.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * TODO:所有Fragment的基类
 *      上下文抽取
 *      初始化布局的方法抽取: 抽象
 *      初始化数据的方法抽取: 可选
 */
public abstract class BaseFragment extends Fragment{

    public Activity mActivity;//所有Fragment的上下文对象

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    //初始化View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    /**
     *  Activity已经初始化完毕了, 当前需要初始化Fragment的数据了
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    //所有继承BaseFragment的的类都需要初始化自己的View
    public abstract View initView();
    /**
     * 初始化数据, 子类覆盖此方法, 来实现自己的数据初始化操作
     */
    public void initData(){

    }
}
