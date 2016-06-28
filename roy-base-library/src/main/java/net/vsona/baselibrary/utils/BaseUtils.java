package net.vsona.baselibrary.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import net.vsona.baselibrary.R;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Common utility class.
 */
public class BaseUtils {
    /**
     * To record the user click time.
     */
    private static long sLastClickTime = 0L;

    /**
     * Get device unique identify.
     *
     * @param context
     */
    public static String getDeviceId(Context context) {
        String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        // If android_id is null , then use serial number.
        if (androidId == null) {
            androidId = Build.SERIAL;
        }
        return androidId;
    }

    public static String getAndroidReleaseVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Get channel from META-INF directory.
     *
     * @return the channel name if success,otherwise return "none"
     */
    public static String getChannel(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("META-INF/channel")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] split = ret.split("_");
        if (split != null && split.length >= 2) {
            return ret.substring(split[0].length() + 1);

        } else {
            return "none";
        }
    }

    public static String getMetaData(Context context, String metaName) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String msg = appInfo.metaData.getString(metaName);
        return msg;
    }

    /**
     * Determine if the device is a tablet (i.e. it has a large screen).
     *
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * Hide input method.
     *
     * @param activity
     */
    public static void hideInputMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showInputMethod(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            imm.showSoftInput(currentFocus, InputMethodManager.SHOW_IMPLICIT);
        }

//        if (imm != null) {
//            imm.viewClicked(this);
//        }
//        if (imm != null && getShowSoftInputOnFocus()) {
//            imm.showSoftInput(this, 0);
//        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device
     * density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need
     *                to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on
     * device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent
     * pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    /**
     * @return return screen width px unit.
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * @return return screen height px unit.
     */
    public static int getScreenHeigth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getScreenHeightExceptStatusBar(Context context) {
        return getScreenHeigth(context) - getStatusBarHeight(context);
    }

    public static int getScreenMinWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return Math.min(size.x, size.y);
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
        /**
         * Rect frame = new Rect();

         getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

         int statusBarHeight = frame.top;
         */
    }

    /**
     * Get ActionBar size.
     */
    public static int getActionBarSize(Context context) {
        int actionBarSize = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarSize = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }
        return actionBarSize;
    }

    /**
     */
    public static int[] getDrawableIdsArray(Context context, @ArrayRes int drawableArraysId) {
        TypedArray ta = context.getResources().obtainTypedArray(drawableArraysId);
        int count = ta.length();
        int[] ids = new int[count];
        for (int i = 0; i < count; i++) {
            ids[i] = ta.getResourceId(i, 0);
        }
        ta.recycle();
        return ids;
    }

    /**
     * Checked the network connect status.
     */
    public static boolean isNetworkConnected(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check WIFI connection.
     *
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && ConnectivityManager.TYPE_WIFI == info.getType());
    }

    /**
     * @param text
     * @param color
     * @param start
     * @param end
     * @return
     */
    public static SpannableString highLight(CharSequence text, int color, int start, int end) {
        SpannableString span = new SpannableString(text);
        span.setSpan(new ForegroundColorSpan(color), start, end,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    public static SpannableString stylePartTextBold(CharSequence text, int start, int end) {
        SpannableString span = new SpannableString(text);
        span.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return span;
    }

    /**
     * Get application version number.
     *
     * @param context
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static String getAppVersionName(Context context) {
        String versionName = "1.0";
        try {
            PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static boolean isEmailLegal(String email) {
        if (TextUtils.isEmpty(email)) return false;

        final String REGEXP = "^([a-z0-9\\-_.+]+)@([a-z0-9\\-]+[.][a-z0-9\\-.]+)$";
        Pattern p = Pattern.compile(REGEXP);
        Matcher m = p.matcher(email.toLowerCase());
        return m.matches();
    }

    public static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isArrayEmpty(Object[] array) {
        return array == null || array.length == 0;
    }


    /**
     * Judge whether the user is deliberately fast clicking. Because if the user
     * fast click a button or list item, application will process too much
     * unnecessary transaction in short time and may cause to some unexpected
     * exception.
     * <p/>
     * <br/>
     * <br/>
     * The definition of fast click is: the user click a view,button or list
     * item in 800 millisecond.
     *
     * @return
     */
    public static boolean isFastClick() {
        return isFastClick(800);
    }

    public static boolean isDoubleClick() {
        return isDoubleClick(800);
    }

    public static boolean isDoubleClick(long space) {
        long time = System.currentTimeMillis();
        if (time - sLastClickTime < space) {
            sLastClickTime = 0;
            return true;
        }
        sLastClickTime = time;
        return false;

    }

    public static boolean isFastClick(long space) {
        long time = System.currentTimeMillis();
        if (time - sLastClickTime < space) {
            return true;
        }
        sLastClickTime = time;
        return false;

    }

    public static void toastNetworkNotAvailable(Context context) {
        Toast.makeText(context, R.string.network_not_available, Toast.LENGTH_SHORT).show();
    }

    public static void toastMessage(Context context, int res) {
        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
    }

    public static void toastMessage(Context context, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length);
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(0xFF & bytes[i]);
            if (temp.length() < 2)
                sb.append(0);
            sb.append(temp.toUpperCase()).append(" ");
        }
        return sb.toString();
    }

    public static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.valueOf(bytes[i])).append(" ");
        }
        return sb.toString();
    }

    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public static boolean isChina() {
        return Locale.getDefault().getLanguage().equals("zh");
    }


    public static String getStringOfRes(Context context, @StringRes int res) {
        return context.getResources().getString(res);
    }

    public static int getIntOfRes(Context context, @IntegerRes int res) {
        return context.getResources().getInteger(res);
    }

    public static int getDpOfRes(Context context, @DimenRes int res) {
        return context.getResources().getDimensionPixelOffset(res);
    }

    public static int getColorOfRes(Context context, @ColorRes int res) {
        return context.getResources().getColor(res);
    }

    public static int getSpOfRes(Context context, @DimenRes int res) {
        return context.getResources().getDimensionPixelSize(res);
    }


    public static void showTipDialog(Activity activity, String title, String dismiss) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setNegativeButton(dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    public static void showTipDialogAndSystemExit(Activity activity, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle(title)
                .setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    public static String creatJsonString(Object object) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object); // 用Gson方式 把object 保存为 json字符串
        return jsonString;
    }


    public static void setSize(View view, int width, int height) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(width, height);
        } else {
            lp.height = height;
            lp.width = width;
        }
        view.setLayoutParams(lp);
    }


    public static void updateFile(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 判断SDK版本是不是4.4或者高于4.4
            String[] paths = new String[]{Environment.getExternalStorageDirectory().toString()};
            MediaScannerConnection.scanFile(context, paths, null, null);
        } else {
            Intent intent;
            intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
            intent.setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            Log.v("sd", "directory changed, send broadcast:" + intent.toString());
            context.sendBroadcast(intent);
        }

    }

    /**
     * Get navigation bar height.
     */
    public static int getNavigationBarHeight(Context context) {
        if (hasNavigationBar(context)) {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        }
        return 0;
    }

    /**
     * Check the device whether has soft navigation bar
     */
    public static boolean hasNavigationBar(Context context) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            return resources.getBoolean(id);
        } else {    // Check for keys
            boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return !hasMenuKey && !hasBackKey;
        }
    }

    public static boolean matches(String content, String format) {
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(content);
        boolean matches = matcher.matches();
        return matches;
    }

}
