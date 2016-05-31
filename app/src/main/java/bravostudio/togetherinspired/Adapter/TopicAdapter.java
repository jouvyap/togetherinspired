package bravostudio.togetherinspired.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import bravostudio.togetherinspired.R;

/**
 * Created by jouvyap on 5/26/16.
 */
public class TopicAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mTopicTitle;

    public TopicAdapter(Context c, String[] topicTitle) {
        mContext = c;
        mTopicTitle = topicTitle;
    }

    public int getCount() {
        return mTopicTitle.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.frame_topic, null);

            CardView cardView = (CardView) gridView.findViewById(R.id.topic_card_view);
            ImageView imageView = (ImageView) gridView.findViewById(R.id.topic_image);
            TextView textView = (TextView) gridView.findViewById(R.id.topic_text);

            switch (mTopicTitle[position]){
                case "Family":
                    cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                            .colorTagFamily));
                    imageView.setImageResource(R.mipmap.ic_face_white_48dp);
                    break;
                case "Finance":
                    cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                            .colorTagFinance));
                    imageView.setImageResource(R.mipmap.ic_attach_money_white_48dp);
                    break;
                case "Health":
                    cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                            .colorTagHealth));
                    imageView.setImageResource(R.mipmap.ic_healing_white_48dp);
                    break;
                case "Shopping":
                    cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                            .colorTagShopping));
                    imageView.setImageResource(R.mipmap.ic_shopping_cart_white_48dp);
                    break;
                case "Hobby":
                    cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color
                            .colorTagHobby));
                    imageView.setImageResource(R.mipmap.ic_videogame_asset_white_48dp);
                    break;
                default:
                    break;
            }

            textView.setText(mTopicTitle[position]);
        } else {
            gridView = convertView;
        }

        return gridView;
    }
}