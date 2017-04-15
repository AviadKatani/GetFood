package aviadapps.getfood;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Aviad on 17/01/2017.
 */

public class Company {
    private int id;
    private String name;
    private String menu;
    private String phone;
    private String emailAddress;
    private String address;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMenu(String menu) {
        this.menu = menu;
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

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getMenu() {
        return menu;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
