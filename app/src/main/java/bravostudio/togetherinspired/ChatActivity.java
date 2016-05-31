package bravostudio.togetherinspired;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import bravostudio.togetherinspired.Adapter.ChatAdapter;
import bravostudio.togetherinspired.Adapter.ForumAdapter;
import bravostudio.togetherinspired.Model.ChatModel;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @Bind(R.id.chat_linear_layout) LinearLayout mChatRoot;
    @Bind(R.id.chat_box) EditText mChatBox;
    @Bind(R.id.chat_send) ImageButton mChatSend;
    @Bind(R.id.chat_recycler_view) RecyclerView mRecyclerView;

    private List<ChatModel> mChatList = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String forumType = intent.getStringExtra(ForumAdapter.FORUM_TYPE);
        String forumTitle = intent.getStringExtra(ForumAdapter.FORUM_TITLE);
        setTitle(forumTitle);
        switch (forumType){
            case "Family":
                mChatRoot.setBackgroundColor(getResources().getColor(R.color.colorTagFamilySoft));
                break;
            case "Finance":
                mChatRoot.setBackgroundColor(getResources().getColor(R.color.colorTagFinanceSoft));
                break;
            case "Health":
                mChatRoot.setBackgroundColor(getResources().getColor(R.color.colorTagHealthSoft));
                break;
            case "Shopping":
                mChatRoot.setBackgroundColor(getResources().getColor(R.color.colorTagShoppingSoft));
                break;
            case "Hobby":
                mChatRoot.setBackgroundColor(getResources().getColor(R.color.colorTagHobbySoft));
                break;
            default:
                break;
        }

        mChatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newChat();
            }
        });

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ChatAdapter(this, mChatList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void newChat(){
        if(!mChatBox.getText().toString().equals("")) {
            mChatList.add(new ChatModel("Jouvy", "In a moment", mChatBox.getText().toString()));
            mChatBox.setText("");
            mAdapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(mChatList.size() - 1);
        }
    }
}
