package bravostudio.togetherinspired.Interface;

import bravostudio.togetherinspired.Model.NewsModel;
import bravostudio.togetherinspired.Model.TopicModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jouvyap on 4/28/16.
 */
public interface ApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("get_topic.php")
    Call<TopicModel> getTopic();

    @GET("get_news.php")
    Call<NewsModel> getNews();
}
