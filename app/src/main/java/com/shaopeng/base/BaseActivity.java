package com.shaopeng.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shaopeng.marqueeview.R;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/25
 */
public class BaseActivity extends AppCompatActivity {

    protected TitleBarView titleBarView;
    protected FrameLayout flChildContent;
    private TitleBarView titleBarView1;


    private void assignViews() {
        titleBarView = (TitleBarView) findViewById(R.id.title_bar_view);
        flChildContent = (FrameLayout) findViewById(R.id.fl_child_content);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_title_bar);
        //沉浸式状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //一进入页面，输入法总是隐藏的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        assignViews();

        if (showTitleBar()) {
            setTranslucentStatus(titleBarView);
        } else {
            titleBarView.setVisibility(View.GONE);
        }

    }

    protected void setTitleText(String title) {
        titleBarView.setTitleText(title);
    }

    protected void setTitleText(@StringRes int title) {
        titleBarView.setTitleText(title);
    }

    protected void setRightText(String str) {
        titleBarView.setRightText(str);
    }

    protected TextView getRightText() {
        return titleBarView.getTvRight();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        this.setContentView(view);
    }

    /**
     * 是否显示titleBar
     *
     * @return
     */
    protected boolean showTitleBar() {
        return true;
    }

    @Override
    public void setContentView(View view) {
        flChildContent.removeAllViews();
        flChildContent.addView(view);
    }

    /**
     * 设置透明状态栏并对某个view 拉伸，仅支持4.4及以上
     *
     * @param view
     */
    public void setTranslucentStatus(View view) {
        Log.d("aaa",Build.VERSION.SDK_INT+"---------------"+Build.VERSION_CODES.KITKAT+"----------------------"+Build.VERSION_CODES.LOLLIPOP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            view.setPadding(view.getPaddingLeft(),
                    DeviceInfo.getStatusHeight(this) + view.getPaddingTop(),
                    view.getPaddingRight(),
                    view.getPaddingBottom());
        }
    }

    /***
     * 设置状态栏透明
     */
    public final void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 颜色
     */
    protected final void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(color);
        }
    }

    /**
     * 隐藏状态栏
     */
    protected final void hideStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }
}
