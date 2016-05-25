package com.example.xwf.zhbj.pagercontent.pagercontenttab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.LeftMenuBasePager;
import com.example.xwf.zhbj.bean.NewsCenterBean;
import com.example.xwf.zhbj.bean.TopNewsBean;
import com.example.xwf.zhbj.utils.CacheUtils;
import com.example.xwf.zhbj.utils.ConstantUtils;
import com.example.xwf.zhbj.view.HorizontalViewPager;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hsia on 16/5/24.
 * E-mail: xiaweifeng@live.cn
 * //TODO:新闻中心页面的tab页面
 */
public class TabPager extends LeftMenuBasePager {
    @Bind(R.id.hvp)
    HorizontalViewPager hvp;
    @Bind(R.id.list_news)
    ListView listNews;
    private NewsCenterBean.NewsCenterMenu.NewsMenuTab newsMenuTab;//顶部新闻对应的页签
    public String url;//顶部新闻标签页的url
    public List<TopNewsBean.DataBean.TopnewsBean> topNewsList;//顶部新闻数据的集合


    public TabPager(Activity activity, NewsCenterBean.NewsCenterMenu.NewsMenuTab newsMenuTab) {
        super(activity);
        this.newsMenuTab = newsMenuTab;
    }

    @Override
    public View initView() {
        View topNewsView = View.inflate(mActivity, R.layout.top_tab_pager, null);
        ButterKnife.bind(this, topNewsView);
        return topNewsView;
    }

    @Override
    public void initData() {
        super.initData();
        url = ConstantUtils.CONNECTURL+newsMenuTab.getUrl();

        //请求网络之前先去本地取数据
        String topNewsJson = CacheUtils.getString(mActivity, url, null);
        if (topNewsJson != null) {
            resolutionJson(topNewsJson);
        }

        // 访问网络请求数据
        getDataFromNet();
    }

    private void getDataFromNet() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d(TAG, newsMenuTab.getTitle()+"数据请求成功"+responseInfo.result);
                //数据请求成功向本地保存一份
                CacheUtils.putString(mActivity,url, String.valueOf(responseInfo.result));
                //解析json
                resolutionJson((String)responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d(TAG, newsMenuTab.getTitle()+"数据请求失败");
            }
        });
    }

    /**
     * 解析json数据
     * @param result
     */
    private void resolutionJson(String result) {
        //把解析json封装成一个方法这样看起来代码没那么乱
        TopNewsBean topNewsBean = topNewsJson(result);
        topNewsList = topNewsBean.getData().getTopnews();
        //初始化顶部新闻的Viewpager数据


        //初始化Viewpager数据
        TopNewsTabAdapter topNewsTabAdapter = new TopNewsTabAdapter();
//        给ViewPager设置数据
        hvp.setAdapter(topNewsTabAdapter);

    }


    private TopNewsBean topNewsJson(String result) {
        Gson gson = new Gson();
        TopNewsBean topNewsBean;topNewsBean = gson.fromJson(result, TopNewsBean.class);
        return topNewsBean;
    }

    class TopNewsTabAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return topNewsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            //设置默认图片和背景拉伸
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(R.drawable.default_bg);
            BitmapUtils bitmapUtils = new BitmapUtils(mActivity);
            // 配置默认图片的像素单位
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.ARGB_4444);
            //topimage的网络地址
            TopNewsBean.DataBean.TopnewsBean topNews = topNewsList.get(position);
            /**
             * container 下面的uri参数请求下来的图片, 设置给container来展示.
             * uri 图片的请求地址
             */
            bitmapUtils.display(imageView, topNews.getTopimage());
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
