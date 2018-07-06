package com.shaopeng.alipay;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shaopeng.marqueeview.R;

/**
 * Description:仿支付宝头部
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/25
 */
public class AliPay extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout abl_bar;
    private View v_title_big_mask;
    private View v_toolbar_small_mask;
    private View v_toolbar_search_mask;
    private View include_toolbar_search;
    private View include_toolbar_small;
    private int mMaskColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_pay);
        abl_bar = findViewById(R.id.abl_bar);
        //顶部搜索布局
        include_toolbar_search = findViewById(R.id.include_toolbar_search);
        //扫一扫 付一付 聊一聊 咻一咻 的小图标布局
        include_toolbar_small = findViewById(R.id.include_toolbar_small);

        //顶部搜索布局遮罩
        v_toolbar_search_mask = findViewById(R.id.v_toolbar_search_mask);
        //扫一扫 付一付 聊一聊 咻一咻 的大图标布局遮罩
        v_title_big_mask = findViewById(R.id.v_title_big_mask);
        //扫一扫 付一付 聊一聊 咻一咻 的大图标布局遮罩
        v_toolbar_small_mask = findViewById(R.id.v_toolbar_small_mask);
        //背景颜色
        mMaskColor = getResources().getColor(R.color.blue_dark);


        abl_bar.addOnOffsetChangedListener(this);


    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //720*1080手机 verticalOffset取值范围[0-200]px
        int absVerticalOffset = Math.abs(verticalOffset);//AppBarLayout竖直方向偏移距离px
        int totalScrollRange = appBarLayout.getTotalScrollRange();//AppBarLayout总的距离px
        //背景颜色转化成RGB的渐变色
        int argb = Color.argb(absVerticalOffset, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        int argbDouble = Color.argb(absVerticalOffset * 2, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑一半距离后小图标应该由渐变到全透明
        int title_small_offset = (200 - absVerticalOffset) < 0 ? 0 : 200 - absVerticalOffset;
        int title_small_argb = Color.argb(title_small_offset * 2, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        //appBarLayout上滑不到一半距离
        if (absVerticalOffset <= totalScrollRange / 2) {
            include_toolbar_search.setVisibility(View.VISIBLE);
            include_toolbar_small.setVisibility(View.GONE);
            //为了和下面的大图标渐变区分,乘以2倍渐变
            v_toolbar_search_mask.setBackgroundColor(argbDouble);
        } else {
            include_toolbar_search.setVisibility(View.GONE);
            include_toolbar_small.setVisibility(View.VISIBLE);
            //appBarLayout上滑一半距离后小图标应该由渐变到全透明
            v_toolbar_small_mask.setBackgroundColor(title_small_argb);

        }
        //上滑时遮罩由全透明到半透明
        v_title_big_mask.setBackgroundColor(argb);
    }
}
