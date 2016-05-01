package bravostudio.togetherinspired.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bravostudio.togetherinspired.MainActivity;
import bravostudio.togetherinspired.Model.NewsModel;
import bravostudio.togetherinspired.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jouvyap on 4/27/16.
 */
public class NewsFragment extends Fragment {

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

        Call<NewsModel> call = ((MainActivity) getActivity()).getApiEndpointInterface().getNews();
        call.enqueue(new Callback<NewsModel>(){

            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                int statusCode = response.code();

                Log.d("JOUVY NEWS", "Success, Code: " + statusCode);

                NewsModel newsModel =  response.body();
                Log.d("JOUVY NEWS", newsModel.getText());
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.d("JOUVY NEWS", "FAILURE");
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
