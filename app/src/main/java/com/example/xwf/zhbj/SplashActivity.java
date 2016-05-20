package com.example.xwf.zhbj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.xwf.zhbj.utils.CacheUtils;

public class SplashActivity extends AppCompatActivity {

    public static final String ISOPER = "isopen";
    public static final String TAG = "Hsia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RelativeLayout rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        //定义三种动画
        AnimationSet set = new AnimationSet(true);
        //旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        //设置动画监听
        set.setAnimationListener(new MyAnimationListener());
        //开启动画
        rlRoot.startAnimation(set);
    }
    class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }
        //当动画执行结束，执行此方法
        @Override
        public void onAnimationEnd(Animation animation) {
            boolean open = CacheUtils.getSharedPreferences(getApplicationContext(), ISOPER, false);
            if (open) {
                Log.d(TAG, "进入主界面");
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }else {
                finish();
                startActivity(new Intent(getApplicationContext(),GuideActivity.class));
                Log.d(TAG, "进入引导页");
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
