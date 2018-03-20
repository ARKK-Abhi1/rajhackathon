package in.leaf.abhi.rajasthanhackathon.pojos;

/**
 * Created by 500060150 on 20-03-2018.
 */

public class Temp {
    String category;
    String city;
    String rating;
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Temp() {}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Temp(String category, String city) {
        this.category=category;
        this.city=city;
    }



}
