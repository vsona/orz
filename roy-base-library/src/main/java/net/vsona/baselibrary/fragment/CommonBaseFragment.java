package net.vsona.baselibrary.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class CommonBaseFragment extends Fragment{

    public View mRootView;
    public Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getRootLayoutResource(),container,false);
        return mRootView;
    }

    protected abstract @LayoutRes int getRootLayoutResource();

    @Override
    public void onAttach(Activity activity) {
        mActivity = activity;
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initView();
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract void initView();
    protected abstract void initData();
}
