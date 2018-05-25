package com.shaopeng.zoom.utils;

import android.graphics.Color;

import java.util.Random;

public class CommonUtil {
    /**
     * 颜色转换
     */
    public static int generateBeautifulColor() {
        Random random = new Random();
        int red = random.nextInt(120) + 30;//30-150
        int green = random.nextInt(120) + 30;//30-150
        int blue = random.nextInt(120) + 30;//30-150
        //使用r,g,b混合生成一种新的颜色
        return Color.rgb(red, green, blue);
    }
}
