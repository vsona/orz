package net.vsona.baselibrary.view;

import android.support.annotation.StringRes;

/**
 * Created by yesdoll on 16/4/26.
 */
public interface ShowMessageView {
    void showMessage(String msg);

    void showMessage(@StringRes int res);
}
