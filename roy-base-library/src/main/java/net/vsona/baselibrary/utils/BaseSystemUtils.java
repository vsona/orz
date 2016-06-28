package net.vsona.baselibrary.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

public class BaseSystemUtils {
    /**
     * 判断应用是否已经启动
     * @param context 一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName){
        ActivityManager activityManager =
                (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for(int i = 0; i < processInfos.size(); i++){
            if(processInfos.get(i).processName.equals(packageName)){
                Log.i("NotificationLaunch",
                        String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }

    public static String getAppVersionName(Context context, String s) {
        PackageManager manager = context.getPackageManager();

        try {
            PackageInfo packageInfo = manager.getPackageInfo(s, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static int compareVersion(String s1, String s2) {
        if(s1 == null && s2 == null) {
            return 0;
        } else if(s1 != null && s2 == null) {
            return 1;
        } else if(s1 == null && s2 != null) {
            return -1;
        } else {
            String[] var2 = s1.split("\\.");
            String[] var3 = s2.split("\\.");

            try {
                int var4;
                for(var4 = 0; var4 < var2.length && var4 < var3.length; ++var4) {
                    int var5 = Integer.parseInt(var2[var4]);
                    int var6 = Integer.parseInt(var3[var4]);
                    if(var5 < var6) {
                        return -1;
                    }

                    if(var5 > var6) {
                        return 1;
                    }
                }

                return var2.length > var4?1:(var3.length > var4?-1:0);
            } catch (NumberFormatException var7) {
                return s1.compareTo(s2);
            }
        }
    }

    public static boolean isActivityExist(Context context, Intent intent) {
        if(context != null && intent != null) {
            PackageManager var2 = context.getPackageManager();
            List var3 = var2.queryIntentActivities(intent, 0);
            return var3.size() != 0;
        } else {
            return false;
        }
    }

    public static String getAppName(Context context) {
        ApplicationInfo var1 = context.getApplicationInfo();
        return var1.loadLabel(context.getPackageManager()).toString();
    }

}
