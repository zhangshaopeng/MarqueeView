package com.shaopeng.marqueeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }
}
