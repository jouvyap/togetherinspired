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

import java.util.ArrayList;
import java.util.List;

import bravostudio.togetherinspired.Adapter.NewsAdapter;
import bravostudio.togetherinspired.MainActivity;
import bravostudio.togetherinspired.Model.NewsModel;
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
public class NewsFragment extends Fragment {

    @Bind(R.id.news_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public NewsFragment(){

    }

    public static NewsFragment newInstance(){
        NewsFragment newsFragment = new NewsFragment();
        return newsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, rootView);

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorTagFamily,
                R.color.colorTagFinance,
                R.color.colorTagHealth,
                R.color.colorTagHobby,
                R.color.colorTagShopping
        );
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refreshTopic();
            }
        });

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        /*
            DATA DUMMY HERE
         */
        List<NewsModel> testDataDummy = new ArrayList<>();
        testDataDummy.add(new NewsModel("Menyimak Kesuksesan Film 'The Angry Birds Movie' " +
                "Adaptasi dari Game", "Hobby", "Content Here"));
        testDataDummy.add(new NewsModel("Asuransi Astra Luncurkan Garda Mobile, Bukti Nyata " +
                "Sebagai Asuransi Paling Digital", "Finance", "Content Here"));
        testDataDummy.add(new NewsModel("Pertama di Indonesia, Aplikasi Ini Mentraktir dan Beri " +
                "Diskon Hingga 30% Setiap Anda Belanja", "Shopping", "Content Here"));
        testDataDummy.add(new NewsModel("Studi: Merokok Selama Hamil Tingkatkan Risiko " +
                "Skizofrenia pada Bayi Kelak", "Health", "Content Here"));
        testDataDummy.add(new NewsModel("Digugat Cerai, Johnny Depp Tetap Tersenyum di Premier " +
                "Film Terbaru", "Family", "Content Here"));
        testDataDummy.add(new NewsModel("Vespa Primavera dan Sprint Model Lama Habiskan Stok " +
                "Hingga Akhir Tahun 2016", "Hobby", "Content Here"));
        testDataDummy.add(new NewsModel("Bank Ini Kebobolan Rp 176 Miliar Dalam 2 jam",
                "Finance", "Content Here"));

        RecyclerView.Adapter mAdapter = new NewsAdapter(getActivity(), testDataDummy);
        mRecyclerView.setAdapter(mAdapter);

//        refreshTopic();
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

//                RecyclerView.Adapter mAdapter = new NewsAdapter(getActivity(), topicModel.getData());
//                mRecyclerView.setAdapter(mAdapter);
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
