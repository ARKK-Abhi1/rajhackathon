package in.leaf.abhi.rajasthanhackathon.pojos;

/**
 * Created by 500060150 on 19-03-2018.
 */

public class Category {
    public Category(String imageurl, String name) {
        this.imageurl = imageurl;
        this.name = name;
    }
    public Category() {

    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String imageurl;
    String name;
}
