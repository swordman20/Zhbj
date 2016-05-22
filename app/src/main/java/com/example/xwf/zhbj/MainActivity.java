package com.example.xwf.zhbj;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.xwf.zhbj.base.BasePager;
import com.example.xwf.zhbj.fragment.ContentFragment;
import com.example.xwf.zhbj.fragment.LeftMenuFragment;
import com.example.xwf.zhbj.pager.HomePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.List;

/**
 * Created by Hsia on 16/5/13.
 * E-mail: xiaweifeng@live.cn
 * //TODO:主页面
 */
public class MainActivity extends SlidingFragmentActivity {
    public static final String CONTENTFRAGMENT = "ContentFragment";//用于标识fragment的id
    private static final String LEFTMENUFRAGMENT = "LeftMenuFragment";
    public static final String TAG = "Hsia";

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果是手机有虚拟按键，将自动不被覆盖。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);
        //获取slidingMean管理器
        setBehindContentView(R.layout.left_mean);
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置边界模式
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置拖拽模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主屏幕留在界面的宽度
        slidingMenu.setBehindOffset(300);
        //slidingMenu被拉开的监听
        // TODO: 16/5/22 明天认真写
        slidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            @Override
            public void onOpened() {
                ContentFragment cf = (ContentFragment) getFragmentManager().findFragmentByTag(CONTENTFRAGMENT);
                List<BasePager> bpl = cf.basePagerList;
                BasePager hp = bpl.get(1);
                ImageButton ib = hp.mImageButtionLeftMenu;
                RotateAnimation rotateAnimation = new RotateAnimation(0,90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(200);
                rotateAnimation.setFillAfter(true);
                ib.startAnimation(rotateAnimation);
            }
        });
        slidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
            @Override
            public void onClosed() {
                ContentFragment cf = (ContentFragment) getFragmentManager().findFragmentByTag(CONTENTFRAGMENT);
                List<BasePager> bpl = cf.basePagerList;
                BasePager bp = bpl.get(1);
                ImageButton ib = bp.mImageButtionLeftMenu;
                RotateAnimation rotateAnimation = new RotateAnimation(90,180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(200);
                rotateAnimation.setFillAfter(true);
                ib.startAnimation(rotateAnimation);
            }
        });
        //  初始化Fragment
        initFragment();
    }

    private void initFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fr_content,new ContentFragment(),CONTENTFRAGMENT);
        ft.replace(R.id.fy_leftmenu,new LeftMenuFragment(),LEFTMENUFRAGMENT);
        ft.commit();
    }
}
