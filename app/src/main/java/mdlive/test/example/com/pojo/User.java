package mdlive.test.example.com.pojo;

import mdlive.test.example.com.pojo.Avatar;

/**
 * Created by payal.menon on 7/3/16.
 */
public class User {

    private String name;
    private Avatar avatar_image;

    public void setUserName(String userName)
    {
        this.name = userName;
    }

    public String getUserName()
    {
        return this.name;
    }

    public void setAvatar(Avatar avatar)
    {
        this.avatar_image = avatar;
    }

    public Avatar getAvatar()
    {
        return this.avatar_image;
    }
}
