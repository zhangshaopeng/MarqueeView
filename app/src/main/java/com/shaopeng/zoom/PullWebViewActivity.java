package com.shaopeng.zoom;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.shaopeng.base.BaseActivity;
import com.shaopeng.marqueeview.R;
import com.shaopeng.zoom.view.PullZoomView;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/25
 */
public class PullWebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_web_view);

        setTitleText("PullWebViewActivity");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("https://github.com/zhangshaopeng/MarqueeView");

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
}
