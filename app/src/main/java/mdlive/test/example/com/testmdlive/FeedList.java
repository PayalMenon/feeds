package mdlive.test.example.com.testmdlive;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import mdlive.test.example.com.cache.ImageController;
import mdlive.test.example.com.pojo.FeedInfo;

/**
 * Created by payal.menon on 7/3/16.
 */
public class FeedList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        RecyclerView feedList = (RecyclerView) getActivity().findViewById(R.id.feedList);
        feedList.setHasFixedSize(true);
        feedList.setAdapter(new FeedListAdapter(getActivity()));
        LinearLayoutManager mngr = new LinearLayoutManager(getActivity());
        mngr.setOrientation(LinearLayoutManager.VERTICAL);
        feedList.setLayoutManager(mngr);
    }

    private void initializeDefaultCard()
    {

    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder
    {
        public NetworkImageView avatar;
        public TextView postTitle;
        public TextView post;

        public FeedViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.card_fragment, parent, false));
            avatar = (NetworkImageView) itemView.findViewById(R.id.feed_avatar);
            postTitle = (TextView) itemView.findViewById(R.id.poster_title);
            post = (TextView) itemView.findViewById(R.id.poster_text);
        }
    }

    public static class FeedListAdapter extends RecyclerView.Adapter<FeedViewHolder>
    {
        private List<FeedInfo> feedList;
        private Activity adapterActivity;
        ImageLoader imageLoader = ImageController.getInstance().getImageLoader();

        public FeedListAdapter(Activity activity) {
            adapterActivity = activity;
            feedList = ((MainActivity) activity).getFeedList();
        }

        @Override
        public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FeedViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(FeedViewHolder holder, int position) {
            if (imageLoader == null)
                imageLoader = ImageController.getInstance().getImageLoader();
            int pos = feedList.size() - position - 1;
            FeedInfo feedInfo = feedList.get(pos);
            holder.postTitle.setText(feedInfo.getPostTitle());
            holder.post.setText(feedInfo.getPost());
            holder.avatar.setDefaultImageResId(R.drawable.ic_launcher);
            holder.avatar.setImageUrl(feedInfo.getAvatarUrl(), imageLoader);
        }

        @Override
        public int getItemCount() {
            return feedList.size();
        }
    }
}
