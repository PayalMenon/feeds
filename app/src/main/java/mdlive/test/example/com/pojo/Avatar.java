package mdlive.test.example.com.pojo;

/**
 * Created by payal.menon on 7/3/16.
 */
public class Avatar {

    private String url;
    private int width;
    private int height;

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setWidth(int width)
    {
        this.width =width;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setImageURl(String url)
    {
        this.url = url;
    }

    public String getImageURl()
    {
        return this.url;
    }
}
