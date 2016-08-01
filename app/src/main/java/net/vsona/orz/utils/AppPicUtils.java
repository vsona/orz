package net.vsona.orz.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;


/**
 * A picture load utility class.
 */
public final class AppPicUtils {

    public static DrawableRequestBuilder<String> url(Context context, String url) {
        return Glide.with(context)
                .fromString()
                .load(url)
//                .placeholder(R.drawable.placeholder);
//                .error(R.drawable.error)
//                .animate(R.anim.my_fancy_anim)
                ;
    }

    public static DrawableRequestBuilder<String> urlNoPlaceHolder(Context context, String url) {
        return Glide.with(context)
                .fromString()
                .load(url)
//                .placeholder(R.drawable.placeholder);
//                .error(R.drawable.error)
//                .animate(R.anim.my_fancy_anim)
                ;
    }

    public static void fill(ImageView mHeaderView, String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) {
            return;
        }
        url(AppContextUtils.get(), imgUrl).into(mHeaderView);
    }
}
