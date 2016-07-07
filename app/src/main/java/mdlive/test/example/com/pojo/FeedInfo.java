package mdlive.test.example.com.pojo;

/**
 * Created by payal.menon on 7/3/16.
 */
public class FeedInfo {

    private String postTitle;
    private String post;
    private String avatarUrl;
    private String timeStamp;

    public void setPostTitle(String title)
    {
        this.postTitle = title;
    }

    public String getPostTitle()
    {
        return this.postTitle;
    }

    public void setPost(String post)
    {
        this.post = post;
    }

    public String getPost()
    {
        return this.post;
    }

    public void setAvatarUrl(String url)
    {
        this.avatarUrl = url;
    }

    public String getAvatarUrl()
    {
        return this.avatarUrl;
    }

    public void setTimeStamp(String time)
    {
        this.timeStamp = time;
    }

    public String getTimeStamp()
    {
        return this.timeStamp;
    }
}
