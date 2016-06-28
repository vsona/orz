package net.vsona.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yesdoll on 16/4/9.
 */
public class BaseStartActivityUtils {

    public interface IBuilder {
        void with(Intent intent);
    }

    protected static void forActivity(Context context, Class<? extends Activity> clazz, IBuilder builder) {
        Intent intent = new Intent(context, clazz);
        if (builder != null) {
            builder.with(intent);
        }
        context.startActivity(intent);
    }

    protected static void forActivity(Context context, Class<? extends Activity> clazz) {
        forActivity(context, clazz, null);
    }
}
