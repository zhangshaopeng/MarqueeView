package com.shaopeng.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shao.utilslibrary.utils.DensityUtils;
import com.shaopeng.marqueeview.R;

/**
 * Describe：
 * auther：  zhangshaopeng
 * Emile：   1377785991@qq.com
 * Date：    2018/6/22
 */

public abstract class DialogUtils {

    private Dialog dialog;
    private TextView title;
    private TextView title2;
    private Button sure;

    public DialogUtils(Activity activity) {
        if (dialog == null) {
            getDialog(activity);
        }
    }

    private Dialog getDialog(Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.my_dialog,null);
        dialog = new Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view, new LinearLayout.LayoutParams(DensityUtils.dip2px(265),DensityUtils.dip2px(135)));
        //设置点击外围弹出框不消失
        dialog.setCanceledOnTouchOutside(false);

        title=(TextView)view.findViewById(R.id.title);
        title2=(TextView)view.findViewById(R.id.title2);
        sure = (Button) view.findViewById(R.id.sure);
        sure=(Button)view.findViewById(R.id.sure);

        Window dialogWindow = dialog.getWindow();
        android.view.WindowManager.LayoutParams lp = dialogWindow
                .getAttributes();
        dialogWindow.setGravity(Gravity.TOP); // 设置生效
        // lp.x = 266; // 新位置X坐标
        lp.y = DensityUtils.dip2px( 220); // 新位置Y坐标
//        lp.width = 300; // 宽度
//        lp.height = 300; // 高度
//        lp.alpha = 0.4f; // 透明度
        dialogWindow.setAttributes(lp);

        sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                SureClick();
            }
        });
        return dialog;
    }


    public abstract void SureClick();

    public void setText(String s1, String s2,String s3) {
        title.setText(s1);
        title2.setText(s2);
        sure.setText(s3);
    }

    public void setColor(int one, int two,int three) {
        title.setTextColor(one);
        title2.setTextColor(two);
        sure.setTextColor(three);
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
