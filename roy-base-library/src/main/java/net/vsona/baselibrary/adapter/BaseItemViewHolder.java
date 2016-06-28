package net.vsona.baselibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gaoruiyi on 15/10/27.
 * RecyclerView 的基础 ItemViewHolder
 */
public class BaseItemViewHolder extends RecyclerView.ViewHolder{
    public View mView;
    public BaseItemViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }
}
