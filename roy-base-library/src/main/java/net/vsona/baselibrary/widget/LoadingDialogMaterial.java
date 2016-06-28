package net.vsona.baselibrary.widget;

import android.content.Context;
import android.support.annotation.StringRes;

import net.vsona.baselibrary.R;

/**
 * Created by yesdoll on 16/4/22.
 */
public class LoadingDialogMaterial extends BaseMaterialProgressDialog{
    public LoadingDialogMaterial(Context context) {
        super(context);
    }

    @Override
    protected void dismissProgress() {
        super.dismissProgress();
    }

    @Override
    protected void showIndeterminate(CharSequence message) {
        super.showIndeterminate(message);
    }

    @Override
    protected void showIndeterminate(@StringRes int resId) {
        super.showIndeterminate(resId);
    }
    public void show(){
        showIndeterminate(R.string.loading);
    }
    public void dismiss(){
        dismissProgress();
    }
}
