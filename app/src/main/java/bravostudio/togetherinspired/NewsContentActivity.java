package bravostudio.togetherinspired;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import bravostudio.togetherinspired.Adapter.NewsAdapter;
import bravostudio.togetherinspired.Model.NewsModel;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsContentActivity extends AppCompatActivity {

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Share via TogetherInspired Mobile Apps.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        NewsModel newsModel = (NewsModel) intent.getSerializableExtra(NewsAdapter.EXTRA_NEWS_MODEL);

        updateUI(newsModel);
    }

    private void updateUI(NewsModel newsModel){
        this.setTitle(newsModel.getTitle());
        switch (newsModel.getTag()){
            case "Family":
                collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color
                        .colorTagFamily));
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color
                        .colorTagFamily));
                break;
            case "Finance":
                collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color
                        .colorTagFinance));
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color
                        .colorTagFinance));
                break;
            case "Health":
                collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color
                        .colorTagHealth));
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color
                        .colorTagHealth));
                break;
            case "Shopping":
                collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color
                        .colorTagShopping));
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color
                        .colorTagShopping));
                break;
            case "Hobby":
                collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color
                        .colorTagHobby));
                collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color
                        .colorTagHobby));
                break;
            default:
                break;
        }
    }
}
