package net.vsona.baselibrary.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import net.vsona.baselibrary.R;


/**
 * Created by gaoruiyi on 15/10/19.
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {
    private CanChildScrollUpCallback mCanChildScrollUpCallback;

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
        setId(R.id.common_refreash_swipe_layout);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface CanChildScrollUpCallback {
        boolean canSwipeRefreshChildScrollUp();
    }

    public void setCanChildScrollUpCallback(CanChildScrollUpCallback canChildScrollUpCallback) {
        mCanChildScrollUpCallback = canChildScrollUpCallback;
    }

    @Override
    public boolean canChildScrollUp() {
        if (mCanChildScrollUpCallback != null) {
            return mCanChildScrollUpCallback.canSwipeRefreshChildScrollUp();
        }
        return super.canChildScrollUp();
    }
}
