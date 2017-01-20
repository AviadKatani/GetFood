package aviadapps.getfood;

/**
 * Created by Aviad on 17/01/2017.
 */

public class User {
    private int id;
    private String name;
    private String userName;
    private String address;

    public User() {
    }

    public User(int id, String name, String address, String userName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.userName = userName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getUserName() {
        return userName;
    }
}


