package bravostudio.togetherinspired.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import bravostudio.togetherinspired.Adapter.TopicAdapter;
import bravostudio.togetherinspired.ForumActivity;
import bravostudio.togetherinspired.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jouvyap on 4/27/16.
 */
public class TopicFragment extends Fragment {

    public static final String FORUM_TITLE = "bravostudio.togetherinspired.FORUM_TITLE";

    @Bind(R.id.gridview)
    GridView gridView;

    String[] test_topic_title = {"Family", "Finance", "Health", "Shopping", "Hobby"};

    public TopicFragment(){

    }

    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        gridView.setAdapter(new TopicAdapter(getActivity(), test_topic_title));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent forumIntent = new Intent(getActivity(), ForumActivity.class);
                forumIntent.putExtra(FORUM_TITLE, test_topic_title[position]);
                startActivity(forumIntent);
            }
        });

        return rootView;
    }
}
