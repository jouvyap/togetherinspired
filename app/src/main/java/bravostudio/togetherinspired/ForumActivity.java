package bravostudio.togetherinspired;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bravostudio.togetherinspired.Adapter.ForumAdapter;
import bravostudio.togetherinspired.Fragment.TopicFragment;
import bravostudio.togetherinspired.Model.ForumModel;
import bravostudio.togetherinspired.Model.NewsModel;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ForumActivity extends AppCompatActivity {

    @Bind(R.id.thread_recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String forumTitle = intent.getStringExtra(TopicFragment.FORUM_TITLE);

        setTitle(forumTitle);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        /*
            DATA DUMMY HERE
         */
        List<ForumModel> testDataDummy = new ArrayList<>();
        testDataDummy.add(new ForumModel("dr Agustin", "19:45 WIB | 27 Mar", "Membangun cita cita" +
                " dan harapan"));
        testDataDummy.add(new ForumModel("Ustad Saiful", "04:35 WIB | 1 Jun", "Kiat kiat mengurus" +
                " anak ketika bekerja"));
        testDataDummy.add(new ForumModel("Jamiludin Mubarok", "20:00 WIB | 3 Jun", "Kisah cinta " +
                "dua manusia"));

        mAdapter = new ForumAdapter(this, forumTitle, testDataDummy);
        mRecyclerView.setAdapter(mAdapter);
    }
}
