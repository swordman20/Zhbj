package com.example.xwf.zhbj.fragment;

import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xwf.zhbj.MainActivity;
import com.example.xwf.zhbj.R;
import com.example.xwf.zhbj.base.BaseFragment;
import com.example.xwf.zhbj.base.BasePager;
import com.example.xwf.zhbj.bean.NewsCenterBean;
import com.example.xwf.zhbj.pagercontent.NewsCenterPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.List;

/**
 * Created by Hsia on 16/5/21.
 * E-mail: xiaweifeng@live.cn
 * //TODO:左侧侧拉栏的fragment
 */
public class LeftMenuFragment extends BaseFragment {
    private static final String TAG = "Hsia";
    private TextView tv_left_item;
    private int currentItemEnable;
    public List<NewsCenterBean.NewsCenterMenu> leftMenList;
    public ListView listView;
    public LeftMenuAdapter leftMenuAdapter;
    public MainActivity leftMenuActivity;

    @Override
    public View initView() {
        listView = new ListView(mActivity);
        listView.setPadding(0,250,0,0);
        return listView;
    }

    /**
     * 从NewsCenterPager那里获取到左侧菜单数据
     * @param newsCenterMenuList
     */
    public void getLeftMenuData(List<NewsCenterBean.NewsCenterMenu> newsCenterMenuList) {
        leftMenList = newsCenterMenuList;
        leftMenuAdapter = new LeftMenuAdapter();
        //设置默认标签
        currentItemEnable = 0;
        switchNewsCenterPager(0);

        listView.setAdapter(leftMenuAdapter);
        listView.setOnItemClickListener(new listViewOnItemClickListener());
    }

    class LeftMenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return leftMenList.size();
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
            View view = null;
            if (convertView == null) {
                view = View.inflate(mActivity, R.layout.left_menu_item, null);
            }else {
                view = convertView;
            }
            tv_left_item = (TextView) view.findViewById(R.id.tv_left_item);
            NewsCenterBean.NewsCenterMenu newsCenterMenu = leftMenList.get(position);
            tv_left_item.setText(newsCenterMenu.getTitle());
            //设置第一个item为true
            if (position == currentItemEnable) {
                tv_left_item.setEnabled(true);
                //其他的为false
            }else {
                tv_left_item.setEnabled(false);
            }
            return view;
        }
    }

    class listViewOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //当我点击item条目的时候吧，postion位置赋值给currentItemEnable
            currentItemEnable = position;
            //刷新adapter，把当前条目设置为true
            leftMenuAdapter.notifyDataSetChanged();

            //点击item条目时关闭侧拉栏
            leftMenuActivity = (MainActivity) LeftMenuFragment.this.mActivity;
            leftMenuActivity.getSlidingMenu().toggle();
            
            //当侧拉栏关闭后，切换新闻中心的自页面
            // TODO: 16/5/23，
            //根据postion位置切换页面
            switchNewsCenterPager(position);
        }
    }

    /**
     * 切换新闻中心的详情页面（就需要拿到新闻中心页面的对象）
     * @param position
     */
    private void switchNewsCenterPager(int position) {
        ContentFragment cf = (ContentFragment) ((MainActivity) mActivity).getFragmentForByTag(((MainActivity) mActivity).CONTENTFRAGMENT);
        //集合中的第一个页面就是新闻中心页面
        NewsCenterPager ncp = (NewsCenterPager) cf.basePagerList.get(1);
        ncp.switchNewsCenterChildrenPager(position);
    }
}
