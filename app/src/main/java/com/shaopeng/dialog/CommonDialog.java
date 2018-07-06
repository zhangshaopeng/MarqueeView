package com.shaopeng.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shao.utilslibrary.utils.DensityUtils;
import com.shaopeng.marqueeview.R;

/**
 * Describe：弹框工具
 * auther：  zhangshaopeng
 * Emile：   1377785991@qq.com
 * Date：    2018/6/22
 */

public abstract class CommonDialog {
    protected Dialog dialog;
    private TextView title;
    private Button sure;
    private Button cancle;

    public CommonDialog(Activity activity) {
        if (dialog == null) {
            getDialog(activity);
        }
    }

    private Dialog getDialog(Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.common_dialog, null);
        dialog = new Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view, new LinearLayout.LayoutParams(DensityUtils.dip2px(245), DensityUtils.dip2px(188)));
        title = (TextView) view.findViewById(R.id.title);
        sure = (Button) view.findViewById(R.id.sure);
        cancle = (Button) view.findViewById(R.id.cancle);
//   TODO：可根据需求自行配置
//        Window dialogWindow = dialog.getWindow();
//        android.view.WindowManager.LayoutParams lp = dialogWindow
//                .getAttributes();
//        dialogWindow.setGravity(Gravity.TOP); // 设置位置
//        dialogWindow.setWindowAnimations(R.style.dialogStyle);//设置动画
        // lp.x = 255; // 新位置X坐标
//        lp.y = DensityUtils.dip2px(120); // 新位置Y坐标
//        lp.width = 200; // 宽度
//        lp.height = 200; // 高度
//        lp.alpha = 0.5f; // 透明度
//        dialogWindow.setAttributes(lp);

        sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SureClick();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                CancleClick();
            }
        });
        return dialog;
    }

    public abstract void SureClick();

    public abstract void CancleClick();

    //内容显示
    public void setText(String s1, String s2, String s3) {
        title.setText(s1);
        sure.setText(s2);
        cancle.setText(s3);
    }

    //字体颜色
    public void setColor(Context context, int titleColor, int rightColor, int leftColor) {
        title.setTextColor(context.getResources().getColor(titleColor));
        sure.setTextColor(context.getResources().getColor(leftColor));
        cancle.setTextColor(context.getResources().getColor(rightColor));
    }

    //字体大小
    public void setSize(int titleSize, int rightSize, int leftSize) {
        title.setTextSize(titleSize);
        sure.setTextSize(leftSize);
        cancle.setTextSize(rightSize);
    }

    public void showDialog() {
        dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
