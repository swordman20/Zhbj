package com.example.xwf.zhbj.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xwf.zhbj.base.BasePager;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:HomePager的基本实现
 */
public class HomePager extends BasePager {
    public HomePager(Activity activity) {
        super(activity);
    }
    private boolean rota = false;
    /**
     * 初始化数据的时候调用
     */
    @Override
    public void initData() {
        super.initData();
        mTextViewTitle.setText("智慧北京");
        TextView tv = new TextView(mActivity);
        tv.setText("首页");
        tv.setTextColor(Color.RED);
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER);
        mImageButtionLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rota) {
                    RotateAnimation rotateAnimation = new RotateAnimation(90, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    v.startAnimation(rotateAnimation);
                    rota = false;
                }else {
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    v.startAnimation(rotateAnimation);
                    rota = true;
                }
            }
        });
        mFrameLayoutContent.addView(tv);
    }
}
