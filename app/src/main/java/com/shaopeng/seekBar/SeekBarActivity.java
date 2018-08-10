package com.shaopeng.seekBar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.shaopeng.base.BaseActivity;
import com.shaopeng.marqueeview.R;

public class SeekBarActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView tv;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleText("滑块登录验证");
        setContentView(R.layout.activity_seek_bar);
        tv = (TextView) findViewById(R.id.tv);
        seekBar = (SeekBar) findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(this);
    }

    /**
     * seekBar进度变化时回调
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getProgress() == seekBar.getMax()) {
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.WHITE);
            tv.setText("完成验证");
            seekBar.setThumb(getResources().getDrawable(R.drawable.bbb));
        } else {
            tv.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * seekBar开始触摸时回调
     *
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * seekBar停止触摸时回调
     *
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != seekBar.getMax()) {
            seekBar.setProgress(0);
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.GRAY);
            seekBar.setThumb(getResources().getDrawable(R.drawable.cc));
            tv.setText("请按住滑块，拖动到最右边");
        }
    }
}

