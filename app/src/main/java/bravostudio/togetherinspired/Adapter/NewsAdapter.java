package bravostudio.togetherinspired.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bravostudio.togetherinspired.Model.NewsModel;
import bravostudio.togetherinspired.NewsContentActivity;
import bravostudio.togetherinspired.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jouvyap on 4/27/16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    public final static String EXTRA_NEWS_MODEL = "bravo.togetherinspired.NEWS_MODEL";
    private static Context mContext;
    private static List<NewsModel> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frame_news, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNewsTitle.setText(mData.get(position).getTitle());
        holder.mNewsTag.setText(mData.get(position).getTag());

        switch (mData.get(position).getTag()){
            case "Family":
                holder.mNewsTagCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagFamily));
                break;
            case "Finance":
                holder.mNewsTagCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagFinance));
                break;
            case "Health":
                holder.mNewsTagCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagHealth));
                break;
            case "Shopping":
                holder.mNewsTagCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                        .colorTagShopping));
                break;
            case "Hobby":
                holder.mNewsTagCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
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

    public NewsAdapter(Context context, List<NewsModel> data){
        mContext = context;
        mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.news_title) TextView mNewsTitle;
        @Bind(R.id.news_tag) TextView mNewsTag;
        @Bind(R.id.news_tag_card_view) CardView mNewsTagCardView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newsContentIntent = new Intent(mContext, NewsContentActivity.class);
                    newsContentIntent.putExtra(EXTRA_NEWS_MODEL, mData.get(getAdapterPosition()));
                    mContext.startActivity(newsContentIntent);
                }
            });
        }
    }
}
