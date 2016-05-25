package com.example.xwf.zhbj.view;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.pagercontent.NewsCenterPager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hsia on 16/5/26.
 * E-mail: xiaweifeng@live.cn
 * //TODO:文件描述
 */
public class BlankPager  {
    public TextView mTVHint;
    public Activity mActivity;
    public BlankPager(Activity activity) {
        this.mActivity = activity;
    }

    public View blankView(){
        View inflate = View.inflate(mActivity, R.layout.fragment_blankpager, null);
        mTVHint = ((TextView) inflate.findViewById(R.id.tv_hint));
        inflate.findViewById(R.id.iv_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ScaleAnimation sa = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                sa.setDuration(6000);
                sa.setFillAfter(false);
                v.startAnimation(sa);
                sa.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mTVHint.setVisibility(View.VISIBLE);
                        Timer timer = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                mActivity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mTVHint.setVisibility(View.INVISIBLE);
//                                        v.setBackgroundResource(R.drawable.newscenter_initview);
                                        ((ImageView) v).setImageResource(R.drawable.newscenter_initview);
                                        new NewsCenterPager(mActivity).rotateImage((ImageView) v);
                                    }
                                });
                            }
                        };
                        timer.schedule(timerTask,4000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        return inflate;
    }
}
