package com.example.xwf.zhbj.pagercontent.pagercontenttab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
public class TabPager extends LeftMenuBasePager implements ViewPager.OnPageChangeListener {
    @Bind(R.id.hvp)
    HorizontalViewPager hvp;
    @Bind(R.id.list_news)
    ListView listNews;
    @Bind(R.id.tv_top_news_des)
    TextView tvTopNewsDes;
    @Bind(R.id.ll_point)
    LinearLayout llPoint;
    private NewsCenterBean.NewsCenterMenu.NewsMenuTab newsMenuTab;//顶部新闻对应的页签
    public String url;//顶部新闻标签页的url
    public List<TopNewsBean.DataBean.TopnewsBean> topNewsList;//顶部新闻数据的集合
    public TopNewsBean.DataBean.TopnewsBean topNews; //顶部轮播的数据
    private int firstDescription; //第一个图片文字和点的描述
    public List<TopNewsBean.DataBean.NewsBean> newsItem;//listView展示的条目
    public BitmapUtils bitmapUtils;


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
        url = ConstantUtils.CONNECTURL + newsMenuTab.getUrl();

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
                Log.d(TAG, newsMenuTab.getTitle() + "数据请求成功" + responseInfo.result);
                //数据请求成功向本地保存一份
                CacheUtils.putString(mActivity, url, String.valueOf(responseInfo.result));
                //解析json
                resolutionJson((String) responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d(TAG, newsMenuTab.getTitle() + "数据请求失败");
            }
        });
    }

    /**
     * 解析json数据
     *
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
        hvp.setOnPageChangeListener(this);

        //初始化文字和点

        llPoint.removeAllViews();//因为访问网络读取缓存这个方法会被执行2此，所以需要要移除以前的view
        View view = null;
        for (int i = 0; i < topNewsList.size(); i++) {
            view = new View(mActivity);
            view.setBackgroundResource(R.drawable.point_seclect);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            if (i != 0) {
                params.leftMargin = 15;
            }
            view.setEnabled(false);
            view.setLayoutParams(params);
            llPoint.addView(view);
        }
        //初始化第一个点和文字
        firstDescription = 0;
        tvTopNewsDes.setText(topNewsList.get(firstDescription).getTitle());
        llPoint.getChildAt(firstDescription).setEnabled(true);


        //初始化listview数据
        newsItem = topNewsBean.getData().getNews();
        ListNewsAdapter listNewsAdapter = new ListNewsAdapter();
        listNews.setAdapter(listNewsAdapter);
    }


    private TopNewsBean topNewsJson(String result) {
        Gson gson = new Gson();
        TopNewsBean topNewsBean;
        topNewsBean = gson.fromJson(result, TopNewsBean.class);
        return topNewsBean;
    }

    //Viewpager的滑动事件监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置点的切换
        tvTopNewsDes.setText(topNewsList.get(position).getTitle());
        llPoint.getChildAt(firstDescription).setEnabled(false);
        llPoint.getChildAt(position).setEnabled(true);
        firstDescription = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class TopNewsTabAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return topNewsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            //设置默认图片和背景拉伸
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(R.drawable.default_bg);
            bitmapUtils = new BitmapUtils(mActivity);
            // 配置默认图片的像素单位
            bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.ARGB_4444);
            //topimage的网络地址
            topNews = topNewsList.get(position);

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

    class ListNewsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsItem.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.listnews_item, null);
                viewHolder = new ViewHolder(convertView);
//                viewHolder.ivListNews = (ImageView) convertView.findViewById(R.id.iv_list_news);
//                viewHolder.tvListNews = (TextView) convertView.findViewById(R.id.tv_list_news);
//                viewHolder.tvListDate = (TextView) convertView.findViewById(R.id.tv_list_date);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            TopNewsBean.DataBean.NewsBean newsBean = newsItem.get(position);
            viewHolder.tvListNews.setText(newsBean.getTitle());
            viewHolder.tvListDate.setText(newsBean.getPubdate());
            //设置默认图片
            viewHolder.ivListNews.setBackgroundResource(R.drawable.listnews_default_bg);
            bitmapUtils.display(viewHolder.ivListNews,newsBean.getListimage());
            return convertView;
        }

         class ViewHolder {
            @Bind(R.id.iv_list_news)
            public ImageView ivListNews;
            @Bind(R.id.tv_list_news)
            public TextView tvListNews;
             @Bind(R.id.tv_list_date)
             public TextView tvListDate;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
