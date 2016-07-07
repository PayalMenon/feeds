package mdlive.test.example.com.testmdlive;


import mdlive.test.example.com.pojo.JsonResponseObj;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by payal.menon on 7/4/16.
 */
public interface FeedService {

    @GET("stream/0/posts/stream/global")
    Observable<JsonResponseObj> getFeeds();
}
