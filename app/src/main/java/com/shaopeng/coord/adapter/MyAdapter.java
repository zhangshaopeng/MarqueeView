package com.shaopeng.coord.adapter;

import android.graphics.Color;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaopeng.coord.bean.DemoBean;
import com.shaopeng.coord.helper.OnDragVHListener;
import com.shaopeng.coord.helper.OnItemMoveListener;
import com.shaopeng.marqueeview.R;


import java.util.List;

/**
 * Description:
 * Company:
 * Author:Zhangshaopeng
 * Email :1377785991@qq.com
 * Data:2018/5/25
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener, OnItemMoveListener {

    private String color[] = {"#1ffdef", "#FddF40", "#cF5081","#1d34ef", "#ccFF40", "#Fccf81"};

    private List<DemoBean> list;

    public MyAdapter(List<DemoBean> list) {
        this.list = list;
    }

    private RecyclerViewOnItemClickListener onItemClickListener;
    private RecyclerViewOnItemLongClickListener onItemLongClickListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener {

        private TextView title, content;
        private View root;
        private CardView cardView;

        public ViewHolder(View root) {
            super(root);
            this.root = root;
            title = (TextView) root.findViewById(R.id.tv_title);
            content = (TextView) root.findViewById(R.id.tv_content);
            cardView = (CardView) root.findViewById(R.id.card);
        }

        @Override
        public void onItemSelected() {
            //开始推拽排序,可以设置被拖拽Item的背景
            //itemView.setBackgroundResource();
        }

        @Override
        public void onItemFinish() {
            //推拽排序结束,也可以设置被拖拽Item的背景
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //绑定视图管理者
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        //设置Tag
        holder.content.setText(list.get(position).getContent());
        holder.cardView.setCardBackgroundColor(Color.parseColor(color[position % color.length]));
        holder.root.setTag(position+"");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_success, parent, false);
        ViewHolder vh = new ViewHolder(root);
        //为Item设置点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);
        return vh;
    }

    /**
     * 拖拽排序相关
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        DemoBean cellData = list.get(fromPosition);
        list.remove(fromPosition);
        list.add(toPosition, cellData);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            String tag = (String) v.getTag();
            onItemClickListener.onItemClickListener(v, (Integer) Integer.valueOf(tag));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        String tag = (String) v.getTag();
        return onItemLongClickListener != null && onItemLongClickListener.onItemLongClickListener(v, (Integer) Integer.valueOf(tag));
    }

    /*设置点击事件*/
    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /*设置长按事件*/
    public void setOnItemLongClickListener(RecyclerViewOnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface RecyclerViewOnItemClickListener {

        void onItemClickListener(View view, int position);

    }

    public interface RecyclerViewOnItemLongClickListener {

        boolean onItemLongClickListener(View view, int position);

    }
}
