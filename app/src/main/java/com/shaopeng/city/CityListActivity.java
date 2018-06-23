package com.shaopeng.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shaopeng.marqueeview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityListActivity extends AppCompatActivity implements SideBar.OnTouchingLetterChangedListener, AdapterView.OnItemClickListener {

    private SideBar sideBar;
    private ListView sortListView;
    private TextView dialog;
    private EditText mClearEditText;
    private LinearLayout titleLayout;
    private TextView title;
    private TextView tvNoBrands;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<CityBean> SourceDateList = new ArrayList<>();
    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private View view;
    private String city = "北京市";
    private String flag = "city";
    private String nowCity = "北京市";
    private CityListAdapter adapter;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initView();
        setViews();
        tvTitle.setText("当前城市--" + city);


    }

    private void setViews() {
        view = LayoutInflater.from(this).inflate(R.layout.city_headview, null, true);
        sortListView.addHeaderView(view);
        //当前定位城市
        TextView positionCity = (TextView) view.findViewById(R.id.position_city);
        positionCity.setText(nowCity);
        positionCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityListActivity.this.finish();
            }
        });
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar.setTextView(dialog);
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(this);
        sortListView.setOnItemClickListener(this);
        // 填充数据
        SourceDateList = filledData(getResources().getStringArray(R.array.city));
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new CityListAdapter(this, SourceDateList, null, flag);
        sortListView.setAdapter(adapter);
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int section = getSectionForPosition(firstVisibleItem);
                int nextSection = getSectionForPosition(firstVisibleItem + 1);
                int nextSecPosition = getPositionForSection(+nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(SourceDateList.get(
                            getPositionForSection(section)).getSortLetters());
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });

        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 这个时候不需要挤压效果 就把他隐藏掉
                titleLayout.setVisibility(View.GONE);
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<CityBean> filterDateList = new ArrayList<CityBean>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
            view.setVisibility(View.VISIBLE);
            tvNoBrands.setVisibility(View.GONE);
        } else {
            filterDateList.clear();
            for (CityBean sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList, new int[]{});
        if (filterDateList.size() == 0) {
            tvNoBrands.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return SourceDateList.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 填充数据
     *
     * @param date
     * @return
     */
    private List<CityBean> filledData(String[] date) {
        List<CityBean> mSortList = new ArrayList<CityBean>();

        for (int i = 0; i < date.length; i++) {
            CityBean sortModel = new CityBean();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;
    }

    private void initView() {
        sortListView = findViewById(R.id.country_lvcountry);
        sideBar = findViewById(R.id.sidrbar);
        dialog = findViewById(R.id.dialog);
        mClearEditText = findViewById(R.id.filter_edit);
        titleLayout = findViewById(R.id.title_layout);
        title = findViewById(R.id.title_layout_catalog);
        tvNoBrands = findViewById(R.id.title_layout_no_brand);
        tvTitle = findViewById(R.id.tv_title_bar_title);
        findViewById(R.id.iv_title_bar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleLayout.setVisibility(View.GONE);
    }

    @Override
    public void onTouchingLetterChanged(String s) {
        /**
         *该字母首次出现的位置
         */
        int position = adapter.getPositionForSection(s.charAt(0));
        if (position != -1) {
            sortListView.setSelection(position + 1);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
        if (position > 0) {
            // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
            String cityName = ((CityBean) adapter.getItem(position - 1)).getName();
            Intent intent = new Intent();
            intent.putExtra("cityName", cityName);
            CityListActivity.this.setResult(RESULT_OK, intent);
            CityListActivity.this.finish();
        }
    }
}
