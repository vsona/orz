package net.vsona.baselibrary.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;

public class ColorUtils {

    /**
     * @param context
     * @param res
     * @return
     */
    public static String color2hex(Context context, int res) {
        int color = context.getResources().getColor(res);
        String s = Integer.toHexString(color);
        if (s.length() == 8) {
            s = s.substring(2);
        }
        return s;
    }

    /**
     * 16进制颜色的字符串 转换成 颜色
     * textViewMsg.setTextColor(ColorUtils.hex2color("#ff0092"));
     *
     * @param color
     * @return
     */
    public static int hex2color(String color) {
        if (TextUtils.isEmpty(color)) {
            color = "#ffffff";
        }

        int result;
        if (color.charAt(0) != '#') {
            color = "#" + color;
        }
        result = Color.parseColor(color);
        return result;
    }
}
