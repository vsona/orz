package net.vsona.baselibrary.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by gaoruiyi on 15/10/27.
 */
public abstract class OnRecyclerViewLastItemVisiableListener extends RecyclerView.OnScrollListener {

    int lastVisibleItem = 0;
    int itemPlus = 1;

    public OnRecyclerViewLastItemVisiableListener() {
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItem + itemPlus == getItemCount()) {
            // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
            onLastItemVisiable();
        }
        actionScrollStateChanged();
    }
    public void actionScrollStateChanged(){

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        lastVisibleItem = findLastVisiableItemPosttion();
    }
    public abstract int getItemCount();
    public abstract int findLastVisiableItemPosttion();

    public abstract void onLastItemVisiable();
}
