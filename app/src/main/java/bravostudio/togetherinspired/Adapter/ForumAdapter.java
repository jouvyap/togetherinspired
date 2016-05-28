package bravostudio.togetherinspired.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bravostudio.togetherinspired.Model.ForumModel;
import bravostudio.togetherinspired.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jouvyap on 5/28/16.
 */
public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {

    private Context mContext;
    private String mForumType;
    private List<ForumModel> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_thread, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mSpeaker.setText(mData.get(position).getSpeaker());
        holder.mTime.setText(mData.get(position).getTime());
        holder.mTitle.setText(mData.get(position).getTitle());

        switch (mForumType){
            case "Family":
                holder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagFamily));
                break;
            case "Finance":
                holder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagFinance));
                break;
            case "Health":
                holder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagHealth));
                break;
            case "Shopping":
                holder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagShopping));
                break;
            case "Hobby":
                holder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagHobby));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ForumAdapter(Context context, String forumType, List<ForumModel> data){
        mContext = context;
        mForumType = forumType;
        mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.thread_card_view) CardView mCardView;
        @Bind(R.id.thread_speaker) TextView mSpeaker;
        @Bind(R.id.thread_time) TextView mTime;
        @Bind(R.id.thread_title) TextView mTitle;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
