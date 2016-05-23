package com.example.xwf.zhbj.base;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.xwf.zhbj.MainActivity;
import com.example.xwf.zhbj.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:正文页面的基类
 */
public class BasePager {
    public Activity mActivity;
    public View rootView;
    public static final String TAG = "Hsia";

    @Bind(R.id.ib_left_menu)
    public ImageButton mImageButtionLeftMenu;
    @Bind(R.id.tv_title)
    public TextView mTextViewTitle;
    public @Bind(R.id.fl_content)
    FrameLayout mFrameLayoutContent;


    public BasePager(Activity activity) {
        this.mActivity = activity;
        //初始化View
        rootView = initView();
    }

    public View initView() {
        View basePagerView = View.inflate(mActivity, R.layout.base_pager, null);
        ButterKnife.bind(this, basePagerView);
        return basePagerView;

    }

    /**
     * 初始化数据, 子类覆盖此方法, 实现自己的数据初始化
     */
    public void initData() {
        //控制菜单隐藏和显示
        mImageButtionLeftMenu.setOnClickListener(new ToggleOnClickListener());
    }

    class ToggleOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //拿到MainActivity对象控制侧拉栏
            ((MainActivity) mActivity).getSlidingMenu().toggle();
            //旋转90°
            imageViewRotate(mImageButtionLeftMenu,0,180);
        }
    }

    public void imageViewRotate(ImageButton ib,int fromDegrees,int toDegrees) {
        //定义一个旋转动画
        RotateAnimation ra = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1000);
        ra.setFillAfter(true);
        ib.startAnimation(ra);
    }

}
