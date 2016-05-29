package com.example.xwf.zhbj.pageleftmenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.LeftMenuBasePager;
import com.example.xwf.zhbj.bean.PhotosBean;
import com.example.xwf.zhbj.utils.CacheUtils;
import com.example.xwf.zhbj.utils.ConstantUtils;
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
 * Created by Hsia on 16/5/23.
 * E-mail: xiaweifeng@live.cn
 * //TODO:组图页面
 */
public class PhotosPager extends LeftMenuBasePager {
    @Bind(R.id.ll_photos)
    ListView llPhotos;
    @Bind(R.id.gv_photo)
    GridView gvPhoto;
    public String url;
    public List<PhotosBean.DataBean.NewsBean> photoNews;
    public BitmapUtils bitmapUtils;

    public PhotosPager(Activity activity) {
        super(activity);
    }
    private boolean isSingleColumns = true; // 当前是否是单列数据, 默认是单列
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_photos, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        bitmapUtils = new BitmapUtils(mActivity);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.ARGB_4444);

        url = ConstantUtils.PHOTOSURL;

        //先从缓存中读取数据
        String photosJsonData = CacheUtils.getString(mActivity, url, null);
        if (photosJsonData != null) {
            parserJsonData(photosJsonData);
        }
        //从网络上请求数据
        getDataFromNet();
    }

    private void getDataFromNet() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                //请求成功本地存一份json，解析json
//                Log.d(TAG, "onSuccess: "+responseInfo.result);
                String result = (String) responseInfo.result;
                parserJsonData(result);
                CacheUtils.putString(mActivity, url, result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.e(TAG, "onFailure: 组图数据请求失败。" + e);
            }
        });
    }

    private void parserJsonData(String result) {
        PhotosBean photosBean = parserJson(result);
        //主要取得数据图片的url+title
        photoNews = photosBean.getData().getNews();

        //设置listview数据
        PhotoAdapter photoAdapter = new PhotoAdapter();
        llPhotos.setAdapter(photoAdapter);
    }

    private PhotosBean parserJson(String result) {
        Gson gson = new Gson();
        PhotosBean photosBean = gson.fromJson(result, PhotosBean.class);
        return photosBean;
    }

    /**
     * 用于切换视图的方法
     * @param ib
     */
    public void switchView(ImageButton ib) {
        //more没有切换是listv
        if (isSingleColumns) {
            llPhotos.setVisibility(View.VISIBLE);
            gvPhoto.setVisibility(View.GONE);
            llPhotos.setAdapter(new PhotoAdapter());
            isSingleColumns =false;
            ib.setImageResource(R.mipmap.icon_pic_list_type);
        }else {
            llPhotos.setVisibility(View.GONE);
            gvPhoto.setVisibility(View.VISIBLE);
            gvPhoto.setAdapter(new PhotoAdapter());
            isSingleColumns =true;
            ib.setImageResource(R.mipmap.icon_pic_grid_type);
        }

    }

    class PhotoAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return photoNews.size();
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
                viewHolder =  new ViewHolder();
                convertView = View.inflate(mActivity, R.layout.photos_list_item, null);
                viewHolder.ivPhotoPhotos = (ImageView) convertView.findViewById(R.id.iv_photo_photos);
                viewHolder.tvTitlePhotos = (TextView) convertView.findViewById(R.id.tv_title_photos);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            PhotosBean.DataBean.NewsBean newsBean = photoNews.get(position);
            viewHolder.tvTitlePhotos.setText(newsBean.getTitle());
            //设置默认图片
            viewHolder.ivPhotoPhotos.setImageResource(R.drawable.default_bg);
            bitmapUtils.display(viewHolder.ivPhotoPhotos,newsBean.getListimage());
            return convertView;
        }

        public class ViewHolder {
//            @Bind(R.id.iv_photo_photos)
            public ImageView ivPhotoPhotos;
//            @Bind(R.id.tv_title_photos)
            public TextView tvTitlePhotos;

        }
    }
}
