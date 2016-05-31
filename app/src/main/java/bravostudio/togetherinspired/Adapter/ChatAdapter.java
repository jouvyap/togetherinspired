package bravostudio.togetherinspired.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bravostudio.togetherinspired.Model.ChatModel;
import bravostudio.togetherinspired.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jouvyap on 5/29/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private Context mContext;
    private List<ChatModel> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_chat_list_left, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mChatSender.setText(mData.get(position).getChatSender());
        holder.mChatTimestamp.setText(mData.get(position).getChatTimestamp());
        holder.mChatMessage.setText(mData.get(position).getChatMessage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ChatAdapter(Context mContext, List<ChatModel> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.chat_sender) TextView mChatSender;
        @Bind(R.id.chat_timestamp) TextView mChatTimestamp;
        @Bind(R.id.chat_message) TextView mChatMessage;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }
}
