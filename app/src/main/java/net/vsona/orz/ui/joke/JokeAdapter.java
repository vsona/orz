package net.vsona.orz.ui.joke;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import net.vsona.baselibrary.adapter.BaseItemViewHolder;
import net.vsona.baselibrary.adapter.BaseRecyclerViewLoadingAdapter;
import net.vsona.orz.R;
import net.vsona.orz.domain.ContentlistEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by roy on 16/6/28.
 */
public class JokeAdapter extends BaseRecyclerViewLoadingAdapter<ContentlistEntity> {

    public JokeAdapter(Context mContext, View footerView) {
        super(mContext, footerView);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_joke,
                viewGroup, false);
        return new JockViewHolder(view);
    }

    @Override
    public void onItemBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        JockViewHolder jokeViewHolder = (JockViewHolder) viewHolder;
        ContentlistEntity contentlistEntity = mDataList.get(position);


        jokeViewHolder.title.setText("#" + contentlistEntity.getTitle() + "#");
        jokeViewHolder.time.setText(getDateBySplit(contentlistEntity.getCt()));
        /*使html中<标签>得以转化*/
        jokeViewHolder.content.setText(Html.fromHtml(contentlistEntity.getText().toString()));
    }

    static class JockViewHolder extends BaseItemViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.content)
        TextView content;

        public JockViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, mView);
        }
    }

    public static String getDateBySplit(String str) {
        String[] strings = str.split(" ");
        return strings[0];
    }
}
