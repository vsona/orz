package net.vsona.orz.utils;

import android.content.Context;

import net.vsona.orz.OrzApplication;

/**
 * Created by roy on 16/5/24.
 */
public class AppContextUtils {
    public static Context get(){
        return OrzApplication.sContext;
    }
}
