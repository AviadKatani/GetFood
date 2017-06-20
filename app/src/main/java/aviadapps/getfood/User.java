/**
 * User class is responsible to contain the full information about users.
 */
package aviadapps.getfood;

/**
 * Created by Aviad on 17/01/2017.
 */

public class User {
    private int id;
    private String name;
    private String phone;
    private String userName;
    private String userPassword;
    private String emailAddress;
    private String address;

    public User() {
    }

    public User(int id, String name, String address, String phone, String userName, String emailAddress, String userPassword) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phone = phone;
        this.userName = userName;
        this.userPassword = userPassword;
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
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserPassword(String password) {
        this.userPassword = password;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
    public String getPhone() {
        return phone;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
}