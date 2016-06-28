package net.vsona.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by gaoruiyi on 15/9/10.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public List<T> mDataList;
    public LayoutInflater mInflater;

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDataList(List<T> list) {
        mDataList = list;
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

}

