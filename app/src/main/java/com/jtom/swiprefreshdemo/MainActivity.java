package com.jtom.swiprefreshdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RefreshLayout mRefreshLayout;
    private List<Bean> mBeanList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    private String[] addrs = {"北京", "幸福街", "美丽街", "太白街", "好看街"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.aty_smart_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.aty_recycler_view);
        mAdapter = new MainAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new BezierRadarHeader(context);
            }
        });
        initData();
        initRefresh();

    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            Bean bean = new Bean();
            bean.setName("我叫第" + i + "名");
            int pos = i % 5;
            bean.setAddr(addrs[pos]);
            mBeanList.add(i, bean);
        }
        mAdapter.setBeanList(mBeanList);
        mRecyclerView.setAdapter(mAdapter);


    }

    private void initRefresh() {
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
                for (int i = 0; i < 10; i++) {
                    Bean bean = new Bean();
                    bean.setName("我是加载的第" + i + "名");
                    int pos = i % 5;
                    bean.setAddr(addrs[pos]);
                    mBeanList.add(bean);
                }
                mAdapter.setBeanList(mBeanList);

            }
        });
    }
}
