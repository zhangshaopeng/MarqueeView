package com.shaopeng.coord;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.shaopeng.coord.adapter.MyAdapter;
import com.shaopeng.coord.bean.DemoBean;
import com.shaopeng.coord.helper.ItemDragHelperCallback;
import com.shaopeng.marqueeview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:沉浸式状态栏，RecyclerView的使用
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/24
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class CoordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coord);
        //沉浸式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //给页面设置工具栏
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbar != null) {
            //设置隐藏图片时候ToolBar的颜色
            collapsingToolbar.setContentScrimColor(Color.parseColor("#11B7F3"));
            //设置工具栏标题
            collapsingToolbar.setTitle("小汽车跑跑跑");
        }
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
                    Toast.makeText(CoordActivity.this, "点击了：" + position, Toast.LENGTH_SHORT).show();
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                //点击了返回箭头
                break;
            case R.id.about:
                new AlertDialog.Builder(this).setMessage("作者:Mr.peng").show();
                break;
        }
        return super.onOptionsItemSelected(item);
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
