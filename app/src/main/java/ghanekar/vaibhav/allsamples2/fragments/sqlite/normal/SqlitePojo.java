package ghanekar.vaibhav.allsamples2.fragments.sqlite.normal;

import java.util.ArrayList;

public class SqlitePojo {

    private int id;
    private String name;
    private String address;

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_SOME_COLUMN= "some_column";

    public SqlitePojo() {

    }

    public SqlitePojo(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getKeyAddress() {
        return KEY_ADDRESS;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SqlitePojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
