package net.vsona.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by gaoruiyi on 15/9/10.
 */
public abstract class BaseRecyclerViewLoadingAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public List<T> mDataList;
    public LayoutInflater mInflater;

    public static int TYPE_ITEM = 100;
    public static final int TYPE_FOOTER = 101;
    public final View mFooterView;
    //若没有数据 是否显示footer true : 显示
    public boolean noDataShowFooter;

    public void setDataList(List<T> list) {
        mDataList = list;
    }

    public BaseRecyclerViewLoadingAdapter(Context mContext, View footerView) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        mFooterView = footerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_ITEM) {
            return onCreateItemViewHolder(viewGroup, viewType);
        }
        if (viewType == TYPE_FOOTER) {
            viewGroup.addView(getFooterView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            return new FooterViewHolder(getFooterView());
        }
        return null;
    }

    protected View getFooterView() {
        if (mFooterView == null) {
            throw new RuntimeException("footerView null");
        }
        return mFooterView;
    }

    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType);


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof BaseItemViewHolder) {
            onItemBindViewHolder(viewHolder, position);
        }
    }

    public abstract void onItemBindViewHolder(RecyclerView.ViewHolder viewHolder, int position);


    @Override
    public int getItemCount() {
        return mDataList == null ? (noDataShowFooter ? 1 : 0) : mDataList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}

