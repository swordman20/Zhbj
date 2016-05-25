package com.example.xwf.zhbj.pagercontent;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.xwf.zhbj.MainActivity;
import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.BasePager;
import com.example.xwf.zhbj.base.LeftMenuBasePager;
import com.example.xwf.zhbj.bean.NewsCenterBean;
import com.example.xwf.zhbj.fragment.LeftMenuFragment;
import com.example.xwf.zhbj.pageleftmenu.HudongPager;
import com.example.xwf.zhbj.pageleftmenu.NewsPager;
import com.example.xwf.zhbj.pageleftmenu.PhotosPager;
import com.example.xwf.zhbj.pageleftmenu.ZhuanTiPager;
import com.example.xwf.zhbj.utils.CacheUtils;
import com.example.xwf.zhbj.utils.ConstantUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:主要实现新闻中心页面
 */
public class NewsCenterPager extends BasePager{
    public List<LeftMenuBasePager> leftMenuBasePagerlist;
    public List<NewsCenterBean.NewsCenterMenu> leftMenuList;

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "新闻中心 加载数据了 ");
        mTextViewTitle.setText("新闻中心");
        View defaultView = View.inflate(mActivity, R.layout.newscenterinitview, null);
        ImageView fencheView = (ImageView) defaultView.findViewById(R.id.iv_fenche);
        rotateImage(fencheView);
        mFrameLayoutContent.addView(defaultView);
        mImageButtionLeftMenu.setVisibility(View.VISIBLE);
        //获取数据之前先读取缓存数据
        String getCacheString = CacheUtils.getString(mActivity, ConstantUtils.NEWSCENTERURL, null);
        if (getCacheString != null) {
            resolutionJson(getCacheString);
        }
        //从网络上获取数据
        getFromDataNet();


    }

    public void rotateImage(ImageView fencheView) {
        RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setInterpolator(new LinearInterpolator());
        ra.setDuration(300);
        ra.setRepeatCount(Integer.MAX_VALUE);
        ra.setFillAfter(true);
        fencheView.startAnimation(ra);
    }


    private void getFromDataNet() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, ConstantUtils.NEWSCENTERURL, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d(TAG, "onSuccess: "+responseInfo.result);
                //保存本地缓存
                boolean putString = CacheUtils.putString(mActivity, ConstantUtils.NEWSCENTERURL, (String) responseInfo.result);
                if (putString) {
                    Log.d(TAG, "onSuccess: "+"数据保存成功");
                }
                //解析Json数据
                resolutionJson((String)responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    /**
     * 用于解析json数据
     * @param result
     */
    private void resolutionJson(String result) {
        Gson gson = new Gson();
        NewsCenterBean newsCenterBean = gson.fromJson(result, NewsCenterBean.class);
        leftMenuList = newsCenterBean.getData();

        //当左侧菜单获取到数据，并设置后，需要初始化对应的页签
        leftMenuBasePagerlist = new ArrayList<>();
        //向集合中添加数据
        NewsCenterBean.NewsCenterMenu newsCenterMenu = leftMenuList.get(0);
        leftMenuBasePagerlist.add(new NewsPager(mActivity,newsCenterMenu));
        leftMenuBasePagerlist.add(new ZhuanTiPager(mActivity));
        leftMenuBasePagerlist.add(new PhotosPager(mActivity));
        leftMenuBasePagerlist.add(new HudongPager(mActivity));


        //把获取到的左侧菜单数据传给LeftMenuFragment(先发送数据)
        sendLeftMenuFormlist(leftMenuList);

    }

    /**
     * 提供一个方法给左侧菜单切换页面
     * @param position
     */
    public void switchNewsCenterChildrenPager(int position) {
        //改变title
        String title = leftMenuList.get(position).getTitle();
        mTextViewTitle.setText(title);
        LeftMenuBasePager leftMenuBasePager = leftMenuBasePagerlist.get(position);
        View view = leftMenuBasePager.initView();
        //在添加view之前移除已经存在的view
        mFrameLayoutContent.removeAllViews();
        mFrameLayoutContent.addView(view);
        //初始化数据
        leftMenuBasePager.initData();
    }

    /**
     * 把解析结果发送给左侧侧拉栏
     * @param leftMenuList
     */
    private void sendLeftMenuFormlist(List<NewsCenterBean.NewsCenterMenu> leftMenuList) {
        //获取到LeftMenuFragment的对象
        MainActivity newsCenterPagerActivity = (MainActivity) this.mActivity;
        LeftMenuFragment leftMenuFragment = (LeftMenuFragment) newsCenterPagerActivity.getFragmentForByTag(newsCenterPagerActivity.LEFTMENUFRAGMENT);
        leftMenuFragment.getLeftMenuData(leftMenuList);
    }

}
