package com.shaopeng.zoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.shaopeng.base.BaseActivity;
import com.shaopeng.coord.adapter.MyAdapter;
import com.shaopeng.coord.bean.DemoBean;
import com.shaopeng.coord.helper.ItemDragHelperCallback;
import com.shaopeng.marqueeview.R;
import com.shaopeng.zoom.view.PullZoomView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/25
 */
public class PullRecyclerViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_recycler_view);

        setTitleText("PullRecyclerViewActivity");

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        List<DemoBean> list = initData();
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);
            // 长按拖拽打开
            ItemDragHelperCallback callback = new ItemDragHelperCallback() {
                @Override
                public boolean isLongPressDragEnabled() {
                    return true;
                }
            };
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(mRecyclerView);
            //设置adapter
            MyAdapter mAdapter = new MyAdapter(list);
            mRecyclerView.setAdapter(mAdapter);
            //设置RecyclerView的每一项的点击事件
            mAdapter.setRecyclerViewOnItemClickListener(new MyAdapter.RecyclerViewOnItemClickListener() {
                @Override
                public void onItemClickListener(View view, int position) {
                    Toast.makeText(PullRecyclerViewActivity.this, "点击了：" + position, Toast.LENGTH_SHORT).show();
                }
            });
            //设置RecyclerView的每一项的长按事件
            mAdapter.setOnItemLongClickListener(new MyAdapter.RecyclerViewOnItemLongClickListener() {
                @Override
                public boolean onItemLongClickListener(View view, int position) {
                    Snackbar.make(view, "长按了：" + position, Snackbar.LENGTH_SHORT).show();
                    return true;
                }
            });


        }



        Intent intent = getIntent();
        float sensitive = intent.getFloatExtra("sensitive", 1.5f);
        int zoomTime = intent.getIntExtra("zoomTime", 500);
        boolean isParallax = intent.getBooleanExtra("isParallax", true);
        boolean isZoomEnable = intent.getBooleanExtra("isZoomEnable", true);
        PullZoomView pzv = (PullZoomView) findViewById(R.id.pzv);
        pzv.setIsParallax(isParallax);
        pzv.setIsZoomEnable(isZoomEnable);
        pzv.setSensitive(sensitive);
        pzv.setZoomTime(zoomTime);
        pzv.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onScroll   t:" + t + "  oldt:" + oldt);
            }

            @Override
            public void onHeaderScroll(int currentY, int maxY) {
                System.out.println("onHeaderScroll   currentY:" + currentY + "  maxY:" + maxY);
            }

            @Override
            public void onContentScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onContentScroll   t:" + t + "  oldt:" + oldt);
            }
        });
        pzv.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onPullZoom(int originHeight, int currentHeight) {
                System.out.println("onPullZoom  originHeight:" + originHeight + "  currentHeight:" + currentHeight);
            }

            @Override
            public void onZoomFinish() {
                System.out.println("onZoomFinish");
            }
        });
    }
    private List<DemoBean> initData() {
        List<DemoBean> list = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            DemoBean cellData = new DemoBean("总条目的第 +" + i+"个", "总的条目数位：" + i);
            list.add(cellData);
        }
        return list;
    }
}

