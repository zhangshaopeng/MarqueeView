package com.shaopeng.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shaopeng.marqueeview.R;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/26
 */
public class TitleBarView extends RelativeLayout implements View.OnClickListener {

    private ImageView ivBack;
    private TextView tvLeft;
    private TextView tvTitle;
    private TextView tvRight;

    public TitleBarView(Context context) {
        super(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivBack = (ImageView) findViewById(R.id.iv_title_bar_back);
        tvLeft = (TextView) findViewById(R.id.tv_title_bar_left);
        tvTitle = (TextView) findViewById(R.id.tv_title_bar_title);
        tvRight = (TextView) findViewById(R.id.tv_title_bar_right);

        ivBack.setOnClickListener(this);

        tvLeft.setVisibility(GONE);
        tvRight.setVisibility(GONE);
    }



    public void setLeftText(String leftText) {
        tvLeft.setText(leftText);
    }

    public void setLeftText(@StringRes int resId) {
        tvLeft.setText(resId);
    }


    public void setTitleText(String titleText) {
        tvTitle.setText(titleText);
    }

    public void setTitleText(@StringRes int resId) {
        tvTitle.setText(resId);
    }


    public void setRightText(String rightText) {
        tvRight.setText(rightText);
    }

    public void setRightText(@StringRes int resId) {
        tvRight.setText(resId);
    }

    public void setRightTextSize(int size) {
        tvRight.setTextColor(size);
    }

    public ImageView getIvBack() {
        return ivBack;
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_title_bar_back) {
            if (getContext() instanceof Activity) {
                Activity a = (Activity) getContext();
                a.finish();
            }

        }
    }
}
