package net.vsona.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by gaoruiyi on 15/9/10.
 */
public abstract class BaseRecyclerViewHeaderAdapter<T> extends BaseRecyclerViewLoadingAdapter<T> {

    public static final int TYPE_HEADER = 102;
    public View mHeaderView;

    public void setDataList(List<T> list) {
        mDataList = list;
    }

    public BaseRecyclerViewHeaderAdapter(Context mContext, View footerView, View headerView) {
        super(mContext, footerView);
        this.mContext = mContext;
        mHeaderView = headerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            return onCreateItemViewHolder(viewGroup, viewType);
        }
        if (viewType == TYPE_HEADER) {
            viewGroup.addView(getHeaderView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            return new HeaderViewHolder(getHeaderView());
        }
        return null;
    }

    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof BaseItemViewHolder) {
            onItemBindViewHolder(viewHolder, position - 1);//减去header的posttion
        }
    }


    @Override
    public int getItemCount() {
        return mDataList == null ? 1 : mDataList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public View getHeaderView() {
        if (mHeaderView == null) {
            throw new RuntimeException("headerView null");
        }
        return mHeaderView;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}

