package com.example.xwf.zhbj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xwf.zhbj.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hsia on 16/5/26.
 * E-mail: xiaweifeng@live.cn
 * //TODO:自定义下拉刷新的View
 */
public class CustomLIstView extends ListView {

    private static final String TAG = "CustomLIstView";
    public int measuredHeight;
    public View headView;
    @Bind(R.id.iv_refresh)
    ImageView ivRefresh;
    @Bind(R.id.pb_bar)
    ProgressBar pbBar;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;
    @Bind(R.id.tv_last_data)
    TextView tvLastData;
    private int downY = -1;//按下初始化赋值
    private View inflate;

    private final int DOWNREFRESH = 0;//下拉刷新状态
    private final int RELEASEREFRESH = 1;//释放刷新状态
    private final int INREFRESH = 2;//正在刷新
    private int currentState = DOWNREFRESH;
    private RotateAnimation upAnima;
    private RotateAnimation downAnima;

    private OnRefreshListener mOnRefreshListener; // 用户的回调事件
    public View footView;
    public int footViewHeight;
    private boolean isLoadingMore = false; // 是否正在加载更多中, 默认为: false

    public CustomLIstView(Context context) {
        super(context);
        initHeadView();
        initFooterView();
    }

    public CustomLIstView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeadView();
        initFooterView();
    }

    public CustomLIstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeadView();
        initFooterView();
    }

    /**
     * 添加角布局
     */
    private void initFooterView() {
        footView = View.inflate(getContext(), R.layout.listview_footerview, null);
        footView.measure(0,0);
        footViewHeight = footView.getMeasuredHeight();
        this.addFooterView(footView);
        footView.setPadding(0, -footViewHeight, 0, 0);
        this.setOnScrollListener(new OnScrollListener() {
            //当页面改变是调用
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当滚动停止时, 或者惯性滑动时, ListView最后一个显示的条目索引为getCount -1;
                if(scrollState == SCROLL_STATE_IDLE ||
                        scrollState == SCROLL_STATE_FLING) {
                    if(getLastVisiblePosition() == getCount() -1 && !isLoadingMore) {
                        System.out.println("滚动到底部了");

                        isLoadingMore  = true;

                        footView.setPadding(0, 0, 0, 0);
                        // 让ListView滚动到底部
                        setSelection(getCount());

                        // 调用使用者的回调事件
                        if(mOnRefreshListener != null) {
                            mOnRefreshListener.onLoadingMore();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initHeadView() {
        headView = View.inflate(getContext(), R.layout.refresh_headview, null);
        ButterKnife.bind(this, headView);
        this.addHeaderView(headView);
        //默认隐藏头布局
        headView.measure(0, 0);
        measuredHeight = headView.getMeasuredHeight();
        headView.setPadding(0, -measuredHeight, 0, 0);
        initAnimation();
    }

    /**
     * 初始化头布局的动画
     */
    private void initAnimation() {
        upAnima = new RotateAnimation(
                0, -180,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        upAnima.setFillAfter(true);
        upAnima.setDuration(500);

        downAnima = new RotateAnimation(
                -180, -360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        downAnima.setFillAfter(true);
        downAnima.setDuration(500);
    }


    public void setSeccondHeadView(View inflate) {
        this.inflate = inflate;
        this.addHeaderView(inflate);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (downY == -1){
                    downY = (int) ev.getY();
                }
//                downY = (int) ev.getY();
            break;
            case MotionEvent.ACTION_MOVE:
                if (downY == -1){
                    downY = (int) ev.getY();
                }
                int moveY = (int) ev.getY();
                int diffY = moveY - downY;
                // 判断当前是否正在刷新中
                if(currentState == RELEASEREFRESH) {
                    // 当前正在刷新中, 不执行下拉, 直接跳出
                    break;
                }

                //如果是从上向下滑动，并且是第一个头布局，才进行下拉操作
                boolean isDisplay = isDisplaySecondHeaderView();
                if (diffY>0&&isDisplay) {
                    int piddingTop = -measuredHeight+diffY;
                    if (piddingTop >= 0 && currentState != RELEASEREFRESH){
                        Log.i(TAG, "进入释放刷新状态");
                        currentState = RELEASEREFRESH;
                        refreshState();
                    }else if (piddingTop<0  && currentState != DOWNREFRESH){
                        Log.i(TAG, "进入下拉刷新");
                        currentState = DOWNREFRESH;
                        refreshState();
                    }
                    headView.setPadding(0,piddingTop,0,0);
                    return true;
                }

                break;
            case MotionEvent.ACTION_UP:
                downY = -1;
                if(currentState == DOWNREFRESH) {
                    // 当前是下拉刷新, 把头布局的隐藏
                    headView.setPadding(0, -measuredHeight, 0, 0);
                } else if(currentState == RELEASEREFRESH) {
                    // 当前是释放刷新, 进入到正在刷新中的状态
                    currentState = INREFRESH;
                    refreshState();

                    headView.setPadding(0, 0, 0, 0);

//                     调用用户的回调事件, 刷新数据
                    if(mOnRefreshListener != null) {
                        mOnRefreshListener.onPullDownRefresh();
                    }
                }


            break;
        }
        return super.onTouchEvent(ev);
    }
    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
    }

    private void refreshState() {
        switch (currentState) {
            case DOWNREFRESH: //下拉刷新
                ivRefresh.startAnimation(downAnima);
                tvRefresh.setText("下拉刷新");
                break;
            case RELEASEREFRESH: //释放刷新
                ivRefresh.startAnimation(upAnima);
                tvRefresh.setText("松开刷新");
                break;
            case INREFRESH: //刷新中
                ivRefresh.clearAnimation();
                ivRefresh.setVisibility(View.INVISIBLE);
                pbBar.setVisibility(View.VISIBLE);
                tvRefresh.setText("正在刷新中..");
                break;
        }
    }

    /**
     * 判断第二头布局是否完全显示
     * @return
     */
    public boolean isDisplaySecondHeaderView() {
        int[] location = new int[2];
        this.getLocationOnScreen(location);
        int listViewLocationY = location[1];
        inflate.getLocationOnScreen(location);
        int inflateLocationY = location[1];

        return listViewLocationY<=inflateLocationY;
    }
    /**
     * 当刷新完数据之后, 调用此方法. 把头布局隐藏
     */
    public void onRefreshFinish(boolean isSuccess) {
        if(isLoadingMore) {
            // 加载更多中
            footView.setPadding(0, -footViewHeight, 0, 0);
            isLoadingMore = false;
        }
        headView.setPadding(0, -measuredHeight, 0, 0);
        currentState = DOWNREFRESH;
        pbBar.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.VISIBLE);
        tvRefresh.setText("下拉刷新");

        if(isSuccess) {
            // 最后刷新时间
            tvLastData.setText("最后刷新时间: " + getCurrentTime());
        }
    }
    public interface OnRefreshListener {

        /**
         * 当下拉刷新时触发此方法
         */
        public void onPullDownRefresh();

        public void onLoadingMore();
    }
    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}


