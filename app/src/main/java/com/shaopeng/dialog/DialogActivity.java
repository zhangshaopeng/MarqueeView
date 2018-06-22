package com.shaopeng.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shao.utilslibrary.utils.ToastUtils;
import com.shaopeng.marqueeview.R;

/**
 * Describe：弹框示范类
 * auther：  zhangshaopeng
 * Emile：   1377785991@qq.com
 * Date：    2018/6/22
 */

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
    }

    public void btt(View view) {
        DialogUtils dialog = new DialogUtils(this) {
            @Override
            public void SureClick() {
                ToastUtils.show("SureClick");
            }
        };
        dialog.showDialog();
    }

    public void bttt(View view) {
        CommonDialog commonDialog = new CommonDialog(this) {
            @Override
            public void SureClick() {
                ToastUtils.show("SureClick");
            }

            @Override
            public void CancleClick() {
                ToastUtils.show("CancleClick");
            }
        };
        //s1：内容  s2：右边按钮  s3：左边按钮
        commonDialog.setText("text1","确定","取消");
        commonDialog.showDialog();

    }
}
