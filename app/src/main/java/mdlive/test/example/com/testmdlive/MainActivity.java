package mdlive.test.example.com.testmdlive;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import mdlive.test.example.com.pojo.Avatar;
import mdlive.test.example.com.pojo.Feed;
import mdlive.test.example.com.pojo.FeedInfo;
import mdlive.test.example.com.pojo.JsonResponseObj;
import mdlive.test.example.com.pojo.User;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static String FEED_FRGMENT_ID = "feed_fragment";
    FeedService service;
    Subscription subscription;
    FrameLayout pauseLayout;
    TextView pauseText;
    FloatingActionButton pauseButton;

    List<FeedInfo> infoList = new ArrayList<FeedInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pauseText = (TextView)findViewById(R.id.floating_text);
        pauseLayout = (FrameLayout) findViewById(R.id.floating_pause_button);
        pauseButton = (FloatingActionButton) findViewById(R.id.pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = pauseText.getText().toString();
                if(text.equals(getApplicationContext().getResources().getText(R.string.pause)))
                {
                    pauseText.setText(R.string.play);
                    unsubscribeFeeds();
                }
                else
                {
                    getNewsFeed();
                    pauseText.setText(R.string.pause);
                }

            }
        });
        getNewsFeed();
    }

    private void showFeedList()
    {
        ProgressBar bar = (ProgressBar)findViewById(R.id.fetching_feed_progress);
        bar.setVisibility(View.GONE);
        TextView text = (TextView) findViewById(R.id.fetching_feed_text);
        text.setVisibility(View.GONE);
        FragmentManager mngr = getFragmentManager();
        FragmentTransaction transaction = mngr.beginTransaction();
        FeedList frg = new FeedList();
        transaction.add(R.id.fragment_container, frg, FEED_FRGMENT_ID);
        transaction.commit();

        pauseLayout.setVisibility(View.VISIBLE);
    }

    public List<FeedInfo> getFeedList()
    {
        return infoList;
    }

    private static String NEWS_FEED_URL = "https://alpha-api.app.net";

    public void getNewsFeed() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NEWS_FEED_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        service = retrofit.create(FeedService.class);

        Observable<JsonResponseObj> request =  Observable.interval(0, 20, TimeUnit.SECONDS, Schedulers.newThread())
        .flatMap(new Func1<Long, Observable<JsonResponseObj>>() {
            @Override
            public Observable<JsonResponseObj> call(Long aLong) {
                return service.getFeeds();
            }
        })
        .observeOn(AndroidSchedulers.mainThread());
        subscription = request.subscribe(new Subscriber<JsonResponseObj>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(JsonResponseObj jsonResponseObj) {
                List<Feed> data = jsonResponseObj.getDataList();
                if (data != null) {
                    parseData(data);
                    showFeedList();
                }
            }
        });
    }

    private void parseData(List<Feed> resultsArray)
    {
        for (int i = 0; i < resultsArray.size(); i++) {
            Feed data = resultsArray.get(i);
            String postData = data.getPostText();
            String id = data.getId();
            User user = data.getUser();
            String timeStamp = data.getCreatedAt();
            String url = null;
            Avatar avatar = user.getAvatar();
            if(null != avatar) {
                url = avatar.getImageURl();
            }
            String postName = user.getUserName();

            FeedInfo info = new FeedInfo();
            info.setPostTitle(postName);
            info.setPost(postData);
            info.setAvatarUrl(url);
            info.setTimeStamp(timeStamp);

            infoList.add(info);
        }
    }

    private void unsubscribeFeeds()
    {
        if(false == subscription.isUnsubscribed())
        {
            subscription.unsubscribe();
        }
    }
}
