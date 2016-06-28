package net.vsona.baselibrary.refresh;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import net.vsona.baselibrary.R;


/**
 * Created by gaoruiyi on 15/10/14.
 */
public class RefreshProxy implements RefreshableInterface {


    private SwipeRefreshLayout mSwipeRefreashLayout;
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener;
    private int mStepRefreshCount;

    public RefreshProxy bindingRefresh(@NonNull View layout, IRefreshProxyListener listener) {
        mSwipeRefreashLayout = (SwipeRefreshLayout) layout.findViewById(R.id.common_refreash_swipe_layout);
        mProxyListener = listener;
        if (mSwipeRefreashLayout == null) {
            throw new NullPointerException("SwipeRefreshLayout null");
        }
        mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mProxyListener != null) {
                    mProxyListener.onRefresh();
                }
            }
        };
        mSwipeRefreashLayout.setOnRefreshListener(mRefreshListener);
        mSwipeRefreashLayout.setColorSchemeResources(R.color.refreash_circle_1, R.color.refreash_circle_2, R.color.refreash_circle_3);
        return this;
    }

    public RefreshProxy setMultiChildrenId(int... ids) {
        if (mSwipeRefreashLayout instanceof MultiSwipeRefreshLayout) {
            ((MultiSwipeRefreshLayout) mSwipeRefreashLayout).setSwipeableChildren(ids);
        }
        return this;
    }

    public boolean isOnRefreshing() {
        return mSwipeRefreashLayout.isRefreshing();
    }

    public SwipeRefreshLayout getSwipeRefreashLayout() {
        return mSwipeRefreashLayout;
    }


    /**
     * 主动刷新
     */
    @Override
    public void onRefreshing() {
        if (mSwipeRefreashLayout.isRefreshing()) {
            mSwipeRefreashLayout.setRefreshing(false);
        }
        if (mSwipeRefreashLayout != null) {
            mSwipeRefreashLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreashLayout.setRefreshing(true);
                }
            });
        }
        if (mRefreshListener != null) {
            //Call onRefresh manually.
            mRefreshListener.onRefresh();
        }
    }

    public void setRefresh(boolean show) {
        if (mSwipeRefreashLayout == null) {
            return;
        }
        if (mSwipeRefreashLayout.isRefreshing()) {
            mSwipeRefreashLayout.setRefreshing(false);
        }
        if (mRefreshListener != null) {
            //Call onRefresh manually.
            setRefreshing(show);
            mRefreshListener.onRefresh();
        }
    }

    /**
     * 设置 刷新状态 样式
     *
     * @param refreshing
     */
    public void setRefreshing(boolean refreshing) {
        if (mSwipeRefreashLayout == null) {
            return;
        }
        mSwipeRefreashLayout.setRefreshing(refreshing);
    }

    @Override
    public void onRefreshCompleted() {
        setRefreshing(false);
    }

    IRefreshProxyListener mProxyListener;

    public interface IRefreshProxyListener {
        void onRefresh();

    }
}
