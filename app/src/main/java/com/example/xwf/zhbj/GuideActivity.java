package com.example.xwf.zhbj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.xwf.zhbj.utils.CacheUtils;

import java.util.ArrayList;

/**
 * Created by Hsia on 16/5/13.
 * E-mail: xiaweifeng@live.cn
 * //TODO:引导页
 */
public class GuideActivity extends AppCompatActivity {

    private static final String TAG = "Hsia";
    private int[] resImageIDS;
    private ArrayList<ImageView> imageViews;
    private Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();

    }

    private void init() {
        ViewPager pager = (ViewPager) findViewById(R.id.vp);
        mStart = (Button) findViewById(R.id.btn_start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheUtils.putSharedPreferences(getApplicationContext(), SplashActivity.ISOPER, true);
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        initData();
        pager.setAdapter(new MyPagerAdapter());
        //vp的滑动监听
        MyOnPageChangeListener myOnPageChangeListener = new MyOnPageChangeListener();
        pager.setOnPageChangeListener(myOnPageChangeListener);
    }

    private void initData() {
        resImageIDS = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        imageViews = new ArrayList<>();
        ImageView imageView;
        for (int i = 0; i < resImageIDS.length; i++) {
            imageView = new ImageView(getApplicationContext());
            imageView.setBackgroundResource(resImageIDS[i]);
            imageViews.add(imageView);
        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            container.addView(iv);
            return iv;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //页面在滑动的过程中调用
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        //当滑动页面被选中后调用
        @Override
        public void onPageSelected(int position) {
            //判断vp滑动到最后一个页面后显示button
            int endPage = imageViews.size() - 1;
            if (endPage == position) {
                mStart.setVisibility(View.VISIBLE);
            }else {
                mStart.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
