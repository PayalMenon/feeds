package mdlive.test.example.com.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

import mdlive.test.example.com.pojo.Feed;
import mdlive.test.example.com.pojo.Meta;

/**
 * Created by payal.menon on 7/4/16.
 */
public class JsonResponseObj {
    @Expose
    private Meta meta;

    @Expose
    private List<Feed> data;

    public List<Feed> getDataList()
    {
        return this.data;
    }
}
