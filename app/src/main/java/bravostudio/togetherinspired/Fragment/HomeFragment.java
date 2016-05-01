package bravostudio.togetherinspired.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bravostudio.togetherinspired.Adapter.TopicAdapter;
import bravostudio.togetherinspired.MainActivity;
import bravostudio.togetherinspired.Model.TopicModel;
import bravostudio.togetherinspired.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jouvyap on 4/27/16.
 */
public class HomeFragment extends Fragment {

    @Bind(R.id.home_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public HomeFragment(){

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTopic();
            }
        });

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        refreshTopic();
        return rootView;
    }

    private void refreshTopic(){
        mSwipeRefreshLayout.setRefreshing(true);

        Call<TopicModel> call = ((MainActivity) getActivity()).getApiEndpointInterface().getTopic();
        call.enqueue(new Callback<TopicModel>() {
            @Override
            public void onResponse(Call<TopicModel> call, Response<TopicModel> response) {
                int statusCode = response.code();

                Log.d("JOUVY HOME", "Success, Code: " + statusCode);

                TopicModel topicModel = response.body();

                RecyclerView.Adapter mAdapter = new TopicAdapter(getActivity(), topicModel.getData());
                mRecyclerView.setAdapter(mAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<TopicModel> call, Throwable t) {
                Log.d("JOUVY HOME", "Failure");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
