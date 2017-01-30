package aviadapps.getfood;

/**
 * Created by Aviad on 17/01/2017.
 */

public class User {
    private String name;
    private int phone;
    private String userName;
    private String userPassword;

    public User() {
    }

    public User(String name, int phone, String userName, String userPassword) {
        this.name = name;
        this.phone = phone;
        this.userName = userName;
        this.userPassword = userPassword;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserPassword(String password) {
        this.userPassword = password;
    }
    public String getName() {
        return name;
    }
    public int getPhone() {
        return phone;
    }
    public String getUserName() {return userName; }
    public String getUserPassword() {
        return userPassword;
    }
}


