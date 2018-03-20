package in.leaf.abhi.rajasthanhackathon.pojos;

/**
 * Created by 500060150 on 20-03-2018.
 */

public class Place {

    String name;
    String city;
    Place() {

    }
    public Place(String name,String city) {
        this.name=name;
        this.city=city;
    }
    public String getName() {
        return name;
    }

    public void setName(String name, String city) {
        this.name = name;
        this.city=city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
