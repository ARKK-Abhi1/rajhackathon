package in.leaf.abhi.rajasthanhackathon.pojos;

/**
 * Created by 500060150 on 14-03-2018.
 */

public class User {
    private String name;
    private String email;
    private String category;
    public User() {

    }
    public User(String name,String email) {
        this.name=name;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setEmail(String email) {
        this.email=email;
    }

}
