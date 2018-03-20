package in.leaf.abhi.rajasthanhackathon.pojos;

/**
 * Created by 500060150 on 20-03-2018.
 */

public class Temp_Rating {
    String rating;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String category;

    public Temp_Rating(){
    }

    public Temp_Rating(String rating,String category) {
        this.rating=rating;
        this.category=category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
