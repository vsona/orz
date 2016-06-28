package net.vsona.orz.utils;

import android.content.Context;

import net.vsona.baselibrary.utils.BaseStartActivityUtils;
import net.vsona.orz.ui.MainActivity;

/**
 * Created by roy on 16/6/28.
 */
public class StartActivityUtils extends BaseStartActivityUtils{

    public static void forMainActivity(Context context) {
        forActivity(context, MainActivity.class);
    }
}
