package com.example.xwf.zhbj.pagercontent.pagercontenttab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xwf.zhbj.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by Hsia on 16/5/27.
 * E-mail: xiaweifeng@live.cn
 * //TODO:文件描述
 */
public class NewsDetailUI extends Activity implements View.OnClickListener {
    private String TAG = "Hsia";
    public ProgressBar mPbLoading;
    public WebView mWebView;
    public ImageButton mIbFinish;
    public ImageButton mIbTextSize;
    public ImageButton mIbShare;
    public TextView mTvTitle;
    public WebSettings settings;
    private int tempCurrent  = -1;
    private int currentSelectTextSize = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果是手机有虚拟按键，将自动不被覆盖。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_newsdetail);
        mPbLoading = ((ProgressBar) findViewById(R.id.pb_loading));
        mWebView = ((WebView) findViewById(R.id.wv_content));
        mIbFinish = ((ImageButton) findViewById(R.id.ib_finish));
        mIbTextSize = ((ImageButton) findViewById(R.id.ib_textsize));
        mIbShare = (ImageButton) findViewById(R.id.ib_share);
        mTvTitle = ((TextView) findViewById(R.id.tv_title));
        initData();
        mIbFinish.setOnClickListener(this);
        mIbTextSize.setOnClickListener(this);
        mIbShare.setOnClickListener(this);


    }

    private void initData() {
        mIbFinish.setVisibility(View.VISIBLE);
        mIbTextSize.setVisibility(View.VISIBLE);
        mIbShare.setVisibility(View.VISIBLE);
        mTvTitle.setMaxWidth(600);
        mTvTitle.setMaxLines(1);
        mTvTitle.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
        mTvTitle.setText(getIntent().getStringExtra("title"));
        String url = getIntent().getStringExtra("url");
        settings = mWebView.getSettings();
//        settings.setJavaScriptEnabled(true); // 启用javascript脚本
//        settings.setBuiltInZoomControls(true); // 启用界面上放大和缩小按钮
//        settings.setUseWideViewPort(true); // 启用双击放大, 双击缩小功能
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                mPbLoading.setVisibility(View.GONE);
            }
        });

        mWebView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_finish:
                finish();
                break;
            case R.id.ib_textsize:
                setTextSize();
                break;
            case R.id.ib_share:
                mobShare();
                break;
        }
    }

    /**
     * 设置webView的字体大小
     */
    private void setTextSize() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("选择字体大小");
        String[] item = new String[]{"超大号字体","大号字体","正常字体","小号字体","超小号字体"};
        ab.setSingleChoiceItems(item, currentSelectTextSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempCurrent = which;
            }
        });
        ab.setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentSelectTextSize = tempCurrent;
                changeTextSize();
            }
        });
        ab.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ab.show();
    }

    /**
     * 切换字体大小的方法
     */
    private void changeTextSize() {
        switch (currentSelectTextSize){
            case 0:
                settings.setTextSize(WebSettings.TextSize.LARGEST);
                break;
            case 1:
                settings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case 2:
                settings.setTextSize(WebSettings.TextSize.NORMAL);
                break;
            case 3:
                settings.setTextSize(WebSettings.TextSize.SMALLER);
                break;
            case 4:
                settings.setTextSize(WebSettings.TextSize.SMALLEST);
                break;
            default:
                break;
        }

    }

    /**
     * 一键社会化分享
     */
    private void mobShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("智慧北京");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
