package aviadapps.getfood;

import java.util.List;

/**
 * Created by Aviad on 17/01/2017.
 */

public class Company {
    // TODO: Finish the company class with it's Database entries.
    private int id;
    private String name;
    private List menu;
    private String phone;
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

    public void setMenu(List menu) {
        this.menu = menu;
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

    public List getMenu() {
        return menu;
    }
}
