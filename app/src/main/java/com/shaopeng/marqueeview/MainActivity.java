package com.shaopeng.marqueeview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shaopeng.base.BaseActivity;
import com.shaopeng.coord.CoordActivity;
import com.shaopeng.dialog.DialogActivity;
import com.shaopeng.zoom.ZoomActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/25
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleText("控件大全");
        final List<String> list = new ArrayList<>();
        list.add("热点新闻1");
        list.add("人文趣事2");
        list.add("科幻动画节目3");
        list.add("影视频道大咖来袭4");
        list.add("文艺青年节到来之际5");

        final MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        /**
         * 数据适配
         */
        final MarqueeViewAdapter marqueeViewAdapter = new MarqueeViewAdapter<String>(list) {
            @Override
            public View getView(MarqueeView parent, final int position, String o) {

                final View view = LayoutInflater.from(getApplication()).inflate(R.layout.item, marqueeView, false);

                TextView textView = (TextView) view.findViewById(R.id.textView);

                textView.setText(list.get(position));

                return view;
            }
        };

        marqueeView.setAdapter(marqueeViewAdapter);
        /**
         * 点击事件
         */
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(MarqueeView parent, View view, int position) {

                Log.d("----------", "----" + list.size() + "---" + position + "--" + list.get(position));
                Toast.makeText(MainActivity.this, "size:" + list.size() + "position:" + position + "", Toast.LENGTH_SHORT).show();

            }
        });


        Button button = (Button) findViewById(R.id.button);
        /**
         * 选项增删
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.size() > 0) {
                    list.remove(0);
                    Log.d("---------", "------list.size() ==" + list.size());
                    Toast.makeText(MainActivity.this, "------list.size() ==" + list.size(), Toast.LENGTH_SHORT).show();
                }

                marqueeViewAdapter.notifyDataSetChanged();

            }
        });

        /**
         * 富文本textView
         */
        TextView textView = (TextView) findViewById(R.id.protocal);
        textView.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spanableInfo = new SpannableString("这是sdfdfsfdsdsfdf一个测试" + ": " + "点dfasfafsfadsfasfdfaf击我");
        spanableInfo.setSpan(new Clickable(MainActivity.this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击成功....", Toast.LENGTH_SHORT).show();
            }
        }), 8, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new Clickable(MainActivity.this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "....", Toast.LENGTH_SHORT).show();
            }
        }), 12, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanableInfo);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "点击成功....", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 滑动头部实例
     *
     * @param view
     */
    public void bt(View view) {
        startActivity(new Intent(MainActivity.this, CoordActivity.class));
    }

    /**
     * 伸缩图片实例
     *
     * @param view
     */
    public void qq(View view) {
        startActivity(new Intent(MainActivity.this, ZoomActivity.class));
    }

    public void timer(View view) {
       startActivity(new Intent(MainActivity.this, DialogActivity.class));
    }
}


/**
 * 对点击事件进行重写，修改字体颜色
 */
class Clickable extends ClickableSpan {
    private Context context;
    private final View.OnClickListener mListener;

    public Clickable(Context context, View.OnClickListener l) {
        mListener = l;
        this.context = context;
    }

    /**
     * 重写父类点击事件
     */
    @Override
    public void onClick(View v) {
        mListener.onClick(v);
    }

    /**
     * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
     */
    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(context.getResources().getColor(R.color.colorPrimary));
    }
}