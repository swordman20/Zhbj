package com.example.xwf.zhbj.pagercontent.pagercontenttab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.LeftMenuBasePager;
import com.example.xwf.zhbj.bean.NewsCenterBean;
import com.example.xwf.zhbj.bean.TopNewsBean;
import com.example.xwf.zhbj.utils.CacheUtils;
import com.example.xwf.zhbj.utils.ConstantUtils;
import com.example.xwf.zhbj.view.CustomLIstView;
import com.example.xwf.zhbj.view.HorizontalViewPager;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Hsia on 16/5/24.
 * E-mail: xiaweifeng@live.cn
 * //TODO:新闻中心页面的tab页面
 */
public class TabPager extends LeftMenuBasePager implements ViewPager.OnPageChangeListener{
    @ViewInject(R.id.hvp)
    HorizontalViewPager hvp;
    @Bind(R.id.list_news)
    CustomLIstView listNews;
    @ViewInject(R.id.tv_top_news_des)
    TextView tvTopNewsDes;
    @ViewInject(R.id.ll_point)
    LinearLayout llPoint;
    private NewsCenterBean.NewsCenterMenu.NewsMenuTab newsMenuTab;//顶部新闻对应的页签
    public String url;//顶部新闻标签页的url
    public List<TopNewsBean.DataBean.TopnewsBean> topNewsList;//顶部新闻数据的集合
    public TopNewsBean.DataBean.TopnewsBean topNews; //顶部轮播的数据
    private int firstDescription; //第一个图片文字和点的描述
    public List<TopNewsBean.DataBean.NewsBean> newsItem;//listView展示的条目
    public BitmapUtils bitmapUtils;
    public RelativeLayout inflateViewPager;
    private boolean isRefresh = false;
    public String moreUrl;//加载更多的url
    public boolean isLoadMore = false;
    public ListNewsAdapter listNewsAdapter;
    // 已读新闻的id数组
    private final String READABLE_NEWS_ID_ARRAY_KEY = "readable_news_id_array_key";
    public TopNewsBean.DataBean.NewsBean newsBeanItem;
    public MyHandle myHandle;


    public TabPager(Activity activity, NewsCenterBean.NewsCenterMenu.NewsMenuTab newsMenuTab) {
        super(activity);
        this.newsMenuTab = newsMenuTab;
    }

    @Override
    public View initView() {
        View topNewsView = View.inflate(mActivity, R.layout.top_tab_pager, null);
        ButterKnife.bind(this, topNewsView);
        inflateViewPager = (RelativeLayout) View.inflate(mActivity, R.layout.top_news_viewpager, null);
        ViewUtils.inject(this, inflateViewPager);
        listNews.setSeccondHeadView(inflateViewPager);
        listNews.setOnRefreshListener(new CustomLIstView.OnRefreshListener() {
            @Override
            public void onPullDownRefresh() {
                isRefresh = true;
                // 访问网络请求数据
                getDataFromNet();
            }
            //当加载更多调用
            @Override
            public void onLoadingMore() {
                if (moreUrl == null) {
                    Toast.makeText(mActivity, "已经没有更多数据了", Toast.LENGTH_SHORT).show();
                    listNews.onRefreshFinish(false);
                }else {
                    getMoreDataNet();
                }
            }
        });

        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int newsposition = position - 2;
                newsBeanItem = newsItem.get(newsposition);
                // 先把已读新闻的id取出来
                String readableIDArray = CacheUtils.getString(mActivity, READABLE_NEWS_ID_ARRAY_KEY, "");
                if(!readableIDArray.contains(newsBeanItem.getId()+"")) {
                    String currentID = null;
                    if(TextUtils.isEmpty(readableIDArray)) {
                        currentID = newsBeanItem.getId() + ", ";
                    } else {
                        currentID = readableIDArray + newsBeanItem.getId() + ", ";
                    }
                    // 把这条新闻的id存储起来
                    CacheUtils.putString(mActivity, READABLE_NEWS_ID_ARRAY_KEY, currentID);
                }

                listNewsAdapter.notifyDataSetChanged();

                Intent intent = new Intent(mActivity, NewsDetailUI.class);
                intent.putExtra("url", newsBeanItem.getUrl());
                intent.putExtra("title",newsBeanItem.getTitle());
                mActivity.startActivity(intent);
            }
        });
        return topNewsView;
    }

    /**
     * 加载更多的时候调用此方法
     */
    private void getMoreDataNet() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, moreUrl, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                isLoadMore = true;
                //解析json
                resolutionJson((String) responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mActivity, "请求网络失败", Toast.LENGTH_SHORT).show();
            }
        });
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

                if (isRefresh) {
                    isRefresh = false;
                    listNews.onRefreshFinish(true);
                }

                Log.d(TAG, newsMenuTab.getTitle() + "数据请求成功" + responseInfo.result);
                //数据请求成功向本地保存一份
                CacheUtils.putString(mActivity, url, String.valueOf(responseInfo.result));
                //解析json
                resolutionJson((String) responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                if (isRefresh) {
                    listNews.onRefreshFinish(false);
                }
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
        TopNewsBean topNewsBean = topNewsJson(result);
        if (!isLoadMore){
            //把解析json封装成一个方法这样看起来代码没那么乱
            topNewsList = topNewsBean.getData().getTopnews();

            moreUrl = topNewsBean.getData().getMore();
            if (TextUtils.isEmpty(moreUrl)) {
                moreUrl = null;
            }else {
                moreUrl = ConstantUtils.CONNECTURL+moreUrl;
            }
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
            listNewsAdapter = new ListNewsAdapter();
            listNews.setAdapter(listNewsAdapter);


            // TODO: 16/5/28    给Viewpager设置自动滑动
            //因为该方法会执行2次，所以需要清空一次
            if (myHandle==null){
                myHandle = new MyHandle();
            }else {
                myHandle.removeCallbacksAndMessages(null);
            }
            myHandle.postDelayed(new MyRunnable(),4000);
        }else {
            isLoadMore = false;
            List<TopNewsBean.DataBean.NewsBean> moreNewsItem = topNewsBean.getData().getNews();
            newsItem.addAll(moreNewsItem);
            listNewsAdapter.notifyDataSetChanged();
        }
    }

    class MyHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //用于处理消息
            int newCurrentItem = (hvp.getCurrentItem() + 1)%topNewsList.size();
            hvp.setCurrentItem(newCurrentItem);
            //消息处理完在重新发送，类似递归
            myHandle.postDelayed(new MyRunnable(),4000);
        }
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            //发一条空的消息
            myHandle.sendEmptyMessage(0);
        }
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
            
            //给imageview设置事件

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
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            TopNewsBean.DataBean.NewsBean newsBean = newsItem.get(position);
            viewHolder.tvListNews.setText(newsBean.getTitle());
            viewHolder.tvListDate.setText(newsBean.getPubdate());

            // 判断当前是否是已读的新闻
            String readableIDArray = CacheUtils.getString(mActivity, READABLE_NEWS_ID_ARRAY_KEY, null);
            // TODO: 16/5/27
            if(!TextUtils.isEmpty(readableIDArray)
                    && readableIDArray.contains(newsBean.getId()+"")) {
                viewHolder.tvListNews.setTextColor(Color.GRAY);
            } else {
                viewHolder.tvListNews.setTextColor(Color.BLACK);
            }

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
