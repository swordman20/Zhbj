package com.example.xwf.zhbj;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.xwf.zhbj.fragment.ContentFragment;
import com.example.xwf.zhbj.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by Hsia on 16/5/13.
 * E-mail: xiaweifeng@live.cn
 * //TODO:主页面
 */
public class MainActivity extends SlidingFragmentActivity {
    public static final String CONTENTFRAGMENT = "ContentFragment";//用于标识fragment的id
    private static final String LEFTMENUFRAGMENT = "LeftMenuFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果是手机有虚拟按键，将自动不被覆盖。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_main);
        //获取slidingMean管理器
        setBehindContentView(R.layout.left_mean);
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置边界模式
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置拖拽模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主屏幕留在界面的宽度
        slidingMenu.setBehindOffset(600);
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
