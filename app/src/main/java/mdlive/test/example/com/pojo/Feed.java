package mdlive.test.example.com.pojo;

/**
 * Created by payal.menon on 7/4/16.
 */
public class Feed {

    private String text;
    private String id;
    private User user;
    private String created_at;

    public void setPostText(String text)
    {
        this.text = text;
    }

    public String getPostText()
    {
        return this.text;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUser()
    {
        return this.user;
    }

    public void setCreatedAt(String createdAt)
    {
        this.created_at = createdAt;
    }

    public String getCreatedAt()
    {
        return this.created_at;
    }
}
