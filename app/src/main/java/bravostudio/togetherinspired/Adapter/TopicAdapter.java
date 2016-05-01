package bravostudio.togetherinspired.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bravostudio.togetherinspired.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jouvyap on 4/27/16.
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_topic, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTopicText.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public TopicAdapter(Context context, List<String> data){
        mContext = context;
        mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.topic_text) TextView mTopicText;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
